package com.maybe.live.support;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.maybe.live.kit.WebKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Tate
 * @date: 2016/5/11 11:11
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if (handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("uri:{}->method:" + handlerMethod.getBean().getClass()
                    .getSimpleName() + "." + handlerMethod
                    .getMethod().getName() + "   arguments:{}", uri, JSON.toJSONString(request.getParameterMap()));

            boolean needLoginForView = needLogin((HandlerMethod) handler) && !RequestContext.isLogined(request) && !WebKit.isAjaxRequest(request);
            boolean needLoginForAjax = needLogin((HandlerMethod) handler) && !RequestContext.isLogined(request) && WebKit.isAjaxRequest(request);

            if (needLoginForAjax) {
                // 告知客户端并输出对应的报错页面(如果有，将以'text/html'内容类型)
                //RequestContext.get().error(599, ResourceKit.getErrorMsg("599"));
                response.setStatus(Config.NEEDLOGIN_STATUS);
                return false;
            } else if (needLoginForView) {
                String returnUrl = buildReturnUrl(request);
                response.sendRedirect("/login?r=" + returnUrl);
                log.debug("need login returnUrl={}", returnUrl);
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

    private boolean needLogin(HandlerMethod handlerMethod) {
        return AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NeedLogin.class) != null
                || AnnotationUtils.findAnnotationDeclaringClass(NeedLogin.class, handlerMethod.getBean().getClass()) != null;
    }

    private String buildReturnUrl(HttpServletRequest request) {
        String queryStr = WebKit.buildQueryStr(request);
        return request.getRequestURI() + (Strings.isNullOrEmpty(queryStr) ? "" : "?" + queryStr);
    }
}
