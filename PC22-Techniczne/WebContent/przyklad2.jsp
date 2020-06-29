<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drugie JSP</title>
</head>
<body>
<h1>Drugie JSP</h1>
<p>Zwykły HTML</p>

<p>Expression Language (EL): ${2+2*3}</p>
<p>Parametr x: ${param.x}</p>

<jsp:useBean id="ksiazka" class="serwlety.beans.Ksiazka"/>

<p>ksiazka: ${ksiazka}</p>
<p>szczegóły: ${ksiazka.tytul} ${ksiazka.autor}, cena ${ksiazka.cena} PLN</p>

<jsp:setProperty name="ksiazka" property="autor" value="Bruce Eckel"/>
<jsp:setProperty name="ksiazka" property="tytul" value="Thinking in Java"/>
<p>Po zmianie...</p>
<p>ksiazka: ${ksiazka}</p>
<p>szczegóły: ${ksiazka.tytul} ${ksiazka.autor}, cena ${ksiazka.cena} PLN</p>

<jsp:useBean id="info" class="serwlety.beans.InfoBean"/>
<p>Godzina: ${info.currentTime}</p>


</body>
</html>