package com.trp.mifid.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class AppLogger {

    private Logger logger;

    public AppLogger(Class<?> clazz)  {
        logger = LoggerFactory.getLogger(clazz);
    }

    public AppLogger(Logger logger) {
        this.logger = logger;
    }

    public void trace(String s) {
        logger.trace(s);
    }

    public void debug(String s) {
        logger.debug(s);
    }

    public void info(String s) {
        logger.info(s);
    }

    public void warn(String s) {
        logger.warn(s);
    }

    public void error(String s, Object object) {
        logger.error(s, object);
    }

    public void error(String s, Throwable throwable) {
        logger.error(s, throwable);
    }
}
