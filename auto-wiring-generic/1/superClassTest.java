Super<String> sup = new Super<String>();
 Sub sub = new Sub();

 @Test
 public void test() {
  
  System.out.println(ResolvableType.forClass(sup.getClass()).getGeneric(0));
  
  
  
  System.out.println(ResolvableType.forClass(sub.getClass()).getSuperType().getGeneric(0));
  
  
  
 }

 public static class Super<T> {
  String test(T value){
   return String.valueOf(value);
  }
 }
 
 public static class Sub extends Super<String> {
  
 }
