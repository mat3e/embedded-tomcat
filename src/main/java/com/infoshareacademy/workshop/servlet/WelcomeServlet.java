package com.infoshareacademy.workshop.servlet;

import com.infoshareacademy.workshop.service.WelcomeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "WelcomeServlet", urlPatterns = {"/api/test"})
public class WelcomeServlet extends HttpServlet {
    private final WelcomeService service = new WelcomeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var name = Optional.ofNullable(req.getParameter("name")).orElse("world");
        var welcomeId = Integer.valueOf(Optional.ofNullable(req.getParameter("welcome")).orElse("1"));
        var fullText = service.getWelcomeMessage(name, welcomeId);
        resp.getWriter().write(fullText);
    }
}
