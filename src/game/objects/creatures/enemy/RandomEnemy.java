package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;
import java.util.Random;

/**
 * Die RandomEnemy-Klasse repräsentiert einen Gegner, der sich zufällig über die Karte bewegt.
 * Im Gegensatz zu anderen Gegnern verfolgt er den Spieler nicht direkt, sondern wählt zufällige Zielpositionen.
 */
public class RandomEnemy extends Enemy {
    private final Random random; // Zufallsgenerator für die Bewegung

    public RandomEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
        random = new Random();
    }

    /**
     * Aktualisiert die Ziel-Koordinaten des Gegners.
     * Der Gegner bewegt sich zufällig über die Karte und wählt neue Positionen nur dann,
     * wenn er sein aktuelles Ziel erreicht hat.
     */
    @Override
    protected void tickTarget() {
        // Falls der Gegner sein Ziel erreicht hat, wählt er ein neues Ziel zufällig auf der Karte
        if ((int) centerX == targetX && (int) centerY == targetY) {
            int nextTargetX = random.nextInt(game.getMap().getWidth());
            int nextTargetY = random.nextInt(game.getMap().getHeight());

            // Prüft, ob die neue Position frei ist, bevor sie als Ziel gesetzt wird
            if (!(game.getMap().isFree(nextTargetX, nextTargetY))) {
                return;
            }

            targetX = nextTargetX;
            targetY = nextTargetY;
        }
    }
}
