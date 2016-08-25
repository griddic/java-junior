package com.acme.edu;

public class Logger {
    static OutStream outStream;
    static Formatter formatter = new CommonFormatter();

    static long int_counter = 0;
    static int int_MAX_counter = 0;

    static int byte_counter = 0;
    static int byte_MAX_counter = 0;

    static String last_string = null;
    static int last_string_counter = 0;

    public Logger(OutStream outStream) {
        this.outStream = outStream;
    }

    public static void logRawString(String message) {
        outStream.write(message);
    }


    public static void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    public static void log(int number) {
        int overflow_index;
        int_counter += number;
        overflow_index = (int) (int_counter/Integer.MAX_VALUE);
        int_MAX_counter += overflow_index;
        int_counter = int_counter - (Integer.MAX_VALUE * overflow_index);
        //logLnRawString(formatter.decorateAnyPrimitiveType(message));
    }

    public static void log(byte number) {
        int overflow_index;
        byte_counter += number;
        overflow_index = (int) (byte_counter/Byte.MAX_VALUE);
        byte_MAX_counter += overflow_index;
        byte_counter = byte_counter - (Byte.MAX_VALUE * overflow_index);
        //logLnRawString(formatter.decorateAnyPrimitiveType(message));
    }

    public static void log(char message) {
        logLnRawString(formatter.decorateChar(message));
    }

    public static void log(String message) {
        if (message.equals(last_string)) {
            ++last_string_counter;
        } else {
            if (last_string_counter > 0) {
                logLnRawString(formatter.decorateStringsSequence(last_string, last_string_counter));
            } // else in this case is a first string in sequance.
            last_string = message;
            last_string_counter = 1;
        }
        // logLnRawString(formatter.decorateString(message));
    }

    public static void strSequenceEnd() {
        logLnRawString(formatter.decorateStringsSequence(last_string, last_string_counter));
        last_string = null;
        last_string_counter = 0;
    }

        public static void log(boolean message) {
        logLnRawString(formatter.decorateAnyPrimitiveType(message));
    }
    public static void log(Object message) {
        logLnRawString(formatter.decorateObject(message));
    }

    public static void intSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(int_counter));
        for (int i = 0; i < int_MAX_counter; i++) {
            logLnRawString(String.format("%s",Integer.MAX_VALUE));
        }
        int_counter = 0;
        int_MAX_counter = 0;
    }

    public static void byteSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(byte_counter));
        for (int i = 0; i < byte_MAX_counter; i++) {
            logLnRawString(String.format("%s",Byte.MAX_VALUE));
        }
        byte_counter = 0;
        byte_MAX_counter = 0;
    }

    public static void log(int[] array) {
        logLnRawString(formatter.decorArray(array));
    }

    public static void log(int[][] matrix) {
        logLnRawString(formatter.decorMatrix(matrix));
    }
}