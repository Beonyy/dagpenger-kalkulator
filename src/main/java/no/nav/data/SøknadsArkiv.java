package no.nav.data;

import no.nav.modell.DagpengerSøknad;

import java.util.ArrayList;

public class SøknadsArkiv {
    private ArrayList<DagpengerSøknad> listeOverSøknader = new ArrayList<>();

    public ArrayList<DagpengerSøknad> hentListeOverSøknader() {
        return this.listeOverSøknader;
    }
}
