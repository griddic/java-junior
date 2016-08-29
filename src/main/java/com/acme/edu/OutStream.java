package com.acme.edu;
/**
 * Created by Java_5 on 23.08.2016.
 */

public interface OutStream {
    public void write (String message)
            throws OutStreamUnableToOpenException, OutStreamUnableToWriteException;
}