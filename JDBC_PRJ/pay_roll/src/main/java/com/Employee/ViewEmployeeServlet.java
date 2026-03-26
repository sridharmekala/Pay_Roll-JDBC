package com.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewEmployees")
public class ViewEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");

            out.println("<title>Employee Management Dashboard</title>");

            out.println("<style>");

            out.println("body{font-family:Arial;background:linear-gradient(to right,#4facfe,#00f2fe);margin:0;padding:0;text-align:center;}");

            out.println(".container{width:80%;margin:80px auto;background:white;padding:30px;border-radius:10px;box-shadow:0 0 15px gray;}");

            out.println("h1{margin-bottom:20px;color:#333;}");

            out.println("table{width:100%;border-collapse:collapse;margin-top:20px;}");

            out.println("th,td{padding:12px;border:1px solid #ddd;text-align:center;}");

            out.println("th{background:#007bff;color:white;}");

            out.println("tr:nth-child(even){background:#f2f2f2;}");

            out.println("tr:hover{background:#e6f2ff;}");

            out.println("a{padding:6px 12px;border-radius:4px;text-decoration:none;color:white;font-size:14px;}");

            out.println(".edit{background:#28a745;}");

            out.println(".delete{background:#dc3545;}");

            out.println(".home{display:inline-block;margin-top:20px;padding:10px 20px;background:#007bff;color:white;border-radius:5px;text-decoration:none;}");

            out.println("</style>");

            out.println("</head>");

            out.println("<body>");

            out.println("<div class='container'>");

            out.println("<h1>Employee Records Management</h1>");

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Department</th>");
            out.println("<th>Salary</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            while (rs.next()) {

                int id = rs.getInt("id");

                out.println("<tr>");

                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getDouble("salary") + "</td>");

                out.println("<td>");
                out.println("<a class='edit' href='updateEmployee?id=" + id + "'>Edit</a> ");
                out.println("<a class='delete' href='deleteEmployee?id=" + id + "'>Delete</a>");
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<a class='home' href='dashboard.html'>Back to Home</a>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
