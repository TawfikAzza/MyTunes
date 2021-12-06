package BLL.exception;

public class SongPlayerException extends Throwable{
    private String message;
    public SongPlayerException(String message, Exception e) {
        this.message = message;
        System.out.println(e);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
