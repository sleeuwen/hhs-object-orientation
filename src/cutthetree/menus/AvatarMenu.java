package cutthetree.menus;

import cutthetree.Avatars;
import cutthetree.Game;
import cutthetree.GameFrame;
import cutthetree.GameState;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Created by Donald Trump on 27-3-2017.
 */
public class AvatarMenu extends Menu{
    private static Image background;
    private static Image menuField;
    private static Image title;

    public AvatarMenu(Game game) {
        super(game, "Woody","Ash","Henk");
        if(background == null)loadImage();
    }
    private static void loadImage() {
        try {
            background = ImageIO.read(StartMenu.class.getResource("/img/menuBackground.png"));
            menuField = ImageIO.read(StartMenu.class.getResource("/img/menuField.png"));
            title = ImageIO.read(StartMenu.class.getResource("/img/menuTitle.png"));
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
}
