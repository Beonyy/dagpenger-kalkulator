package no.nav;

import no.nav.kontroller.AutomatiskSaksbehandler;
import no.nav.kontroller.Saksbehandler;
import no.nav.data.SøknadsArkiv;
import no.nav.data.TilfeldigDataProdusent;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;
import no.nav.modell.Årslønn;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
/*
        DagpengerKalkulator dagpengerKalkulator = new DagpengerKalkulator();
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2023, 500000));
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2022, 450000));
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2021, 400000));
        System.out.println("---🤖 Kalkulerer dagsats... 🤖---");
        System.out.println("Personen har rett på følgende dagsats: " + dagpengerKalkulator.kalkulerDagsats());
        System.out.println("---🤖 Dagsats ferdig kalkulert 🤖---");
*/

        System.out.println("\n------------ Kjempefin og kul demo ----------------\n");

        SøknadsArkiv søknadsArkiv = TilfeldigDataProdusent.genererSøknadsArkiv();
        AutomatiskSaksbehandler automatiskSaksbehandler = new AutomatiskSaksbehandler(søknadsArkiv);
        Scanner scanner = new Scanner(System.in);
        Saksbehandler saksbehandler;
        ArrayList<DagpengerSøknad> hentedeSøknader;

        ArrayList<Årslønn> årslønner = new ArrayList<>();
        DagpengerSøknad nesteSøknad = new DagpengerSøknad(årslønner);

        //forhåndsbehandler søknadene
        automatiskSaksbehandler.forhåndsbehandleDagpengerSøknader();


        while (true) {
            System.out.println("~~*~~ Hei! Velkommen på jobb på dagpengesenteret, på tide å logge inn. ~~*~~\n" +
                    "Du kan autentisere deg med tre spesialiteter:\n1. Avslag\n2. Innvilget\n" +
                    "3. Innvilget med makssats\nHva er din spesialisering i dag? [1, 2, 3]");
            int svar = scanner.nextInt();

            if (svar < 4 && svar > 0) {
                if (svar == 1) {
                    saksbehandler = new Saksbehandler(Status.AVSLAG_FOR_LAV_INNTEKT, søknadsArkiv);
                    break;
                } else if (svar == 2) {
                    saksbehandler = new Saksbehandler(Status.INNVILGET, søknadsArkiv);
                    break;
                } else if (svar == 3){
                    saksbehandler = new Saksbehandler(Status.INNVILGET_MED_MAKS_SATS, søknadsArkiv);
                    break;
                }
            } else {
                System.out.println("\n\nUgyldig svar, kun tallene 1, 2 & 3 er gyldige.\n");
            }
        }

        while (true) {
            System.out.println("~~*~~ Her er kontrollpanelet ditt! ~~*~~\nHva vil du gjøre?");
            System.out.println("1. Hent neste ventende søknad\n2. Logg ut");
            int svar = scanner.nextInt();

            if (svar > 2 || svar < 1) {
                System.out.println("\nUgyldig svar, kun tallene 1 & 2 er gyldige.\n");
            } else if (svar == 2) {
                System.out.println("~~*~~ Farvel! ~~*~~");
                System.exit(0);
            } else if (svar == 1){
                hentedeSøknader = saksbehandler.hentSøknaderForGjennomgang();

                if (hentedeSøknader.isEmpty()) {
                    System.out.println("Ingen ventende søknader. Pausetid!");
                    System.exit(0);
                } else {
                    nesteSøknad = hentedeSøknader.get(0);
                    System.out.println("Du har " + hentedeSøknader.size() + " søknader i listen din.");
                    System.out.println("Neste søknad:\n\n" + nesteSøknad.toString());
                }

                scanner.nextLine();
                System.out.println("Press 'enter' for å håndtere søknaden.");
                scanner.nextLine();

                /// Implementasjonen her kunne godt brukt en dedikert while-loop,
                /// og gitt advarsler dersom ny vurdering avviker fra forhåndsvurderingen. "Er du sikker?"
                boolean ferdigstilt = false;
                while (!ferdigstilt) {
                    System.out.println("Velg utfall for denne søknaden: [1, 2, 3, 4]\n1. Avslag\n2. Innvilget\n" +
                            "3. Innvilget med makssats\n4. Bekreft eksisterende vurdering");
                    int valg = scanner.nextInt();

                    if (valg == 1) {
                        nesteSøknad.ferdigstillSøknad(Status.AVSLAG_FOR_LAV_INNTEKT);
                        ferdigstilt = true;
                    } else if (valg == 2) {
                        nesteSøknad.ferdigstillSøknad(Status.INNVILGET);
                        ferdigstilt = true;
                    } else if (valg == 3) {
                        nesteSøknad.ferdigstillSøknad(Status.INNVILGET_MED_MAKS_SATS);
                        ferdigstilt = true;
                    } else if (valg == 4){
                        nesteSøknad.ferdigstillSøknad(nesteSøknad.hentMidlertidigSøknadsstatus());
                        ferdigstilt = true;
                    }
                    else {
                        System.out.println("\nUgyldig svar, kun tallene 1, 2, 3 & 4 er gyldige.\n");
                    }
                }
                System.out.println("\nSøknaden er ferdigstilt, med vurdering " + nesteSøknad.hentEndeligSøknadsstatus()+".\n\n");
            }
        }
    }
}