package cutthetree;

import javax.swing.*;
import java.awt.*;

/**
 * Instances of this class will create a new {@link JFrame} with
 * the correct size.
 */
public class GameFrame extends JFrame {
    protected final static int FRAME_HEIGHT = 900;
    protected final static int FRAME_WIDTH = 900;

    public GameFrame() {
        getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setTitle("Cut The Tree");
        setResizable(false);

        add(new Game());
        pack();
    }
}
