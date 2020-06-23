package kalkulator.impl;


import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import kalkulator.*;
import javax.jws.WebService;

@WebService(serviceName = "KalkulatorWrapped",
	portName = "KalkulatorWrappedSOAP",
	wsdlLocation = "/WEB-INF/wsdl/KalkulatorWrapped.wsdl",
	endpointInterface = "kalkulator.KalkulatorWrapped",
	targetNamespace = "http://www.example.org/KalkulatorWrapped/")
public class KalkulatorWrappedImpl implements KalkulatorWrapped {
	// w stylu WRAPPED parametry i wyniki są dodatkowo opakowywane w element XML, którego nazwa jest taka jak nazwa operacji
	// element opakowujący jest zdefiniowany w schema w WSDL
	
	public int sub(int arg1, int arg2) {
		return 0;
	}

	public int add(int arg1, int arg2) {
		return 0;
	}

	public void div(int arg1, int arg2, javax.xml.ws.Holder<java.lang.Integer> quotient,
			javax.xml.ws.Holder<java.lang.Integer> rest) {
		return;
	}

	public int inc(int arg) {
		return 0;
	}
}