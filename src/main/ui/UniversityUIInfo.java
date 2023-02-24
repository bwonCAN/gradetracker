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

    public ArrayList<University> albertaPrograms() {
        University albertaScience = new University("University of Alberta", "Science",
                100);
        University albertaBusiness = new University("University of Alberta", "Business",
                100);
        University albertaArts = new University("University of Alberta", "Arts", 100);
        University albertaEngineering = new University("University of Alberta",
                "Applied Science/Engineering", 100);
        University albertaKin = new University("University of Alberta", "Kinesiology",
                100);

        ArrayList<University> alberta = new ArrayList<>();
        alberta.add(albertaScience);
        alberta.add(albertaBusiness);
        alberta.add(albertaArts);
        alberta.add(albertaEngineering);
        alberta.add(albertaKin);

        return alberta;
    }

    public ArrayList<University> ottawaPrograms() {
        University ottawaScience = new University("University of Ottawa", "Science",
                100);
        University ottawaBusiness = new University("University of Ottawa", "Business",
                100);
        University ottawaArts = new University("University of Ottawa", "Arts", 100);
        University ottawaEngineering = new University("University of Ottawa",
                "Applied Science/Engineering", 100);
        University ottawaKin = new University("University of Ottawa", "Kinesiology",
                100);

        ArrayList<University> ottawa = new ArrayList<>();
        ottawa.add(ottawaScience);
        ottawa.add(ottawaBusiness);
        ottawa.add(ottawaArts);
        ottawa.add(ottawaEngineering);
        ottawa.add(ottawaKin);

        return ottawa;
    }

    public ArrayList<University> calgaryPrograms() {
        University calgaryScience = new University("University of Calgary", "Science",
                100);
        University calgaryBusiness = new University("University of Calgary", "Business",
                100);
        University calgaryArts = new University("University of Calgary", "Arts", 100);
        University calgaryEngineering = new University("University of Calgary",
                "Applied Science/Engineering", 100);
        University calgaryKin = new University("University of Calgary", "Kinesiology",
                100);

        ArrayList<University> calgary = new ArrayList<>();
        calgary.add(calgaryScience);
        calgary.add(calgaryBusiness);
        calgary.add(calgaryArts);
        calgary.add(calgaryEngineering);
        calgary.add(calgaryKin);

        return calgary;
    }

    public ArrayList<University> waterlooPrograms() {
        University uwScience = new University("University of Waterloo", "Science",
                100);
        University uwBusiness = new University("University of Waterloo", "Business",
                100);
        University uwArts = new University("University of Waterloo", "Arts", 100);
        University uwEngineering = new University("University of Waterloo",
                "Applied Science/Engineering", 100);
        University uwKin = new University("University of Waterloo", "Kinesiology",
                100);

        ArrayList<University> waterloo = new ArrayList<>();
        waterloo.add(uwScience);
        waterloo.add(uwBusiness);
        waterloo.add(uwArts);
        waterloo.add(uwEngineering);
        waterloo.add(uwKin);

        return waterloo;
    }

    public ArrayList<University> westernPrograms() {
        University westernScience = new University("Western University", "Science",
                100);
        University westernBusiness = new University("Western University", "Business",
                100);
        University westernArts = new University("Western University", "Arts", 100);
        University westernEngineering = new University("Western University",
                "Applied Science/Engineering", 100);
        University westernKin = new University("Western University", "Kinesiology",
                100);

        ArrayList<University> western = new ArrayList<>();
        western.add(westernScience);
        western.add(westernBusiness);
        western.add(westernArts);
        western.add(westernEngineering);
        western.add(westernKin);

        return western;
    }

    public ArrayList<University> lavalPrograms() {
        University lavalScience = new University("Universte Laval", "Science",
                100);
        University lavalBusiness = new University("Universte Laval", "Business",
                100);
        University lavalArts = new University("Universte Laval", "Arts", 100);
        University lavalEngineering = new University("Universte Laval",
                "Applied Science/Engineering", 100);
        University lavalKin = new University("Universte Laval", "Kinesiology",
                100);

        ArrayList<University> laval = new ArrayList<>();
        laval.add(lavalScience);
        laval.add(lavalBusiness);
        laval.add(lavalArts);
        laval.add(lavalEngineering);
        laval.add(lavalKin);

        return laval;
    }

    public ArrayList<University> queensPrograms() {
        University queensScience = new University("Queen's University", "Science",
                100);
        University queensBusiness = new University("Queen's University", "Business",
                100);
        University queensArts = new University("Queen's University", "Arts", 100);
        University queensEngineering = new University("Queen's University",
                "Applied Science/Engineering", 100);
        University queensKin = new University("Queen's University", "Kinesiology",
                100);

        ArrayList<University> queens = new ArrayList<>();
        queens.add(queensScience);
        queens.add(queensBusiness);
        queens.add(queensArts);
        queens.add(queensEngineering);
        queens.add(queensKin);

        return queens;
    }

    public ArrayList<University> sfuPrograms() {
        University sfuScience = new University("Simon Fraser University", "Science",
                100);
        University sfuBusiness = new University("Simon Fraser University", "Business",
                100);
        University sfuArts = new University("Simon Fraser University", "Arts", 100);
        University sfuEngineering = new University("Simon Fraser University",
                "Applied Science/Engineering", 100);
        University sfuKin = new University("Simon Fraser University", "Kinesiology",
                100);

        ArrayList<University> sfu = new ArrayList<>();
        sfu.add(sfuScience);
        sfu.add(sfuBusiness);
        sfu.add(sfuArts);
        sfu.add(sfuEngineering);
        sfu.add(sfuKin);

        return sfu;
    }

    public ArrayList<University> dalhousiePrograms() {
        University dalScience = new University("Dalhousie University", "Science",
                100);
        University dalBusiness = new University("Dalhousie University", "Business",
                100);
        University dalArts = new University("Dalhousie University", "Arts", 100);
        University dalEngineering = new University("Dalhousie University",
                "Applied Science/Engineering", 100);
        University dalKin = new University("Dalhousie University", "Kinesiology",
                100);

        ArrayList<University> dalhousie = new ArrayList<>();
        dalhousie.add(dalScience);
        dalhousie.add(dalBusiness);
        dalhousie.add(dalArts);
        dalhousie.add(dalEngineering);
        dalhousie.add(dalKin);

        return dalhousie;
    }

    public ArrayList<University> uvicPrograms() {
        University uvicScience = new University("University of Victoria", "Science",
                100);
        University uvicBusiness = new University("University of Victoria", "Business",
                100);
        University uvicArts = new University("University of Victoria", "Arts", 100);
        University uvicEngineering = new University("University of Victoria",
                "Applied Science/Engineering", 100);
        University uvicKin = new University("University of Victoria", "Kinesiology",
                100);

        ArrayList<University> uvic = new ArrayList<>();
        uvic.add(uvicScience);
        uvic.add(uvicBusiness);
        uvic.add(uvicArts);
        uvic.add(uvicEngineering);
        uvic.add(uvicKin);
        return uvic;
    }
}



//    //UofT:
//    University utScience = new University("University of Toronto", "Science",
//            100);
//    University utBusiness = new University("University of Toronto", "Business",
//            100);
//    University utArts = new University("University of Toronto", "Arts", 100);
//    University utEngineering = new University("University of Toronto",
//            "Applied Science/Engineering", 100);
//    University utKin = new University("University of Toronto", "Kinesiology",
//            100);
//
//    // UBC:
//    University ubcScience = new University("University of British Columbia", "Science",
//            100);
//    University ubcBusiness = new University("University of British Columbia", "Business",
//            100);
//    University ubcArts = new University("University of British Columbia", "Arts", 100);
//    University ubcEngineering = new University("University of British Columbia",
//            "Applied Science/Engineering", 100);
//    University ubcKin = new University("University of British Columbia", "Kinesiology",
//            100);
//
//    // McGill:
//    University mcGillScience = new University("McGill University", "Science",
//            100);
//    University mcGillBusiness = new University("McGill University", "Business",
//            100);
//    University mcGillArts = new University("McGill University", "Arts", 100);
//    University mcGillEngineering = new University("McGill University",
//            "Applied Science/Engineering", 100);
//    University mcGillKin = new University("McGill University", "Kinesiology",
//            100);
//
//    // McMaster:
//    University mcMasterScience = new University("McMaster University", "Science",
//            100);
//    University mcMasterBusiness = new University("McMaster University", "Business",
//            100);
//    University mcMasterArts = new University("McMaster University","Arts", 100);
//    University mcMasterEngineering = new University("McMaster University",
//            "Applied Science/Engineering", 100);
//    University mcMasterKin = new University("McMaster University", "Kinesiology",
//            100);
//
//    // Montreal:
//    University montrealScience = new University("University of Montreal", "Science",
//            100);
//    University montrealBusiness = new University("University of Montreal", "Business",
//            100);
//    University montrealArts = new University("University of Montreal", "Arts", 100);
//    University montrealEngineering = new University("University of Montreal",
//            "Applied Science/Engineering", 100);
//    University montrealKin = new University("University of Montreal", "Kinesiology",
//            100);
//
//    // Alberta:
//    University albertaScience = new University("University of Alberta", "Science",
//            100);
//    University albertaBusiness = new University("University of Alberta", "Business",
//            100);
//    University albertaArts = new University("University of Alberta", "Arts", 100);
//    University albertaEngineering = new University("University of Alberta",
//            "Applied Science/Engineering", 100);
//    University albertaKin = new University("University of Alberta", "Kinesiology",
//            100);
//
//    // Ottawa:
//    University ottawaScience = new University("University of Ottawa", "Science",
//            100);
//    University ottawaBusiness = new University("University of Ottawa", "Business",
//            100);
//    University ottawaArts = new University("University of Ottawa", "Arts", 100);
//    University ottawaEngineering = new University("University of Ottawa",
//            "Applied Science/Engineering", 100);
//    University ottawaKin = new University("University of Ottawa", "Kinesiology",
//            100);
//
//    // Calgary:
//    University calgaryScience = new University("University of Calgary", "Science",
//            100);
//    University calgaryBusiness = new University("University of Calgary", "Business",
//            100);
//    University calgaryArts = new University("University of Calgary", "Arts", 100);
//    University calgaryEngineering = new University("University of Calgary",
//            "Applied Science/Engineering", 100);
//    University calgaryKin = new University("University of Calgary", "Kinesiology",
//            100);
//
//    // Waterloo:
//    University uwScience = new University("University of Waterloo", "Science",
//            100);
//    University uwBusiness = new University("University of Waterloo", "Business",
//            100);
//    University uwArts = new University("University of Waterloo", "Arts", 100);
//    University uwEngineering = new University("University of Waterloo",
//            "Applied Science/Engineering", 100);
//    University uwKin = new University("University of Waterloo", "Kinesiology",
//            100);
//
//    // Western:
//    University westernScience = new University("Western University", "Science",
//            100);
//    University westernBusiness = new University("Western University", "Business",
//            100);
//    University westernArts = new University("Western University", "Arts", 100);
//    University westernEngineering = new University("Western University",
//            "Applied Science/Engineering", 100);
//    University westernKin = new University("Western University", "Kinesiology",
//            100);
//
//    //Laval:
//    University lavalScience = new University("Universte Laval", "Science",
//            100);
//    University lavalBusiness = new University("Universte Laval", "Business",
//            100);
//    University lavalArts = new University("Universte Laval", "Arts", 100);
//    University lavalEngineering = new University("Universte Laval",
//            "Applied Science/Engineering", 100);
//    University lavalKin = new University("Universte Laval", "Kinesiology",
//            100);
//
//    // Queen's:
//    University queensScience = new University("Queen's University", "Science",
//            100);
//    University queensBusiness = new University("Queen's University", "Business",
//            100);
//    University queensArts = new University("Queen's University", "Arts", 100);
//    University queensEngineering = new University("Queen's University",
//            "Applied Science/Engineering", 100);
//    University queensKin = new University("Queen's University", "Kinesiology",
//            100);
//
//    // SFU:
//    University sfuScience = new University("Simon Fraser University", "Science",
//            100);
//    University sfuBusiness = new University("Simon Fraser University", "Business",
//            100);
//    University sfuArts = new University("Simon Fraser University", "Arts", 100);
//    University sfuEngineering = new University("Simon Fraser University",
//            "Applied Science/Engineering", 100);
//    University sfuKin = new University("Simon Fraser University", "Kinesiology",
//            100);
//
//    // Dalhousie:
//    University dalScience = new University("Dalhousie University", "Science",
//            100);
//    University dalBusiness = new University("Dalhousie University", "Business",
//            100);
//    University dalArts = new University("Dalhousie University", "Arts", 100);
//    University dalEngineering = new University("Dalhousie University",
//            "Applied Science/Engineering", 100);
//    University dalKin = new University("Dalhousie University", "Kinesiology",
//            100);
//
//    // UVIC:
//    University uvicScience = new University("University of Victoria", "Science",
//            100);
//    University uvicBusiness = new University("University of Victoria", "Business",
//            100);
//    University uvicArts = new University("University of Victoria", "Arts", 100);
//    University uvicEngineering = new University("University of Victoria",
//            "Applied Science/Engineering", 100);
//    University uvicKin = new University("University of Victoria", "Kinesiology",
//            100);
//}
