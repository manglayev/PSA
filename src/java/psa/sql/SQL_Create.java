/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author TALGAT
 */
public class SQL_Create {
    private PreparedStatement create = null;
    public void query(String id){
        String checkIn = "";
        //DateFormat df = new SimpleDateFormat("yyyyMMdd-HH_mm_ss");
        //Date dateobj = new Date();
        //checkIn = df.format(dateobj);
        Timestamp timeStamp = new Timestamp(new Date().getTime());
        checkIn = timeStamp.toString();   
        SQL_Connection sqlConnection = new SQL_Connection();
        Connection connection = sqlConnection.getConnection();
        System.out.println("ID "+id);
        System.out.println("checkIn "+checkIn);
        //,  CONTAINER_CHECK_IN, PATH_TO_IMAGES
        try{           
            create = connection.prepareStatement("INSERT INTO PSA_CONTAINERS "
                                             + "(CONTAINER_ID, CONTAINER_CHECK_IN, PATH_TO_IMAGES) "
                                             + " VALUES (?, ?, ?)");
            create.setString(1, id);
            create.setString(2, checkIn);
            create.setString(3, id);
            int result = create.executeUpdate();
            System.out.println("inserted "+result+" line");
            create.close();
            connection.close();
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}