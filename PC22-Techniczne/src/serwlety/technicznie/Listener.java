package serwlety.technicznie;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import serwlety.beans.InfoBean;

// Ten listener wypisuje na konsolę informacje o zdarzeniach takich jak:
// * uruchomienie aplikacji
// * zatrzymanie aplikacji
// * inicjalizacja sesji
// * wygaśnięcie sesji
// * nadejście requestu
// * zakończenie obsługi requestu
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

	// wykonywane po uruchomieniu aplikacji na serwerze
    public void contextInitialized(ServletContextEvent ev)  {
    	System.out.println("start aplikacji");
    	
    	// dodaję atrybut do kontekstu aplikacji (do "application scope")
    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(100);
    	obiekt.setNapis("servlet context");
    	ev.getServletContext().setAttribute("licznik_app", obiekt);
    }

    // wykonywane podczas "kontrolowanego" zamykania aplikacji
    public void contextDestroyed(ServletContextEvent ev)  { 
    	System.out.println("koniec aplikacji");
    }

    // gdy jest tworzona nowa sesja
    public void sessionCreated(HttpSessionEvent ev)  { 
    	System.out.println("utworzenie sesji " + ev.getSession().getId());
    	
    	// timeout sesji w sekundach
    	ev.getSession().setMaxInactiveInterval(20);

    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(100);
    	obiekt.setNapis("sesja");
    	ev.getSession().setAttribute("licznik_ses", obiekt);
    }

    public void sessionDestroyed(HttpSessionEvent ev) {
    	System.out.println("koniec sesji " + ev.getSession().getId());
    }

    public void requestInitialized(ServletRequestEvent ev) {
    	String txt = "przyszedł request";
    	ServletRequest servletRequest = ev.getServletRequest();
    	if(servletRequest instanceof HttpServletRequest) {
    		txt += " z " + ((HttpServletRequest)servletRequest).getRemoteAddr()
    			+ " pod adres " + ((HttpServletRequest)servletRequest).getRequestURI();
    	}
    	
    	System.out.println(txt);
    	
    	InfoBean obiekt = new InfoBean();
    	obiekt.setLicznik(100);
    	obiekt.setNapis("request");
    	ev.getServletRequest().setAttribute("licznik_req", obiekt);
    }

    public void requestDestroyed(ServletRequestEvent ev)  { 
    	System.out.println("obsługa requestu zakończona");
    }
}
