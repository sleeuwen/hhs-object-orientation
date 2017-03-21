package cutthetree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by The Lion Kings on 21-3-2017.
 */
public class LumberaxeTest {
    private Lumberaxe lumberaxe1;

    @Before
    public void setUp() throws Exception {
        lumberaxe1 = new Lumberaxe(0, 0, Color.RED);
    }

    @Test
    public void getColorPositive() throws Exception {
        assertTrue(lumberaxe1.getColor() == Color.RED);
    }

    @Test
    public void getColorNegative() throws Exception {
        assertFalse(lumberaxe1.getColor() == Color.BLACK);
    }
}
