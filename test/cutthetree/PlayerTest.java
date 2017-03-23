package cutthetree;

import org.junit.Before;
import org.junit.Test;
import sun.print.DialogOwner;

import static org.junit.Assert.*;

/**
 * Created by The Lion Kings on 21-3-2017.
 */
public class PlayerTest {
    private Player player1;
    private Lumberaxe axe;

    @Before
    public void setUp() throws Exception {
        player1 = new Player(1, 1);
        axe = new Lumberaxe(2, 5, Color.BLACK);
    }

    @Test
    public void movePositive() throws Exception {
        player1.move(1, 1);

        assertTrue(player1.xPos == 2 && player1.yPos == 2);
    }

    @Test
    public void moveNegative() throws Exception {
        player1.move(1, 1);

        assertFalse(player1.xPos == 5 && player1.yPos == 7);
    }

    @Test
    public void initialDirectionDown() throws Exception {
        assertEquals(Direction.DOWN, player1.getDirection());
    }

    @Test
    public void changeDirectionPositive() throws Exception {
        player1.changeDirection(Direction.LEFT);

        assertEquals(Direction.LEFT, player1.getDirection());
    }

    @Test
    public void changeDirectionNegative() throws Exception {
        player1.changeDirection(Direction.LEFT);

        assertNotEquals(Direction.UP, player1.getDirection());
    }

    @Test
    public void initiallyHasNoAxe() throws Exception {
        assertNull(player1.getAxe());
    }

    @Test
    public void grabLumberaxePositive() throws Exception {
        player1.grabLumberaxe(axe);

        assertEquals(axe, player1.getAxe());
    }

    @Test
    public void grabLumberaxeNegative() throws Exception {
        player1.grabLumberaxe(axe);
        Lumberaxe wrong = new Lumberaxe(5, 5, Color.WHITE);

        assertNotEquals(wrong, player1.getAxe());
    }
}
