/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.servlets;

import java.awt.image.BufferedImage;
import java.util.Base64;
import java.io.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.*;
import psa.sql.SQL_Create;
/**
 *
 * @author TALGAT
 */
public class SaveImageServlet extends HttpServlet {       
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isMultipart;
    private int maxFileSize = 50 * 1024 * 1024;
    private int maxMemSize  = 50 * 1024*1024;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET");
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
    System.out.println("POST");
//  System.out.println("type "+request.getContentType());
//  Enumeration parameters = request.getParameterNames();
//  while(parameters.hasMoreElements()){
//      System.out.println(parameters.nextElement().toString());
//  }
//  Check that we have a file upload request
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);    
    if( !isMultipart ){
       System.out.println("content is not a multipart type");
       return;
    }   
    DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
    factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
    factory.setRepository(new File("c:\\temp"));
    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    // maximum file size to be uploaded.
    upload.setSizeMax( maxFileSize );
    try{
        System.out.println("is trying");
        // Parse the request to get file items.
        List<FileItem> fileItems = upload.parseRequest(request);        
        System.out.println("There are "+fileItems.size()+" file items");
        // Process the uploaded file items
        Iterator<FileItem> i = fileItems.iterator();
        while ( i.hasNext () ){
            System.out.println("is looping");
            FileItem fi = i.next();            
            if ( !fi.isFormField () ){
                //Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String contentType = fi.getContentType();
                long sizeInBytes = fi.getSize();
                //String value = fi.getString();
                System.out.println("name 1: "+fieldName);
                System.out.println("size 1: "+sizeInBytes);
                //System.out.println("value 1: "+value);
                System.out.println("contentType 1: "+contentType);
                //Write the file
                DateFormat df = new SimpleDateFormat("yyyyMMdd-HH_mm_ss");
                Date dateobj = new Date();
                String diretoryName = df.format(dateobj);
                String path = "C:\\Users\\Public\\PSA_IMAGES\\"+diretoryName;
                //create directory
                File directoryPathAndName = new File(path);
                directoryPathAndName.mkdir();
                //create file
                File camera1 = new File(path+"\\CAMERA_1.jpg");
                fi.write(camera1);
//              file = new File("C:\\Users\\TALGAT\\PSA_IMAGES\\"+diretoryName);
//              if( fileName.lastIndexOf("\\") >= 0 ){
//                  file = new File( filePath + 
//                  fileName.substring( fileName.lastIndexOf("\\"))) ;
//              }else{
//                  file = new File( filePath + 
//                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//              }
//              fi.write( file ) ;               
            }else{
                String name = fi.getFieldName();
                String value = fi.getString();
                System.out.println("name: "+name);
                System.out.println("value: "+value);
                if(value.length()>100){
                    FileOutputStream fileOutputStream = null;
                    try{
                        InputStream is = fi.getInputStream();
                        StringWriter writer = new StringWriter();
                        IOUtils.copy(is, writer, "UTF-8");
                        String imageString = writer.toString();
                        imageString = imageString.substring("data:image/jpeg;base64,".length());
                        byte[] contentData = imageString.getBytes();
                        byte[] decodedData = Base64.getDecoder().decode(contentData);             
                        DateFormat df = new SimpleDateFormat("yyyyMMdd-HH_mm_ss");
                        Date dateobj = new Date();
                        String diretoryName = df.format(dateobj);
                        String path = "C:\\Users\\Public\\PSA_IMAGES\\"+diretoryName;
                        //create directory
                        File directoryPathAndName = new File(path);
                        directoryPathAndName.mkdir();
                        //create file
                        File camera1 = new File(path+"\\CAMERA_1.jpg");
                        fileOutputStream = new FileOutputStream(camera1);
                        fileOutputStream.write(decodedData);
                    }catch(Exception exception){
                        exception.printStackTrace();
                    }finally {                       
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }   
                }                              
            }
        }
    }catch(Exception ex) {
        System.out.println(ex);
    }
    //request.getRequestDispatcher("/PSA/containersList.jsp").forward(request, response);
    response.sendRedirect("/PSA/containersList.jsp");
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
