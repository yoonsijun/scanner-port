/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import core.domain.*;
import core.facade.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author rcastro
 */
public class EscanearPuertoServlet extends HttpServlet {

    String page = "/scanner.jsp";
    String INIT = "inicio";
    String ESCANEAR = "escanear";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try
        {
            String host = request.getParameter("host");  
            
            System.out.println(request.getParameter("modo"));
            Integer modo = Integer.parseInt(request.getParameter("modo"));
	               
            
            Integer puerto = 0;
            Integer puertoDesde = 0;
            Integer puertoHasta = 0;
            String puertos = "";
            
            if(modo == 1)
            {
                puerto = Integer.parseInt(request.getParameter("pto"));
            }	    
            if(modo == 2)
            {                
                puertos = request.getParameter("ptos");
            }            
            if(modo == 3)
            {
                puertoDesde = Integer.parseInt(request.getParameter("ptodesde"));
                puertoHasta = Integer.parseInt(request.getParameter("ptohasta"));
            }
            
            Boolean indServicio = true;
            
            String ip = Utilidades.obtenerIpServer(host);
            System.out.println("ip:"+ip);
            
            Escaner  escaner = new Escaner();
            
            List<PortInfo> list = escaner.escanerPuertos(ip, modo, puerto, puertoDesde, puertoHasta, puertos);
            System.out.println("nro puertos:"+list.size());

            Servicio servicio = new Servicio();
                        
            list = servicio.obtenerServicios(ip, list);
                       
            request.setAttribute("beans",list);
            request.setAttribute("indMostrar", "1"); 
                      
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	    if (dispatcher != null){
		dispatcher.forward(request, response);
            }             
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.err.println("error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try
        {
            String host = request.getParameter("host");            
            Integer modo = Integer.parseInt(request.getParameter("modo"));
	    
            Integer puerto = 0;
            Integer puertoDesde = 0;
            Integer puertoHasta = 0;
            
            if(modo == 1)
            {
                puerto = Integer.parseInt(request.getParameter("pto"));
            }	    
            if(modo == 2)
            {
                puertoDesde = Integer.parseInt(request.getParameter("ptodesde"));
                puertoHasta = Integer.parseInt(request.getParameter("ptohasta"));
            }
            
            Boolean indServicio = true;
            
            String ip = Utilidades.obtenerIpServer(host);
            
            Escaner  escaner = new Escaner();
            
            
            //List<PortInfo> list = escaner.escanerPuertos(ip, modo, puerto, puertoDesde, puertoHasta);
            
            Servicio servicio = new Servicio();
                        
            //list = servicio.obtenerServicios(ip, list);
                        
            
            //request.setAttribute("beans",list);
            request.setAttribute("indMostrar", "1"); 
          
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	    if (dispatcher != null){
		dispatcher.forward(request, response);
            } 
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.err.println("error");
        }

    }
}
