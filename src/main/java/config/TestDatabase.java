package config;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabase {

    public static void main(String[] args) {

        try (Connection conn = Database.getConnection()) {

            System.out.println("KONEKSI BERHASIL!");
            System.out.println("Database: db_sikursus");

        } catch (SQLException e) {

            System.out.println("KONEKSI GAGAL!");
            System.out.println(e.getMessage());

        }
    }
}