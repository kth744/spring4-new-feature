package conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class ConditionalConfig {
	@Conditional(DevCondition.class)
	@Bean(name="movie")
	public Movie getDevMovie(){
		Movie movie = new Movie();
		movie.setTitle("Dev");
		return movie;
	}
	
	@Conditional(ProdCondition.class)
	@Bean(name="movie")
	public Movie getProdMovie(){
		Movie movie = new Movie();
		movie.setTitle("Prod");
		return movie;
	}
	
	@Conditional(DefaultCondition.class)
	@Bean(name="movie")
	public Movie getDefaultMovie(){
		Movie movie = new Movie();
		movie.setTitle("Default");
		return movie;
	}
}
