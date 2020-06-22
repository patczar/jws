package przyklady_xpath;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class SklepNSContext implements NamespaceContext {

	@Override
	public String getNamespaceURI(String prefix) {
		if("sklep".equals(prefix))
			return "urn:sklep";
		else
			return null;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator getPrefixes(String namespaceURI) {
		// TODO Auto-generate)d method stub
		return null;
	}
}
