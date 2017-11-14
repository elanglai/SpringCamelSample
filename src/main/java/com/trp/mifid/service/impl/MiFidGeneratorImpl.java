package com.trp.mifid.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="MiFidGenerator")
public class MiFidGeneratorImpl implements com.trp.mifid.service.MiFidGenerator {

    final Logger logger = LoggerFactory.getLogger(MiFidGeneratorImpl.class);

    @Override
    public void generate() {
        logger.trace("generate:start");
        try {
            logger.info("Msg 1");
        } finally {
            logger.trace("generate:end");
        }

    }
}
