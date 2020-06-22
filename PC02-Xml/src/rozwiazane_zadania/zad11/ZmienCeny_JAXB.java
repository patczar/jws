package rozwiazane_zadania.zad11;

import java.io.File;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pomoce.Args;
import rozwiazane_zadania.zad10.sklep.Sklep;
import rozwiazane_zadania.zad10.sklep.TTowar;

public class ZmienCeny_JAXB {

	private static final BigDecimal STO = new BigDecimal(100);
	
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "new_11.xml", "herbata", "20");
		if(args.length < 4) {
			System.out.println("Za malo parametrow. Podaj plik-wej, plik-wyj, id-kategorii, podwyzka");
			return;
		}
		File wejscie = new File(args[0]);
		File wyjscie = new File(args[1]);
		String idKategorii = args[2];
		BigDecimal podwyzka = new BigDecimal(args[3]);
		
		try {
			JAXBContext ctx = JAXBContext.newInstance(Sklep.class);
			Unmarshaller u = ctx.createUnmarshaller();
			Sklep sklep = (Sklep)u.unmarshal(wejscie);

			for(TTowar t : sklep.getTowar()) {
				if(idKategorii.equals(t.getIdKategorii())) {
					System.out.println("Towar: "+t.getNazwa());
					BigDecimal cena = t.getCena();
					cena = cena.multiply(BigDecimal.ONE.add(podwyzka.divide(STO)));
					cena = cena.setScale(2);
					t.setCena(cena);
				}
			}
			
			Marshaller m = ctx.createMarshaller();
			m.marshal(sklep, wyjscie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


