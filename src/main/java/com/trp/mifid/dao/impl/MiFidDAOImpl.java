package com.trp.mifid.dao.impl;

import com.trp.mifid.dao.MiFidDAO;
import com.trp.mifid.model.ChargeDetail;
import com.trp.mifid.model.MiFidDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
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

//        List rowList = jdbcTemplate.queryForList("select * from mifid");
//        for (Object row: rowList) {
//            logger.debug("row="+row);
//        }


//        return mifidDetails;
        return mockResponse();
    }

    private List<MiFidDetail> mockResponse() {
        List<MiFidDetail> mockResponse = new ArrayList<>();
        BigDecimal oneOfCharge = new BigDecimal("10.87");
        BigDecimal onGoingCharge = new BigDecimal("20.87");
        BigDecimal incidentalCharge = new BigDecimal("30.87");
        BigDecimal tradingCharge = new BigDecimal("40.87");
        BigDecimal totalCharge = new BigDecimal("50.87");
        BigDecimal oneOffPercent = new BigDecimal("60.87");
        BigDecimal onGoingPercent = new BigDecimal("70.87");
        BigDecimal tradingPercent = new BigDecimal("80.87");
        BigDecimal totalPercent = new BigDecimal("90.87");

        mockResponse.add(createMiFidDetail(
                "fundA",
                new ChargeDetail(oneOfCharge, onGoingCharge, incidentalCharge, tradingCharge, totalCharge, oneOffPercent, onGoingPercent, tradingPercent, totalPercent),
                new ChargeDetail(oneOfCharge, onGoingCharge, incidentalCharge, tradingCharge, totalCharge, oneOffPercent, onGoingPercent, tradingPercent, totalPercent)
        ));
        return mockResponse;
    }

    private MiFidDetail createMiFidDetail(String fundCode, ChargeDetail monthlyCharge, ChargeDetail annualCharge) {
        MiFidDetail miFidDetail = new MiFidDetail();
        miFidDetail.setFundCode(fundCode);
        miFidDetail.getMonthlyCharges().add(annualCharge);
        miFidDetail.getAnnualCharges().add(monthlyCharge);

        return miFidDetail;
    }
}
