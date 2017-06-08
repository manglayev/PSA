/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author TALGAT
 */
public class SQL_Update {
    private PreparedStatement update = null;
    public void query(String owner, String checkIn, String checkOut, String comments, String path, String status, int id){
        SQL_Connection sqlConnection = new SQL_Connection();
        Connection connection = sqlConnection.getConnection();
        try{           
            update = connection.prepareStatement("UPDATE PSA_CONTAINERS "
                   + "SET CONTAINER_OWNER = ?, CONTAINER_CHECK_IN = ?, CONTAINER_CHECK_OUT = ?, COMMENTS = ?, PATH_TO_IMAGES = ?, PAYMENT_STATUS = ? "
                    + "WHERE CONTAINER_ID = ?");
            
            update.setString(1, owner);
            update.setString(2, checkIn);
            update.setString(3, checkOut);
            update.setString(4, comments);
            update.setString(5, path);
            update.setString(6, status);
            update.setInt(7, id);
            
            int result = update.executeUpdate();
            System.out.println("updated "+result+" line");
            update.close();
            connection.close();
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}
