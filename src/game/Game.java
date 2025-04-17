package game;

// Importiert verschiedene Klassen, die für das Spiel benötigt werden
import game.objects.creatures.Player;
import game.objects.creatures.enemy.ChasingEnemy;
import game.objects.creatures.enemy.CuttingEnemy;
import game.objects.creatures.enemy.Enemy;
import game.objects.creatures.enemy.RandomEnemy;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hauptklasse des Spiels. Erbt von JFrame für die GUI-Darstellung.
 */
public class Game extends JFrame {
    private final Display display; // Anzeige-Element des Spiels
    private final GameMap map; // Spielkarte, die die Umgebung darstellt
    private final Player player; // Spieler-Objekt
    private boolean won; // Speichert, ob das Spiel gewonnen wurde
    private final Enemy[] enemies; // Array mit allen Gegnern im Spiel

    /**
     * Konstruktor der Game-Klasse. Initialisiert das Spiel und die Spielobjekte.
     */
    public Game() {
        super("Game"); // Setzt den Fenstertitel

        display = new Display(this);
        map = new GameMap(40); // Erstellt eine neue Karte mit Größe 40
        player = new Player(this, 13.5, 10.5, 0.375, 0.07); // Erstellt den Spieler mit bestimmten Positionen und Werten
        addKeyListener(player); // Fügt den Spieler als KeyListener hinzu, um Eingaben zu registrieren

        // Initialisiert verschiedene Gegnertypen
        enemies = new Enemy[]{
                new ChasingEnemy(this, player, 12.5, 8.5, 0.375, 0.06, Color.RED),
                new CuttingEnemy(this, player, 13.5, 8.5, 0.375, 0.065, Color.GREEN),
                new RandomEnemy(this, player, 14.5, 8.5, 0.375, 0.07, Color.MAGENTA)
        };

        // Setzt die Fensterparameter
        setSize(1096, 759);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        startGameLoop(); // Startet die Hauptspiel-Schleife
    }

    /**
     * Startet die Hauptspiel-Schleife. Diese wird in einem separaten Thread regelmäßig ausgeführt.
     */
    private void startGameLoop() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            tick(); // Aktualisiert das Spiel
            display.repaint(); // Zeichnet die Anzeige neu
        }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS); // Führt die Schleife 60-mal pro Sekunde aus
    }

    /**
     * Setzt das Spiel zurück, um eine neue Runde zu starten.
     */
    private void reset() {
        won = false;
        map.reset();
        player.reset();
        for (Enemy enemy : enemies) {
            enemy.reset();
        }
    }

    /**
     * Wird aufgerufen, wenn der Spieler gewinnt.
     */
    public void win() {
        won = true;
    }

    /**
     * Wird aufgerufen, wenn der Spieler verliert.
     */
    public void lose() {
        JOptionPane.showMessageDialog(null, "Game Over!");
        reset();
    }

    /**
     * Aktualisiert die Spielobjekte pro Frame.
     */
    private void tick() {
        if (won) {
            JOptionPane.showMessageDialog(null, "You Won!");
            reset();
        }
        player.tick(); // Aktualisiert den Spieler
        for (Enemy enemy : enemies) {
            enemy.tick(); // Aktualisiert alle Gegner
        }
    }

    /**
     * Zeichnet die Spielobjekte auf den Bildschirm.
     * @param g2 Das Graphics2D-Objekt, mit dem gezeichnet wird.
     */
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight()); // Füllt den Hintergrund schwarz

        int tileSize = map.getTileSize();

        map.render(g2, tileSize); // Zeichnet die Karte
        player.render(g2, tileSize); // Zeichnet den Spieler
        for (Enemy enemy : enemies) {
            enemy.render(g2, tileSize); // Zeichnet die Gegner
        }
    }

    /**
     * Gibt die Spielkarte zurück.
     * @return Das GameMap-Objekt.
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Gibt das Array der Gegner zurück.
     * @return Ein Array der Gegner.
     */
    public Enemy[] getEnemies() {
        return enemies;
    }

    /**
     * Hauptmethode zum Starten des Spiels.
     * @param args Kommandozeilenargumente (nicht genutzt).
     */
    public static void main(String[] args) {
        new Game(); // Erstellt ein neues Spiel
    }
}
