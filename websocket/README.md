### 웹소켓이란?
웹소켓(WebSochet)은 서버와 클라이언트 간의 효율적인 양방향 통신을 실현하기 위한 구조입니다.

최근에는 Gmail 처럼 데이터의 실시간 특성을 중시한 웹 애플리케이션이 많이 등장하여 많은 주목을 받고 있습니다.
자바스크립트의 처리 성능이 크게 개선된 현재, 웹 애플리케이션의 성능면에서 병목 현상이 나타나는 것은 네트워크 통신 부분으로 웹소켓은 실시간 웹을 구현하기 위한 핵심 기술로 기대받고 있습니다.

웹소켓은 매우 단순한 API로 구성되어 있습니다. 
웹소켓을 이용하면 하나의 HTTP 접속으로 양방향 메시지를 자유롭게 주고받을 수 있습니다.
XMLHttpRequest와 Server-Sent Event를 조합해서 양방향 통신을 구현하는 경우와 비교해 통신 효율이 좋고, 설계나 구현도 간단해지는 장점이 있습니다.

- 출처: http://webclub.tistory.com/463 [Web Club]

### Socket.io는 무엇인가?
WebSocket은 다가올 미래의 기술이지 아직 인터넷 기업에서 시범적으로라도 써 볼 수 있는 기술이 아니다. WebSocket이 미래의 기술이라면 Socket.io는 현재 바로 사용할 수 있는 기술이다. Socket.io는 JavaScript를 이용하여 브라우저 종류에 상관없이 실시간 웹을 구현할 수 있도록 한 기술이다.

Guillermo Rauch가 만든 Socket.io는 WebSocket, FlashSocket, AJAX Long Polling, AJAX Multi part Streaming, IFrame, JSONP Polling을 하나의 API로 추상화한 것이다. 즉 브라우저와 웹 서버의 종류와 버전을 파악하여 가장 적합한 기술을 선택하여 사용하는 방식이다. 가령 브라우저에 Flash Plugin v10.0.0 이상(FlashSocket 지원 버전)이 설치되어 있으면 FlashSocket을 사용하고, Flash Plugin이 없으면 AJAX Long Polling 방식을 사용한다.

개발자가 각 기술을 깊이 이해하지 못하거나 구현 방법을 잘 알지 못해도 사용할 수 있다. Web Socket과 달리 Socket.io는 표준 기술이 아니고 Node.js 모듈로서 Guillermo Rauch가 CTO로 있는 LearnBoost(https://www.learnboost.com) 라는 회사의 저작물이며 MIT 라이센스를 가진 오픈소스이다. 현재 Node.js가 아닌 다른 프레임워크에서 Socket.io를 사용할 수 있도록 하는 시도가 있다.

- 출처 : http://d2.naver.com/helloworld/1336

### spring boot websocket 샘플코드
- https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-tomcat

### 따라하기
- http://blog.naver.com/PostView.nhn?blogId=beabeak&logNo=220471878778&parentCategoryNo=&categoryNo=86&viewDate=&isShowPopularPosts=true&from=search