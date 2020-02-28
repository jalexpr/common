package ru.textanalysis.common.exception;

public class RuTextanalysisRuntimeException extends RuntimeException {
    public RuTextanalysisRuntimeException() {
    }

    public RuTextanalysisRuntimeException(String s) {
        super(s);
    }

    public RuTextanalysisRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RuTextanalysisRuntimeException(Throwable throwable) {
        super(throwable);
    }

}
