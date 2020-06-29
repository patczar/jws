<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zakresy</title>
</head>
<body>
<h1>Zakresy zmiennych</h1>
<p>Wersja bez filtru.</p>

<jsp:useBean id="domyslnie" class="serwlety.beans.InfoBean"/>
<jsp:useBean id="licznik_page" class="serwlety.beans.InfoBean" scope="page"/>
<jsp:useBean id="licznik_req" class="serwlety.beans.InfoBean" scope="request"/>
<jsp:useBean id="licznik_ses" class="serwlety.beans.InfoBean" scope="session"/>
<jsp:useBean id="licznik_app" class="serwlety.beans.InfoBean" scope="application"/>

<h2>Domyślnie</h2>
<p>${domyslnie.licznik}</p>
<p>${domyslnie.licznik}</p>

<h2>Page</h2>
<p>${licznik_page.licznik}</p>
<p>${licznik_page.licznik}</p>

<h2>Request</h2>
<p>${licznik_req.licznik}</p>
<p>${licznik_req.licznik}</p>

<h2>Session</h2>
<p>${licznik_ses.licznik}</p>
<p>${licznik_ses.licznik}</p>

<h2>Application</h2>
<p>${licznik_app.licznik}</p>
<p>${licznik_app.licznik}</p>

</body>
</html>