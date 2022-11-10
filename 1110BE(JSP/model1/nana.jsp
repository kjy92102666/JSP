<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cnt_ = request.getParameter("cnt");
	
	int cnt=10;
	if( cnt_ != null && !cnt_.equals("") ){
		cnt = Integer.parseInt(cnt_);
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{
		border-botton : 1px solid #ccc;
	}
</style>
</head>
<body>
<div>
<%
      for (int i = 0; i < cnt; i++) {%>
         <div>안녕 Servlet!</div>
      <% } %>

</div>
<form action="nana.jsp" method="POST">	<!-- EL, jstl 배우면 간단하게 처리가능 -->
	반복할 횟수 : <input type="text" name="cnt" >
	<input type="submit" value="전송">
	
</form>

</body>
</html>