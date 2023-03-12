package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads program from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Program read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProgram(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses program from JSON object and returns it
    private Program parseProgram(JSONObject jsonObject) {
        Program wr = new Program();
        addCourseLists(wr, jsonObject);
        return wr;
    }

    // PROCESSES COURSE LISTS(plural)
    // MODIFIES: wr
    // EFFECTS: parses course lists from JSON object and adds them to program
    private void addCourseLists(Program wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("course lists");
        for (Object json : jsonArray) {
            JSONObject nextCourseList = (JSONObject) json;
            addCourseList(wr, nextCourseList);
        }
    }

    // PROCESSES COURSE LIST / COURSES
    // MODIFIES: wr
    // EFFECTS: parses course list from JSON object and adds it to program
    private void addCourseList(Program wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        CourseList courseList = new CourseList(name);
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(courseList, nextCourse);
        }
        wr.addCourseList(courseList);
    }


    // PROCESSES COURSE / WORKS COMPLETED (may need to add grade)
    // MODIFIES: wr
    // EFFECTS: parses course from JSON object and adds it to program
    private void addCourse(CourseList courseList, JSONObject jsonObject) {
        Rubric initRubric = new Rubric(0,0,0,0,0,
                0);
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("completed work");
        Course course = new Course(name, initRubric);
        for (Object json : jsonArray) {
            JSONObject nextWorkCompleted = (JSONObject) json;
            addWorkCompleted(course, nextWorkCompleted);
        }
        courseList.addCourse(course);
    }

    // MODIFIES: course list
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addWorkCompleted(Course course, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double grade = jsonObject.getDouble("grade");
        WorkCompleted workCompleted = new WorkCompleted(name, grade);
        course.addCompletedWork(workCompleted);
    }

}
