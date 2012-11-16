/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import core.domain.PortInfo;
import core.facade.Escaner;
import java.io.IOException;
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

    String page="/scanner.jsp";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try
        {
            String host = request.getParameter("txtHost");
            String modo = request.getParameter("rbModo");
	    Integer puerto = Integer.parseInt(request.getParameter("txtPuertoEspecifico"));
	    Integer puertoDesde = Integer.parseInt(request.getParameter("txtPuertoDesde"));
            Integer puertoHasta = Integer.parseInt(request.getParameter("txtPuertoHasta"));

            Escaner  escaner = new Escaner();
            List<PortInfo> list = escaner.escanerPuertos(host,modo, puerto, puertoDesde, puertoHasta);
            request.setAttribute("beans",list);
            request.setAttribute("indMostrar", "1");

            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	    if (dispatcher != null){
		dispatcher.forward(request, response);
            }
        }
        catch(Exception ex)
        {
            System.err.println("error");
        }

    }
}
