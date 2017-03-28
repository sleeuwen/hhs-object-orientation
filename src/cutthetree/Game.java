package cutthetree;

import cutthetree.menus.*;
import cutthetree.menus.Menu;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The Game class is responsible for keeping track of
 * the current state of the game and painting it.
 *
 * It is also used to play songs and sound effects, and
 * disable them when needed.
 */
public class Game extends JComponent {
    private PlayField playField;

    private int difficulty = 0;
    private static Clip clip;
    private static int currentLevel;
    private static Avatars avatar = Avatars.WOODY;

    private static boolean sound = true, fx = true;

    private static GameState state = GameState.START;

    private static String[] sounds = new String[]{
            "opening", "winning"
    };
    private static String[] effects = new String[]{
            "chopping", "grab"
    };

    private Map<GameState, Menu> menus = new HashMap<>();

    public Game() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (state == GameState.PLAYING) playField.dispatchEvent(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (state == GameState.PLAYING) {
                    playField.dispatchEvent(e);
                } else {
                    menus.get(state).dispatchEvent(e);
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state != GameState.PLAYING) {
                    menus.get(state).dispatchEvent(e);
                }
            }
        });

        menus.put(GameState.START, new StartMenu(this));
        menus.put(GameState.LEVEL_SELECT, new LevelSelectMenu(this));
        menus.put(GameState.AVATAR, new AvatarMenu(this));
        menus.put(GameState.PAUSED, new PauseMenu(this));
        menus.put(GameState.FINISHED, new FinishMenu(this));

        Game.loadSound("opening.wav");

        // Start game loop.
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();
    }
    public static void stopSound() {
        clip.stop();
    }
    public static void loadSound(String filename) {
        try {
            if ((sound && Arrays.asList(sounds).contains(filename.split("\\.")[0])) || (fx && Arrays.asList(effects).contains(filename.split("\\.")[0]))) {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Game.class.getResource("/sound/" + filename)));
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCurrentLevel(int level) {
        currentLevel = level;
    }

    public static void changeState(GameState state) {
        Game.state = state;
    }

    public void toggleSound() {
        sound = !sound;

        if (state.ordinal() < GameState.PLAYING.ordinal() && sound) {
            clip.start();
        } else {
            clip.stop();
        }
    }

    public void toggleEffects() {
        fx = !fx;
    }

    public boolean isSoundEnabled() {
        return sound;
    }

    public boolean isEffectsEnabled() {
        return fx;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public static void setAvatar(Avatars chr) {
        avatar = chr;
    }

    public static Avatars getAvatar() {
        return avatar;
    }

    public void start() {
        playField = new PlayField(12, 12, LevelType.values()[difficulty], 0);
        state = GameState.PLAYING;
        clip.stop();
    }

    public void restart() {
        playField = new PlayField(12, 12, LevelType.values()[difficulty], currentLevel);
        state = GameState.PLAYING;
    }

    private void gameLoop() {
        int fps = 1000 / 30;

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
        if (state.ordinal() >= GameState.PLAYING.ordinal()) {
            playField.paintComponent(g);
        }

        if (menus.containsKey(state)) {
            menus.get(state).paintComponent(g);
        }
    }
}
