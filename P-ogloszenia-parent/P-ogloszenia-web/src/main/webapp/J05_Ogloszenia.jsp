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
<title>Ogłoszenia - wersja JSP 05</title>
</head>
<body>
<h1>Wszystkie ogłoszenia</h1>
<p>Wersja zrobiona w oparciu o beany</p>

<jsp:useBean id="ogloszeniaBean" class="ogloszenia.beans.OgloszeniaBean"/>

<c:forEach var="ogl" items="${ogloszeniaBean.wszystkieOgloszenia}">
	<div class="ogloszenie">
		<h2>${ogl.tytul}</h2>
		<p>${ogl.marka} ${ogl.model} ${ogl.generacja}</p>
		<p>${ogl.cena} PLN</p>
		<img class="foto" src="foto?id=${ogl.idOgloszenia}">
		<p>Lokalizacja ${ogl.lokalizacja}</p>
		<p>Wystawione ${ogl.wystawione}</p>
		<p>${ogl.opis}</p>
		<h3>Sprzedawca</h3>
		<p>${ogl.sprzedawca.nazwa}</p>
		<p>tel. ${ogl.sprzedawca.telefon}</p>
	</div>
</c:forEach>

</body>
</html>
