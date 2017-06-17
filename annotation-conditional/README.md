
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

## 두개의 조건에 부합될 경우
두개의 조건에 부합하게 될 경우 다음과 같이 첫번째로 대상이 되는 컴포넌트를 주입하게 된다.

```
/**
	 * Determine if an item should be skipped based on {@code @Conditional} annotations.
	 * @param metadata the meta data
	 * @param phase the phase of the call
	 * @return if the item should be skipped
	 */
	public boolean shouldSkip(AnnotatedTypeMetadata metadata, ConfigurationPhase phase) {
		if (metadata == null || !metadata.isAnnotated(Conditional.class.getName())) {
			return false;
		}

		if (phase == null) {
			if (metadata instanceof AnnotationMetadata &&
					ConfigurationClassUtils.isConfigurationCandidate((AnnotationMetadata) metadata)) {
				return shouldSkip(metadata, ConfigurationPhase.PARSE_CONFIGURATION);
			}
			return shouldSkip(metadata, ConfigurationPhase.REGISTER_BEAN);
		}

		for (String[] conditionClasses : getConditionClasses(metadata)) {
			for (String conditionClass : conditionClasses) {
				Condition condition = getCondition(conditionClass, this.context.getClassLoader());
				ConfigurationPhase requiredPhase = null;
				if (condition instanceof ConfigurationCondition) {
					requiredPhase = ((ConfigurationCondition) condition).getConfigurationPhase();
				}
				if (requiredPhase == null || requiredPhase == phase) {
					if (!condition.matches(this.context, metadata)) {
						return true;
					}
				}
			}
		}

		return false;
	}
```
