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

    private boolean pause = false;

    private int selected = 0;
    private boolean selectLevel;
    private int difficulty = 0;
    private Clip clip;
    private Image imageF;
    private Image imageT;
    private Image imageMenu;


    private String[] choices = new String[]{
            "Start", "Difficulty", "Exit"
    };
    private String[] choicesMenu = new String[]{
            "Continue", "Restart", "Exit"};

    private String[] levels = new String[]{
            "Tutorial", "Easy", "Medium", "Hard", "Back"
    };

    public Game() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!playScreen) {

                    if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                        pause = !pause;
                        repaint();
                        return;
                    }
                    else if(pause) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                if(selected>0){
                                    selected--;}
                                break;
                            case KeyEvent.VK_DOWN:
                                if(selected<choices.length-1);
                                selected++;
                                break;
                            case KeyEvent.VK_ENTER:
                                if(selected == 1){
                                   playField = new PlayField(12,12);
                                }
                                if(selected == 2) {
                                    playScreen = true;
                                }
                                selected = 0;
                                pause = false;
                                break;

                        }
                        repaint();
                        return;
                    }

                    playField.dispatchEvent(e);
                    repaint();
                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if(!selectLevel){
                            if (selected == 0) {
                                playField = new PlayField(12,12);
                                playScreen = false;
                                clip.stop();
                                repaint();

                            }
                            if (selected == 1){
                                selectLevel = true;
                                selected = 0;
                                repaint();
                            }
                            if (selected == 2){
                                System.exit(0);
                            }
                        }else{

                            if(selected != 4){
                                difficulty = selected;

                            }
                            selectLevel = false;
                            selected = 0;
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (selected > 0) selected--;
                        break;
                    case KeyEvent.VK_DOWN:
                        String[] choices = selectLevel ? levels : Game.this.choices;
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
            imageF = ImageIO.read(getClass().getResource("/img/menuField.png"));
            imageT = ImageIO.read(getClass().getResource("/img/menuTitle.png"));
            imageMenu = ImageIO.read(getClass().getResource("/img/pauseScreen.png"));
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

            g.drawImage(imageF, 225, 232, 450, 464, null);
//            g.setColor(Color.lightGray);
//            g.fillRect(225, 232, 450, 464);

            g.setColor(Color.WHITE);
            g.drawImage(imageT, getWidth() / 2 - 200, 120, null);


            String[] choices = selectLevel ? levels : this.choices;
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


            if (pause) {
                g.drawImage(imageMenu, 0, 0, null);
                g.setFont(font);

                for (int i = 0; i < choicesMenu.length; i++) {
                    if (i == selected) {
                        g.setColor(java.awt.Color.RED);
                    } else {
                        g.setColor(Color.WHITE);
                    }

                    drawCentered(g, choicesMenu[i], 420 + (i * 32));
                }
            }
        }
    }

    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str, getWidth() / 2 - width / 2, y);
    }
}
