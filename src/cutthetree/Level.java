package cutthetree;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Level {
    static ArrayList<ArrayList<Field>> generateLevel(LevelType type, int height, int width) {
        if (type == LevelType.RANDOM) {
            throw new UnsupportedOperationException("Levels of type random are not yet supported.");
        } else {
            URL resource = Level.class.getResource("/level/" + type.toString().toLowerCase() + ".txt");

            if (resource != null) {
                return loadLevel(resource.getFile());
            } else {
                throw new UnsupportedOperationException("Levels of type " + type.toString().toLowerCase() + " are not yet supported.");
            }
        }
    }

    private static ArrayList<ArrayList<Field>> loadLevel(String file) {
        // Load a level from a given file path following the given spec:
        // W: Wall
        // F: Finish
        // 1-6: Different colored Lumberaxe
        // a-f: Different colored Tree
        // Anything else as Field

        ArrayList<ArrayList<Field>> fields = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line;
            int x = 0;
            while ((line = in.readLine()) != null) {
                if (line.trim().isEmpty()) break;

                for (int y = 0; y < line.length(); y++) {
                    if (fields.size() <= y) fields.add(new ArrayList<>());

                    char chr = line.charAt(y);
                    if (chr == 'W') {
                        fields.get(y).add(new Wall(y, x));
                    } else if (chr == 'F') {
                        fields.get(y).add(new Finish(y, x));
                    } else if (chr >= '1' && chr <= '6') {
                        fields.get(y).add(new Lumberaxe(y, x, Color.values()[chr - '1']));
                    } else if (chr >= 'a' && chr <= 'f') {
                        fields.get(y).add(new Tree(y, x, Color.values()[chr - 'a']));
                    } else {
                        fields.get(y).add(new Field(y, x));
                    }
                }

                x++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fields;
    }
}
