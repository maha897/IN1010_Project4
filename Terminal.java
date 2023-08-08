import java.util.Scanner;

//Terminal tilbyr kommandolinje basert interaksjon med resten av programmet
public class Terminal {
    Oversikt global = null;
    Scanner terminal;
    Filhandler fh;

    Terminal() {
        global = new Oversikt();
        fh = new Filhandler("testdata.txt", global);
        terminal = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Terminal term = new Terminal();
        while (true) {
            term.hovedmeny();
        }
    }

    void hovedmeny() {
        printHeader("Hovedprogram");
        System.out.println("1: Oversikt");
        System.out.println("2: Opprett og legg til nye");
        System.out.println("3: Bruk resepter");
        System.out.println("4: Skriv til fil");
        System.out.println("5: Avslutt program");

        String input = terminal.nextLine();

        System.out.println(input);
        switch (input) {
            case "1":
                printOversikt();
                break;
            case "2":
                printOpprett();
                break;
            case "3":
                printBruk();
                break;
            case "4":
                skrivTilFil();
                break;
            case "5":
                terminal.close();
                System.exit(-1);
        }
    }

    // Oppretter fra brukerinput
    void opprettLege() {
        System.out.println("Skriv inn lege: NAVN,ID (ID 0 for vanlig lege)");
        String svar = terminal.nextLine();
        String[] split = svar.split(",");

        if (split.length == 2) {
            if (split[1].equals("0")) {
                global.opprettLege(split[0], split[1]);
                System.out.println("Lege lagt inn.");
                System.out.println("Navn:" + split[0]);
                return;
            } else {
                global.opprettLege(split[0], split[1]);
                System.out.println("Spesialist lagt inn.");
                System.out.println("Navn:" + split[0]);
                System.out.println("ID:" + split[1]);

            }
        } else {
            System.out.println("Ugyldig input. Ble ikke lagt inn.");
        }
    }

    // Oppretter fra brukerinput
    void opprettPasient() {
        System.out.println("Skriv inn pasient: NAVN,FODSELSNUMMER");
        String svar = terminal.nextLine();
        String[] split = svar.split(",");

        if (split.length == 2) {
            global.opprettPasient(split[0], split[1]);
            System.out.println("Pasient lagt inn.");
            System.out.println("Navn:" + split[0]);
            System.out.println("Fødselsnummer:" + split[1]);
            return;
        } else {
            System.out.println("Ugyldig input. Ble ikke lagt inn.");
        }
    }

    // Oppretter fra brukerinput
    void opprettLegemidler() {
        System.out.println("Skriv inn legemiddel: NAVN,TYPE,PRIS,VIRKESTOFF,STYRKE (Styrke 0 for vanlige legemidler)");
        String svar = terminal.nextLine();
        String[] split = svar.split(",");

        if (split.length == 5 && sjekkLMType(split[1]) && sjekkInt(split[2]) && sjekkDouble(split[3])
                && sjekkInt(split[4])) {
            Legemiddel legemiddel = global.opprettLegemidler(split[0], split[1], Integer.parseInt(split[2]),
                    Double.parseDouble(split[3]), Integer.parseInt(split[4]));
            if (legemiddel != null) {
                System.out.println("Legemiddel lagt inn.");
                System.out.println("Navn:" + legemiddel.hentNavn());
                System.out.println("ID:" + legemiddel.hentID());
                System.out.println("Pris:" + legemiddel.hentPris());
                System.out.println("Virkestoff:" + legemiddel.hentVirkestoff());
                if (legemiddel instanceof Narkotisk || legemiddel instanceof Vanedannende) {
                    System.out.println("Styrke:" + split[4]);
                }
            }
            return;
        } else {
            System.out.println("Ugyldig input. Ble ikke lagt inn.");
        }
    }

    // Oppretter fra brukerinput
    void opprettResepter() {
        System.out.println("Skriv inn resept: LEGEMIDDELNR,LEGENAVN,PASIENTID,TYPE,REIT");
        String svar = terminal.nextLine();
        String[] split = svar.split(",");

        if (split.length == 5 && sjekkInt(split[0]) && sjekkLege(split[1])
                && sjekkInt(split[2]) && sjekkReseptType(split[3]) && sjekkInt(split[4])) {
            global.opprettResepter(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]), split[3],
                    Integer.parseInt(split[4]));
            return;
        } else {
            System.out.println("Ugyldig input. Ble ikke lagt inn.");
        }
    }

    // Printer ut meny for oppretting
    void printOpprett() {
        printHeader("Opprett ny");
        System.out.println("1: Leger");
        System.out.println("2: Pasient");
        System.out.println("3: Legemidler");
        System.out.println("4: Resepter");
        String input = terminal.nextLine();

        switch (input) {
            case "1":
                opprettLege();
                break;
            case "2":
                opprettPasient();
                break;
            case "3":
                opprettLegemidler();
                break;
            case "4":
                opprettResepter();
                break;
            case "5":
                hovedmeny();

            default:
                hovedmeny();
        }
    }

    // Bruker resept pr pasient fra brukerinput
    void printBruk() {
        printHeader("Hvilken pasient vil du se resepter for?");
        int index = 1;
        while (index <= global.hentPasientliste().stoerrelse()) {
            for (Pasient pasient : global.hentPasientliste()) {
                if (pasient.hentID() == index) {
                    System.out
                            .println(pasient.hentID() + ": " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")");
                    index++;
                }
            }
        }
        // printHeader("Hvilken pasient vil du se resepter for?");
        // for (Pasient pasient : global.hentPasientliste()) {
        // System.out.println(pasient.hentID() + ": " + pasient.hentNavn() + " (fnr " +
        // pasient.hentFnr() + ")");
        // }

        String input = terminal.nextLine();

        if (Integer.parseInt(input) > Pasient.hentAntallPasienter() || Integer.parseInt(input) < 0
                || !sjekkInt(input)) {
            System.out.println("Ugyldig pasient valg");
            return;
        }

        Integer pID = Integer.parseInt(input);
        Pasient pasient = global.finnPasient(pID);
        System.out.println("Valgt pasient: " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")");

        if (pasient.hentResepter().stoerrelse() > 0) {
            printHeader("Hvilken resept vil du bruke?");
            for (Resept resept : pasient.hentResepter()) {
                System.out.println(resept.hentID() + ": " + resept.hentLegemiddel().hentNavn() + " ("
                        + resept.hentReit() + " reit)");
            }
        } else {
            System.out.println("Pasient har ingen resepter");
            return;
        }

        input = terminal.nextLine();

        if (Integer.parseInt(input) > Resept.hentAntallResepter() || Integer.parseInt(input) < 0
                || !sjekkInt(input)) {
            System.out.println("Ugyldig resept valg");
            return;
        }
        Boolean riktigResept = false;
        for (Resept res : pasient.hentResepter()) {
            if (res.hentID() == Integer.parseInt(input)) {
                riktigResept = true;
                break;
            }
        }

        if (!riktigResept) {
            System.out.println("Ugyldig resept valg");
            return;
        }

        Integer rID = Integer.parseInt(input);
        Resept resept = global.finnResept(rID);

        resept.bruk();
        System.out
                .println("Resept på " + resept.hentLegemiddel().hentNavn() + " brukt en gang\n" + resept.hentReit()
                        + " bruk igjen");

    }

    // Standard header
    void printHeader(String navn) {
        String border = "==================================";
        System.out.println(border);
        System.out.println(navn);
        System.out.println(border);
    }

    void printLeger() {
        printHeader("Printer ut leger");
        global.skrivUtLeger();
    }

    void printPasient() {
        printHeader("Printer ut pasienter");
        global.skrivUtPasienter();
    }

    void printLegemidler() {
        printHeader("Printer ut legemidler");
        global.skrivUtLegemiddler();
    }

    void printResepter() {
        printHeader("Printer ut resepter pr lege");
        global.skrivUtResepter();
    }

    // Bruker Filhandler til å skrive til fil
    void skrivTilFil() {
        fh.lesTilFil();
    }

    // Printer ut meny for oversikt
    void printOversikt() {
        printHeader("Oversikt");
        System.out.println("1: Leger");
        System.out.println("2: Pasient");
        System.out.println("3: Legemidler");
        System.out.println("4: Resepter");
        System.out.println("5: Statistikk");
        System.out.println("6: Hovedmeny");

        String input = terminal.nextLine();
        switch (input) {
            case "1":
                printLeger();
                break;
            case "2":
                printPasient();
                break;
            case "3":
                printLegemidler();
                break;
            case "4":
                printResepter();
                break;
            case "5":
                printStatistikk();
                break;
            case "6":
                hovedmeny();
                break;
            default:
                hovedmeny();
        }
    }

    // Teller antall utskrevne resepter på vanedannende legemidler
    public void antallVanedannede() {
        int count = 0;
        for (Resept resept : global.hentReseptliste()) {
            if (resept.hentLegemiddel() instanceof Vanedannende) {
                count++;
            }
        }
        System.out.println("Det har blitt skrevet ut" + " " + count + " " + "vanedannenderesepter");

    }

    // Teller antall utskrevne resepter på narkotiske legemidler
    public void antallNarkotiske() {
        int count = 0;
        for (Resept resept : global.hentReseptliste()) {
            if (resept.hentLegemiddel() instanceof Narkotisk) {
                count++;
            }
        }
        System.out.println("Det har blitt skrevet ut" + " " + count + " " + "narkotiskeresepter");
    }

    // Printer ut leger som har skrevet ut narkotiske legemidler
    public void narkoLeger() {
        for (Lege lege : global.hentLegeliste()) {
            int count = 0;
            for (Resept resept : lege.hentUtskrevneResepter()) {
                if (resept.legemiddel instanceof Narkotisk) {
                    count++;
                }
            }
            if (0 < count) {
                System.out.println("Leger som har gitt ut narkotika");
                System.out.println(lege.hentNavn());
                System.out.println("antall narkotiske respeter");
                System.out.println(count);
            }
        }
    }

    // Printer ut pasienter som har resept på narkotiske legemidler
    public void narkoPasienter() {
        for (Pasient pasient : global.hentPasientliste()) {
            int count = 0;
            for (Resept narkotika : pasient.hentResepter()) {
                if (narkotika.hentLegemiddel() instanceof Narkotisk) {
                    count++;

                }
            }
            if (0 < count) {
                System.out.println("Pasienter som bruker narkotika");
                System.out.println(pasient.hentNavn());
                System.out.println("antall narkotiske respeter");
                System.out.println(count);

            }
        }
    }

    // Printer meny for statistikk
    public void printStatistikk() {
        printHeader("Statistikk");
        System.out.println("1: Se total antall utskrevne vanedannende legemidler");
        System.out.println("2: Se totalt antall utskrevne narkotiske legemidler");
        System.out.println("3: Se hvilke leger som har skrevet ut narkotiske resepter og hvor mange");
        System.out.println("4: Se pasienter som har faatt narkotiske resepter og hvor mange");

        String input = terminal.nextLine();
        switch (input) {
            case "1":
                antallVanedannede();
                break;
            case "2":
                antallNarkotiske();
                break;
            case "3":
                narkoLeger();
                break;
            case "4":
                narkoPasienter();
                break;
            default:
                hovedmeny();
        }
    }

    boolean sjekkInt(String element) {
        try {
            Integer.parseInt(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean sjekkDouble(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean sjekkLMType(String type) {
        if (type.equals("narkotisk") || type.equals("vanedannende") || type.equals("vanlig"))
            return true;
        else
            return false;
    }

    boolean sjekkReseptType(String type) {
        System.out.println(type);
        if (type.equals("blaa") || type.equals("hvit") || type.equals("p") || type.equals("militaer"))
            return true;
        else
            return false;
    }

    boolean sjekkLegemiddel(String navn) {
        for (Legemiddel legemiddel : global.hentMiddelliste()) {
            if (navn.equals(legemiddel.hentNavn()))
                return true;
        }
        return false;
    }

    boolean sjekkLege(String navn) {
        for (Lege lege : global.hentLegeliste()) {
            if (navn.equals(lege.hentNavn()))
                return true;
        }
        return false;
    }

    boolean sjekkPasient(String navn) {
        for (Pasient pasient : global.hentPasientliste()) {
            if (navn.equals(pasient.hentNavn()))
                return true;
        }
        return false;
    }
}
