package com.seda.commons.logger;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class LoggerBaseHandler implements LoggerInterface {

    // Logger logger;

    private LoggerWrapper logger =  CustomLoggerManager.get(LoggerBaseHandler.class);


    // public LoggerBaseHandler(Logger logger) {
    //     this.logger = logger;
    // }

    public String getName()
    {
        return logger.getName();
    }

    private String buildMessage(String message) {
        return buildJsonMessage(message, null);
    }

    private String buildMessage(String message, Map<LOGGER_PROPERTY, Object> properties) {
        return buildJsonMessage(message, properties);
    }

    private String buildJsonMessage(String message, Map<LOGGER_PROPERTY, Object> properties) {
        ObjectMapper mapper = new ObjectMapper();
        Map<LOGGER_PROPERTY, Object> messageMap = buildMessageMap(message, properties);
        try {
            return mapper.writeValueAsString(messageMap);
        } catch (JsonProcessingException jex) {
            jex.printStackTrace();
            return null;
        }
    }

    private Map<LOGGER_PROPERTY, Object> buildMessageMap(String message, Map<LOGGER_PROPERTY, Object> properties) {
        Map<LOGGER_PROPERTY, Object> messageDetails = new HashMap<LOGGER_PROPERTY, Object>();
        if (properties != null && !properties.isEmpty()) messageDetails.putAll(properties);
        messageDetails.put(LOGGER_PROPERTY.Message, (message != null) ? message : "");
        try {
            messageDetails.put(LOGGER_PROPERTY.JVMId, ManagementFactory.getRuntimeMXBean().getName());
        } catch (Exception e) {
            System.out.println("ERRORE: " + e.getMessage());
        }
        if (messageDetails.containsKey(LOGGER_PROPERTY.JVMId))
            messageDetails.put(LOGGER_PROPERTY.ThreadId, Long.valueOf(Thread.currentThread().getId()));
        return messageDetails;
    }

    //INFO
    public void info(String message)
    {
        logger.info(buildMessage(message));
    }

    public void info(String message, Map<LOGGER_PROPERTY, Object> propertie) {
        logger.info(buildMessage(message, propertie));
    }

    //WARN
    public void warn(String message) {
        logger.warn(buildMessage(message));
    }

    public void warn(Throwable e) {
        warn(e.getMessage());
    }

    public void warn(String message, Throwable e) {
        warn(message + ": " + e.getMessage());
    }

    public void warn(String message, Map<LOGGER_PROPERTY, Object> properties) {
        logger.warn(buildMessage(message, properties));
    }

    public void warn(String message, Throwable e, Map<LOGGER_PROPERTY, Object> properties) {
        warn(message + ": " + e.getMessage(), properties);
    }

    //ERROR
    public void error(String message) {
        logger.error(buildMessage(message));
    }

    public void error(Throwable e) {
        error(e.getMessage());
    }

    public void error(String message, Throwable e) {
        error(message + ": " + e.getMessage());
    }

    public void error(String message, Map<LOGGER_PROPERTY, Object> properties) {
        logger.error(buildMessage(message, properties));
    }

    public void error(String message, Throwable e, Map<LOGGER_PROPERTY, Object> properties) {
        error(message + ": " + e.getMessage(), properties);
    }

    //DEGUB
    public void debug(String message) {
        logger.debug(buildMessage(message));
    }

    public void debug(String message, Map<LOGGER_PROPERTY, Object> properties) {
        logger.debug(buildMessage(message, properties));
    }

}
