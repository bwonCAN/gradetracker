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

    // getters + setters:

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

    public void setGrade(double grade) {
        this.grade = grade;
    }

    //

    // MODIFIES: this
    // EFFECTS: adds new completed assignment to the course
    public void addCompletedWork(WorkCompleted work) {
        completedWork.add(work);
    }

    // MODIFIES: this
    // EFFECTS: removes completed assignment from the course
    public void removeCompletedWork(WorkCompleted work) {
        completedWork.remove(work);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grades from a list of work completed
    public double calculateGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        for (int k = 0; k < grades.size(); k++) {
            numerator = numerator + grades.get(k).getGrade();
        }
        grade = (numerator / grades.size());
        return grade;
    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all quizzes, scaled by rubric
    public double calculateQuizGrade(ArrayList<WorkCompleted> quizGrades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < quizGrades.size(); k++) {
            if ((quizGrades.get(k).getName().contains("Quiz")) || (quizGrades.get(k).getName().contains("quiz"))) {
                numerator = numerator + quizGrades.get(k).getGrade();
                grades1.add(quizGrades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getQuizValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all assignments, scaled by rubric
    public double calculateAssignmentGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Assign")) || (grades.get(k).getName().contains("assign"))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getAssignmentValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all midterms, scaled by rubric
    public double calculateMidtermGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Mid")) || (grades.get(k).getName().contains("mid"))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getMidtermValue()) / 100);
    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all projects, scaled by rubric
    public double calculateProjectGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Proj")) || (grades.get(k).getName().contains("proj"))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getProjectValue()) / 100);
    }

    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all participation, scaled by rubric
    public double calculateParticipationGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Partici")) || (grades.get(k).getName().contains("partici"))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getParticipationValue()) / 100);
    }


    // REQUIRES: WorkCompleted must not be empty
    // EFFECTS: calculates grade of all final exams, scaled by rubric
    public double calculateFinalExamGrade(ArrayList<WorkCompleted> grades) {
        double numerator = 0;
        ArrayList<WorkCompleted> grades1 = new ArrayList<>();
        for (int k = 0; k < grades.size(); k++) {
            if ((grades.get(k).getName().contains("Final")) || (grades.get(k).getName().contains("final"))) {
                numerator = numerator + grades.get(k).getGrade();
                grades1.add(grades.get(k));
            }
        }
        return (((numerator / grades1.size()) * rubric.getFinalExamValue()) / 100);
    }


    // REQUIRES: every submethod needs to have a value, otherwise throws NaN error
    // EFFECTS: calculates overall grade of course
    public double calculateGradeFinal(ArrayList<WorkCompleted> grades) {
        this.grade = ((calculateFinalExamGrade(grades))
                + (calculateParticipationGrade(grades))
                + (calculateProjectGrade(grades))
                + (calculateMidtermGrade(grades))
                + (calculateAssignmentGrade(grades))
                + (calculateQuizGrade(grades)));

        return this.grade;
    }

}
