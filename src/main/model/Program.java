package model;

import java.util.ArrayList;

//represents the top hierarchy of the program
public class Program {
    private ArrayList<CourseList> courseLists;

    // EFFECTS: creates program
    public Program() {
        this.courseLists = new ArrayList<>();
    }

    //GETTERS:
    public ArrayList<CourseList> getCourseLists() {
        return courseLists;
    }

    // MODIFIES: this
    // EFFECTS: adds new course list
    public void addCourseList(CourseList courselist) {
        courseLists.add(courselist);
    }

    // MODIFIES: this
    // EFFECTS: adds new course list
    public void removeCourseList(CourseList courselist) {
        courseLists.remove(courselist);
    }

}
