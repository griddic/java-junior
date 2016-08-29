package com.acme.edu;


/**
 * Created by Java_6 on 25.08.2016.
 */
public class CommonFormatter implements Formatter {
    private String chatTemplate;
    private String stringTemplate;
    private String obgectTemplate;
    private String primitiveTemplate;
    private String stringSequenceTemplate;
    private String primitivesArrayTemplate;
    private String primitivesMatrixTemplate;

    public CommonFormatter () {
        this("char: %s", "string: %s", "reference: %s",
                "primitive: %s", "%s (x%d)", "primitives array: %s",
                "primitives matrix: {" + System.lineSeparator() + "%s}");
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

    @Override
    public String decorateChar(char ch) {
        return String.format(chatTemplate, ch);
    }

    @Override
    public String decorateString(String str) {
        return String.format(stringTemplate, str);
    }

    @Override
    public String decorateObject(Object obj) {
        return String.format(obgectTemplate, obj);
    }

    @Override
    public String decorateAnyPrimitiveType(Object obj) {
        return String.format(primitiveTemplate, obj);
    }

    @Override
    public String decorateStringsSequence(String lastString, int lastStringCounter) {
        if (lastStringCounter == 1) {
            return decorateString(lastString);
        } else {
            return String.format(stringSequenceTemplate, lastString, lastStringCounter);
        }
    }

    @Override
    public String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < (array.length - 1); i++) {
            result.append(String.format("%d, ", array[i]));
        }
        result.append(String.format("%d", array[array.length - 1]));
        return String.format("{%s}", result);
    }

    @Override
    public String decorArray(int[] array) {
        String message = arrayToString(array);
        return String.format(primitivesArrayTemplate, message);
    }

    @Override
    public String decorMatrix(int[][] matrix) {
        StringBuilder array = new StringBuilder("");
        for (int i = 0; i <= (matrix.length - 1); i++) {
            array.append(this.arrayToString(matrix[i]) + System.lineSeparator());
        }
        return String.format(primitivesMatrixTemplate, array);
    }
}
