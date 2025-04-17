package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Creature;
import game.objects.creatures.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Die Enemy-Klasse repräsentiert einen generischen Gegner im Spiel.
 * Sie erbt von Creature und bietet grundlegende Funktionen zur Gegnerbewegung und Kollisionserkennung.
 */
public abstract class Enemy extends Creature {
    protected Player player; // Referenz auf den Spieler
    protected int targetX; // X-Koordinate des Zielorts, den der Gegner ansteuert
    protected int targetY; // Y-Koordinate des Zielorts, den der Gegner ansteuert

    public Enemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, centerX, centerY, radius, speed, color);
        this.player = player;
        targetX = (int) centerX;
        targetY = (int) centerY;
    }

    /**
     * Findet die kürzeste Richtung zum angegebenen Zielpunkt (Pfadfindung).
     * Die Methode nutzt eine einfache A*-ähnliche Suche.
     * @param goalX X-Koordinate des Ziels.
     * @param goalY Y-Koordinate des Ziels.
     * @return Der erste Knoten der Richtung zum Ziel.
     */
    private Node shortestDirectionTo(int goalX, int goalY) {
        Node startNode = new Node((int) centerX, (int) centerY, null, 0, goalX, goalY);

        Queue<Node> queue = new PriorityQueue<>(Node::compareTo);
        queue.add(startNode);

        Set<Node> visited = new HashSet<>();

        Node currentNode;
        while ((currentNode = queue.poll()) != null) {
            if (visited.contains(currentNode)) {
                continue;
            }

            // Falls das Ziel erreicht ist, wird die initiale Bewegungsrichtung zurückgegeben
            if (currentNode.getX() == goalX && currentNode.getY() == goalY) {
                return currentNode.initialDirection();
            }

            visited.add(currentNode);
            queue.addAll(currentNode.neighbors(game.getMap(), goalX, goalY));
        }

        return null; // Falls kein gültiger Pfad gefunden wurde
    }

    /**
     * Abstrakte Methode zur Bestimmung des Zielpunkts des Gegners.
     * Wird von konkreten Gegnerklassen wie ChasingEnemy und CuttingEnemy überschrieben.
     */
    protected abstract void tickTarget();

    /**
     * Aktualisiert die bevorzugte Bewegungsrichtung des Gegners basierend auf dem Zielpunkt.
     */
    @Override
    public void tickPreferredDirection() {
        tickTarget();

        Node aim = shortestDirectionTo(targetX, targetY);
        if (aim != null) {
            preferredDirectionX = Integer.signum(aim.getX() - (int) centerX);
            preferredDirectionY = Integer.signum(aim.getY() - (int) centerY);
        }
    }

    /**
     * Prüft, ob der Gegner mit dem Spieler kollidiert.
     * Falls eine Kollision erkannt wird, verliert der Spieler das Spiel.
     */
    private void tickPlayerCollision() {
        double dx = player.getCenterX() - centerX;
        double dy = player.getCenterY() - centerY;
        double r = player.getRadius() + radius;

        // Falls sich der Spieler innerhalb des Kollisionsbereichs befindet, verliert er
        if (dx * dx + dy * dy < r * r) {
            game.lose();
        }
    }

    /**
     * Aktualisiert die Bewegung und prüft auf Kollisionen mit dem Spieler.
     */
    @Override
    public void tick() {
        super.tick();
        tickPlayerCollision();
    }

    /**
     * Zeichnet den Gegner auf dem Bildschirm.
     * @param g Das Graphics2D-Objekt zum Zeichnen.
     * @param tileSize Größe der Spielfeld-Kacheln.
     */
    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double sizeOnScreen = radiusOnScreen * 2.0;

        // Zeichnet den Gegner als Quadrat
        g.setColor(color);
        g.fill(new Rectangle2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, sizeOnScreen, sizeOnScreen));

        // Zeichnet die Augen des Gegners, die zum Zielpunkt ausgerichtet sind
        renderEyes(g, centerXOnScreen, centerYOnScreen, radiusOnScreen, targetX + 0.5, targetY + 0.5);
    }
}
