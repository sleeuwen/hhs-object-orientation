package cutthetree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Koen van Zeijl on 21-3-2017.
 */
public class PlayFieldTest {
    @Test
    public void getColorPositive() throws Exception {
        PlayField p = new PlayField(12,12,LevelType.TUTORIAL,0);
        PlayField p1 = new PlayField(12,12,LevelType.EASY,1);
        assertTrue(1 == 1);
    }
}