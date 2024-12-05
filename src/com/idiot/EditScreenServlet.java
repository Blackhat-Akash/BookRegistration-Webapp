//package com.idiot;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/editScreen")
//public class EditScreenServlet extends HttpServlet {
//    private static final String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA where id=?";
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        // Get PrintWriter
//        PrintWriter pw = res.getWriter();
//        res.setContentType("text/html");
//
//        // Get the ID of the record
//        int id = Integer.parseInt(req.getParameter("id"));
////        String idParam = req.getParameter("id");
////        if (idParam == null || idParam.trim().isEmpty()) {
////            pw.println("<h1>Error: ID parameter is missing or invalid.</h1>");
////            pw.println("<a href='home.html'>Home</a>");
////            return;
////        }
////
////        int id;
////        try {
////            id = Integer.parseInt(idParam);
////        } catch (NumberFormatException e) {
////            pw.println("<h1>Error: ID must be a valid integer.</h1>");
////            pw.println("<a href='home.html'>Home</a>");
////            return;
////        }
//
//        // Load JDBC Driver
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnf) {
//            cnf.printStackTrace();
//            pw.println("<h1>Error loading database driver.</h1>");
//            return;
//        }
//
//        // Generate connection
//        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
//             PreparedStatement pst = con.prepareStatement(query)) {
//             
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//
////            if (rs.next()) {
//            rs.next();
//                pw.println("<form action='editurl?id=" + id + "'  method='post'>");
//                pw.println("<table align='center'>");
//                pw.println("<tr>");
//                pw.println("<td>Book Name:</td>");
//                pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td>");
//                pw.println("</tr>");
//                pw.println("<tr>");
//                pw.println("<td>Book Edition:</td>");
//                pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(2) + "'></td>");
//                pw.println("</tr>");
//                pw.println("<tr>");
//                pw.println("<td>Book Price:</td>");
//                pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(3) + "'></td>");
//                pw.println("</tr>");
//                pw.println("<tr>");
//                pw.println("<td><input type='submit' value='Edit'></td>");
//                pw.println("<td><input type='reset' value='Cancel'></td>");
//                pw.println("</tr>");
//                pw.println("</table>");
//                pw.println("</form>");
////            } else {
////                pw.println("<h1>No record found for ID: " + id + "</h1>");
////            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//            pw.println("<h1>Error: " + se.getMessage() + "</h1>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
//        }
//
//        pw.println("<a href='home.html'>Home</a>");
//    }
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        doGet(req, res);
//    }
//}


//package com.idiot;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/editScreen")
//public class EditScreenServlet extends HttpServlet {
//    private static final String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA WHERE id=?";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        PrintWriter pw = res.getWriter();
//        res.setContentType("text/html");
//
//        // Get the `id` parameter and validate it
//        String idParam = req.getParameter("id");
//        if (idParam == null || idParam.trim().isEmpty()) {
//            pw.println("<h1>Error: ID parameter is missing or invalid.</h1>");
//            pw.println("<a href='home.html'>Go to Home</a>");
//            return;
//        }
//
//        int id;
//        try {
//            id = Integer.parseInt(idParam);
//        } catch (NumberFormatException e) {
//            pw.println("<h1>Error: ID must be a valid integer.</h1>");
//            pw.println("<a href='home.html'>Go to Home</a>");
//            return;
//        }
//
//        // Load JDBC Driver and process the request
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnf) {
//            cnf.printStackTrace();
//            pw.println("<h1>Error loading database driver.</h1>");
//            return;
//        }
//
//        // Fetch data from the database
//        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
//             PreparedStatement pst = con.prepareStatement(query)) {
//
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//
//            if (rs.next()) {
//                // Generate the form with the fetched data
//                pw.println("<form action='updateBook?id=" + idParam + "' method='post'>");
//                pw.println("<table align='center'>");
//                pw.println("<tr><td>Book Name:</td>");
//                pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td></tr>");
//                pw.println("<tr><td>Book Edition:</td>");
//                pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(2) + "'></td></tr>");
//                pw.println("<tr><td>Book Price:</td>");
//                pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(3) + "'></td></tr>");
//                //pw.println("<input type='hidden' name='id' value='" + idParam + "'>");
//                pw.println("<tr><td><input type='submit' value='Edit'></td>");
//                pw.println("<td><input type='reset' value='Cancel'></td></tr>");
//                pw.println("</table>");
//                pw.println("</form>");
//            } else {
//                pw.println("<h1>No record found for ID: " + idParam + "</h1>");
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//            pw.println("<h1>Error fetching data: " + se.getMessage() + "</h1>");
//        }
//
//        pw.println("<a href='home.html'>Go to Home</a>");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//        doGet(req, res);
//    }
//}



package com.idiot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA WHERE ID=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Get the `id` parameter and validate it
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            pw.println("<h1>Error: ID parameter is missing or invalid.</h1>");
            pw.println("<a href='home.html'>Go to Home</a>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            pw.println("<h1>Error: ID must be a valid integer.</h1>");
            pw.println("<a href='home.html'>Go to Home</a>");
            return;
        }

        // Load JDBC Driver and process the request
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h1>Error loading database driver.</h1>");
            return;
        }

        // Fetch data from the database
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "010200");
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Generate the form with the fetched data
                pw.println("<form action='updateBook?id=" + idParam + "' method='post'>");
                pw.println("<table align='center'>");
                pw.println("<tr><td>Book Name:</td>");
                pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td></tr>");
                pw.println("<tr><td>Book Edition:</td>");
                pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(2) + "'></td></tr>");
                pw.println("<tr><td>Book Price:</td>");
                pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(3) + "'></td></tr>");
                // Hidden ID parameter to send in the form
                pw.println("<input type='hidden' name='id' value='" + idParam + "'>");
                pw.println("<tr><td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='Cancel'></td></tr>");
                pw.println("</table>");
                pw.println("</form>");
            } else {
                pw.println("<h1>No record found for ID: " + idParam + "</h1>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>Error fetching data: " + se.getMessage() + "</h1>");
        }

        pw.println("<a href='home.html'>Go to Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doGet(req, res);
    }
}
