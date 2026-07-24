package de.harbor.hafen.model;

/**
 * Beschreibt eine Position auf einer einfachen 2D-Karte.
 */
public class Koordinate {

    private int x;
    private int y;

    public Koordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void verschiebenNach(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
