public class ExceptionCollector {

    private Exception exception;

    private static ExceptionCollector exceptionCollector = new ExceptionCollector();

    public static void clear() {
        exceptionCollector.exception = null;
    }

    private Exception getException() {
        return this.exception;
    }

    private void setException(Exception e) {
        this.exception = e;
    }

    public static Exception getExceptionInstance() {
        return exceptionCollector.getException();
    }

    public static void setExceptionInstance(Exception e) {
        exceptionCollector.setException(e);
    }
}
