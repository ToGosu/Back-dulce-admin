package co.edu.uco.dulceAdmin.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"co.edu.uco.dulceAdmin"})
@SpringBootApplication
public class DulceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DulceAdminApplication.class, args);
	}

}
