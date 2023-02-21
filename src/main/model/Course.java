package model;

import java.util.ArrayList;

// Represents a course in school with the course name and the rubric of the specific course and the grade so far
public class Course {
    private String name; // name of the course
    private Rubric rubric; // rubric for the course
    private ArrayList<WorkCompleted> completedWork; // list of completed work (quizzes, exams, projects, etc)
    private double grade; // the grade for the course so far, in %

    // creates new course with the course name, the corresponding rubric, and list of completed work for that course
    public Course(String courseName, Rubric courseRubric) {
        this.name = courseName;
        this.rubric = courseRubric;
        this.completedWork = new ArrayList<>();
        this.grade = 0;
    }

    // getters:

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

    // MODIFIES: this
    // EFFECTS: adds new completed assignment to the course
    public void addCompletedWork(WorkCompleted work) {
        completedWork.add(work);
    }

    // MODIFIES: this
    // EFFECTS: removes completed assignment to the course
    public void removeCompletedWork(WorkCompleted work) {
        completedWork.remove(work);
    }

    // To calculate grade, I have to add quizzes + assignments + projects, etc. So I have to calculate each and add


    public double calculateGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            numerator = numerator + grades.get(k).getGrade();
        }
        grade = (numerator / grades.size());
        return grade;
    }

    // EFFECTS: calculates grade of all quizzes, scaled by rubric
    public double calculateQuizGrade(ArrayList<WorkCompleted> quizGrades) {
        double numerator = 0;
        for (int k = 0; k < quizGrades.size(); k++) {
            if ((quizGrades.get(k).getName().contains("Quiz")) || (quizGrades.get(k).getName().contains("quiz"))) {
                numerator = numerator + quizGrades.get(k).getGrade();
            } else {
                quizGrades.remove(k);
            }
        }
        return (((numerator / quizGrades.size()) * rubric.getQuizValue()) / 100);
    }


    // REQUIRES: all items in array list is part of assignments category
    // EFFECTS: calculates grade of all assignments, scaled by rubric
    public double calculateAssignmentGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Assign")) || (grades.get(k).getName().contains("assign"))) {
                numerator = numerator + grades.get(k).getGrade();
            } else {
                grades.remove(k);
            }
        }
        return (((numerator / grades.size()) * rubric.getAssignmentValue()) / 100);
    }


    // REQUIRES: all items in array list is part of midterms category
    // EFFECTS: calculates grade of all midterms, scaled by rubric
    public double calculateMidtermGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Mid")) || (grades.get(k).getName().contains("mid"))) {
                numerator = numerator + grades.get(k).getGrade();
            } else {
                grades.remove(k);
            }
        }
        return (((numerator / grades.size()) * rubric.getMidtermValue()) / 100);
    }

    // REQUIRES: all items in array list is part of projects category
    // EFFECTS: calculates grade of all projects, scaled by rubric
    public double calculateProjectGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Proj")) || (grades.get(k).getName().contains("proj"))) {
                numerator = numerator + grades.get(k).getGrade();
            } else {
                grades.remove(k);
            }
        }
        return (((numerator / grades.size()) * rubric.getProjectValue()) / 100);
    }

    // REQUIRES: all items in array list is part of participation category
    // EFFECTS: calculates grade of all participation, scaled by rubric
    public double calculateParticipationGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Partici")) || (grades.get(k).getName().contains("partici"))) {
                numerator = numerator + grades.get(k).getGrade();
            } else {
                grades.remove(k);
            }
        }
        return (((numerator / grades.size()) * rubric.getParticipationValue()) / 100);
    }


    // REQUIRES: all items in array list is part of final exam category
    // EFFECTS: calculates grade of all final exams, scaled by rubric
    public double calculateFinalExamGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Final")) || (grades.get(k).getName().contains("final"))) {
                numerator = numerator + grades.get(k).getGrade();
            } else {
                grades.remove(k);
            }
        }
        return (((numerator / grades.size()) * rubric.getFinalExamValue()) / 100);
    }


    // EFFECTS: calculates overall grade of course
    public double calculateGradeFinal(ArrayList<WorkCompleted> grades) {
        return ((calculateFinalExamGrade(grades))
                + (calculateParticipationGrade(grades))
                + (calculateProjectGrade(grades))
                + (calculateMidtermGrade(grades))
                + (calculateAssignmentGrade(grades))
                + (calculateQuizGrade(grades)));
    }
    // ROUGH CODE FOR CALCULATION
//    public double calculateGrade(ArrayList<Double> grades) {
//        double numerator = 0;
//        for (int k = 0; k < grades.size(); k++) {
//            numerator = numerator + grades.get(k);
//        }
//        grade = (numerator / grades.size());
//        return grade;
//    }

}
