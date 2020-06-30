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

// Na WildFly to uruchomi się pod adresem
// http://localhost:8080/P12-OgloszeniaWeb-1.0/P01_OdczytTekstowy
// (dopisany nr wersji z Mavena)

@WebServlet("/P01_OdczytTekstowy")
public class P01_OdczytTekstowy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWszystkie();
			
			out.printf("Odczytano %d ogłoszeń:\n", ogloszenia.size());
/*			out.flush();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
*/
			for(Samochodowe ogl : ogloszenia) {
				out.println(ogl);
			}
			
		} catch (BladBazyDanych e) {
			e.printStackTrace(out);
		}
	}
}
