package cutthetree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by The lion kings on 20-3-2017.
 */
public class Game extends JComponent {
    private boolean playScreen = true;
    private Image image;
    private Font font;
    private PlayField playField;
    private int selected = 0;
    private String[] choices = new String[]{
            "Start","Difficulty","Exit"
    };


    public Game() {
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(selected == 0){
                        playField = new PlayField(12,12);
                        playScreen = false;
                        playField.grabFocus();
                        repaint();
                    }
                }


                if(e.getKeyCode() == KeyEvent.VK_UP){
                    if(selected>0){
                        selected--;
                        repaint();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(selected<choices.length-1){
                        selected++;
                        repaint();
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



        loadFont();
        loadImage();
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/img/menuBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (playScreen) {

            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawImage(image,0,0,null);
            g.setColor(Color.lightGray);
            g.fillRect(225,232,450,464);
            g.setColor(Color.WHITE);

            drawCentered(g,"CutThaTree",150);
            for(int i = 0; i < choices.length; i++){
                if(i==selected){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.WHITE);
                }
                drawCentered(g,choices[i],420+(i*32));
            }

        } else {
            playField.paintComponent(g);

        }
    }

    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str,getWidth()/2-width/2,y);
    }
}
