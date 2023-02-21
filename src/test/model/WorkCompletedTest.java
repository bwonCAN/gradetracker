package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkCompletedTest {
    private WorkCompleted testWorkCompleted;

    @Test
    public void testConstructor() {
        testWorkCompleted = new WorkCompleted("Quiz 1", 100);
        assertEquals("Quiz 1", testWorkCompleted.getName());
        assertEquals(100, testWorkCompleted.getGrade());
    }
}
