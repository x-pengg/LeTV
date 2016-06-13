package com.maybe.live.support;

import com.alibaba.fastjson.JSON;
import com.maybe.live.kit.WebKit;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author: Tate
 * @date: 2016/5/16 16:05
 */

public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        String methodName = "";
        if (handler != null && handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            methodName = method.getName();
        }
        String errLog = String.format("[uri->%s] [method->%s] has error", httpServletRequest.getRequestURI(), methodName);
        log.error(errLog, ex);
        if (WebKit.isAjaxRequest(httpServletRequest)) {
            handlerAjax(httpServletResponse, ex);
        } else {
            return handlerNormal(ex);
        }
        return null;
    }


    private ModelAndView handlerNormal(Exception ex) {
        ModelAndView mav = new ModelAndView("500");

        if (ex instanceof GenericException) {
            CustomResp customResp = CustomResp.withEx((GenericException) ex);
            mav.addObject("ex", String.format("错误代码：%s  “%s”", customResp.getCode(), customResp.getMessage()));
        } else {
            mav.addObject("ex", ex);
        }
        return mav;
    }

    private void handlerAjax(HttpServletResponse response, Exception ex) {
        // 设置错误的响应状态码
        response.setStatus(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR);
        PrintWriter writer = null;
        if (ex instanceof GenericException) {
            try {
                writer = response.getWriter();
                CustomResp customResp = CustomResp.withEx((GenericException) ex);
                writer.write(JSON.toJSONString(customResp));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(writer);
            }
        }
    }
}
