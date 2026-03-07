package no.nav;

import no.nav.dagpenger.AutomatiskSaksbehandler;
import no.nav.dagpenger.DagpengerKalkulator;
import no.nav.dagpenger.DagpengerVerktøy;
import no.nav.dagpenger.Saksbehandler;
import no.nav.data.SøknadsArkiv;
import no.nav.data.TilfeldigDataProdusent;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;
import no.nav.modell.Årslønn;


public class Main {
    public static void main(String[] args) {

        DagpengerKalkulator dagpengerKalkulator = new DagpengerKalkulator();
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2023, 500000));
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2022, 450000));
        dagpengerKalkulator.leggTilÅrslønn(new Årslønn(2021, 400000));
        System.out.println("---🤖 Kalkulerer dagsats... 🤖---");
        System.out.println("Personen har rett på følgende dagsats: " + dagpengerKalkulator.kalkulerDagsats());
        System.out.println("---🤖 Dagsats ferdig kalkulert 🤖---");


        System.out.println("\n------------------------\n");

        //Søknadsarkivet vil oppdatere datakilden til begge saksbehandlerne.
        SøknadsArkiv søknadsArkiv = TilfeldigDataProdusent.genererSøknadsArkiv();
        AutomatiskSaksbehandler automatiskSaksbehandler = new AutomatiskSaksbehandler(søknadsArkiv);
        Saksbehandler saksbehandler = new Saksbehandler(Status.InnvilgetMedMaksSats, søknadsArkiv);

        System.out.println(saksbehandler.hentSpesialisering());
        automatiskSaksbehandler.prosesserDagpengerSøknader();
        int count = 0;
        for (DagpengerSøknad søknad : saksbehandler.hentSøknaderForGjennomgang()) {
            System.out.println("Status for søknad: " + søknad.hentSøknadsstatus());
            System.out.println(count);
            count ++;
            for (Årslønn årslønn : søknad.hentÅrslønner()) {
                System.out.println("Årslønn for " + årslønn.hentÅretForLønn()
                        + " var " + årslønn.hentÅrslønn());
            }
        }
    }

}