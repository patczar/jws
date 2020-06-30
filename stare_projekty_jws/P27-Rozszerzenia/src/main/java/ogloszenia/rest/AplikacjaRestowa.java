package ogloszenia.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/* Ta klasa jest aktywatorem technologii JAX-RS.
 * Musi: dziedziczyć z klasy Application, posiadać adnotację @ApplicationPath
 */

@ApplicationPath("/")
public class AplikacjaRestowa extends Application {

}
