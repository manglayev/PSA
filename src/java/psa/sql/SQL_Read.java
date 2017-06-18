/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 *
 * @author TALGAT
 */
public class SQL_Read { 
    private PreparedStatement read = null;
    ResultSet resultSet = null;
    public ArrayList query(){
        SQL_Connection sqlConnection = new SQL_Connection();
        Connection connection = sqlConnection.getConnection();        
        //ArrayList<String> columns = new ArrayList<>();
        ArrayList<Map> rows = new ArrayList<Map>();
        try{
            read = connection.prepareStatement("SELECT * FROM PSA_CONTAINERS");
            resultSet = read.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();            
            while(resultSet.next()){
                Map<String, String> container = new HashMap<String, String>();
                for(int i=1;i<=numberOfColumns;i++){
                    String columnValue = "NOT FILLED";
                    if(resultSet.getObject(i)!=null){
                        columnValue = resultSet.getObject(i).toString();
                    }
                    container.put(resultSetMetaData.getColumnLabel(i), columnValue);                    
                }
                rows.add(container);
            }
            read.close();
            resultSet.close();
            connection.close();
        }catch(SQLException exception){
            System.out.print(exception);            
        }
        return rows;
    }
}