package no.nav.modell;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Holder på all informasjon om en søknad som er nødvendig for saksbehandling.
 *  {@link #midlertidigSøknadsstatus} holder på en midlertidig vurdering, brukes for
 *  å tilgangsstyre hvilke(n) {@link no.nav.kontroller.Saksbehandler} som skal ha
 *  tilgang til en gitt søknad. Saksbehandleren setter manuelt en {@link #endeligSøknadsstatus},
 *  som lagrer det endelige resultatet.
 */
public class DagpengerSøknad {
    /*
    TODO:årslønner holder ukritisk på listen over årslønner den får servert,
     bør benytte verktøy (fra DagpengerKalkulator) til å sikre dette.
    */
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

    public Status hentEndeligSøknadsstatus() {
        return this.endeligSøknadsstatus;
    }

    /**
     * Gjør det mulig å få en enkel visuell oversikt, erstatter rådatabehandling
     * @return Tekst med all informasjon relevant til dagpengesøknadsbehandling
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Foreløpig søknadsstatus er: " + this.hentMidlertidigSøknadsstatus() +
                "\nBasert på disse årslønningene:\n");

        for (Årslønn årslønn : this.årslønner) {
            if (årslønn.hentÅretForLønn() < LocalDate.now().getYear() &&
                    årslønn.hentÅretForLønn() > LocalDate.now().getYear() - 4) {

                string.append(årslønn.hentÅretForLønn() + ": " + årslønn.hentÅrslønn() + "\n");
            }
        }
        if (this.hentEndeligSøknadsstatus() != null) {
            string.append("Søknaden er allerede ferdigstilt, med resultatet " + this.hentEndeligSøknadsstatus());
        } else {
            string.append("Søknaden trenger ferdigstilling.");
        }

        return string.toString();
    }

    /**
     * Setter det endelige resultatet av søknadsvurderingen.
     */
    public void ferdigstillSøknad(Status søknadsstatus) {
        this.endeligSøknadsstatus = søknadsstatus;
    }
}
