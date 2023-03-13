package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniversityTest {
    private University testUniversity;
    private String name;
    private String program;
    private int admissionAverage;


    @Test
    void testConstructor() {
        testUniversity = new University("UBC", "Science", 94);
        assertEquals("UBC", testUniversity.getName());
        assertEquals("Science", testUniversity.getProgram());
        assertEquals(94, testUniversity.getAdmissionAverage());
    }

    @Test
    void testJSon() {
        testUniversity = new University("UBC", "Science", 94);
        JSONObject json = testUniversity.toJson();
        assertEquals("UBC", json.getString("name"));
        assertEquals("Science", json.getString("program"));
        assertEquals(94, json.getInt("admission average"));

    }

}
