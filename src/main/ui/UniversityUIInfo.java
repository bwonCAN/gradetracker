package ui;

import model.University;

import java.util.ArrayList;

public class UniversityUIInfo extends University {
    private String name;
    private String program;
    private int admissionAverage;

    public UniversityUIInfo(String name, String program, int admissionAverage) {
        super(name, program, admissionAverage);
    }

    // EFFECTS: returns list of UT Programs
    public ArrayList<University> utPrograms() {
        University utScience = new University("University of Toronto", "Science",
                100);
        University utBusiness = new University("University of Toronto", "Business",
                100);
        University utArts = new University("University of Toronto", "Arts", 100);
        University utEngineering = new University("University of Toronto",
                "Applied Science/Engineering", 100);
        University utKin = new University("University of Toronto", "Kinesiology",
                100);
        ArrayList<University> universityToronto = new ArrayList<>();
        universityToronto.add(utScience);
        universityToronto.add(utBusiness);
        universityToronto.add(utArts);
        universityToronto.add(utEngineering);
        universityToronto.add(utKin);
        return universityToronto;
    }

    // EFFECTS: returns list of UBC Programs
    public ArrayList<University> ubcPrograms() {
        University ubcScience = new University("University of British Columbia", "Science",
                100);
        University ubcBusiness = new University("University of British Columbia", "Business",
                100);
        University ubcArts = new University("University of British Columbia", "Arts", 100);
        University ubcEngineering = new University("University of British Columbia",
                "Applied Science/Engineering", 100);
        University ubcKin = new University("University of British Columbia", "Kinesiology",
                100);

        ArrayList<University> universityBC = new ArrayList<>();
        universityBC.add(ubcScience);
        universityBC.add(ubcBusiness);
        universityBC.add(ubcArts);
        universityBC.add(ubcEngineering);
        universityBC.add(ubcKin);
        return universityBC;
    }


    // EFFECTS: returns list of McGill Programs
    public ArrayList<University> mcGillPrograms() {
        University mcGillScience = new University("McGill University", "Science",
                100);
        University mcGillBusiness = new University("McGill University", "Business",
                100);
        University mcGillArts = new University("McGill University", "Arts", 100);
        University mcGillEngineering = new University("McGill University",
                "Applied Science/Engineering", 100);
        University mcGillKin = new University("McGill University", "Kinesiology",
                100);

        ArrayList<University> mcGill = new ArrayList<>();
        mcGill.add(mcGillScience);
        mcGill.add(mcGillBusiness);
        mcGill.add(mcGillArts);
        mcGill.add(mcGillEngineering);
        mcGill.add(mcGillKin);
        return mcGill;
    }

    // EFFECTS: returns list of UT Programs
    public ArrayList<University> mcMasterPrograms() {
        University mcMasterScience = new University("McMaster University", "Science",
                100);
        University mcMasterBusiness = new University("McMaster University", "Business",
                100);
        University mcMasterArts = new University("McMaster University", "Arts", 100);
        University mcMasterEngineering = new University("McMaster University",
                "Applied Science/Engineering", 100);
        University mcMasterKin = new University("McMaster University", "Kinesiology",
                100);

        ArrayList<University> mcMaster = new ArrayList<>();
        mcMaster.add(mcMasterScience);
        mcMaster.add(mcMasterBusiness);
        mcMaster.add(mcMasterArts);
        mcMaster.add(mcMasterEngineering);
        mcMaster.add(mcMasterKin);
        return mcMaster;
    }

    public ArrayList<University> montrealPrograms() {
        University montrealScience = new University("University of Montreal", "Science",
                100);
        University montrealBusiness = new University("University of Montreal", "Business",
                100);
        University montrealArts = new University("University of Montreal", "Arts", 100);
        University montrealEngineering = new University("University of Montreal",
                "Applied Science/Engineering", 100);
        University montrealKin = new University("University of Montreal", "Kinesiology",
                100);

        ArrayList<University> montreal = new ArrayList<>();
        montreal.add(montrealScience);
        montreal.add(montrealBusiness);
        montreal.add(montrealArts);
        montreal.add(montrealEngineering);
        montreal.add(montrealKin);

        return montreal;
    }
}