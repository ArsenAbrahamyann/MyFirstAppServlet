package com.example.appuser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "addUser", value = "/addUser")
public class AddUser extends HttpServlet {
    protected void
    processRequest(HttpServletRequest request,
                   HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        int status = 0;
        try {
            status = UserDaoHandler.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (status > 0) {
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.html")
                    .include(request, response);
        } else {
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}