### 애너테이션 기반 종단점(endpoint)을 지원하는 @Jms 애노테이션

## 참조 ##
- Spring in Action (17장 Spring Messaging, p549)
- https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/jms.html

Jms (java message service)
- Async Message 처리
- ActiveMQ 오픈 소스를 활용(e.g. like Tomcat, Hadoop ...)
- Spring Config로 ActiveMQ와 연동(Connector, Url ...)

- ActiveMQ는 큐와 토픽이 존재
  - Queue : 1:1로 메세지 전송
  - Topic : 1:N으로 메세지 전송
  
- ActiveMQ를 start한다

- Spring에선 JmsTemplate을 이용해 send, receive가 가능함
- 전달하기 전에 Convert를 이용해 메세지 가공도 가능

Spring의 Jms연동을 잘 알아야 endpoint의 @Jms를 알 수 있을듯 (Jms 규모가 크므로 우선 이정도만 알고 PASS)
