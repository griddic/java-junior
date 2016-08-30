package com.acme.edu.LoggingToFileTesting;

import com.acme.edu.Logger;
import com.acme.edu.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

/**
 * Created by Java_6 on 30.08.2016.
 */
public class OutStreamToFileTest {

    private Logger logger;
    private String fileName = "logger.log";

    @Before
    public void setUpSystemOut() {
        logger = LoggerFactory.fileOnlyLogger(fileName);
    }

    @Test
    public void shouldWriteCharToFile() throws IOException {
        //region When
        logger.log('a');
        File file = new File(fileName);
        List lines = FileUtils.readLines(file, "UTF-8");
        //endregion
        //region Than
        assert (lines.get(lines.size()-1).toString().equals("char: a"));
        //endregion
    }

    @Test
    public void shouldWriteIntegerOverflowCorrectlyToFile() throws IOException {
        //When
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.intSequenceEnd();
        File file = new File(fileName);
        List lines = FileUtils.readLines(file, "UTF-8");
        //Than
        assert (lines.get(lines.size()-2).toString().equals("primitive: 10"));
        assert (lines.get(lines.size()-1).toString().equals("" + Integer.MAX_VALUE));
    }

    @Test
    public void shouldWriteByteOverflowCorrectlyToFile() throws IOException {
        //When
        logger.log((byte)67);
        logger.log(Byte.MAX_VALUE);
        logger.byteSequenceEnd();
        File file = new File(fileName);
        List lines = FileUtils.readLines(file, "UTF-8");
        //Than
        assert (lines.get(lines.size()-2).toString().equals("primitive: 67"));
        assert (lines.get(lines.size()-1).toString().equals("" + Byte.MAX_VALUE));
    }
}
