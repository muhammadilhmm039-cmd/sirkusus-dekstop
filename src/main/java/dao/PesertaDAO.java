package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Peserta;

public class PesertaDAO {

    public boolean insert(
            Peserta peserta,
            String jenisKelamin,
            String email) throws SQLException {

        String sql = "INSERT INTO peserta "
                + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, peserta.getNim());
            ps.setString(2, peserta.getNama());
            ps.setString(3, jenisKelamin);
            ps.setString(4, peserta.getProdi());
            ps.setString(5, peserta.getNoHp());
            ps.setString(6, email);

            return ps.executeUpdate() == 1;
        }
    }
    public java.util.List<model.Peserta> getAll()
        throws java.sql.SQLException {

    java.util.List<model.Peserta> daftar =
            new java.util.ArrayList<>();

    String sql = "SELECT id, nama, no_hp, nim, prodi "
            + "FROM peserta ORDER BY id DESC";

    try (java.sql.Connection conn =
                 config.Database.getConnection();
         java.sql.PreparedStatement ps =
                 conn.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            model.Peserta peserta = new model.Peserta(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("no_hp"),
                    rs.getString("nim"),
                    rs.getString("prodi")
            );

            daftar.add(peserta);
        }
    }

    return daftar;
}
}