package edu.udem.java2.ejemplo1.sampleservlets;

import edu.udem.java2.ejemplo1.sampleservlets.util.JDBCUtil;
import edu.udem.java2.ejemplo1.vo.Persona;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jpramirez
 */
public class LoginServlet extends HttpServlet {

    Map<String, Persona> usersMap;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        usersMap = new HashMap<>();
        usersMap.put("jsmith", new Persona("Smith", "Jhon", "jsmith", "123", ""));
        usersMap.put("mperez", new Persona("Perez", "Mary", "mperez", "456", ""));

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        
        Persona persona = null;

        if((login != null && login.length() > 0) && (pwd != null && pwd.length() > 0)){
            persona = JDBCUtil.obtenerPersona(login, pwd);
        }
        
        if (persona != null) {
            request.setAttribute("message", "Bienvenido" + persona.getNombre() + " " + usersMap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        
        request.setAttribute("message", "Bienvenido " + usersMap.get(login).getNombre() + " " + usersMap.get(login).getApellidos());

        if (usersMap.get(login) != null && usersMap.get(login).getPassword().equals(pwd)) {

            RequestDispatcher dispacher = request.getRequestDispatcher("/welcome.jsp");
            dispacher.forward(request, response);
        } else {
            RequestDispatcher dispacher = request.getRequestDispatcher("/login.jsp");
            dispacher.forward(request, response);
        }
    }

}
