//Subklasse for narkotisk legemiddel. Arver superklassen Legemiddel
class Narkotisk extends Legemiddel{
    private int styrke;     //Styrken til det narkotiske legemiddelet
    private static int ant_narkotisk = 0;

    //Omdefinerer toString, skriver ut tilgjengelig info og kaller opp super.toString for generell tilgjengelig info
    @Override
    public String toString(){
        System.out.println("\n" + getClass().getSimpleName() + " Legemiddel " + hentNavn());
        String sStyrke = Integer.toString(hentNarkotiskStyrke());
        return super.toString() + "\nStyrke: " + sStyrke;
    }

    //Konstruktør
    public Narkotisk(String navn, int pris, double virkestoff, int styrke){
        
        super(navn, pris, virkestoff);
        
        this.styrke = styrke;
        ant_narkotisk++;

    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }
    public int  hentantall(){
        return ant_narkotisk;
    }
}