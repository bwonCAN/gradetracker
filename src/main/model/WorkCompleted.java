package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represents all the coursework of a student, eg: quizzes, projects, midterms, finals, participation, etc
public class WorkCompleted implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("grade", grade);
        return json;
    }

}
