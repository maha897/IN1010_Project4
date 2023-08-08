//Subklasse MilResept arvet av superklasse HvitResept
class MilResept extends HvitResept {
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);    //Gir 3 reit
    }

    //Returnerer prisen på legemiddelet. Fra abstact prisAaBetale
    //Prisen er prisen til legemiddelet 100% rabbatert fra Militær resept
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        pris -= pris * 1;
        return pris;
    }
}