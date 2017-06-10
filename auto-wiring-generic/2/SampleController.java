 @Autowired
 private GenericTest<Printable> genericTest;

 @Autowired
 private GenericTest<FilePrinter> fileTest;
 
 
 // TODO 제네릭 최초 빈 생성시 생성자로 넣어도 됨 혹은 setter
 @Autowired
 private ConsolePrinter console;
 @Autowired
 private FilePrinter file;
