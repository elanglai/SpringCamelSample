package com.trp.mifid.dao.impl;

import com.trp.mifid.dao.MiFidDAO;
import com.trp.mifid.model.MiFidDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MiFidDAOImpl implements MiFidDAO {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MiFidDetail> retrieveMfidDetails() {

//        List<MiFidDetail> mifidDetails = jdbcTemplate.query("select * from mifid",
//                new BeanPropertyRowMapper<MiFidDetail>());

        List rowList = jdbcTemplate.queryForList("select * from mifid");
        for (Object row: rowList) {
            logger.debug("row="+row);
        }
//        return mifidDetails;
        return null;
    }
}
