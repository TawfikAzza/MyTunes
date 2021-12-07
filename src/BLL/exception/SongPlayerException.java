package BLL.exception;

public class SongPlayerException extends Throwable{
    private String message;
    public SongPlayerException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
