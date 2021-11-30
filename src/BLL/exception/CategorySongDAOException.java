package BLL.exception;

public class CategorySongDAOException extends Throwable{
    public CategorySongDAOException(String message, Exception e) {
        System.out.println(e);
    }
}
