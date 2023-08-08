//Superklasse Legemiddel
//Subklassene Narkotisk, Vanedannende og Vanlig har ligger i sine representative .java filer
public abstract class Legemiddel {
    //Variabler til legemiddel
    private String navn;
    private int ID;
    private static int antLegemiddel = 0;
    private int pris;
    private double virkestoff;  // Gis i mg

    //Omdefinerer toString, skriver ut tilgjengelig info om legemiddel
    @Override
    public String toString(){
        String sNavn = hentNavn();
        String sID = Integer.toString(hentID());
        String sPris = Integer.toString(hentPris());
        String sVirkestoff = Double.toString(hentVirkestoff());
        return "Navn: " + sNavn + "\nID: " + sID + "\nPris: " + sPris + "\nVirkestoff: " + sVirkestoff;
    }
    
    //Konstruktør
    public Legemiddel(String navn, int pris, double virkestoff){
        antLegemiddel++;
        ID = antLegemiddel;

        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }   

    public int hentID(){
        return ID;
    }

    public static int hentAntallLegemiddel(){
        return antLegemiddel;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(int nyPris){
        pris = nyPris;
    }
}