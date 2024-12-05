package com.idiot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class DeleteServlet extends HttpServlet {
    private static final String query = "DELETE FROM BOOKDATA WHERE ID=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Get the 'id' parameter from the request
        String idParam = req.getParameter("id");

        // Check if the id parameter is null or empty
        if (idParam == null || idParam.trim().isEmpty()) {
            pw.println("<h2>Error: ID parameter is missing or invalid!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        int id;
        try {
            // Parse the 'id' parameter to integer
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            pw.println("<h2>Error: ID must be a valid integer!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h2>Error: Unable to load database driver!</h2>");
            pw.println("<a href='home.html'>Go Back to Home</a>");
            return;
        }

        // Execute the delete query
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
             PreparedStatement pst = con.prepareStatement(query)) {

            // Set the ID parameter for the query
            pst.setInt(1, id);

            // Execute the delete operation
            int count = pst.executeUpdate();

            // Check if the delete was successful
            if (count == 1) {
                pw.println("<h2>Record deleted successfully!</h2>");
            } else {
                pw.println("<h2>Error: No record found with ID " + id + "!</h2>");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h2>Database Error: " + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h2>Error: " + e.getMessage() + "</h2>");
        }

        // Provide links for navigation
        pw.println("<a href='home.html'>Go Back to Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>View Book List</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}
