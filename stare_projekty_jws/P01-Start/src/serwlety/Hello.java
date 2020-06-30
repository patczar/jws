package serwlety;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("text/html");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		// do wysyłania uzywamy PrintWritera pobranego w ten sposób
		PrintWriter out = response.getWriter();
		
		// do wysyłania danych binarnych używamy analogicznie obiektu OutputStream pobranego tak:
		// ServletOutputStream output = response.getOutputStream();
		// output.write(bajty);
		
		out.println("Hello!");
		out.println("Teraz jest <b>godzina</b> " + LocalTime.now());
		out.println("Żółte gąski gegają na łące w Łodzi.");		
		out.close();
	}

}
