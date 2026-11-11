
package app;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PasswordUtil;

public class TestPassword {

    public static void main(String[] args) {

        String sql = "SELECT password_hash FROM users "
                   + "WHERE username = 'admin'";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                String hash = rs.getString("password_hash");

                boolean cocok = PasswordUtil.verify(
                        "Admin@123".toCharArray(),
                        hash
                );

                System.out.println("Akun admin ditemukan.");
                System.out.println("Format hash: "
                        + (hash.split(":").length == 3
                        ? "VALID" : "TIDAK VALID"));

                System.out.println("Password cocok: " + cocok);

            } else {
                System.out.println("Akun admin tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("Kesalahan database:");
            e.printStackTrace();
        }
    }
}
