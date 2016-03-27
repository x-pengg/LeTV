package com.mylive.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by TateChan on 2016/3/26.
 */


@WebServlet("/live")
public class PlayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "直播QAQ");
        req.getRequestDispatcher("WEB-INF/jsp/play.jsp").forward(req, resp);
    }

}
