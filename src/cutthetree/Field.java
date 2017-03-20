package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Field {
    public static final int SIZE = 75;

    protected static Image grass;

    protected int xPos, yPos;
    protected boolean isSolid = false;

    public Field(int x, int y) {
        xPos = x;
        yPos = y;

        if (grass == null) loadImage();
    }

    private static void loadImage() {
        try {
            grass = ImageIO.read(Field.class.getResource("/img/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void paint(Graphics g) {
        g.drawImage(grass, xPos * SIZE, yPos * SIZE, null);
    }
}
