package BLL.exception;

public class CategorySongDAOException extends Throwable{
    private String message;
    public CategorySongDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
