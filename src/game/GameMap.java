package game;

import game.objects.GameObject;
import game.objects.tiles.Air;
import game.objects.tiles.Block;
import game.objects.tiles.Dot;
import game.objects.tiles.Tile;

import java.awt.*;

/**
 * Repräsentiert die Karte des Spiels. Erbt von GameObject und speichert die verschiedenen Spielfelder.
 */
public class GameMap extends GameObject {
    // Standard-Karte mit verschiedenen Zahlen, die unterschiedliche Felder repräsentieren:
    // 1 = Block (Hindernis), 2 = Dot (Sammelobjekt), 0 = Air (freier Platz)
    private static final int[][] DEFAULT_MAP = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1},
            {1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 0, 2, 2, 2, 0, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 2, 1},
            {1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1},
            {1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private final int tileSize;     // Größe der einzelnen Kacheln

    private final Tile[][] tiles;   // 2D-Array mit den einzelnen Spielfeldern

    /**
     * Konstruktor, der die Karte mit einer bestimmten Kachelgröße initialisiert.
     * tileSize Größe der einzelnen Tiles.
     */
    public GameMap(int tileSize) {
        this.tileSize = tileSize;
        tiles = new Tile[DEFAULT_MAP.length][DEFAULT_MAP[0].length];
        reset();
    }

    /**
     * Setzt die Karte zurück und erstellt die Felder entsprechend der Default-Karte.
     */
    public void reset() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                tiles[y][x] = switch (DEFAULT_MAP[y][x]) {
                    case 1 -> new Block(x, y);
                    case 2 -> new Dot(x, y);
                    default -> new Air(x, y);
                };
            }
        }
    }

    /**
     * Rendert die Karte auf dem Bildschirm.
     * g Graphics2D-Objekt zum Zeichnen.
     * tileSize Größe der einzelnen Kacheln.
     */
    @Override
    public void render(Graphics2D g, int tileSize) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.render(g, tileSize);
            }
        }
    }

    /**
     * Berechnet die Anzahl der Dots (sammelbare Objekte) auf der Karte.
     * @return Anzahl der Dots.
     */
    public int dotCount() {
        int sum = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile instanceof Dot) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * Überprüft, ob eine bestimmte Position auf der Karte frei ist.
     * @ x X-Koordinate.
     * @ y Y-Koordinate.
     * @return true, wenn der Bereich frei ist, false, wenn dort ein Block ist.
     */
    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
            return false;
        }

        return !(tiles[y][x] instanceof Block);
    }

    /**
     * Gibt die Breite der Karte zurück.
     * @return Anzahl der Kacheln in der Breite.
     */
    public int getWidth() {
        return tiles[0].length;
    }

    /**
     * Gibt die Höhe der Karte zurück.
     * @return Anzahl der Kacheln in der Höhe.
     */
    public int getHeight() {
        return tiles.length;
    }

    /**
     * Gibt die Größe der einzelnen Kacheln zurück.
     * @return Tile-Größe.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Gibt eine bestimmte Kachel zurück.
     * x X-Koordinate.
     * @ y Y-Koordinate.
     * @return Tile-Objekt an der angegebenen Position.
     */
    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    /**
     * Setzt eine bestimmte Kachel auf der Karte.
     * x X-Koordinate.
     * y Y-Koordinate.
     * tile Neue Kachel, die dort platziert werden soll.
     */
    public void setTile(int x, int y, Tile tile) {
        tiles[y][x] = tile;
    }
}