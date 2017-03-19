package cutthetree;

import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class Level {
    static ArrayList<ArrayList<Field>> generateLevel(LevelType type, int height, int width) {
        ArrayList<ArrayList<Field>> fields = new ArrayList<>();

        switch (type) {
            case RANDOM:
                break;
            case TUTORIAL:
                return getTutorialField();
            case EASY:
                break;
            case MEDIUM:
                break;
            case HARD:
                break;
        }

        return fields;
    }

    static private ArrayList<ArrayList<Field>> getTutorialField() {
        ArrayList<ArrayList<Field>> fields = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            ArrayList<Field> singleFields = new ArrayList<>();

            for (int j = 0; j < 12; j++) {
                if (j == 0 || j == 11 || i == 0 || i == 11) {
                    singleFields.add(new Wall(i, j));
                    continue;
                }

                if (i == 1) {
                    singleFields.add(j == 9 ? new Lumberaxe(i, j, Color.PURPLE) : new Field(i, j));
                } else if (i == 2) {
                    singleFields.add(new Wall(i, 1));
                    singleFields.add(new Field(i, 2));
                    singleFields.add(new Field(i, 3));
                    singleFields.add(new Wall(i, 4));
                    singleFields.add(new Wall(i, 5));
                    singleFields.add(new Tree(i, 6, Color.PURPLE));
                    singleFields.add(new Wall(i, 7));
                    singleFields.add(new Wall(i, 8));
                    singleFields.add(new Wall(i, 9));
                    singleFields.add(new Wall(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 3) {
                    singleFields.add(j == 2 ? new Field(i, j) : new Tree(i, j, Color.RED));
                } else if (i == 4) {
                    singleFields.add(new Field(i, 1));
                    singleFields.add(new Field(i, 2));
                    singleFields.add(new Field(i, 3));
                    singleFields.add(new Field(i, 4));
                    singleFields.add(new Tree(i, 5, Color.RED));
                    singleFields.add(new Field(i, 6));
                    singleFields.add(new Wall(i, 7));
                    singleFields.add(new Tree(i, 8, Color.BLUE));
                    singleFields.add(new Tree(i, 9, Color.WHITE));
                    singleFields.add(new Tree(i, 10, Color.BLUE));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 5) {
                    singleFields.add(new Field(i, 1));
                    singleFields.add(new Lumberaxe(i, 2, Color.RED));
                    singleFields.add(new Field(i, 3));
                    singleFields.add(new Field(i, 4));
                    singleFields.add(new Wall(i, 5));
                    singleFields.add(new Field(i, 6));
                    singleFields.add(new Wall(i, 7));
                    singleFields.add(new Tree(i, 8, Color.WHITE));
                    singleFields.add(new Field(i, 9));
                    singleFields.add(new Field(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 6) {
                    singleFields.add(new Field(i, 1));
                    singleFields.add(new Lumberaxe(i, 2, Color.YELLOW));
                    singleFields.add(new Field(i, 3));
                    singleFields.add(new Field(i, 4));
                    singleFields.add(new Wall(i, 5));
                    singleFields.add(new Field(i, 6));
                    singleFields.add(new Tree(i, 7, Color.YELLOW));
                    singleFields.add(new Tree(i, 8, Color.RED));
                    singleFields.add(new Field(i, 9));
                    singleFields.add(new Field(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 7) {
                    singleFields.add((j == 5 || j == 6 || j == 7) ? new Wall(i, j) : new Field(i, j));
                } else if (i == 8) {
                    singleFields.add(new Tree(i, 1, Color.WHITE));
                    singleFields.add(new Tree(i, 2, Color.WHITE));
                    singleFields.add(new Tree(i, 3, Color.YELLOW));
                    singleFields.add(new Tree(i, 4, Color.BLUE));
                    singleFields.add(new Tree(i, 5, Color.WHITE));
                    singleFields.add(new Tree(i, 6, Color.RED));
                    singleFields.add(new Wall(i, 7));
                    singleFields.add(new Field(i, 8));
                    singleFields.add(new Wall(i, 9));
                    singleFields.add(new Wall(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 9) {
                    singleFields.add(new Tree(i, 1, Color.WHITE));
                    singleFields.add(new Tree(i, 2, Color.BLUE));
                    singleFields.add(new Tree(i, 3, Color.YELLOW));
                    singleFields.add(new Tree(i, 4, Color.BLUE));
                    singleFields.add(new Tree(i, 5, Color.WHITE));
                    singleFields.add(new Tree(i, 6, Color.YELLOW));
                    singleFields.add(new Field(i, 7));
                    singleFields.add(new Field(i, 8));
                    singleFields.add(new Field(i, 9));
                    singleFields.add(new Field(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i == 10) {
                    singleFields.add(new Tree(i, 1, Color.BLUE));
                    singleFields.add(new Tree(i, 2, Color.WHITE));
                    singleFields.add(new Lumberaxe(i, 3, Color.RED));
                    singleFields.add(new Tree(i, 4, Color.BLACK));
                    singleFields.add(new Tree(i, 5, Color.BLACK));
                    singleFields.add(new Field(i, 6));
                    singleFields.add(new Field(i, 7));
                    singleFields.add(new Field(i, 8));
                    singleFields.add(new Field(i, 9));
                    singleFields.add(new Finish(i, 10));
                    singleFields.add(new Wall(i, 11));
                    break;
                }
            }

            fields.add(singleFields);
        }

        return fields;
    }
}
