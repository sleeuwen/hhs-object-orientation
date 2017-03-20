package cutthetree;

import javax.swing.*;

/**
 * Created by The lion kings on 17-3-2017.
 */

public class Main {
    public static void main(String[] args) {
        JFrame window = new PlayFrame();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Cut The Tree");
        window.setResizable(false);
        window.setVisible(true);
    }
}
