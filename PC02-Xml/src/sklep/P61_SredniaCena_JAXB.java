package sklep;

import java.io.File;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sklep.jaxb.Sklep;
import sklep.jaxb.TTowar;

public class P61_SredniaCena_JAXB {

	public static void main(String[] args) {
		System.out.println("Startujemy..");
		
		try {
			String plik = "pliki/sklep.xml";
			String kategoria = "herbata";
			
			JAXBContext ctx = JAXBContext.newInstance(Sklep.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			
			Sklep sklep = (Sklep)unmarshaller.unmarshal(new File(plik));
			double suma = 0.0;
			int licznik = 0;
			for (TTowar towar : sklep.getTowar()) {
				if(Objects.equals(towar.getIdKategorii(), kategoria)) {
					suma += towar.getCena().doubleValue();
					licznik ++;
				}
			}
			double srednia = suma / licznik;
			System.out.println(srednia);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
