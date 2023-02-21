package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseListTest {
    private Course testCourse1;
    private Course testCourse2;
    private Rubric testRubric;
    private CourseList testCourseList;

    @BeforeEach
    public void setup() {
        testRubric = new Rubric(10, 10, 20, 30,
                5, 25);
        testCourse1 = new Course("CPSC 210", testRubric);
        testCourse2 = new Course("ENGL 301", testRubric);
        testCourseList = new CourseList("Spring 2023");
    }

    @Test
    public void testConstructor() {
        assertEquals("Spring 2023", testCourseList.getName());
        assertEquals(0, testCourseList.getCourses().size());
    }

    @Test
    public void testAddCourse() {
        testCourseList.addCourse(testCourse1);
        assertEquals(testCourse1, testCourseList.getCourses().get(0));
        assertEquals(1, testCourseList.getCourses().size());
        testCourseList.addCourse(testCourse2);
        assertEquals(testCourse2, testCourseList.getCourses().get(1));
        assertEquals(2, testCourseList.getCourses().size());

    }

    @Test
    public void testRemoveCourse() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        testCourseList.removeCourse(testCourse1);
        assertEquals(1, testCourseList.getCourses().size());
        assertEquals(testCourse2, testCourseList.getCourses().get(0));
        testCourseList.removeCourse(testCourse2);
        assertEquals(0, testCourseList.getCourses().size());

    }

    @Test
    public void testEraseCourses() {
        testCourseList.addCourse(testCourse1);
        testCourseList.addCourse(testCourse2);
        testCourseList.eraseCourses();
        assertEquals(0, testCourseList.getCourses().size());

    }

}