package de.harbor.dashboard;

import de.harbor.hafen.service.HafenService;
import de.harbor.mitarbeiter.model.Mitarbeiter;
import de.harbor.schiff.model.Schiff;

import java.util.Scanner;

/**
 * Einfaches Konsolen-Dashboard fuer den HarborPlanner.
 */
public class HafenDashboard {

    private static final int KARTEN_BREITE = 100;
    private static final int KARTEN_HOEHE = 30;

    private final HafenService hafenService;
    private final Scanner scanner;

    public HafenDashboard(HafenService hafenService) {
        this.hafenService = hafenService;
        this.scanner = new Scanner(System.in);
    }

    public void starten() {
        boolean laeuft = true;
        while (laeuft) {
            menueAnzeigen();
            int auswahl = zahlEinlesen("Auswahl: ");
            laeuft = auswahlVerarbeiten(auswahl);
        }
    }

    private void menueAnzeigen() {
        System.out.println();
        System.out.println("=== HarborPlanner Dashboard ===");
        System.out.println("1  - Hafenstatus anzeigen");
        System.out.println("2  - Alle Schiffe anzeigen");
        System.out.println("3  - Alle Mitarbeiter anzeigen");
        System.out.println("4  - Containerschiff erstellen");
        System.out.println("5  - Lotsenschiff erstellen");
        System.out.println("6  - Hafenmitarbeiter erstellen");
        System.out.println("7  - Schiffsmitarbeiter erstellen");
        System.out.println("8  - Schiff anlegen");
        System.out.println("9  - Schiff ablegen");
        System.out.println("10 - Schiff tanken");
        System.out.println("11 - Schiff beladen");
        System.out.println("12 - Schiff entladen");
        System.out.println("13 - Schiff bewegen");
        System.out.println("14 - Weltkarte anzeigen (100x30)");
        System.out.println("0  - Programm beenden");
    }

    private boolean auswahlVerarbeiten(int auswahl) {
        switch (auswahl) {
            case 0:
                System.out.println("HarborPlanner wird beendet.");
                return false;
            case 1:
                hafenstatusAnzeigen();
                break;
            case 2:
                schiffeAnzeigen();
                break;
            case 3:
                mitarbeiterAnzeigen();
                break;
            case 4:
                containerschiffErstellen();
                break;
            case 5:
                lotsenschiffErstellen();
                break;
            case 6:
                hafenmitarbeiterErstellen();
                break;
            case 7:
                schiffsmitarbeiterErstellen();
                break;
            case 8:
                schiffAnlegen();
                break;
            case 9:
                schiffAblegen();
                break;
            case 10:
                schiffTanken();
                break;
            case 11:
                schiffBeladen();
                break;
            case 12:
                schiffEntladen();
                break;
            case 13:
                schiffBewegen();
                break;
            case 14:
                karteAnzeigen();
                break;
            default:
                System.out.println("Diese Auswahl gibt es nicht.");
                break;
        }

        return true;
    }

    private void hafenstatusAnzeigen() {
        System.out.println(hafenService.hafenstatus());
    }

    private void schiffeAnzeigen() {
        if (hafenService.alleSchiffe().isEmpty()) {
            System.out.println("Es gibt noch keine Schiffe.");
            return;
        }

        for (Schiff schiff : hafenService.alleSchiffe()) {
            System.out.println(schiff);
        }
    }

    private void mitarbeiterAnzeigen() {
        if (hafenService.alleMitarbeiter().isEmpty()) {
            System.out.println("Es gibt noch keine Mitarbeiter.");
            return;
        }

        for (Mitarbeiter mitarbeiter : hafenService.alleMitarbeiter()) {
            System.out.println(mitarbeiter);
        }
    }

    private void containerschiffErstellen() {
        int nummer = zahlEinlesen("Schiffsnummer: ");
        String name = textEinlesen("Name: ");
        int x = zahlEinlesen("X-Koordinate: ");
        int y = zahlEinlesen("Y-Koordinate: ");
        double tankGroesse = kommazahlEinlesen("Tankgroesse: ");
        double kraftstoffMenge = kommazahlEinlesen("Aktuelle Kraftstoffmenge: ");
        int containerKapazitaet = zahlEinlesen("Containerkapazitaet: ");

        Schiff schiff = hafenService.containerschiffErstellen(nummer, name, x, y, tankGroesse,
                kraftstoffMenge, containerKapazitaet);
        System.out.println("Containerschiff erstellt: " + schiff);
    }

    private void lotsenschiffErstellen() {
        int nummer = zahlEinlesen("Schiffsnummer: ");
        String name = textEinlesen("Name: ");
        int x = zahlEinlesen("X-Koordinate: ");
        int y = zahlEinlesen("Y-Koordinate: ");
        double tankGroesse = kommazahlEinlesen("Tankgroesse: ");
        double kraftstoffMenge = kommazahlEinlesen("Aktuelle Kraftstoffmenge: ");

        Schiff schiff = hafenService.lotsenschiffErstellen(nummer, name, x, y, tankGroesse, kraftstoffMenge);
        System.out.println("Lotsenschiff erstellt: " + schiff);
    }

    private void hafenmitarbeiterErstellen() {
        int nummer = zahlEinlesen("Mitarbeiternummer: ");
        String name = textEinlesen("Name: ");
        Mitarbeiter mitarbeiter = hafenService.hafenmitarbeiterErstellen(nummer, name);
        System.out.println("Hafenmitarbeiter erstellt: " + mitarbeiter);
    }

    private void schiffsmitarbeiterErstellen() {
        int nummer = zahlEinlesen("Mitarbeiternummer: ");
        String name = textEinlesen("Name: ");
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        Mitarbeiter mitarbeiter = hafenService.schiffsmitarbeiterErstellen(nummer, name, schiffsNummer);
        System.out.println("Schiffsmitarbeiter erstellt: " + mitarbeiter);
    }

    private void schiffAnlegen() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        System.out.println(hafenService.schiffAnlegen(schiffsNummer));
    }

    private void schiffAblegen() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        System.out.println(hafenService.schiffAblegen(schiffsNummer));
    }

    private void schiffTanken() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        double menge = kommazahlEinlesen("Kraftstoffmenge: ");
        System.out.println(hafenService.schiffTanken(schiffsNummer, menge));
    }

    private void schiffBeladen() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        int menge = zahlEinlesen("Ladungsmenge: ");
        System.out.println(hafenService.schiffBeladen(schiffsNummer, menge));
    }

    private void schiffEntladen() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        int menge = zahlEinlesen("Ladungsmenge: ");
        System.out.println(hafenService.schiffEntladen(schiffsNummer, menge));
    }

    private void schiffBewegen() {
        int schiffsNummer = zahlEinlesen("Schiffsnummer: ");
        int x = zahlEinlesen("Neue X-Koordinate: ");
        int y = zahlEinlesen("Neue Y-Koordinate: ");
        System.out.println(hafenService.schiffBewegen(schiffsNummer, x, y));
    }

    private void karteAnzeigen() {
        System.out.println(hafenService.karteErstellen(KARTEN_BREITE, KARTEN_HOEHE));
    }

    private int zahlEinlesen(String aufforderung) {
        while (true) {
            System.out.print(aufforderung);
            String eingabe = scanner.nextLine();
            try {
                return Integer.parseInt(eingabe);
            } catch (NumberFormatException exception) {
                System.out.println("Bitte eine ganze Zahl eingeben.");
            }
        }
    }

    private double kommazahlEinlesen(String aufforderung) {
        while (true) {
            System.out.print(aufforderung);
            String eingabe = scanner.nextLine().replace(',', '.');
            try {
                return Double.parseDouble(eingabe);
            } catch (NumberFormatException exception) {
                System.out.println("Bitte eine Zahl eingeben.");
            }
        }
    }

    private String textEinlesen(String aufforderung) {
        System.out.print(aufforderung);
        String eingabe = scanner.nextLine();
        while (eingabe.isBlank()) {
            System.out.println("Der Text darf nicht leer sein.");
            System.out.print(aufforderung);
            eingabe = scanner.nextLine();
        }

        return eingabe;
    }
}
