package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkCompletedTest {
    private WorkCompleted testWorkCompleted;

    @BeforeEach
    void setup() {
        testWorkCompleted = new WorkCompleted("Quiz 1", 100);

    }

    @Test
    public void testConstructor() {
        assertEquals("Quiz 1", testWorkCompleted.getName());
        assertEquals(100, testWorkCompleted.getGrade());
    }

    @Test
    void testJSon() {
        JSONObject json = testWorkCompleted.toJson();
        assertEquals("Quiz 1", json.getString("name"));
        assertEquals(100 , json.getInt("grade"));

    }

    @Test
    void testHashCode() {
        WorkCompleted test2 = new WorkCompleted("Quiz 1", 100);
        assertEquals(testWorkCompleted.hashCode(), test2.hashCode());

    }

    @Test
    void testEquals() {
        assertTrue(testWorkCompleted.equals(new WorkCompleted("Quiz 1", 100)));
        assertTrue(testWorkCompleted.equals(testWorkCompleted));
        assertFalse(testWorkCompleted.equals(new WorkCompleted("Quiz 1", 90)));
        assertFalse(testWorkCompleted.equals(new WorkCompleted("Quiz 2", 100)));
        assertFalse(testWorkCompleted.equals(new WorkCompleted("Quiz 2", 90)));
        assertFalse(testWorkCompleted.equals(null));
        assertFalse(testWorkCompleted.equals(("Quiz 2")));

    }
}
