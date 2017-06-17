### 참조링크
- https://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java-bean-description

### Bean description

Sometimes it is helpful to provide a more detailed textual description of a bean. This can be particularly useful when beans are exposed (perhaps via JMX) for monitoring purposes.
To add a description to a @Bean the @Description annotation can be used:

```
@Configuration
public class AppConfig {

    @Bean
    @Description("Provides a basic example of a bean")
    public Foo foo() {
        return new Foo();
    }

}
```
