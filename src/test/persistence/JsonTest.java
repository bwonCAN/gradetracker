package persistence;

import model.Course;
import model.CourseList;
import model.Rubric;
import model.WorkCompleted;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JSON Serialization Demo code referenced
public class JsonTest {

    protected void checkWorkCompleted(String name, double grade, WorkCompleted workCompleted) {
        assertEquals(name, workCompleted.getName());
        assertEquals(grade, workCompleted.getGrade());
    }

    protected void checkCourse(String name, Rubric rubric, ArrayList<WorkCompleted> workCompleted, Course course) {
        assertEquals(name, course.getName());
        assertEquals(rubric, course.getRubric());
        assertEquals(workCompleted, course.getCompletedWork());
    }

    protected void checkCourseList(String name, ArrayList<Course> courses, CourseList courseList) {
        assertEquals(name, courseList.getName());
        assertEquals(courses.size(), courseList.getCourses().size());
        assertEquals(courses, courseList.getCourses());
    }


}
