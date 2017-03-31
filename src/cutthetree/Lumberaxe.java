package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The Lumberaxe class represents a Lumberaxe in the game
 * with a given {@link Color}.
 */
public class Lumberaxe extends Field {
    private static Image image;
    private Color color;

    public Lumberaxe(int x, int y, Color color) {
        super(x, y);

        this.color = color;
        if (image == null) loadImage();
    }

    /**
     * Loads the images required to paint this object on screen
     */
    private static void loadImage() {
        try {
            image = ImageIO.read(Lumberaxe.class.getResource("/img/axes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return The color of this lumberaxe.
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x = xPos * SIZE;
        int y = yPos * SIZE;
        int offset = color.ordinal() * SIZE;

        g.drawImage(
                image, // Source image
                x, y, x + SIZE, y + SIZE, // Destination position
                offset, 0, offset + SIZE, SIZE, // Source position
                null
        );
    }
}
