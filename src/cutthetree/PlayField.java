package cutthetree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayField extends JComponent {
    private static Image imageAxe;
    private static Image image;

    private boolean pause = false;

    private int height, width;
    private Player player;

    private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

    public PlayField(int height, int width) {
        this.height = height;
        this.width = width;

        fields = Level.generateLevel(LevelType.TUTORIAL, height, width);
        player = new Player(1, 1);
        fields.get(1).set(1, player);

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        walk(Direction.UP, 0, -1);
                        break;
                    case KeyEvent.VK_DOWN:
                        walk(Direction.DOWN, 0, 1);
                        break;
                    case KeyEvent.VK_LEFT:
                        walk(Direction.LEFT, -1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        walk(Direction.RIGHT, 1, 0);
                        break;
                    case KeyEvent.VK_SPACE:
                        cut();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        pause = !pause;
                        if(pause){
                            //// TODO: 20-3-2017 playscreen
                        }
                        break;
                }

                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        if (image == null || imageAxe == null) loadImage();
    }

    private void cut() {

    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/backpack-icon.png"));
            imageAxe = ImageIO.read(getClass().getResource("/img/axes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void walk(Direction direction, int dx, int dy) {
        player.changeDirection(direction);

        int x = player.xPos;
        int y = player.yPos;

        if (fields.get(x + dx).get(y + dy).isSolid()) {
            return;
        }

        if (fields.get(x + dx).get(y + dy) instanceof Lumberaxe) {
            player.grabLumberaxe((Lumberaxe) fields.get(x + dx).get(y + dy));
        }

        player.move(dx, dy);
        fields.get(x).set(y, new Field(x, y));
        fields.get(x + dx).set(y + dy, player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ArrayList<Field> row : fields) {
            for (Field field : row) {
                field.paint(g);
            }
        }

        paintBackpack(g);
    }

    private void paintBackpack(Graphics g) {
        int x = (width - 1) * Field.SIZE;
        int y = 0;

        g.drawImage(image, x, y, null);

        if (player.getAxe() != null) {
            int offset = player.getAxe().getColor().ordinal() * Field.SIZE;

            g.drawImage(
                    imageAxe, // Source image
                    x, y, x + Field.SIZE, y + Field.SIZE, // Destination position
                    offset, 0, offset + Field.SIZE, Field.SIZE, // Source position
                    null
            );
        }
    }
}
