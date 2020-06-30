package ogloszenia.serwlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ogloszenia.beans.OgloszeniaBean;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Samochodowe;


@WebServlet("/P07_Ogloszenia")
public class P07_Ogloszenia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OgloszeniaBean ogloszeniaBean = new OgloszeniaBean();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		ogloszeniaBean.setCenaMinimalna(min);
		ogloszeniaBean.setCenaMaksymalna(max);
		
		try {
			List<Samochodowe> listaOgloszen = ogloszeniaBean.getOgloszeniaWgCeny();
			
			request.setAttribute("ogloszenia", listaOgloszen);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/J07_Ogloszenia.jsp");
			if(dispatcher != null) {

				// przekazujemy obsługę zapytania do skryptu JSP
				dispatcher.forward(request, response);
				// sterowanie wróci w to miejsce, ale z tego już nie korzystamy
			}
		} catch (BladBazyDanych e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
