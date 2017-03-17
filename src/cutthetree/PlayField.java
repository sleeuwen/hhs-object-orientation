package cutthetree;


import java.util.ArrayList;

/**
 * Created by The lion kings on 17-3-2017.
 */
public class PlayField {
    private int heigth,width;
    private ArrayList<ArrayList<Field>> fields = new ArrayList<>();

    public PlayField(){

    }
    public PlayField(int heigth, int width){
        this.heigth = heigth;
        this.width = width;
    }


}
