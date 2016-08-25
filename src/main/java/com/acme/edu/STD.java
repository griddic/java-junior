package com.acme.edu;

/**
 * Created by Java_5 on 25.08.2016.
 */
public class STD implements OutStream {
    @Override
    public void write (String message) {
        System.out.print(message);
    }
}