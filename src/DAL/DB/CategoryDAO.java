package DAL.DB;

import BE.CategorySong;
import DAL.ConnectionManager;
import DAL.interfaces.ICategorySongDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO implements ICategorySongDataAccess {
    ConnectionManager cm;
    public CategoryDAO() throws Exception {
        cm = new ConnectionManager();
    }
    @Override
    public CategorySong getCategorySong(int idCategory) throws Exception {
        return null;
    }

    @Override
    public List<CategorySong> getALlCategorySong() throws Exception {
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

        return allCategories;
    }

    @Override
    public CategorySong createCategorySong(CategorySong category) throws Exception{
        CategorySong categoryCreated = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO CATEGORY VALUE(?);";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setString(1, category.getName());
            pstmtInsert.executeQuery();
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            while(rs.next()) {
                categoryCreated = new CategorySong(rs.getInt(1),category.getName());
            }
         }

        return categoryCreated;
    }

    @Override
    public void updateCategorySong(CategorySong categorySong) throws Exception{
        try (Connection con = cm.getConnection()) {
            String sqlcommandUpdate = "UPDATE CATEGORY SET name = ? WHERE id = ?;";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlcommandUpdate);
            pstmtUpdate.setString(1,categorySong.getName());
            pstmtUpdate.setInt(2,categorySong.getId());
            pstmtUpdate.executeUpdate();

        }

    }

    @Override
    public void deleteCategorySong(CategorySong categorySong) throws Exception{
        try (Connection con = cm.getConnection()) {
            String sqlcommandDelete = "DELETE FROM CATEGORY WHERE id=?;";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlcommandDelete);
            pstmtDelete.setInt(1,categorySong.getId());
            pstmtDelete.execute();
        }

    }
}
