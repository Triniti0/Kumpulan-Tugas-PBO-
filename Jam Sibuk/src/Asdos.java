public class Asdos extends Mahasiswa{
    private int jamNgados;


    public Asdos(String nama, int SKS, int jamNgados){
        super(nama, SKS);
        this.jamNgados = jamNgados;
    }

    public int getJamSibuk(){
        return super.getJamSibuk() + jamNgados;
    }
}