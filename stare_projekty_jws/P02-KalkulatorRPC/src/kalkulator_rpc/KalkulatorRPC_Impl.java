package kalkulator_rpc;

import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(endpointInterface="kalkulator_rpc.KalkulatorRpc")
public class KalkulatorRPC_Impl implements KalkulatorRpc {
	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}

	public int sub(int arg1, int arg2) {
		return arg1 - arg2;
	}

	public int mul(int arg1, int arg2) {
		return arg1 * arg2;
	}

	public void div(int arg1, int arg2, Holder<Integer> iloraz, Holder<Integer> reszta) {
		iloraz.value = arg1 / arg2;
		reszta.value = arg1 % arg2;
	}

	public int suma(WieleLiczb liczby) {
		return liczby.liczba.stream().mapToInt(x -> x).sum();
	}

}
