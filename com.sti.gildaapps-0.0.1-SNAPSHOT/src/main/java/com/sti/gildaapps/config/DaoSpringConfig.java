package com.sti.gildaapps.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sti.gildaapps.dao.AccountDao;
import com.sti.gildaapps.dao.CustomerDao;
import com.sti.gildaapps.dao.TransactionDao;
import com.sti.gildaapss.dao.impl.AccountDaoImpl;
import com.sti.gildaapss.dao.impl.CustomerDaoImpl;
import com.sti.gildaapss.dao.impl.TransactionDaoImpl;

@Configuration
public class DaoSpringConfig {
	
	@Bean //object yang dibikin di spring
	public CustomerDao customerDao() {
		return new CustomerDaoImpl(); // dipulangkan dengan menggunakan autowired
	}
	
	@Bean //object yang dibikin di spring
	public AccountDao accountDao() {
		return new AccountDaoImpl(); // dipulangkan dengan menggunakan autowired
	}
	
	@Bean //object yang dibikin di spring
	public TransactionDao transactionDao() {
		return new TransactionDaoImpl(); // dipulangkan dengan menggunakan autowired
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                .allowedHeaders("*");
            }
        };
    }
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
