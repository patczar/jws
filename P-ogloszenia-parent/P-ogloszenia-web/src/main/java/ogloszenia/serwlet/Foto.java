package ogloszenia.serwlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.util.Ustawienia;

@WebServlet("/foto")
public class Foto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Properties ustawienia = Ustawienia.wczytaj();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null) {
			Path sciezka = Paths.get(ustawienia.getProperty("katalog_foto"), id + ".jpg");
			byte[] dane = Files.readAllBytes(sciezka);
			
			response.setContentType("image/jpeg");
			response.getOutputStream().write(dane);
		}
	}

}
