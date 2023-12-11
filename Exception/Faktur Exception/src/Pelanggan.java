public class Pelanggan implements Supermarket{
    public String namaPelanggan;
    //public String noHp;
    public String alamat;
    public int noFaktur;
    public String namaBarang;
    public double hargaBarang;
    public int jumlahBeli;
    public double totalBayar;

    public Pelanggan(String namaPelanggan, String alamat) {
        this.namaPelanggan = namaPelanggan;
        //this.noHp = noHp;
        this.alamat = alamat;
        this.noFaktur = 1;
    }

    public void beliBarang(String namaBarang, double hargaBarang, int jumlahBeli) {
        if (hargaBarang <= 0 || jumlahBeli <= 0) {
            throw new IllegalArgumentException("Invalid input. Tidak boleh kecil dari 0.");
        }
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
        this.totalBayar = hargaBarang * jumlahBeli;
        this.noFaktur++;
    }

    @Override
    public void tampilStruk() {
        System.out.println("DATA PELANGGAN");
        System.out.println("---------------------------------------");
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("Alamat:         " + alamat);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("---------------------------------------");
        System.out.println("Nama Barang:    " + namaBarang);
        System.out.println("Harga Barang:   " + hargaBarang);
        System.out.println("Jumlah Beli:    " + jumlahBeli);
        System.out.println("Total Bayar:    " + totalBayar);
    }
}
