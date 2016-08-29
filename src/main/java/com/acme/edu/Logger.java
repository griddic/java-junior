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
     * In the end of any integer sequence intSequenceEnd() method must be called.
     */
    public void log(int number) {
        summatorForIntSequence += number;
        int additionToOverflow = (int) (summatorForIntSequence /Integer.MAX_VALUE);
        ammountOfMaxIntegerOverflow += additionToOverflow;
        summatorForIntSequence = summatorForIntSequence - (Integer.MAX_VALUE * additionToOverflow);
    }

    /** Method log (byte) accumulates values for byte sequences.
     * In the end of any byte sequence byteSequenceEnd() method must be called.
     */
    public void log(byte number) {
        int additionToOverflow;
        summatorForByteSequence += number;
        additionToOverflow = (int) (summatorForByteSequence /Byte.MAX_VALUE);
        ammountOfMaxByteOverflow += additionToOverflow;
        summatorForByteSequence = summatorForByteSequence - (Byte.MAX_VALUE * additionToOverflow);
    }

    /** This method logs char data.
     *
     */
    public void log(char message) {
        logLnRawString(formatter.decorateChar(message));
    }

    /** Method log (string) accumulates number of repeating string for string sequences.
     * In the end of any string sequence strSequenceEnd() method must be called.
     *
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


    public void strSequenceEnd() {
        logLnRawString(formatter.decorateStringsSequence(lastStringInStringsSequence, quantityOfLastStringRepeated));
        lastStringInStringsSequence = null;
        quantityOfLastStringRepeated = 0;
    }

        public void log(boolean message) {
        logLnRawString(formatter.decorateAnyPrimitiveType(message));
    }

    public void log(Object message) {
        logLnRawString(formatter.decorateObject(message));
    }

    public void intSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(summatorForIntSequence));
        for (int i = 0; i < ammountOfMaxIntegerOverflow; i++) {
            logLnRawString(String.format("%s",Integer.MAX_VALUE));
        }
        summatorForIntSequence = 0;
        ammountOfMaxIntegerOverflow = 0;
    }

    public void byteSequenceEnd() {
        logLnRawString(formatter.decorateAnyPrimitiveType(summatorForByteSequence));
        for (int i = 0; i < ammountOfMaxByteOverflow; i++) {
            logLnRawString(String.format("%s",Byte.MAX_VALUE));
        }
        summatorForByteSequence = 0;
        ammountOfMaxByteOverflow = 0;
    }

    public void log(int[] array) {
        logLnRawString(formatter.decorArray(array));
    }

    public void log(int[][] matrix) {
        logLnRawString(formatter.decorMatrix(matrix));
    }
}