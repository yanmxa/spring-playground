package com.node;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogWithLomboxTest {
    @Test
    public void delete() {
        log.info("记录信息日志 lombok");
        log.warn("记录警告日志 lombok");
        log.error("记录错误日志 lombok");
    }
}
