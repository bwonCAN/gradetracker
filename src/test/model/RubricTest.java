package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RubricTest {
    private Rubric testRubric;


    @Test
    public void testConstructor() {
        testRubric = new Rubric(10, 10, 20, 30,
                5, 25);
        assertEquals(10, testRubric.getQuizValue());
        assertEquals(10, testRubric.getAssignmentValue());
        assertEquals(20, testRubric.getMidtermValue());
        assertEquals(30, testRubric.getProjectValue());
        assertEquals(5, testRubric.getParticipationValue());
        assertEquals(25, testRubric.getFinalExamValue());






    }
}
