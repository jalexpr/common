package ru.textanalysis.common.util;

import ru.textanalysis.common.exception.RuTextanalysisException;
import ru.textanalysis.common.exception.RuTextanalysisRuntimeException;

import java.io.IOException;

public class Cmd {
    public static void applyCommand(final String command) throws RuTextanalysisException {
        Thread thread = new Thread(() -> createProcess(command));
        thread.start();
        waitUntilFinish(thread);
    }

    public static Process createProcess(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            return processBuilder.start();
        } catch (IOException ex) {
            throw new RuTextanalysisRuntimeException(ex);
        }
    }

    public static void waitUntilFinish(Thread thread) throws RuTextanalysisException {
        try {
            Thread.sleep(1000);
            while (thread.isAlive()) {
                Thread.sleep(10000);
            }
        } catch (InterruptedException ex) {
            throw new RuTextanalysisException(ex);
        }
    }
}
