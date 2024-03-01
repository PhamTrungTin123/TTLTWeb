package database;

import model.Comment;
import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class CommentDAO implements DAOInterface<Comment> {

    @Override
    public ArrayList<Comment> selectAll() {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM comments";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idcomment = rs.getInt("comment_id");
                int idproduct = rs.getInt("product_id");
                int iduser = rs.getInt("user_id");
                String detailcomment = rs.getString("detail_comment");
                Date datec = rs.getDate("date_comment");
                Product pr = new ProductDAO().selectById(idproduct);
                User use = new UserDAO().selectById(iduser);

                Comment co = new Comment(idcomment,pr,use,detailcomment,datec);


                comments.add(co);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public Comment selectById(int id) {
        Comment result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM comments WHERE comment_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idcomment = rs.getInt("comment_id");
                int idproduct = rs.getInt("product_id");
                int iduser = rs.getInt("user_id");
                String detailcomment = rs.getString("detail_comment");
                Date datec = rs.getDate("date_comment");
                Product pr = new ProductDAO().selectById(idproduct);
                User use = new UserDAO().selectById(iduser);
                result = new Comment(idcomment,pr,use,detailcomment,datec);

            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Comment comment) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO comments(comment_id,product_id,user_id,detail_comment,date_comment)"
                    + "VALUE(?, ?, ?, ?, ?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, comment.getCommentid());
            rs.setInt(2,comment.getProduct().getProductId());
            rs.setInt(3,comment.getUser().getUserId());
            rs.setString(4,comment.getDetailComment());
            rs.setDate(5, (Date) comment.getDateComment());

            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int insertAll(ArrayList<Comment> list) {
        int result = 0;
        for (Comment co : list) {

            ;
            if (this.insert(co) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Comment comment) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from comments " + "WHERE comment_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, comment.getCommentid());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Comment> list) {
        int result = 0;

        for (Comment co : list) {
            result += delete(co);
        }
        return result;
    }

    @Override
    public int update(Comment comment) {
        int result = 0;
        Comment oldProduct = this.selectById(comment.getCommentid());

        if (oldProduct != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE comments SET product_id=? " +
                        ", user_id=? " +
                        ", detail_comment=? " +
                        ", date_comment=? " +

                        "WHERE comment_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, comment.getCommentid());
                rs.setInt(2,comment.getProduct().getProductId());
                rs.setInt(3,comment.getUser().getUserId());
                rs.setString(4,comment.getDetailComment());
                rs.setDate(5, (Date) comment.getDateComment());


                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
