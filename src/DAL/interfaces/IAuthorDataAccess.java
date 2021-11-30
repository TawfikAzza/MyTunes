package DAL.interfaces;

import BE.Author;


import java.util.List;

public interface IAuthorDataAccess {
    /**
     * Get an Author from the database based on the id in parameter
     * Return an author
     * */
    Author getAuthor(int idAuthor) throws Exception;
    /**
     * Get all the author contained in the database
     * return a List of object author
     * */
    List<Author> getALlAuthors() throws Exception;
    /**
     * Create an Author in the Database
     * return the author object created.
     * */
    Author createAuthor(String authorName) throws Exception;
    /**
     * Update an existing author,
     * return nothing
     * */
    void updateAuthor(Author author) throws Exception;

    /**
     * Delete an existing author from the database
     *
     * */
    void deleteAuthor(Author author) throws Exception;
}
