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
    private int height, width;
    private Player player;

    private Image imageAxe;
    private Image image;
    private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

    public PlayField(int height, int width){
        this.height = height;
        this.width = width;

        try {
            image = ImageIO.read(getClass().getResource("/img/backpack-icon.png"));
            imageAxe = ImageIO.read(getClass().getResource("/img/axes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fields = Level.generateLevel(Level.Type.tutorial,height,width);
        player = new Player(1,1);
        fields.get(1).set(1,player);
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==40){
                    int xPos = player.xPos;
                    int yPos = player.yPos;
                    if (fields.get(xPos/75).get((yPos+75)/75) instanceof Tree || fields.get(xPos/75).get((yPos+75)/75) instanceof Wall) return;
                    if (fields.get(xPos/75).get((yPos+75)/75) instanceof Lumberaxe){
                        player.grabLumberaxe((Lumberaxe) fields.get(xPos/75).get((yPos+75)/75));
                    }
                    player.move(0,75);
                    fields.get(xPos/75).set(yPos/75,new Field(xPos/75,yPos/75));
                    fields.get(player.xPos/75).set(player.yPos/75,player);
                    PlayField.this.repaint();
                }
                if (e.getKeyCode()==38){
                    int xPos = player.xPos;
                    int yPos = player.yPos;
                    if (fields.get(xPos/75).get((yPos-75)/75) instanceof Tree || fields.get(xPos/75).get((yPos-75)/75) instanceof Wall) return;
                    player.move(0,-75);
                    fields.get(xPos/75).set(yPos/75,new Field(xPos/75,yPos/75));
                    fields.get(player.xPos/75).set(player.yPos/75,player);
                    PlayField.this.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        setFocusable(true);
        addKeyListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ArrayList<Field> row : fields) {
            for (Field field : row) {
                field.paint(g);
                if(field.xPos/75==11&&field.yPos/75==0){
                    g.drawImage(image,field.xPos,field.yPos,null);
                    System.out.println(player.getAxe());
                    if(player.getAxe()!=null){

                        int idx = player.getAxe().getColor().ordinal();
                        g.drawImage(imageAxe, field.xPos, field.yPos, field.xPos + 75, field.yPos + 75, idx * 75, 0, (idx + 1) * 75, 75, null);
                    }

                }

            }
        }
    }
}
