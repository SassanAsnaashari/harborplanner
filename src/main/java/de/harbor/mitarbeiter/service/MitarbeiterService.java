package de.harbor.mitarbeiter.service;

import de.harbor.mitarbeiter.model.Mitarbeiter;

import java.util.List;
import java.util.Optional;

/**
 * Service-Schnittstelle fuer Mitarbeiter.
 */
public interface MitarbeiterService {

    Mitarbeiter hafenmitarbeiterErstellen(int nummer, String name);

    Mitarbeiter schiffsmitarbeiterErstellen(int nummer, String name, int schiffsNummer);

    List<Mitarbeiter> alleMitarbeiter();

    Optional<Mitarbeiter> findeMitarbeiter(int mitarbeiterNummer);

    String pauseMachen(int mitarbeiterNummer);

    String essen(int mitarbeiterNummer);
}
