package cutthetree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new GameFrame();
        try {
            window.setIconImage(ImageIO.read(Main.class.getResource("/img/gameIcon.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
