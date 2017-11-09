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

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final Logger logger = LoggerFactory.getLogger(MifidGeneratorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MifidGeneratorApplication.class, args);
	}

	@PostConstruct
	private void initDB() {
		logger.info("Creating table accnt_ref");
		jdbcTemplate.execute("drop table accnt_ref if exists");
		jdbcTemplate.execute("create table accnt_ref(id serial, name varchar(255), surname varchar(255))");

		logger.info("Creating table explicit_cost");
		jdbcTemplate.execute("drop table explicit_cost if exists");
		jdbcTemplate.execute("create table explicit_cost(id serial, name varchar(255), surname varchar(255))");

		logger.info("Creating table implicit_cost");
		jdbcTemplate.execute("drop table implicit_cost if exists");
		jdbcTemplate.execute("create table implicit_cost(id serial, name varchar(255), surname varchar(255))");
	}

}
