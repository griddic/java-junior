package com.acme.edu;

import java.util.Objects;

public class Logger {
    static STD handler = new STD();
    static Formatter formatter = new Formatter();

    public static void logRawString(String message) {
        handler.write(message);
    }


    public static void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    public static void log(int message) {
        logLnRawString(formatter.anyPrimitiveType(message));
    }

    public static void log(byte message) {
        logLnRawString(formatter.anyPrimitiveType(message));
    }

    public static void log(char message) {
        logLnRawString(formatter.char_(message));
    }

    public static void log(String message) {
        logLnRawString(formatter.string_(message));
    }

    public static void log(boolean message) {
        logLnRawString(formatter.anyPrimitiveType(message));
    }
    public static void log(Object message) {
        logLnRawString(formatter.object_(message));
    }
}

class Formatter {
    public static String char_ (char ch) {
        return String.format("char: %s", ch);
    }
    public static String string_(String str) {
        return String.format("string: %s", str);
    }
    public static String object_(Object obj) {
        return String.format("reference: %s", obj);
    }
    public static String anyPrimitiveType(Object obj) {
        return String.format("primitive: %s", obj);
    }
}