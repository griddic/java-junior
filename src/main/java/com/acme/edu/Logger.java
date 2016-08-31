package com.acme.edu;

public class Logger {
    private OutputsContainer outStreams;
    private Formatter formatter;

    private long summatorForIntSequence = 0;
    private int ammountOfMaxIntegerOverflow = 0;

    private int summatorForByteSequence = 0;
    private int ammountOfMaxByteOverflow = 0;

    private String lastStringInStringsSequence = null;
    private int quantityOfLastStringRepeated = 0;

    /**
     * This is constructor method for Logger.
     * @param formatter allows to configure decoration for data being logged.
     * @param outStreams allows to configure one ore many output streams.
     */
    public Logger(Formatter formatter, OutStream... outStreams) {
        this.outStreams = new OutputsContainer(outStreams);
        this.formatter = formatter;
    }


    private void logRawString(String message) {
        try {
            outStreams.write(message);
        }
        catch (OutStreamUnableToOpenException e) {
            e.printStackTrace();
        }
        catch (OutStreamUnableToWriteException e) {
            e.printStackTrace();
        }
    }


    private void logLnRawString(String message) {
        logRawString(message + System.lineSeparator());
    }

    /** Method log (int) accumulates values for integer sequences.
     * Input data must be "int" type.
     * Method accumulates sequenced integers.
     * To show accumulated data, in the end of any integer sequence intSequenceEnd() method must be called.
     */
    public void log(int number) {
        summatorForIntSequence += number;
        int additionToOverflow = (int) (summatorForIntSequence/Integer.MAX_VALUE);
        ammountOfMaxIntegerOverflow += additionToOverflow;
        summatorForIntSequence = summatorForIntSequence - (Integer.MAX_VALUE * additionToOverflow);
    }

    /** Method log (byte) accumulates values for byte sequences.
     * Input data must be "byte" type.
     * Method accumulates sequenced integers.
     * To show accumulated data, in the end of any byte sequence byteSequenceEnd() method must be called.
     */
    public void log(byte number) {
        int additionToOverflow;
        summatorForByteSequence += number;
        additionToOverflow = (summatorForByteSequence /Byte.MAX_VALUE);
        ammountOfMaxByteOverflow += additionToOverflow;
        summatorForByteSequence = summatorForByteSequence - (Byte.MAX_VALUE * additionToOverflow);
    }

    /** This method logs char data with configured decoration.
     * Input data must be "char" type.
     */
    public void log(char message) {
        logLnRawString(formatter.decorateChar(message));
    }

    /** Method log (string) accumulates number of repeating string for string sequences.
     * Input data must be "String" type.
     * To show accumulated data, in the end of any string sequence strSequenceEnd() method must be called.
     */
    public void log(String message) {
        if (message.equals(lastStringInStringsSequence)) {
            ++quantityOfLastStringRepeated;
        } else {
            if (quantityOfLastStringRepeated > 0) {
                logLnRawString(formatter.decorateStringsSequence(lastStringInStringsSequence, quantityOfLastStringRepeated));
            } // else in this case is a first string in sequance.
            lastStringInStringsSequence = message;
            quantityOfLastStringRepeated = 1;
        }
    }


    /**
     * Method strSequenceEnd outputs accumulated data for String sequences.
     * This method will log sequenced repeating strings as one line with number of repeats with configured decoration.
     */
    public void strSequenceEnd() {
        logLnRawString(formatter.decorateStringsSequence(lastStringInStringsSequence, quantityOfLastStringRepeated));
        lastStringInStringsSequence = null;
        quantityOfLastStringRepeated = 0;
    }

    /** This method logs boolean data with configured decoration.
     * Input data must be "boolean" type.
     */
    public void log(boolean message) {
        logLnRawString(formatter.decorateAnyPrimitiveType(message));
    }

    /** This method logs object data with configured decoration.
     * Input data must be "object" type.
     */
    public void log(Object message) {
        logLnRawString(formatter.decorateObject(message));
    }

    /**
     * Method intSequenceEnd outputs accumulated data for int sequences with configured decoration.
     * This method will log sequenced integers as their sum.
     * If sum is greater, than MAX_Integer, method will output tail after dividing sum by MAX_Integer and than a number of MAX_Integers that makes the correct sum.
     */
    public void intSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(summatorForIntSequence));
        for (int i = 0; i < ammountOfMaxIntegerOverflow; i++) {
            logLnRawString(String.format("%s",Integer.MAX_VALUE));
        }
        summatorForIntSequence = 0;
        ammountOfMaxIntegerOverflow = 0;
    }

    /**
     * Method byteSequenceEnd outputs accumulated data for byte sequences with configured decoration.
     * This method will log sequenced byte numbers as their sum.
     * If sum is greater, than MAX_Byte, method will output tail after dividing sum by MAX_Byte and than a number of MAX_Bytes that makes the correct sum.
     */
    public void byteSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(summatorForByteSequence));
        for (int i = 0; i < ammountOfMaxByteOverflow; i++) {
            logLnRawString(String.format("%s",Byte.MAX_VALUE));
        }
        summatorForByteSequence = 0;
        ammountOfMaxByteOverflow = 0;
    }

    /** This method logs integer arrays with configured decoration.
     * Input data must be "int []" type.
     */
    public void log(int[] array) {
        logLnRawString(formatter.decorArray(array));
    }

    /** This method logs integer matrixes with configured decoration.
     * Input data must be "int [][]" type.
     */
    public void log(int[][] matrix) {
        logLnRawString(formatter.decorMatrix(matrix));
    }
}

