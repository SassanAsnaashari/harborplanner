# HarborPlanner

Study project for Java OOP at IU.

## Projektidee

HarborPlanner ist ein kleines Konsolenprojekt fuer die Vorlesung. Es modelliert einen Hafen mit Schiffen und Mitarbeitern. Dabei stehen OOP-Grundlagen und Java-Code-Conventions im Vordergrund.

## Fachlicher Schnitt

Das Projekt ist nach Fachbereichen geschnitten:

- `de.harbor.schiff`
  - `model`: `Schiff`, `Containerschiff`, `Lotsenschiff`
  - `service`: `SchiffService`, `SchiffServiceImpl`
- `de.harbor.mitarbeiter`
  - `model`: `Mitarbeiter`, `Hafenmitarbeiter`, `Schiffsmitarbeiter`
  - `service`: `MitarbeiterService`, `MitarbeiterServiceImpl`
- `de.harbor.hafen`
  - `model`: `Hafen`, `Koordinate`
  - `service`: `HafenService`, `HafenServiceImpl`
- `de.harbor.dashboard`
  - Konsolenmenue fuer Benutzereingaben

`Main` startet nur das Dashboard. Das Dashboard arbeitet gegen das Interface `HafenService`.

Die Weltkarte ist 100x30 Felder gross. Meer wird als Leerraum dargestellt, Land mit `.`, Schiffe mit dem Unicode-Symbol `\u26F5` und Haefen mit dem Unicode-Symbol `\u2693` markiert.

## Starten

```bash
mvn compile
mvn exec:java -Dexec.mainClass="de.harbor.Main"
```

Alternativ kann `de.harbor.Main` direkt in der IDE gestartet werden.
