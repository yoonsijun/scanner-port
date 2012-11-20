<%-- 
    Document   : index
    Created on : 08/11/2012, 03:03:40 PM
    Author     : rcastro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            
            function direccionar(){
                
                document.location.href="escanearPuerto?accion=inicio";
                
            }
            
        </script>
    </head>
    <body onload="direccionar();">
        <h1>Hello World!</h1>
        <a href="escanearPuerto?accion=inicio">Escanear puertos</a>
      
    </body>
</html>
