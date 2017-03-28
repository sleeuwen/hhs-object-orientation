package cutthetree.menus;

import cutthetree.Avatars;
import cutthetree.Game;
import cutthetree.GameFrame;
import cutthetree.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Donald Trump on 27-3-2017.
 */
public class AvatarMenu extends Menu{
    private static Image background;
    private static Image menuField;
    private static Image title;
    private static Image[] avatars = new Image[3];
    private long start = System.currentTimeMillis();
    private long diff;

    protected int selected = 0;
    protected int walk = 2;
    private Font avatarFont;

    public AvatarMenu(Game game) {
        super(game, "Woody","Ash","Peeta");
        if(background == null)loadImage();
    }
    private static void loadImage() {
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

    @Override
    void itemSelected(int index) {
        Game.setAvatar(Avatars.values()[index]);
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


    private void loadFont() {
        try {
            avatarFont = Font.createFont(Font.TRUETYPE_FONT, Menu.class.getResourceAsStream("/font/pokemon.ttf")).deriveFont(20f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintChoices(Graphics g) {
        loadFont();
        g.setFont(avatarFont);
        //g.drawImage()

        for (int i = 0; i < choices.length; i++) {
            g.setColor(i == selected ? Color.RED : Color.WHITE);
            if (i == selected){
                diff = System.currentTimeMillis() - start;

                if (diff > 150){
                    start = System.currentTimeMillis();
                    if (walk != 3) {walk++;}
                    else if (walk == 3) walk = 0;
                }
                g.drawImage(
                        avatars[i], // Source image,
                        242 + (i*150), 450, 242+(i*150) + 120, 450 + 120, // Destination position
                        (walk * 120), 0, 120 + (walk * 120), 120, // Source position
                        null
                );


            }else{
                g.drawImage(
                        avatars[i], // Source image,
                        242 + (i*150), 450, 242+(i*150) + 120, 450 + 120, // Destination position
                        0, 0, 120, 120, // Source position
                        null
                );
            }

            drawCentered(g,choices[i],600,i);
        }
    }

    protected void drawCentered(Graphics g, String str, int y, int i) {
        int width = g.getFontMetrics().stringWidth(str);

        g.drawString(str, GameFrame.FRAME_WIDTH / 6 * (i+2) - width / 2, y);
    }
}
