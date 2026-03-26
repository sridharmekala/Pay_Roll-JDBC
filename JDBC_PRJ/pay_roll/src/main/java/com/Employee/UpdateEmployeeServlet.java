package com.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM employees WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");


            PrintWriter out = response.getWriter();

            if (rs.next()) {

                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");

                out.println("<title>Update Employee</title>");

                out.println("<style>");

                out.println("body{font-family:Arial;background:linear-gradient(to right,#667eea,#764ba2);margin:0;text-align:center;}");

                out.println(".container{width:420px;margin:120px auto;background:white;padding:35px;border-radius:10px;box-shadow:0 0 15px gray;}");

                out.println("h2{margin-bottom:20px;color:#333;}");

                out.println("input[type=text]{width:90%;padding:10px;margin:8px;border-radius:5px;border:1px solid #ccc;}");

                out.println("input[type=submit]{background:#4facfe;color:white;border:none;padding:12px 25px;border-radius:6px;font-size:16px;cursor:pointer;}");

                out.println("input[type=submit]:hover{background:#0077ff;}");

                out.println("a{display:inline-block;margin-top:15px;text-decoration:none;color:#555;}");

                out.println("</style>");

                out.println("</head>");
                out.println("<body>");

                out.println("<div class='container'>");

                out.println("<h2>✏ Update Employee Details</h2>");

                out.println("<form action='updateEmployee' method='post'>");

                out.println("<input type='hidden' name='id' value='"+rs.getInt("id")+"'>");

                out.println("<input type='text' name='name' value='"+rs.getString("name")+"' placeholder='Employee Name'><br>");

                out.println("<input type='text' name='department' value='"+rs.getString("department")+"' placeholder='Department'><br>");

                out.println("<input type='text' name='salary' value='"+rs.getDouble("salary")+"' placeholder='Salary'><br><br>");

                out.println("<input type='submit' value='Update Employee'>");

                out.println("</form>");

                out.println("<br><a href='viewEmployees'>Back to Employee List</a>");

                out.println("</div>");

                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            ps.executeUpdate();

            response.sendRedirect("viewEmployees");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
