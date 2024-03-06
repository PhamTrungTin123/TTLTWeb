package database;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class ImportDAO implements DAOInterface<Import> {
    @Override
    public ArrayList<Import> selectAll() {
        ArrayList<Import> imports = new ArrayList<>();
        try {
            // tao mot connection
            Connection con = JDBCUtil.getConnection();

            // tao cau lenh sql
            String sql = "SELECT * FROM imports";

            PreparedStatement st = con.prepareStatement(sql);

            // thuc thi

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idImport = rs.getInt("import_id");
                String supplier = rs.getString("supplier");
                String note = rs.getString("note");
                Date dateI = rs.getDate("import_date");
                Import im = new Import(idImport,supplier,note,dateI);


                imports.add(im);

            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imports;
    }

    @Override
    public Import selectById(int id) {
       Import result = null;

        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM imports WHERE import_id =?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idImport = rs.getInt("import_id");
                String supplier = rs.getString("supplier");
                String note = rs.getString("note");
                Date dateI = rs.getDate("import_date");

                result = new Import(idImport,supplier,note,dateI);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int insert(Import imported) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO imports(import_id, supplier,note,import_date)"
                    + "VALUE(?, ?, ?, ?)";

            PreparedStatement rs = con.prepareStatement(sql);

            rs.setInt(1, imported.getImportId());
            rs.setString(2, imported.getSupplier());
            rs.setString(3, imported.getNote());
            rs.setDate(4,  imported.getImportDate());


            result = rs.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int insertAll(ArrayList<Import> list) {
        int result = 0;
        for (Import im : list) {

            ;
            if (this.insert(im) == 1)
                result += 1;
        }
        return result;
    }

    @Override
    public int delete(Import imported) {
        int result = 0;

        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE from imports " + "WHERE import_id=?";

            PreparedStatement rs = con.prepareStatement(sql);
            rs.setInt(1, imported.getImportId());

            result = rs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteAll(ArrayList<Import> list) {
        int result = 0;

        for (Import im : list) {
            result += delete(im);
        }
        return result;
    }

    @Override
    public int update(Import imported) {
        int result = 0;
        Import oldRating = this.selectById(imported.getImportId());

        if (oldRating != null) {

            try {
                Connection con = JDBCUtil.getConnection();

                String sql = "UPDATE imports SET  supplier=? " +
                        ", note=? " +

                        ", import_date=? " +
                        "WHERE import_id = ?";

                PreparedStatement rs = con.prepareStatement(sql);

                rs.setInt(1, imported.getImportId());
                rs.setString(2, imported.getSupplier());
                rs.setString(3, imported.getNote());
                rs.setDate(4, (Date) imported.getImportDate());



                result = rs.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
