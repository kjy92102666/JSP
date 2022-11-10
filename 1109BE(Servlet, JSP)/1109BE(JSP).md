==이클립스 단축키==
 Ctrl+Shift+F:자동 줄맞춤,  Ctrl+D : 줄 삭제

==자바 작업 전 확인사항==
 프로젝트(properties-project facets-자바버전확인(1.8)-jre경로 확인)

*- super는 상속시에 사용가능. super.은 부모객체에서 쓸 수 있는 메소드,필드들 호출...

... method=\"post\">로 작업을 요청할 시, 서블렛에서 do post로 받는게 당연한 로직.
## GET / POST 특화된 서비스 함수
 calcpage는 get을 요청하는 페이지 일뿐, 
  ![[Pasted image 20221109145056.png]]
 *- 처음 한번만 작동(init()가 생성될 때 하는 작업.)

## 오류페이지 코드 
[JSP 에러페이지, HTTP 에러코드 정리 :: Zer0's_stuLab (tistory.com)](https://zer0lab.tistory.com/17)
 404 Error : URL 없음 
 405 Error : URL은 있으나 이를 처리할 메소드 없음
 




*- post는 form에서 설정하지 않는한 작동x


서비스를 거쳐서 doget, dopost로 갈라지는 로직.



====================이때까지 수동으로 jsp 작동=.===================

==JSP==
`<% %>` : service 메소드 내에 자바 코드를 입력할 때에 사용하는 블록 (out.write())

`<%= %>` : service 메소드 내에 변수 자체를 출력할 때 사용하는 블록 (out.print())

`<%! %>` : 메소드를 선언할 때 사용하는 블록

`<%@ %>` : 초기 설정을 위한 page 지시자