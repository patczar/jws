package ogloszenia.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ogloszenia.exn.BladAplikacji;

@Provider
public class BladAplikacjiMapper implements ExceptionMapper<BladAplikacji> {
	@Override
	public Response toResponse(BladAplikacji e) {
		final String html = "<html><body>"
				+ "<h2>500 Server Error</h2>"
				+ "<p style='color:red'>" + e.getMessage() + "</p>"
				+ "</body></html>";
		
		return Response.serverError()
				.type("text/html")
				.entity(html)
				.build();
	}
}
