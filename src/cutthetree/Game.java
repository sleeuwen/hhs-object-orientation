package cutthetree;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by The lion kings on 20-3-2017.
 */
public class Game extends JComponent {
    private boolean playScreen = true;
    private Image imageBackground;
    private Font font;
    private PlayField playField;

    private boolean paused = false;

    private int selected = 0;
    private boolean selectLevel;
    private int difficulty = 0;
    private static Clip clip;
    private Image imageField;
    private Image imageTitle;
    private Image imagePaused;
    private Image imageSound;
    private Image imageNoSound;
    private Image imageFx;
    private Image imageNoFx;
    private int finishSelected = 0;

    private static boolean finished = false;

    private static int currentLevel;

    private static boolean sound = true, fx = true;


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

    private String[] finishMenu = new String[]{
            "Exit"
    };

    public Game() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!playScreen && !finished) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        paused = !paused;
                    } else if (paused) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                if (selected > 0) selected--;
                                break;
                            case KeyEvent.VK_DOWN:
                                if (selected < choices.length - 1) selected++;
                                break;
                            case KeyEvent.VK_ENTER:
                                if (selected == 1) {
                                    playField = new PlayField(12, 12, LevelType.values()[difficulty], currentLevel);
                                } else if (selected == 2) {
                                    playScreen = true;
                                    Game.loadSound("opening.wav");
                                }

                                selected = 0;
                                paused = false;
                                break;

                        }
                    } else {
                        playField.dispatchEvent(e);
                    }

                    return;
                } else if (!playScreen && finished) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        playScreen = true;
                        finished = false;
                    }

                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if (!selectLevel) {
                            if (selected == 0) {
                                playField = new PlayField(12, 12, LevelType.values()[difficulty], 0);
                                playScreen = false;
                                clip.stop();
                            } else if (selected == 1) {
                                selectLevel = true;
                            } else if (selected == 2) {
                                System.exit(0);
                            }
                        } else {
                            if (selected < levels.length - 1) difficulty = selected;

                            selectLevel = false;
                        }

                        selected = 0;
                        break;
                    case KeyEvent.VK_UP:
                        if (selected > 0) selected--;
                        break;
                    case KeyEvent.VK_DOWN:
                        String[] choices = selectLevel ? levels : Game.this.choices;
                        if (selected < choices.length - 1) selected++;
                        break;
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (paused) {
                    if (x > 168 && x < 208 && y > 594 && y < 636) sound = !sound;
                    if (x > 213 && x < 253 && y > 594 && y < 636) fx = !fx;
                } else {
                    if (x > 240 && x < 280 && y > 640 && y < 680) {
                        sound = !sound;

                        if (sound) {
                            clip.start();
                        } else {
                            clip.stop();
                        }
                    }
                    if (x > 285 && x < 325 && y > 640 && y < 680) fx = !fx;
                }
            }
        });

        loadFont();
        loadImages();
        Game.loadSound("opening.wav");

        // Start game loop.
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();
    }

    public static void setCurrentLevel(int level){
        currentLevel = level;
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadSound(String filename) {
        try {
            if((sound && Arrays.asList(sounds).contains(filename.split("\\.")[0])) || (fx && Arrays.asList(effects).contains(filename.split("\\.")[0]))){
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Game.class.getResource("/sound/" + filename)));
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadImages() {
        try {
            imageBackground = ImageIO.read(getClass().getResource("/img/menuBackground.png"));
            imageField = ImageIO.read(getClass().getResource("/img/menuField.png"));
            imageTitle = ImageIO.read(getClass().getResource("/img/menuTitle.png"));
            imagePaused = ImageIO.read(getClass().getResource("/img/pauseScreen.png"));
            imageSound = ImageIO.read(getClass().getResource("/img/sound.png"));
            imageNoSound = ImageIO.read(getClass().getResource("/img/noSound.png"));
            imageFx = ImageIO.read(getClass().getResource("/img/fx.png"));
            imageNoFx = ImageIO.read(getClass().getResource("/img/noFx.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() {
        int fps = 1000 / 12;

        while (true) {
            try {
                long start = System.nanoTime();
                repaint();
                long diff = System.nanoTime() - start;

                // Sleep to accomplish wanted fps
                Thread.sleep(Math.max(0, fps - diff / 1_000_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (playScreen) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawImage(imageBackground, 0, 0, null);

            g.drawImage(imageField, 225, 232, 450, 464, null);
            g.drawImage(imageTitle, getWidth() / 2 - 200, 120, null);

            g.drawImage(sound ? imageSound : imageNoSound, 240, 640, null);
            g.drawImage(fx ? imageFx : imageNoFx, 285, 640, null);

            g.setColor(Color.WHITE);
            String[] choices = selectLevel ? levels : this.choices;
            for (int i = 0; i < choices.length; i++) {
                g.setColor(i == selected ? Color.RED : Color.WHITE);

                drawCentered(g, choices[i], 420 + (i * 32));
            }
        } else {
            playField.paintComponent(g);

            if (paused) {
                g.drawImage(imagePaused, 0, 0, null);
                g.setFont(font);
                g.drawImage(sound ? imageSound : imageNoSound, 168, 594, null);
                g.drawImage(fx ? imageFx : imageNoFx, 213, 594, null);

                for (int i = 0; i < choicesMenu.length; i++) {
                    g.setColor(i == selected ? Color.RED : Color.WHITE);

                    drawCentered(g, choicesMenu[i], 420 + (i * 32));
                }
            }

            if (finished) {
                for (int i = 0; i < finishMenu.length; i++) {
                    g.setFont(font);
                    g.setColor(i == selected ? Color.RED : Color.WHITE);

                    drawCentered(g, finishMenu[i], 420 + (i*32));
                }
            }
        }
    }

    private void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str, getWidth() / 2 - width / 2, y);
    }

    public static void setFinished(boolean b){
        finished = b;
    }
}
