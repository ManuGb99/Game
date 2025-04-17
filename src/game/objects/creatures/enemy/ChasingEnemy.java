package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

/**
 * Die ChasingEnemy-Klasse repräsentiert einen Gegner, der den Spieler aktiv verfolgt.
 * Sie erbt von der Enemy-Klasse und überschreibt die Methode zur Zielverfolgung.
 */
public class ChasingEnemy extends Enemy {
    public ChasingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    /**
     * Aktualisiert die Ziel-Koordinaten des Gegners basierend auf der aktuellen Position des Spielers.
     * Der Gegner setzt seine Zielposition direkt auf die Spielerposition, um ihm zu folgen.
     */
    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();    // Setzt das Ziel auf die X-Koordinate des Spielers
        targetY = (int) player.getCenterY();    // Setzt das Ziel auf die Y-Koordinate des Spielers
    }
}