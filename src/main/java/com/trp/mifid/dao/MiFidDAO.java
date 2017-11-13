package com.trp.mifid.dao;

import com.trp.mifid.model.MiFidDetail;

import java.util.List;

public interface MiFidDAO {
    List<MiFidDetail> retrieveMfidDetails();
}
