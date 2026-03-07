package no.nav.dagpenger;

import no.nav.data.SøknadsArkiv;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;

import java.util.ArrayList;

public class Saksbehandler {
    private final Status spesialisering;
    private final SøknadsArkiv søknadsArkiv;

    public Saksbehandler(Status spesialisering, SøknadsArkiv søknadsArkiv) {
        this.spesialisering = spesialisering;
        this.søknadsArkiv = søknadsArkiv;
    }

    public Status hentSpesialisering() {
        return this.spesialisering;
    }


    public ArrayList<DagpengerSøknad> hentSøknaderForGjennomgang() {
        ArrayList<DagpengerSøknad> søknaderForGjennomgang = new ArrayList<>();
        for (DagpengerSøknad søknad : this.søknadsArkiv.hentAlleSøknader()) {
            if (søknad.hentSøknadsstatus() == this.spesialisering) {
                søknaderForGjennomgang.add(søknad);
            }
        }
        return søknaderForGjennomgang;
    }
}
