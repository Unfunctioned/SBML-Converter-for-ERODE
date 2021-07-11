package sbml.test.framework;

public class ExceptionCollector {

    private Exception exception;

    private static ExceptionCollector exceptionCollector = new ExceptionCollector();

    public static void clear() {
        exceptionCollector.exception = null;
    }

    public static ExceptionCollector getInstance() {
        return exceptionCollector;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception e) {
        this.exception = e;
    }
}
