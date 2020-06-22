package rozwiazane_zadania.zad13;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import pomoce.Args;
import rozwiazane_zadania.zad13.sklep.Sklep;
import rozwiazane_zadania.zad13.sklep.Towar;

public class WypiszTowary_JAXB {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml");
		try {
			JAXBContext ctx = JAXBContext.newInstance(Sklep.class);
			Unmarshaller u = ctx.createUnmarshaller();
			Sklep sklep = (Sklep)u.unmarshal(new File(args[0]));

			for(Towar t : sklep.getTowary()) {
				System.out.println("Towar: "+t.getNazwa() + "  " + t.getCenaNetto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
