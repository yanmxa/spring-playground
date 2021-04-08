package com.node;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void delete() {
        logger.info("记录信息日志");
        logger.warn("记录警告日志");
        logger.error("记录错误日志");
    }
}
