package com.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM admin_users WHERE username=? AND password=?"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // ✅ SUCCESS LOGIN → redirect to dashboard
                response.sendRedirect(request.getContextPath() + "/dashboard.html");

            } else {

                // ❌ INVALID LOGIN → go back to login page
                response.getWriter().println("<h2>Invalid Login</h2>");
                response.getWriter().println("<a href='index.html'>Try Again</a>");
            }

        } catch (Exception e) {

            // ❌ ERROR HANDLING
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}
