/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udem.java2.ejemplo1.sampleservlets.util;

import edu.udem.java2.ejemplo1.vo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author s206e20
 */
public class JDBCUtil {

    public static Persona obtenerPersona(String login, String password) {

        Persona persona = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            st = con.createStatement();
            rs = st.executeQuery("SELECT NOMBRE, APELLIDO, LOGIN, PASSWORD, EMAIL"
                    + " FROM USERS WHERE LOGIN = '" + login
                    + "' AND PASSWORD = '" + password + "'");

            while (rs.next()) {
                persona = new Persona();
                persona.setNombre(rs.getString("NOMBRE"));
                persona.setApellidos(rs.getString("APELLIDO"));
                persona.setUsuario(rs.getString("LOGIN"));
                persona.setPassword("PASSWORD");
                persona.setEmail(rs.getString("EMAIL"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(st != null){
                    st.close();
                }
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return persona;
    }
}
