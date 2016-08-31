package com.acme.edu;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Java_5 on 31.08.2016.
 */
public class StringsLoggerServer {
    public static void main(String[] args) {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(1111, 100)) {
                serverSocket.setSoTimeout(300_000);

                while (true) {
                    Socket client = serverSocket.accept();

                    try (
                            DataInputStream in = new DataInputStream(
                                    new BufferedInputStream(
                                            client.getInputStream()
                                    )
                            )
                    ) {
                        System.out.print(in.readUTF());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
