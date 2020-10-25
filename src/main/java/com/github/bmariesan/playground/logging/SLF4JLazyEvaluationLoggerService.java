package com.github.bmariesan.playground.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SLF4JLazyEvaluationLoggerService implements LoggerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SLF4JLazyEvaluationLoggerService.class);

    @Override
    public boolean isLogDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    @Override
    public boolean isLogInfoEnabled() {
        return LOGGER.isInfoEnabled();
    }

    @Override
    public void debug(String message) {
        LOGGER.debug(message);
    }

    @Override
    public void info(String message) {
        LOGGER.info(message);
    }
}

