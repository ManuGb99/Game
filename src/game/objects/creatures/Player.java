package game.objects.creatures;

import game.Game;
import game.objects.creatures.enemy.Enemy;
import game.objects.tiles.Air;
import game.objects.tiles.Dot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

/**
 * Die Player-Klasse repräsentiert den Spieler im Spiel.
 * Sie erbt von Creature und implementiert KeyListener, um Tastatureingaben zu verarbeiten.
 */
public class Player extends Creature implements KeyListener {

    /**
     * Konstruktor der Player-Klasse.
     * Initialisiert die Position, Größe und Geschwindigkeit des Spielers.
     * @param game Referenz auf das Game-Objekt.
     * @param centerX Start-X-Koordinate des Spielers.
     * @param centerY Start-Y-Koordinate des Spielers.
     * @param radius Größe des Spielers.
     * @param speed Bewegungsgeschwindigkeit des Spielers.
     */
    public Player(Game game, double centerX, double centerY, double radius, double speed) {
        super(game, centerX, centerY, radius, speed, Color.YELLOW);
    }

    /**
     * Prüft, ob der Spieler mit einem Punkt (Dot) kollidiert.
     * Falls ja, wird der Punkt entfernt und das Spiel gewonnen, wenn alle Dots eingesammelt wurden.
     */
    private void tickDotCollision() {
        int x = (int) centerX;
        int y = (int) centerY;

        if (game.getMap().getTile(x, y) instanceof Dot dot) {
            double dx = dot.getCenterX() - centerX;
            double dy = dot.getCenterY() - centerY;
            double r = dot.getRadius() + radius;

            // Prüft, ob der Spieler den Punkt berührt
            if (dx * dx + dy * dy < r * r) {
                game.getMap().setTile(x, y, new Air(x, y)); // Entfernt den Dot
                if (game.getMap().dotCount() == 0) {
                    game.win(); // Spieler gewinnt, wenn keine Dots mehr übrig sind
                }
            }
        }
    }

    /**
     * Aktualisiert die bevorzugte Bewegungsrichtung.
     * Die Methode wird auch von Gegnern genutzt.
     */
    @Override
    protected void tickPreferredDirection() {
        for (Enemy enemy : game.getEnemies()) {
            enemy.tickPreferredDirection();
        }
    }

    /**
     * Aktualisiert die Bewegung des Spielers und prüft Kollisionen mit Dots.
     */
    @Override
    public void tick() {
        super.tick();
        tickDotCollision();
    }

    /**
     * Zeichnet den Spieler auf dem Bildschirm.
     * @param g Das Graphics2D-Objekt zum Zeichnen.
     * @param tileSize Größe der Spielfeld-Kacheln.
     */
    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2.0;

        // Zeichnet den Spieler als Kreis
        g.setColor(color);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));

        // Augen-Rendering: Blickrichtung zum nächsten Gegner
        Enemy closestEnemy = null;
        double closestSqDistance = Double.MAX_VALUE;
        for (Enemy enemy : game.getEnemies()) {
            double difX = enemy.centerX - centerX;
            double difY = enemy.centerY - centerY;
            double sqDistance = difX * difX + difY * difY;
            if (sqDistance < closestSqDistance) {
                closestEnemy = enemy;
                closestSqDistance = sqDistance;
            }
        }
        renderEyes(g, centerXOnScreen, centerYOnScreen, radiusOnScreen, closestEnemy.centerX, closestEnemy.centerY);
    }

    /**
     * Ignoriert Tastatureingaben, die nur getippt werden (nicht gedrückt).
     * @param e KeyEvent-Objekt.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    /**
     * Reagiert auf Tastatureingaben, um die Bewegungsrichtung des Spielers zu steuern.
     * WASD-Steuerung:
     * - W = Nach oben
     * - A = Nach links
     * - S = Nach unten
     * - D = Nach rechts
     * @param e KeyEvent-Objekt.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                preferredDirectionX = 0;
                preferredDirectionY = -1;
            }
            case KeyEvent.VK_A -> {
                preferredDirectionX = -1;
                preferredDirectionY = 0;
            }
            case KeyEvent.VK_S -> {
                preferredDirectionX = 0;
                preferredDirectionY = 1;
            }
            case KeyEvent.VK_D -> {
                preferredDirectionX = 1;
                preferredDirectionY = 0;
            }
        }
    }

    /**
     * Ignoriert das Loslassen einer Taste.
     * @param e KeyEvent-Objekt.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // ignore
    }
}
