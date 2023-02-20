package model;

import model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
    private Rubric testRubric; // rubric for the course
    private Course testCourse;

    @BeforeEach
    public void setup() {
        testRubric = new Rubric(10, 10, 10, 10,
                10, 50);
        testCourse = new Course("CPSC 210", testRubric);
    }

    @Test
    public void testCalculateGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Quiz 1", 100));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 2", 50));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 3", 30));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 4", 20));
        ArrayList<WorkCompleted> quizGrades = testCourse.getCompletedWork();
        assertEquals(50, testCourse.calculateGrade(quizGrades));

    }

    @Test
    public void testCalculateQuizGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Quiz 1", 100));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 2", 50));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 3", 30));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 4", 20));
        ArrayList<WorkCompleted> quizGrades = testCourse.getCompletedWork();
        assertEquals(10, testRubric.getQuizValue());
        assertEquals(5, testCourse.calculateQuizGrade(quizGrades));

    }
}
