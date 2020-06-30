<%@page import="ogloszenia.exn.BladBazyDanych"%>
<%@page import="ogloszenia.model.Samochodowe"%>
<%@page import="java.util.List"%>
<%@page import="ogloszenia.baza.OgloszeniaDAO"%>
<%@page import="ogloszenia.baza.DostepDoBazy"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='styl.css'>
<title>Ogłoszenia - wersja JSP 03</title>
</head>
<body>
<h1>Wszystkie ogłoszenia</h1>
<p>Wersja zrobiona nieporządnie!</p>
<%
try(DostepDoBazy db = new DostepDoBazy()) {
	OgloszeniaDAO dao = db.ogloszeniaDAO();
	List<Samochodowe> ogloszenia = dao.odczytajWszystkie();
	for(Samochodowe ogl : ogloszenia) {
%>

<div class='ogloszenie'>
<h2><%= ogl.getTytul() %></h2>
<p>Cena: <strong><%= ogl.getCena() %></strong></p>
<p>Marka: <%=ogl.getMarka() + " " + ogl.getModel()%></p>
</div>"

<%

	}
} catch (BladBazyDanych e) {
%>
	<div class='error'><%= e %></div>
<%	
}
%>

</body>
</html>
