package ogloszenia.serwlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.model.Samochodowe;

@WebServlet("/DodajDoSchowka")
public class DodajDoSchowka extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		try {
			int idOgloszenia = Integer.parseInt(idStr);
			try(DostepDoBazy db = new DostepDoBazy()) {
				OgloszeniaDAO dao = db.ogloszeniaDAO();
				
				Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
				
				HttpSession sesja = request.getSession();
				Collection<Samochodowe> kolekcja = (Collection<Samochodowe>) sesja.getAttribute("schowek");
				kolekcja.add(ogloszenie);
				
				// nie trzeba robić setAttribute, bo w sesji jest referencja do kolekcji, którą zmodyfikowaliśmy
				
				response.sendRedirect("ogloszenia.html");
			}			
		} catch (Exception e) {
			// ignorujemy sytuacje wyjątkowe: brak ogłoszenia, zły format parametru itd.
		}
	}
}
