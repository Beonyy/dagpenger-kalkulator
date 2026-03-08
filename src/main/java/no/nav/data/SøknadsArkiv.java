package no.nav.data;

import no.nav.modell.DagpengerSøknad;

import java.util.ArrayList;

/**
 * En midlertidig beholder for data, uavhengig av kilde.
 * Brukes og oppdateres av både {@link no.nav.kontroller.AutomatiskSaksbehandler}
 *  og {@link no.nav.kontroller.Saksbehandler}.
 */
public class SøknadsArkiv {
    //TODO: Opprette to lister: "behandlede" og "ubehandlede" søknader,
    // saksbehandlerens metode for vurdering flytter også fra en til den andre,
    // hindrer potensielt iterasjon over ferdige søknader i det hele tatt.

    private final ArrayList<DagpengerSøknad> listeOverSøknader;

    public SøknadsArkiv(ArrayList<DagpengerSøknad> listeOverSøknader) {
        this.listeOverSøknader = listeOverSøknader;
    }

    public ArrayList<DagpengerSøknad> hentAlleSøknader() {
        return this.listeOverSøknader;
    }
}
