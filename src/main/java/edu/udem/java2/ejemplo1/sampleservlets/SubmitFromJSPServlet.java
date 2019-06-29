package edu.udem.java2.ejemplo1.sampleservlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author s206e20
 */
public class SubmitFromJSPServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", "Hello!!" + request.getParameter("fName")+ " "+request.getParameter("lName") );
        RequestDispatcher dispacher = request.getRequestDispatcher("/welcome.jsp");
        dispacher.forward(request, response);
}

}
