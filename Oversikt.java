//Beholder klasse som holder all informasjon om alle variablene som brukes i programmet
class Oversikt {

    private Koe<Pasient> pasientliste = new Koe<Pasient>();
    private Koe<Legemiddel> middelliste = new Koe<Legemiddel>();
    private Koe<Resept> reseptliste = new Koe<Resept>();
    private Prioritetskoe<Lege> legeliste = new Prioritetskoe<Lege>();

    public void skrivUtAlt() {
        skrivUtPasienter();
        skrivUtLegemiddler();
        skrivUtLeger();
        skrivUtResepter();
    }

    public Koe<Pasient> hentPasientliste() {
        return pasientliste;
    }

    public Koe<Legemiddel> hentMiddelliste() {
        return middelliste;
    }

    public Prioritetskoe<Lege> hentLegeliste() {
        return legeliste;
    }

    public Koe<Resept> hentReseptliste() {
        return reseptliste;
    }

    void skrivUtPasienter() {
        for (Pasient pasient : pasientliste) {
            System.out.println(pasient);
        }
    }

    void skrivUtLegemiddler() {
        for (Legemiddel legemiddel : middelliste) {
            System.out.println(legemiddel);
        }
    }

    void skrivUtLeger() {
        for (Lege lege : legeliste) {
            System.out.println(lege);
        }
    }

    //Printer alle resepter pr lege
    void skrivUtResepter() {
        for (Lege lege : legeliste) {
            System.out.println("\nResepter for " + lege.getClass().getSimpleName() + " " + lege.hentNavn() + ":");
            if (lege.hentUtskrevneResepter().stoerrelse() > 0) {
                for (Resept resept : lege.hentUtskrevneResepter()) {
                    System.out.println(resept.hentLegemiddel().hentNavn());
                }
            }
        }
    }

    //Returnerer Lege basert på navn
    Lege finnLege(String navn) {
        for (Lege lege : legeliste) {
            if (navn.equals(lege.hentNavn())) {
                return lege;
            }
        }
        return null;
    }

    //Returnerer Legemiddel basert på legemiddel nummer
    Legemiddel finnLegemiddel(int lnr) {
        for (Legemiddel legemiddel : middelliste) {
            if (lnr == legemiddel.hentID()) {
                return legemiddel;
            }
        }
        return null;
    }

    //Returnerer Pasient basert på pasientID
    Pasient finnPasient(int pasientID) {
        for (Pasient pasient : pasientliste) {
            if (pasientID == pasient.hentID()) {
                return pasient;
            }
        }
        return null;
    }

    //Returnerer Resept basert på reseptID
    Resept finnResept(int reseptID) {
        for (Resept resept : reseptliste) {
            if (reseptID == resept.hentID()) {
                return resept;
            }
        }
        return null;
    }

    void opprettPasient(String navn, String fn) {

        Pasient pas = new Pasient(navn, fn);
        pasientliste.leggTil(pas);
    }

    void opprettLege(String navn, String kontrollID) {
        if (kontrollID.equals("0")) {
            Lege l = new Lege(navn);
            legeliste.leggTil(l);
        } else {
            Spesialist s = new Spesialist(navn, kontrollID);
            legeliste.leggTil(s);
        }
    }

    Legemiddel opprettLegemidler(String navn, String type, int pris, double virke, int styrke) {
        switch (type) {
            case "narkotisk": {
                Narkotisk heroin = new Narkotisk(navn, pris, virke, styrke);
                middelliste.leggTil(heroin);
                return heroin;
            }

            case "vanedannende": {
                Vanedannende nikotin = new Vanedannende(navn, pris, virke, styrke);
                middelliste.leggTil(nikotin);
                return nikotin;
            }

            case "vanlig": {
                Vanlig vanlig = new Vanlig(navn, pris, virke);
                middelliste.leggTil(vanlig);
                return vanlig;
            }
            default: {
                System.out.println("Error med fil");
                return null;
            }

        }
    }

    //Oppretter resepter så lenge parameterne for Lege, Legemiddel og pasient er gyldig
    //Håndterer ulovlig utskrift, om det er en narkotisk Legemiddel som ikke blir skrevet ut på en blå resept av en Spesialist
    void opprettResepter(int LegemiddelNr, String legeNavn, int pasientID, String type, int reit) {
        Lege lege = finnLege(legeNavn);
        Legemiddel legemiddel = finnLegemiddel(LegemiddelNr);
        Pasient pasient = finnPasient(pasientID);
        Resept resept = null;
        if (lege == null || legemiddel == null || pasient == null) {
            System.out.println("Ugyldig input");
            return;
        }
        try {
            switch (type) {
                case "hvit": {
                    resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                    break;
                }
                case "blaa": {
                    resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                    break;
                }

                case "militaer": {
                    resept = lege.skrivMilResept(legemiddel, pasient);
                    break;
                }
                case "p": {
                    resept = lege.skrivPResept(legemiddel, pasient, reit);
                    break;
                }
                default: {
                    System.out.println("Feil resept type");
                    return;
                }
            }
            reseptliste.leggTil(resept);
            pasient.leggtilResept(resept);
        } catch (UlovligUtskrift e) {
            System.out.println("Ugyldig utskrift av lege eller legemiddel");
        }
    }
}