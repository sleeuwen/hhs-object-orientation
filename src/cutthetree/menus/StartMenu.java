package cutthetree.menus;

import cutthetree.Game;
import cutthetree.GameFrame;
import cutthetree.GameState;

import javax.imageio.ImageIO;
import java.awt.*;

public class StartMenu extends Menu {
    private static Image background;
    private static Image menuField;
    private static Image title;

    public StartMenu(Game game) {
        this(game, "Start", "Difficulty", "Avatar", "Exit");
    }

    protected StartMenu(Game game, String... choices) {
        super(game, choices);

        enableSoundToggler(240, 640);
        if (background == null || menuField == null || title == null) loadImage();
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
        if (index == 0) {
            game.start();
        } else if (index == 1) {
            Game.changeState(GameState.LEVEL_SELECT);
        } else if (index == 2) {
            Game.changeState(GameState.AVATAR);
        } else if (index == 3) {
            System.exit(0);
        }

        selected = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);

        g.drawImage(title, GameFrame.FRAME_WIDTH / 2 - 200, 120, null);
        g.drawImage(menuField, 225, 232, 450, 464, null);

        paintSoundToggles(g, 240, 640);
        paintChoices(g);
        paintVersion(g);
    }
}
