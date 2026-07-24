package de.harbor.hafen.service;

import de.harbor.hafen.model.Hafen;
import de.harbor.hafen.model.Koordinate;
import de.harbor.mitarbeiter.model.Mitarbeiter;
import de.harbor.mitarbeiter.service.MitarbeiterService;
import de.harbor.mitarbeiter.service.MitarbeiterServiceImpl;
import de.harbor.schiff.model.Schiff;
import de.harbor.schiff.service.SchiffService;
import de.harbor.schiff.service.SchiffServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service-Implementierung, die Hafen, Schiffe und Mitarbeiter koordiniert.
 */
public class HafenServiceImpl implements HafenService {

    private static final String HAFEN_FELD = "\u2693";
    private static final String LAND_FELD = ".";
    private static final String MEER_FELD = " ";
    private static final String SCHIFF_FELD = "\u26F5";

    private final Hafen hafen;
    private final List<Hafen> haefen;
    private final SchiffService schiffService;
    private final MitarbeiterService mitarbeiterService;

    public HafenServiceImpl() {
        this(new Hafen("Hamburg", new Koordinate(48, 10)), new SchiffServiceImpl(), new MitarbeiterServiceImpl());
    }

    public HafenServiceImpl(Hafen hafen, SchiffService schiffService, MitarbeiterService mitarbeiterService) {
        this.hafen = hafen;
        this.haefen = new ArrayList<>();
        this.haefen.add(hafen);
        this.haefen.add(new Hafen("New York", new Koordinate(32, 10)));
        this.haefen.add(new Hafen("Singapur", new Koordinate(78, 17)));
        this.schiffService = schiffService;
        this.mitarbeiterService = mitarbeiterService;
    }

    @Override
    public Schiff containerschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                           double kraftstoffMenge, int containerKapazitaet) {
        return schiffService.containerschiffErstellen(nummer, name, x, y, tankGroesse,
                kraftstoffMenge, containerKapazitaet);
    }

    @Override
    public Schiff lotsenschiffErstellen(int nummer, String name, int x, int y, double tankGroesse,
                                        double kraftstoffMenge) {
        return schiffService.lotsenschiffErstellen(nummer, name, x, y, tankGroesse, kraftstoffMenge);
    }

    @Override
    public Mitarbeiter hafenmitarbeiterErstellen(int nummer, String name) {
        Mitarbeiter neuerMitarbeiter = mitarbeiterService.hafenmitarbeiterErstellen(nummer, name);
        hafen.mitarbeiterHinzufuegen(neuerMitarbeiter);
        return neuerMitarbeiter;
    }

    @Override
    public Mitarbeiter schiffsmitarbeiterErstellen(int nummer, String name, int schiffsNummer) {
        Mitarbeiter neuerMitarbeiter = mitarbeiterService.schiffsmitarbeiterErstellen(nummer, name, schiffsNummer);
        hafen.mitarbeiterHinzufuegen(neuerMitarbeiter);
        return neuerMitarbeiter;
    }

    @Override
    public List<Schiff> alleSchiffe() {
        return schiffService.alleSchiffe();
    }

    @Override
    public List<Mitarbeiter> alleMitarbeiter() {
        return mitarbeiterService.alleMitarbeiter();
    }

    @Override
    public List<Hafen> alleHaefen() {
        return new ArrayList<>(haefen);
    }

    @Override
    public String hafenstatus() {
        // TODO implement me lecture 2
        return "Not implemented yet";
    }

    @Override
    public String schiffAnlegen(int schiffsNummer) {
        Optional<Schiff> schiff = schiffService.findeSchiff(schiffsNummer);
        if (schiff.isEmpty()) {
            return "Schiff wurde nicht gefunden.";
        }

        hafen.schiffAnlegen(schiff.get());
        return "Schiff wurde angelegt.";
    }

    @Override
    public String schiffAblegen(int schiffsNummer) {
        if (hafen.schiffAblegen(schiffsNummer)) {
            return "Schiff hat abgelegt.";
        }

        return "Schiff ist nicht im Hafen angelegt.";
    }

    @Override
    public String schiffTanken(int schiffsNummer, double menge) {
        return schiffService.tanken(schiffsNummer, menge);
    }

    @Override
    public String schiffBeladen(int schiffsNummer, int menge) {
        return schiffService.beladen(schiffsNummer, menge);
    }

    @Override
    public String schiffEntladen(int schiffsNummer, int menge) {
        return schiffService.entladen(schiffsNummer, menge);
    }

    @Override
    public String schiffBewegen(int schiffsNummer, int x, int y) {
        return schiffService.bewegen(schiffsNummer, x, y);
    }

    @Override
    public String karteErstellen(int breite, int hoehe) {
        String[][] karte = neueKarte(breite, hoehe);
        for (Schiff schiff : schiffService.alleSchiffe()) {
            Koordinate koordinate = schiff.getKoordinate();
            if (liegtAufKarte(koordinate, breite, hoehe)) {
                karte[koordinate.getY()][koordinate.getX()] = SCHIFF_FELD;
            }
        }

        for (Hafen einzelnerHafen : haefen) {
            Koordinate standort = einzelnerHafen.getStandort();
            if (liegtAufKarte(standort, breite, hoehe)) {
                karte[standort.getY()][standort.getX()] = HAFEN_FELD;
            }
        }

        return karteAlsText(karte);
    }

    private String[][] neueKarte(int breite, int hoehe) {
        String[][] karte = new String[hoehe][breite];
        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                karte[y][x] = feldFuerKoordinate(x, y);
            }
        }

        return karte;
    }

    private boolean liegtAufKarte(Koordinate koordinate, int breite, int hoehe) {
        return koordinate.getX() >= 0
                && koordinate.getX() < breite
                && koordinate.getY() >= 0
                && koordinate.getY() < hoehe;
    }

    private String karteAlsText(String[][] karte) {
        StringBuilder ausgabe = new StringBuilder();
        for (String[] zeile : karte) {
            for (String feld : zeile) {
                ausgabe.append(feld);
            }
            ausgabe.append(System.lineSeparator());
        }

        return ausgabe.toString();
    }

    private boolean istLand(int x, int y) {
        double laengengrad = xZuLaengengrad(x);
        double breitengrad = yZuBreitengrad(y);

        return istNordamerika(laengengrad, breitengrad)
                || istSuedamerika(laengengrad, breitengrad)
                || istGroenland(laengengrad, breitengrad)
                || istEuropa(laengengrad, breitengrad)
                || istAfrika(laengengrad, breitengrad)
                || istAsien(laengengrad, breitengrad)
                || istAustralien(laengengrad, breitengrad);
    }

    private String feldFuerKoordinate(int x, int y) {
        return istLand(x, y) ? LAND_FELD : MEER_FELD;
    }

    private double xZuLaengengrad(int x) {
        return (x / 99.0) * 360.0 - 180.0;
    }

    private double yZuBreitengrad(int y) {
        return 85.0 - (y / 29.0) * 170.0;
    }

    private boolean istNordamerika(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, -105, 50, 55, 25)
                || inEllipse(laengengrad, breitengrad, -95, 30, 35, 18)
                || inEllipse(laengengrad, breitengrad, -75, 18, 18, 8);
    }

    private boolean istSuedamerika(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, -60, -15, 23, 35)
                && !(laengengrad < -76 && breitengrad > -8);
    }

    private boolean istGroenland(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, -42, 72, 20, 9);
    }

    private boolean istEuropa(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, 10, 50, 27, 13)
                || inEllipse(laengengrad, breitengrad, 25, 42, 18, 8);
    }

    private boolean istAfrika(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, 20, 5, 28, 34)
                && !(laengengrad < -8 && breitengrad < -18);
    }

    private boolean istAsien(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, 80, 48, 62, 24)
                || inEllipse(laengengrad, breitengrad, 95, 20, 34, 20)
                || inEllipse(laengengrad, breitengrad, 120, 2, 18, 12)
                || inEllipse(laengengrad, breitengrad, 140, 38, 10, 18);
    }

    private boolean istAustralien(double laengengrad, double breitengrad) {
        return inEllipse(laengengrad, breitengrad, 134, -25, 22, 11);
    }

    private boolean inEllipse(double x, double y, double mittelpunktX, double mittelpunktY,
                              double radiusX, double radiusY) {
        double xAnteil = Math.pow((x - mittelpunktX) / radiusX, 2);
        double yAnteil = Math.pow((y - mittelpunktY) / radiusY, 2);
        return xAnteil + yAnteil <= 1;
    }
}
