<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedQueen corporation </title>
</head>
<body>
<h1>GAE + Struts 2 Example</h1>

<s:form action="Welcome">
	<s:textfield name="username" label="Username"/>
	<s:password name="password" label="Password"/>
	<s:submit/>
</s:form>

</body>
</html>