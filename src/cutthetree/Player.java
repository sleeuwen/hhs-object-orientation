package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Field {
    private static Image image;

    private int animX;
    private int animY;
    private int animState = 2;

    private Lumberaxe axe;
    private String message = "";

    private Direction direction = Direction.DOWN;

    public Player(int xPos, int yPos) {
        super(xPos, yPos);

        if (image == null) loadImage();
    }

    private static void loadImage() {
        try {
            image = ImageIO.read(Player.class.getResource("/img/playerSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean move(int dx, int dy) {
        if (animX + animY != 0) return false;

        animX = dx;
        animY = dy;

        xPos += dx;
        yPos += dy;

        return true;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void grabLumberaxe(Lumberaxe axe) {
        this.axe = axe;
    }

    public Lumberaxe getAxe() {
        return this.axe;
    }

    public void say(String text) {
        message = text;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x = xPos * SIZE;
        int y = yPos * SIZE;
        int offsetX = 150;
        int offsetY = direction.ordinal() * SIZE;

        if (animX + animY != 0) {
            if (animState == 2) {
                animState = 0;
            } else {
                animState++;
            }

            x += animX * ((SIZE / 3) * (animState + 1)) + (SIZE * -animX);
            y += animY * ((SIZE / 3) * (animState + 1)) + (SIZE * -animY);

            offsetX = animState * SIZE;
            if (animState == 2) {
                animX = 0;
                animY = 0;
            }
        }

        g.drawImage(
                image, // Source image,
                x, y, x + SIZE, y + SIZE, // Destination position
                offsetX, offsetY, offsetX + SIZE, offsetY + SIZE, // Source position
                null
        );

        if (!message.isEmpty()) {
            new SpeechBalloon(xPos, yPos, message).paint(g);
        }
    }
}
