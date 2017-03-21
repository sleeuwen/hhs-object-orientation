package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Tree extends Field {
    private static Image image;
    private static Image animImage;

    private Color color;
    private int animState = -1;

    public Tree(int x, int y, Color color) {
        super(x, y);

        isSolid = true;
        this.color = color;
        if (image == null || animImage == null) loadImage();
    }

    private static void loadImage() {
        try {
            image = ImageIO.read(Tree.class.getResource("/img/colorTree.png"));
            animImage = ImageIO.read(Tree.class.getResource("/img/slicedTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return this.color;
    }

    public boolean cut(Lumberaxe axe) {
        if (axe != null && color == axe.getColor()) {
            animState = 0;

            return true;
        }

        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (animState >= 4) return;

        int x = xPos * SIZE;
        int y = yPos * SIZE;
        int offset = color.ordinal() * SIZE;

        if (animState >= 0) {
            offset = animState * SIZE;

            animState++;

            if (animState == 4) {
                isSolid = false;
            }
        }

        g.drawImage(
                animState < 0 ? image : animImage, // Source image
                x, y, x + SIZE, y + SIZE, // Destination position
                offset, 0, offset + SIZE, SIZE, // Source position
                null
        );
    }
}
