package przyklady_dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import pomoce.Statystyki;

public class WypisywaczDOM {

	public static void main(String[] args) {
		try {
			if(args.length < 1) {
				System.out.println("Za mało argumentów");
				return;
			}
			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(args[0]);
			System.out.println("Wczytano dokument");
			
			Statystyki statystyki = wyliczStatystyki(doc);
			System.out.println(statystyki.wypisz());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static Statystyki wyliczStatystyki(Document doc) {
		Statystyki statystyki = new Statystyki();
		dodajDoStatystyk(statystyki, doc);
		
		return statystyki;
	}

	private static void dodajDoStatystyk(Statystyki stat, Node nd) {
		switch(nd.getNodeType()) {
		case Node.DOCUMENT_NODE :
			System.out.println("Jestem w korzeniu");
			for(Node dziecko = nd.getFirstChild(); dziecko != null; dziecko = dziecko.getNextSibling())
				dodajDoStatystyk(stat, dziecko);
			break;
		case Node.ELEMENT_NODE :
			System.out.println("Jestem w elemencie "+nd.getNodeName());
			for(Node dziecko = nd.getFirstChild(); dziecko != null; dziecko = dziecko.getNextSibling())
				dodajDoStatystyk(stat, dziecko);
			System.out.println("Koniec elementu "+nd.getNodeName());
			break;
		case Node.TEXT_NODE :
			System.out.println("Jestem w tekście ["+nd.getNodeValue()+"] elementu "+
			nd.getParentNode().getNodeName());
			break;
		}
	}
	
}
