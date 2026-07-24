package de.harbor.hafen.model;

import de.harbor.mitarbeiter.model.Mitarbeiter;
import de.harbor.schiff.model.Schiff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Beschreibt einen Hafen mit Standort, Schiffen und Mitarbeitern.
 */
public class Hafen {

    private final String name;
    private final Koordinate standort;
    private final List<Schiff> angelegteSchiffe;
    private final List<Mitarbeiter> mitarbeiter;

    public Hafen(String name, Koordinate standort) {
        this.name = name;
        this.standort = standort;
        this.angelegteSchiffe = new ArrayList<>();
        this.mitarbeiter = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Koordinate getStandort() {
        return standort;
    }

    public List<Schiff> getAngelegteSchiffe() {
        return new ArrayList<>(angelegteSchiffe);
    }

    public List<Mitarbeiter> getMitarbeiter() {
        return new ArrayList<>(mitarbeiter);
    }

    public void schiffAnlegen(Schiff schiff) {
        if (findeAngelegtesSchiff(schiff.getNummer()).isEmpty()) {
            angelegteSchiffe.add(schiff);
            schiff.anlegen();
        }
    }

    public boolean schiffAblegen(int schiffsNummer) {
        Optional<Schiff> schiff = findeAngelegtesSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return false;
        }

        schiff.get().ablegen();
        angelegteSchiffe.remove(schiff.get());
        return true;
    }

    public void mitarbeiterHinzufuegen(Mitarbeiter neuerMitarbeiter) {
        mitarbeiter.add(neuerMitarbeiter);
    }

    public Optional<Schiff> findeAngelegtesSchiff(int schiffsNummer) {
        for (Schiff schiff : angelegteSchiffe) {
            if (schiff.getNummer() == schiffsNummer) {
                return Optional.of(schiff);
            }
        }

        return Optional.empty();
    }

    //TODO implement toString method lecture 2

}
