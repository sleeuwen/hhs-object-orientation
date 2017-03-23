package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The Tree class represents a destroyable tree in the game
 * with a given {@link Color}
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

    /**
     * Loads the images required to paint this object on screen
     */
    private static void loadImage() {
        try {
            image = ImageIO.read(Tree.class.getResource("/img/colorTree.png"));
            animImage = ImageIO.read(Tree.class.getResource("/img/slicedTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return The color of this tree.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return Whether this tree is currently being cut
     */
    public boolean isBeingCut() {
        return animState >= 0;
    }

    /**
     * Try to cut this tree with the given axe.<br/>
     * This will also start the animation of cutting the tree when possible.
     *
     * @return Whether this tree can be cut with the given axe.
     */
    public boolean cut(Lumberaxe axe) {
        if (axe != null && color == axe.getColor()) {
            animState = 0; // Start animation.

            return true;
        }

        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (animState >= 16) return;

        int x = xPos * SIZE;
        int y = yPos * SIZE;
        int offset = color.ordinal() * SIZE;

        // Calculate current animation image offset
        if (animState >= 0) {
            offset = (animState / 4) * SIZE;

            animState++;

            if (animState == 16) {
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
