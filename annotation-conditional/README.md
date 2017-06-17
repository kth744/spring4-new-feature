
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
    return env.containsProperty("magic"); // magic 프로퍼티가 있는지 체크
  }
}
```



matches 메소드에 필요한 파라미터인 ConditionContext와 AnnotatedTypeMetadata 인터페이스는 다음과 같이 정의되어 있다.

-  ConditionContext

   ```java
   public interface ConditionContext {
     BeanDefinitionRegistry getRegistry();
     ConfigurableListableBeanFactory getBeanFactory();
     Emvironment getEnvironment();
     ResourceLoader getResourceLoader();
     ClassLoader getClassLoader();
   }
   ```

   -  getRegistry() 반환을 통해 BeanDefinitionRegistry로 빈 정의를 확인한다.
   -  빈의 존재를 확인하고, getBeanFactory()에서 반환되는 ConfigurableListableBeanFactory를 통해 빈 프로퍼티를 발굴한다.
   -  getEnvironment()로부터 얻은 Environment를 통해 환경 변수 값을 확인한다.
   -  getResourceLoader()에서 반환된 ResourceLoader를 통해 로드된 자원 내용을 읽고 검사한다.
   -  getClassLoader()에서 반환된 ClassLoader를 통해 클래스의 존재를 로드하고 확인한다.





-  AnnotatedTypeMetadata

   ```java
   public interface AnnotatedTypeMetadata {
     boolean isAnnotated(String annotationType);
     Map<String, Object> getAnnotationAttributes(String annotationType);
     Map<String, Object> getAnnotationAttributes(String annotationType, boolean classValuesAsString);
     MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationType);
     MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationType, boolean classValuesAsString);
   }
   ```

   -  isAnnotated() 메소드를 사용하여, @Bean 메소드가 특정 어노테이션 타입을 사용해 애너테이션을 붙일 수 있는지를 검사한다.





스프링4에서 @Profile 애너테이션이 @Conditional을 기반으로 리팩토링 되었다.

@Conditional의 예제로 @Profile을 살펴본다.

-  Profile

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(ProfileCondition.class)
public @interface Profile {
  String[] value();
}
```



-  ProfileCondition

```java
class ProfileCondition implements Condition {
  public boolean matches (ConditionContext context, AnnotatedTypeMetadata metadata) {
    if(context.getEnvironment() != null) {
      MultiValueMap<String, Object> attrs = metadata.getAllAnnotataionAttributes(Profile.class.getName());
      if(attrs != null) {
        for (Object value : attrs.get("value")) {
          if (context.getEnvironment().acceptsProfiles(((String[]) value))) {
            return true;
          }
        }
        return false;
      }
    }
    return false;
  }
}
```



ProfileCondition은 AnnotatedTypeMetadata에서 @Profile의 모든 애너테이션 애트리뷰트를 가져온다.

빈 프로파일의 이름이 포함되어 있는 value 애트리뷰트를 명시적으로 확인한다. 

프로파일이(acceptsProfile() 메소드를 호출하여) 활성 상태인지 여부를 확인하기 위해 ConditionContext에서 가져온 Environment를 살펴본다.





# 두개의 조건에 부합될 경우
두개의 조건에 부합하게 될 경우 다음과 같이 첫번째로 대상이 되는 컴포넌트를 주입하게 된다.

```java
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
