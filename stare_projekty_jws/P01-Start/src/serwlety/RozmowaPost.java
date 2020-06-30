package serwlety;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RozmowaPost")
public class RozmowaPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// zapytanie typu GET - zakładamy, że to pierwsze wejście na stronę - nie czytamy parametru
		
		wypiszPocztatekStrony(out);
		wypiszKoniecStrony(out);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		// zapytanie typu POST - zakładamy, że został wypełniony formularz - czytamy parametr imie i wyświetlamy powitanie

		wypiszPocztatekStrony(out);		
		wypiszPowitanie(request, out);
		wypiszKoniecStrony(out);
	}
	
	private void wypiszPocztatekStrony(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Rozmowa</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Rozmowa serwletowa</h1>");
		out.println("<form method='post'>");
		out.println("Jak masz na imię?");
		out.println("<input type='text' name='imie'>");
		out.println("<button>OK</button>");
		out.println("</form>");
	}

	private void wypiszKoniecStrony(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}
	
	private void wypiszPowitanie(HttpServletRequest request, PrintWriter out) {
		String imie = request.getParameter("imie");
		out.println("<p class='powitanie'>Witaj " + imie + "</p>");
	}

}
