package game;

import javax.swing.*;
import java.awt.*;

/**
 * Die Display-Klasse ist für die Darstellung des Spiels verantwortlich.
 * Sie erbt von JPanel und wird als Zeichenfläche für die Spielgrafik verwendet.
 */
public class Display extends JPanel {
    private final Game game; // Referenz auf das Game-Objekt

    /**
     * Konstruktor der Display-Klasse.
     * @param game Das Game-Objekt, das gezeichnet werden soll.
     */
    public Display(Game game) {
        super(); // Ruft den Konstruktor der JPanel-Klasse auf
        this.game = game;
        game.add(this); // Fügt das Display-Panel zum Spiel hinzu
    }

    /**
     * Überschreibt die paint-Methode, um das Spiel zu zeichnen.
     * @param g Das Graphics-Objekt zum Zeichnen der Komponenten.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // Wandelt Graphics in Graphics2D für erweiterte Zeichnungsmöglichkeiten

        // Setzt Rendering-Hinweise für eine bessere Grafikqualität
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Übergibt die Zeichenfläche an die Game-Klasse, um das Spiel zu rendern
        game.render(g2);
    }
}
