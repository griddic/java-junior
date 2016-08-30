package com.acme.edu.UnitTests;
import static org.fest.assertions.Assertions.assertThat; // main one

import com.acme.edu.CommonFormatter;
import com.acme.edu.Formatter;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;
import org.junit.Test;

/**
 * Created by Java_5 on 26.08.2016.
 */
public class CommonFormatterTest {
    Formatter formatter = new CommonFormatter("char: %s", "string: %s", "reference: %s",
            "primitive: %s", "%s (x%d)", "primitives array: %s",
            "primitives matrix: {" + System.lineSeparator() + "%s}");

    @Test
    public void checkDefaultConstructor() {
        Formatter defaultFormatter = new CommonFormatter();
        assertThat(defaultFormatter).isNotNull();
    }

    @Test
    public void decorateIntPrimitive () {
        assertThat(formatter.decorateAnyPrimitiveType(5)).isEqualTo("primitive: 5");
    }

    @Test
    public void decorateChar () {
        assertThat(formatter.decorateChar('a')).isEqualTo("char: a");
    }

    @Test
    public void decorateString () {
        assertThat(formatter.decorateString("Hello world!")).isEqualTo("string: Hello world!");
    }

    @Test
    public void decorateStrings () {
        assertThat(formatter.decorateStringsSequence("Hello world!", 2)).isEqualTo("Hello world! (x2)");
    }

    @Test
    public void decorateStringsSequanceWithOnlyOneString () {
        assertThat(formatter.decorateStringsSequence("Hello world!", 1)).isEqualTo("string: Hello world!");
    }

    @Test
    public void decorateObject () {
        assertThat(formatter.decorateObject(5)).isEqualTo("reference: 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDecorateLong () {
        formatter.decorateObject(12873219877391283L);
    }

    @Test
    public void decorateArray () {
        assertThat(formatter.decorArray(new int[] {1,2,3})).isEqualTo("primitives array: {1, 2, 3}");
    }

    @Test
    public void decorateMatrix () {
        assertThat(formatter.decorMatrix(new int[][] {{1,2},{3,4}})).isEqualTo("primitives matrix: {" + System.lineSeparator() +
                "{1, 2}"  + System.lineSeparator() +
                "{3, 4}"  + System.lineSeparator() +
                "}");
    }

}
