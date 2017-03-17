package cutthetree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Wall extends Field {
    public Wall(int x, int y) {
        super(x, y);

        isSolid = true;
        try {
            image = ImageIO.read(getClass().getResource("/img/solidTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
