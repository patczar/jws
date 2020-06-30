package ogloszenia.rest.ext;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ogloszenia.exn.NieznanyRekord;

@Provider
public class NieznanyRekordMapper implements ExceptionMapper<NieznanyRekord> {

	@Override
	public Response toResponse(NieznanyRekord e) {
		final String html = "<html><body>" 
				+ "<h2>404 Not Found</h2>"
				+ "<p style='color:#BB0044'>"
				+ e.getMessage()
				+ "</p>" + "</body></html>";

		return Response.status(404)
				.type("text/html")
				.entity(html)
			.build();
	}

}
