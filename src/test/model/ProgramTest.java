package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramTest {
    CourseList courseList1;
    CourseList courseList2;
    Program testProgram;

    @BeforeEach
    public void setup() {
        courseList1 = new CourseList("Winter 2022");
        courseList2 = new CourseList("Fall 2022");
        testProgram = new Program();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testProgram.getCourseLists().size());

    }

    @Test
    public void testAddCourseList() {
        testProgram.addCourseList(courseList1);
        assertEquals(1, testProgram.getCourseLists().size());
        testProgram.addCourseList(courseList2);
        assertEquals(2, testProgram.getCourseLists().size());

    }

    @Test
    public void testRemoveCourseList() {
        testProgram.addCourseList(courseList1);
        testProgram.addCourseList(courseList2);
        testProgram.removeCourseList(courseList1);
        assertEquals(1, testProgram.getCourseLists().size());
        testProgram.removeCourseList(courseList2);
        assertEquals(0, testProgram.getCourseLists().size());

    }
}
