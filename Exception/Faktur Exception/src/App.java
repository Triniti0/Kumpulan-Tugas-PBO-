import java.util.Scanner;

public class App extends Penjualan{
    public App(String namaPelanggan, String alamat) {
        super(namaPelanggan, alamat);
    }

    public void tampilStruk() {
        System.out.println("------------------------------");
        System.out.println("    SUPERMARKET MAS YUDA      ");
        System.out.println("==============================");
        super.tampilStruk();
        System.out.println("Kasir:              Mr. Dreamy Bull");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("TERIMA KASIH TELAH BERBELANJA");
        System.out.println("------------------------------");
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nama Pembeli:         ");
            String name = scanner.nextLine();
            System.out.print("Alamat:               ");
            String address = scanner.nextLine();
            System.out.print("Nama Barang:          ");
            String item = scanner.nextLine();
            System.out.print("Harga Barang:         ");
            double itemPrice = scanner.nextDouble();
            System.out.print("Jumlah:               ");
            int quantity = scanner.nextInt();

            try {
                App r = new App(name, address);
                r.beliBarang(item, itemPrice, quantity);
                r.tampilStruk();
            } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
