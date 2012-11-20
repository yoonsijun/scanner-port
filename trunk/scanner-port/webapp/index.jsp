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
        <title>.::SCD::.</title>
        <script type="text/javascript" src="js/jquery.js"></script>
        <link rel="Stylesheet" media="all" type="text/css" href="css/css.css" />
        <script type="text/javascript">
            
            function direccionar(){
                
                //document.location.href="escanearPuerto?accion=inicio";                
            }
            
            function enviarForm()
            {
                 var host = $('#txtHost').val();
                 var pto = $('#txtPuertoEspecifico').val();
                 var ptoDesde = $('#txtPuertoDesde').val();
                 var ptoHasta = $('#txtPuertoHasta').val();
                 var modo = $('input:radio[name=rbModo]:checked').val();
                 var servicio = "0";                
                 
                 if($("#cbServicio").is(':checked')) {  
                     servicio = "1";     
                 } 
                 var url = "escanearPuerto?accion=inicio&host=" + host + "&pto=" + pto +"&ptodesde=" + ptoDesde + "&ptohasta=" + ptoHasta + "&modo=" + modo + "&servicio=" + servicio;
                 
                 
                 $("#spinner").html('<img src="images/spinner.gif" alt="Wait" />');
                 $('#content').load(url, null, function() {
                      $("#spinner").html('');
                 });                
            }
            
        </script>
    </head>
    <body>
        <h1>Servicios</h1>
        
        <div id="spinner"></div>
        <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td style="width: 30%">
                    
                  <form name="frmBloque" id="frmEscaneo">  
                    <table>
                        <tr>
                            <td>IP/Dominio:</td>
                            <td colspan="2"><input type="text" id="txtHost" name="txtHost"></td>

                        </tr>
                        <tr>
                            <td><input type="radio" name="rbModo" value="1"></td>
                            <td>Puerto específico:</td>                
                            <td>
                                <input type="text" id="txtPuertoEspecifico" name="txtPuertoEspecifico" >
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
                            <td><input type="radio" name="rbModo" id="rbModo" value="3" checked="ckecked"></td>
                            <td>Todos</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3">                                
                                Determinar servicios:
                                <input type="checkbox" name="cbServicio" id="cbServicio" />                                
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                 <a href="JavaScript:enviarForm();">EscanearPuerto</a>
                            </td>
                        </tr>
                    </table>
                </form>
                    
                </td>
                <td style="width: 30%">
                    <div id="content" style="width: 100%; height: 400px;">                        
                    </div>
                    
                </td>
            </tr>
            
        </table>
      
        
    </body>
</html>
