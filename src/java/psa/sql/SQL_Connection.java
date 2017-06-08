/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author TALGAT
 */
public class SQL_Connection {
    static final String DB_URL = "jdbc:derby://localhost:1527/PSA";
    static final String USER = "aral";
    static final String PASS = "hs";
    
    public Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL,USER,PASS);            
        }catch(SQLException sqlException){
            System.out.println(sqlException);
        }
         return connection;
    }
}
