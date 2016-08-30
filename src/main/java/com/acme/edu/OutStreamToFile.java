package com.acme.edu;

import java.io.*;

/**
 * Created by Java_5 on 30.08.2016.
 */
public class OutStreamToFile implements OutStream {
    public OutStreamToFile(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    @Override
    public void write(String message) throws OutStreamUnableToOpenException, OutStreamUnableToWriteException {
        //region Serialization
        try (OutputStreamWriter out = new OutputStreamWriter(
                new BufferedOutputStream(
                        new FileOutputStream(this.fileName, true)))) {

            out.write(message);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
