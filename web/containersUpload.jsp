<%-- 
    Document   : containersUpload
    Created on : May 2, 2017, 11:35:26 AM
    Author     : TALGAT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Container</title>
        <!--W3 start-->
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link type="text/css" rel="stylesheet" href="/PSA/CSS/w3.css">            
            <link rel="shortcut icon" href="/PSA/Images/Logo.png" type="image/x-icon" />
            <style>
                body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
                body {font-size:16px;}
                .w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
                .w3-half img:hover{opacity:1}
            </style>
            <!--W3 end-->            
    </head>
    <body>
        <h1>Hello World!</h1>        
        <!-- Sidebar/menu -->
        <div id='trueSide'>
            <nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
                <div>
                    <img id="logo" src="/PSA/Images/Logo.png" width="200px" height="200px">
                </div>
              <div class="w3-container">
                <h3 class="w3-padding-64"><b>Логистика</b></h3>
              </div>
              <div class="w3-bar-block">
                <a href="/PSA/index.html" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Главная</a> 
                <a href="/PSA/containersList.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Контейнеры</a> 
                <a href="/PSA/containersUpload.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Регистрация</a> 
              </div>
            </nav>
        </div>
        <!-- template end-->
        <!-- !PAGE CONTENT! -->
        <div id = 'trueHeader' class="w3-main" style="margin-left:340px;margin-right:40px">
            <div class="w3-container" style="margin-top:80px" id="showcase">
                <h1 class="w3-jumbo"><b>Регистрация нового контейнера</b></h1>
                <hr style="width:1200px;border:5px solid red" class="w3-round">
            </div>
        </div>          
    </body>
</html>
