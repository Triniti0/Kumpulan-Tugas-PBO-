import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Receipt extends Penjualan{
    //String namaKasir;

    public Receipt(String namaPelanggan, String noHp, String alamat) {
        super(namaPelanggan, noHp, alamat);
        //this.namaKasir = namaKasir;
    }

    public void insertData() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO sales (nama_pelanggan, no_hp, nama_barang, harga_barang, jumlah_beli, total_bayar, tanggal_pembelian) " +
                           "VALUES (?, ?, ?, ?, ?, ?, NOW())";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, this.namaPelanggan);
                preparedStatement.setString(2, this.noHp);
                preparedStatement.setString(3, this.namaBarang);
                preparedStatement.setDouble(4, this.hargaBarang);
                preparedStatement.setInt(5, this.jumlahBeli);
                preparedStatement.setDouble(6, this.totalBayar);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readData(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM sales WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        this.noFaktur = resultSet.getInt("id");
                        this.namaPelanggan = resultSet.getString("nama_pelanggan");
                        this.noHp = resultSet.getString("no_hp");
                        this.namaBarang = resultSet.getString("nama_barang");
                        this.hargaBarang = resultSet.getDouble("harga_barang");
                        this.jumlahBeli = resultSet.getInt("jumlah_beli");
                        this.totalBayar = resultSet.getDouble("total_bayar");
                    } else {
                        System.out.println("Data not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE sales SET nama_pelanggan=?, no_hp=?, nama_barang=?, harga_barang=?, jumlah_beli=?, total_bayar=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, this.namaPelanggan);
                preparedStatement.setString(2, this.noHp);
                preparedStatement.setString(3, this.namaBarang);
                preparedStatement.setDouble(4, this.hargaBarang);
                preparedStatement.setInt(5, this.jumlahBeli);
                preparedStatement.setDouble(6, this.totalBayar);
                preparedStatement.setInt(7, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Data not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM sales WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully.");
                } else {
                    System.out.println("Data not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void tampilStruk1() {
        System.out.println(" ");
        System.out.println("          AMBATU SWALAYAN         ");
        System.out.println("---------------------------------------");

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("D-dd-MM-yyyy");
        System.out.println("hari/Tanggal:       " + today.format(formatter));

        LocalDateTime currentDateTime = LocalDateTime.now();
        formatter = DateTimeFormatter.ofPattern("hh-mm-ss ");
        //System.out.println("Jam         :       " + currentDateTime.format(formatter));

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = currentDateTime.atZone(zoneId);
        System.out.println("Jam         :       " + zonedDateTime.format(formatter) + " " + zonedDateTime.getZone());
        System.out.println("=======================================");

       
        super.tampilStruk();
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("Nama Kasir          : MR. Dreamy Bull"); //anggap saja Mr. Dreamy Bull adalah hanya kasir yang tersedia:))
        System.out.println("---------------------------------------");
        System.out.println("TERIMA KASIH TELAH BERBELANJA");
        System.out.println(" ");
    }
}
