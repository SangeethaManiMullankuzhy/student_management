package com.fee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.library.exception.FeignErrorMapper;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.library")
public class FeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeeServiceApplication.class, args);
	}
	
    @Bean
    public FeignErrorMapper feignErrorMapper() {
        return new FeignErrorMapper();
    }

}
