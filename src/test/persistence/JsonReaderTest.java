package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JSON Serialization Demo code referenced
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Program pr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyProgram() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProgram.json");
        try {
            Program wr = reader.read();
            assertEquals(0, wr.getCourseLists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralProgram() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProgram.json");
        try {
            Program wr = reader.read();
            ArrayList<CourseList> courseLists = wr.getCourseLists();
            assertEquals(2, courseLists.size());
            Course cpsc210 = new Course("CPSC 210", new Rubric(10, 10,
                    10, 10, 10, 50));
            cpsc210.addCompletedWork(new WorkCompleted("Quiz 1", 100));
            cpsc210.addCompletedWork(new WorkCompleted("Assignment 1", 100));
            cpsc210.addCompletedWork(new WorkCompleted("Project 1", 100));
            cpsc210.addCompletedWork(new WorkCompleted("Midterm 1", 100));
            cpsc210.addCompletedWork(new WorkCompleted("Participation 1", 100));
            cpsc210.addCompletedWork(new WorkCompleted("Final Exam", 100));
            ArrayList<Course> courses = new ArrayList();
            courses.add(cpsc210);
            checkCourseList("FALL 2022", courses, courseLists.get(0));

            Course cpsc110 = new Course("CPSC 110", new Rubric(10, 10,
                    10, 10, 10, 50));
            Course eosc113 = new Course("EOSC113", new Rubric(10, 10,
                    10, 10, 10, 50));
            cpsc110.addCompletedWork(new WorkCompleted("Assignment 1", 100));
            eosc113.addCompletedWork(new WorkCompleted("Quiz 1", 100));
            ArrayList<Course> courses1 = new ArrayList();
            courses1.add(cpsc110);
            courses1.add(eosc113);
            checkCourseList("Winter 2023", courses1, courseLists.get(1));


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourse() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProgram.json");
        try {
            Program wr = reader.read();
            List<CourseList> courseLists = wr.getCourseLists();
            List<Course> courses = courseLists.get(0).getCourses();
            Course cpsc210Data = courses.get(0);
            ArrayList<WorkCompleted> cpsc210Works = new ArrayList<>();
            cpsc210Works.add(new WorkCompleted("Quiz 1", 100));
            cpsc210Works.add(new WorkCompleted("Assignment 1", 100));
            cpsc210Works.add(new WorkCompleted("Project 1", 100));
            cpsc210Works.add(new WorkCompleted("Midterm 1", 100));
            cpsc210Works.add(new WorkCompleted("Participation 1", 100));
            cpsc210Works.add(new WorkCompleted("Final Exam", 100));
            cpsc210Data.getRubric();
            Rubric initRubric = new Rubric(10, 10, 10, 10,
                    10, 50);
            checkCourse("CPSC 210", initRubric, cpsc210Works, cpsc210Data);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkCompleted() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProgram.json");
        try {
            Program wr = reader.read();
            List<CourseList> courseLists = wr.getCourseLists();
            List<Course> courses = courseLists.get(0).getCourses();
            Course cpsc210Data = courses.get(0);
            checkWorkCompleted("Quiz 1", 100, cpsc210Data.getCompletedWork().get(0));
            checkWorkCompleted("Assignment 1", 100, cpsc210Data.getCompletedWork().get(1));
            checkWorkCompleted("Project 1", 100, cpsc210Data.getCompletedWork().get(2));
            checkWorkCompleted("Midterm 1", 100, cpsc210Data.getCompletedWork().get(3));
            checkWorkCompleted("Participation 1", 100, cpsc210Data.getCompletedWork().get(4));
            checkWorkCompleted("Final Exam", 100, cpsc210Data.getCompletedWork().get(5));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    protected void checkRubric() {
        Rubric rubric = new Rubric(10, 10, 10,
                10, 10, 50);
        Rubric rubric1 = new Rubric(10, 10, 10,
                10, 10, 50);
        assertEquals(rubric, rubric1);


    }
}
