<%-- 
    Document   : containerUpload
    Created on : Apr 30, 2017, 12:21:30 AM
    Author     : Temirlan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload container</title>
    </head>
    <body>
        <script>
            var n =  new Date();
            var y = n.getFullYear();
            var m = n.getMonth() + 1;
            var d = n.getDate();
            document.getElementById("date").innerHTML = m + "/" + d + "/" + y;
        </script>
        <form action="uploadContainer" method="GET">
            <input type="file" name="img" multiple>

            <p><b>Name of container</b><br>
            <input type="text" size="30" name="nameOfContainer"><br><br>

            <input type="date" name="calendar">
            <textarea rows="4" cols="50" name="description">

            </textarea>
            <input type="submit" value="submit">
        </form>
    </body>
</html>