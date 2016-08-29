package com.acme.edu.iteration01;

import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

@Ignore
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

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.intSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("primitive: 0");
        //assertSysoutEquals("primitive: 1" + System.lineSeparator() + "primitive: 0" + System.lineSeparator() + "primitive: -1" + System.lineSeparator() );
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        logger.log((byte)1);
        logger.log((byte)0);
        logger.log((byte)-1);
        logger.byteSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("primitive: 0");
        //assertSysoutContains("1");
        //assertSysoutContains("0");
        //assertSysoutContains("-1");
        //endregion
    }


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        logger.log('a');
        logger.log('b');
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }


    @Test
    public void shouldLogString() throws IOException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.strSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        logger.log(true);
        logger.log(false);
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }


    @Test
    public void shouldLogReference() throws IOException {
        //region when
        logger.log(new Object());
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}