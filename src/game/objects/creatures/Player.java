package game.objects.creatures;
import game.Game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Player extends Creature implements KeyListener {
    public Player(Game game, double centerX, double centerY, double radius, double speed) {
        super(game, centerX, centerY, radius, speed, Color.YELLOW);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2;

        g.setColor(color);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                prefferedDirectionX = 0;
                prefferedDirectionY = -1;
            }
            case KeyEvent.VK_S -> {
                prefferedDirectionX = 0;
                prefferedDirectionY = 1;
            }
            case KeyEvent.VK_A -> {
                prefferedDirectionX = -1;
                prefferedDirectionY = 0;
            }
            case KeyEvent.VK_D -> {
                prefferedDirectionX = 1;
                prefferedDirectionY = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // ignore
    }
}
