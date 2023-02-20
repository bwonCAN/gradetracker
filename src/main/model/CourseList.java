package model;

import java.util.ArrayList;

// a list of the classes a student is currently taking this term
public class CourseList {
    private String name; // a name for the current term
    private ArrayList<Course> courses; // a list of courses for this term

    // EFFECTS: creates new course list with a name for this name and a empty list of courses for this term
    public CourseList(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

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

    // EFFECTS: shows current courses
    public ArrayList<Course> showCourses() {
        return this.courses;
    }

    // MODIFIES: this
    // EFFECTS: erases all courses in current list
    public void eraseCourses() {
        this.courses.clear();
    }
}