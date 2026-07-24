package de.harbor.schiff.model;

import de.harbor.hafen.model.Koordinate;

/**
 * Abstrakte Basisklasse fuer alle Schiffe.
 */
public abstract class Schiff {

    private final int nummer;
    private final String name;
    private final double tankGroesse;
    private final int maximaleLadung;
    private Koordinate koordinate;
    private double kraftstoffMenge;
    private int ladung;
    private boolean angelegt;

    protected Schiff(int nummer, String name, Koordinate koordinate, double tankGroesse,
                     double kraftstoffMenge, int maximaleLadung) {
        this.nummer = nummer;
        this.name = name;
        this.koordinate = koordinate;
        this.tankGroesse = Math.max(0, tankGroesse);
        this.kraftstoffMenge = Math.min(Math.max(0, kraftstoffMenge), this.tankGroesse);
        this.maximaleLadung = Math.max(0, maximaleLadung);
        this.ladung = 0;
        this.angelegt = false;
    }

    public int getNummer() {
        return nummer;
    }

    public String getName() {
        return name;
    }

    public Koordinate getKoordinate() {
        return koordinate;
    }

    public double getTankGroesse() {
        return tankGroesse;
    }

    public double getKraftstoffMenge() {
        return kraftstoffMenge;
    }

    public int getLadung() {
        return ladung;
    }

    public int getMaximaleLadung() {
        return maximaleLadung;
    }

    public boolean isAngelegt() {
        return angelegt;
    }

    public double tanken(double menge) {
        if (menge <= 0) {
            return 0;
        }

        double moeglicheMenge = tankGroesse - kraftstoffMenge;
        double getankteMenge = Math.min(menge, moeglicheMenge);
        kraftstoffMenge += getankteMenge;
        return getankteMenge;
    }

    public int beladen(int menge) {
        if (menge <= 0) {
            return 0;
        }

        int moeglicheLadung = maximaleLadung - ladung;
        int geladeneMenge = Math.min(menge, moeglicheLadung);
        ladung += geladeneMenge;
        return geladeneMenge;
    }

    public int entladen(int menge) {
        if (menge <= 0) {
            return 0;
        }

        int entladeneMenge = Math.min(menge, ladung);
        ladung -= entladeneMenge;
        return entladeneMenge;
    }

    public void bewegenNach(int x, int y) {
        koordinate = new Koordinate(x, y);
    }

    public void anlegen() {
        angelegt = true;
    }

    public void ablegen() {
        angelegt = false;
    }

    // TODO implement toString with lecture 2

    // TODO implement equals with lecture 2
}
