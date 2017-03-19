package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Wall extends Field {
    private static Image image;

    public Wall(int x, int y) {
        super(x, y);

        isSolid = true;
        if (image == null) loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/solidTree.png"));
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
