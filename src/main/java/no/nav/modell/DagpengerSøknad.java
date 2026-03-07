package no.nav.modell;

import java.util.ArrayList;

public class DagpengerSøknad {
    private ArrayList<Årslønn> årslønner;
    private Status endeligSøknadsstatus;
    private Status midlertidigSøknadsstatus;

    public DagpengerSøknad(ArrayList<Årslønn> årslønner) {
        this.årslønner = årslønner;
    }

    public ArrayList<Årslønn> hentÅrslønner() {
        return this.årslønner;
    }

    public Status hentMidlertidigSøknadsstatus() {
        return this.midlertidigSøknadsstatus;
    }

    public void settMidlertidigSøknadsstatus(Status søknadsstatus) {
        this.midlertidigSøknadsstatus = søknadsstatus;
    }

    public Status hentSøknadsstatus() {
        return this.endeligSøknadsstatus;
    }

    public void ferdigstillSøknad(Status søknadsstatus) {
        this.endeligSøknadsstatus = søknadsstatus;
    }
}
