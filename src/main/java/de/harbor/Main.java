package de.harbor;

import de.harbor.dashboard.HafenDashboard;
import de.harbor.hafen.service.HafenServiceImpl;

/**
 * Einstiegspunkt fuer den HarborPlanner.
 */
public class Main {

    public static void main(String[] args) {
        new HafenDashboard(new HafenServiceImpl()).starten();
    }
}
