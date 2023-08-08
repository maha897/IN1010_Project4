//Subklasse BlaaResept arvet av superklasse Resept
class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    //Returnerer fargen til resept. Fra abstract farge(); metode
    public String farge(){
        return "blaa";
    }

    //Returnerer prisen på legemiddelet. Fra abstact prisAaBetale
    //Prisen er prisen til legemiddelet 75% rabbatert fra Blå resept
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        pris = pris - (int)((double)pris * 0.75);
        return pris;
    }
}