package no.nav.modell;

import no.nav.dagpenger.Saksbehandler;

/**
 * Representerer de mulige utfallene for en {@link DagpengerSøknad},
 *  og de mulige spesialiseringene for en {@link Saksbehandler}.
 */
public enum Status {
    /** Benyttes når søkerens inntekt er under minimumsgrensen */
    AVSLAG_FOR_LAV_INNTEKT,

    /** Godkjent søknad med standard sats */
    INNVILGET,

    /** Godkjent søknad med maksimal sats */
    INNVILGET_MED_MAKS_SATS,
}
