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
<title>Ogłoszenia - wersja JSP 06</title>
</head>
<body>
<h1>Wszystkie ogłoszenia</h1>
<p>Wersja zrobiona w oparciu o beany</p>

<form method="post">
<table>
<tr><td>Cena minimalna:</td><td><input type="number" name="min" value="${param.min}"></td></tr>
<tr><td>Cena maksymalna:</td><td><input type="number" name="max" value="${param.max}"></td></tr>
<tr><td><button>Wyślij</button></td></tr>
</table>
</form>

<jsp:useBean id="ogloszeniaBean" class="ogloszenia.beans.OgloszeniaBean"/>

<%-- Ustawienie w obiekcie ogloszeniaBean własności cenaMinimalna
	na wartość odczytaną z parametru min.
	Technicznie wywołany zostanie setter:  setCenaMinimalna(...)
--%>
<jsp:setProperty name="ogloszeniaBean" property="cenaMinimalna" param="min"/>
<jsp:setProperty name="ogloszeniaBean" property="cenaMaksymalna" param="max"/>


<c:forEach var="ogl" items="${ogloszeniaBean.ogloszeniaWgCeny}">
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
