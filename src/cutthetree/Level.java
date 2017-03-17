package cutthetree;

import java.util.ArrayList;

/**
 * Created by Koen van Zeijl on 17-3-2017.
 */
public class Level {
    public enum Type{random,tutorial,easy,medium,hard}

    public ArrayList<ArrayList<Field>> generateLevel(Type type, int height, int width){
        ArrayList<ArrayList<Field>> fields = new ArrayList<>();
        switch (type){
            case random:
                break;
            case tutorial:
                fields = getTutorialField();
                break;
            case easy:
                break;
            case medium:
                break;
            case hard:
                break;
        }
        return fields;
    }

    private ArrayList<ArrayList<Field>> getTutorialField() {
        ArrayList<ArrayList<Field>> fields = new ArrayList<>();
        for(int i=0;i<11;i++){
            ArrayList<Field> singleFields = new ArrayList<>();
            for (int j = 0; j<11;j++){
                if (j == 0) singleFields.add(new Wall());

                if (i == 0) {
                    singleFields.add(new Wall());
                }else if(i==1) {
                    singleFields.add(new Field());
                } else if (i==2){
                    singleFields.add(new Wall());
                    singleFields.add(new Field());
                    singleFields.add(new Field());
                    singleFields.add(new Wall());
                    singleFields.add(new Wall());
                    singleFields.add(new Tree());
                }
            }
            fields.add(singleFields);
        }
        return fields;
    }
}
