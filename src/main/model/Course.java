package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents a course in school with the course name and the rubric of the specific course and the grade so far
public class Course implements Writable {
    private String name; // name of the course
    private Rubric rubric; // rubric for the course
    private ArrayList<WorkCompleted> completedWork; // list of completed work (quizzes, exams, projects, etc)
    private double grade; // the grade for the course so far, in %
    double numerator = 0;
    ArrayList<WorkCompleted> grades1 = new ArrayList<>();


    // creates new course with the course name, the corresponding rubric, and list of completed work for that course
    public Course(String courseName, Rubric courseRubric) {
        this.name = courseName;
        this.rubric = courseRubric;
        this.completedWork = new ArrayList<>();
        this.grade = 0;
    }

    // getters + setters:

    public String getName() {
        return name;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public ArrayList<WorkCompleted> getCompletedWork() {
        return completedWork;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    //

    // MODIFIES: this
    // EFFECTS: adds new completed assignment to the course
    public void addCompletedWork(WorkCompleted work) {
        completedWork.add(work);
    }

    // MODIFIES: this
    // EFFECTS: removes completed assignment from the course
    public void removeCompletedWork(WorkCompleted work) {
        completedWork.remove(work);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grades from a list of work completed
    public void calculateGrade(ArrayList<WorkCompleted> grades, String capitalWork, String work) {
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains(capitalWork)) || (grades.get(k).getName().contains(work))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }

    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all quizzes, scaled by rubric
    public double calculateQuizGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Quiz", "quiz");
        return (((numerator / grades1.size()) * rubric.getQuizValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all assignments, scaled by rubric
    public double calculateAssignmentGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Assign", "assign");
        return (((numerator / grades1.size()) * rubric.getAssignmentValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all midterms, scaled by rubric
    public double calculateMidtermGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Midterm", "midterm");
        return (((numerator / grades1.size()) * rubric.getMidtermValue()) / 100);
    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all projects, scaled by rubric
    public double calculateProjectGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Project", "project");
        return (((numerator / grades1.size()) * rubric.getProjectValue()) / 100);
    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all participation, scaled by rubric
    public double calculateParticipationGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Partici", "partici");
        return (((numerator / grades1.size()) * rubric.getParticipationValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all final exams, scaled by rubric
    public double calculateFinalExamGrade(ArrayList<WorkCompleted> grades) {
        calculateGrade(grades, "Final", "final");
        return (((numerator / grades1.size()) * rubric.getFinalExamValue()) / 100);
    }


    // REQUIRES: every submethod needs to have a value, otherwise throws NaN error
    // EFFECTS: calculates overall grade of course
    public double calculateGradeFinal(ArrayList<WorkCompleted> grades) {
        double finalExamGrade = calculateFinalExamGrade(grades);
        clearNumeratorAndGrades();
        double participationGrade = calculateParticipationGrade(grades);
        clearNumeratorAndGrades();
        double projectGrade = calculateProjectGrade(grades);
        clearNumeratorAndGrades();
        double midtermGrade = calculateMidtermGrade(grades);
        clearNumeratorAndGrades();
        double assignmentGrade = calculateAssignmentGrade(grades);
        clearNumeratorAndGrades();
        double quizGrade = calculateQuizGrade(grades);
        clearNumeratorAndGrades();
        this.grade = finalExamGrade + participationGrade + projectGrade + midtermGrade + assignmentGrade + quizGrade;
        return this.grade;
    }

    // EFFECTS: resets numerator to 0 and clears the list of grades1
    public void clearNumeratorAndGrades() {
        numerator = 0;
        grades1.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Double.compare(course.grade, grade) == 0 && Double.compare(course.numerator, numerator) == 0
                && name.equals(course.name) && rubric.equals(course.rubric)
                && completedWork.equals(course.completedWork) && Objects.equals(grades1, course.grades1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rubric, completedWork, grade, numerator, grades1);
    }

    // EFFECTS: converts Course to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rubric", rubricToJson());
        json.put("grade", grade);
        json.put("completed work", completedWorkToJson());
        return json;
    }

    // EFFECTS: returns things in this completed work as a JSON array
    private JSONArray completedWorkToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkCompleted t : completedWork) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns integers in rubric as JSON
    private JSONObject rubricToJson() {
        JSONObject jsonRubric = new JSONObject();
        jsonRubric.put("quizzes", rubric.getQuizValue());
        jsonRubric.put("assignments", rubric.getAssignmentValue());
        jsonRubric.put("project", rubric.getProjectValue());
        jsonRubric.put("midterm", rubric.getMidtermValue());
        jsonRubric.put("participation", rubric.getParticipationValue());
        jsonRubric.put("final exam", rubric.getFinalExamValue());
        return jsonRubric;

    }

    @Override
    public String toString() {
        return name;
    }

}
