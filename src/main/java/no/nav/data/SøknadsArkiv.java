package no.nav.data;

import no.nav.modell.DagpengerSøknad;

import java.util.ArrayList;

public class SøknadsArkiv {
    //TODO: Opprette to lister: "behandlede" og "ubehandlede" søknader,
    // saksbehandlerens metode for vurdering flytter også fra en til den andre,
    // hindrer potensielt iterasjon over ferdige søknader i det hele tatt.

    private final ArrayList<DagpengerSøknad> listeOverSøknader;

    public SøknadsArkiv(ArrayList<DagpengerSøknad> listeOverSøknader) {
        this.listeOverSøknader = new ArrayList<>();
    }

    public ArrayList<DagpengerSøknad> hentAlleSøknader() {
        return this.listeOverSøknader;
    }
}
