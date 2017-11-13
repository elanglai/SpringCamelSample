package com.trp.mifid.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.event.LoggingEvent;

import java.util.Arrays;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppLoggerTest {
    public static final String TEST_APPENDER = "testAppender";
    public static final String ERROR_MSG = "error msg";
    public static final String WARN_MSG = "warn msg";
    public static final String INFO_MSG = "info msg";
    public static final String DEBUG_MSG = "debug msg";
    private AppLogger appLogger;

    @Mock
    Logger loggerMock;

    @Before
    public void setup() {
        appLogger = new AppLogger(loggerMock);
    }

    @Test
    public void trace() throws Exception {
        appLogger.trace(ERROR_MSG);
        verify(loggerMock).trace(ERROR_MSG);
    }


    @Test
    public void debug() throws Exception {
        appLogger.debug(DEBUG_MSG);
        verify(loggerMock).debug(DEBUG_MSG);
    }

    @Test
    public void info() throws Exception {
        appLogger.info(INFO_MSG);
        verify(loggerMock).info(INFO_MSG);

    }

    @Test
    public void warn() throws Exception {
        appLogger.warn(WARN_MSG);
        verify(loggerMock).warn(WARN_MSG);
    }

    @Test
    public void error() throws Exception {
        Object object = new Object();
        appLogger.error(ERROR_MSG, object);
        Arrays[] objects = null;
        verify(loggerMock).error(ERROR_MSG, object);
    }

    @Test
    public void errorWithThrowable() throws Exception {
        Throwable throwable = new NullPointerException();
        appLogger.error(ERROR_MSG, throwable);

        verify(loggerMock).error(ERROR_MSG, throwable);
    }
}