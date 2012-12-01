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
        <div class="content">
            <div class="header"></div>
            <div class="center">
        
        
        
        
        <table cellspacing="0" cellpadding="0" border="0" class="tbmain">
            <tr>
                <td  class="tbmain-left" valign="top">
                  <div class="titulo-form">Servicios</div>
                  <form name="frmBloque" id="frmEscaneo">  
                    <table>
                        <tr>
                            <td colspan="2">
                                IP/Dominio:
                                <input type="text" id="txtHost" name="txtHost" class="input-caja"style=" width: 150px">
                            </td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="rbModo" value="1"></td>
                            <td>
                                Puerto específico:
                                 <input type="text" id="txtPuertoEspecifico" name="txtPuertoEspecifico" class="input-caja">
                            </td>                                            
                        </tr>            
                        <tr>
                            <td><input type="radio" name="rbModo" value="2"></td>                            
                            <td>
                                Rango de puertos:
                                Desde:
                                <input type="text" id="txtPuertoDesde" name="txtPuertoDesde" class="input-caja">
                                Hasta:
                                <input type="text" id="txtPuertoHasta" name="txtPuertoHasta" class="input-caja">
                            </td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="rbModo" id="rbModo" value="3" checked="ckecked"></td>
                            <td>Todos</td>                            
                        </tr>
                        <tr>
                            <td colspan="2">                                
                                Determinar servicios:
                                <input type="checkbox" name="cbServicio" id="cbServicio" />                                
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center; padding-top: 20px">
                                <a href="JavaScript:enviarForm();"><img src="images/boton.png" alt="Escanear puertos"></a>
                            </td>
                        </tr>
                    </table>
                </form>
                    
                </td>
                <td class="tbmain-center" valign="top">
                    <div id="spinner"></div>
                    <div id="content" style="width: 100%; height: 400px;">
                        <div class="titulo-general">Escaneador de Puetos</div>
                        <div class="texto-general">
                            Programa que permite detectar los puertos abiertos de un host remoto,
                            identificar el protocolo y el servicio que corren en dichos puertos.                            
                        </div>
                        <div class="img-general">
                            <img src="images/fiis.png" />
                        </div>
                    </div>                    
                </td>
            </tr>
            
        </table>
        <div style="clear: both"></div>
            </div>
            <div class="bottom"></div>
        </div>
        
    </body>
</html>
