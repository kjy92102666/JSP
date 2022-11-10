<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int a=10;
	int b=20;
	int result;

	result = a+b;
	//위의 작업은 다 서블렛에서 하게 될거다.
	//html에서 필요한 정보만 찍는게 jsp다~
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>a: <%=a %>
<p>b: <%=b %>
<p>결과: <%= result %>

<h1>계산기</h1>

<form action="adder" >	<!-- method=get 은 생략가능. 기본값으로 들어가는듯 -->
	<ul>
		<li>
			<label for="x">X : </label>
			<input id="x" name="num1" type="text"/>	
			<!-- id를 추가함으로써 x텍스트를 눌러도 텍스트커서잡힘 -->
		</li>
		<li>
			<label for="y">Y : </label>
			<input id="y" name="num2" type="text"/>
		</li>
		<p>
			<input type="submit" value="Sum">
		</p>
	</ul>
</form>
</body>
</html>