package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkCompleted that = (WorkCompleted) o;
        return Double.compare(that.grade, grade) == 0 && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, grade);
    }

    // EFFECTS: converts WorkCompleted to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("grade", grade);
        return json;
    }

    @Override
    public String toString() {
        return name;
    }

}
