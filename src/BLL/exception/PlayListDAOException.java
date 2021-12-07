package BLL.exception;

public class PlayListDAOException extends Throwable{
    private String message;
    public PlayListDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
