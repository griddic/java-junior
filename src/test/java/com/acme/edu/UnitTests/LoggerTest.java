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

    @Test
    public void shouldAccumulateStringsForStringSequences() {
        //Given
        Formatter formatter = mock(Formatter.class);
        OutStream outStream = mock(OutStream.class);
        Logger logger = new Logger (formatter, outStream);
        when(formatter.decorateStringsSequence("str 1", 2)).thenReturn("str 1 (x2)");
        //When
        logger.log("str 1");
        logger.log("str 1");
        logger.strSequenceEnd();
        //Then
        verify(outStream).write("str 1 (x2)" + System.lineSeparator());
    }

    @Test
    public void shouldLogIntArrays () {
        //Given
        Formatter formatter = mock(Formatter.class);
        OutStream outStream = mock(OutStream.class);
        Logger logger = new Logger (formatter, outStream);
        when(formatter.decorArray(new int[] {1,2,3,4,5}))
                .thenReturn("primitives array: {1, 2, 3, 4, 5}");
        //When
        logger.log(new int[] {1,2,3,4,5});
        //Then
        verify(outStream).write("primitives array: {1, 2, 3, 4, 5}"
                + System.lineSeparator());
    }

    /*@Test
    public void shouldLogIntMatrix () {
        //Given
        Formatter formatter = mock(Formatter.class);
        OutStream outStream = mock(OutStream.class);
        Logger logger = new Logger (formatter, outStream);
        when(formatter.decorMatrix(new int[][] {{1,2,3},{3,2,1}}))
                .thenReturn("primitives matrix: {" + System.lineSeparator() +
                        "{1, 2, 3}" + System.lineSeparator() +
                        "{3, 2, 1}" + System.lineSeparator() +
                        "}" + System.lineSeparator());
        //When
        logger.log(new int[][] {{1,2,3}, {3,2,1}});
        System.out.println(formatter.decorMatrix(new int[][] {{1,2,3},{3,2,1}}));
        System.out.println("primitives matrix: {" + System.lineSeparator() +
                "{1, 2, 3}" + System.lineSeparator() +
                "{3, 2, 1}" + System.lineSeparator() +
                "}" + System.lineSeparator());
        //Then
        verify(outStream).write("primitives matrix: {" + System.lineSeparator() +
                "{1, 2, 3}" + System.lineSeparator() +
                "{3, 2, 1}" + System.lineSeparator() +
                "}" + System.lineSeparator());
    }*/
}
