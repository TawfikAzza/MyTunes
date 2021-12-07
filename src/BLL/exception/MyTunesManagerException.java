package BLL.exception;

public class MyTunesManagerException extends Throwable {
    private String message;
    public MyTunesManagerException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
