package rozwiazane_zadania.zad05;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;

public class SklepNSContext implements NamespaceContext {

	private static final List<String> PREF_LIST = Collections.singletonList("sklep");

	@Override
	public String getNamespaceURI(String prefix) {
		if("sklep".equals(prefix))
			return "urn:sklep";
		else
			return null;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		if("urn:sklep".equals(namespaceURI))
			return "sklep";
		else
			return null;
	}

	@Override
	public Iterator getPrefixes(String namespaceURI) {
		return PREF_LIST.iterator();
	}
}
