package ch.grideye.log.generator.services;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    public void trace(final Class<?> c, final String... messages) {
        LoggerFactory.getLogger(c).trace(String.join(" ", messages));
    }

    public void debug(final Class<?> c, final String... messages) {
        LoggerFactory.getLogger(c).debug(String.join(" ", messages));
    }

    public void info(final Class<?> c, final String... messages) {
        LoggerFactory.getLogger(c).info(String.join(" ", messages));
    }

    public void warn(final Class<?> c, final String... messages) {
        LoggerFactory.getLogger(c).warn(String.join(" ", messages));
    }

    public void error(final Class<?> c, final String... messages) {
        LoggerFactory.getLogger(c).error(String.join(" ", messages));
    }
}
