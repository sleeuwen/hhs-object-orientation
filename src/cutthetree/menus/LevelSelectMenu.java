package cutthetree.menus;

import cutthetree.Game;
import cutthetree.GameState;

public class LevelSelectMenu extends StartMenu {
    public LevelSelectMenu(Game game) {
        super(game, "Tutorial", "Easy", "Medium", "Hard");
    }

    @Override
    void itemSelected(int index) {
        game.setDifficulty(index);
        Game.changeState(GameState.START);
    }
}
