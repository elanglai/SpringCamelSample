package com.trp.mifid.service.impl;

import com.trp.mifid.config.AppConfig;
import com.trp.mifid.dao.AccountReferenceDAO;
import com.trp.mifid.dao.ExplicitCostDAO;
import com.trp.mifid.dao.ImplicitCostDAO;
import com.trp.mifid.exception.InvalidSourceFolderException;
import com.trp.mifid.exception.NoSourceFileFoundException;
import com.trp.mifid.filter.CsvFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value="SourceFileImport")
public class SourceFileImportImpl implements com.trp.mifid.service.SourceFileImport {

    private final Logger logger = LoggerFactory.getLogger(SourceFileImportImpl.class);

    @Autowired
    private CsvFileFilter csvFileFilter;
    @Autowired
    private ImplicitCostDAO implicitCostDAO;
    @Autowired
    private ExplicitCostDAO explicitCostDAO;
    @Autowired
    private AccountReferenceDAO accountReferenceDAO;
    @Autowired
    private AppConfig appConfig;

    /**
     * Import all inbound source files
     */
    public void importSources() {
        final String srcPath = appConfig.getSourcePath();
        File folder = new File(srcPath);

        validateFolderContent(folder, srcPath);
    }

    /**
     * Verifies the inbound source folder to make sure it contains at least one csv file
     */
    private void validateFolderContent(File folder, String srcPath) {
        if (! folder.isDirectory()) {
            String msg = String.format("Source path [%s] is not a valid directory", srcPath);
            throw new InvalidSourceFolderException(msg);
        }

        // Verify the cvs file count > 0
        int csvFileCount = folder.listFiles(csvFileFilter).length;
        if (csvFileCount == 0) {
            String msg = String.format("No csv file found under the folder path [%s]", srcPath);
            throw new NoSourceFileFoundException(msg);
        }

        logger.info("Found {} cvs files in the inbound source folder path [{}]", csvFileCount, srcPath);
    }

    /**
     * Imports the inbound source files into the in-memory data model
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
