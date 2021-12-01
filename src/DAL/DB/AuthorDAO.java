package DAL.DB;

import BE.Author;
import DAL.ConnectionManager;
import DAL.interfaces.IAuthorDataAccess;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AuthorDAO implements IAuthorDataAccess {
    ConnectionManager cm;
    public AuthorDAO() throws Exception {
        cm = new ConnectionManager();
    }
    /**
     * Get an Author from the database based on the id in parameter
     * Return an author
     *
     * @param idAuthor
     */
    @Override
    public Author getAuthor(int idAuthor) throws Exception {
        Author authorSearched = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Author WHERE id=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1,idAuthor);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                authorSearched= new Author(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        }

        return authorSearched;
    }

    /**
     * Get all the author contained in the database
     * return a List of object author
     */
    @Override
    public List<Author> getALlAuthors() throws Exception {
        List<Author> allAuthors = new ArrayList();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Author;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allAuthors.add(new Author(
                        rs.getInt("id"),
                        rs.getString("name"))
                );
            }
        }

        return allAuthors;
    }

    /**
     * Create an Author in the Database
     * return the author object created.
     *
     * @param authorName
     */
    @Override
    public Author createAuthor(String authorName) throws Exception {
        Author authorCreated=null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO AUTHOR VALUE ('?');";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtSelect.setString(1,authorName);
            pstmtSelect.execute();
            ResultSet rs = pstmtSelect.getGeneratedKeys();
            while(rs.next())
            {
                authorCreated = new Author(
                        rs.getInt(1),
                        authorName
                );
            }
        }

        return authorCreated;
    }

    /**
     * Update an existing author,
     * return nothing
     *
     * @param author
     */
    @Override
    public void updateAuthor(Author author) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sqlcommandUpdate = "UPDATE AUTHOR SET name = ? WHERE id = ?;";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlcommandUpdate);
            pstmtUpdate.setString(1,author.getName());
            pstmtUpdate.setInt(2,author.getId());
            pstmtUpdate.executeUpdate();

        }

    }

    /**
     * Delete an existing author from the database
     *
     * @param author
     */
    @Override
    public void deleteAuthor(Author author) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sqlcommandDelete = "DELETE FROM AUTHOR WHERE id=?;";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlcommandDelete);
            pstmtDelete.setInt(1,author.getId());
            pstmtDelete.execute();

        }

    }
}
