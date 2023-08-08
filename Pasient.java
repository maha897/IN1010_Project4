class Pasient{
    private String navn;
    private String fodselsnummer;
    private static int antallPasienter = 1;
    private int pasientID;
    private Koe<Resept> pasientenResepter;

    Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        this.pasientID = antallPasienter;
        this.pasientenResepter = new Koe<Resept>();
        antallPasienter++;
    }

    @Override
    public String toString() {
        return "\nNavn: " + navn + "\nFødselsnummer: " + fodselsnummer + "\nID: " + pasientID;
    }

    public int hentID(){
        return pasientID;
    }

    public static int hentAntallPasienter(){
        return antallPasienter;
    }

    public String hentNavn(){
        return navn;
    }

    public String hentFnr(){
        return fodselsnummer;
    }
    public void leggtilResept(Resept nyrResept){
        pasientenResepter.leggTil(nyrResept);
    }
    public Koe<Resept> hentResepter(){
        return pasientenResepter;
    }
}