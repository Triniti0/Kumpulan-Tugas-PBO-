public class Penjualan extends Pelanggan{
    public int noFaktur;
    public String namaBarang;
    public double hargaBarang;
    public int jumlahBeli;
    public double totalBayar;

    public Penjualan(String namaPelanggan, String noHp, String alamat) {
        super(namaPelanggan, noHp, alamat);
        this.noFaktur = 1;
    }

    public void beliBarang(String namaBarang, double hargaBarang, int jumlahBeli) {
        if (hargaBarang <= 0 || jumlahBeli <= 0) {
            throw new IllegalArgumentException("Invalid input. Harga dan Kuantitas barang harus diatas 0. Silahkan input kembali: ");
        }
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
        this.totalBayar = hargaBarang * jumlahBeli;
        this.noFaktur++;
    }

    public void tampilStruk() {
        System.out.println("DATA PELANGGAN ");
        System.out.println("---------------------------------------");
        System.out.println("Nama Pelanggan:      " + this.namaPelanggan);
        System.out.println("No HP:               " + this.noHp);
        System.out.println("Alamat:              " + this.alamat);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("---------------------------------------");
        System.out.println("Nama Barang:         " + this.namaBarang);
        System.out.println("Harga Barang:        " + this.hargaBarang);
        System.out.println("Jumlah Beli:         " + this.jumlahBeli);
        System.out.println("Total Bayar:         " + this.totalBayar);
    }
}
