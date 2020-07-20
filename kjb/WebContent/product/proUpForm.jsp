<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="header">
	<jsp:include page = "/header.jsp"/>
</div>
            <p><a href="main"><font style="color: black;">ICIA</font> <font style="color: gray;">STORE</font></a></p><br>
            <br><br><h3 style="font-weight: bold;">회원가입</h3>
	<form action="proUpload" method ="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td colspan="2" align="center"><h3>상품업로드</h3></td>
			</tr>
			<tr>
				<td><input type="radio" name="p_kind" value="w" checked>화이트
					<input type="radio" name="p_kind" value="b">블랙</td>
			</tr>
			<tr>
				<td>제품이름</td>
				<td><input type="text" name="p_name"></td>
			</tr>
			<tr>
				<td>제품가격</td>
				<td><input type="text" name="p_price"></td>
			</tr>
			<tr>
				<td>제품제고</td>
				<td><input type="text" name="p_qty"></td>
			</tr>
			<tr>
				<td>제품설명</td>
				<td><input type="text" name="p_contents"></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" name="p_file"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button>상품등록</button> <input
					type="reset" value="등록취소"></td>
			</tr>
			<tr>
		</table>
	</form>
</body>
</html>