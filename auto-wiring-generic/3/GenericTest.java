@Component("genericStringTest")
public class GenericTest<T> {
 public void print(T t) {
  System.out.println(t.toString());
 }
}
