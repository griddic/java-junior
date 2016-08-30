package com.acme.edu.UnitTests;

import com.acme.edu.*;
import org.junit.Test;

/**
 * Created by Java_5 on 26.08.2016.
 */
public class OutStreamToConsoleTest implements SysoutCaptureAndAssertionAbility {
    @Test
    public void writenStringAppearsInConsole() throws OutStreamUnableToWriteException, OutStreamUnableToOpenException {
        OutStream stdout = new OutStreamToConsole();

        resetOut();
        captureSysout();

        stdout.write("lalala");
        assertSysoutEquals("lalala");
    }
}
