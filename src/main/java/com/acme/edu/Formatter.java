package com.acme.edu;

/**
 * Created by Java_6 on 25.08.2016.
 */
public interface Formatter {
    public String decorateChar(char ch);

    public String decorateString(String str);

    public String decorateObject(Object obj) ;

    public String decorateAnyPrimitiveType(Object obj);

    public String decorateStringsSequence(String lastString, int lastStringCounter) ;

    public String arrayToString(int[] array) ;

    public String decorArray(int[] array) ;

    public String decorMatrix(int[][] matrix) ;
}
