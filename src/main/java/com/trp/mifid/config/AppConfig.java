package com.trp.mifid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;

@Component
@ManagedBean
public class AppConfig {

    @Value("${trp.mifid.implicit.costs.filename.regex}")
    private String IMPLICIT_COSTS_FILE_NAME_REGEX;

    @Value("${trp.mifid.explicit.costs.filename.regex}")
    public String EXPLICIT_COSTS_FILE_NAME_REGEX;

    @Value("${trp.mifid.portfolio.account.filename.regex}")
    private String ACCOUNT_REFERENCE_FILE_NAME_REGEX;

    @Value("${trp.mifid.dry.run.cron}")
    private String dryRunCron;

    @Value("${trp.mifid.final.run.cron}")
    private String finalRunCron;

    @Value("${trp.mifid.inbound.source.folder.path}")
    private String sourcePath;

    @Value("${trp.mifid.test.run.enabled}")
    private boolean testRunEnabled;


    public String getIMPLICIT_COSTS_FILE_NAME_REGEX() {
        return IMPLICIT_COSTS_FILE_NAME_REGEX;
    }

    public String getEXPLICIT_COSTS_FILE_NAME_REGEX() {
        return EXPLICIT_COSTS_FILE_NAME_REGEX;
    }

    public String getACCOUNT_REFERENCE_FILE_NAME_REGEX() {
        return ACCOUNT_REFERENCE_FILE_NAME_REGEX;
    }

    public String getDryRunCron() {
        return dryRunCron.replace(' ', '+');
    }

    public String getFinalRunCron() {
        return finalRunCron.replace (' ', '+');
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public boolean isTestRunEnabled() {
        return testRunEnabled;
    }

}
