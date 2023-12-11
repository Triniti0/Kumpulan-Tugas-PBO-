public class Simulator {
    public static void main(String[] args) {
        Asdos fairuzikun = new Asdos("Fairuzikun", 23, 4);
        Dosen rajaOpDamanik = new Dosen("Raja OP Damanik", 5);
        Asdos angelChan = new Asdos("Angel-chan", 20, 4);
        Mahasiswa firman = new Mahasiswa("Firman", 20);
        Mahasiswa nidToPassThisSem = new Mahasiswa("Nid to pass this sem", 23);
        Dosen nivotko = new Dosen("Nivotko", 3);

        int totalJamSibuk = fairuzikun.getJamSibuk() + rajaOpDamanik.getJamSibuk() + angelChan.getJamSibuk() 
        + firman.getJamSibuk() + nidToPassThisSem.getJamSibuk() + nivotko.getJamSibuk();

        System.out.println(fairuzikun.getNama() + " adalah seorang asdos dengan jam sibuk " + fairuzikun.getJamSibuk());
        System.out.println(rajaOpDamanik.getNama() + " adalah seorang dosen dengan jam sibuk " + rajaOpDamanik.getJamSibuk());
        System.out.println(angelChan.getNama() + " adalah seorang asdos dengan jam sibuk " + angelChan.getJamSibuk());
        System.out.println(firman.getNama() + " adalah seorang mahasiswa dengan jam sibuk " + firman.getJamSibuk());
        System.out.println(nidToPassThisSem.getNama() + " adalah seorang mahasiswa dengan jam sibuk " + nidToPassThisSem.getJamSibuk());
        System.out.println(nivotko.getNama() + " adalah seorang dosen dengan jam sibuk " + nivotko.getJamSibuk());
        System.out.println("Total jam sibuk elemen Fasilkom adalah " + totalJamSibuk);
    }
}
 
