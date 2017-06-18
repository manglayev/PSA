/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.servlets;

import com.google.gson.Gson;
import psa.sql.SQL_Create;
import psa.sql.SQL_Update;
import psa.sql.SQL_Delete;
import psa.sql.SQL_Read;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import psa.model.PrepareImage;
/**
 *
 * @author TALGAT
 */
public class ViewListServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          SQL_Create sqlCreate = new SQL_Create();        
//        SQL_Update sqlUpdate = new SQL_Update();
//        SQL_Delete sqlDelete = new SQL_Delete();
        
//        String owner = "Schlumberger";
//        String checkIn = "2017-04-25";
//        String checkOut = "2017-07-30";
//        String comments = "Alliance";
//        String path = "/PSA/images/Schlumberger";
//        String paymentStatus = "90%";
        
        
        //sqlCreate.query(owner, checkIn, checkOut, comments, path, paymentStatus);
        //sqlUpdate.query(owner, checkIn, checkOut, comments, path, paymentStatus, 1);
        //sqlDelete.query(2);
        
        //SQL_Read sqlRead = new SQL_Read();
        //ArrayList rows = sqlRead.query();
        //String jsonTable = returnJSON(rows);
        //String jsonTable = sqlRead.query();
        //System.out.println(jsonTable);
        //response.setContentType("application/json");
        //response.getWriter().write(jsonTable);      
    }
    public String returnJSON(ArrayList rows){
        String jsonTable = new Gson().toJson(rows);        
        return jsonTable;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        //
        //go into camera folder and check if there is a photo from camera
        //
        //PrepareImage prepareImage = new PrepareImage();
        //prepareImage.prepareImage();
        SQL_Read sqlRead = new SQL_Read();
        ArrayList rows = sqlRead.query();
        String jsonTable = returnJSON(rows);
        //System.out.println("jsonTable "+jsonTable);
        response.setContentType("application/json");
        response.getWriter().write(jsonTable);    
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}