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
public class SQL_Delete {
    private PreparedStatement delete = null;
    public void query(int id){
        SQL_Connection sqlConnection = new SQL_Connection();
        Connection connection = sqlConnection.getConnection();
        try{           
            delete = connection.prepareStatement("DELETE FROM PSA_CONTAINERS WHERE CONTAINER_ID = ?");                        
            delete.setInt(1, id);
            
            int result = delete.executeUpdate();
            System.out.println("delete "+result+" line");
            delete.close();
            connection.close();
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}
