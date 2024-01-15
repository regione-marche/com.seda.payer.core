package com.seda.commons.logger;

import org.apache.logging.log4j.LogManager;

public class CustomLoggerManager {

    public static LoggerWrapper get(final Class<?> clazz) {
        return new LoggerWrapper(LogManager.getLogger(clazz));
    }

}