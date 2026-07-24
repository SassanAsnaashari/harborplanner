package de.harbor.schiff.service;

import de.harbor.schiff.model.Schiff;

import java.util.List;
import java.util.Optional;

/**
 * Service-Schnittstelle fuer schiffsbezogene Operationen.
 */
public interface SchiffService {

    Schiff containerschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                    double kraftstoffMenge, int containerKapazitaet);

    Schiff lotsenschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                 double kraftstoffMenge);

    List<Schiff> alleSchiffe();

    Optional<Schiff> findeSchiff(int schiffsNummer);

    String tanken(int schiffsNummer, double menge);

    String beladen(int schiffsNummer, int menge);

    String entladen(int schiffsNummer, int menge);

    String bewegen(int schiffsNummer, int x, int y);
}
