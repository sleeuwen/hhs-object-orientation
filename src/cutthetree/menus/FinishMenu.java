package cutthetree.menus;

import cutthetree.Game;
import cutthetree.GameState;
import cutthetree.PlayField;

import javax.imageio.ImageIO;
import java.awt.*;

public class FinishMenu extends Menu {
    private static Image background;

    public FinishMenu(Game game) {
        super(game, "Exit");

        if (background == null) loadImage();
    }

    private static void loadImage() {
        try {
            background = ImageIO.read(PlayField.class.getResource("/img/finished.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void itemSelected(int index) {
        Game.changeState(GameState.START);
        Game.stopSound();
        Game.loadSound("opening.wav");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);

        paintChoices(g);
    }
}
