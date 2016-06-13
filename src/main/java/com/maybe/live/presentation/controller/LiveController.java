package com.maybe.live.presentation.controller;

import com.maybe.live.domain.Content;
import com.maybe.live.domain.Type;
import com.maybe.live.presentation.form.LiveForm;
import com.maybe.live.service.ILiveService;
import com.maybe.live.support.CustomResp;
import com.maybe.live.support.NeedLogin;
import com.maybe.live.support.ServletContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tate on 2016/6/1 0001.
 */
@Controller
@RequestMapping("live")
@NeedLogin
public class LiveController extends BaseController {

    @Autowired
    private ILiveService liveService;

    @RequestMapping("")
    public String toCreateLive(Model model) {
        List<Type> types = ServletContext.getLiveTypes();
        Content liveOfUser = liveService.getLiveOfUser(getUser().getId());
        if (liveOfUser==null) {
            model.addAttribute("live", liveOfUser);
            return "create_live";
        }else{
            model.addAttribute("types", types);
            return "my_live";
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public CustomResp doCreateLive(@Valid LiveForm liveForm, BindingResult bindingResult) {
        CustomResp validate = validateForm(bindingResult);
        if (validate != null) {
            return validate;
        }
        Content content = new Content();
        BeanUtils.copyProperties(liveForm, content);
        liveService.createLive(content);
        return CustomResp.withMsgKey(200);
    }

}
