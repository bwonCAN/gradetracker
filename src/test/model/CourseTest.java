package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
    private Rubric testRubric;
    private Course testCourse;

    @BeforeEach
    public void setup() {
        testRubric = new Rubric(10, 10, 20, 30,
                5, 25);
        testCourse = new Course("CPSC 210", testRubric);
    }

    @Test
    public void testConstructor() {
        assertEquals("CPSC 210", testCourse.getName());
        assertEquals(testRubric, testCourse.getRubric());
        assertEquals(0, testCourse.getCompletedWork().size());
        assertEquals(0, testCourse.getGrade());
    }

    @Test
    public void testAddCompletedWork() {
        testCourse.addCompletedWork(new WorkCompleted("Quiz 1", 100));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 2", 50));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 3", 30));
        testCourse.addCompletedWork(new WorkCompleted("Quiz 4", 20));
        assertEquals(4, testCourse.getCompletedWork().size());
    }

    @Test
    public void testRemoveCompletedWork() {
        WorkCompleted work1 = new WorkCompleted("Quiz 1", 100);
        WorkCompleted work2 = new WorkCompleted("Quiz 2", 50);
        WorkCompleted work3 = new WorkCompleted("Quiz 3", 30);
        WorkCompleted work4 = new WorkCompleted("Quiz 4", 20);
        testCourse.addCompletedWork(work1);
        testCourse.addCompletedWork(work2);
        testCourse.addCompletedWork(work3);
        testCourse.addCompletedWork(work4);
        testCourse.removeCompletedWork(work1);
        testCourse.removeCompletedWork(work2);
        testCourse.removeCompletedWork(work4);
        assertEquals(1, testCourse.getCompletedWork().size());
        assertEquals(work3, testCourse.getCompletedWork().get(0));

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
        testCourse.addCompletedWork(new WorkCompleted("Assignment 1", 20));
        assertEquals(5, testCourse.calculateQuizGrade(quizGrades));
        testCourse.addCompletedWork(new WorkCompleted("Midterm 2", 20));
        assertEquals(5, testCourse.calculateQuizGrade(quizGrades));


    }

    @Test
    public void testCalculateAssignmentGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Assignment 1", 10));
        testCourse.addCompletedWork(new WorkCompleted("Assignment 2", 20));
        testCourse.addCompletedWork(new WorkCompleted("Assignment 3", 30));
        testCourse.addCompletedWork(new WorkCompleted("Assignment 4", 40));
        ArrayList<WorkCompleted> assignmentGrades = testCourse.getCompletedWork();
        assertEquals(2.5, testCourse.calculateAssignmentGrade(assignmentGrades));

    }

    @Test
    public void testCalculateMidtermGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Midterm 1", 10));
        testCourse.addCompletedWork(new WorkCompleted("Midterm 2", 20));
        testCourse.addCompletedWork(new WorkCompleted("Midterm 3", 30));
        testCourse.addCompletedWork(new WorkCompleted("Midterm 4", 40));
        ArrayList<WorkCompleted> midtermGrades = testCourse.getCompletedWork();
        assertEquals(5, testCourse.calculateMidtermGrade(midtermGrades));

    }

    @Test
    public void testProjectAssignmentGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Project 1", 80));
        testCourse.addCompletedWork(new WorkCompleted("Project 2", 90));
        ArrayList<WorkCompleted> projectGrades = testCourse.getCompletedWork();
        assertEquals(25.5, testCourse.calculateProjectGrade(projectGrades));
    }

    @Test
    public void testCalculateParticipationGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Participation", 90));
        ArrayList<WorkCompleted> participationGrade = testCourse.getCompletedWork();
        assertEquals(4.5, testCourse.calculateParticipationGrade(participationGrade));
    }

    @Test
    public void testFinalExamGrade() {
        testCourse.addCompletedWork(new WorkCompleted("Final Exam", 100));
        ArrayList<WorkCompleted> finalExamGrade = testCourse.getCompletedWork();
        assertEquals(25, testCourse.calculateFinalExamGrade(finalExamGrade));
    }

//    @Test
//    public void testConsecutiveCategories() {
//        testCourse.addCompletedWork(new WorkCompleted("Quiz 1", 100));
//        testCourse.addCompletedWork(new WorkCompleted("Assignment 1", 100));
//        assertEquals(20, testCourse.calculateGradeFinal(testCourse.getCompletedWork()));
//    }

    @Test
    public void testCalculateGradesFinal() {
        testCourse.addCompletedWork(new WorkCompleted("Quiz 1", 100));
        testCourse.addCompletedWork(new WorkCompleted("Assignment 1", 50));
        testCourse.addCompletedWork(new WorkCompleted("Midterm 1", 50));
        testCourse.addCompletedWork(new WorkCompleted("Project 1", 50));
        testCourse.addCompletedWork(new WorkCompleted("Participation", 100));
        testCourse.addCompletedWork(new WorkCompleted("Final Exam", 80));
        ArrayList<WorkCompleted> grade = testCourse.getCompletedWork();
        assertEquals(65, testCourse.calculateGradeFinal(grade));


    }
}

