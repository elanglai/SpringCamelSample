package com.trp.mifid.controller;

import com.trp.mifid.dao.MiFidDAO;
import com.trp.mifid.model.MiFidDetail;
import com.trp.mifid.util.JsonUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CamelController {

    @Autowired
    MiFidDAO miFidDAO;

    @Autowired
    CamelContext camelContext;

    @Autowired
    ProducerTemplate producerTemplate;

    @RequestMapping(value = "/run")
    public ResponseEntity startOnDemandProcess() {
        try {
            producerTemplate.sendBody("direct:onDemandTrigger", "Manually trigger the MiFID generation process");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/mifid", produces = "application/json")
    @ResponseBody
    public List<MiFidDetail> retrieveMiFid() {
        return miFidDAO.retrieveMfidDetails();
    }
}