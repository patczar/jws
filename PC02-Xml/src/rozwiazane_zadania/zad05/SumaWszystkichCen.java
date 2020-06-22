package rozwiazane_zadania.zad05;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import pomoce.Args;

public class SumaWszystkichCen {

	public static void main(String[] args) {
		try {
			args = Args.argsOrDefault(args, "pliki/sklep.xml");

			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fact.newDocumentBuilder();
			
			XPathFactory xf = XPathFactory.newInstance();
			XPath xpath = xf.newXPath();
			
			Document doc = builder.parse(args[0]);
			
			Double wynik = (Double)xpath.evaluate("sum(//cena)", doc, XPathConstants.NUMBER);
			System.out.println(wynik);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		

	}

}
