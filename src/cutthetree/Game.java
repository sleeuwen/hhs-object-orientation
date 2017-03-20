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
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
    private static Clip clip;
    private Image imageF;
    private Image imageT;
    private Image imageMenu;
    private Image imageSound;
    private Image imageNoSound;
    private Image imageFx;
    private Image imageNoFx;

    private static boolean sound = true,fx = true;


    private static String[] sounds = new String[]{
        "opening","winning"
    };
    private static String[] effects = new String[]{
            "chopping","grab"
    };
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
                                    Game.loadSound("opening.wav");
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();

                if (x>240&&x<280&&y>640&&y<680){
                    sound = !sound;
                    if (sound){
                        clip.start();
                    }else{
                        clip.stop();
                    }
                }
                if (x>285&&x<325&&y>640&&y<680) fx = !fx;
                repaint();
            }
        });
        loadFont();
        loadImage();
        Game.loadSound("opening.wav");
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void loadSound(String filename){
        try {
            if((sound && Arrays.asList(sounds).contains(filename.split("\\.")[0])) || (fx && Arrays.asList(effects).contains(filename.split("\\.")[0]))){
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(Game.class.getResource("/sound/" + filename).getFile())));
                clip.start();
            }
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
            imageSound = ImageIO.read(getClass().getResource("/img/sound.png"));
            imageNoSound = ImageIO.read(getClass().getResource("/img/noSound.png"));
            imageFx = ImageIO.read(getClass().getResource("/img/fx.png"));
            imageNoFx = ImageIO.read(getClass().getResource("/img/noFx.png"));

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


            g.drawImage(sound?imageSound:imageNoSound,240,640,null);
            g.drawImage(fx?imageFx:imageNoFx,285,640,null);
            //g.drawImage(imageSound,240,640,null);
            //g.drawImage(imageFx, 285,640,null);
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
