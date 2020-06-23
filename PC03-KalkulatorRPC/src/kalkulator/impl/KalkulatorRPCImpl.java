package kalkulator.impl;


import kalkulator.*;
import javax.jws.WebService;

@WebService(serviceName = "KalkulatorRPC", endpointInterface = "kalkulator.KalkulatorRPC", targetNamespace = "http://www.example.org/KalkulatorRPC/")
public class KalkulatorRPCImpl implements KalkulatorRPC {
	public int sub(int arg1, int arg2) {
		return arg1-arg2;
	}

	public int add(int arg1, int arg2) {
		return arg1+arg2;
	}

	public int mul(int arg1, int arg2) {
		return arg1*arg2;
	}

	public int inc(int incRequest) {
		return incRequest+1;
	}
}