package com.acme.edu;

/**
 * Created by Java_5 on 26.08.2016.
 */
public class LoggerFactory {
    public static Logger createTestLogger () {
        Logger logger = new Logger(
                new CommonFormatter("char: %s", "string: %s", "reference: %s",
                        "primitive: %s", "%s (x%d)", "primitives array: %s",
                        "primitives matrix: {" + System.lineSeparator() + "%s}"),
                new ConsoleOutput(),
                message -> System.out.println("Hello! " + message + " !!!!1!!"));
        return logger;
    }
}
