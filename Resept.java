//Superklasse Resept
//Subklasser HvitResept og BlaaResept ligger i HvitResept.java og BlaaResept.java
//HvitEesept er en egen superklasse for subklassene PResept og MilResept

public abstract class Resept {
    //Instansvariabler til Resept
    private int ID;                     //Hvilken ID resepten har, blir tildelt kronologisk
    private static int antResepter = 0; //Antall resepter laget
    protected Legemiddel legemiddel;    //Hvilket legemiddel resepten tilhører
    private Lege utskrivendeLege;       //Hvilken lege som har skrevet ut resepten
    private Pasient tilhorendePasient;              //Pasient IDen på pasienten som eier resepten
    private int reit;                   //Antall ganger bruk som er igjen på resepten

    //Omdefinerer toString, skriver ut tilgjengelig info om resept
    @Override
    public String toString(){
        String sID = Integer.toString(hentID());
        String sLegemiddel = legemiddel.hentNavn();
        String sPasientID = Integer.toString(hentPasientID());
        String sReit = Integer.toString(hentReit());
        String sGyldig = Boolean.toString(sjekkGyldighet());
        String sPris = Integer.toString(prisAaBetale());

        //Printer ut hvilke legemiddel resepten gjelder for
        System.out.println("\nResepten for legemiddelet " + sLegemiddel + " har følgende info:");

        //Printer ut alle instansvariabler om resepten til legemiddelet, inkludert om den er gyldig
        return "ID: " + sID + "\nLegemiddel: " + sLegemiddel + "\nPasient ID: " + sPasientID + "\nAntall bruk igjen: " + sReit + "\nGyldig: " + sGyldig + "\nPris å betale: " + sPris;
    }

    //Konstruktør
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        antResepter++;
        ID = antResepter;

        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.tilhorendePasient = pasient;
        this.reit = reit;
    }

    //Sjekker om reit er 0 eller ikke og returnerer true eller false.
    public boolean sjekkGyldighet(){
        if (reit <= 0){
            return false;
        } else {return true;}
    }

    public int hentID(){
        return ID;
    }
    public static int hentAntallResepter(){
        return antResepter;
    }
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }
    public Lege hentLege(){
        return utskrivendeLege;
    }
    public int hentPasientID(){
        return tilhorendePasient.hentID();
    }
    public Pasient hentPasient(){
        return tilhorendePasient;
    }
    public int hentReit(){
        return reit;
    }

    //Bruker resepten 1 gang og minker reit med 1 om mulig
    public boolean bruk(){
        if (hentReit() <= 0){
            return false;
        }else{
            reit--;
            return true;
        }
    }

    //Abstrakt metode for å returnere fargen til resepten.
    abstract public String farge();

    //Abstrakt metode for å returnere prisen på legemiddelet
    abstract public int prisAaBetale();
}