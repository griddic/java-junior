package com.acme.edu;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Java_5 on 31.08.2016.
 */
public class OutStreamToLoggingServer implements OutStream {
    private final String host;
    private final int port;

    public OutStreamToLoggingServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void write(String message) throws OutStreamUnableToOpenException, OutStreamUnableToWriteException {
        try (
                DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(
                                new Socket(this.host, this.port).getOutputStream()
                        )
                )
        ) {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new OutStreamUnableToWriteException(String.format("Unable to send '%s' to logging server.", message), e);
        }
    }
}
