package BLL.exception;

public class MyTunesManagerException extends Throwable {
    public MyTunesManagerException(String message, Exception e) {
        System.out.println(e);
    }
}
