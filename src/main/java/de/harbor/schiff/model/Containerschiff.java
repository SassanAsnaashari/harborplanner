package de.harbor.schiff.model;

import de.harbor.hafen.model.Koordinate;

/**
 * Schiffstyp fuer Containertransport.
 */
public class Containerschiff extends Schiff {

    private final int containerKapazitaet;

    public Containerschiff(int nummer, String name, Koordinate koordinate, double tankGroesse,
                           double kraftstoffMenge, int containerKapazitaet) {
        super(nummer, name, koordinate, tankGroesse, kraftstoffMenge, containerKapazitaet);
        this.containerKapazitaet = Math.max(0, containerKapazitaet);
    }

    public int getContainerKapazitaet() {
        return containerKapazitaet;
    }
}
