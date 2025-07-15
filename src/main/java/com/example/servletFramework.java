package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {
        // Get or create the session
        HttpSession session = request.getSession();

        // Set a session attribute
        session.setAttribute("username", "JohnDoe");

        // Retrieve it back
        String username = (String) session.getAttribute("username");

        // Write response
        response.setContentType("text/plain");
        response.getWriter().println("Username from session: " + username);
    }
}
