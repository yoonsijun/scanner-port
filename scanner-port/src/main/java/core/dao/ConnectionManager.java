/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class ConnectionManager {

    private static Connection activeConnection = null;

    public static Connection getConnection() {
        if (activeConnection == null) {
            try 
            {
                Class.forName("org.hsqldb.jdbcDriver");
//                activeConnection = DriverManager.getConnection("jdbc:hsqldb:file:D:/jose/uni/cursos/scd/trabajo/scanner-port/db/scannerport-db","sa", "");
                activeConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/scannerport","sa", "");
            } catch (Exception ex) {

                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }    
        
        }
        return activeConnection;
    }
}