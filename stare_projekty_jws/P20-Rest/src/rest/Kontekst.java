package rest;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/kontekst")
@Produces("text/plain")
public class Kontekst {	
	@GET
	public String info(
		@Context UriInfo uriInfo,
		@Context HttpHeaders headers,
		@Context Request restRequest,
		@Context ServletContext servletContext,
		@Context ServletConfig servletConfig,
		@Context HttpServletRequest servletRequest,
		@Context HttpServletResponse servletResponse
	) {
		StringBuilder b = new StringBuilder();
		
		b.append("UriInfo\n");
		b.append("Base URI     : ").append(uriInfo.getBaseUri()).append('\n');
		b.append("Absolute path: ").append(uriInfo.getAbsolutePath()).append('\n');
		b.append("Path         : ").append(uriInfo.getPath()).append('\n');
		
		b.append("\nHttpHeaders\n");
		b.append(headers.getRequestHeaders());

		// Request restRequest - wiąże się z obsługą keszowania
		
		b.append("\nServletContext\n");
		b.append("ServlerInfo: ").append(servletContext.getServerInfo()).append('\n');
		
		b.append("\nServletRequest\n");
		b.append("RemoteAddr: ").append(servletRequest.getRemoteAddr()).append('\n');
		b.append("LocalAddr : ").append(servletRequest.getLocalAddr()).append('\n');
		b.append("URI       : ").append(servletRequest.getRequestURI()).append('\n');
		b.append("Parametry :\n");
		
		for (Map.Entry e : servletRequest.getParameterMap().entrySet()) {
			String[] v = (String[]) e.getValue();
			b.append(" * " + e.getKey() + " : " + Arrays.toString(v) + "\n");
		}
		
		b.append("\nZalogowany : ").append(servletRequest.getRemoteUser()).append('\n');
		
		return b.toString();
	}

}
