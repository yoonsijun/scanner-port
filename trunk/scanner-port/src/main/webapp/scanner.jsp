<%-- 
    Document   : scanner
    Created on : 08/11/2012, 03:05:15 PM
    Author     : rcastro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">                        
            function validarFormulario()
            {                 
                    
               return true;
            }
            
        </script>
    </head>
    <body>
        <h1>Escanear puertos</h1>
        
        Modo de escaneo: 
        
        <form name="frmBloque" id="frmEscaneo" method="post" action="escanearPuerto?accion=escanear" onsubmit="return validarFormulario();" >  
            <table>
                <tr>
                    <td>IP/Dominio:</td>
                    <td colspan="2"><input type="text" id="txtHost" name="txtHost"></td>
                    
                </tr>
                <tr>
                    <td><input type="radio" name="rbModo" value="1"></td>
                    <td>Puerto específico:</td>                
                    <td>
                        <input type="text" id="txtPuertoEspecifico" name="txtPuertoEspecifico" value="">
                    </td>
                </tr>            
                <tr>
                    <td><input type="radio" name="rbModo" value="2"></td>
                    <td>Rango de puertos:</td>
                    <td>
                        <input type="text" id="txtPuertoDesde" name="txtPuertoDesde">
                        <input type="text" id="txtPuertoHasta" name="txtPuertoHasta">
                    </td>
                </tr>
                <tr>
                    <td><input type="radio" name="rbModo" value="3"></td>
                    <td>Todos</td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Escanear" name="btnEscanear" />
                    </td>
                </tr>
            </table>
        </form>
        <div style="display:${param.accion eq 'inicio'?'none':'block'}">
            <table cellspacing="0" cellpadding="0" border="0" class="tabla">
                <tr>
                     <th><b>Puerto</b></th>
                     <th><b>Protocolo</b></th>
                     <th><b>Servicio</b></th>
                     <th><b>Aplicación</b></th>
                     <th><b>Versión</b></th>                                      
                     <th><b>Respuesta</b></th>  
                </tr>

            </table>
        </div>           
${beans}
                    
    </body>
</html>
