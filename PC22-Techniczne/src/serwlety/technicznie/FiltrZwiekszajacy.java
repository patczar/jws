package serwlety.technicznie;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import serwlety.beans.InfoBean;

@WebFilter({"/zakresy_filtr", "/zakresy_filtr.jsp"})
public class FiltrZwiekszajacy implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init filtra");
	}

	public void destroy() {
		System.out.println("destroy filtra");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter - początek");
		InfoBean licznik = (InfoBean) request.getAttribute("licznik_req");
		if(licznik != null) {
			licznik.increment(50);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);

		// sterowanie wraca do filtru już po wysłaniu treści z jsp lub serwletu
		System.out.println("doFilter koniec");
	}
}
