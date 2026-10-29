import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import Activity0.ArrayOperations;
import Activity2.Maze;
import Activity2.MazeSolver;
import Activity2.MazeSpaceObject;
import AbstractDataTypes.LinkedLists.LinkedList;
import AbstractDataTypes.Stacks.HtmlValidator;

public class RegressionTest {
    @Test
    public void arrayEqualityChecksLengthsAndValues() {
        assertFalse(ArrayOperations.areArraysEqual(new int[]{1}, new int[]{1, 2}));
        assertFalse(ArrayOperations.areArraysEqual(new int[]{1, 2}, new int[]{1}));
        assertFalse(ArrayOperations.areArraysEqual(new int[]{1}, new int[]{2}));
        assertTrue(ArrayOperations.areArraysEqual(new int[]{1, 2}, new int[]{1, 2}));
        assertTrue(ArrayOperations.areArraysEqual(new int[0], new int[0]));
        assertTrue(ArrayOperations.areArraysEqual(null, null));
        assertFalse(ArrayOperations.areArraysEqual(null, new int[0]));
    }

    @Test
    public void duplicateRemovalPreservesAllIntegerValuesAndOrder() {
        int[] input = {-999999991, 2, -999999991, 0, Integer.MIN_VALUE, 2, Integer.MAX_VALUE};
        int[] original = input.clone();
        assertArrayEquals(new int[]{-999999991, 2, 0, Integer.MIN_VALUE, Integer.MAX_VALUE},
                ArrayOperations.removeDuplicatesFromArray(input));
        assertArrayEquals(original, input);
        assertArrayEquals(new int[0], ArrayOperations.removeDuplicatesFromArray(new int[0]));
        assertArrayEquals(new int[]{7}, ArrayOperations.removeDuplicatesFromArray(new int[]{7, 7, 7}));
    }

    @Test
    public void recursionHandlesZeroAndPositiveInputs() {
        assertEquals(1, RecursionDemo.fact(0));
        assertEquals(120, RecursionDemo.fact(5));
        assertEquals(0, RecursionDemo.sum(0));
        assertEquals(15, RecursionDemo.sum(5));
        assertEquals("0", RecursionDemo.bin(0));
        assertEquals("1", RecursionDemo.bin(1));
        assertEquals("10001001", RecursionDemo.bin(137));
    }

    @Test
    public void recursionRejectsNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> RecursionDemo.fact(-1));
        assertThrows(IllegalArgumentException.class, () -> RecursionDemo.sum(-1));
        assertThrows(IllegalArgumentException.class, () -> RecursionDemo.bin(-1));
        assertThrows(IllegalArgumentException.class, () -> RecursionDemo.rev(-1));
    }

    @Test
    public void listSearchSupportsObjectsAndNull() {
        LinkedList<Object> list = new LinkedList<>();
        Object value = new Object();
        list.addBack(value);
        list.addBack(null);
        assertTrue(list.contains(value));
        assertEquals(0, list.indexOf(value));
        assertTrue(list.contains(null));
        assertEquals(1, list.indexOf(null));
        assertFalse(list.contains(new Object()));
        assertEquals(-1, list.indexOf(new Object()));
    }

    @Test
    public void listSearchUsesEqualityRatherThanOrdering() {
        LinkedList<StringBuilder> list = new LinkedList<>();
        StringBuilder value = new StringBuilder("same");
        list.addBack(value);
        assertTrue(list.contains(value));
        assertFalse(list.contains(new StringBuilder("same")));
    }

    @Test
    public void htmlAcceptsAttributesVoidTagsAndComments() {
        assertTrue(HtmlValidator.isValid("<p class=\"x\">text</p>"));
        assertTrue(HtmlValidator.isValid("<p>text<br>text<img src='x'><input disabled></p>"));
        assertTrue(HtmlValidator.isValid("<!DOCTYPE html><!-- <bad> --><DIV title='a > b'><span>x</span><hr/></DIV>"));
        assertTrue(HtmlValidator.isValid("<custom-element data-x=\"y\"/>"));
        String nested = "<div>".repeat(100) + "</div>".repeat(100);
        assertTrue(HtmlValidator.isValid(nested));
    }

    @Test
    public void htmlRejectsBrokenNestingAndIncompleteTags() {
        assertFalse(HtmlValidator.isValid("<html><head></html></head>"));
        assertFalse(HtmlValidator.isValid("<p>"));
        assertFalse(HtmlValidator.isValid("<p></p"));
        assertFalse(HtmlValidator.isValid("<p title='unfinished>text</p>"));
        assertFalse(HtmlValidator.isValid("<!-- unfinished"));
        assertFalse(HtmlValidator.isValid("</br>"));
        assertFalse(HtmlValidator.isValid(null));
    }

    private Maze mazeWithFinish(final int finishRow, final int finishColumn) {
        Maze maze = new Maze(5, 5) {
            @Override public MazeSpaceObject getStart() { return getCell(1, 1); }
            @Override public MazeSpaceObject getFinish() { return getCell(finishRow, finishColumn); }
        };
        for (int row = 0; row < maze.getRows(); row++) {
            for (int column = 0; column < maze.getColumns(); column++) { maze.getCell(row, column).setWall(); }
        }
        maze.getStart().setStartSpot();
        maze.getFinish().setFinishSpot();
        return maze;
    }

    private String solveBFS(Maze maze) {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(output)) {
            System.setOut(capture);
            new MazeSolver().solveBFS(maze);
        } finally {
            System.setOut(original);
        }
        return output.toString();
    }

    @Test
    public void bfsReportsAnUnreachableFinish() {
        Maze maze = mazeWithFinish(5, 5);
        assertTrue(solveBFS(maze).contains("No path was found to reach the finish!"));
        assertFalse(maze.getFinish().hasVisited());
    }

    @Test
    public void bfsStillFindsAPathAndResetsForReuse() {
        Maze maze = mazeWithFinish(1, 2);
        for (int attempt = 0; attempt < 2; attempt++) {
            String output = solveBFS(maze);
            assertTrue(output.contains("BFS RESULT"));
            assertFalse(output.contains("No path was found"));
            assertSame(maze.getStart(), maze.getFinish().getPrevious());
        }
    }
}