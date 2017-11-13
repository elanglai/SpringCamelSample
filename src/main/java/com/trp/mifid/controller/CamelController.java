package com.trp.mifid.controller;

import com.trp.mifid.dao.MiFidDAO;
import com.trp.mifid.model.MiFidDetail;
import com.trp.mifid.util.JsonUtil;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String startOnDemandProcess() {
        try {
            producerTemplate.sendBody("direct:onDemandTrigger", "Manually trigger the MiFID generation process");
        }
        catch(Exception e) {
            return "FAILED";
        }

        return "OK";
    }

    @RequestMapping(value = "/mifid")
    public String retrieveMiFid() {
        // Retrieve the result
        List<MiFidDetail> miFidDetails = miFidDAO.retrieveMfidDetails();
        return JsonUtil.toJson(miFidDetails);
    }
}