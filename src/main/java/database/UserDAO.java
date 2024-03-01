package database;

import model.User;
import util.PasswordEncryption;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class UserDAO implements DAOInterface<User>{

    @Override
    public ArrayList<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM users ";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                String names = rs.getString("name");
                Date birthday = rs.getDate("birthday");
                String gt = rs.getString("sexual");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String avatar = rs.getString("avatar");
                User customer = new User(id, username, password,role_id, names, birthday, gt, phoneNumber, email, avatar);




                users.add(customer);

            }

            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectById(int id) {
        User result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM book.users WHERE user_id = ?";
            System.out.println(sql);
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("userId");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int role_id = rs.getInt("role_id");
                String name = rs.getString("name");
                Date birthday = rs.getDate("birthday");
                String gt = rs.getString("sexual");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String avatar = rs.getString("avatar");
                result = new User(id1, username, password,role_id, name, birthday, gt, phoneNumber, email, avatar);

            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public int insert(User user) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO book.users(user_id, username, password,role_id,name, birthday, sexual, phoneNumber, email, avatar)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, user.getUserId());
            rs.setString(2, user.getUsername());

            String passwordS = PasswordEncryption.toSHA1(user.getPassword());

            rs.setString(3, passwordS);
            rs.setInt(4,user.getRole());
            rs.setString(5, user.getName());
            rs.setDate(6, (Date) user.getBirthday());
            rs.setString(7, user.getSexual());
            rs.setString(8, user.getPhone());
            rs.setString(9, user.getEmail());
            rs.setString(10, user.getAvatar());
            System.out.println(user);

            result = rs.executeUpdate();
            System.out.println("da them vao");
            JDBCUtil.closeConnection(con);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;


    }

    @Override
    public int insertAll(ArrayList<User> list) {
        int result = 0;
        for (User customer : list) {

            ;
            if (this.insert(customer) == 1)
                result += 1;
        }
        return result;

    }

    @Override
    public int delete(User user) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from users " + "WHERE user_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, user.getUserId());

            result = rs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public int deleteAll(ArrayList<User> list) {
        int result = 0;

        for (User user : list) {
            result += delete(user);
        }
        return result;

    }

    @Override
    public int update(User user) {
        int result = 0;
        User oldUser = this.selectById(user.getUserId());
        if (oldUser != null) {
            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE book.users SET  username=? " + ", password=? " + ", role_id=?" + ", name=? " + ", birthday=? "
                        + ", sexual=? " + ", phoneNumber=? " + ", email=? " + ", avatar=? " + "WHERE user_id =?";

                PreparedStatement rs = con.prepareStatement(sql);
                rs.setInt(1, user.getUserId());
                rs.setString(2, user.getUsername());

                String passwordS = PasswordEncryption.toSHA1(user.getPassword());
                rs.setString(3, passwordS);
                rs.setInt(4, user.getRole());
                rs.setString(5, user.getName());
                rs.setDate(6, (Date) user.getBirthday());
                rs.setString(7, user.getSexual());
                rs.setString(8, user.getPhone());
                rs.setString(9, user.getEmail());
                rs.setString(10, user.getAvatar());

                result = rs.executeUpdate();
                System.out.println("done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> userl =userDAO.selectAll();
        for(User u : userl){
            System.out.println(u);
        }
    }
    }
