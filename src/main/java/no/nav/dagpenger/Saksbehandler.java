package no.nav.dagpenger;

import no.nav.data.SøknadsArkiv;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;

import java.util.ArrayList;

/**
 * Fasiliterer arbeidet til en menneskelig saksbehandler, inneholder tilgangskontroll
 *  slik at saksbehandlere kun kan behandle søknader som er blitt forhåndsvurdert
 *  til å falle innen sin egen spesialitet.
 * <b>MERK:</b> Bruk av klassen krever at angitt {@link SøknadsArkiv} først behandles av
 *  en {@link AutomatiskSaksbehandler}.
 */
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

    /**
     * Ser gjennom alle søknader i angitt {@link SøknadsArkiv}, henter ut de som samsvarer
     *  med denne Saksbehandlerens {@link #spesialisering}.
     * @return liste over forhåndsbehandlede søknader som faller innenfor
     *  saksbehandlerens spesialitet.
     */
    public ArrayList<DagpengerSøknad> hentSøknaderForGjennomgang() {
        ArrayList<DagpengerSøknad> søknaderForGjennomgang = new ArrayList<>();

        for (DagpengerSøknad søknad : this.søknadsArkiv.hentAlleSøknader()) {
            if (søknad.hentMidlertidigSøknadsstatus() == this.spesialisering) {
                søknaderForGjennomgang.add(søknad);
            }
        }
        return søknaderForGjennomgang;
    }
}
