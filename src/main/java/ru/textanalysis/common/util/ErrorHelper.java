package ru.textanalysis.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ErrorHelper {
    public static String getPrettyError(Throwable e) {
        return getPrettyError(null, e, true);
    }

    public static String getPrettyError(String message, Throwable e) {
        return getPrettyError(message, e, true);
    }

    public static String getPrettyErrorShort(Throwable e) {
        return getPrettyError(null, e, false);
    }

    public static String getPrettyErrorShort(String message, Throwable e) {
        return getPrettyError(message, e, false);
    }

    private static String getPrettyError(String message, Throwable e, boolean withStacktrace) {
        return String.format("%s%s",
                StringUtils.isBlank(message) ? "" : (message + (withStacktrace ? "\n" : " ")),
                withStacktrace ? ExceptionUtils.getStackTrace(e)
                        : String.format("%s -> %s", e.getClass().getSimpleName(), e.getMessage())
        );
    }
}

