package com.ohrlings.arrowheadadapter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
    @Autowired
    AdapterController controller;

    private final static String TEST_DATA = "testData";
    private final static String LOGGER_NAME = "com.ohrlings.arrowheadadapter";
    private MemoryAppender appender;

    @BeforeEach
    public void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(LOGGER_NAME);
        appender = new MemoryAppender();
        appender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(appender);
        appender.start();
    }

    @AfterAll
    public void cleanUp() {
        appender.reset();
        appender.stop();
    }

    @Test
    public void shouldGetA200InReturn() {
        var actual = controller.pushData(TEST_DATA);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void shouldGetTheSameResponseAsPushed() {
        var actual = controller.pushData(TEST_DATA);

        assertEquals(TEST_DATA, actual.getBody());
    }

    @Test
    public void shouldPrintOutTheProvidedValueToLog() {
        controller.pushData(TEST_DATA);

        assertThat(appender.countEventsForLogger(LOGGER_NAME)).isEqualTo(1);
        assertThat(appender.search(TEST_DATA, Level.INFO).size()).isEqualTo(1);
        assertThat(appender.contains(TEST_DATA, Level.TRACE)).isFalse();
    }
}
