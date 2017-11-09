package com.trp.mifid.model;

import java.util.ArrayList;
import java.util.List;

public class MiFidDetail {
    private String fundCode;
    private List<ChargeDetail> monthlyCharges = new ArrayList<>();
    private List<ChargeDetail> annualCharges = new ArrayList<>();

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public List<ChargeDetail> getMonthlyCharges() {
        return monthlyCharges;
    }

    public List<ChargeDetail> getAnnualCharges() {
        return annualCharges;
    }
}
