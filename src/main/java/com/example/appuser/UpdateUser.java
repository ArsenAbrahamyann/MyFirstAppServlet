package com.example.appuser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "updateUser", value = "/updateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("id");

        int id = Integer.parseInt(userId);
        try {
            User user = UserDaoHandler.getUserById(id);

            out.println("<h2>Edit User Account</h2>");
            out.print(
                    "<form action='patchUser' method='post'>");
            out.print("<table>");
            out.print(
                    "<tr><td></td><td><input type='hidden' name='id' value='"
                    + user.getId() + "'/></td></tr>");
            out.print(
                    "<tr><td>Name:</td><td><input type='text' name='name' value='"
                    + user.getUsername() + "'/></td></tr>");
            out.print(
                    "<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
            out.print("</table>");
            out.print("</form>");

            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
    }
}
