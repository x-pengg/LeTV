package com.maybe.live.support;

import lombok.Data;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Tate
 * @date: 2016/5/11 20:08
 */
@Data
public class RequestContext {

    private final static ThreadLocal<RequestContext> contexts = new ThreadLocal<RequestContext>();

    private ServletContext context;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Cookie> cookies;

    public static RequestContext init(ServletContext ctx, HttpServletRequest req, HttpServletResponse res) {
        RequestContext rc = new RequestContext();
        rc.context = ctx;
        rc.request = req;
        rc.response = res;
        rc.response.setCharacterEncoding(Config.UTF_8);
        rc.session = req.getSession(false);
        rc.cookies = new HashMap<String, Cookie>();
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie ck : cookies) {
                rc.cookies.put(ck.getName(), ck);
            }
        contexts.set(rc);
        return rc;
    }

    public void destroy() {
        this.context = null;
        this.request = null;
        this.response = null;
        this.session = null;
        this.cookies = null;
        contexts.remove();
    }

    public static RequestContext get() {
        return contexts.get();
    }


    public void closeCache() {
        header("Pragma", "No-cache");
        header("Cache-Control", "no-cache");
        header("Expires", 0L);
    }

    public String ip() {
        return request.getRemoteAddr();
    }

    public String uri() {
        return request.getRequestURI();
    }

    public String ctx() {
        return request.getContextPath();
    }

    public String ua(){
        return request.getHeader("User-Agent");
    }

    public LocalDateTime now(){
        return LocalDateTime.now();
    }

    public void redirect(String uri) throws IOException {
        response.sendRedirect(uri);
    }

    public void forward(String uri) throws ServletException, IOException {
        RequestDispatcher rd = context.getRequestDispatcher(uri);
        rd.forward(request, response);
    }

    public void include(String uri) throws ServletException, IOException {
        RequestDispatcher rd = context.getRequestDispatcher(uri);
        rd.include(request, response);
    }

    public <T> T getSessionAttribute(String attr) {
        HttpSession session = session(true);
        Object obj = session.getAttribute(attr);
        return obj != null ? ((T) obj) : null;
    }

    public HttpSession session(boolean create) {
        return (session == null && create) ? (session = request.getSession()) : session;
    }

    public void error(int code, String... msg) throws IOException {
        if (msg.length > 0) {
            response.sendError(code, msg[0]);
        } else {
            response.sendError(code);
        }

    }

    public void forbidden() throws IOException {
        error(HttpServletResponse.SC_FORBIDDEN);
    }

    public void notFound() throws IOException {
        error(HttpServletResponse.SC_NOT_FOUND);
    }

    public void print(Object msg) throws IOException {
        if (!Config.UTF_8.equalsIgnoreCase(response.getCharacterEncoding()))
            response.setCharacterEncoding(Config.UTF_8);
        response.getWriter().print(msg);
    }

    public void printXml(Object msg) throws IOException {
        response.setContentType("application/xml");
        print(msg);
    }

    public static void clearLoginSession() {
        get().session(true).removeAttribute(Config.USER_SESSION);
    }

    public static void setLoginSession(Object obj) {
        get().session(true).setAttribute(Config.USER_SESSION, obj);
    }

    public static boolean isLogined(HttpServletRequest req) {
        return get().getSessionAttribute(Config.USER_SESSION)!=null;
    }

    public String header(String name) {
        return request.getHeader(name);
    }

    public void header(String name, String value) {
        response.setHeader(name, value);
    }

    public void header(String name, int value) {
        response.setIntHeader(name, value);
    }

    public void header(String name, long value) {
        response.setDateHeader(name, value);
    }

    public String getBaseURI() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }

}
