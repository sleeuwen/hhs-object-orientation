package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Field {
    //private int xPos;
    //private int yPos;
    private static Image image;

    private Lumberaxe axe;
    public enum side{up,down,left,right}

    public Player(int xPos, int yPos) {
        super(xPos,yPos);
        if(image==null){loadImage();}
    }
    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/playerSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void move(int dx, int dy) {
        xPos += dx;
        yPos += dy;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, xPos, yPos, xPos + 75, yPos + 75, 150, 0,  225, 75, null);


    }
    public void grabLumberaxe(Lumberaxe axe) {
        this.axe = axe;
    }

    public Lumberaxe getAxe() {
        return this.axe;
    }
}
