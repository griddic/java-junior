package com.acme.edu;

/**
 * Created by Java_6 on 25.08.2016.
 */
public class CommonFormatter implements Formatter {

    public String decorateChar(char ch) {
        return String.format("char: %s", ch);
    }

    public String decorateString(String str) {
        return String.format("string: %s", str);
    }

    public String decorateObject(Object obj) {
        return String.format("reference: %s", obj);
    }

    public String decorateAnyPrimitiveType(Object obj) {
        return String.format("primitive: %s", obj);
    }

    public String decorateStringsSequence(String last_string, int last_string_counter) {
        if (last_string_counter == 1) {
            return decorateString(last_string);
        } else {
            return String.format("%s (x%d)", last_string, last_string_counter);
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
        return String.format("primitives array: %s", message);
    }

    public String decorMatrix(int[][] matrix) {
        String result = "primitives matrix: {" + System.lineSeparator();
        for (int i = 0; i <= (matrix.length - 1); i++) {
            result = result + this.arrayToString(matrix[i]) + System.lineSeparator();
        }
        result = result + "}";
        return result;
    }
}
