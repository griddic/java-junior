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
                new OutStreamToConsole(),
                new OutStreamToFile("logger.log"),
                new OutStreamToLoggingServer("localhost", 1111));
        return logger;
    }

    public static Logger consoleOutOnlyLogger () {
        return new Logger(new CommonFormatter(), new OutStreamToConsole());
    }

    public static Logger fileOnlyLogger (String fileName) {
        return new Logger (new CommonFormatter(), new OutStreamToFile(fileName));
    }
}
