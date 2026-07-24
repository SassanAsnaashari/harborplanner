package de.harbor.hafen.model;

import java.util.Objects;

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
    // TODO implement me with lecture 2
}
