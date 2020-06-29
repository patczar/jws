<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.LocalTime"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pierwsze JSP</title>
</head>
<body>
<h1>Pierwsze JSP</h1>
<p>Zwykły HTML</p>

<%
	int lokalny = 100;
	int x = 2 + 2;
	LocalTime czas = LocalTime.now();
	System.out.println("To wypisało JSP " + czas);
	out.println("<p>Teraz jest godzina: " + czas + "</p>");
%>

<p>Znowu zwykły HTML</p>

<%-- Komentarz JSP - to nie zostanie wysłane do klienta. --%>
<!-- Komentarz HTML - to zostanie wysłane do klienta. -->

<%-- Zwykły scriptlet - dowolny fragment kodu w Javie: --%>
<%
x++;

if(sprawdzNapis("Ala")) {
	out.println("Ala jest OK");
} else {
	out.println("Ala nie jest OK");
}
%>

<%-- Wydrukowanie wartości wyrażenia: --%>
<p>Wynik mnożenia: <%= 5 * x %></p>
<p>Bieżący czas: <%= ZonedDateTime.now() %></p>

<%-- Deklaracje POZIOMU KLASY --%>
<%!
int instancyjny = 200; // zmienna instancyjna w klasie serwletu
static int statyczny = 300; // zmienna statyczna w tej klasie

private static boolean sprawdzNapis(String napis) {
	return napis != null && napis.length() >= 3;
}
%>

<p>Licznik lokalny: <%= ++lokalny %></p>
<p>Licznik instancyjny: <%= ++instancyjny %></p>
<p>Licznik statyczny: <%= ++statyczny %></p>

</body>
</html>