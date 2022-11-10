순수하게 html할거면 jsp쓸 필요X
마우스우클릭을 Context_Menu
`*쿼리스트링 http://localhost:8080/nana.jsp <<?cnt=20>> *`
 jsp에서 서블릿이랑 비슷한 작동을 한다.
[Servlet / JSP #11 JSP Program](https://velog.io/@underlier12/Servlet-JSP-11-JSP-Program-j4k5tc89eo)

****Model 1 - 컨트롤러와 뷰가 물리적으로 분리되지 않은 방식
*Model 2 - 컨트롤러와 뷰가 물리적으로 분리된 방식
*JSP는 servlet이다~. *
==JSP==
=

- 표현 태그
  `<% %>` : service 메소드 내에 자바 코드를 입력할 때에 사용하는 블록 (out.write())
  `<%= %>` : service 메소드 내에 변수 자체를 출력할 때 사용하는 블록 (out.print())
  `<%! %>` : 메소드를 선언할 때 사용하는 블록
  `<%@ %>` : 초기 설정을 위한 page 지시자

 - 내장객체

	`*public void _JspService(...req, ...res){ 
	- pageContext - 그때 그때 상황에 맞는 데이터를 갖는다,(``==페이지)
		 유효범위는 이 page.(지역변수의 개념.)
	- session : javax.servlet.http.HttpSession
	     getAttribute(문자열attr로 설정된 세션값을 obj형태로 반환)
	     setAttribute(문자열 name으로 attr을 설정)
	- application : javax.servlet.ServletContext
		 범위가 전체 servlet
nana.jsp.   전체가 html(<!DOCTYPE html>~)


MVC model1 (비교적 소규모.)
=
스파게티 코드를 지양하고 코드 블럭의 분산으로 인해 가독성이 떨어지지 않도록 하기 위해 MVC 모델 1을 구축하게 되었다. 가독성이 매우 떨어지는 코드 블럭을 
 아주 깔끔하게 정리되어 보기 편하게 함.
 -모델의 최소 단위는 변수. 
 출력 데이터 : Model.[data]
 입력과 제어를 담당 : Controller[자바코드]
 출력 담당 : View[HTML 코드]. 보통 템플릿이라고 말한다.
  - jsp가 View를 처리한다.
 
MVC model2
=
Model 2에서는 Controller와 View가 분리되며 Dispatcher를 통해 Forwarding을 해주게 되는데 파일들이 많아지면 공통 Dispatcher 기능을 분리하여 집중화하게 된다.
 (은행에서 번호표를 통해 창구를 나눠서 대기하는게 Dispatcher.)
//else 안쓰는 방법.
	String result="짝수";
	if(num % 2 != 0){
		result = "홀수";
	}


시간, 공간 생각하자!  
 - request는 req 요청할떄마다, session은 로그인~로그아웃까지.
 - 고로, req가 훨씬 짧다.

데이터를 주고받을 떄,
 req,res 방법으로 주고받는게 가장 간단한 방법
   (여기서데이터가 필요하면 session 추가해주고)










```1
```
