package game.objects.tiles;

import java.awt.*;

/**
 * Die Block-Klasse repräsentiert ein Hindernis auf der Spielkarte.
 * Sie erbt von Tile und wird als feste, unpassierbare Kachel dargestellt.
 */
public class Block extends Tile {

    /**
     * Konstruktor für einen Block.
     * @param x X-Koordinate der Kachel auf der Karte.
     * @param y Y-Koordinate der Kachel auf der Karte.
     */
    public Block(int x, int y) {
        super(x, y);
    }

    /**
     * Rendert den Block auf dem Bildschirm.
     * @param g Das Graphics2D-Objekt zum Zeichnen.
     * @param tileSize Größe der Spielfeld-Kacheln.
     */
    @Override
    public void render(Graphics2D g, int tileSize) {
        g.setColor(Color.BLUE); // Setzt die Farbe des Blocks auf Blau
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize); // Zeichnet ein gefülltes Rechteck als Block
    }
}
