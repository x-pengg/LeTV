package com.maybe.live.presentation.controller;

import com.google.common.collect.Lists;
import com.maybe.live.domain.User;
import com.maybe.live.support.Config;
import com.maybe.live.support.CustomResp;
import com.maybe.live.support.RequestContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tate
 * @date: 2016/5/10 17:16
 */
public class BaseController {


    public User getUser() {
        Object obj = WebUtils.getSessionAttribute(ctx().getRequest(), Config.USER_SESSION);
        if (obj == null) {
            return null;
        }
        return (User) obj;
    }

    CustomResp validateForm(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (bindingResult.hasErrors()) {
            ArrayList<Object> errMsgList = Lists.newArrayList();
            for (ObjectError allError : allErrors) {
                errMsgList.add(allError.getDefaultMessage());
            }
            return CustomResp.withMsgKey(400, errMsgList);
        }
        return null;
    }

    public RequestContext ctx(){
        return RequestContext.get();
    }

    public String redirect(String url) {
        return "redirect:" + url;
    }

}
