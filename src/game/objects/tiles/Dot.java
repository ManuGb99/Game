package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Die Dot-Klasse repräsentiert ein sammelbares Objekt (Punkt) im Spiel.
 * Sie erbt von Tile und wird als kleiner weißer Kreis dargestellt.
 */
public class Dot extends Tile {
    protected final double radius; // Radius des Punktes

    /**
     * Erstellt einen Dot mit benutzerdefiniertem Radius.
     * @param x X-Koordinate der Kachel auf der Karte.
     * @param y Y-Koordinate der Kachel auf der Karte.
     * @param radius Radius des Dot-Objekts.
     */
    protected Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    /**
     * Erstellt einen Dot mit einem Standardradius von 0.125.
     * @param x X-Koordinate der Kachel auf der Karte.
     * @param y Y-Koordinate der Kachel auf der Karte.
     */
    public Dot(int x, int y) {
        this(x, y, 0.125);
    }

    /**
     * Rendert den Dot auf dem Bildschirm als weißen Kreis.
     * @param g Das Graphics2D-Objekt zum Zeichnen.
     * @param tileSize Größe der Spielfeld-Kacheln.
     */
    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = getCenterX() * tileSize;
        double centerYOnScreen = getCenterY() * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2.0;

        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }

    /**
     * Gibt die X-Koordinate des Dot-Zentrums zurück.
     * @return X-Koordinate des Zentrums.
     */
    public double getCenterX() {
        return x + 0.5;
    }

    /**
     * Gibt die Y-Koordinate des Dot-Zentrums zurück.
     * @return Y-Koordinate des Zentrums.
     */
    public double getCenterY() {
        return y + 0.5;
    }

    /**
     * Gibt den Radius des Dot-Objekts zurück.
     * @return Radius des Dots.
     */
    public double getRadius() {
        return radius;
    }
}
