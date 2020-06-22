package przyklady_dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import pomoce.Statystyki;

public class StatystykiDOM {

	public static void main(String[] args) {
		try {
			if(args.length < 1) {
				System.out.println("Za mało argumentów");
				return;
			}
			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(args[0]);
			
			Statystyki statystyki = wyliczStatystyki(doc);
			System.out.println(statystyki.wypisz());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static Statystyki wyliczStatystyki(Document doc) {
		Statystyki statystyki = new Statystyki();
		dodajDoStatystyk(statystyki, doc, 0);
		
		return statystyki;
	}

	private static void dodajDoStatystyk(Statystyki stat, Node node, int glebokosc) {
		/* To jest dobre, gdy sprawdzamy glebokosc dla jednego wezla.
		 * Przy przetwarzaniu rekurencyjnym lepiej przekazywac sobie glebokosc w parametrze. 
		int glebokosc = 0;
		for(Node wgore = node; wgore != null; wgore = wgore.getParentNode()) {
			glebokosc++;
		} */
		
		stat.zapamietajGlebokosc(glebokosc);
		
		switch(node.getNodeType()) {
		case Node.ELEMENT_NODE :
			stat.dodajElement(node.getNodeName());

		case Node.DOCUMENT_NODE :
			for(Node dziecko = node.getFirstChild(); dziecko != null; dziecko = dziecko.getNextSibling())
				dodajDoStatystyk(stat, dziecko, glebokosc+1);
			break;
		case Node.TEXT_NODE :
			stat.dodajTekst(node.getNodeValue().length());
			break;
		}
	}
}
