package cutthetree;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayField extends JComponent {
    private static Image imageAxe;
    private static Image image;
    private static Image imageMenu;

    private boolean finished = false;

    private int height, width;
    private Player player;

    private int selected = 0;
    private Font font;



    private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

    public PlayField(int height, int width) {
        this.height = height;
        this.width = width;

        fields = Level.generateLevel(LevelType.TUTORIAL, height, width);

        player = new Player(1, 1);
        fields.get(1).set(1, player);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.say("");

                if (!finished) {
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
                            checkTree();
                            break;

                    }
                }

                repaint();
            }
        });

        if (image == null || imageAxe == null) loadImage();
        loadFont();
    }

    private void checkTree() {
        switch (player.getDirection()){
            case UP:
                cut(0,-1);
                break;
            case DOWN:
                cut(0,1);
                break;
            case LEFT:
                cut(-1,0);
                break;
            case RIGHT:
                cut(1,0);
                break;
        }
    }


    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/backpack-icon.png"));
            imageAxe = ImageIO.read(getClass().getResource("/img/axes.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cut(int dx, int dy){
        int x = player.xPos;
        int y = player.yPos;

        if (!fields.get(x + dx).get(y + dy).isSolid() || fields.get(x + dx).get(y + dy) instanceof Wall) {
            return;
        }
        Tree tree = (Tree)fields.get(x + dx).get(y + dy);
        if(tree.cut(player.getAxe())){
            fields.get(x + dx).set(y +dy, new Field(x+dx, y+dy));
            loadSound("chopping.wav");
        }else {
            player.say("I need a " + tree.getColor() + " axe to cut this tree");
        }
    }
    private void loadSound(String filename){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/sound/" + filename).getFile())));
            clip.start();
        }
        catch (Exception e) {
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
            loadSound("grab.wav");
        }
        if (fields.get(x + dx).get(y + dy) instanceof Finish) {
            loadSound("winning.wav");
            finished = true;
            fields.get(x).set(y, new Field(x, y));
            return;
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
        if(!finished) player.paint(g);

    }
    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str, PlayFrame.FRAME_WIDTH / 2 - width / 2, y);

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
    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
