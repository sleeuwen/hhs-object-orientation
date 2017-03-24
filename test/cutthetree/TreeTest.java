package cutthetree;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * Created by The lion kings on 21-3-2017.
 */
public class TreeTest {
    Tree testTree1;
    Tree testTree2;
    Tree testTree3;
    Lumberaxe wrongAxe;
    Lumberaxe goodAxe;

    @Before
    public void setUp() throws Exception {
        testTree1 = new Tree(5, 5, Color.BLACK);
        testTree2 = new Tree(6, 4, Color.WHITE);
        testTree3 = testTree2;

        wrongAxe = new Lumberaxe(1, 1, Color.RED);
        goodAxe = new Lumberaxe(1, 1, Color.BLACK);
    }

    @Test
    public void getColorPositive() throws Exception {
        assertTrue(testTree1.getColor() == Color.BLACK);
    }

    @Test
    public void getColorNegative() throws Exception {
        assertFalse(testTree1.getColor() == Color.WHITE);
    }

    @Test
    public void cutPositive() throws Exception {
        assertTrue(testTree1.cut(goodAxe));
        assertTrue(testTree1.isBeingCut());
    }

    @Test
    public void cutNegative() throws Exception {
        assertFalse(testTree1.cut(null));
        assertFalse(testTree1.isBeingCut());

        assertFalse(testTree1.cut(wrongAxe));
        assertFalse(testTree1.isBeingCut());
    }

    @Test
    public void equalsPositive() throws Exception {
        assertEquals(testTree2, testTree3);
    }

    @Test
    public void equalsNegative() throws Exception {
        assertNotEquals(testTree1, testTree3);
    }

    @Test
    public void treeIsSolid() throws Exception {
        assertTrue(testTree1.isSolid());
    }

    @Test
    public void treeIsNotSolidAfterAnimation() throws Exception {
        Image image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        // Start cut animation
        testTree1.cut(goodAxe);

        // Animation takes 16 frames
        for (int i = 0; i < 16; i++) {
            assertTrue(testTree1.isSolid());

            testTree1.paint(g);
        }

        // Tree is no longer solid after animation so the player can walk through it
        assertFalse(testTree1.isSolid());
    }
}
