package serwlety.przyklady;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Kalkulator1")
public class Kalkulator1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println(".error {color:red}");
		out.println("</style>");
		out.println("<title>Kalkulator serwletowy</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Kalkulator serwletowy</h1>");
		
		out.println("<p>Podaj działanie:</p>");
		out.println("<form method='post'>");
		out.println("<input name='x' type='text'>");
		out.println("<select name='operacja'>");
		out.println("<option value='+'>+</option>");
		out.println("<option value='-'>-</option>");
		out.println("<option value='*'>*</option>");
		out.println("<option value='/'>/</option>");
		out.println("</select>");
		out.println("<input name='y' type='text'>");
		out.println("<button>Oblicz</button>");
		out.println("</form>");
		
		String strX = request.getParameter("x");
		String strY = request.getParameter("y");
		String op = request.getParameter("operacja");
		
		if(isNotEmpty(strX) && isNotEmpty(strY) && isNotEmpty(op)) {
			try {
				int x = Integer.parseInt(strX);
				int y = Integer.parseInt(strY);
				int wynik = LogikaKalkulatora.oblicz(x, y, op);
				
				out.println("<div class='wynik'>");
				out.println(x + " " + op + " " + y + " = <strong>" + wynik + "</strong>");
				out.println("</div>");
			} catch (Exception e) {
				out.print("<div class='error'>Błąd: " + e + "</div>");
			}
		}
		
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private static boolean isNotEmpty(String s) {
		return s != null && !s.isEmpty();
	}
}
