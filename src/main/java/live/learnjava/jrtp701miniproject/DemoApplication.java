package live.learnjava.jrtp701miniproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import live.learnjava.jrtp701miniproject.entity.TravelPlan;
import live.learnjava.jrtp701miniproject.repository.ITravelPlanRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner insertData(ITravelPlanRepository repository) {
		return args -> {
			TravelPlan plan1 = new TravelPlan();
			plan1.setPlanCategoryId(1);
			plan1.setPlanName("SIG/4D-5N");
			plan1.setPlanMinBudget(90000.99);
			plan1.setActiveSW("active");
			plan1.setPlanDescription("Singapore - 4 Days and 5 Nights - 5 star hotels");
			repository.save(plan1);
			
			TravelPlan plan2 = new TravelPlan();
			plan2.setPlanCategoryId(2);
			plan2.setPlanName("NY/4D-5N");
			plan2.setPlanDescription("New York 4 Days and 5 Night - 4 to 5 star hotels");
			plan2.setPlanMinBudget(490000.99);
			plan2.setActiveSW("active");
			repository.save(plan2);
		};
	}

}
