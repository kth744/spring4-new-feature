### 공식 레퍼런스
- https://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html#testcontext-executing-sql-declaratively

스프링 4.1 버전에 새롭게 추가된 테스트 기능 중에서 @Sql 애노테이션이 있는데, @Sql 애노테이션은 지정한 스크립트를 실행해주는 애노테이션이다.  스프링 3 버전에 추가된 ResourceDatabasePopulator를 사용해도 되지만, @Sql 애노테이션을 이용하면 매우 편리하게 테스트에서 데이터를 초기화할 수 있다.
 - 출처: 자바캔(Java Can Do IT)
 - http://javacan.tistory.com/entry/spring41-AtSql-annotation-intro
 
### 사용법
아래 코드는 각 테스트 메서드를 실행하기 전에 member.init1.sql 파일과 memberinit2.sql 파일에 입력한 쿼리 목록을 실행한다.
```java
@ContextConfiguration("classpath:spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Sql({"classpath:member.init1.sql", "classpath:member.init2.sql"})
public class MemberIntTest {
    @Autowired private MemberService memberService;

    … 중략 …
```

regist() 테스트 메서드를 실행할 때에는 "member.init3.sql" 만을 사용해서 쿼리를 실행
```java
@Sql("classpath:member.init3.sql")
    @Test
    public void regist() {
        … 중략 …
    }
```

@Sql은 테스트 메서드 실행 전과 실행 후 중에서 언제 쿼리를 실행할지 여부를 지정할 수 있다. @Sql의 executionPhase 속성의 값으로 ExecutionPhase 열거 타입에 정의된 BEFORE_TEST_METHOD나 AFTER_TEST_METHOD를 설정하면 된다. 기본 값은 BEFORE_TEST_METHOD 이다. 다음 코드는 executionPhase 속성의 설정 예를 보여주고 있다.

자바8을 사용하면 @Sql 애노테이션을 여러 개 사용해서 실행할 쿼리를 지정할 수 있다.

```java
@Sql("init.sql")
@Sql(scripts="remove.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
@Test public void someTest() { … }
```

자바 7 이하 버전을 사용한다면, 아래 코드처럼 @SqlGroup 애노테이션을 이용하면 여러 개의 @Sql을 한 테스트 클래스나 메서드에 적용할 수 있다.

```java
@SqlGroup( {
    @Sql("init.sql"), @Sql(scripts="clear.sql", executionPhase=ExecutionPhase.AFTER_TEST_METHOD)} )
@Test public void someTest() { … }
```

@Sql 애노테이션은 별도 설정을 하지 않으면 @ContextConfiguration에 지정한 설정 정보에 있는 DataSource 빈을 사용해서 스크립트를 실행하고, 트랜잭션 관리자가 존재할 경우 해당 트랜잭션 관리자를 이용해서 트랜잭션 범위 내에서 스크립트를 실행한다.

### 그럼 우리는 이것을 어떻게 써야 하는가? 써야만 할까?
- 마이그레이션을 테스트 할때 쓸만하겠다.
- 클래스 별로 데이터를 조작한뒤 생성된 dirty data를 날리는 데에는 도움이 될 것 같다.
- 그런데 굳이 사용해야만 하는 이유는 찾지 못하겠다.