package cutthetree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {
    private Wall wall;

    @Before
    public void setUp() throws Exception {
        wall = new Wall(0, 0);
    }

    @Test
    public void wallIsSolid() throws Exception {
        assertTrue(wall.isSolid());
    }
}
