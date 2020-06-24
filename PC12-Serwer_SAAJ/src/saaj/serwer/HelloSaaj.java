package saaj.serwer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@WebServlet("/Hello")
public class HelloSaaj extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NS = "urn:szkolenie.jws.hello";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("wsdl".equalsIgnoreCase(request.getQueryString())) {
			sendWSDL(response);
		} else {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("Halo tu webserwis.");
			out.println("Przyślij SOAP-a za pomocą POST");
			out.close();			
		}
	}

	private void sendWSDL(HttpServletResponse response) throws IOException {
		response.setContentType("application/xml");
		copyStream(getServletContext().getResourceAsStream("hello.wsdl"), response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			MimeHeaders headers = new MimeHeaders();
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = request.getHeader(headerName);
				headers.setHeader(headerName, headerValue);
			}

			String name = null;
			int repeat = 1;

			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage req = mf.createMessage(headers, request.getInputStream());
			SOAPBody reqBody = req.getSOAPBody();
			NodeList found = reqBody.getElementsByTagNameNS(NS, "sayHello");

			if (found.getLength() > 0) {
				Element sayHello = (Element) found.item(0);

				found = sayHello.getElementsByTagNameNS(XMLConstants.NULL_NS_URI, "name");
				if (found.getLength() > 0) {
					name = found.item(0).getTextContent();
				}
				found = sayHello.getElementsByTagNameNS(XMLConstants.NULL_NS_URI, "repeat");
				if (found.getLength() > 0) {
					try {
						repeat = Integer.parseInt(found.item(0).getTextContent());
					} catch (NumberFormatException nfe) {
						repeat = 0;
					}
				}
			}

			SOAPMessage resp = mf.createMessage();
			SOAPBody respBody = resp.getSOAPBody();

			if (name != null) {
				StringBuilder txt = new StringBuilder();
				for (int i = 0; i < repeat; ++i) {
					txt.append("Hello ");
					txt.append(name);
					txt.append('\n');
				}
				SOAPElement sayHelloResponse = respBody.addBodyElement(new QName(NS, "sayHelloResponse", "pre"));
				SOAPElement message = sayHelloResponse.addChildElement(new QName(XMLConstants.NULL_NS_URI, "message"));
				message.setTextContent(txt.toString());
			} else {
				Name qname = SOAPFactory.newInstance().createName("Client", null, SOAPConstants.URI_NS_SOAP_ENVELOPE);
				SOAPFault fault = respBody.addFault();
				fault.setFaultCode(qname);
				fault.setFaultString("Bad request");
			}

			response.setContentType("text/xml");
			resp.writeTo(response.getOutputStream());
			response.getOutputStream().close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private static void copyStream(InputStream in, ServletOutputStream out) throws IOException {
		byte[] buf = new byte[4096];
		int count;
		while((count = in.read(buf)) != -1) {
			out.write(buf, 0, count);
		}
		in.close();
		out.close();
	}
}
