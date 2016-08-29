package com.acme.edu;

/**
 * Created by Java_6 on 26.08.2016.
 */
public class OutputsContainer implements OutStream {
    OutStream[] streams;

    public OutputsContainer(OutStream[] outStreams) {
        this.streams = outStreams;
    }

    @Override
    public void write(String message)
            throws OutStreamUnableToOpenException, OutStreamUnableToWriteException {
        for (OutStream stream:
             streams) {
            stream.write(message);
        }
    }
}
