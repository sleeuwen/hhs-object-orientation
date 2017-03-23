package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The Finish class represents the finish field.
 */
public class Finish extends Field {
    private static Image image;

    public Finish(int x, int y) {
        super(x, y);

        if (image == null) loadImage();
    }

    /**
     * Loads the images required to paint this object on screen
     */
    private static void loadImage() {
        try {
            image = ImageIO.read(Finish.class.getResource("/img/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, xPos * SIZE, yPos * SIZE, null);
    }
}
