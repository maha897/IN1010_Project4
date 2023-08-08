//Subklasse Spesialist arvet av superklasse Lege, og implimentert grensesnittet Godkjenningsfritak
class Spesialist extends Lege implements Godkjenningsfritak{
    private String kontrollID;

    //Omdefinerer toString, skriver ut tilgjengelig info og kaller opp super.toString for generell tilgjengelig info
    @Override
    public String toString(){
        String sKontrollID = hentKontrollID();
        return super.toString() + "\nKontroll ID: " + sKontrollID;
    }

    //Konstruktør
    public Spesialist(String navn, String kontrollID){
        super(navn);
        this.kontrollID = kontrollID;
    }

    @Override
    public String hentKontrollID(){
        return kontrollID;
    }
}