//Subklasse for Vanedannende legemiddel. Arver superklassen Legemiddel

class Vanedannende extends Legemiddel{
    private static int antvanedannende_resept = 0;
    private int styrke; //Hvor vanedannende legemiddelet er

    //Omdefinerer toString, skriver ut tilgjengelig info og kaller opp super.toString for generell tilgjengelig info
    @Override
    public String toString(){
        System.out.println("\n" + getClass().getSimpleName() + " Legemiddel " + hentNavn());
        String sStyrke = Integer.toString(hentVanedannendeStyrke());
        return super.toString() + "\nStyrke: " + sStyrke;
    }

    //Konstruktør
    public Vanedannende(String navn, int pris, double virkestoff, int styrke){
        
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        antvanedannende_resept ++;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }

    public int hentantallresepter(){
        return antvanedannende_resept;
    }
}