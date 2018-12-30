package com.sti.gildaapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sti.gildaapps.config.DaoSpringConfig;

/**
 * Hello world!
 *
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories({"com.sti.gildaapps.dao.repository"})
//@EntityScan({"com.sti.gildaapps.model"})
@Import({DaoSpringConfig.class})

public class Application 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(Application.class, args);
    }
}
