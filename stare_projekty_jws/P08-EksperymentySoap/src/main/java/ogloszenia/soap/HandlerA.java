package ogloszenia.soap;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HandlerA implements SOAPHandler<SOAPMessageContext> {
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("A handleMessage");
		return true;
	}

	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("A handleFault");
		return false;
	}

	public void close(MessageContext context) {
		System.out.println("A close");
	}

	public Set<QName> getHeaders() {
		System.out.println("A headers");
		return null;
	}
}
