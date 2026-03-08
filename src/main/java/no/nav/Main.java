package no.nav;

import no.nav.dagpenger.AutomatiskSaksbehandler;
import no.nav.dagpenger.Saksbehandler;
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
/*

        /// Oppretter alle nødvendive instanser og variable

        SøknadsArkiv søknadsArkiv = TilfeldigDataProdusent.genererSøknadsArkiv();
        AutomatiskSaksbehandler automatiskSaksbehandler = new AutomatiskSaksbehandler(søknadsArkiv);
        Saksbehandler innvilgetMaksSaksbehandler = new Saksbehandler(Status.INNVILGET_MED_MAKS_SATS, søknadsArkiv);
        Saksbehandler innvilgetSaksbehandler = new Saksbehandler(Status.INNVILGET, søknadsArkiv);
        Saksbehandler avslagSaksbehandler = new Saksbehandler(Status.AVSLAG_FOR_LAV_INNTEKT, søknadsArkiv);
        int teller, antallInnvilget, antallInnvilgetMaks, antallAvslag;


        /// Sjekker om saksbehandler har tilgang til søknader før de er forhåndsbehandlet
        //Altså en test hvor svaret bør være 0

        teller = 0;
        for (DagpengerSøknad søknad : innvilgetMaksSaksbehandler.hentSøknaderForGjennomgang()) {
            teller++;
        }
        System.out.println("\nSøknader hentet av innvilgetMaksSaksbehandler før forhåndsbehandling, skal være 0: \n" + teller);


        /// Forhåndsbehandler alle søknadene i søknadsArkiv

        automatiskSaksbehandler.forhåndsbehandleDagpengerSøknader();


        /// Lar tre saksbehandlere med forskjellig tilgang hente ut "sine" søknader

        ArrayList<DagpengerSøknad> innvilgetMaksSøknader = innvilgetMaksSaksbehandler.hentSøknaderForGjennomgang();
        ArrayList<DagpengerSøknad> innvilgetSøknader = innvilgetSaksbehandler.hentSøknaderForGjennomgang();
        ArrayList<DagpengerSøknad> avslagSøknader = avslagSaksbehandler.hentSøknaderForGjennomgang();


        /// Teller antall søknader i hver saksbehandlers liste og printer summen

        teller = 0;
        for (DagpengerSøknad søknad : innvilgetMaksSøknader) {
            teller++;
        }
        System.out.println("\nSøknader hentet av innvilgetMaksSaksbehandler etter forhåndsbehandling: \n" + teller);

        teller = 0;
        for (DagpengerSøknad søknad : innvilgetSøknader) {
            teller++;
        }
        System.out.println("\nSøknader hentet av innvilgetSaksbehandler etter forhåndsbehandling: \n" + teller);

        teller = 0;
        for (DagpengerSøknad søknad : avslagSøknader) {
            teller++;
        }
        System.out.println("\nSøknader hentet av avslagSaksbehandler etter forhåndsbehandling: \n" + teller);

        /// Saksbehandler foretar en vurdering

*/

        System.out.println("\n------------ Kjempefin og kul demo ----------------\n");

        SøknadsArkiv søknadsArkiv = TilfeldigDataProdusent.genererSøknadsArkiv();
        AutomatiskSaksbehandler automatiskSaksbehandler = new AutomatiskSaksbehandler(søknadsArkiv);
        Scanner scanner = new Scanner(System.in);
        Saksbehandler saksbehandler;
        ArrayList<DagpengerSøknad> hentedeSøknader;

        ArrayList<Årslønn> årslønner = new ArrayList<>();
        DagpengerSøknad nesteSøknad = new DagpengerSøknad(årslønner);
        int teller;

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
                System.out.println("\n\n Ugyldig svar, kun tallene 1, 2 & 3 er gyldige.\n");
            }
        }

        while (true) {
            System.out.println("~~*~~ Her er kontrollpanelet ditt! ~~*~~\nHva vil du gjøre?");
            System.out.println("1. Hent neste ventende søknad\n2. Logg ut");
            int svar = scanner.nextInt();

            if (svar > 3 && svar < 0) {
                System.out.println("Ugyldig svar, kun tallene 1 & 2 er gyldige.");
            } else if (svar == 2) {
                System.out.println("~~*~~ Farvel! ~~*~~");
                System.exit(0);
            } else if (svar == 1){
                teller = 0;
                hentedeSøknader = saksbehandler.hentSøknaderForGjennomgang();

                if (hentedeSøknader.isEmpty()) {
                    System.out.println("Ingen ventende søknader. Pausetid!");
                    System.exit(0);
                } else {
                    for (DagpengerSøknad søknad : hentedeSøknader) {
                        teller++;
                    }
                    nesteSøknad = hentedeSøknader.get(0);
                    System.out.println("Du har " + teller + " søknader i listen din.");
                    System.out.println("Neste søknad:\n\n" + nesteSøknad.toString());
                }

                ///
                scanner.nextLine();
                System.out.println("Press 'enter' for å håndtere søknaden.");
                scanner.nextLine();

                System.out.println("Velg utfall for denne søknaden: [1, 2, 3]\n1. Avslag\n2. Innvilget\n" +
                        "3. Innvilget med makssats\n");
                int valg = scanner.nextInt();

                if (valg == 1) {
                    nesteSøknad.ferdigstillSøknad(Status.AVSLAG_FOR_LAV_INNTEKT);
                } else if (valg == 2) {
                    nesteSøknad.ferdigstillSøknad(Status.INNVILGET);
                } else if (valg == 3) {
                    nesteSøknad.ferdigstillSøknad(Status.INNVILGET_MED_MAKS_SATS);
                } else {
                    System.out.println("Ugyldig svar, kun tallene 1, 2 & 3 er gyldige.");
                }

                System.out.println("\nSøknaden er ferdigstilt, med vurdering " + nesteSøknad.hentSøknadsstatus()+".\n\n");
            }
        }
    }
}