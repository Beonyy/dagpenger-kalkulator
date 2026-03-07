package no.nav.modell;

import java.util.ArrayList;

public class DagpengerSøknad {
    private ArrayList<Årslønn> årslønner;
    private Søknadsstatus søknadsstatus;

    private enum Søknadsstatus {
        AvslagForLavInntekt,
        Innvilget,
        InnvilgetMedMakssats,
    }

    public DagpengerSøknad(ArrayList<Årslønn> årslønner) {
        this.årslønner = årslønner;
    }

    public ArrayList<Årslønn> hentÅrslønner() {
        return this.årslønner;
    }

    public Søknadsstatus hentSøknadsstatus() {
        return this.søknadsstatus;
    }

    public void settSøknadsstatus(Søknadsstatus søknadsstatus) {
        this.søknadsstatus = søknadsstatus;
    }
}
