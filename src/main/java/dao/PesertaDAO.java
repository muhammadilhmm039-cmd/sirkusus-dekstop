
package dao;

import config.Database;
import model.Peserta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PesertaDAO {

    // CREATE: MENAMBAHKAN DATA PESERTA
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

            return ps.executeUpdate() > 0;
        }
    }

    // READ: MENGAMBIL SEMUA DATA PESERTA
    public List<Object[]> getAllRows() throws SQLException {

        List<Object[]> daftar = new ArrayList<>();

        String sql = "SELECT id_peserta, nim, nama, "
                + "jenis_kelamin, prodi, no_hp, email "
                + "FROM peserta ORDER BY id_peserta DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Object[] data = {
                    rs.getInt("id_peserta"),
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("prodi"),
                    rs.getString("no_hp"),
                    rs.getString("email")
                };

                daftar.add(data);
            }
        }

        return daftar;
    }

    // UPDATE: MENGUBAH DATA PESERTA
    public boolean update(
            int id,
            Peserta peserta,
            String jenisKelamin,
            String email) throws SQLException {

        String sql = "UPDATE peserta SET "
                + "nim = ?, "
                + "nama = ?, "
                + "jenis_kelamin = ?, "
                + "prodi = ?, "
                + "no_hp = ?, "
                + "email = ? "
                + "WHERE id_peserta = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, peserta.getNim());
            ps.setString(2, peserta.getNama());
            ps.setString(3, jenisKelamin);
            ps.setString(4, peserta.getProdi());
            ps.setString(5, peserta.getNoHp());
            ps.setString(6, email);
            ps.setInt(7, id);

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE: MENGHAPUS DATA PESERTA
    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM peserta "
                + "WHERE id_peserta = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}
