package game.objects;

import java.awt.*;

/**
 * Abstrakte Basisklasse für alle Spielobjekte.
 * Jedes Objekt, das von dieser Klasse erbt, muss eine eigene Render-Methode implementieren.
 */
public abstract class GameObject {
    public abstract void render(Graphics2D g, int tileSize);
}