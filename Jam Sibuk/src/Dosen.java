public class Dosen extends Elemen{
    private int jumlahHariKerja;

    
    public Dosen(String nama, int jumlahHariKerja){
        super(nama);
        this.jumlahHariKerja = jumlahHariKerja;
    }
    
    public int getJamSibuk(){
        return jumlahHariKerja * 8;
    }
}