package ogloszenia.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ogloszenia")
public class ListaOgloszen {
	@XmlElement(name="ogloszenie")
	public List<Samochodowe> ogloszenia;
}
