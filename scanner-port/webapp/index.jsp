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
                if(validarForm())
                {
                    var host = $('#txtHost').val();
                    var pto = $('#txtPuertoEspecifico').val();
                    var ptoDesde = $('#txtPuertoDesde').val();
                    var ptoHasta = $('#txtPuertoHasta').val();
                    var ptos = $('#txtPuertos').val();
                    var modo =  $('#cmbModo').val();
                    var servicio = "0";                

                    if($("#cbServicio").is(':checked')) {  
                        servicio = "1";     
                    }

                    var url = "escanearPuerto?accion=inicio&host=" + host + "&pto=" + pto + "&ptos="  + ptos + "&ptodesde=" + ptoDesde + "&ptohasta=" + ptoHasta + "&modo=" + modo + "&servicio=" + servicio;

                    $("#spinner").html($('#loader').html());
                    $('#content').load(url, null, function() {
                         $("#spinner").html('');
                    });                
                }
            }
            
            function validarForm()
            {
                 var host = $('#txtHost').val();
                 var modo = $('#cmbModo').val();
                 var ptoEspecifico = $('#txtPuertoEspecifico').val();                 
                 var ptoDesde = $('#txtPuertoDesde').val();
                 var ptoHasta = $('#txtPuertoHasta').val();
                 var ptos = $('#txtPuertos').val();
                 
                 if(host == '')
                 {
                     alert('Ingrese un IP o dominio.');
                     return false;
                 }
                 if(modo == '')
                 {
                     alert('Seleccione el modo de escaneo.');
                     return false;
                 }
                 else
                 {                     
                     if(modo == "1")
                     {
                         if(ptoEspecifico == ''){
                             alert('Ingrese el número de puerto.');
                             return false;
                         }
                     }
                     if(modo == "2")
                     {
                         if(ptos == '')
                         {
                             alert('Ingrese puetos especificos separados por comas.');
                             return false;
                         }
                     }
                     if(modo == "3")
                     {
                         if(ptoDesde == '' || ptoHasta == ''){
                             alert('Ingrese rango de puertos.');
                             return false;
                         }
                     }                    
                 }         
                 return true;
            }
            
            function habilitarControl()
            {                
                var modo = $('#cmbModo').val();
                $('#trPuerto').css('display','none');
                $('#trPuertos').css('display','none');
                $('#trRango').css('display','none');
                
                if(modo == '1'){
                    $('#trPuerto').css('display','block');
                }
                if(modo == '2'){
                    $('#trPuertos').css('display','block');    
                }
                if(modo == '3'){
                    $('#trRango').css('display','block');
                }
            }
            
            function cargarInicio()
            {
                $("#content").html($("#inicio").html());                    
            }
            
        </script>
    </head>
    <body onload="cargarInicio();">
        <div class="content">
            <div class="header">
                <div class="indexhref">
                    <a href="JavaScript:cargarInicio();">Inicio</a>
                </div>                
            </div>
            <div class="center">
                        
        <table cellspacing="0" cellpadding="0" border="0" class="tbmain">
            <tr>
                <td  class="tbmain-left" valign="top">
                  <div class="titulo-form">Servicios</div>
                  <form name="frmBloque" id="frmEscaneo">  
                    <table>
                        <tr>
                            <td>
                                <div class="titlabel">IP/Dominio:</div>
                                <div class="formcontrol">
                                    <input type="text" id="txtHost" name="txtHost" class="input-caja">
                                </div>                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="titlabel">Modo</div>
                                <div class="formcontrol">
                                    <select id="cmbModo" name="cmbModo" onchange="habilitarControl();" class="input-combo">
                                        <option value="">-Seleccione-</option>
                                        <option value="1">Puerto específico</option>
                                        <option value="2">Varios</option>
                                        <option value="3">Rango</option>
                                        <option value="4">Todos</option>
                                    </select>
                                </div>                                
                            </td>
                        </tr>
                        <tr id="trPuerto" style="display: none">                            
                            <td>
                                <div class="titlabel">Puerto</div>
                                <div class="formcontrol">
                                    <input type="text" id="txtPuertoEspecifico" name="txtPuertoEspecifico" class="input-caja">
                                </div>                                
                            </td>                                            
                        </tr>  
                        <tr id="trPuertos" style="display: none">                            
                            <td>
                                <div class="titlabel">Ingrese los puertos:</div>
                                <div class="formcontrol">
                                    <input type="text" id="txtPuertos" name="txtPuertos" class="input-caja">
                                </div>                                
                            </td>                                            
                        </tr>  
                        <tr id="trRango" style="display: none">                                           
                            <td>                                
                                <div class="titlabel">Desde:</div>
                                <div class="formcontrol">
                                    <input type="text" id="txtPuertoDesde" name="txtPuertoDesde" class="input-caja">
                                </div>                                
                                <div class="titlabel">Hasta:</div>
                                <div class="formcontrol">
                                    <input type="text" id="txtPuertoHasta" name="txtPuertoHasta" class="input-caja">
                                </div>                                
                            </td>
                        </tr>                        
                        <tr>
                            <td>                                
                                <div class="titlabel">Determinar Servicios
                                    <input type="checkbox" name="cbServicio" id="cbServicio" />                                
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center; padding-top: 20px">
                                <a href="JavaScript:enviarForm();"><img src="images/boton.png" alt="Escanear puertos"></a>
                            </td>
                        </tr>
                    </table>
                </form>
                    
                </td>
                <td class="tbmain-center" valign="top">
                    <div id="spinner"></div>
                    <div id="content" style="width: 100%; height: 400px;">
                        
                    </div>                    
                </td>
            </tr>
            
        </table>
        <div style="clear: both"></div>
            </div>
            <div class="bottom"></div>
            <div class="txtbottom">Universidad Nacional de Ingeniería</div>
        </div>
        <div id="inicio" style="display: none" >
              <div class="titulo-general">Escaneador de Puetos</div>
              <div class="texto-general">
                  Programa que permite detectar los puertos abiertos de un host remoto,
                  identificar el protocolo y el servicio que corren en dichos puertos.                            
              </div>
              <div class="img-general">
                  <img src="images/fiis.png" />
              </div>
        </div>
        
        <div id="loader" style="display: none">
            <div class="loader">
                <img src="images/spinner.gif" alt="Wait" />
                <span>Espere por favor...</span>
            </div>
        </div>        
    </body>
</html>
