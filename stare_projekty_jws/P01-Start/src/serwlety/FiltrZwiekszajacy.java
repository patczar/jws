package serwlety;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.InfoBean;

// Filtr zwiększa wartość zmiennej licznik-req w obiekcie request o 50
// (a jeśli jej nie ma, to ustawia).
@WebFilter({"/ZakresyFiltr"})
public class FiltrZwiekszajacy implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filtr - początek");
		InfoBean obiekt = (InfoBean) request.getAttribute("licznik-req");
		if(obiekt == null) {
			obiekt = new InfoBean();
			request.setAttribute("licznik-req", obiekt);
		}
		obiekt.setLicznik(obiekt.zwieksz(50));
		
		// pass the request along the filter chain
		chain.doFilter(request, response);

		// sterowanie wraca do filtru już po wysłaniu treści z jsp (w tym przypadku)
		System.out.println("filtr - koniec, licznik = " + obiekt.getLicznik());
	}


}
