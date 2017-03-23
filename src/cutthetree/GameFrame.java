package cutthetree;

import javax.swing.*;

/**
 * Instances of this class will create a new {@link JFrame} with
 * the correct size.
 */
public class GameFrame extends JFrame {
    protected final static int FRAME_HEIGHT = 929;
    protected final static int FRAME_WIDTH = 900;

    public GameFrame() {
        getContentPane().setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Cut The Tree");
        setResizable(false);

        add(new Game());
    }
}
