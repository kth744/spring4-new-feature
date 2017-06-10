Groovy DSL을 통한 외부 빈 설정을 지원한다.

Spring 4 sandbox Groovy
 - https://github.com/hantsy/spring4-sandbox/blob/master/groovy-dsl/src/main/resources/com/hantsylabs/example/spring/config/JpaConfigGroovy.groovy

Using Groovy Based Spring Configuration
 - https://objectpartners.com/2016/01/12/using-groovy-based-spring-configuration/

Spring 4: Groovy DSL bean definition
 - http://hantsy.blogspot.kr/2013/12/spring-4-groovy-dsl-bean-definition.html

GroovyWebApplicationContext
 - http://javadox.com/org.springframework/spring-web/4.1.1.RELEASE/org/springframework/web/context/support/GroovyWebApplicationContext.java.html


사용법
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <context-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.GroovyWebApplicationContext</param-value>
    </context-param>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>WEB-INF/applicationContext.groovy</param-value>
    </context-param>
