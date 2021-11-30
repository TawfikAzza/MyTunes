package BLL.exception;

public class AuthorDAOException extends Throwable{
    public AuthorDAOException(String message, Exception e) {
        System.out.println(e);
    }
}
