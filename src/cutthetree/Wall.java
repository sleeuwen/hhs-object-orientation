package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The Wall class represents a non-destroyable tree in the field.
 */
public class Wall extends Field {
    private static Image image;

    public Wall(int x, int y) {
        super(x, y);

        isSolid = true;
        if (image == null) loadImage();
    }

    /**
     * Loads the images required to paint this object on screen
     */
    private static void loadImage() {
        try {
            image = ImageIO.read(Wall.class.getResource("/img/solidTree.png"));
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
