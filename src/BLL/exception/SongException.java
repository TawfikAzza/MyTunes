package BLL.exception;

public class SongException extends Throwable{
    private String message;
    public SongException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
