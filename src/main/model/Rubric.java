package model;

import org.json.JSONObject;
import persistence.Writable;

// mark breakdown for a class
public class Rubric implements Writable {
    private int quizzes; // value of all quizzes in the course, 0 if not needed
    private int assignments; // value of all assignments in the course, 0 if not needed
    private int midterm; // value of midterm in the course, 0 if not needed
    private int project; // value of project in the course, 0 if not needed
    private int participation; // value of participation in the course, 0 if not needed
    private int finalExam; // value of participation in the course, 0 if not needed

    // REQUIRES: total summed value of the fields should equal 100
    // EFFECTS: creates new rubric with appropriate values
    public Rubric(int quizValue, int assignmentsValue, int midtermValue, int projectValue, int participationValue,
                  int finalExamValue) {
        this.quizzes = quizValue;
        this.assignments = assignmentsValue;
        this.midterm = midtermValue;
        this.project = projectValue;
        this.participation = participationValue;
        this.finalExam = finalExamValue;

    }
    // getters

    public int getQuizValue() {
        return quizzes;
    }

    public int getAssignmentValue() {
        return assignments;
    }

    public int getMidtermValue() {
        return midterm;
    }

    public int getProjectValue() {
        return project;
    }

    public int getParticipationValue() {
        return participation;
    }

    public int getFinalExamValue() {
        return finalExam;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("quizzes", quizzes);
        json.put("assignments", assignments);
        json.put("midterm", midterm);
        json.put("project", project);
        json.put("participation", participation);
        json.put("final exam", finalExam);

        return json;
    }

}
