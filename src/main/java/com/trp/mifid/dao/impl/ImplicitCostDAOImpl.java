package com.trp.mifid.dao.impl;

import com.trp.mifid.dao.ImplicitCostDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ImplicitCostDAOImpl implements ImplicitCostDAO {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initDB() {
        logger.info("Creating table implicit_cost");
        jdbcTemplate.execute("drop table implicit_cost if exists");
        jdbcTemplate.execute("create table implicit_cost(id serial, name varchar(255), surname varchar(255))");
    }

    @Override
    public void create(List<List<String>> sourceContents) {
        String sql = "insert into implicit_cost(name, surname) values (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                List<String> columns = sourceContents.get(i);
                ps.setString(1, columns.get(0));
                ps.setString(2, columns.get(1));
                // ps.setBigDecimal(3, new BigDecimal(columns.get(2)));
            }

            @Override
            public int getBatchSize() {
                return sourceContents.size();
            }
        });
    }
}
