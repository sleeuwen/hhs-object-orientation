package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Koen van Zeijl on 17-3-2017.
 */
public class Tree extends Field {
    private static Image image;
    private Color color;

    public Tree(int x, int y, Color color){
        super(x,y);

        this.color = color;
        if(image==null){loadImage();}
    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/colorTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int idx = color.ordinal();
        g.drawImage(image, xPos, yPos, xPos + 75, yPos + 75, idx * 75, 0, (idx + 1) * 75, 75, null);


    }

    public boolean cut(Lumberaxe axe){
        return false;
    }
}
