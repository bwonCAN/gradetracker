package model;

import java.util.ArrayList;

// represents a university name along with their popular programs and average grade for admission
public class University {
    private String name;
    private String program;
    private int admissionAverage;

    // EFFECTS: creates a university with their name, specific program, and average grade needed for admission
    public University(String name, String program, int admissionAverage) {

        this.name = name;
        this.program = program;
        this.admissionAverage = admissionAverage;
    }

    // getters

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public int getAdmissionAverage() {
        return admissionAverage;
    }

}

