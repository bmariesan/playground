package com.github.bmariesan.playground.logging;

import java.util.function.Supplier;

public interface LoggerService {

    boolean isLogDebugEnabled();

    boolean isLogInfoEnabled();

    void debug(String message);

    void info(String message);

    default void debug(Supplier<String> messageSupplier) {
        if (isLogDebugEnabled()) {
            debug(messageSupplier.get());
        }
    }

    default void info(Supplier<String> messageSupplier) {
        if (isLogInfoEnabled()) {
            info(messageSupplier.get());
        }
    }

}
