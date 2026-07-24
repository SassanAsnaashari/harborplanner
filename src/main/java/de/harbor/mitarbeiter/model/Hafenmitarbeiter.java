package de.harbor.mitarbeiter.model;

/**
 * Mitarbeiter, der im Hafen arbeitet.
 */
public class Hafenmitarbeiter extends Mitarbeiter {

    private boolean krank;
    private boolean beiDerArbeit;

    public Hafenmitarbeiter(int nummer, String name) {
        super(nummer, name);
        this.krank = false;
        this.beiDerArbeit = false;
    }

    public boolean isKrank() {
        return krank;
    }

    public boolean isBeiDerArbeit() {
        return beiDerArbeit;
    }

    public void beiArbeitErscheinen() {
        if (!krank) {
            beiDerArbeit = true;
        }
    }

    public void krankMelden() {
        krank = true;
        beiDerArbeit = false;
    }

    public void gesundMelden() {
        krank = false;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Krank: " + (krank ? "ja" : "nein")
                + " | Bei der Arbeit: " + (beiDerArbeit ? "ja" : "nein");
    }
}
