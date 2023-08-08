//Subklasse for Vanlig legemiddel. Arver superklassen Legemiddel
class Vanlig extends Legemiddel{
    //Omdefinerer toString, kaller opp super.toString for å skrive ut generell tilgjengelig info
    @Override
    public String toString(){
        System.out.println("\n" + getClass().getSimpleName() + " Legemiddel " + hentNavn());
        return super.toString();
    }

    //Konstruktør
    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }
}
