package no.nav.kontroller;

import no.nav.data.GrunnbeløpAPI;
import java.io.IOException;

/**
 * Verktøy med forskjellige hjelpemetoder til å kalkulere forskjellige grunnbeløpsverdier, som
 * bruker i prossesen for å kalkulere hvilken dagsats en person har rett på. Grunnbeløpet brukt
 * i disse metodene hente fra NAV sitt grunnbeløp-API.
 *
 * @author Emil Elton Nilsen
 * @version 1.0
 */
public class DagpengerVerktøy {

    private double grunnbeløp;
    private final double antallGForMinimumsrett;
    private final double antallGForMakssats;

    public DagpengerVerktøy() {
        antallGForMinimumsrett = 1.5;
        antallGForMakssats = 6;

        //Usikker på om det er lurt å la verktøyet fortsette dersom det ikke får hentet
        // grunnpengerbeløpet? Skulle kanskje stoppet programmet istedenfor, eller tvinge
        // implementasjonen mot frontend til å behandle det?
        try {
            this.grunnbeløp = new GrunnbeløpAPI().hentGrunnbeløp();
        } catch (IOException | InterruptedException exception) {
            System.out.println("Problemer med tilkobling til grunnbeløp API'et" + exception.getMessage());
        }
    }

    //Jeg lekte med tanken å lage en konstruktør som lar deg sette disse verdiene manuelt,
    // men slike endringer skjer antakelig sjeldent..
    // Og det vil heller skape enkel mulighet for feilsituasjoner.
    /*
    public DagpengeVerktøy(double antallGForMinimumsrett, double antallGForMakssats) {
        this.antallGForMinimumsrett = antallGForMinimumsrett;
        this.antallGForMakssats = antallGForMakssats;

        try {
            this.grunnbeløp = new GrunnbeløpAPI().hentGrunnbeløp();
        } catch (IOException | InterruptedException exception) {
            System.out.println("Problemer med tilkobling til grunnbeløp API'et" + exception.getMessage());
        }
    }*/

    public double getAntallGForMinimumsrett(){
        return antallGForMinimumsrett;
    }

    public double getAntallGForMakssats() {
        return antallGForMakssats;
    }

    /**
     * Kalkulerer det totale grunnbeløpet for gitt antall år.
     * @param antallÅr antall år med grunnbeløp å kalkulere.
     * @return grunnbeløpet over gitt antall år.
     */
    public double hentTotaltGrunnbeløpForGittAntallÅr(int antallÅr) {
        return this.grunnbeløp * antallÅr;
    }

    /**
     * Kalkulerer hvor mye en person må tjene det siste året for å ha rett på kontroller.
     * @return 1.5G basert på dagens grunnbeløp.
     */
    public double hentMinimumÅrslønnForRettPåDagpenger() {
        return this.grunnbeløp * this.antallGForMinimumsrett;
    }

    /**
     * Kalkulerer hvor høyt maks årlig dagpengegrunnlag kan være.
     * @return 6G basert på dagens grunnbeløp.
     */
    public double hentMaksÅrligDagpengegrunnlag() {
        return this.grunnbeløp * this.antallGForMakssats;
    }
}
