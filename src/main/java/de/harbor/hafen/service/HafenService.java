package de.harbor.hafen.service;

import de.harbor.hafen.model.Hafen;
import de.harbor.mitarbeiter.model.Mitarbeiter;
import de.harbor.schiff.model.Schiff;

import java.util.List;

/**
 * Zentrale Service-Schnittstelle fuer das Hafen-Dashboard.
 */
public interface HafenService {

    Schiff containerschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                    double kraftstoffMenge, int containerKapazitaet);

    Schiff lotsenschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                 double kraftstoffMenge);

    Mitarbeiter hafenmitarbeiterErstellen(int nummer, String name);

    Mitarbeiter schiffsmitarbeiterErstellen(int nummer, String name, int schiffsNummer);

    List<Schiff> alleSchiffe();

    List<Mitarbeiter> alleMitarbeiter();

    List<Hafen> alleHaefen();

    String hafenstatus();

    String schiffAnlegen(int schiffsNummer);

    String schiffAblegen(int schiffsNummer);

    String schiffTanken(int schiffsNummer, double menge);

    String schiffBeladen(int schiffsNummer, int menge);

    String schiffEntladen(int schiffsNummer, int menge);

    String schiffBewegen(int schiffsNummer, int x, int y);

    String karteErstellen(int breite, int hoehe);
}
