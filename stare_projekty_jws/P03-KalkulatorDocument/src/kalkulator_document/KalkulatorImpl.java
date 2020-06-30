package kalkulator_document;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(
	endpointInterface="kalkulator_document.Kalkulator",
	wsdlLocation="WEB-INF/wsdl/kalkulator.wsdl",
	serviceName="kalkulator",
	portName="kalkulatorSOAP",
	targetNamespace="http://www.example.org/kalkulator/"
)
public class KalkulatorImpl implements Kalkulator {
	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}

	public int sub(int arg1, int arg2) {
		return arg1 - arg2;
	}

	public int mul(int arg1, int arg2) {
		return arg1 * arg2;
		
		// return Math.multiplyExact(arg1, arg2);
	}

	public void div(int arg1, int arg2, Holder<Integer> iloraz, Holder<Integer> reszta) {
		iloraz.value = arg1 / arg2;
		reszta.value = arg1 % arg2;
	}

	public int sum(List<Integer> liczby) {
		return liczby.stream().mapToInt(x -> x).sum();
	}
}
