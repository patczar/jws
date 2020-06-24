package ogloszenia.model;

import java.util.List;

public class ListaOgloszen {
	public List<OgloszenieSamochodowe> ogloszenia;
	
	public static ListaOgloszen nowa(List<OgloszenieSamochodowe> ogloszenia) {
		ListaOgloszen lista = new ListaOgloszen();
		lista.ogloszenia = ogloszenia;
		return lista;
	}
}
