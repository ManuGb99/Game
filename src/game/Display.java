package game;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    private final Game game;

    public Display(Game game) {
        super();
        this.game = game;
        game.add(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.RED);
        g.drawRect(50, 100, 200, 200);
        g.drawOval(200, 200, 100, 100);

        game.render(g2);
    }
}
