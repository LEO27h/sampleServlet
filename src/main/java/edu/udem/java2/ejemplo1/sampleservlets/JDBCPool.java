/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udem.java2.ejemplo1.sampleservlets;

import edu.udem.java2.ejemplo1.vo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author s206e20
 */
public class JDBCPool {

    BasicDataSource ds = null;

    public void init() {
        ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/test");
        ds.setUsername("sa");
        ds.setPassword("sa");

    }
    
    public void destroy(){
        try {
            ds.close();
        } catch (Exception e) {
            Logger.getLogger(JDBCPool.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Persona obtenerPersona(String login, String password) {

        Persona persona = new Persona();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;

        try {

            con = ds.getConnection();
            rs = ps.executeQuery("SELECT NOMBRE, APELLIDO, LOGIN, PASSWORD, EMAIL"
                    + " FROM USERS WHERE LOGIN = '" + login
                    + "' AND PASSWORD = '" + password + "'");

            ps.setString(1, login);
            ps.setString(2, password);
            
            ps.executeQuery();

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return persona;
    }

}
