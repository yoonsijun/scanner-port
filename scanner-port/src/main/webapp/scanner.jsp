<%-- 
    Document   : scanner
    Created on : 08/11/2012, 03:05:15 PM
    Author     : rcastro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"
import="core.domain.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/popup.js"></script>
       <link rel="Stylesheet" media="all" type="text/css" href="css/css.css" />
       
        <script type="text/javascript">                        
            function validarFormulario()
            {                 
               
               
               
               return true;
            }
            
            function mostrarResultado(id) {                              
                $('#resultado').html($('#' + id).html());
                $("#div-popup").bPopup({ opacity: 0.4 });    
            }
            
        </script>
    </head>
    <body>
        <h1>Escanear puertos</h1>
        
        Modo de escaneo:
        
         <%
              Integer indEditar = (request.getAttribute("indMostrar")!=null)?Integer.parseInt(request.getAttribute("indMostrar").toString()):0;
         %>
        <form name="frmBloque" id="frmEscaneo" method="post" action="escanearPuerto" onsubmit="return validarFormulario();" >  
            <table>
                <tr>
                    <td>IP/Dominio:</td>
                    <td colspan="2"><input type="text" id="txtHost" name="txtHost"></td>
                    
                </tr>
                <tr>
                    <td><input type="radio" name="rbModo" value="1"></td>
                    <td>Puerto específico:</td>                
                    <td>
                        <input type="text" id="txtPuertoEspecifico" name="txtPuertoEspecifico" value="<%= (indEditar == 1)? request.getAttribute("indMostrar"):""%>">
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
                    <td><input type="radio" name="rbModo" value="3" checked="ckecked"></td>
                    <td>Todos</td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="3">
                        Escanear puerto:
                        <input type="checkbox" name="cbPuerto" checked="checked"  />
                        Determinar servicios:
                        <input type="checkbox" name="cbServicio" />
                        <input type="hidden" name="hfPuerto" id="hfPuerto" />
                        <input type="hidden" name="hfServicio" id="hfServicio" />
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" value="Escanear" name="btnEscanear" />
                    </td>
                </tr>
            </table>
        </form>
        <div style="display: <%= (indEditar == 1)? "block":"none"%>">
            <table cellspacing="0" cellpadding="0" border="0" class="tabla" style="width:100%">
                <tr>
                     <th><b>Puerto</b></th>
                     <th><b>Protocolo</b></th>
                     <th><b>Servicio</b></th>
                     <th><b>Aplicación</b></th>
                     <th><b>Versión</b></th>                                      
                     <th><b>Respuesta</b></th>  
                </tr>
                     <% List<PortInfo> list = (List<PortInfo>)request.getAttribute("beans"); %> 
                     <% for(int i = 0; i < list.size(); i++ )
                        {
                            PortInfo entity = (PortInfo)list.get(i);
                     %>   
                            <tr class="<%= (i%2==0)?"normal":"alter" %>">
                                <td><%= entity.getPuerto() %></td>
                                <td><%= entity.getProtocolo() %></td>
                                <td><%= entity.getServicio() %></td>
                                <td><%= entity.getAplicacion() %></td>
                                <td><%= entity.getVersion() %></td>
                                <td>
                                    <a href="JavaScript:mostrarResultado('idProtocolo<%= entity.getProtocolo() %>');">Mostrar</a>
                                    <div id="idProtocolo<%= entity.getProtocolo() %>" style="display:none;">
                                        <table id="tblComando">
                                            <tr>
                                                <th>ID</th>
                                                <th>Protocolo</th>
                                                <th>Comando</th>
                                                <th>Resultado</th>
                                            </tr>
                                            <% 
                                                List<Comando> listComando = entity.getListaComando();
                                                for(int j = 0; j < listComando.size(); j++ ){
                                                    Comando cmd = (Comando)listComando.get(j);
                                            %>
                                                <tr>
                                                    <td><%= cmd.getId() %></td>
                                                    <td><%= cmd.getProtocolo() %></td>
                                                    <td><%= cmd.getCommand() %></td>
                                                    <td><%= cmd.getRespuesta() %></td>
                                                </tr>
                                            <%
                                                }
                                            %>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                     <% }%>       

            </table>
        </div>     
                     
        <div id="div-popup">
             <a class="bClose">X</a>
             <div id="resultado" class="resultado">
                
             </div> 
        </div>             
                     
                    
    </body>
</html>
