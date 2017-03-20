package cutthetree;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/**
 * Created by The lion kings on 20-3-2017.
 */
public class Game extends JComponent {
    private boolean playScreen = true;
    private Font font;
    public Game() {
        loadFont();
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (playScreen) {






            g.setColor(Color.BLACK);
            g.setFont(font);
            g.fillRect(0, 0, 900, 929);
            g.setColor(Color.lightGray);
            g.fillRect(225,232,450,464);
            g.setColor(Color.WHITE);

            int width = g.getFontMetrics().stringWidth("CutThaTree");

//            g.drawString("CutThaTree",getWidth()/2-width / 2,150);
//            g.drawString("Start",250,420);
//            g.drawString("Difficulty",250,452);
//            g.drawString("Exit",250,484);


            drawCentered(g,"CutThaTree",150);
            drawCentered(g,"Start",420);
            drawCentered(g,"Difficulty",452);
            drawCentered(g,"Exit",484);
        } else {
            new PlayField(12, 12);
        }
    }

    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str,getWidth()/2-width,y);
    }
}
