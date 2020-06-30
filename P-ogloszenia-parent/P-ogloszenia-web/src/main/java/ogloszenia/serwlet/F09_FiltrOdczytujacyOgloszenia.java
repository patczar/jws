package ogloszenia.serwlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ogloszenia.beans.OgloszeniaBean;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Samochodowe;

@WebFilter("/J09_OgloszeniaFiltr.jsp")
public class F09_FiltrOdczytujacyOgloszenia implements Filter {
	private OgloszeniaBean ogloszeniaBean = new OgloszeniaBean();


	public void init(FilterConfig fConfig) {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		ogloszeniaBean.setCenaMinimalna(min);
		ogloszeniaBean.setCenaMaksymalna(max);
		
		try {
			List<Samochodowe> listaOgloszen = ogloszeniaBean.getOgloszeniaWgCeny();
			request.setAttribute("ogloszenia", listaOgloszen);
		} catch (BladBazyDanych e) {
			throw new ServletException(e.getMessage(), e);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
