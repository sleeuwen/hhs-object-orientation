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

        for (int i = 0; i < width; i++) {
            ArrayList<Field> row = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                row.add(new Field(i * 90, j * 90));
            }
            fields.add(row);
        }

        fields.get(0).set(7, new Lumberaxe(0, 7 * 90, 4));
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
