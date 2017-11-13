package com.trp.mifid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MifidGeneratorApplication {

	final Logger logger = LoggerFactory.getLogger(MifidGeneratorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MifidGeneratorApplication.class, args);
	}

}
