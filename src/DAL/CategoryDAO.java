package DAL;

import BE.Author;
import BE.CategorySong;
import DAL.interfaces.ICategorySongDataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO implements ICategorySongDataAccess {
    ConnectionManager cm;
    public CategoryDAO() throws IOException {
        cm = new ConnectionManager();
    }
    @Override
    public CategorySong getCategorySong(int idCategory) {
        return null;
    }

    @Override
    public List<CategorySong> getALlCategorySong() {
        List<CategorySong> allCategories = new ArrayList();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM CATEGORY;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allCategories.add(new CategorySong(
                        rs.getInt("id"),
                        rs.getString("name"))
                );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategories;
    }

    @Override
    public CategorySong createCategorySong(CategorySong category) {
        return null;
    }

    @Override
    public void updateCategorySong(int idCategory) {

    }

    @Override
    public void deleteCategorySong(int idCategory) {

    }
}
