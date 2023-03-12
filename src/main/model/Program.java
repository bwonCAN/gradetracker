package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//represents the top hierarchy of the program
public class Program implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course lists", courseListsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray courseListsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CourseList c : courseLists) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

}
