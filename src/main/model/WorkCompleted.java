package model;

// represents all the coursework of a student, eg: quizzes, projects, midterms, finals, participation, etc
public class WorkCompleted {
    private String name;
    private double grade;

    // creates new work completed with a given name and a grade
    public WorkCompleted(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // GETTERS:

    public double getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }
    //


}
