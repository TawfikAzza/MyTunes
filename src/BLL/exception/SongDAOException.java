package BLL.exception;

public class SongDAOException extends Throwable{
    private String message;
    public SongDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
