<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	//코드
	int num =0;
	String num_ = request.getParameter("n");
	if((num_ != null && !num_.equals(""))){
		num = Integer.parseInt(num_);
	}
	//else 안쓰는 방법.
	String result="짝수";
	
	if(num % 2 != 0){
		result = "홀수";
	}
%>
<!DOCTYPE html><!-- HTML -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		숫자 <%=num_ %>은 : <input type="text" value="<%=result %>" /> 
	</form>
</body>
</html>