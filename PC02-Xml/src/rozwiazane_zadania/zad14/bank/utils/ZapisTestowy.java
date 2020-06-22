package rozwiazane_zadania.zad14.bank.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pomoce.Args;
import rozwiazane_zadania.zad14.bank.model.Bank;

public class ZapisTestowy {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "bank1.xml");
		Bank bank = Przyklady.przykladowyBank();
		
		try {
			JAXBContext ctx = JAXBContext.newInstance(Bank.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(bank, new File(args[0]));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
