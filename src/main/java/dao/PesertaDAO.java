
package dao;

import config.Database;
import model.Peserta;
import util.LookupItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PesertaDAO {

    // ================= CREATE =================
    // MENAMBAHKAN DATA PESERTA

    public boolean insert(Peserta peserta) throws SQLException {

        String sql = "INSERT INTO peserta "
                + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, peserta.getNim());
            ps.setString(2, peserta.getNama());
            ps.setString(3, peserta.getJenisKelamin());
            ps.setString(4, peserta.getProdi());
            ps.setString(5, peserta.getNoHp());
            ps.setString(6, peserta.getEmail());

            return ps.executeUpdate() == 1;
        }
    }

    // ================= READ =================
    // MENGAMBIL SEMUA DATA PESERTA

    public List<Peserta> findAll() throws SQLException {

        List<Peserta> daftar = new ArrayList<>();

        String sql = "SELECT id_peserta, nim, nama, jenis_kelamin, "
                + "prodi, no_hp, email "
                + "FROM peserta ORDER BY id_peserta DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Peserta peserta = new Peserta(
                        rs.getInt("id_peserta"),
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("prodi"),
                        rs.getString("no_hp"),
                        rs.getString("email")
                );

                daftar.add(peserta);
            }
        }

        return daftar;
    }

    // ================= UPDATE =================
    // MENGUBAH DATA PESERTA

    public boolean update(Peserta peserta) throws SQLException {

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
            ps.setString(3, peserta.getJenisKelamin());
            ps.setString(4, peserta.getProdi());
            ps.setString(5, peserta.getNoHp());
            ps.setString(6, peserta.getEmail());
            ps.setInt(7, peserta.getId());

            return ps.executeUpdate() == 1;
        }
    }

    // ================= DELETE =================
    // MENGHAPUS DATA PESERTA

    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM peserta WHERE id_peserta = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }

    // ================= SEARCH =================
    // MENCARI DATA PESERTA

    public List<Peserta> search(String keyword) throws SQLException {

        List<Peserta> daftar = new ArrayList<>();

        String sql = "SELECT id_peserta, nim, nama, jenis_kelamin, "
                + "prodi, no_hp, email "
                + "FROM peserta "
                + "WHERE nim LIKE ? "
                + "OR nama LIKE ? "
                + "OR prodi LIKE ? "
                + "OR email LIKE ? "
                + "ORDER BY id_peserta DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String cari = "%" + keyword + "%";

            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ps.setString(4, cari);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Peserta peserta = new Peserta(
                            rs.getInt("id_peserta"),
                            rs.getString("nim"),
                            rs.getString("nama"),
                            rs.getString("jenis_kelamin"),
                            rs.getString("prodi"),
                            rs.getString("no_hp"),
                            rs.getString("email")
                    );

                    daftar.add(peserta);
                }
            }
        }

        return daftar;
    }

    // ================= LOOKUP =================
    // MENGAMBIL ID DAN NAMA PESERTA UNTUK COMBOBOX

    public List<LookupItem> lookup() throws SQLException {

        List<LookupItem> daftar = new ArrayList<>();

        String sql = "SELECT id_peserta, nama "
                + "FROM peserta ORDER BY nama ASC";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                daftar.add(
                        new LookupItem(
                                rs.getInt("id_peserta"),
                                rs.getString("nama")
                        )
                );
            }
        }

        return daftar;
    }
}
