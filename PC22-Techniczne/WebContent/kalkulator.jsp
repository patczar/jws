<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styl.css">
<title>Kalkulator JSP</title>
</head>
<body>
<h1>Kalkulator JSP</h1>

<form method="post">
<p>Podaj dwie liczby:</p>
<input type="number" name="x" value="${param.x}">
<select name="operacja">
<option value="+">+</option>
<option value="-">-</option>
<option value="*">*</option>
<option value="/">/</option>
</select>
<input type="number" name="y" value="${param.y}">
<button>Oblicz</button>
</form>

<jsp:useBean id="kalkulator" class="serwlety.beans.KalkulatorBean"/>
<jsp:setProperty name="kalkulator" property="x" param="x"/>
<jsp:setProperty name="kalkulator" property="y" param="y"/>
<jsp:setProperty name="kalkulator" property="operacja" param="operacja"/>

<div class="wynik">
${kalkulator.x} ${kalkulator.operacja} ${kalkulator.y} = <strong>${kalkulator.wynik}</strong>
</div>
</body>
</html>
