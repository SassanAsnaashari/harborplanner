package de.harbor.mitarbeiter.service;

import de.harbor.mitarbeiter.model.Hafenmitarbeiter;
import de.harbor.mitarbeiter.model.Mitarbeiter;
import de.harbor.mitarbeiter.model.Schiffsmitarbeiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Einfache In-Memory-Implementierung fuer Mitarbeiter.
 */
public class MitarbeiterServiceImpl implements MitarbeiterService {

    private final List<Mitarbeiter> mitarbeiter;

    public MitarbeiterServiceImpl() {
        this.mitarbeiter = new ArrayList<>();
    }

    @Override
    public Mitarbeiter hafenmitarbeiterErstellen(int nummer, String name) {
        Mitarbeiter neuerMitarbeiter = new Hafenmitarbeiter(nummer, name);
        mitarbeiter.add(neuerMitarbeiter);
        return neuerMitarbeiter;
    }

    @Override
    public Mitarbeiter schiffsmitarbeiterErstellen(int nummer, String name, int schiffsNummer) {
        Mitarbeiter neuerMitarbeiter = new Schiffsmitarbeiter(nummer, name, schiffsNummer);
        mitarbeiter.add(neuerMitarbeiter);
        return neuerMitarbeiter;
    }

    @Override
    public List<Mitarbeiter> alleMitarbeiter() {
        return new ArrayList<>(mitarbeiter);
    }

    @Override
    public Optional<Mitarbeiter> findeMitarbeiter(int mitarbeiterNummer) {
        for (Mitarbeiter einzelnerMitarbeiter : mitarbeiter) {
            if (einzelnerMitarbeiter.getNummer() == mitarbeiterNummer) {
                return Optional.of(einzelnerMitarbeiter);
            }
        }

        return Optional.empty();
    }

    @Override
    public String pauseMachen(int mitarbeiterNummer) {
        Optional<Mitarbeiter> gefundenerMitarbeiter = findeMitarbeiter(mitarbeiterNummer);
        if (gefundenerMitarbeiter.isEmpty()) {
            return "Mitarbeiter wurde nicht gefunden.";
        }

        gefundenerMitarbeiter.get().pauseMachen();
        return "Mitarbeiter macht jetzt Pause.";
    }

    @Override
    public String essen(int mitarbeiterNummer) {
        Optional<Mitarbeiter> gefundenerMitarbeiter = findeMitarbeiter(mitarbeiterNummer);
        if (gefundenerMitarbeiter.isEmpty()) {
            return "Mitarbeiter wurde nicht gefunden.";
        }

        gefundenerMitarbeiter.get().essen();
        return "Mitarbeiter isst jetzt.";
    }
}
