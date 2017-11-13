package com.trp.mifid.service;

import java.util.List;

public interface SourceFileImport {
    void importSource(String filename, List<List<String>> sourceContent);
}
