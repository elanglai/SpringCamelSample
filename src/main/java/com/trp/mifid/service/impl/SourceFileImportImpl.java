package com.trp.mifid.service.impl;

import com.trp.mifid.config.AppConfig;
import com.trp.mifid.dao.AccountReferenceDAO;
import com.trp.mifid.dao.ExplicitCostDAO;
import com.trp.mifid.dao.ImplicitCostDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value="SourceFileImportImpl")
public class SourceFileImportImpl implements com.trp.mifid.service.SourceFileImport {

    private final Logger logger = LoggerFactory.getLogger(SourceFileImportImpl.class);

    @Autowired
    private ImplicitCostDAO implicitCostDAO;
    @Autowired
    private ExplicitCostDAO explicitCostDAO;
    @Autowired
    private AccountReferenceDAO accountReferenceDAO;
    @Autowired
    private AppConfig appConfig;

    /**
     * Imports the inbound source files into the in-memory data model
     * @param filename
     * @param sourceContent
     */
    @Override
    public void importSource(String filename, List<List<String>> sourceContent) {
        logger.trace("importSource:start");
        try {
            Matcher implicitCostMatcher = Pattern.compile(appConfig.getIMPLICIT_COSTS_FILE_NAME_REGEX()).matcher(filename);
            Matcher explicitCostMatcher = Pattern.compile(appConfig.getEXPLICIT_COSTS_FILE_NAME_REGEX()).matcher(filename);
            Matcher accountReferenceMatcher = Pattern.compile(appConfig.getACCOUNT_REFERENCE_FILE_NAME_REGEX()).matcher(filename);

            if (implicitCostMatcher.matches()) {
                implicitCostDAO.create(sourceContent);
            } else if (explicitCostMatcher.matches()) {
                explicitCostDAO.create(sourceContent);
            } else if (accountReferenceMatcher.matches()) {
                accountReferenceDAO.create(sourceContent);
            }
        } finally {
            logger.trace("importSource:end");
        }

    }
}
