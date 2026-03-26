package com.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO employees(name,department,salary) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, salary);

            ps.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Employee Added</title>");

            out.println("<style>");

            // BACKGROUND
            out.println("body{margin:0;font-family:Arial,Helvetica,sans-serif;");
            out.println("background: linear-gradient(to right, #ffd6e8, #fff0f5);");
            out.println("display:flex;justify-content:center;align-items:center;height:100vh;}");

            // CARD
            out.println(".card{width:420px;background:white;padding:35px;border-radius:12px;");
            out.println("box-shadow:0 8px 25px rgba(0,0,0,0.2);text-align:center;}");

            // ICON
            out.println(".icon{font-size:50px;color:#28a745;margin-bottom:10px;}");

            // TEXT
            out.println("h2{color:#333;margin-bottom:10px;}");
            out.println("p{color:#666;margin-bottom:25px;}");

            // BUTTONS
            out.println(".btn{display:inline-block;padding:12px 20px;margin:10px;");
            out.println("border-radius:6px;text-decoration:none;font-size:14px;transition:0.3s;}");

            out.println(".primary{background:#ff4d88;color:white;}");
            out.println(".primary:hover{background:#e6005c;}");

            out.println(".secondary{background:#ff4d88;color:white;}");
            out.println(".secondary:hover{background:#e6005c;}");
            
            out.println(".home{background:#6c757d;color:white;}");
            out.println(".home:hover{background:#495057;}");

            out.println("</style>");
            out.println("</head>");

            out.println("<body>");

            out.println("<div class='card'>");

            out.println("<div class='icon'>✔</div>");

            out.println("<h2>Employee Added Successfully</h2>");
            out.println("<p><b>" + name + "</b> has been added to the system.</p>");

            out.println("<a href='addEmployee.html' class='btn secondary'>Add Another</a>");
            out.println("<a href='viewEmployees' class='btn primary'>View Employees</a>");
            out.println("<a href='dashboard.html' class='btn home'>Back to Home</a>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}