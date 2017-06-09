/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa.model;

import java.io.File;
import psa.sql.SQL_Create;

/**
 *
 * @author TALGAT
 */
public class PrepareImage {
    public void prepareImage(){
        //if Windows
        String pathToDirectory = "C:\\Users\\Public\\FTP_PSA";
        //if Mac
        //String pathToDirectory = "/Users/TALGAT/PSA_CAMERA/";
        final File folder = new File(pathToDirectory);
        listFilesForFolder(folder);        
    }
    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            }else{
                if(fileEntry.exists()){
                    String [] extentions = {"jpg", "png", "jpeg", "bmp"};
                    System.out.println("file name "+fileEntry.getName());
                    String fileName = fileEntry.getName();
                    String [] splittedFileName = fileName.split("\\.(?=[^\\.]+$)");
                    System.out.println(splittedFileName.length);
                    String extention = splittedFileName[1];
                    for (String extention1 : extentions) {
                        if (extention1.equals(extention)) {
                            System.out.println("extention "+extention);
                            SQL_Create sqlCreate = new SQL_Create();
                            sqlCreate.query();
                        }
                    }
                }else{
                    System.out.println("file doesn't exist");
                }
            }
        }
    }
}