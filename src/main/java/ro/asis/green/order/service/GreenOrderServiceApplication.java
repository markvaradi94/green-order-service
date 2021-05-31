package ro.asis.green.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GreenOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenOrderServiceApplication.class, args);
	}

}
