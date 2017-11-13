package com.trp.mifid.dao.impl;

import com.trp.mifid.dao.AccountReferenceDAO;
import com.trp.mifid.service.impl.MiFidGeneratorImpl;
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
public class AccountReferenceDAOImpl implements AccountReferenceDAO {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initDB() {
        logger.info("Creating table accnt_ref");
        jdbcTemplate.execute("drop table accnt_ref if exists");
        jdbcTemplate.execute("create table accnt_ref(id serial, name varchar(255), surname varchar(255))");
    }

    @Override
    public void create(List<List<String>> sourceContents) {

        String sql = "insert into accnt_ref(name, surname) values (?, ?)";

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
