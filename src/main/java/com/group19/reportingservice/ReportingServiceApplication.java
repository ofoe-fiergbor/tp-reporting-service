package com.group19.reportingservice;

import com.group19.reportingservice.model.dto.MessageDto;
import com.group19.reportingservice.service.firebase.MessagingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableEurekaClient
public class ReportingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingServiceApplication.class, args);
	}

	@Bean
	public List<MessageDto> messaging() throws ExecutionException, InterruptedException {
		return new MessagingService().getMessages();
	}
}
