package BLL.exception;

public class SongException extends Throwable{
    public SongException(String message, Exception e) {
        System.out.println(e);
    }
}
