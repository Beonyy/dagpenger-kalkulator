package no.nav.data;

import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Årslønn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Er illustrerende kilde til data, som vil genereres omtrent tilfeldig,
 *  forsøkt å passe antatt data systemet vil måtte håndtere. Se regelsett fra
 *  DagpengeKalkulator.
 * Det er lagt inn en 1/5 sjanse for årsinntekt på 0, og 1/5 sjanse for
 *  årsinntekt på 1 000 000.
 * Legger kun inn data for de 3 siste fullførte årene.
 */
public class TilfeldigDataProdusent {


    public static SøknadsArkiv genererSøknadsArkiv() {
        //SøknadsArkiv søknadsArkiv = new SøknadsArkiv();
        //ArrayList<DagpengerSøknad> listeOverSøknader = søknadsArkiv.hentAlleSøknader();

        ArrayList<DagpengerSøknad> listeOverSøknader = new ArrayList<>();
        Random tilfeldig = new Random();
        int inneværendeÅr = LocalDate.now().getYear();
        int maksLønn = 450000;
        int minimumsLønn = 0;
        int år, lønn;

        for (int i = 0; i < 101; i++) {
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

