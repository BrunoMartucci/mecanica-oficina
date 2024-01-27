package com.prov.mecanicaoficina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.prov.mecanicaoficina")
@EntityScan(basePackages = "com.prov.mecanicaoficina.entity")
public class MecanicaOficinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MecanicaOficinaApplication.class, args);
	}

}
