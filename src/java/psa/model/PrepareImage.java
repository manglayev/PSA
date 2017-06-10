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
        String pathWindows = "C:\\Users\\Public\\FTP_PSA";
        String pathWindows2 = "C:\\Users\\Public\\PSA_IMAGES\\";
        //if Mac
        String pathMac = "/Users/TALGAT/PSA_CAMERA/";
        String pathMac2 = "/Users/TALGAT/PSA_IMAGES/";
        //if Ubunutu
        String pathUbuntu = "/home/hikuser/hikvision/192.168.1.64_codes";
        String pathUbuntu2 = "/home/hikuser/hikvision/192.168.1.64_codes";
        String [] pathToFolder = {pathWindows, pathMac, pathUbuntu};
        String [] pathToReserve = {pathWindows2, pathMac2, pathUbuntu2};
        
        for(int i=0; i<pathToFolder.length; i++){
            final File folder = new File(pathToFolder[i]);
            if(folder.exists()){
                System.out.println("path "+pathToFolder[i]);
                listFilesForFolder(folder, pathToReserve[i]);
            }else{
                System.out.println("non-path "+pathToFolder[i]);
            }
        }
        //final File folder = new File(pathToDirectory);
        //listFilesForFolder(folder);
    }
    public void listFilesForFolder(final File folder, String reserveFolder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, reserveFolder);
            }else{
                if(fileEntry.exists()){
                    String [] extentions = {"jpg", "png", "jpeg", "bmp"};
                    System.out.println("file name "+fileEntry.getName());
                    String fileName = fileEntry.getName();
                    String [] splittedFileName = fileName.split("\\.(?=[^\\.]+$)");
                    System.out.println(splittedFileName.length);
                    String extention = splittedFileName[1];
                    String imageName = splittedFileName[0];
                    for (String extention1 : extentions) {
                        if (extention1.equals(extention)) {
                            System.out.println("extention "+extention);
                            SQL_Create sqlCreate = new SQL_Create();
                            sqlCreate.query(imageName);
                        }
                    }
                    fileEntry.renameTo(new File (reserveFolder+fileName));
                    fileEntry.delete();
                }else{
                    System.out.println("file doesn't exist");
                }
            }
        }
    }
}