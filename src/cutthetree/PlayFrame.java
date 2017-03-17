package cutthetree;

import javax.swing.*;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayFrame extends JFrame {
    private final static int FRAME_HEIGHT = 1040;
    private final static int FRAME_WIDTH = 1080;

    public PlayFrame(){
        getContentPane().setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        add(new PlayField(10, 10));
    }
}
