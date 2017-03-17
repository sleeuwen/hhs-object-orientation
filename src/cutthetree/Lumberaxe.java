package cutthetree;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Lumberaxe extends Field {
    private int code;

    public Lumberaxe(int code) {
        this.code = code;
    }

    public Lumberaxe(int x, int y, int code) {
        super(x, y);
        this.code = code;

        try {
            image = ImageIO.read(getClass().getResource("/img/lumberaxe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }
}
