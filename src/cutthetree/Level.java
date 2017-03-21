package cutthetree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Level {
    static ArrayList<ArrayList<Field>> generateLevel(LevelType type, int height, int width, int levelNumber) {
        URL resource;
        Random random = new Random();
        int number = random.nextInt(1) + 1;
        if (type != LevelType.TUTORIAL) {
            Game.setCurrentLevel(levelNumber == 0? number:levelNumber);
            resource = Level.class.getResource("/level/" + type.toString().toLowerCase().substring(0, 1) + (levelNumber == 0? number : levelNumber) + ".txt");
        }else {
            resource = Level.class.getResource("/level/" + type.toString().toLowerCase() + ".txt");
        }
        if (resource != null) {
            return loadLevel(resource.getFile());
        } else {
            throw new UnsupportedOperationException("Levels of type " + type.toString().toLowerCase() + " are not yet supported.");
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
            int y = 0;
            while ((line = in.readLine()) != null) {
                if (line.trim().isEmpty()) break;

                for (int x = 0; x < line.length(); x++) {
                    if (fields.size() <= x) fields.add(new ArrayList<>());

                    char chr = line.charAt(x);
                    if (chr == 'W') {
                        fields.get(x).add(new Wall(x, y));
                    } else if (chr == 'F') {
                        fields.get(x).add(new Finish(x, y));
                    } else if (chr >= '1' && chr <= '6') {
                        fields.get(x).add(new Lumberaxe(x, y, Color.values()[chr - '1']));
                    } else if (chr >= 'a' && chr <= 'f') {
                        fields.get(x).add(new Tree(x, y, Color.values()[chr - 'a']));
                    } else {
                        fields.get(x).add(new Field(x, y));
                    }
                }

                y++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fields;
    }
}
