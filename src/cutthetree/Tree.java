package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Tree extends Field {
    private static Image image;
    private Color color;

    public Tree(int x, int y, Color color) {
        super(x, y);

        isSolid = true;
        this.color = color;
        if (image == null) loadImage();
    }
    public Color getColor(){return this.color;}

    private static void loadImage() {
        try {
            image = ImageIO.read(Tree.class.getResource("/img/colorTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean cut(Lumberaxe axe) {
        if (axe != null && color == axe.getColor()) return true;
        return false;
    }

}
