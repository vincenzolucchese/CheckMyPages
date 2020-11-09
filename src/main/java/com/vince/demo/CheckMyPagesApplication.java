package com.vince.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vince.demo.repository.WebSiteRepository;
import com.vince.demo.util.CheckSiteService;
import com.vince.demo.util.FileSystemService;

@SpringBootApplication
public class CheckMyPagesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CheckMyPagesApplication.class);

	@Autowired
	private FileSystemService fileSystemService;
	
	@Autowired
	private CheckSiteService checkSiteService;

	public static void main(String[] args) {
		SpringApplication.run(CheckMyPagesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(WebSiteRepository repository) {
		return (args) -> {
			Date timeStamp = new Date();
			fileSystemService.fillDbByJson(timeStamp);	
			checkSiteService.checkAllSiteNow(timeStamp);
		};
	}
	

}
