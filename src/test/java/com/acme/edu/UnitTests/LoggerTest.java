package com.acme.edu.UnitTests;

import com.acme.edu.Formatter;
import com.acme.edu.Logger;
import com.acme.edu.LoggerFactory;
import com.acme.edu.OutStream;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Java_6 on 26.08.2016.
 */
public class LoggerTest {
    @Test
    public void shouldAccumulateNumbersForIntSequences() {
        //Given
        Formatter formatter = mock(Formatter.class);
        OutStream outStream = mock(OutStream.class);
        Logger logger = new Logger (formatter, outStream);
        when(formatter.decorateAnyPrimitiveType((long) 5)).thenReturn("primitive: 5");
        //When
        logger.log(5);
        logger.intSequenceEnd();
        //Then
        verify(outStream).write("primitive: 5" + System.lineSeparator());
    }
    @Test
    public void shouldAccumulateNumbersForByteSequences() {
        //Given
        Formatter formatter = mock(Formatter.class);
        OutStream outStream = mock(OutStream.class);
        Logger logger = new Logger (formatter, outStream);
        when(formatter.decorateAnyPrimitiveType((int) 5)).thenReturn("primitive: 5");
        //When
        logger.log((byte)5);
        logger.byteSequenceEnd();
        //Then
        verify(outStream).write("primitive: 5" + System.lineSeparator());
    }
}
