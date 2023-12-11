public class Pelanggan implements Supermarket{
    public String namaPelanggan;
    public String noHp;
    public String alamat;
    public int noFaktur;
    public String namaBarang;
    public double hargaBarang;
    public int jumlahBeli;
    public double totalBayar;

    public Pelanggan(String namaPelanggan, String noHp, String alamat) {
        this.namaPelanggan = namaPelanggan;
        this.noHp = noHp;
        this.alamat = alamat;
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
        System.out.println("=====================");
        System.out.println("No. Faktur:     " + noFaktur  );
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        System.out.println("No. HP:         " + noHp);
        System.out.println("Alamat:         " + alamat);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        System.out.println("Nama Barang:    " + namaBarang);
        System.out.println("Harga Barang:   " + hargaBarang);
        System.out.println("Jumlah Beli:    " + jumlahBeli);
        System.out.println("Total Bayar:    " + totalBayar);
        System.out.println("=====================");
    }
}

