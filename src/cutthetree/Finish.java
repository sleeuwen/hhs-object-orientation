package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Finish extends Field {
    private static Image image;

    public Finish(int x, int y) {
        super(x, y);
        if (image == null) loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/house.png"));
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
