package rozwiazane_zadania.zad10;

import java.io.File;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import pomoce.Args;
import rozwiazane_zadania.zad10.sklep.Sklep;
import rozwiazane_zadania.zad10.sklep.TTowar;

public class NajtanszyTowar_JAXB {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "herbata");
		try {
			JAXBContext ctx = JAXBContext.newInstance(Sklep.class);
			Unmarshaller u = ctx.createUnmarshaller();
			Sklep sklep = (Sklep)u.unmarshal(new File(args[0]));
			
			String idKategorii = args[1];

			BigDecimal cenaMinimalna = new BigDecimal(Integer.MAX_VALUE);
			String najtanszyTowar = null;

			for(TTowar t : sklep.getTowar()) {
				if(idKategorii.equals(t.getIdKategorii())) {
					System.out.println("Towar: "+t.getNazwa());
					BigDecimal cena = t.getCena();
					if(cena.compareTo(cenaMinimalna) < 0) {
						cenaMinimalna = cena;
						najtanszyTowar = t.getNazwa();
					}
				}
			}
			System.out.println("Najtanszy towar: "+najtanszyTowar+" , cena = "+cenaMinimalna);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


