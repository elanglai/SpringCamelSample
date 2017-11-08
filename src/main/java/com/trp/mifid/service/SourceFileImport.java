package com.trp.mifid.service;

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

@Component(value="SourceFileImport")
public class SourceFileImport {

    private final Logger logger = LoggerFactory.getLogger(SourceFileImport.class);

    @Value("${implicit.costs.filename.regex:CUSTOM_MiFID_II_ImplicitCosts_}")
    private String IMPLICIT_COSTS_FILE_NAME_REGEX;
    @Value("${explicit.costs.filename.regex:CUSTOM_MiFID_II_ExplicitCosts_}")
    private String EXPLICIT_COSTS_FILE_NAME_REGEX;
    @Value("${portfolio.account.filename.regex:Portfolio Account CUSTOM_MiFID_II_AccountRefernce_}")
    private String ACCOUNT_REFERENCE_FILE_NAME_REGEX ;

    @Autowired
    private ImplicitCostDAO implicitCostDAO;
    @Autowired
    private ExplicitCostDAO explicitCostDAO;
    @Autowired
    private AccountReferenceDAO accountReferenceDAO;

    /**
     * Imports the inbound source files into the in-memory data model
     * @param filename
     * @param sourceContent
     */
    public void importSource(String filename, List<List<String>> sourceContent) {
        logger.trace("importSource:start");
        try {
            Matcher implicitCostMatcher = Pattern.compile(IMPLICIT_COSTS_FILE_NAME_REGEX).matcher(filename);
            Matcher explicitCostMatcher = Pattern.compile(EXPLICIT_COSTS_FILE_NAME_REGEX).matcher(filename);
            Matcher accountReferenceMatcher = Pattern.compile(ACCOUNT_REFERENCE_FILE_NAME_REGEX).matcher(filename);

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
