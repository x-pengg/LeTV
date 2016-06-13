package com.maybe.live.kit;

import com.google.common.collect.Lists;
import com.maybe.live.domain.Type;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author: Tate
 * @date: 2016/5/11 20:57
 */
public class WebKit {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request == null ? false : null != request.getHeader("accept") && request.getHeader("accept").indexOf("application/json") > -1 || request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1;
    }


    public static String buildQueryStr(HttpServletRequest request) {
        List result = new ArrayList();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = String.valueOf(parameterNames.nextElement());
            String[] values = request.getParameterValues(key);
            for (String value : values) {
                result.add(new BasicNameValuePair(key, value));
            }
        }
        return URLEncodedUtils.format(result, Charset.forName("UTF-8"));
    }


    public static ServletContext getServletContext() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        return webApplicationContext.getServletContext();
    }


}
