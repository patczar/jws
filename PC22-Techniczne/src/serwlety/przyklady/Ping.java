package serwlety.przyklady;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ping")
public class Ping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adresKlienta = request.getRemoteAddr();
		System.out.println("ping " + adresKlienta);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		try(PrintWriter out = response.getWriter()) {
			out.println("Twój adres to " + adresKlienta);
		}
	}

}
