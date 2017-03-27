package cutthetree.menus;

import cutthetree.Avatars;
import cutthetree.Game;
import cutthetree.GameState;

import java.awt.*;

/**
 * Created by Donald Trump on 27-3-2017.
 */
public class AvatarMenu extends StartMenu{
    private static Image background;


    public AvatarMenu(Game game) {
        super(game, "Woody","Ash","Henk");
    }

    @Override
    void itemSelected(int index) {
        Game.setAvatar(Avatars.values()[index]);
        Game.changeState(GameState.START);
    }
}
