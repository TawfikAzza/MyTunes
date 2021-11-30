package BLL.exception;

public class SongPlayerException extends Throwable{
    public SongPlayerException(String message, Exception e) {
        System.out.println(e);
    }
}
