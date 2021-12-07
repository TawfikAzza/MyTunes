package BLL.exception;

public class AuthorDAOException extends Throwable{
    private String message;
    public AuthorDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
