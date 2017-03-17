package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Field {
    protected int xPos, yPos;
    protected boolean isSolid = false;

    protected Image grass;
    protected Image image;

    public Field() {
        this(0, 0);
    }

    public Field(int x, int y) {
        this.xPos = x;
        this.yPos = y;

        try {
            grass = ImageIO.read(getClass().getResource("/img/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(grass, xPos, yPos, null);

        if (image != null) {
            g.drawImage(image, xPos, yPos, null);
        }
    }

    public boolean isSolid() {
        return isSolid;
    }
}
