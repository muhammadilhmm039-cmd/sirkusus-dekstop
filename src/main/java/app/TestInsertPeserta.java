
package app;

import dao.PesertaDAO;
import model.Peserta;
import java.sql.SQLException;

public class TestInsertPeserta {

    public static void main(String[] args) {

        try {
            Peserta peserta = new Peserta(
                    "252099",
                    "Nadia Putri",
                    "P",
                    "Informatika",
                    "081200000099",
                    "nadia@example.test"
            );

            PesertaDAO dao = new PesertaDAO();

            boolean berhasil = dao.insert(peserta);

            if (berhasil) {
                System.out.println(
                        "DATA PESERTA BERHASIL DISIMPAN!"
                );
            } else {
                System.out.println(
                        "DATA PESERTA GAGAL DISIMPAN!"
                );
            }

        } catch (SQLException e) {
            System.out.println(
                    "KESALAHAN DATABASE: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "DATA TIDAK VALID: " + e.getMessage()
            );
        }
    }
}
