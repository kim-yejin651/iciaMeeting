<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>
input[type=button], input[type=submit], input[type=reset] {
	background-color: black;
	border: none;
	color: white;
	padding: 16px 32px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>
<body>

<div id="header">
	<jsp:include page = "/header.jsp"/>
</div>

	<center>
		<form method="post">
		<br> <br> <br> <br> <br> <br>
			<table>
				<tr>
					<td rowspan="4"><img width= '300' height='300' src="upload/${p.p_oriFileName}"></td>
					<td><h3>${p.p_name}</h3></td>
				</tr>
				<tr>
					<td>${p.p_price}</td>
				</tr>
				<tr>
					<td>${p.p_contents}</td>
				</tr>
				<tr><td>수량 : <input type="number" min="1" value="1" name="p_qty"></td></tr>
				<tr><td><input hidden="hidden" type="text" value="${p.p_code}" name="p_code"></td></tr>
				<tr><td><input hidden="hidden" type="text" value="${p.p_price}" name="p_price"></td></tr>
				<tr><td><input hidden="hidden" type="text" value="${p.p_name}" name="p_name"></td></tr>
				<tr><td colspan="2" align="center">
					<input type="submit" value="장바구니" onclick="javascript: form.action='/kjb/basketFrm';"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="구매" onclick="javascript: form.action='/kjb/buy';"/>
					</td>
				</tr>
			</table>
			<br> <br> <br> <br> <br> <br><br> <br>
		</form>
	</center>
	<div id="footer">
		<jsp:include page="../footer.jsp" />
	</div>
	<c:if test="${!empty id}">
		<br> ${p.p_id}
	</c:if>

</body>
</html>