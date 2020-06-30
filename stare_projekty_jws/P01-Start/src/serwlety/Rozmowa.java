package serwlety;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Rozmowa")
public class Rozmowa extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imie = request.getParameter("imie");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Rozmowa</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Rozmowa serwletowa</h1>");
		out.println("<form>");
		out.println("Jak masz na imię?");
		out.println("<input type='text' name='imie'>");
		out.println("<button>OK</button>");
		out.println("</form>");
		
		if(imie != null) {
			out.println("<p class='powitanie'>Witaj " + imie + "</p>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
