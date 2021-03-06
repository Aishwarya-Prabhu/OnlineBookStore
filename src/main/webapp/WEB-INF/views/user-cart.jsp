<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body style="background-color:lightblue;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/"><input type="submit" value="Logout"></a>
	<br />
	<br>
	<br>

	<form:form action="${contextPath}/cart/checkout" method="post"
		commandName="cart">

		<%-- 	Book Name: ${c.title} --%>
		<!-- 	Category -->
		<table border="1" cellpadding="5" cellspacing="5">
			<tr>
				<td><b>Book Name</b></td>
				<td><b>Book Category</b></td>
				<td><b>Image</b></td>
				<td><b>Price</b></td>

			</tr>

         <c:forEach var="advert" items="${adverts}">
			<tr>
				<td width="100">${advert.title}</td>
				<td width="100">
					<c:forEach var="categ" items="${advert.categories}">
	                   	${categ} , 
	                </c:forEach>
	            </td>
	            <td width="100"><img height="75" width="75" src="${advert.filename}" /></td>
	            <td width="100">${advert.price}</td>
			</tr>
		</c:forEach>
	
	</table>
	<input type="submit" value="Checkout Items" />
	</form:form>
</body>
</html>