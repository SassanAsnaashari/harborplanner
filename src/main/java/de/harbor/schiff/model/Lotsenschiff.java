package de.harbor.schiff.model;

import de.harbor.hafen.model.Koordinate;

import java.io.Serializable;

/**
 * Kleines Schiff, das andere Schiffe im Hafen unterstuetzt.
 */
public class Lotsenschiff extends Schiff implements Cloneable{

    private boolean imEinsatz;

    public Lotsenschiff(int nummer, String name, Koordinate koordinate, double tankGroesse,
                        double kraftstoffMenge) {
        super(nummer, name, koordinate, tankGroesse, kraftstoffMenge, 5);
        this.imEinsatz = false;
    }

    public boolean isImEinsatz() {
        return imEinsatz;
    }

    public void einsatzStarten() {
        imEinsatz = true;
    }

    public void einsatzBeenden() {
        imEinsatz = false;
    }

    // TODO implement clone method with lecture 2
}
