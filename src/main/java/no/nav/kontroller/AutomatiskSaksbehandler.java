package no.nav.kontroller;

import no.nav.data.SøknadsArkiv;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;
import no.nav.modell.Årslønn;

/**
 * TODO: Finne bedre klassenavn?
 *Foretar de(n) automatiserte prosessene en søknad kan gå gjennom før de behandles
 *  av en menneskelig saksbehandler.
 */
public class AutomatiskSaksbehandler {
    private final SøknadsArkiv søknadsArkiv;

    public AutomatiskSaksbehandler(SøknadsArkiv søknadsArkiv) {
        this.søknadsArkiv = søknadsArkiv;
    }

    /**
     * Vurderer søknadene i arkivet mot reglene i kalkulatoren,
     *  et av tre mulige utfall registreres som en midlertidig vurdering for hver søknad.
     * Endringene skjer i objektene i {@link SøknadsArkiv}et som ble gitt
     *  ved objektopprettelse.
     * */
    public void forhåndsbehandleDagpengerSøknader() {
        for (DagpengerSøknad dagpengerSøknad : this.søknadsArkiv.hentAlleSøknader()) {

            //For fremtidige tilfeller hvor ny data potenielt hentes inn i samme søknadsArkiv.
            if (dagpengerSøknad.hentEndeligSøknadsstatus() != null) {
                continue;
            }

            DagpengerKalkulator dagpengerKalkulator = new DagpengerKalkulator();

            for (Årslønn årslønn : dagpengerSøknad.hentÅrslønner()) {
                dagpengerKalkulator.leggTilÅrslønn(årslønn);
            }

            if (dagpengerKalkulator.harRettigheterTilDagpenger()) {
                if (dagpengerKalkulator.velgBeregningsMetode().equals("MAKS_ÅRLIG_DAGPENGERGRUNNLAG")) {
                    dagpengerSøknad.settMidlertidigSøknadsstatus(Status.INNVILGET_MED_MAKS_SATS);
                } else {
                    dagpengerSøknad.settMidlertidigSøknadsstatus(Status.INNVILGET);
                }
            } else {
                dagpengerSøknad.settMidlertidigSøknadsstatus(Status.AVSLAG_FOR_LAV_INNTEKT);
            }
        }
    }
}
