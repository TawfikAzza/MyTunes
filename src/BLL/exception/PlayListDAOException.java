package BLL.exception;

public class PlayListDAOException extends Throwable{
    public PlayListDAOException(String message, Exception e) {
        System.out.println(e);
    }
}
