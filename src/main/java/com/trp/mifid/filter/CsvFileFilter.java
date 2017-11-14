package com.trp.mifid.filter;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;

@Component
public class CsvFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".csv");
    }
}
