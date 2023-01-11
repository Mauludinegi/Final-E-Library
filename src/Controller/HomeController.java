package Controller;

import java.sql.Connection;
import Model.BukuModel;
import Model.Peminjaman;
import View.HomeView;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import Connection.Koneksi;

public class HomeController {

    private BukuModel buku;
    private Peminjaman transaksi;
    Connection conn;
    Koneksi kon = new Koneksi();

    public void setBuku(BukuModel buku) {
        this.buku = buku;
    }

    public void setTransaksi(Peminjaman transaksi) {
        this.transaksi = transaksi;
    }

    public void getData(HomeView view) {
        int pilih = view.getTabelBuku().getSelectedRow();
        String idBuku = (String) view.getTabelBuku().getValueAt(pilih, 0);
        String judul = (String) view.getTabelBuku().getValueAt(pilih, 1);
        String pengarang = (String) view.getTabelBuku().getValueAt(pilih, 2);
        String penerbit = (String) view.getTabelBuku().getValueAt(pilih, 3);
        String tahun = (String) view.getTabelBuku().getValueAt(pilih, 4);
        
        view.getIdBuku().setText(idBuku);
        view.getJudul().setText(judul);
        view.getPengarang().setText(pengarang);
        view.getPenerbit().setText(penerbit);
        view.getTahun().setText(tahun);

        buku.setIdBuku(idBuku);
    }

    public void readBuku(HomeView view) throws SQLException {
        buku.read(view);
    }

    public void addBuku(HomeView view) throws SQLException {
        try {
            String idBuku = view.getIdBuku().getText();
            String judul = view.getJudul().getText();
            String pengarang = view.getPengarang().getText();
            String penerbit = view.getPenerbit().getText();
            String tahun = view.getTahun().getText();

            if (idBuku.equals("") || judul.equals("") || pengarang.equals("") || penerbit.equals("") || tahun.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                try {
                    String query = "INSERT INTO `buku` (`idBuku`, `judul`, `pengarang`, `penerbit`, `tahun`, `status`) VALUES ('"
                            + idBuku + "', '"
                            + judul + "', '"
                            + pengarang + "', '"
                            + penerbit + "', '"
                            + tahun + "', 'ada')";
                    readBuku(view);
                    view.reset();
                    kon.statement = kon.connection.createStatement();
                    kon.statement.execute(query);

                    JOptionPane.showMessageDialog(view, "Berhasil menambahkan buku");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(view, "Data sudah ada");
                }

            }

        } catch (HeadlessException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    } 
    

    public void updateBuku(HomeView view) throws SQLException {
        try {
            String idBuku = view.getIdBuku().getText();
            String judul = view.getJudul().getText();
            String pengarang = view.getPengarang().getText();
            String penerbit = view.getPenerbit().getText();
            String tahun = view.getTahun().getText();

            if (idBuku.equals("") || judul.equals("") || pengarang.equals("") || tahun.equals("") || penerbit.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                try {
                    String query = "UPDATE buku SET idBuku = '"
                            + idBuku + "', judul = '"
                            + judul + "', pengarang = '"
                            + pengarang + "', penerbit = '"
                            + penerbit + "', tahun = '"
                            + tahun + "' WHERE buku.idBuku = '" + idBuku + "'";
                    kon.statement = kon.connection.createStatement();
                    kon.statement.executeUpdate(query);
                    readBuku(view);
                    view.reset();
                    JOptionPane.showMessageDialog(view, "Berhasil update data");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat terhubung ke database");
                }
            }
        } catch (HeadlessException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }


    public void deleteBuku(HomeView view) throws SQLException {
        try {
            String idBuku = view.getIdBuku().getText();
            String judul = view.getJudul().getText();
            String pengarang = view.getPengarang().getText();
            String penerbit = view.getPenerbit().getText();
            String tahun = view.getTahun().getText();

            if (idBuku.equals("") || judul.equals("") || pengarang.equals("") || penerbit.equals("") || tahun.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                try {
                    String query = "DELETE FROM `buku` WHERE `idBuku` = '" + idBuku + "'";
                    kon.statement = kon.connection.createStatement();
                    kon.statement.execute(query);
                    JOptionPane.showMessageDialog(view, "Berhasil menghapus data");
                    readBuku(view);
                    view.reset();

                } catch (SQLException ex) {
                    Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (HeadlessException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    

    

    public void readPeminjam(HomeView view) {
        transaksi.read(view);
    }
    
    public void readPeminjamSelesai(HomeView view) {
        transaksi.readSelesai(view);
    }

    public void kembaliBuku(HomeView view) {
        int pilih = view.getTabelPengembalian().getSelectedRow();

        String idAnggota = (String) view.getTabelPengembalian().getValueAt(pilih, 0);
        String idBuku = (String) view.getTabelPengembalian().getValueAt(pilih, 4);
        
        String status = "ada";
        
        transaksi.updateTransaksi(view, idAnggota);
        transaksi.updateStatusBuku(view, status, idBuku);
        transaksi.read(view);
        transaksi.readSelesai(view);
    }

    public void cmBuku(HomeView view) {
        transaksi.cmBuku(view);
    }

    public void dataPinjamBuku(HomeView view, String selectedValue) {
        transaksi.dataPinjamBuku(view, selectedValue);
    }

    public void pinjamBuku(HomeView view) throws SQLException {
        try {
            String idAnggota = view.getIdAnggota().getText();
            String nama = view.getNama().getText();
            String noTelp = view.getNoTlp().getText();
            String alamat = view.getAlamat().getText();
            String idBuku = view.getCmBuku().getSelectedItem().toString();
            String judul = view.getIJudul().getText();
            String pengarang = view.getiPengarang().getText();
            String penerbit = view.getiPengarang().getText();
            String tahun = view.getITahun().getText();
            String status = "peminjaman";
            
            if (idAnggota.equals("") || nama.equals("") || noTelp.equals("") || alamat.equals("") || idBuku.equals("") ||judul.equals("") ||pengarang.equals("") ||penerbit.equals("") ||tahun.equals("")) {
                JOptionPane.showMessageDialog(view, "Form tidak boleh kosong");
            } else {
                int opsi = JOptionPane.showConfirmDialog(view, "Yakin ingin meminjam?");
                if (opsi == JOptionPane.YES_OPTION) {
                    transaksi.pinjamBuku(view, idAnggota, nama, noTelp, alamat, idBuku, judul);
                    transaksi.updateStatusPinjam(view, status, idBuku);
                    view.resetForm();
                }
            }
        }catch (HeadlessException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
    
    public void viewReport() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
            JasperDesign jdeDesign = JRXmlLoader.load("C:\\Users\\HP\\Music\\E-Library\\src\\Ireport\\report2.jrxml");
            String query = "select * from peminjaman ORDER BY status";
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);
            
            jdeDesign.setQuery(updateQuery);
            
            JasperReport jreport = JasperCompileManager.compileReport(jdeDesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, conn);
            JasperViewer.viewReport(jprint);
            
        
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
