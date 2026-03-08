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
     * TODO: vurdere output-formatet her. Hensiktsmessig å lage en toString? Bedre å la frontend gjøre som de vil?
     * Ser gjennom alle søknader i angitt {@link SøknadsArkiv}, henter ut de søknadene som samsvarer
     *  med denne Saksbehandlerens {@link #spesialisering} og som ikke er ferdigstilte.
     * @return liste over forhåndsbehandlede søknader som faller innenfor saksbehandlerens spesialitet.
     */
    public ArrayList<DagpengerSøknad> hentSøknaderForGjennomgang() {
        ArrayList<DagpengerSøknad> søknaderForGjennomgang = new ArrayList<>();

        for (DagpengerSøknad søknad : this.søknadsArkiv.hentAlleSøknader()) {

            //filtrerer ut de som ikke tilhører spesialiteten eller allerede har en endelig søknadsstatus
            if (søknad.hentMidlertidigSøknadsstatus() == this.spesialisering && søknad.hentSøknadsstatus() == null) {
                søknaderForGjennomgang.add(søknad);
            }
        }
        return søknaderForGjennomgang;
    }
}
