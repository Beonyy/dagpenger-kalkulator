package no.nav.modell;

import java.util.ArrayList;

public class DagpengerSøknad {
    private ArrayList<Årslønn> årslønner;
    private Status søknadsstatus;

    public DagpengerSøknad(ArrayList<Årslønn> årslønner) {
        this.årslønner = årslønner;
    }

    public ArrayList<Årslønn> hentÅrslønner() {
        return this.årslønner;
    }

    public Status hentSøknadsstatus() {
        return this.søknadsstatus;
    }

    public void settSøknadsstatus(Status søknadsstatus) {
        this.søknadsstatus = søknadsstatus;
    }
}
