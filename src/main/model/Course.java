package model;

import java.math.BigDecimal;
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
    public ArrayList<WorkCompleted> getCompletedWork() {
        return completedWork;
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


    public double calculateGrade(ArrayList<WorkCompleted> quizGrades) {
        double numerator = 0;
        for (int k = 0; k < quizGrades.size(); k++) {
            numerator = numerator + quizGrades.get(k).getGrade();
        }
        grade = (numerator / quizGrades.size());
        return grade;
    }

    // REQUIRES: all items in array list is part of quizzes category
    // EFFECTS: calculates grade of all quizzes, scaled by rubric
    public double calculateQuizGrade(ArrayList<WorkCompleted> quizGrades) {
        grade = calculateGrade(quizGrades);
        return ((grade * rubric.getQuizValue()) / 100);
    }

    // REQUIRES: all items in array list is part of assignments category
    // EFFECTS: calculates grade of all assignments, scaled by rubric
    public double calculateAssignmentGrade(ArrayList<WorkCompleted> assignmentGrades) {
        grade = calculateGrade(assignmentGrades);
        return ((grade * rubric.getAssignmentValue()) / 100);
    }

    // REQUIRES: all items in array list is part of midterms category
    // EFFECTS: calculates grade of all midterms, scaled by rubric
    public double calculateMidtermGrade(ArrayList<WorkCompleted> midtermGrades) {
        grade = calculateGrade(midtermGrades);
        return ((grade * rubric.getMidtermValue()) / 100);
    }

    // REQUIRES: all items in array list is part of projects category
    // EFFECTS: calculates grade of all projects, scaled by rubric
    public double calculateProjectGrade(ArrayList<WorkCompleted> projectGrades) {
        grade = calculateGrade(projectGrades);
        return ((grade * rubric.getProjectValue()) / 100);
    }

    // REQUIRES: all items in array list is part of participation category
    // EFFECTS: calculates grade of all participation, scaled by rubric
    public double calculateParticipationGrade(ArrayList<WorkCompleted> participationGrade) {
        grade = calculateGrade(participationGrade);
        return ((grade * rubric.getParticipationValue()) / 100);
    }


    // REQUIRES: all items in array list is part of final exam category
    // EFFECTS: calculates grade of all final exams, scaled by rubric
    public double calculateFinalExamGrade(ArrayList<WorkCompleted> finalExamGrade) {
        grade = calculateGrade(finalExamGrade);
        return ((grade * rubric.getFinalExamValue()) / 100);
    }


    // EFFECTS: calculates overall grade of course
    public double calculateFinalGrades(ArrayList<WorkCompleted> quizGrades, ArrayList<WorkCompleted> assignmentGrades,
                                       ArrayList<WorkCompleted> midtermGrades, ArrayList<WorkCompleted> projectGrades,
                                       ArrayList<WorkCompleted> participationGrade, ArrayList<WorkCompleted>
                                               finalExamGrade) {
        return (((calculateQuizGrade(quizGrades)) + (calculateAssignmentGrade(assignmentGrades))
                + (calculateMidtermGrade(midtermGrades))
                + (calculateProjectGrade(projectGrades))
                + (calculateParticipationGrade(participationGrade))
                + (calculateFinalExamGrade(finalExamGrade))) / 6);
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
