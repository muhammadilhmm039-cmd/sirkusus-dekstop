package app;

import dao.PesertaDAO;
import model.Peserta;
import java.sql.SQLException;

public class TestInsertPeserta {

    public static void main(String[] args) {

        try {
            Peserta peserta = new Peserta(
                    0,
                    "Nadia Putri",
                    "081200000099",
                    "252099",
                    "Informatika"
            );

            PesertaDAO dao = new PesertaDAO();

            boolean berhasil = dao.insert(
                    peserta,
                    "P",
                    "nadia@example.test"
            );

            if (berhasil) {
                System.out.println(
                        "DATA PESERTA BERHASIL DISIMPAN!"
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