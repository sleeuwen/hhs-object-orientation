package cutthetree.menus;

import cutthetree.Game;
import cutthetree.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Menu extends JComponent {
    protected static Image imageSound, imageNoSound;
    protected static Image imageFx, imageNoFx;
    protected static Font font;

    protected final String[] choices;
    protected int selected = 0;
    protected Game game;

    protected Menu(Game game, String... choices) {
        this.game = game;
        this.choices = choices;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                onKeyPress(e);
            }
        });

        if (imageSound == null || imageFx == null) loadImages();
        if (font == null) loadFont();
    }

    private static void loadImages() {
        try {
            imageSound = ImageIO.read(Menu.class.getResource("/img/sound.png"));
            imageNoSound = ImageIO.read(Menu.class.getResource("/img/noSound.png"));
            imageFx = ImageIO.read(Menu.class.getResource("/img/fx.png"));
            imageNoFx = ImageIO.read(Menu.class.getResource("/img/noFx.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Menu.class.getResourceAsStream("/font/pokemon.ttf")).deriveFont(32f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (selected > 0) selected--;
                break;
            case KeyEvent.VK_DOWN:
                if (selected < choices.length - 1) selected++;
                break;
            case KeyEvent.VK_ENTER:
                itemSelected(selected);
                break;
        }
    }

    protected void enableSoundToggler(int x, int y) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > x && e.getX() < x + 40 && e.getY() > y && e.getY() < y + 40) {
                    game.toggleSound();
                } else if (e.getX() > x + 45 && e.getX() < x + 85 && e.getY() > y && e.getY() < y + 40) {
                    game.toggleEffects();
                }
            }
        });
    }

    abstract void itemSelected(int index);

    @Override
    public abstract void paintComponent(Graphics g);

    protected void paintSoundToggles(Graphics g, int x, int y) {
        g.drawImage(game.isSoundEnabled() ? imageSound : imageNoSound, x, y, null);
        g.drawImage(game.isEffectsEnabled() ? imageFx : imageNoFx, x + 45, y, null);
    }

    protected void paintChoices(Graphics g) {
        g.setFont(font);

        for (int i = 0; i < choices.length; i++) {
            g.setColor(i == selected ? Color.RED : Color.WHITE);

            drawCentered(g, choices[i], 420 + (i * 32));
        }
    }

    protected void drawCentered(Graphics g, String str, int y) {
        int width = g.getFontMetrics().stringWidth(str);

        g.drawString(str, GameFrame.FRAME_WIDTH / 2 - width / 2, y);
    }
}
