<%@page import="ogloszenia.exn.BladBazyDanych"%>
<%@page import="ogloszenia.model.Samochodowe"%>
<%@page import="java.util.List"%>
<%@page import="ogloszenia.baza.OgloszeniaDAO"%>
<%@page import="ogloszenia.baza.DostepDoBazy"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='styl.css'>
<title>Ogłoszenia - wersja JSP 08</title>
</head>
<body>
<h1>Wybrane ogłoszenia</h1>
<p>Wersja zrobiona w oparciu o forward z sesją</p>

<div class="schowek">
<h4>Zapamiętane ogłoszenia</h4>
<ul>
<c:forEach var="ogl" items="${schowek}">
	<li>${ogl.marka} ${ogl.model} za <b>${ogl.cena}</b></li>
</c:forEach>
</ul>
</div>

<form method="post">
<table>
<tr><td>Cena minimalna:</td><td><input type="number" name="min" value="${param.min}"></td></tr>
<tr><td>Cena maksymalna:</td><td><input type="number" name="max" value="${param.max}"></td></tr>
<tr><td><button>Wyślij</button></td></tr>
</table>
</form>

<c:forEach var="ogl" items="${ogloszenia}">
	<div class="ogloszenie">
		<h2>${ogl.tytul}</h2>
		<p>${ogl.marka} ${ogl.model} ${ogl.generacja}</p>
		<p>${ogl.cena} PLN</p>
		<p>Przebieg: ${ogl.przebieg} km</p>
		<img class="foto" src="foto?id=${ogl.idOgloszenia}">
		<p>Lokalizacja ${ogl.lokalizacja}</p>
		<p>Wystawione ${ogl.wystawione}</p>
		<p>${ogl.opis}</p>
		<h3>Sprzedawca</h3>
		<p>${ogl.sprzedawca.nazwa}</p>
		<p>tel. ${ogl.sprzedawca.telefon}</p>
		<p><a href="DodajDoSchowka?id=${ogl.idOgloszenia}">zapamiętaj</a></p>
	</div>
</c:forEach>

</body>
</html>
