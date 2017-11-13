package com.trp.mifid.model;

import java.math.BigDecimal;

public class ChargeDetail {
    private BigDecimal oneOfCharge;
    private BigDecimal onGoingCharge;
    private BigDecimal incidentalCharge;
    private BigDecimal tradingCharge;
    private BigDecimal totalCharge;
    private BigDecimal oneOffPercent;
    private BigDecimal onGoingPercent;
    private BigDecimal tradingPercent;
    private BigDecimal totalPercent;

    public ChargeDetail(BigDecimal oneOfCharge, BigDecimal onGoingCharge, BigDecimal incidentalCharge, BigDecimal tradingCharge, BigDecimal totalCharge, BigDecimal oneOffPercent, BigDecimal onGoingPercent, BigDecimal tradingPercent, BigDecimal totalPercent) {
        this.oneOfCharge = oneOfCharge;
        this.onGoingCharge = onGoingCharge;
        this.incidentalCharge = incidentalCharge;
        this.tradingCharge = tradingCharge;
        this.totalCharge = totalCharge;
        this.oneOffPercent = oneOffPercent;
        this.onGoingPercent = onGoingPercent;
        this.tradingPercent = tradingPercent;
        this.totalPercent = totalPercent;
    }

    public BigDecimal getOneOfCharge() {
        return oneOfCharge;
    }

    public BigDecimal getOnGoingCharge() {
        return onGoingCharge;
    }

    public BigDecimal getIncidentalCharge() {
        return incidentalCharge;
    }

    public BigDecimal getTradingCharge() {
        return tradingCharge;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public BigDecimal getOneOffPercent() {
        return oneOffPercent;
    }

    public BigDecimal getOnGoingPercent() {
        return onGoingPercent;
    }

    public BigDecimal getTradingPercent() {
        return tradingPercent;
    }

    public BigDecimal getTotalPercent() {
        return totalPercent;
    }
}
