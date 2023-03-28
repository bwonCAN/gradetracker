package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// a list of the classes a student is currently taking this term
public class CourseList implements Writable {
    private String name; // a name for the current term
    private ArrayList<Course> courses; // a list of courses for this term

    // EFFECTS: creates new course list with a name for this name and a empty list of courses for this term
    public CourseList(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    // GETTERS:

    public String getName() {
        return name;
    }


    // EFFECTS: shows current courses
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    //

    // MODIFIES: this
    // EFFECTS: adds new course to the course list
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    // MODIFIES: this
    // EFFECTS: removes specified course in the course list
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    // MODIFIES: this
    // EFFECTS: erases all courses in current list
    public void eraseCourses() {
        this.courses.clear();
    }

    // EFFECTS: calculate grade of all courses in course list
    public double calculateCourseListGrade(CourseList courses) {
        double grade = 0;
        for (Course c : courses.getCourses()) {
            grade += c.getGrade();
        }
        return (grade / courses.getCourses().size());
    }

    // EFFECTS: converts Course List to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseList that = (CourseList) o;
        return name.equals(that.name) && courses.equals(that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, courses);
    }

    @Override
    public String toString() {
        return name;
    }
}
