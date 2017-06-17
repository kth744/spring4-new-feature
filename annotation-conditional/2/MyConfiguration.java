package conditional.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class MyConfiguration {

	  @Bean(name="emailerService")
	  @Conditional(WindowsCondition.class)
	  public EmailService windowsEmailerService(){
	      return new WindowsEmailService();
	  }

	  @Bean(name="emailerService")
	  @Conditional(LinuxCondition.class)
	  public EmailService linuxEmailerService(){
	    return new LinuxEmailService();
	  }

}
