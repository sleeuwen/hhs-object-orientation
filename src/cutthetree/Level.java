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
                    singleFields.add(j==0?new Wall(i, 0):new Wall(i,11));
                    continue;
                }
                if (i == 0 || i==11) {
                    singleFields.add(new Wall(i,j));
                }else if(i==1) {
                    singleFields.add(j==9?new Lumberaxe(i,j,Color.Purple):new Field(i,j));
                } else if (i==2){
                    singleFields.add(new Wall(i,1));
                    singleFields.add(new Field(i,2));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Wall(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Tree(i,6,Color.Purple));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Wall(i,8));
                    singleFields.add(new Wall(i,9));
                    singleFields.add(new Wall(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                }
                else if (i==3){
                    singleFields.add(j==2? new Field(i,j):new Tree(i,j,Color.Red));
                } else if(i==4){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Field(i,2));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Tree(i,5,Color.Red));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Tree(i,8,Color.Blue));
                    singleFields.add(new Tree(i,9,Color.White));
                    singleFields.add(new Tree(i,10,Color.Blue));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==5){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Lumberaxe(i,2,Color.Red));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Tree(i,8,Color.White));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==6){
                    singleFields.add(new Field(i,1));
                    singleFields.add(new Lumberaxe(i,2,Color.Yellow));
                    singleFields.add(new Field(i,3));
                    singleFields.add(new Field(i,4));
                    singleFields.add(new Wall(i,5));
                    singleFields.add(new Field(i,6));
                    singleFields.add(new Tree(i,7,Color.Yellow));
                    singleFields.add(new Tree(i,8,Color.Red));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==7){
                    singleFields.add((j==5||j==6||j==7)? new Wall(i,j):new Field(i,j));
                } else if(i==8){
                    singleFields.add(new Tree(i,1,Color.White));
                    singleFields.add(new Tree(i,2,Color.White));
                    singleFields.add(new Tree(i,3,Color.Yellow));
                    singleFields.add(new Tree(i,4,Color.Blue));
                    singleFields.add(new Tree(i,5,Color.White));
                    singleFields.add(new Tree(i,6,Color.Red));
                    singleFields.add(new Wall(i,7));
                    singleFields.add(new Field(i,8));
                    singleFields.add(new Wall(i,9));
                    singleFields.add(new Wall(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if(i==9){
                    singleFields.add(new Tree(i,1,Color.White));
                    singleFields.add(new Tree(i,2,Color.Blue));
                    singleFields.add(new Tree(i,3,Color.Yellow));
                    singleFields.add(new Tree(i,4,Color.Blue));
                    singleFields.add(new Tree(i,5,Color.White));
                    singleFields.add(new Tree(i,6,Color.Yellow));
                    singleFields.add(new Field(i,7));
                    singleFields.add(new Field(i,8));
                    singleFields.add(new Field(i,9));
                    singleFields.add(new Field(i,10));
                    singleFields.add(new Wall(i, 11));
                    break;
                } else if (i==10){
                    singleFields.add(new Tree(i,1,Color.Blue));
                    singleFields.add(new Tree(i,2,Color.White));
                    singleFields.add(new Lumberaxe(i,3,Color.Red));
                    singleFields.add(new Tree(i,4,Color.Black));
                    singleFields.add(new Tree(i,5,Color.Black));
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
