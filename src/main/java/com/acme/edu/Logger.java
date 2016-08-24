package com.acme.edu;

public class Logger {
    static STD handler = new STD();
    static Formatter formatter = new Formatter();
    static long int_counter = 0;
    static int int_MAX_counter = 0;

    static int byte_counter = 0;
    static int byte_MAX_counter = 0;

    static String last_string = null;
    static int last_string_counter = 0;

    public static void logRawString(String message) {
        handler.write(message);
    }


    public static void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    public static void log(int number) {
        int a;
        int_counter += number;
        a = (int) (int_counter/Integer.MAX_VALUE);
        int_MAX_counter += a;
        int_counter = int_counter - (Integer.MAX_VALUE * a);
        //logLnRawString(formatter.anyPrimitiveType(message));
    }

    public static void log(byte number) {
        int a;
        byte_counter += number;
        a = (int) (byte_counter/Byte.MAX_VALUE);
        byte_MAX_counter += a;
        byte_counter = byte_counter - (Byte.MAX_VALUE * a);
        //logLnRawString(formatter.anyPrimitiveType(message));
    }

    public static void log(char message) {
        logLnRawString(formatter.char_(message));
    }

    public static void log(String message) {
        if (message.equals(last_string)) {
            ++last_string_counter;
        } else {
            if (last_string_counter > 0) {
                logLnRawString(formatter.stringsSequance(last_string, last_string_counter));
            } // else in this case is a first string in sequance.
            last_string = message;
            last_string_counter = 1;
        }
        // logLnRawString(formatter.string_(message));
    }

    public static void strSequenceEnd() {
        logLnRawString(formatter.stringsSequance(last_string, last_string_counter));
        last_string = null;
        last_string_counter = 0;
    }

        public static void log(boolean message) {
        logLnRawString(formatter.anyPrimitiveType(message));
    }
    public static void log(Object message) {
        logLnRawString(formatter.object_(message));
    }

    public static void intSequenceEnd() {
        logLnRawString(formatter.anyPrimitiveType(int_counter));
        for (int i = 0; i < int_MAX_counter; i++) {
            logLnRawString(String.format("%s",Integer.MAX_VALUE));
        }
        int_counter = 0;
        int_MAX_counter = 0;
    }

    public static void byteSequenceEnd() {
        logLnRawString(formatter.anyPrimitiveType(byte_counter));
        for (int i = 0; i < byte_MAX_counter; i++) {
            logLnRawString(String.format("%s",Byte.MAX_VALUE));
        }
        byte_counter = 0;
        byte_MAX_counter = 0;
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

    public String stringsSequance(String last_string, int last_string_counter) {
        if (last_string_counter == 1) {
            return string_(last_string);
        } else {
            return String.format("%s (x%d)", last_string, last_string_counter);
        }
    }
}