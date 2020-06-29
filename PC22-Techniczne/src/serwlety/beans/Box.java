package serwlety.beans;

/* To jest trywialny przykład klasy zgodnej z wzorcem Java Bean.
 * Klasa:
 *  - posiada konstruktor domyślny (bezargumentowy);
 *    taki konstruktor może być wpisany jawnie, ale jeśli nie żadnego innego konstruktora, jest generowany automatycznie,
 *  - dostęp do własności (properties) odbywa się poprzez publiczne gettery i settery,
 *  - elementy dodatkowe, które nie są wymagane do działania JSP: obsługa zdarzeń, obserwowanie zmian w propertiesach, blokowanie zmian - poczytajcie w podręczniku i ew. w tutorialu na stronach Oracla.
 *    (te dodatkowe bajery mają znaczenie głównie w aplikacjach okienkowych, gdy używa się technologii "beans bidnding")
 */

public class Box {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		if(content == null)
			return "Puste pudełko";
		else
			return "Pudełko, w którym jest [" + content + "]";
	}
}
