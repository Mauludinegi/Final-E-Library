package Model;

import Connection.Koneksi;
import View.HomeView;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BukuModel {

    private String idBuku;
    private String judul;
    private String penerbit;
    private String pengarang;
    private String tahun;

    Koneksi conn = new Koneksi();

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

    
    

 

    public void read(HomeView view) throws SQLException {
        try {
            DefaultTableModel tabelData = new DefaultTableModel();
            view.getTabelBuku().setModel(tabelData);

            tabelData.addColumn("idBuku");
            tabelData.addColumn("judul");
            tabelData.addColumn("penerbit");
            tabelData.addColumn("pengarang");
            tabelData.addColumn("tahun");
            tabelData.addColumn("status");
            tabelData.addColumn("tanggal");

            String query = "SELECT * FROM buku where status = 'ada' ORDER BY tanggal";

            conn.statement = conn.connection.createStatement();
            ResultSet rs = conn.statement.executeQuery(query);

            while (rs.next()) {
                Object[] obj = new Object[7];

                obj[0] = rs.getString("idBuku");
                obj[1] = rs.getString("judul");
                obj[2] = rs.getString("pengarang");
                obj[3] = rs.getString("penerbit");
                obj[4] = rs.getString("tahun");
                obj[5] = rs.getString("status");
                obj[6] = rs.getString("tanggal");
                tabelData.addRow(obj);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
        }
    }
}
