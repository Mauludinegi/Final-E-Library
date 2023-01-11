package Model;

import Connection.Koneksi;
import View.HomeView;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Peminjaman {

    private String idAnggota;
    private String nama;
    private String noTelp;
    private String alamat;
    private String idBuku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tahun;


    Koneksi conn = new Koneksi();

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public Koneksi getConn() {
        return conn;
    }

    public void setConn(Koneksi conn) {
        this.conn = conn;
    }

  

    public void read(HomeView view) {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelPengembalian().setModel(tabelData);

            tabelData.addColumn("idAnggota");
            tabelData.addColumn("nama");
            tabelData.addColumn("No_Telp");
            tabelData.addColumn("Alamat");
            tabelData.addColumn("idBuku");
            tabelData.addColumn("judul");
            tabelData.addColumn("status");
            tabelData.addColumn("createdAt");

            String query = "SELECT * FROM peminjaman WHERE status = 'peminjaman'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[8];

                obj[0] = rs.getString("idAnggota");
                obj[1] = rs.getString("nama");
                obj[2] = rs.getString("noTlp");
                obj[3] = rs.getString("alamat");
                obj[4] = rs.getString("idBuku");
                obj[5] = rs.getString("judul");
                obj[6] = rs.getString("status");
                obj[7] = rs.getString("createdAt");
                

                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void readSelesai(HomeView view) {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelPengembalianSelesai().setModel(tabelData);

            tabelData.addColumn("idAnggota");
            tabelData.addColumn("nama");
            tabelData.addColumn("No_Telp");
            tabelData.addColumn("Alamat");
            tabelData.addColumn("idBuku");
            tabelData.addColumn("judul");
            tabelData.addColumn("status");
            tabelData.addColumn("createdAt");

            String query = "SELECT * FROM peminjaman WHERE status = 'selesai'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[8];

                obj[0] = rs.getString("idAnggota");
                obj[1] = rs.getString("nama");
                obj[2] = rs.getString("noTlp");
                obj[3] = rs.getString("alamat");
                obj[4] = rs.getString("idBuku");
                obj[5] = rs.getString("judul");
                obj[6] = rs.getString("status");
                obj[7] = rs.getString("createdAt");

                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void updateTransaksi(HomeView view, String idAnggota) {
        try {
            String query = "UPDATE peminjaman SET status = 'selesai' WHERE idAnggota = '" + idAnggota + "'";

            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void updateStatusBuku(HomeView view, String status, String idBuku) {
        try {
            String query = "UPDATE buku SET status = '" + status + "' WHERE idBuku = '" + idBuku + "'";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(view, "Berhasil mengembalikan Buku");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void cmBuku(HomeView view) {
        try {
            String query = "SELECT * FROM buku WHERE buku.status = 'ada'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                view.getCmBuku().addItem(rs.getString("idBuku"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void dataPinjamBuku(HomeView view, String selectedValue) {
        try {
            String query = "SELECT * FROM buku WHERE buku.idBuku = '" + selectedValue + "'";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                view.getIJudul().setText(rs.getString("judul"));
                view.getiPengarang().setText(rs.getString("pengarang"));
                view.getIPenerbit().setText(rs.getString("penerbit"));
                view.getITahun().setText(rs.getString("tahun"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }

    public void pinjamBuku(HomeView view,
            String idAnggota,
            String nama,
            String noTelp,
            String alamat,
            String idBuku,
            String judul) {
        try {
            String query = "INSERT INTO `peminjaman`(`idAnggota`, `nama`, `noTlp`, `alamat`, `idBuku`, `judul`, `status`) VALUES ('" + 
                    idAnggota + "',"
                    + " '" + nama + "',"
                    + " '" + noTelp + "',"
                    + " '" + alamat + "',"
                    + " '" + idBuku + "',"
                    + " '" + judul + "','peminjaman')";

            conn.statement = conn.connection.createStatement();
            conn.statement.execute(query);

            JOptionPane.showMessageDialog(view, "Berhasil meminjam buku");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    
    public void updateStatusPinjam(HomeView view, String status, String idBuku) {
        try {
            String query = "UPDATE buku SET status = '" + status + "' WHERE idBuku = '" + idBuku + "'";
            
            conn.statement = conn.connection.createStatement();
            conn.statement.executeUpdate(query);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }
}
