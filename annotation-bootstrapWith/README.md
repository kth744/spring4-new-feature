### bootstrap이란?
부트스트랩이란 「그 자체의 동작에 의해서 어떤 소정의 상태로 이행하도록 설정되어 있는 방법, 예를 들면, 최초의 수개의 명령에 의해서 그것에 계속해서 모든 명령을 입력장치에서 계산기내에 패치(fetch) 할 수 있도록 하는 방법」이라 정의되고 있다. 즉, 프로그램을 입력하는 방법의 하나이며, 최초에 명령을 읽어 들이기 위한 간단한 조작을 하면 계속해서 명령을 패치(fetch) 하는 것을 순차로 하여 최종적으로는 완전한 프로그램이 기억장치내에 수용(收用)되도록 하는 방법을 말한다.
- 출처 : [네이버 지식백과] 부트 스트랩 [Bootstrap] (정보통신용어사전, 2008. 1. 15., 일진사)

### bootstrapWith
테스트에서 context가 올라가는 것 또한 custumizing 할 수 있도록 해준다.

```html
Resolve the {@link TestContextBootstrapper} type for the test class in the supplied {@link BootstrapContext}, instantiate it, and provide it a reference to the {@link BootstrapContext}.
<p>If the {@link BootstrapWith @BootstrapWith} annotation is present on the test class, either directly or as a meta-annotation, then its {@link BootstrapWith#value value} will be used as the bootstrapper type.

Otherwise, the {@link org.springframework.test.context.support.
DefaultTestContextBootstrapper DefaultTestContextBootstrapper} will be used.
```

- 출처 : https://github.com/alipayhuber/hack-spring/blob/f1dada2c2aeb8d8b61964e9ad6dfb39d786e2452/spring-test/src/main/java/org/springframework/test/context/BootstrapUtils.java
