package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO implements DAOInterface<Order>{
    @Override
    public ArrayList<Order> selectAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM orders";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idImport = rs.getInt("order_id");
                int idUser = rs.getInt("user_id");
                Double totalPrice = rs.getDouble("total_price");
                String userConsignee = rs.getString("nameConsignee");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int idPayment = rs.getInt("payment_id");
                String status = rs.getString("status");
                Date dateO = rs.getDate("booking_date");

                String note = rs.getString("note");
                User u = new UserDAO().selectById(idUser);
                Payment pay = new PaymentDAO().selectById(idPayment);
                Order order = new Order(idImport,u,totalPrice,userConsignee,phone,address,pay,status,dateO,note);


                orders.add(order);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;

    }

    @Override
    public Order selectById(int id) {
        Order result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM orders WHERE order_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idImport = rs.getInt("order_id");
                int idUser = rs.getInt("user_id");
                Double totalPrice = rs.getDouble("total_price");
                String userConsignee = rs.getString("nameConsignee");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int idPayment = rs.getInt("payment_id");
                String status = rs.getString("status");
                Date dateO = rs.getDate("booking_date");

                String note = rs.getString("note");
                User u = new UserDAO().selectById(idUser);
                Payment pay = new PaymentDAO().selectById(idPayment);
                result = new Order(idImport,u,totalPrice,userConsignee,phone,address,pay,status,dateO,note);

            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public int insert(Order order) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO orders(order_id, user_id,total_price,nameConsignee,phone,address,payment_id,status,booking_date,note)"
                    + "VALUE(?, ?, ?, ?,?,?,?,?,?,?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, order.getOrderId());
            rs.setInt(2, order.getUser().getUserId());
            rs.setDouble(3, order.getTotalPrice());
            rs.setString(4, order.getNameConsignee());
            rs.setString(5, order.getPhone());
            rs.setString(6, order.getAddress());
            rs.setDouble(7, order.getPayment().getPaymentId());
            rs.setString(8,order.getStatus());
            rs.setDate(9, order.getBookingDate());
            rs.setString(10, order.getNote());



            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    @Override
    public int insertAll(ArrayList<Order> list) {
        int result = 0;
        for (Order o : list) {

            ;
            if (this.insert(o) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Order order) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from orders " + "WHERE order_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, order.getOrderId());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Order> list) {
        int result = 0;

        for (Order o : list) {
            result += delete(o);
        }
        return result;
    }

    @Override
    public int update(Order order) {
        int result = 0;
        Order oldRating = this.selectById(order.getOrderId());

        if (oldRating != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE orders SET user_id=? " +

                        ", total_price=? " +
                        ", nameConsignee=? " +

                        ", phone=? " +
                        ", address=? " +
                        ", payment_id=? " +
                        ", status=? " +
                        ", booking_date=? " +

                        ", note=? " +
                        "WHERE order_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, order.getOrderId());
                rs.setInt(2, order.getUser().getUserId());
                rs.setDouble(3, order.getTotalPrice());
                rs.setString(4, order.getNameConsignee());
                rs.setString(5, order.getPhone());
                rs.setString(6, order.getAddress());
                rs.setDouble(7, order.getPayment().getPaymentId());
                rs.setString(8,order.getStatus());
                rs.setDate(9, (Date) order.getBookingDate());
                rs.setString(10, order.getNote());




                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
