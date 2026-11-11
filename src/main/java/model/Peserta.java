
package model;

public class Peserta extends Orang {

    private String nim;
    private String prodi;
    private String jenisKelamin;
    private String email;

    // Constructor kosong
    public Peserta() {
        super(0, "Belum diisi", "");
        this.nim = "";
        this.prodi = "";
        this.jenisKelamin = "";
        this.email = "";
    }

    // Constructor lama: 5 parameter
    public Peserta(
            int id,
            String nama,
            String noHp,
            String nim,
            String prodi) {

        super(id, nama, noHp);

        this.nim = nim;
        this.prodi = prodi;
        this.jenisKelamin = "";
        this.email = "";
    }

    // Constructor tanpa ID: 6 parameter
    public Peserta(
            String nim,
            String nama,
            String jenisKelamin,
            String prodi,
            String noHp,
            String email) {

        super(0, nama, noHp);

        setNim(nim);
        setJenisKelamin(jenisKelamin);
        setProdi(prodi);
        setEmail(email);
    }

    // Constructor lengkap: 7 parameter
    public Peserta(
            int id,
            String nim,
            String nama,
            String jenisKelamin,
            String prodi,
            String noHp,
            String email) {

        super(id, nama, noHp);

        setNim(nim);
        setJenisKelamin(jenisKelamin);
        setProdi(prodi);
        setEmail(email);
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        if (jenisKelamin == null || jenisKelamin.trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis kelamin wajib dipilih");
        }
        this.jenisKelamin = jenisKelamin.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    @Override
    public String getInfo() {
        return "[Peserta] " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi();
    }
}
