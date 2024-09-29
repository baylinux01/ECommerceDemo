package baylinux01.eCommerceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ECommerceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceDemoApplication.class, args);
	}

}
