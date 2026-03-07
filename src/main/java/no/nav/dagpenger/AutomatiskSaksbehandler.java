package no.nav.dagpenger;

import no.nav.data.SøknadsArkiv;
import no.nav.modell.DagpengerSøknad;
import no.nav.modell.Status;
import no.nav.modell.Årslønn;

public class AutomatiskSaksbehandler {
    private final SøknadsArkiv søknadsArkiv;

    public AutomatiskSaksbehandler(SøknadsArkiv søknadsArkiv) {
        this.søknadsArkiv = søknadsArkiv;
    }

    /**
     * Vurderer søknadene i arkivet mot reglene i kalkulatoren automatisk
     * @return Søknadsarkiv med anbefalt søknadsutfall lagt til i hver ubehandlede søknad
     * */
    public SøknadsArkiv prosesserDagpengerSøknader() {
        for (DagpengerSøknad dagpengerSøknad : this.søknadsArkiv.hentAlleSøknader()) {

            //Unngår at ferdigstilte søknader behandles på nytt
            if (dagpengerSøknad.hentSøknadsstatus() != null) {
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
        return this.søknadsArkiv;
    }
}
