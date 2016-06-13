package com.maybe.live.presentation.controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.maybe.live.domain.Token;
import com.maybe.live.domain.Type;
import com.maybe.live.domain.User;
import com.maybe.live.kit.WebKit;
import com.maybe.live.kit.qiniu.QiNiuPicResp;
import com.maybe.live.presentation.form.LoginForm;
import com.maybe.live.presentation.form.RegisterForm;
import com.maybe.live.service.IFileService;
import com.maybe.live.service.ISendMailService;
import com.maybe.live.service.ITokenService;
import com.maybe.live.service.IUserService;
import com.maybe.live.support.Config;
import com.maybe.live.support.CustomResp;
import com.maybe.live.support.NeedLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tate on 2016/5/2 0002.
 */

@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ISendMailService iSendMailService;
    @Autowired
    private ITokenService iTokenService;
    @Autowired
    private IFileService iFileService;


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin(@RequestParam(value = "r", required = false) String returnUrl) {
        if (!Strings.isNullOrEmpty(returnUrl)) {
            ctx().session(true).setAttribute(Config.RETURN_URL, returnUrl);
        }
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String toSignUp() {
        return "register";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doLogin(@Valid LoginForm userForm, BindingResult bindingResult) {
        CustomResp validate = validateForm(bindingResult);
        if (validate != null) {
            return validate;
        }
        if (!iUserService.userIsValid(userForm.getEmail())) {
            return CustomResp.withMsgKey(204);
        }
        User user = iUserService.login(userForm.getEmail(), userForm.getPassword());
        ctx().setLoginSession(user);
        log.info("uid: {} 登录成功!", user.getId());
        String returnUrl = ctx().getSessionAttribute(Config.RETURN_URL);
        if (!Strings.isNullOrEmpty(returnUrl)) {
            return CustomResp.withMsgKey(200, returnUrl);
        }
        return CustomResp.withMsgKey(200);
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult, @ModelAttribute User user) {
        CustomResp validate = validateForm(bindingResult);
        if (validate != null) {
            return validate;
        }
        iUserService.register(user);
        Token token = iTokenService.createToken(user.getEmail());
        iSendMailService.sendEmailOfRegistered(user.getEmail(), token.getToken());
        User regUser = iUserService.login(user.getEmail());
        ctx().setLoginSession(regUser);
        log.info("email: {} 注册成功! ", user.getEmail());
        return CustomResp.withMsgKey(200);

    }

    @RequestMapping("active/{token}")
    public String toActivateToken(@PathVariable("token") String token, Model model) {
        User user = iUserService.findUserByToken(token);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("token", token);
        return "active";
    }

    @RequestMapping(value = "active", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doActivateToken(@RequestParam("token") String token) {
        iUserService.registerTokenValidate(token);
        iUserService.clearTokenOfRegistered(token);
        return CustomResp.withMsgKey(200);
    }

    @RequestMapping("forgot")
    public String toForgot() {
        return "forgot";
    }

    @RequestMapping(value = "sendResetMail", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doSendResetMail(@RequestParam("email") String email) {
        if (!iUserService.userIsValid(email)) {
            return CustomResp.withMsgKey(204);
        }
        Token token = iTokenService.createToken(email);
        iSendMailService.sendEmailOfForGot(email, token.getToken(), ctx().ip(), ctx().ua(), ctx().now().toString());
        return CustomResp.withMsgKey(200);
    }

    @RequestMapping("logout")
    public String doLogout() {
        ctx().clearLoginSession();
        return "redirect:/";
    }


    @RequestMapping("reset/{token}")
    public String toReset(@PathVariable("token") String token, Model model) {
        User user = iUserService.findUserByToken(token);
        model.addAttribute("email", user.getEmail());
        iUserService.forgotTokenValidate(token);
        return "reset";
    }

    @RequestMapping(value = "reset", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doReset(@RequestParam("newPassword") String newPassword,
                              @RequestParam("email") String email) {
        try {
            Preconditions.checkState(!Strings.isNullOrEmpty(newPassword), "请输入新的密码");
            Preconditions.checkState(newPassword.length() >= 6 && newPassword.length() <= 100, "密码长度为6-100个字符");
        } catch (IllegalStateException e) {
            return CustomResp.withMsgKey(400, e.getMessage());
        }
        iUserService.resetPassword(newPassword, email);
        return CustomResp.withMsgKey(200);
    }

    @RequestMapping(value = "modifyName", method = RequestMethod.POST)
    @ResponseBody
    @NeedLogin
    public CustomResp doModifyName(@RequestParam("newName") String newName) {
        try {
            Preconditions.checkState(Strings.isNullOrEmpty(newName), "请输入新的昵称");
            Preconditions.checkState(newName.length() < 2 && newName.length() > 25, "昵称长度为2-25个字符");
        } catch (IllegalStateException e) {
            return CustomResp.withMsgKey(400, e.getMessage());
        }
        iUserService.modifyName(newName, getUser().getId());
        return CustomResp.withMsgKey(200);
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp uploadTest(@RequestParam("files") MultipartFile[] files) {
        List<QiNiuPicResp> qiNiuPicResp = iFileService.uploadPicToQiNiu(files);
        return CustomResp.withMsgKey(200, qiNiuPicResp);
    }

    @RequestMapping("profile")
    @NeedLogin
    public String toProfile() {
        return "profile";
    }


}

