package de.harbor.schiff.service;

import de.harbor.hafen.model.Koordinate;
import de.harbor.schiff.model.Containerschiff;
import de.harbor.schiff.model.Lotsenschiff;
import de.harbor.schiff.model.Schiff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Einfache In-Memory-Implementierung fuer Schiffe.
 */
public class SchiffServiceImpl implements SchiffService {

    private final List<Schiff> schiffe;

    public SchiffServiceImpl() {
        this.schiffe = new ArrayList<>();
    }

    @Override
    public Schiff containerschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                           double kraftstoffMenge, int containerKapazitaet) {
        Schiff schiff = new Containerschiff(nummer, name, new Koordinate(x, y), tankGroesse,
                kraftstoffMenge, containerKapazitaet);
        schiffe.add(schiff);
        return schiff;
    }

    @Override
    public Schiff lotsenschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                        double kraftstoffMenge) {
        // TODO implement me lecture 2 and clone basic ship
        Schiff schiff = new Lotsenschiff(0,"not yet build", new Koordinate(0, 0), 0.0, 0.0);
        schiffe.add(schiff);
        return schiff;
    }

    @Override
    public List<Schiff> alleSchiffe() {
        return new ArrayList<>(schiffe);
    }

    @Override
    public Optional<Schiff> findeSchiff(int schiffsNummer) {
        for (Schiff schiff : schiffe) {
            if (schiff.getNummer() == schiffsNummer) {
                return Optional.of(schiff);
            }
        }

        return Optional.empty();
    }

    @Override
    public String tanken(int schiffsNummer, double menge) {
        Optional<Schiff> schiff = findeSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return "Schiff wurde nicht gefunden.";
        }

        double getankteMenge = schiff.get().tanken(menge);
        return "Es wurden " + getankteMenge + " Kraftstoff-Einheiten getankt.";
    }

    @Override
    public String beladen(int schiffsNummer, int menge) {
        Optional<Schiff> schiff = findeSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return "Schiff wurde nicht gefunden.";
        }

        int geladeneMenge = schiff.get().beladen(menge);
        return "Es wurden " + geladeneMenge + " Ladungs-Einheiten beladen.";
    }

    @Override
    public String entladen(int schiffsNummer, int menge) {
        Optional<Schiff> schiff = findeSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return "Schiff wurde nicht gefunden.";
        }

        int entladeneMenge = schiff.get().entladen(menge);
        return "Es wurden " + entladeneMenge + " Ladungs-Einheiten entladen.";
    }

    @Override
    public String bewegen(int schiffsNummer, int x, int y) {
        Optional<Schiff> schiff = findeSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return "Schiff wurde nicht gefunden.";
        }

        schiff.get().bewegenNach(x, y);
        return "Schiff wurde nach (" + x + ", " + y + ") bewegt.";
    }
}
