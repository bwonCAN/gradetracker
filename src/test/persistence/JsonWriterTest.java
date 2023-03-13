package persistence;

import model.Course;
import model.CourseList;
import model.Program;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// JSON Serialization Demo code referenced
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Program wr = new Program();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProgram() {
        try {
            Program wr = new Program();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProgram.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProgram.json");
            wr = reader.read();
            assertEquals(0, wr.getCourseLists().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralProgram() {
        try {
            Program wr = new Program ();
            wr.addCourseList(new CourseList("FALL 2022"));
            wr.addCourseList(new CourseList("WINTER 2023"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralProgram.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralProgram.json");
            wr = reader.read();
            ArrayList<CourseList> courseList = wr.getCourseLists();
            assertEquals(2, courseList.size());
            checkCourseList("FALL 2022", new ArrayList<>(), courseList.get(0));
            checkCourseList("WINTER 2023", new ArrayList<>(), courseList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
