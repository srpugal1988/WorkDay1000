package com.niralcenter.jcmswebface;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;




@SpringBootApplication
public class JcmswebfaceApplication {

	private static final Logger logger = LogManager.getLogger(JcmswebfaceApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(JcmswebfaceApplication.class, args);
		logger.info("===============================================================================");
		logger.info("               JCMSWEBFACE WEBAPP HAS BEEN STARTED                             ");
		logger.info("===============================================================================");
		
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
		
		//INTEGRATED--------->PROFILE,SESSION,VALIDATOR,EXCEPTION,SPRING LOGGING,FILTER
		//NOT-INTEGRATED----->SPRING SECURITY,SWAGGER
		
	}

}
