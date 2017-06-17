
# 참조링크
 - 첫번째 : http://dev.anyframejava.org/docs/anyframe/plugin/essential/core/1.5.2-SNAPSHOT/reference/html/ch03.html
 - 두번째 : https://www.intertech.com/Blog/spring-4-conditional-bean-configuration/

# @Conditional 지원

조건에 따라 빈을 설정하거나 하지 않을 수 있다.

출처 : 서적 Spring in Action (스프링 인 액션)



다음과 같이 @Conditional 어노테이션을 사용할 수 있다.

```java
@Bean
@Conditional(MagicExistsCondition.class)
public MagicBean magicBean() {
  return new MagicBean();
}
```



@Conditional을 조건을 지정하기 위한 클래스이고 **Condition 인터페이스**와 같이 사용된다.

Condition 인터페이스는 다음과 같이 정의되어 있다.

```java
public interface Condition {
  boolean matches(ConditionContext ctxt, AnnotatedTypeMatadata metadata);
}
```



@Conditional 인터페이스를 상속받은 MagicExistsCondition 클래스를 구현한다.

```java
package com.demo;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotationTypeMetadata;
import org.springframework.util.ClassUtils;

public class MagicExistsCondition implements Condition {
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Environment env = context.getEnvironment();
    return env.containsProperty("magic"); // magic 프로퍼티 체크
  }
}
```



matches 메소드에 필요한 파라미터인 ConditionContext와 AnnotatedTypeMetadata 인터페이스는 다음과 같이 정의되어 있다.

```java
public interface ConditionContext {
  BeanDefinitionRegistry getRegistry();
}
```









