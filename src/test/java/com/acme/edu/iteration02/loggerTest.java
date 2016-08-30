package com.acme.edu.iteration02;

import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;


public class loggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    private Logger logger;
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger =  LoggerFactory.createTestLogger();

    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion



    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        logger.log("str 1");
        logger.strSequenceEnd();
        logger.log(1);
        logger.log(2);
        logger.intSequenceEnd();
        logger.log("str 2");
        logger.strSequenceEnd();
        logger.log(0);
        logger.intSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("str 1" + System.lineSeparator());
        assertSysoutContains("3" + System.lineSeparator());
        assertSysoutContains("str 2" + System.lineSeparator());
        assertSysoutContains("0" + System.lineSeparator());
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        logger.log("str 1");
        logger.strSequenceEnd();
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.intSequenceEnd();
        logger.log("str 2");
        logger.strSequenceEnd();
        logger.log(0);
        logger.intSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Integer.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");

        //endregion
    }


    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        logger.log("str 1");
        logger.strSequenceEnd();
        logger.log((byte)10);
        logger.log(Byte.MAX_VALUE);
        logger.byteSequenceEnd();
        logger.log("str 2");
        logger.strSequenceEnd();
        logger.log(0);
        logger.byteSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Byte.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.strSequenceEnd();
        logger.log(0);
        logger.intSequenceEnd();
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.strSequenceEnd();
        logger.log("str 4");
        logger.strSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0" );
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");
        assertSysoutContains("str 4");
        //endregion
    }

}