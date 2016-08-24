package com.acme.edu;

import java.util.Objects;

public class Logger {
    static STD handler = new STD();

    public static void logRawString(String message) {
        handler.write(message);
    }


    public static void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    public static void log(int message) {
        logLnRawString(Formatter.anyPrimitiveType(message));
    }

    public static void log(byte message) {
        logLnRawString(Formatter.anyPrimitiveType(message));
    }

    public static void log(char message) {
        logLnRawString(Formatter.char_(message));
    }

    public static void log(String message) {
        logLnRawString(Formatter.string_(message));
    }

    public static void log(boolean message) {
        logLnRawString(Formatter.anyPrimitiveType(message));
    }
    public static void log(Object message) {
        logLnRawString(Formatter.object_(message));
    }
}

class Formatter {
    static String format(String template, Object msg) {
        return String.format(template, msg);
    }
    public static String char_ (char ch) {
        return format("char: %s", ch);
    }
    public static String string_(String str) {
        return format("string: %s", str);
    }
    public static String object_(Object obj) {
        return format("reference: %s", obj);
    }
    public static String anyPrimitiveType(Object obj) {
        return format("primitive: %s", obj);
    }
}