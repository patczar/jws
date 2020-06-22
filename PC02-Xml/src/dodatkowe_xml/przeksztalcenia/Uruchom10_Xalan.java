package dodatkowe_xml.przeksztalcenia;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Uruchom10_Xalan {

	public static void main(String[] args) {
		System.out.println("Przygotowania...");
		File xml = new File("waluty_2017f.xml");
		File xsl = new File("xsl10.xsl");
		File wynik = new File("wynik1.xml");
		
		int pozycjaOd = 3;
		int pozycjaDo = 10;

		// typy Source i Result obejmują różnego typu specyfikacje wejścia i wyjścia transformacji
		StreamSource src = new StreamSource(xml);
		StreamSource xslSource = new StreamSource(xsl);
		StreamResult res = new StreamResult(wynik);
		
		try {
			// pobieramy fabrykę transformerów - tutaj nastepuje wybór implementacji
			TransformerFactory tf = TransformerFactory.newInstance(
					"com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl",
					Uruchom10_Xalan.class.getClassLoader());
			
			System.out.println("Klasa fabryki: " + tf.getClass().getName());
			
			// tworzymy transformer na podstawie arkusza XSLT
			Transformer transformer = tf.newTransformer(xslSource);
			
			transformer.setParameter("pozycja-od", pozycjaOd);
			transformer.setParameter("pozycja-do", pozycjaDo);

			// uruchamiamy przekształcenie XSLT
			System.out.println("Uruchamiam XSLT...");
			transformer.transform(src, res);

			System.out.println("Gotowe...");
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
	}

}
