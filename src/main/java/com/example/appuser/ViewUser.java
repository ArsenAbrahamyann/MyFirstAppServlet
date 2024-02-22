package com.example.appuser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "viewUser", value = "/viewUser")
public class ViewUser extends HttpServlet {
    protected void
    processRequest(HttpServletRequest request,
                   HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
        PrintWriter out = response.getWriter();

        String pageId = request.getParameter("page");
        int total = 3;
        int pagesId = Integer.parseInt(pageId);
        if (pagesId == 1) {
        }
        else {
            pagesId = pagesId - 1;
            pagesId = pagesId * total + 1;
        }

        List<User> list = null;
        out.println(
                "<a href='/addUser'>Add user</a>");

        out.print("<h1> User Table: </h1>");
        out.print(
                "<table border='1' cellpadding='4' width='80%'>");
        out.print("<tr><th>Id</th><th>username</th></tr>");
        try {
            list = UserDaoHandler.getAllUsers(pagesId,
                    total);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (list != null) {
            for (User user : list) {
                out.print("<tr><td>" + user.getId()
                          + "</td><td>" + user.getUsername()
                          + "</td></tr>");
            }
            out.print("</table>");
            out.print("<a href='viewUser?page=1'>1</a> ");
            out.print("<a href='viewUser?page=2'>2</a> ");
            out.print("<a href='viewUser?page=3'>3</a> ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
    }
}
