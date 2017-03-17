package cutthetree;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SpeechBalloon extends Field {
    private static final int PADDING = 5 * 2;
    private static Font FONT;

    private String message;

    public SpeechBalloon(int x, int y, String message) {
        super(x, y);

        this.message = message.trim();

        if (FONT == null) loadFont();
    }

    private void loadFont() {
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/img/pokemon.ttf")).deriveFont(12f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (message.length() == 0) return;

        String[] lines = message.split("\n");
        g.setFont(FONT);

        int height = g.getFontMetrics().getHeight() + 5;
        int width = 0;
        for (String line : lines) {
            width = Math.max(width, g.getFontMetrics().stringWidth(line));
        }
        width += PADDING * 2;

        RoundRectangle2D rectangle = new RoundRectangle2D.Float(
                xPos - width / 2 - 5,
                yPos - 10 - PADDING - (height * lines.length),
                width + PADDING,
                height * lines.length + PADDING,
                10,
                10
        );

        g.setColor(Color.WHITE);
        ((Graphics2D) g).fill(rectangle);

        g.fillPolygon(new Polygon(
                new int[]{xPos, xPos, xPos - 10, xPos},
                new int[]{yPos, yPos - 10, yPos - 10, yPos},
                4
        ));

        // Draw message
        g.setColor(Color.BLACK);
        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], xPos - (width / 2) + PADDING, yPos - 12 - (PADDING / 2) - (height * i));
        }
    }
}
