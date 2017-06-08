<%-- 
    Document   : containersList
    Created on : Apr 30, 2017, 4:58:33 AM
    Author     : TALGAT
--%>
<%@page import="java.util.Map"%>
<%@page import="psa.sql.SQL_Read"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String   servlet1 = response.encodeURL(request.getContextPath()+"/ViewListServlet"); %>
    <% String   servlet2 = response.encodeURL(request.getContextPath()+"/SaveImageServlet"); %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Containers</title>
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
                
        <link type="text/css" rel="stylesheet" href="/PSA/CSS/containerListCSS.css" />
        <script type="text/javascript" src="/PSA/JS/scripts/jquery-1.11.1.min.js"></script>        
        <link rel="stylesheet" href="/PSA/JS/jqwidgets/styles/jqx.base.css" type="text/css" />   
        <link rel="stylesheet" href="/PSA/JS/jqwidgets/styles/jqx.energyblue.css" type="text/css" />
        <script>
            var servletURL1  = '<%=servlet1 %>';            
            var servletURL2  = '<%=servlet2 %>'; 
        </script>
        <script type="text/javascript" src="/PSA/JS/scripts/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxcore.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxbuttons.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxscrollbar.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxmenu.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxwindow.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxcheckbox.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxpanel.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxgrid.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxgrid.selection.js"></script> 
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxgrid.columnsresize.js"></script> 
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxdata.js"></script> 
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxdata.export.js"></script> 
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxgrid.export.js"></script> 
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxgrid.sort.js"></script>        
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxdatetimeinput.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxcalendar.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/jqxtooltip.js"></script>
        <script type="text/javascript" src="/PSA/JS/jqwidgets/globalization/globalize.js"></script>
        <script type="text/javascript" src="/PSA/JS/containerListLJS.js"></script>
    </head>
    <body>
        <!-- template start-->
        <!-- Sidebar/menu -->
        <div id='trueSide'>
            <nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
                <div>
                    <img id="logo" src="/PSA/Images/Logo.png" width="200px" height="200px">
                </div>
                <div class="w3-container">
                    <h3 class="w3-padding-64"><b>Контейнерные<br>Перевозки</b></h3>
                </div>
                <div class="w3-bar-block">
                  <a href="/PSA/index.html" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Главная</a> 
                  <a href="/PSA/containersList.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Контейнеры</a> 
                  <a href="/PSA/containersUpload.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Регистрация</a> 
                  <a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Контакты</a>
                </div>
            </nav>
        </div>
        <!-- template end-->
        <!-- !PAGE CONTENT! -->
        <div id = 'trueHeader' class="w3-main" style="margin-left:340px;margin-right:40px">
            <!-- Header -->
            <div class="w3-container" style="margin-top:80px" id="showcase">
                <h1 class="w3-jumbo"><b>Зарегистрированные контейнеры</b></h1>
                <hr style="width:1200px;border:5px solid red" class="w3-round">
            </div>                  
            <div id='jqxWidget' style="font-size: 13px; font-family: Verdana;">
                <div id="jqxgrid">
                    <div id="mainDemoContainer">
                        <div id="customWindow">
                            <div id="customWindowHeader">
                                <span id="captureContainer" style="float: left">Зарегистрировать новый контейнер </span>
                            </div>
                            <div id="customWindowContent" style="overflow: hidden">
                                <div style="margin: 10px">   
                                    <form id="submissionForm" action="/PSA/SaveImageServlet" method="POST" name="registrationForm" enctype="multipart/form-data">
                                        <table style="width:100%">                                        
                                            <tr>
                                                <td>
                                                    <div id="videoContainer1">
                                                        <video autoplay="true" id="videoElement1"></video>
                                                        <canvas id="canvas1" name="canvas1Name" width="1280" height="1024"></canvas>                                                        
                                                        <input name="camera1File"  type="text"  id="camera1FileID"  style="visibility:hidden" />
                                                    </div>
                                                </td>
                                                <td>
                                                    <div id="videoContainer2">
                                                        <!--
                                                        <video autoplay="true" id="videoElement2"></video>
                                                        <canvas id="canvas2" name="canvas2Name" width="1280" height="1024"></canvas>
                                                        <input id="camera2ImageId" name="camera2ImageName" type="file" style="visibility:hidden" />
                                                        -->
                                                    </div>                                                    
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <input type="button" value="Камера 1" id="snapShot1" />
                                                    <input type="button" disabled value="Переснять 1" id="Preview1" />
                                                </td>
                                                <td align="center">
                                                    <input type="button" value="Камера 2" id="snapShot2" />
                                                    <input type="button" disabled value="Переснять 2" id="Preview2" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div id="videoContainer3"><video autoplay="true" id="videoElement3"></video></div>
                                                </td>
                                                <td>
                                                    <div id="videoContainer4"><video autoplay="true" id="videoElement4"></video></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <input type="button" value="Камера 3" id="snapShot3" />
                                                    <input type="button" disabled value="Переснять 3" id="Preview3" />
                                                </td>
                                                <td align="center">
                                                    <input type="button" value="Камера 4" id="snapShot4" />
                                                    <input type="button" disabled value="Переснять 4" id="Preview4" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan='2' align="center">
                                                    <input type="button" value="СНЯТЬ НА ВСЕ КАМЕРЫ ОДНОВРЕМЕННО" id="snapShotAll" />
                                                    <input type="button" value="ПЕРЕСНЯТЬ НА ВСЕ КАМЕРЫ ОДНОВРЕМЕННО" id="snapShotAll" />                                                    
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <span>Дата отбытия:</span>
                                                </td>
                                                <td align="center">
                                                    <div id='dateTimeInput' name="dateTimeInputName"></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <span>Владелец:</span>
                                                </td>
                                                <td align="center">
                                                    <input name="ownerName" type="text" maxlength="100" style="width: 300px; border: 1px solid #aaa" id="owner" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <input type="submit" value="Сохранить" id="registerButton" />
                                                </td>
                                                <td align="center">
                                                    <input type="button" value="Отменить"   id="cancelButton" />
                                                </td>
                                            </tr>                                        
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>                
                </div>
                <div id="buttonsExport" style='margin-top: 20px;'>
                    <div style='float: left;'>
                        <input type="button" value="Export to Excel" id='excelExport' />                    
                    </div>
                    <div style='margin-left: 10px; float: left;'>
                        <input type="button" value="Export to PDF" id='pdfExport' />
                    </div>
                    <div style='margin-left: 10px; float: left;'>
                        <input type="button" value="Export to XML" id='xmlExport' />
                    </div>
                    <div style='margin-left: 10px; float: left;'>
                        <input type="button" value="Export to JSON" id='jsonExport' />
                    </div>
                </div>
            </div>
        <div class="mainDiv">
        </div>                 
        </div>
        
        <div id="containersSlider">
            <div class='closeButtonDiv'>
                <img class='closeButton' src='/PSA/JS/images/closeButton.png' width='30px' height='30px' onclick="closeFunction()">
            </div>
            <br>
            <div class="w3-content" style="max-width:1200px">
                <img id="imgBig" class="mySlides" src="" width='700px' height='500px'>
                <div class="w3-row-padding w3-section">
                  <div class="w3-col s4">
                    <img id="imgThumb" class="demo w3-opacity w3-hover-opacity-off" src="" width='120px' height='100px' onclick="currentDiv(1)">
                  </div>
                </div>
            </div>
         </div>        
    </body>
</html>