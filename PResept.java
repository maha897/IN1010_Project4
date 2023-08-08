//Subklasse PResept arvet av superklasse HvitResept
class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    //Returnerer prisen på legemiddelet. Fra abstact prisAaBetale
    //Prisen er prisen til legemiddelet - 108 som er rabatten til PResept
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        if((pris - 108) >= 0){
            return pris - 108;
        } else {return 0;}
    }
}