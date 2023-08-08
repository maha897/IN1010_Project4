import java.util.Scanner;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.Math;

public class Filhandler {

    Oversikt global;
    File nyfil;
    Filhandler fh;

    Filhandler(String navn, Oversikt oversikt) {

        nyfil = new File(navn);
        global = oversikt;

        lesFraFil();
    }

    void lesFraFil() {
        // i teller hvilken # vi er på i .txt filen
        // Separerer hvilke objekt som skal lages
        int i = 0;
        Scanner leser = null;
        try {
            leser = new Scanner(nyfil);
        } catch (FileNotFoundException e) {
            System.out.println("fant ikke fil");
            System.exit(-1);
        }

        while (leser.hasNextLine()) {
            String linje = leser.nextLine();
            String[] detaljer = linje.split(",");
            if (linje.charAt(0) == '#') {
                i++;
                continue;
            }

            // PASIENTER
            if (i == 1) {
                global.opprettPasient(detaljer[0], detaljer[1]);
            }
            // LEGEMIDLER
            if (i == 2) {
                int styrke = 0;

                if (detaljer.length == 5) {
                    styrke = Integer.parseInt(detaljer[4].trim());
                }

                global.opprettLegemidler(detaljer[0], detaljer[1],
                        (int) Math.round((Double.parseDouble(detaljer[2].trim()))),
                        Double.parseDouble(detaljer[3]), styrke);
            }
            // LEGER
            if (i == 3) {
                global.opprettLege(detaljer[0], detaljer[1]);
            }
            // RESEPTER
            if (i == 4) {
                int reit = 0;
                if (detaljer.length == 5) {
                    reit = Integer.parseInt(detaljer[4]);
                }

                global.opprettResepter(Integer.parseInt(detaljer[0].trim()), detaljer[1],
                        Integer.parseInt(detaljer[2]), detaljer[3], reit);
            }
        }
    }

    public void lesTilFil() {

        try {
            FileWriter outFile = new FileWriter("filename.txt");
            // Pasienter
            outFile.write("# Pasienter (navn, fnr)\n");
            for (Pasient pasient : global.hentPasientliste()) {
                outFile.write(pasient.hentNavn() + "," + pasient.hentFnr() + "\n");
            }

            // Legemidler
            outFile.write("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");
            for (Legemiddel lm : global.hentMiddelliste()) {
                if (lm instanceof Narkotisk) {
                    Narkotisk narkotisk = (Narkotisk) lm;
                    outFile.write(lm.hentNavn() + ",narkotisk," + lm.hentPris() + "," + lm.hentVirkestoff() + ","
                            + narkotisk.hentNarkotiskStyrke() + "\n");
                } else if (lm instanceof Vanedannende) {
                    Vanedannende vanedannende = (Vanedannende) lm;
                    outFile.write(lm.hentNavn() + ",vanedannende," + lm.hentPris() + "," + lm.hentVirkestoff() + ","
                            + vanedannende.hentVanedannendeStyrke() + "\n");
                } else if (lm instanceof Vanlig) {
                    outFile.write(lm.hentNavn() + ",vanlig," + lm.hentPris() + "," + lm.hentVirkestoff() + "\n");
                }
            }

            // Leger
            outFile.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege lege : global.hentLegeliste()) {
                if (lege instanceof Spesialist) {
                    Spesialist spesialist = (Spesialist) lege;
                    outFile.write(lege.hentNavn() + "," + spesialist.hentKontrollID() + "\n");
                } else if (lege instanceof Lege) {
                    outFile.write(lege.hentNavn() + ",0\n");
                }
            }

            // Resepter
            outFile.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])" + "\n");
            for (Lege lege : global.hentLegeliste()) {
                for (Resept resept : lege.hentUtskrevneResepter()) {
                    if (resept instanceof BlaaResept) {
                        outFile.write(resept.hentID() + "," + lege.hentNavn() + "," + resept.hentPasientID() + ",blaa,"
                                + resept.hentReit() + "\n");
                    } else if (resept instanceof MilResept) {
                        outFile.write(resept.hentID() + "," + lege.hentNavn() + "," + resept.hentPasientID()
                                + ",militaer" + "\n");
                    } else if (resept instanceof PResept) {
                        outFile.write(resept.hentID() + "," + lege.hentNavn() + "," + resept.hentPasientID() + ",p,"
                                + resept.hentReit() + "\n");
                    } else if (resept instanceof HvitResept) {
                        outFile.write(resept.hentID() + "," + lege.hentNavn() + "," + resept.hentPasientID() + ",hvit,"
                                + resept.hentReit() + "\n");
                    } else if (resept instanceof HvitResept) {
                        outFile.write(resept.hentID() + "," + lege.hentNavn() + "," + resept.hentPasientID() + ",p,"
                                + resept.hentReit() + "\n");
                    }
                }
            }
            outFile.close();
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
}
