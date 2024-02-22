package com.example.appuser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "deleteUser", value = "/deleteUser")
public class DeleteUser extends HttpServlet {
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
        processRequest(request, response);

        String userId = request.getParameter("id");

        int id = Integer.parseInt(userId);
        try {
            UserDaoHandler.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("viewUser?page=1");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
    }
}
