package ogloszenia.serwlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Samochodowe;

@WebServlet("/P02_OgloszeniaHtml")
public class P02_OgloszeniaHtml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'>");
		out.println("<title>Ogłoszenia</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Ogłoszenia</h1>");
	
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWszystkie();
			for(Samochodowe ogl : ogloszenia) {
				out.println(ogl.dajHtml());
			}
		} catch (BladBazyDanych e) {
			out.println("<div class='error'>");
			out.println("<p><strong>" + e + "</strong></p>");
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			out.println("</div>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
}
