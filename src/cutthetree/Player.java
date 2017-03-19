package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Field {
    private static Image image;

    private Lumberaxe axe;

    private Direction direction = Direction.DOWN;

    public Player(int xPos, int yPos) {
        super(xPos, yPos);

        if (image == null) loadImage();
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

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x = xPos * SIZE;
        int y = yPos * SIZE;
        int offset = direction.ordinal() * SIZE;

        g.drawImage(
                image, // Source image,
                x, y, x + SIZE, y + SIZE, // Destination position
                150, offset, 225, offset + SIZE, // Source position
                null
        );
    }

    public void grabLumberaxe(Lumberaxe axe) {
        this.axe = axe;
    }

    public Lumberaxe getAxe() {
        return this.axe;
    }
}
