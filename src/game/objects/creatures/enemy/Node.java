package game.objects.creatures.enemy;

import game.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Die Node-Klasse repräsentiert einen Knoten für Pfadfindungsalgorithmen.
 * Sie wird verwendet, um den kürzesten Weg zu einem Ziel auf der Karte zu berechnen.
 */
public class Node implements Comparable<Node> {
    private final int x; // X-Koordinate des Knotens
    private final int y; // Y-Koordinate des Knotens
    private final Node previous; // Vorgänger-Knoten (für den Pfad)

    private final int distanceFromStart; // Distanz vom Startpunkt bis zu diesem Knoten
    private final int minDistanceToGoal; // Geschätzte minimale Distanz bis zum Ziel (Manhattan-Distanz)

    public Node(int x, int y, Node previous, int distanceFromStart, int goalX, int goalY) {
        this.x = x;
        this.y = y;
        this.previous = previous;
        this.distanceFromStart = distanceFromStart;
        minDistanceToGoal = Math.abs(goalX - x) + Math.abs(goalY - y); // Manhattan-Distanz zum Ziel
    }

    /**
     * Gibt die Nachbarknoten zurück, die frei begehbar sind.
     * @param map Die Spielkarte.
     * @param goalX X-Koordinate des Zielpunktes.
     * @param goalY Y-Koordinate des Zielpunktes.
     * @return Eine Liste aller möglichen benachbarten Knoten.
     */
    public List<Node> neighbors(GameMap map, int goalX, int goalY) {
        List<Node> neighbors = new ArrayList<>();

        // Prüft, ob die angrenzenden Felder begehbar sind und fügt sie als Nachbarn hinzu
        if (map.isFree(x - 1, y)) {
            neighbors.add(new Node(x - 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x + 1, y)) {
            neighbors.add(new Node(x + 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x, y - 1)) {
            neighbors.add(new Node(x, y - 1, this, distanceFromStart + 1, goalX, goalY));
        }
        if (map.isFree(x, y + 1)) {
            neighbors.add(new Node(x, y + 1, this, distanceFromStart + 1, goalX, goalY));
        }

        return neighbors;
    }

    /**
     * Gibt den ersten Schritt des Pfades zurück, um sich zum Ziel zu bewegen.
     * @return Der erste Bewegungsknoten im optimalen Pfad.
     */
    public Node initialDirection() {
        if (distanceFromStart <= 1) {
            return this;
        }

        return previous.initialDirection();
    }

    /**
     * Vergleicht diesen Knoten mit einem anderen Knoten, um den besten Pfad zu bestimmen.
     * @param other Der andere Knoten.
     * @return Vergleichswert basierend auf der Summe von tatsächlicher und geschätzter Distanz.
     */
    @Override
    public int compareTo(Node other) {
        return Integer.compare(distanceFromStart + minDistanceToGoal, other.distanceFromStart + other.minDistanceToGoal);
    }

    /**
     * Vergleicht zwei Knoten basierend auf ihrer Position.
     * @param o Das andere Objekt.
     * @return true, wenn die Knoten dieselbe Position haben, sonst false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    /**
     * Berechnet den Hash-Wert des Knotens basierend auf seiner Position.
     * @return Der berechnete Hash-Wert.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Gibt die X-Koordinate des Knotens zurück.
     * @return X-Koordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die Y-Koordinate des Knotens zurück.
     * @return Y-Koordinate.
     */
    public int getY() {
        return y;
    }
}
