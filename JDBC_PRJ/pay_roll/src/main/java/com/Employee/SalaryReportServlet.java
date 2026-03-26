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

@WebServlet("/salaryReport")
public class SalaryReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String query =
                    "SELECT COUNT(*) AS totalEmployees, " +
                    "SUM(salary) AS totalSalary, " +
                    "AVG(salary) AS avgSalary, " +
                    "MAX(salary) AS maxSalary, " +
                    "MIN(salary) AS minSalary " +
                    "FROM employees";

            ResultSet rs = st.executeQuery(query);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Salary Report</title>");

            out.println("<style>");

            out.println("body{margin:0;font-family:Arial,Helvetica,sans-serif;");
            out.println("background:linear-gradient(to right,#d4f5e9,#eafff5);");
            out.println("display:flex;justify-content:center;align-items:center;height:100vh;}");

            out.println(".card{width:450px;background:white;padding:30px;border-radius:12px;");
            out.println("box-shadow:0 8px 25px rgba(0,0,0,0.15);text-align:center;}");

            out.println("h1{color:#333;margin-bottom:20px;font-size:22px;}");

            out.println(".grid{display:grid;grid-template-columns:1fr 1fr;gap:15px;}");

            out.println(".box{background:#e6fff5;padding:15px;border-radius:8px;");
            out.println("box-shadow:0 2px 8px rgba(0,0,0,0.08);}"); 

            out.println(".label{font-size:14px;color:#555;}");
            out.println(".value{font-size:18px;font-weight:bold;color:#28a745;margin-top:5px;}");

            out.println(".btn{display:inline-block;margin-top:20px;padding:10px 18px;");
            out.println("border-radius:6px;text-decoration:none;color:white;font-size:14px;transition:0.3s;}");

            out.println(".back{background:#28a745;}");
            out.println(".back:hover{background:#218838;}");

            out.println(".home{background:#20c997;margin-left:10px;}");
            out.println(".home:hover{background:#17a589;}");

            out.println("</style>");
            out.println("</head>");

            out.println("<body>");

            if (rs.next()) {

                out.println("<div class='card'>");

                out.println("<h1>Salary Analytics</h1>");

                out.println("<div class='grid'>");

                out.println("<div class='box'><div class='label'>Total Employees</div><div class='value'>" + rs.getInt("totalEmployees") + "</div></div>");

                out.println("<div class='box'><div class='label'>Total Salary</div><div class='value'>" + rs.getDouble("totalSalary") + "</div></div>");

                out.println("<div class='box'><div class='label'>Average Salary</div><div class='value'>" + rs.getDouble("avgSalary") + "</div></div>");

                out.println("<div class='box'><div class='label'>Highest Salary</div><div class='value'>" + rs.getDouble("maxSalary") + "</div></div>");

                out.println("<div class='box'><div class='label'>Lowest Salary</div><div class='value'>" + rs.getDouble("minSalary") + "</div></div>");

                out.println("</div>");

                out.println("<a href='report.html' class='btn back'>Back</a>");
                out.println("<a href='dashboard.html' class='btn home'>Home</a>");

                out.println("</div>");
            }

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}