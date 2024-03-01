package database;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO implements DAOInterface<Category>{
    @Override
    public ArrayList<Category> selectAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM categories";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("category_id");
                String name = rs.getString("category_name");

                Category category = new Category(id, name);
                categories.add(category);

            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category selectById(int id) {
        Category result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM book.categories WHERE category_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("category_id");
                String username = rs.getString("category_name");

                result = new Category(id1, username);


            }
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Category category) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO categories(category_id, category_name)"
                    + "VALUE(?, ?)";
            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, category.getCategoryId());
            rs.setString(2, category.getCategoryName());
            result = rs.executeUpdate();
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int insertAll(ArrayList<Category> list) {
        int result = 0;
        for (Category category : list) {
            if (this.insert(category) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Category category) {
        int result =0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from categories "
                    + "WHERE category_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, category.getCategoryId());



            result= rs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Category> list) {
        int result = 0;
        for (Category category : list) {
            result += delete(category);
        }
        return result;
    }

    @Override
    public int update(Category category) {
        int result = 0;
        Category oldCategory = this.selectById(category.getCategoryId());
        if(oldCategory !=null) {
            try {
                Connection con = JDBCUtil.getConnection();
                String sql = "UPDATE book.categories SET  category_name=? "+ "WHERE category_id =?";
                PreparedStatement rs = con.prepareStatement(sql);
                rs.setString(1, category.getCategoryName());
                rs.setInt(2, category.getCategoryId());
                result = rs.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categori = categoryDAO.selectAll();
        for(Category c : categori){
            System.out.println(c);
        }
        Category c1 = new CategoryDAO().selectById(1);
        System.out.println(c1);
    }
}
