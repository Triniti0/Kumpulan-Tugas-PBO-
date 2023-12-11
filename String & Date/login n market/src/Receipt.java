import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;

public class Receipt extends Penjualan{
    //String namaKasir;

    public Receipt(String namaPelanggan, String noHp, String alamat) {
        super(namaPelanggan, noHp, alamat);
        //this.namaKasir = namaKasir;
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
