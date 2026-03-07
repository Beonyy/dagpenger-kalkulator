package no.nav.data;

import no.nav.modell.DagpengerSøknad;

import java.util.ArrayList;

public class SøknadsArkiv {
    private final ArrayList<DagpengerSøknad> listeOverSøknader;

    public SøknadsArkiv(ArrayList<DagpengerSøknad> listeOverSøknader) {
        this.listeOverSøknader = listeOverSøknader;
    }

    public ArrayList<DagpengerSøknad> hentAlleSøknader() {
        return this.listeOverSøknader;
    }
}
