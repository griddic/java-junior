package com.acme.edu;

/**
 * Created by Java_6 on 25.08.2016.
 */
public class CommonFormatter implements Formatter {
    private String chatTemplate = "char: %s";
    private String stringTemplate = "string: %s";
    private String obgectTemplate = "reference: %s";
    private String primitiveTemplate = "primitive: %s";
    private String stringSequenceTemplate = "%s (x%d)";
    private String primitivesArrayTemplate = "primitives array: %s";
    private String primitivesMatrixTemplate = "primitives matrix: {" + System.lineSeparator() + "%s}";

    public CommonFormatter () {

    }

    public CommonFormatter(String chatTemplate, String stringTemplate, String obgectTemplate, String primitiveTemplate, String stringSequenceTemplate, String primitivesArrayTemplate, String primitivesMatrixTemplate) {
        this.chatTemplate = chatTemplate;
        this.stringTemplate = stringTemplate;
        this.obgectTemplate = obgectTemplate;
        this.primitiveTemplate = primitiveTemplate;
        this.stringSequenceTemplate = stringSequenceTemplate;
        this.primitivesArrayTemplate = primitivesArrayTemplate;
        this.primitivesMatrixTemplate = primitivesMatrixTemplate;
    }

    public String decorateChar(char ch) {
        return String.format(chatTemplate, ch);
    }

    public String decorateString(String str) {
        return String.format(stringTemplate, str);
    }

    public String decorateObject(Object obj) {
        return String.format(obgectTemplate, obj);
    }

    public String decorateAnyPrimitiveType(Object obj) {
        return String.format(primitiveTemplate, obj);
    }

    public String decorateStringsSequence(String last_string, int last_string_counter) {
        if (last_string_counter == 1) {
            return decorateString(last_string);
        } else {
            return String.format(stringSequenceTemplate, last_string, last_string_counter);
        }
    }

    public String arrayToString(int[] array) {
        String result = ""; //= "{-1, 0, 1}";
        for (int i = 0; i < (array.length - 1); i++) {
            result = result + String.format("%d, ", array[i]);
        }
        result = result + String.format("%d", array[array.length - 1]);
        return String.format("{%s}", result);
    }

    public String decorArray(int[] array) {
        String message = arrayToString(array);
        return String.format(primitivesArrayTemplate, message);
    }

    public String decorMatrix(int[][] matrix) {
        String array = "";
        for (int i = 0; i <= (matrix.length - 1); i++) {
            array = array + this.arrayToString(matrix[i]) + System.lineSeparator();
        }
        return String.format(primitivesMatrixTemplate, array);
    }
}
