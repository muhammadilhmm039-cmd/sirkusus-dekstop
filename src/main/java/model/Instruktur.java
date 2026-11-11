
package model;

public class Instruktur extends Orang {

    private String keahlian;
    private String email;

    // Constructor kosong untuk kebutuhan form
    public Instruktur() {
        super(0, "Belum diisi", "");
        this.keahlian = "Belum diisi";
        this.email = "";
    }

    // Constructor lama: 4 parameter
    public Instruktur(
            int id,
            String nama,
            String noHp,
            String keahlian) {

        super(id, nama, noHp);
        setKeahlian(keahlian);
        this.email = "";
    }

    // Constructor lengkap: 5 parameter
    public Instruktur(
            int id,
            String nama,
            String noHp,
            String keahlian,
            String email) {

        super(id, nama, noHp);
        setKeahlian(keahlian);
        setEmail(email);
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        if (keahlian == null || keahlian.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Keahlian wajib diisi"
            );
        }

        this.keahlian = keahlian.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    @Override
    public String getInfo() {
        return "[Instruktur] " + super.getInfo()
                + " | Keahlian: " + getKeahlian();
    }
}
