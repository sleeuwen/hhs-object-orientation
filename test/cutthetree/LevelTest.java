package cutthetree;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LevelTest {
    private static final String LEVEL = "WWWWWWWWWWWW\n" +
            "W          W\n" +
            "W123456    W\n" +
            "Wabcdef    W\n" +
            "W          W\n" +
            "W          W\n" +
            "W          W\n" +
            "W          W\n" +
            "W          W\n" +
            "W          W\n" +
            "W         FW\n" +
            "WWWWWWWWWWWW";

    private Method loadLevel;

    @Before
    public void setUp() throws Exception {
        loadLevel = Level.class.getDeclaredMethod("loadLevel", InputStream.class);
        loadLevel.setAccessible(true);
    }

    @Test
    public void generateTutorialLevel() throws Exception {
        ArrayList<ArrayList<Field>> fields = Level.generateLevel(LevelType.TUTORIAL, 12, 12, 0);

        assertNotNull(fields);
    }

    @Test
    public void generateLevel() throws Exception {
        ArrayList<ArrayList<Field>> fields = Level.generateLevel(LevelType.EASY, 12, 12, 0);

        assertNotNull(fields);
    }

    @Test
    public void loadLevelSimple() throws Exception {
        InputStream is = new ByteArrayInputStream(LEVEL.getBytes());
        ArrayList<ArrayList<Field>> fields = (ArrayList<ArrayList<Field>>) loadLevel.invoke(null, is);

        assertTrue("W is a Wall Object", fields.get(0).get(0) instanceof Wall);
        assertTrue("F is a Finish Object", fields.get(10).get(10) instanceof Finish);
        assertTrue("<empty> is a Field object", fields.get(1).get(1).getClass() == Field.class);
    }

    @Test
    public void loadLevelLumberaxe() throws Exception {
        InputStream is = new ByteArrayInputStream(LEVEL.getBytes());
        ArrayList<ArrayList<Field>> fields = (ArrayList<ArrayList<Field>>) loadLevel.invoke(null, is);

        for (int i = 1; i <= 6; i++) {
            assertTrue(i + " is a Lumberaxe object", fields.get(i).get(2) instanceof Lumberaxe);
        }

        assertTrue("1 is color RED", Color.RED == ((Lumberaxe) fields.get(1).get(2)).getColor());
        assertTrue("2 is color PURPLE", Color.PURPLE == ((Lumberaxe) fields.get(2).get(2)).getColor());
        assertTrue("3 is color YELLOW", Color.YELLOW == ((Lumberaxe) fields.get(3).get(2)).getColor());
        assertTrue("4 is color BLUE", Color.BLUE == ((Lumberaxe) fields.get(4).get(2)).getColor());
        assertTrue("5 is color WHITE", Color.WHITE == ((Lumberaxe) fields.get(5).get(2)).getColor());
        assertTrue("6 is color BLACK", Color.BLACK == ((Lumberaxe) fields.get(6).get(2)).getColor());
    }

    @Test
    public void loadLevelTree() throws Exception {
        InputStream is = new ByteArrayInputStream(LEVEL.getBytes());
        ArrayList<ArrayList<Field>> fields = (ArrayList<ArrayList<Field>>) loadLevel.invoke(null, is);

        for (int i = 1; i <= 6; i++) {
            assertTrue((char) ('a' + i - 1) + " is a Tree object", fields.get(i).get(3) instanceof Tree);
        }

        assertTrue("a is color RED", Color.RED == ((Tree) fields.get(1).get(3)).getColor());
        assertTrue("a is color PURPLE", Color.PURPLE == ((Tree) fields.get(2).get(3)).getColor());
        assertTrue("a is color YELLOW", Color.YELLOW == ((Tree) fields.get(3).get(3)).getColor());
        assertTrue("a is color BLUE", Color.BLUE == ((Tree) fields.get(4).get(3)).getColor());
        assertTrue("a is color WHITE", Color.WHITE == ((Tree) fields.get(5).get(3)).getColor());
        assertTrue("a is color BLACK", Color.BLACK == ((Tree) fields.get(6).get(3)).getColor());
    }
}
