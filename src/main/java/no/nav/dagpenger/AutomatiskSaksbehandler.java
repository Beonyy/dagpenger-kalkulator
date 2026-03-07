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

    public SøknadsArkiv prosesserDagpengerSøknader() {
        for (DagpengerSøknad dagpengerSøknad : this.søknadsArkiv.hentAlleSøknader()) {
            DagpengerKalkulator dagpengerKalkulator = new DagpengerKalkulator();

            for (Årslønn årslønn : dagpengerSøknad.hentÅrslønner()) {
                dagpengerKalkulator.leggTilÅrslønn(årslønn);
            }

            if (dagpengerKalkulator.harRettigheterTilDagpenger()) {
                if (dagpengerKalkulator.velgBeregningsMetode().equals("MAKS_ÅRLIG_DAGPENGERGRUNNLAG")) {
                    dagpengerSøknad.settSøknadsstatus(Status.InnvilgetMedMaksSats);
                } else {
                    dagpengerSøknad.settSøknadsstatus(Status.Innvilget);
                }
            } else {
                dagpengerSøknad.settSøknadsstatus(Status.AvslagForLavInntekt);
            }
        }
        return this.søknadsArkiv;
    }
}
