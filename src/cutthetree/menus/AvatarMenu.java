package cutthetree.menus;

import cutthetree.Avatar;
import cutthetree.Game;
import cutthetree.GameFrame;
import cutthetree.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by The lion kings on 27-3-2017.
 */
public class AvatarMenu extends Menu{
    private static Image background;
    private static Image menuField;
    private static Image title;
    private static Image[] avatars = new Image[3];
    private static Font avatarFont;

    private long start = System.currentTimeMillis();
    private int selected = 0;
    private int walk = 2;

    public AvatarMenu(Game game) {
        super(game, "Woody", "Ash", "Peeta");

        enableSoundToggler(240, 640);
        if (background == null) loadImages();
        if (avatarFont == null) loadFont();
    }

    private static void loadImages() {
        try {
            background = ImageIO.read(StartMenu.class.getResource("/img/menuBackground.png"));
            menuField = ImageIO.read(StartMenu.class.getResource("/img/menuField.png"));
            title = ImageIO.read(StartMenu.class.getResource("/img/menuTitle.png"));
            avatars[0] = ImageIO.read(StartMenu.class.getResource("/img/woodyWalkingSprite.png"));
            avatars[1] = ImageIO.read(StartMenu.class.getResource("/img/ashWalkingSprite.png"));
            avatars[2] = ImageIO.read(StartMenu.class.getResource("/img/peetaWalkingSprite.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFont() {
        try {
            avatarFont = Font.createFont(Font.TRUETYPE_FONT, Menu.class.getResourceAsStream("/font/pokemon.ttf")).deriveFont(20f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void itemSelected(int index) {
        Game.setAvatar(Avatar.values()[index]);
        Game.changeState(GameState.START);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);

        g.drawImage(title, GameFrame.FRAME_WIDTH / 2 - 200, 120, null);
        g.drawImage(menuField, 225, 232, 450, 464, null);

        paintSoundToggles(g, 240, 640);
        paintChoices(g);
    }

    @Override
    protected void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (selected > 0) selected--;
                break;
            case KeyEvent.VK_RIGHT:
                if (selected < choices.length - 1) selected++;
                break;
            case KeyEvent.VK_ENTER:
                itemSelected(selected);
                break;
        }
    }

    @Override
    protected void paintChoices(Graphics g) {
        g.setFont(avatarFont);

        for (int i = 0; i < choices.length; i++) {
            g.setColor(i == selected ? Color.RED : Color.WHITE);

            int offset = 0;

            if (i == selected) {
                long diff = System.currentTimeMillis() - start;

                if (diff > 150) {
                    start = System.currentTimeMillis();

                    walk = (walk + 1) % 4;
                }

                offset = walk * 120;
            }

            g.drawImage(
                    avatars[i], // Source image
                    242 + (i * 150), 450, 362 + (i * 150), 570, // Destination position
                    offset, 0, offset + 120, 120, // Source position
                    null
            );

            drawCentered(g, choices[i], i);
        }
    }

    @Override
    protected void drawCentered(Graphics g, String str, int i) {
        int width = g.getFontMetrics().stringWidth(str);

        g.drawString(str, GameFrame.FRAME_WIDTH / 6 * (i + 2) - width / 2, 600);
    }
}
