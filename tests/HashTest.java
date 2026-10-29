import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

public class HashTest {
    @Test
    public void addFindRemoveAndResize() {
        Hash<String, Integer> table = new Hash<>(1);
        assertTrue(table.add("Aa", 1));
        assertTrue(table.add("BB", 2));
        assertFalse(table.add("Aa", 3));
        assertEquals(2.0, table.loadFactor(), 0.0001);
        table.resize(7);
        assertEquals(Integer.valueOf(1), table.getValue("Aa"));
        assertEquals(Integer.valueOf(2), table.getValue("BB"));
        assertEquals(2.0 / 7, table.loadFactor(), 0.0001);
        assertFalse(table.remove("Aa", 3));
        assertTrue(table.remove("Aa", 1));
        assertNull(table.getValue("Aa"));
        assertEquals(1.0 / 7, table.loadFactor(), 0.0001);
        table.resize(1);
        assertEquals(Integer.valueOf(2), table.getValue("BB"));
    }

    @Test
    public void nullAndNegativeHashKeys() {
        Hash<Object, String> table = new Hash<>();
        Object key = new Object() { public int hashCode() { return Integer.MIN_VALUE; } };
        table.add(key, "value");
        assertTrue(table.add(null, null));
        assertFalse(table.add(null, "other"));
        table.resize(3);
        assertEquals("value", table.getValue(key));
        assertTrue(table.remove(null, null));
    }

    @Test
    public void iterationAndInvalidCapacity() {
        Hash<String, Integer> table = new Hash<>();
        assertFalse(table.iterator().hasNext());
        assertThrows(NoSuchElementException.class, () -> table.iterator().next());
        table.add("a", 1);
        table.add("b", 2);
        Set<String> keys = new HashSet<>();
        for (String key : table) { keys.add(key); }
        assertEquals(Set.of("a", "b"), keys);
        assertThrows(IllegalArgumentException.class, () -> new Hash<>(0));
        assertThrows(IllegalArgumentException.class, () -> table.resize(-1));
        assertEquals(Integer.valueOf(1), table.getValue("a"));
    }
}
