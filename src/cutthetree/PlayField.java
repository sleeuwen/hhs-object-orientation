package cutthetree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayField extends JComponent {
    private int height, width;
    private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

    public PlayField(int height, int width){
        this.height = height;
        this.width = width;
        fields = Level.generateLevel(Level.Type.tutorial,height,width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (ArrayList<Field> row : fields) {
            for (Field field : row) {
                field.paint(g);
            }
        }
    }
}
