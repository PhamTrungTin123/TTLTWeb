package database;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAOInterface<Product> {
    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM products";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idProduct = rs.getInt("product_id");
                String nameProduct = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double unitPrice = rs.getDouble("unit_price");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                int publicationYear = rs.getInt("publication_year");
                String publisher = rs.getString("publisher");
                int categoryId = rs.getInt("category_id");

                Category category = new CategoryDAO().selectById(categoryId);
                Product product = new Product(idProduct, nameProduct, description, image, unitPrice, price, quantity, author, publicationYear, publisher, category);


                products.add(product);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public ArrayList<Product> selectAllOrderBy() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql

            String sql = "SELECT * FROM products";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idProduct = rs.getInt("product_id");
                String nameProduct = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double unitPrice = rs.getDouble("unit_price");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                int publicationYear = rs.getInt("publication_year");
                String publisher = rs.getString("publisher");
                int categoryId = rs.getInt("category_id");

                Category category = new CategoryDAO().selectById(categoryId);
                Product product = new Product(idProduct, nameProduct, description, image, unitPrice, price, quantity, author, publicationYear, publisher, category);


                products.add(product);

            }

            JDBCUtil.closeConnection(con);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public ArrayList<Product> selectAllOrderBytt(String orderBy) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM products";
            if ("price-asc".equals(orderBy)) {
                sql += " ORDER BY price";
            } else if ("price-desc".equals(orderBy)) {
                sql += " ORDER BY price DESC";
            } else {
                // mặc định sắp xếp theo ID sản phẩm
                sql += " ORDER BY CAST(product_id AS SIGNED)";
            }
            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idProduct = rs.getInt("product_id");
                String nameProduct = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double unitPrice = rs.getDouble("unit_price");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                int publicationYear = rs.getInt("publication_year");
                String publisher = rs.getString("publisher");
                int categoryId = rs.getInt("category_id");

                Category category = new CategoryDAO().selectById(categoryId);
                Product product = new Product(idProduct, nameProduct, description, image, unitPrice, price, quantity, author, publicationYear, publisher, category);


                products.add(product);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> arr = new ArrayList<Product>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    @Override
    public Product selectById(int id) {
        Product result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM products WHERE product_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("product_id");
                String nameProduct = rs.getString("product_name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double unitPrice = rs.getDouble("unit_price");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String author = rs.getString("author");
                int publicationYear = rs.getInt("publication_year");
                String publisher = rs.getString("publisher");
                int categoryId = rs.getInt("category_id");

                Category category = new CategoryDAO().selectById(categoryId);
                result = new Product(idProduct, nameProduct, description, image, unitPrice, price, quantity, author, publicationYear, publisher, category);

            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Product product) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO products(product_id, product_name, description, image, unit_price, price, author,publication_year,publisher,category_id)"
                    + "VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, product.getProductId());
            rs.setString(2, product.getProduct_name());
            rs.setString(3, product.getDescription());
            rs.setString(4, product.getImage());
            rs.setDouble(5, product.getUnitPrice());
            rs.setDouble(6, product.getPrice());
            rs.setInt(7, product.getQuantity());
            rs.setString(8, product.getAuthor());
            rs.setInt(9, product.getPublicationYear());
            rs.setString(10, product.getPublisher());
            rs.setInt(11, product.getCategory().getCategoryId());

            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int insertAll(ArrayList<Product> list) {
        int result = 0;
        for (Product product : list) {

            ;
            if (this.insert(product) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Product product) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from products " + "WHERE product_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, product.getProductId());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Product> list) {
        int result = 0;

        for (Product product : list) {
            result += delete(product);
        }
        return result;
    }

    @Override
    public int update(Product product) {
        int result = 0;
        Product oldProduct = this.selectById(product.getProductId());

        if (oldProduct != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE pizza.products SET  product_name=? " +
                        ", description=? " +
                        ", image=? " +
                        ", unit_price=? " +
                        ", price=? " +
                        ", quantity=?" +
                        ", author=?" +
                        ", publication_year=? " +
                        ", publisher=? " +
                        ", category_id=? " +
                        "WHERE product_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, product.getProductId());
                rs.setString(2, product.getProduct_name());
                rs.setString(3, product.getDescription());
                rs.setString(4, product.getImage());
                rs.setDouble(5, product.getUnitPrice());
                rs.setDouble(6, product.getPrice());
                rs.setInt(7, product.getQuantity());
                rs.setString(8, product.getAuthor());
                rs.setInt(9, product.getPublicationYear());
                rs.setString(10, product.getPublisher());
                rs.setInt(11, product.getCategory().getCategoryId());


                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
