package cutthetree;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Lumberaxe extends Field {
    private int color;

    //public Lumberaxe(int code) {
    //    this.code = code;
    //}

    public Lumberaxe(int x, int y, int color) {
        super(x, y);
        this.color = color;

        try {
            image = ImageIO.read(getClass().getResource("/img/lumberaxe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return color;
    }
}
