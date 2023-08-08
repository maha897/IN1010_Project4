//Subklasse HvitResept arvet av superklasse Resept
//HvitResept er en egen superklasse for subklassene PResept og MilResept
class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    //Returnerer fargen til resept. Fra abstract farge(); metode
    public String farge(){
        return "hvit";
    }

    //Returnerer prisen på legemiddelet. Fra abstact prisAaBetale
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }
}