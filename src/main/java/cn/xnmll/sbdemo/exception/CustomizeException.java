package cn.xnmll.sbdemo.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message = message;
    }
}
