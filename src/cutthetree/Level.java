package cutthetree;

import java.util.ArrayList;

/**
 * Created by Koen van Zeijl on 17-3-2017.
 */
public class Level {
    public enum Type{random,tutorial,easy,medium,hard}

    static ArrayList<ArrayList<Field>> generateLevel(Type type, int height, int width){
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

    static private ArrayList<ArrayList<Field>> getTutorialField() {
        ArrayList<ArrayList<Field>> fields = new ArrayList<>();
        for(int i=0;i<12;i++){
            ArrayList<Field> singleFields = new ArrayList<>();
            for (int j = 0; j<12;j++){
                if (j == 0 || j == 11) {
                    singleFields.add(new Wall(i, 0));
                    continue;
                }
                if (i == 0 || i==11) {
                    singleFields.add(new Wall(i,j));
                }else if(i==1) {
                    singleFields.add(j==9?new Lumberaxe(i,j,2):new Field(i,j));
                } else if (i==2){
                    singleFields.add(new Wall(i,1));
                    singleFields.add(new Field(i,2));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Wall(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Tree(i,6,2));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Wall(i,8));
                    singleFields.add(new Wall(i,9));
                    singleFields.add(new Wall(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                }
                else if (i==3){
                    singleFields.add(j==2? new Field(i,j):new Tree(i,j,1));
                } else if(i==4){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Field(i,2));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Tree(i,5,1));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Tree(i,8,4));
                    singleFields.add(new Tree(i,9,5));
                    singleFields.add(new Tree(i,10,4));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==5){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Lumberaxe(i,2,1));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Tree(i,8,5));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                } else if(i==6){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Lumberaxe(i,2,3));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Tree(i,7,3));
                    singleFields.add(new Tree(i,8,1));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                } else if(i==7){
                    singleFields.add((j==5||j==6||j==7)? new Wall(i,j):new Field(i,j));
                } else if(i==8){
                    singleFields.add(new Tree(i,1,5));
                    singleFields.add(new Tree(i,2,5));
                    singleFields.add(new Tree(i,3,3));
                    singleFields.add(new Tree(i,4,4));
                    singleFields.add(new Tree(i,5,5));
                    singleFields.add(new Tree(i,6,1));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Field(i,8));
                    singleFields.add(new Wall(i,9));
                    singleFields.add(new Wall(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==9){
                    singleFields.add(new Tree(i,1,5));
                    singleFields.add(new Tree(i,2,4));
                    singleFields.add(new Tree(i,3,3));
                    singleFields.add(new Tree(i,4,4));
                    singleFields.add(new Tree(i,5,5));
                    singleFields.add(new Tree(i,6,3));
                    singleFields.add(new Field(i,7));
                    singleFields.add(new Field(i,8));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i==10){
                    singleFields.add(new Tree(i,1,4));
                    singleFields.add(new Tree(i,2,5));
                    singleFields.add(new Lumberaxe(i,3,1));
                    singleFields.add(new Tree(i,4,6));
                    singleFields.add(new Tree(i,5,6));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Field(i,7));
                    singleFields.add(new Field(i,8));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Finish(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                }
            }
            fields.add(singleFields);
        }
        return fields;
    }
}
