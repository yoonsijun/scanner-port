package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {

	public DemoServlet(){
		
	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		System.out.println("In the Index servlet");
		getServletContext().getRequestDispatcher("/demo.jsp").forward(request,response);
    }
}
