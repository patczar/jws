package ogloszenia.serwlet;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ogloszenia.model.Samochodowe;

@WebListener
public class L08_InicjalizacjaSesji implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent ev)  {
    	HttpSession sesja = ev.getSession();
    	System.out.println("Utworzenie sesji " + sesja.getId());
    	sesja.setMaxInactiveInterval(30);
    	
    	Collection<Samochodowe> kolekcja = new LinkedHashSet<>();
    	sesja.setAttribute("schowek", kolekcja);
    }

    public void sessionDestroyed(HttpSessionEvent ev)  { 
    	HttpSession sesja = ev.getSession();
    	System.out.println("Koniec sesji " + sesja.getId());
    }
	
}
