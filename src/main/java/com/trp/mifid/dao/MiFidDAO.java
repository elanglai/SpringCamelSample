package com.trp.mifid.dao;

import com.trp.mifid.model.MiFidDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MiFidDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MiFidDetail> retrieveMfidDetails() {

        List<MiFidDetail> mifidDetails = jdbcTemplate.query("select * from accnt_ref",
                new BeanPropertyRowMapper<MiFidDetail>());

        return mifidDetails;
    }

}
