package cutthetree;

import javax.swing.*;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayFrame extends JFrame {
    private final static int FRAME_HEIGHT = 940;
    private final static int FRAME_WIDTH = 900;

    public PlayFrame(){
        getContentPane().setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        add(new PlayField(10, 10));
    }
}
