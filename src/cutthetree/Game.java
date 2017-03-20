package cutthetree;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by The lion kings on 20-3-2017.
 */
public class Game extends JComponent {
    private boolean playScreen = true;
    private Image image;
    private Font font;
    private PlayField playField;
    private int selected = 0;
    private Clip clip;

    private String[] choices = new String[]{
            "Start", "Difficulty", "Exit"
    };

    public Game() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!playScreen) {
                    playField.dispatchEvent(e);
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if (selected == 0) {
                            playField = new PlayField(12, 12);
                            playScreen = false;
                            clip.stop();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (selected > 0) selected--;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (selected < choices.length - 1) selected++;
                        break;
                }

                repaint();
            }
        });

        loadFont();
        loadImage();
        loadSound();
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void loadSound(){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/sound/opening.wav").getFile())));
            clip.start();
        }
        catch (Exception e) {
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
            g.drawImage(image, 0, 0, null);

            g.setColor(Color.lightGray);
            g.fillRect(225, 232, 450, 464);

            g.setColor(Color.WHITE);
            drawCentered(g, "CutThaTree", 150);

            for (int i = 0; i < choices.length; i++) {
                if (i == selected) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.WHITE);
                }

                drawCentered(g, choices[i], 420 + (i * 32));
            }
        } else {
            playField.paintComponent(g);
        }
    }

    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str, getWidth() / 2 - width / 2, y);
    }
}
