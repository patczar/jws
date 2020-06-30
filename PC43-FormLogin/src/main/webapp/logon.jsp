<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zaloguj się</title>
</head>
<body>
<h1>Zaloguj się</h1>

<form method="POST" action="j_security_check">
<table>
<tr>
	<td><label for="j_username">Użytkownik:</label></td>
	<td><input type="text" name="j_username"></td>
</tr><tr>
	<td><label for="j_password">Hasło:</label></td>
	<td><input type="password" name="j_password"></td>
</tr>
<tr>
	<td><button>Zaloguj</button></td>
</tr>
</table>
</form>

</body>
</html>
