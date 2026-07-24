package de.harbor.mitarbeiter.model;

/**
 * Mitarbeiter, der einem Schiff zugeordnet ist.
 */
public class Schiffsmitarbeiter extends Mitarbeiter {

    private int schiffsNummer;

    public Schiffsmitarbeiter(int nummer, String name, int schiffsNummer) {
        super(nummer, name);
        this.schiffsNummer = schiffsNummer;
    }

    public int getSchiffsNummer() {
        return schiffsNummer;
    }

    public void schiffWechseln(int schiffsNummer) {
        this.schiffsNummer = schiffsNummer;
    }

    @Override
    public String toString() {
        return super.toString() + " | Schiff: " + schiffsNummer;
    }
}
