package no.nav.data;

import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Årslønn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *Er illustrerende kilde til data, som vil genereres omtrent tilfeldig.
 */
public class TilfeldigDataProdusent {

    /**
     *Inneholder justeringer i "tilfeldigheten" for å tvinge frem tilfeller av alternativene i {@link no.nav.modell.Status}.
     * Se regelsett fra {@link no.nav.dagpenger.DagpengerKalkulator}.
     * Det er lagt inn en 20% sjanse for årsinntekt på 0 for hvert år, 20% sjanse for årsinntekt på 1 000 000
     * for hvert år, og de resterende tilfellene får et tilfeldig tall for lønn mellom 0 og 450 000.
     * <b>MERK: </b>Legger kun inn data for de 3 siste fullførte årene.
     * @return Et nytt {@link SøknadsArkiv} med generert testdata.
     */
    //Kan ved behov lage en override som tar en eksisterende liste som argument,
    //og legger til nye ubehandlede søknader.
    public static SøknadsArkiv genererSøknadsArkiv() {

        ArrayList<DagpengerSøknad> listeOverSøknader = new ArrayList<>();
        Random tilfeldig = new Random();
        int inneværendeÅr = LocalDate.now().getYear();
        int maksLønn = 450000;
        int minimumsLønn = 0;
        int år, lønn;

        for (int i = 0; i < 100; i++) {
            ArrayList<Årslønn> årslønner = new ArrayList<Årslønn>();
            for (int j = 1; j <= 3; j++) {
                år = inneværendeÅr - j;
                int femtedelsSjanse = tilfeldig.nextInt(5);

                if (femtedelsSjanse == 0) {
                    lønn = 0;
                } else if (femtedelsSjanse == 4) {
                    lønn = 1000000;
                }
                else {
                    lønn = tilfeldig.nextInt(maksLønn - minimumsLønn + 1) + minimumsLønn;
                }

                Årslønn årslønn = new Årslønn(år, lønn);
                årslønner.add(årslønn);
            }
            DagpengerSøknad dagpengerSøknad = new DagpengerSøknad(årslønner);
            listeOverSøknader.add(dagpengerSøknad);
        }
        return new SøknadsArkiv(listeOverSøknader);
    }
}

