package com.acme.edu;

public class Logger {
    public static void logAnyPrimitiveType(Object message) {
        System.out.println("primitive: " + message);
    }

    public static void log(int message) {
        logAnyPrimitiveType(message);
    }

    public static void log(byte message) {
        logAnyPrimitiveType(message);
    }

    public static void log(char message) {
        System.out.println("char: " + message);
    }

    public static void log(String message) {
        System.out.println("string: " + message);
    }

    public static void log(boolean message) {
        logAnyPrimitiveType(message);
    }
    public static void log(Object message) {
        System.out.println("reference: " + message);
    }
}
