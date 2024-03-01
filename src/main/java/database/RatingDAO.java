package database;

import model.Product;
import model.Rating;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RatingDAO implements DAOInterface<Rating>{
    @Override
    public ArrayList<Rating> selectAll() {
        ArrayList<Rating> ratings = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM rating";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idrating = rs.getInt("rating_id");
                int idproduct = rs.getInt("product_id");
                int iduser = rs.getInt("user_id");
                int numberating = rs.getInt("rating");
                Product pr = new ProductDAO().selectById(idproduct);
                User use = new UserDAO().selectById(iduser);

              Rating rating = new Rating(idrating,pr,use,numberating);


                ratings.add(rating);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratings;
    }

    @Override
    public Rating selectById(int id) {
        Rating result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM rating WHERE rating_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idrating = rs.getInt("rating_id");
                int idproduct = rs.getInt("product_id");
                int iduser = rs.getInt("user_id");
                int numberating = rs.getInt("rating");
                Product pr = new ProductDAO().selectById(idproduct);
                User use = new UserDAO().selectById(iduser);
                result = new Rating(idrating,pr,use,numberating);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public int insert(Rating rating) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO rating(rating_id, product_id,user_id,rating)"
                    + "VALUE(?, ?, ?, ?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, rating.getRatingId());
            rs.setInt(2, rating.getProduct().getProductId());
            rs.setInt(3, rating.getUser().getUserId());
            rs.setInt(4, rating.getRating());


            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }



    @Override
    public int insertAll(ArrayList<Rating> list) {
        int result = 0;
        for (Rating rat : list) {

            ;
            if (this.insert(rat) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Rating rating) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from rating " + "WHERE rating_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, rating.getRatingId());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Rating> list) {
        int result = 0;

        for (Rating rat : list) {
            result += delete(rat);
        }
        return result;
    }


    @Override
    public int update(Rating rating) {
        int result = 0;
        Rating oldRating = this.selectById(rating.getRatingId());

        if (oldRating != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE rating SET  product_id=? " +
                        ", user_id=? " +

                        ", rating=? " +
                        "WHERE rating_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, rating.getRatingId());
                rs.setInt(2, rating.getProduct().getProductId());
                rs.setInt(3, rating.getUser().getUserId());
                rs.setInt(4, rating.getRating());




                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
    }

