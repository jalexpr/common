package ru.textanalysis.common.exception;

public class RuTextanalysisException extends Exception {
    public RuTextanalysisException() {
    }

    public RuTextanalysisException(String s) {
        super(s);
    }

    public RuTextanalysisException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RuTextanalysisException(Throwable throwable) {
        super(throwable);
    }

}
