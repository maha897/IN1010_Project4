//Superklasse Lege
//Subklasse Spesialist ligger i Spesialist.java
public class Lege implements Comparable<Lege> {
    private String navn;
    private IndeksertListe<Resept> utskrevneResepter;

    //Konstruktør
    public Lege(String navn){
        this.navn = navn;
        this.utskrevneResepter = new IndeksertListe<Resept>();
    }

    ////Omdefinerer toString, skriver ut tilgjengelig info om Lege
    @Override
    public String toString(){
        String sNavn = hentNavn();
        
        System.out.println("\n" + getClass().getSimpleName() + " " + hentNavn());
        return "Navn: " + sNavn;
    }

    @Override
    public int compareTo(Lege annenLege){
        if (navn.compareTo(annenLege.navn) > 0) return 1;
        if (navn.compareTo(annenLege.navn) < 0) return -1;
        if (navn.compareTo(annenLege.navn) == 0) return 0;
        return 0;
    }

    public String hentNavn(){
        return navn;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter(){
        return utskrevneResepter;
    }

    private void sjekkGyldighet(Legemiddel legemiddel, boolean erBlaa)throws UlovligUtskrift{
        //Om ikke narkotisk, er den gyldig
        if (!(legemiddel instanceof Narkotisk)){
            return;
        }
        //Om lege er spesialist med blå resept, er narkotisk ok
        if ((this instanceof Spesialist) && erBlaa == true ){
            return;
        }
        //Alt annet er ugyldig
        throw new UlovligUtskrift(this, legemiddel);
    }

    HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
        sjekkGyldighet(legemiddel, false);
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvitResept.hentID(), hvitResept);
        return hvitResept;
    }

    MilResept skrivMilResept (Legemiddel legemiddel, Pasient pasient)throws UlovligUtskrift{
        sjekkGyldighet(legemiddel, false);
        MilResept milResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(milResept.hentID(), milResept);
        return milResept;
    }

    PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
        sjekkGyldighet(legemiddel, false);
        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(pResept.hentID(), pResept);
        return pResept;
    }

    BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
        sjekkGyldighet(legemiddel, true);
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(blaaResept.hentID(), blaaResept);
        return blaaResept;
    }
}