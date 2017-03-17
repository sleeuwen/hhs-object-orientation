package cutthetree;

import java.awt.*;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Wall extends Field {
    public Wall(int x, int y) {
        super(x, y);

        isSolid = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
