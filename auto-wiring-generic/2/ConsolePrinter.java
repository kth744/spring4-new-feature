@Component
public class ConsolePrinter implements Printable {

 @Override
 public void print() {
  System.out.println("console print");
 }

 @Override
 public String toString() {
  return "console toString print";
 }

}
