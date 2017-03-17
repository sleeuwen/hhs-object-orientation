package cutthetree;

import java.awt.*;

/**
 * Created by Koen van Zeijl on 17-3-2017.
 */
public class Tree extends Field {
    private int color;

    public Tree(int x, int y,int color){
        super(x,y);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public boolean cut(Lumberaxe axe){
        return false;
    }
}
