package model;

import java.util.ArrayList;

// Represents a course in school with the course name and the rubric of the specific course and the grade so far
public class Course {
    private String name; // name of the course
    private Rubric rubric; // rubric for the course
    private ArrayList<String> completedWork; // list of completed work (quizzes, exams, projects, etc)

    // creates new course with the course name, the corresponding rubric, and list of completed work for that course
    public Course(String courseName, Rubric courseRubric) {
        this.name = courseName;
        this.rubric = courseRubric;
        completedWork = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new completed assignment to the course
    public void addCompletedWork(String name) {
        completedWork.add(name);
    }

    // MODIFIES: this
    // EFFECTS: removes completed assignment to the course
    public void removeCompletedWork(String name) {
        completedWork.remove(name);
    }

}
