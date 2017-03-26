package cutthetree;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    public void doesntMoveWhileAnimating() throws Exception {
        Image image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        // Start moving down
        assertTrue(player1.move(0, 1));

        // Animation takes 9 frames
        for (int i = 0; i < 9; i++) {
            assertFalse(player1.move(0, 1));

            player1.paint(g);
        }

        // Can move after animation
        assertTrue(player1.move(0, 1));
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
