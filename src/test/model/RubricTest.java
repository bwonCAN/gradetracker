package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RubricTest {
    private Rubric testRubric;


    @BeforeEach
    void setup() {
        testRubric = new Rubric(10, 10, 20, 30,
                5, 25);
        Rubric test2 = new Rubric(10, 10, 20, 30, 5,
                25);
    }

    @Test
    public void testConstructor() {
        assertEquals(10, testRubric.getQuizValue());
        assertEquals(10, testRubric.getAssignmentValue());
        assertEquals(20, testRubric.getMidtermValue());
        assertEquals(30, testRubric.getProjectValue());
        assertEquals(5, testRubric.getParticipationValue());
        assertEquals(25, testRubric.getFinalExamValue());

    }

    @Test
    void testJSon() {
        JSONObject json = testRubric.toJson();
        assertEquals(10, json.getInt("quizzes"));
        assertEquals(10, json.getInt("assignments"));
        assertEquals(20, json.getInt("midterm"));
        assertEquals(30, json.getInt("project"));
        assertEquals(5, json.getInt("participation"));
        assertEquals(25, json.getInt("final exam"));

    }

    @Test
    void testHashCode() {
        Rubric test2 = new Rubric(10, 10, 20, 30, 5,
                25);
        assertEquals(testRubric.hashCode(), test2.hashCode());

    }

    @Test
    void testEquals() {
        Rubric test2 = new Rubric(10, 10, 20, 30, 5,
                25);
        assertTrue(testRubric.equals(test2));
        assertFalse(testRubric.equals(new Rubric (0,0,0,
                0,0,0)));
        assertTrue(testRubric.equals(testRubric));
        assertFalse(testRubric.equals(null));
        assertFalse(testRubric.equals(("Quiz 2")));

    }
}
