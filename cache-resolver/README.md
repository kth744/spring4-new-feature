# spring cache
spreing 4.1버전 부터는 cacheResolver를 설정할 경우 기존에 설정되었던 cacheManager가 무시된다.

> Since Spring 4.1, the value attribute of the cache annotations are no longer mandatory since this particular information can be provided by the CacheResolver regardless of the content of the annotation.
Similarly to key and keyGenerator, the cacheManager and cacheResolver parameters are mutually exclusive and an operation specifying both will result in an exception as a custom CacheManager will be ignored by the CacheResolver implementation. This is probably not what you expect.

- https://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html

### CacheResolver.java
- https://insight.io/github.com/spring-projects/spring-framework/blob/33f99910dd08f9f596987ade96d0398ae9d7f7ba/spring-context/src/main/java/org/springframework/cache/interceptor/CacheResolver.java

### spring cacheResolver
CacheManager 는 getCache와 getCacheNames로 캐시 객체, 이름 Collection을 얻습니다.
- http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/cache/CacheManager.html

CacheResolver 는 resolveCaches 는 캐쉬 collections을 얻습니다.
- http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/interceptor/CacheResolver.html

하위 추상 클래스인 AbstractCacheResolver는 CacheManager를 Has a관계로 가질 수 있도록 setCacheManager, getCacheManager 프로퍼티 메서드를 제공합니다.
- http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/interceptor/AbstractCacheResolver.html

구현 클래스인 SimpleCacheResolver는 캐시 인스턴스를 얻을 때 설정된 CacheManager를 참고하고
- http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/interceptor/SimpleCacheResolver.html

그 밖에 하위 클래스론 NamedCacheResolver, SimpleExceptionCacheResolver가 있습니다.
- http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/interceptor/NamedCacheResolver.html
- http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/jcache/interceptor/SimpleExceptionCacheResolver.html

CacheResolver는 Functional Interface이다.

### spring cache의 기초
애너테이션 | 내용
-------- | ----------
``@Cacheable`` | 스프링이 메소드 사용 전에 메서드 반환 값을 위해 캐시를 살펴본다. 값이 존재할 경우 캐시된 값이 반환된다. 값이 존재하지 않는다면 메서드가 실행되고 반환된 값이 캐시에 남는다.
``@CachePut`` | 스프링이 캐시에 메서드 반환 값을 저장한다. 캐시는 메서드 실행전에는 체크되지 않으며 메서드는 항상 실행된다.
``@CacheEvict`` | 스프링이 캐시에서 한 개 이상의 엔트리를 내쫓는다.
``@Caching`` | 다른 캐싱 애너테이션을 여러번 즉시 적용하기 위해 사용할 수 있는 그룹 애너테이션이다.

### spring context의 cacheResolve 관련 샘플코드
- https://github.com/spring-projects/spring-framework/blob/master/spring-context/src/test/java/org/springframework/cache/interceptor/CacheResolverCustomizationTests.java

```java
@CacheConfig(cacheNames = "default")
static class SimpleService {
    private final AtomicLong counter = new AtomicLong();

    @Cacheable
    public Object getSimple(Object key) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheResolver = "primaryCacheResolver")
    public Object getWithCustomCacheResolver(Object key) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheManager = "anotherCacheManager")
    public Object getWithCustomCacheManager(Object key) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheResolver = "runtimeCacheResolver", key = "#p0")
    public Object getWithRuntimeCacheResolution(Object key, String cacheName) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheResolver = "namedCacheResolver")
    public Object getWithNamedCacheResolution(Object key) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheResolver = "nullCacheResolver") // No cache resolved for the operation
    public Object noCacheResolved(Object key) {
        return this.counter.getAndIncrement();
    }

    @Cacheable(cacheResolver = "unknownCacheResolver") // No such bean defined
    public Object unknownCacheResolver(Object key) {
        return this.counter.getAndIncrement();
    }
}
```


### JSR-107과 spring 4.1
캐쉬처리를 4.1이전에는 CacheManager란 놈의 구현체들이 했는데 4.1에는 CacheResolver로 변경된거 같다.
(아예 바뀐건지 새로운걸로 대체되었는지는 확인필요)
CacheManager는 runtime시점에 cache를 변경할 수 없었는데 CacheResolver는 runtime에 실제 메소드가 수행되는 시점에 Cache처리를 할수 있다.
- 출처 : http://becko.tistory.com/87 [becko]
- JSR-107 : https://jcp.org/en/jsr/detail?id=107

### spring Ehcache
- http://dev.dwuthk.com/entry/Spring-Ehcache-적용-정리

### spring @CacheConfig
- http://www.concretepage.com/spring-4/spring-4-cacheconfig-annotation-example

### spring @Cacheable
- https://www.youtube.com/watch?v=ugkK494jen0&feature=youtu.be