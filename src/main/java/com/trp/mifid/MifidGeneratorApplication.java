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
		logger.info("Creating table employees");
		jdbcTemplate.execute("drop table employees if exists");
		jdbcTemplate.execute("create table employees(id serial, name varchar(255), surname varchar(255))");

		jdbcTemplate.execute("insert into employees(name, surname) values('Jan', 'Kowalski')");
		jdbcTemplate.execute("insert into employees(name, surname) values('Stefan', 'Nowak')");


		logger.info("Creating table allocations");
		jdbcTemplate.execute("drop table allocations if exists");
		jdbcTemplate.execute("create table allocations(id serial, week int, year int, shift int, employee_id bigint)");
		jdbcTemplate.execute("insert into allocations(week, year, shift, employee_id) values(29, 2015, 1, 1)");
		jdbcTemplate.execute("insert into allocations(week, year, shift, employee_id) values(28, 2015, 2, 1)");
		jdbcTemplate.execute("insert into allocations(week, year, shift, employee_id) values(29, 2015, 3, 2)");
		jdbcTemplate.execute("insert into allocations(week, year, shift, employee_id) values(28, 2015, 2, 2)");

		logger.warn("resultset: start");
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("select * from employees");
		for (Map row: rows) {
			logger.warn("name:{}, surname:{}", row.get("name"), row.get("surname"));
		}
		logger.warn("resultset: end");
	}

}
