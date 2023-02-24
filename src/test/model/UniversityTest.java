package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniversityTest {
    private University testUniversity;


    @Test
    public void testConstructor() {
        testUniversity = new University("UBC", "Science", 94);
        assertEquals("UBC", testUniversity.getName());
        assertEquals("Science", testUniversity.getProgram());
        assertEquals(94, testUniversity.getAdmissionAverage());
    }

}
