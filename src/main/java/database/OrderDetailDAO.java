package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailDAO implements DAOInterface<OrderDetail>{
    @Override
    public ArrayList<OrderDetail> selectAll() {
        ArrayList<OrderDetail> orderdetails = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM orderdetails";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idorderde = rs.getInt("detail_id");
                int idOrder = rs.getInt("order_id");
                int idProduct = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Double price = rs.getDouble("price");
                Order o = new OrderDAO().selectById(idOrder);


                Product pro = new ProductDAO().selectById(idProduct);
                OrderDetail orderdetail = new OrderDetail(idorderde,o,pro,quantity,price);


                orderdetails.add(orderdetail);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderdetails;
    }

    @Override
    public OrderDetail selectById(int id) {
        OrderDetail result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM orderdetails WHERE detail_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idorderde = rs.getInt("detail_id");
                int idOrder = rs.getInt("order_id");
                int idProduct = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Double price = rs.getDouble("price");
                Order o = new OrderDAO().selectById(idOrder);


                Product pro = new ProductDAO().selectById(idProduct);
                result = new OrderDetail(idorderde,o,pro,quantity,price);

            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(OrderDetail orderDetail) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO orderdetails(detail_id, order_id,product_id,quantity,price)"
                    + "VALUE(?, ?, ?, ?,?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, orderDetail.getDetailId());
            rs.setInt(2, orderDetail.getOrder().getOrderId());
            rs.setInt(3, orderDetail.getProduct().getProductId());
            rs.setInt(4, orderDetail.getQuantity());
            rs.setDouble(5, orderDetail.getPrice());




            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    @Override
    public int insertAll(ArrayList<OrderDetail> list) {
        int result = 0;
        for (OrderDetail o : list) {

            ;
            if (this.insert(o) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(OrderDetail orderDetail) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from orderdetails " + "WHERE detail_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1,orderDetail.getDetailId());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<OrderDetail> list) {
        int result = 0;

        for (OrderDetail o : list) {
            result += delete(o);
        }
        return result;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        int result = 0;
        OrderDetail oldRating = this.selectById(orderDetail.getDetailId());

        if (oldRating != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE orderdetails SET order_id=? " +

                        ", product_id=? " +
                        ", quantity=? " +

                        ", price=? " +

                        "WHERE detail_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, orderDetail.getDetailId());
                rs.setInt(2, orderDetail.getOrder().getOrderId());
                rs.setInt(3, orderDetail.getProduct().getProductId());
                rs.setInt(4, orderDetail.getQuantity());
                rs.setDouble(5, orderDetail.getPrice());




                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
