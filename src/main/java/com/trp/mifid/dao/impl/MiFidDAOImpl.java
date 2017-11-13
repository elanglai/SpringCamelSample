package com.trp.mifid.dao.impl;

import com.trp.mifid.dao.MiFidDAO;
import com.trp.mifid.model.MiFidDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MiFidDAOImpl implements MiFidDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MiFidDetail> retrieveMfidDetails() {

        List<MiFidDetail> mifidDetails = jdbcTemplate.query("select * from accnt_ref",
                new BeanPropertyRowMapper<MiFidDetail>());

        return mifidDetails;
    }
}
