package sekurity;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/admin/who", "/user/who"})
public class WhoAmI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String remoteUser = request.getRemoteUser();
		Principal principal = request.getUserPrincipal();
		
		out.println("remoteUser: " + remoteUser);
		out.println("userPrincipal: " + principal);
		if(principal != null) {
			out.println("principal name: " + principal.getName());
		}
		
		if(request.isUserInRole("uprawniony")) {
			out.println("jest uprawniony");
		} else {
			out.println("nie jest uprawniony");			
		}
		
		if(request.isUserInRole("magik")) {
			out.println("jest magik");
		} else {
			out.println("nie jest magikiem");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
