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
       <div class="titulo-general">Resultado</div>
                        <div class="texto-general">
                            Programa que permite detectar los puertos abiertos de un host remoto,
                            identificar el protocolo y el servicio que corren en dichos puertos.                            
                        </div>
        
        
         <%
              Integer indEditar = (request.getAttribute("indMostrar")!=null)?Integer.parseInt(request.getAttribute("indMostrar").toString()):0;
         %>

        <div style="display: <%= (indEditar == 1)? "block":"none"%>">
            <table cellspacing="0" cellpadding="0" border="0" style="width:100%">
                <tr class="trheader">
                     <th class="thnormal">Puerto</th>
                     <th class="thnormal">Protocolo</th>
                     <th class="thnormal">Servicio</th>
                     <th class="thnormal">Aplicación</th>
                     <th class="thnormal">Versión</th>                                      
                     <th class="thnormal">Respuesta</th>  
                </tr>
                     <% List<PortInfo> list = (List<PortInfo>)request.getAttribute("beans"); %> 
                     <% for(int i = 0; i < list.size(); i++ )
                        {
                            PortInfo entity = (PortInfo)list.get(i);
                     %>   
                            <tr>
                                <td class="tdnormal"><%= entity.getPuerto() %></td>
                                <td class="tdverde"><%= entity.getProtocolo() %></td>
                                <td class="tdnormal"><%= entity.getServicio() %></td>
                                <td class="tdnormal"><%= entity.getAplicacion() %></td>
                                <td class="tdnormal"><%= entity.getVersion() %></td>
                                <td class="tdverde">
                                    <a href="JavaScript:mostrarResultado('idProtocolo<%= entity.getProtocolo() %>');">Mostrar</a>
                                    <div id="idProtocolo<%= entity.getProtocolo() %>" style="display:none;">
                                        <table id="tblComando" cellspacing="0" cellpadding="0" border="0" class="tabla">
                                            <tr class="trheader">
                                                <th class="thnormal">ID</th>
                                                <th class="thnormal">Protocolo</th>
                                                <th class="thnormal">Comando</th>
                                                <th class="thnormal">Resultado</th>
                                            </tr>
                                            <% 
                                                List<Comando> listComando = entity.getListaComando();
                                                for(int j = 0; j < listComando.size(); j++ ){
                                                    Comando cmd = (Comando)listComando.get(j);
                                            %>
                                                <tr class="trresultado">
                                                    <td class="tdnormal"><%= cmd.getId() %></td>
                                                    <td class="tdnormal"><%= cmd.getProtocolo() %></td>
                                                    <td class="tdverde" style="text-align: left;"><%= cmd.getCommand() %></td>
                                                    <td class="tdnormal" style="text-align: left;"><%= cmd.getRespuesta() %></td>
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
             <div class="titulo-general" style="margin-top:30px; margin-left: 20px;">Resultado</div>
             <div id="resultado" class="resultado">
                 
                
                
             </div> 
        </div>             
                     
                    
    </body>
</html>
