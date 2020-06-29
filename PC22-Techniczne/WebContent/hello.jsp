<%@ page import="java.time.LocalTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP</title>
</head>
<body>
<h1>Hello JSP</h1>

<p>Ala ma kota.</p>

<%
System.out.println("ping");
out.println("<p>a kuku</p>");
%>

<p>Teraz jest godzina: <%= LocalTime.now() %></p>

</body>
</html>
