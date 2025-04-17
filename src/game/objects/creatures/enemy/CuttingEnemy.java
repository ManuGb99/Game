package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

/**
 * Die CuttingEnemy-Klasse repräsentiert einen Gegner, der die Bewegung des Spielers voraussieht.
 * Anstatt direkt zu verfolgen, berechnet er die nächste freie Position in der Bewegungsrichtung des Spielers.
 */
public class CuttingEnemy extends Enemy {

    public CuttingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    /**
     * Aktualisiert die Ziel-Koordinaten des Gegners basierend auf der aktuellen Position und Bewegungsrichtung des Spielers.
     * Der Gegner versucht vorauszusehen, wo sich der Spieler hinbewegen wird.
     */
    @Override
    protected void tickTarget() {
        // Setzt das Ziel zunächst auf die aktuelle Spielerposition
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();

        // Holt die aktuelle Bewegungsrichtung des Spielers
        int vx = player.getMovingDirectionX();
        int vy = player.getMovingDirectionY();

        // Wenn sich der Spieler horizontal bewegt, verfolgt der Gegner den nächstgelegenen freien Punkt auf dieser Achse
        if (vx != 0) {
            while (game.getMap().isFree(targetX + vx, targetY)) {
                targetX += vx;
            }
        }
        // Wenn sich der Spieler vertikal bewegt, verfolgt der Gegner den nächstgelegenen freien Punkt auf dieser Achse
        else if (vy != 0) {
            while (game.getMap().isFree(targetX, targetY + vy)) {
                targetY += vy;
            }
        }
    }
}
