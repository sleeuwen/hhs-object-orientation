package cutthetree;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * The SpeechBalloon class is a {@link Field} which will
 * show a custom message at the given coordinates.
 */
public class SpeechBalloon extends Field {
    private static final int PADDING = 5 * 2;
    private static Font font;

    private String message;

    public SpeechBalloon(int x, int y, String message) {
        super(x, y);

        this.message = message.trim();
        if (font == null) loadFont();
    }

    /**
     * Loads the font the message is displayed in.
     */
    private static void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, SpeechBalloon.class.getResourceAsStream("/font/pokemon.ttf")).deriveFont(12f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (message.isEmpty()) return;

        String[] lines = message.split("\n");
        g.setFont(font);

        // Measure text width/height for the current font
        int height = g.getFontMetrics().getHeight() + 5;
        int width = 0;
        for (String line : lines) {
            width = Math.max(width, g.getFontMetrics().stringWidth(line));
        }
        width += PADDING * 2;

        // Get absolute x/y positions, 1/4th closer to the center
        int x = xPos * SIZE + (SIZE / 4);
        int y = yPos * SIZE + (SIZE / 4);

        // Draw the text balloon at the appropriate x/y coordinates and correct width/height
        RoundRectangle2D rectangle = new RoundRectangle2D.Float(
                Math.max(PADDING, Math.min(GameFrame.FRAME_WIDTH - width - PADDING * 2, x - width / 2 - 5)),
                y - 10 - PADDING - (height * lines.length),
                width + PADDING,
                height * lines.length + PADDING,
                10,
                10
        );

        g.setColor(Color.WHITE);
        ((Graphics2D) g).fill(rectangle);

        // Draw the triangle to the player.
        g.fillPolygon(new Polygon(
                new int[]{x, x, x - 10, x},
                new int[]{y, y - 10, y - 10, y},
                4
        ));

        // Draw the message
        x = (int) rectangle.getX() + PADDING + 5;
        y = (int) rectangle.getY() + height + 2;

        g.setColor(Color.BLACK);
        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], x, y + (height * i));
        }
    }
}
