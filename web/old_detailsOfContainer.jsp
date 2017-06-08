<%-- 
    Document   : detailsOfContainer
    Created on : Apr 30, 2017, 6:55:50 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>
        $(document).ready(function(){           
            $("#ID").text("заглушка для ID");
            $("#Name").text("заглушка для наименования");
            $("#Date").text("заглушка для даты");
            $("#description").text("заглушка для описания");
        });
        </script>
    </head>
    <body>
        
        
        <p id="ID">ID</p> | <p id="Name">Name</p> | <p id="Date">Date</p> <br>
        
        <textarea rows="4" cols="50" id="description">

        </textarea>
    </body>
</html>
