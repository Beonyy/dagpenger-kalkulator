package no.nav.data;

import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Årslønn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/// Spinner opp noe eksempeldata å teste på.
public class TilfeldigDataProdusent {

    public static ArrayList<DagpengerSøknad> genererSøknadsArkiv() {
        SøknadsArkiv søknadsArkiv = new SøknadsArkiv();
        ArrayList<DagpengerSøknad> listeOverSøknader = søknadsArkiv.hentListeOverSøknader();
        Random random = new Random();
        int maksLønn = 450000;
        int minimumsLønn = 0;
        int maksÅrstall = LocalDate.now().getYear() - 1;
        int minimumsÅrstall = LocalDate.now().getYear() - 3;

        Random tilfeldig = new Random();
        int år;
        int lønn;
        for (int i = 0; i < 101; i++) {
            ArrayList<Årslønn> årslønner = new ArrayList<Årslønn>();
            for (int j = 0; j < 4; j++) {
                if (tilfeldig.nextInt(5) == 0) {
                    lønn = 0;
                } else {
                    lønn = tilfeldig.nextInt(maksLønn - minimumsLønn + 1) + minimumsLønn;
                }
                år = tilfeldig.nextInt(maksÅrstall - minimumsÅrstall + 1) + minimumsÅrstall;
                Årslønn årslønn = new Årslønn(år, lønn);
                årslønner.add(årslønn);
            }
            DagpengerSøknad dagpengerSøknad = new DagpengerSøknad(årslønner);
            listeOverSøknader.add(dagpengerSøknad);
        }
        return listeOverSøknader;
    }
}

