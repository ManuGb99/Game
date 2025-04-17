package game.objects.tiles;

import game.objects.GameObject;

/**
 * Abstrakte Basisklasse für alle Kacheln im Spiel.
 * Kacheln sind stationäre Spielobjekte, die auf einer festen Position auf der Karte liegen.
 */
public abstract class Tile extends GameObject {
    protected final int x; // X-Koordinate der Kachel
    protected final int y; // Y-Koordinate der Kachel

    /**
     * Konstruktor für eine Kachel.
     * @param x X-Koordinate der Kachel auf der Karte.
     * @param y Y-Koordinate der Kachel auf der Karte.
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
