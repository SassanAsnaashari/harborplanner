package de.harbor.mitarbeiter.model;

/**
 * Abstrakte Basisklasse fuer Mitarbeiter im Hafenprojekt.
 */
public abstract class Mitarbeiter {

    private final int nummer;
    private final String name;
    private boolean machtPause;

    protected Mitarbeiter(int nummer, String name) {
        this.nummer = nummer;
        this.name = name;
        this.machtPause = false;
    }

    public int getNummer() {
        return nummer;
    }

    public String getName() {
        return name;
    }

    public boolean isMachtPause() {
        return machtPause;
    }

    public void essen() {
        machtPause = true;
    }

    public void pauseMachen() {
        machtPause = true;
    }

    public void pauseBeenden() {
        machtPause = false;
    }

    @Override
    public String toString() {
        return nummer + " - " + name + " | Pause: " + (machtPause ? "ja" : "nein");
    }
}
