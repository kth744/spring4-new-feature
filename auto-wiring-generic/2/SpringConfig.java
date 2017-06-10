@Bean
 public GenericTest<Printable> getGenericTest() {
  return new GenericTest<Printable>();
 }

 @Bean
 public GenericTest<FilePrinter> getFileTest() {
  return new GenericTest<FilePrinter>();
 }
