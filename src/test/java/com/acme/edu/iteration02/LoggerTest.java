package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion



    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.strSequenceEnd();
        Logger.log(1);
        Logger.log(2);
        Logger.intSequenceEnd();
        Logger.log("str 2");
        Logger.strSequenceEnd();
        Logger.log(0);
        Logger.intSequenceEnd();
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
        Logger.log("str 1");
        Logger.strSequenceEnd();
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.intSequenceEnd();
        Logger.log("str 2");
        Logger.strSequenceEnd();
        Logger.log(0);
        Logger.intSequenceEnd();
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
        Logger.log("str 1");
        Logger.strSequenceEnd();
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.byteSequenceEnd();
        Logger.log("str 2");
        Logger.strSequenceEnd();
        Logger.log(0);
        Logger.byteSequenceEnd();
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
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.strSequenceEnd();
        Logger.log(0);
        Logger.intSequenceEnd();
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.strSequenceEnd();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0" );
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");
        //endregion
    }

}