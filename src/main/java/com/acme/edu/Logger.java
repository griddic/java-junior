package com.acme.edu;

public class Logger {
    static STD handler = new STD();
    static Formatter formatter = new Formatter();
    static int int_counter = 0;
    static int int_MAX_counter = 0;

    public static void logRawString(String message) {
        handler.write(message);
    }


    public static void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    public static void log(int number) {
        if (number == Integer.MAX_VALUE) {
            int_MAX_counter += 1;
        } else {
            int_counter += number;
        }
        //logLnRawString(formatter.anyPrimitiveType(message));
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

    public static void sequenceEnd() {
        logLnRawString(formatter.anyPrimitiveType(int_counter));
        for (int i = 0; i < int_MAX_counter; i++) {
            logLnRawString(String.format("%s",Integer.MAX_VALUE));
        }
        int_counter = 0;
        int_MAX_counter = 0;
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
    public static String none(Object obj) {
        return String.format("%s", obj);
    }
}