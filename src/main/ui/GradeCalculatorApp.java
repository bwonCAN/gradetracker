package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;


// Grade Calculator Application
public class GradeCalculatorApp {
    private Program program;
    private CourseList courseList;
    private Rubric rubric;
    private Course course;
    private Scanner input;
    private WorkCompleted work;

    // EFFECTS: runs the grade calculator app
    public GradeCalculatorApp() {
        runCourseLists();
    }

    // ADD WORK LISTS:

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCourseLists() {
        boolean keepGoing = true;
        String command = null;
        program = new Program();

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nCome Back Soon To Update Your Marks, Good Luck With Your Studies!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            System.out.print("What Name Would You Like To Give New Course List?");
            addNewCourseList();
        } else if (command.equals("r")) {
            System.out.print("Which Course List Would You Like To Remove? Please Type Name Exactly: ");
            for (CourseList c : program.getCourseLists()) {
                System.out.println(c.getName());
            }
            removeCourseLists();
        } else if (command.equals("v")) {
            viewCourseList();
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(input.next());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("\nHi and welcome to my application! I hope this will be of benefit to you!");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course list");
        System.out.println("\tr -> remove course list (must have at least two to work)");
        System.out.println("\tv -> view course list");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of Course Lists to user
    private void displayMenu1() {
        System.out.println("\nSelect from:");
        for (int i = 0; i < program.getCourseLists().size(); i++) {
            System.out.println("press" + " " + i + " " + "for" + " " + program.getCourseLists().get(i).getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes
    private void init() {
        Program p = new Program();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // MODIFIES: this
    // EFFECTS: adds new course list
    private void addNewCourseList() {
        courseList = new CourseList(input.next());
        program.addCourseList(courseList);
        System.out.println(courseList.getName() + " " + "Course List Added");

    }

    // REQUIRES: must have at least two course lists in the program for this to run
    // MODIFIES: this
    // EFFECTS: removes course list
    private void removeCourseLists() {
        for (CourseList c : program.getCourseLists()) {
            if (c.getName().equals(input.next())) {
                program.removeCourseList(c);
                System.out.println("Course List Removed");
            } else {
                System.out.println("Error Occurred, Please Ensure To Type The Name Exactly. It Is Case Sensitive.");
            }
        }
        //System.out.println("Course List Removed");
    }

    // EFFECTS: views course list !!!
    private void viewCourseList() {
        program.getCourseLists();
        System.out.println("Which Course List Would You Like To View In Detail?");
        displayMenu1();
        CourseList selectedCourseList = selectCourseList(input.nextInt());
        runCourses(selectedCourseList);
    }

    // EFFECTS: prompts user to select course list and returns it
    private CourseList selectCourseList(int command) {
        return program.getCourseLists().get(command);
    }

    //EFFECTS: show grade of the current course list so far
    private void showGrade(CourseList c) {
        System.out.println("Your Grade For This Course List Is:" + " " + c.calculateCourseListGrade(c));
    }

    //EFFECTS: create a new course list
    private void newCourseList() {
        CourseList c2 = new CourseList(input.next());
    }

    // END OF MULTIPLE WORK LISTS



    // ADDS COURSES TO A SPECIFIED WORK LIST

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCourses(CourseList selectedCourseList) {
        boolean keepGoing = true;
        String command = null;
        Course course = null;

        init();

        while (keepGoing) {
            displayMenu2(selectedCourseList);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandCourses(command, selectedCourseList);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandCourses(String command, CourseList selectedCourseList) {
        if (command.equals("a")) {
            addCourse(selectedCourseList);
        } else if (command.equals("r")) {
            System.out.print("Which Course Would You Like To Remove? Please Type Name Exactly: ");
            for (Course c : selectedCourseList.getCourses()) {
                System.out.println(c.getName());
            }
            removeCourse(selectedCourseList);
        } else if (command.equals("s")) {
            showCourses(selectedCourseList);
        } else if (command.equals("v")) {
            viewCourse(selectedCourseList);
        } else if (command.equals("g")) {
            showGrade(selectedCourseList);
        } else if (command.equals("n")) {
            newCourseList();
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(input.next());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of Course options to user
    private void displayMenu2(CourseList selectedCourseList) {
        System.out.print("You are now in the" + " " + selectedCourseList.getName() + " " + "course list");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course");
        System.out.println("\tr -> remove course (must have at least two to work)");
        System.out.println("\ts -> show courses");
        System.out.println("\tv -> view course details");
        System.out.println("\tg -> show overall grade for this course list");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> back");
    }

    // EFFECTS: displays menu of Courses to user
    private void displayMenu3(CourseList selectedCourseList) {
        System.out.println("\nSelect from:");
        for (int i = 0; i < selectedCourseList.getCourses().size(); i++) {
            System.out.println("press" + " " + i + " " + "for" + " " + selectedCourseList.getCourses().get(i).getName())
            ;
        }
    }


    // EFFECTS: adds course to course list
    private void addCourse(CourseList c) {
        System.out.print("Please Enter The Rubric Criteria Of The Course You Would Like To Enter In This Order: "
                + "Quizzes, Assignments, Midterm Exams, Projects, Participation, Final Exams.  Type The First Number"
                + " And Then Enter.");
        rubric = new Rubric(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(),
                input.nextInt());
        System.out.print("Please Enter The Name Of The Course You Would Like To Add");
        course = new Course(input.next(), rubric);
        c.addCourse(course);
        System.out.println(course.getName() + " " + "added");
    }

    // REQUIRES: more than two courses are needed in the course list before this is able to remove one of them
    // EFFECTS: removes course from course list
    private void removeCourse(CourseList c) {
        for (Course course : c.getCourses()) {
            if (course.getName().equals(input.next())) {
                c.removeCourse(course);
                System.out.println("Course Removed");

            } else {
                System.out.println("No Course In List Matches, Please Type Name Again. It Is Case Sensitive");
            }
        }
    }

    // EFFECTS: show courses in course list
    private void showCourses(CourseList c) {
        System.out.println("Your Courses For This Course List Are: " + " ");
        for (int i = 0; i < c.getCourses().size(); i++) {
            System.out.println(c.getCourses().get(i).getName());
        }
    }

    //EFFECTS: view details of specific course
    private void viewCourse(CourseList c) {
        c.getCourses();
        System.out.println("Which Course Would You Like To View?");
        displayMenu3(c);
        Course selectedCourse = selectCourse(input.nextInt(), c);
        runOneCourse(selectedCourse);
    }

    // EFFECTS: prompts user to select course list and returns it
    private Course selectCourse(int command, CourseList c) {
        return c.getCourses().get(command);
    }

    // END OF ADDING COURSES TO SPECIFIED WORK LIST




    // ADDING ASSIGNMENTS TO A SPECIFIED COURSE

    // MODIFIES: this
    // EFFECTS: processes user input !!!
    private void runOneCourse(Course selectedCourse) {
        boolean keepGoing = true;
        String command = null;
        init();

        while (keepGoing) {
            displayMenu4(selectedCourse);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandAssignments(command, selectedCourse);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandAssignments(String command, Course selectedCourse) {
        if (command.equals("a")) {
            addAssignment(selectedCourse);
        } else if (command.equals("r")) {
            System.out.print("Which Assignment Would You Like To Remove? Please Type Name Exactly");
            for (WorkCompleted c : course.getCompletedWork()) {
                System.out.println(c.getName());
            }
            removeAssignment(selectedCourse);
        } else if (command.equals("s")) {
            showAssignments(selectedCourse);
        } else if (command.equals("v")) {
            viewAssignment(selectedCourse);
        } else if (command.equals("g")) {
            showGradeInCourse(selectedCourse);
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(command);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of Assignments to user
    private void displayMenu4(Course selectedCourse) {
        System.out.print("You are now in the" + " " + selectedCourse.getName() + " " + "course");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add assignment");
        System.out.println("\tr -> remove assignment (must have at least two to work)");
        System.out.println("\ts -> show assignments in course");
        System.out.println("\tg -> show overall grade for course");
        System.out.println("\tv -> view assignment details");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> back");
    }

    // EFFECTS: adds assignment to course
    private void addAssignment(Course c) {
        System.out.println("Please Enter Name Of Assignment You Would Like To Add. (Eg: Assignment 1) and press enter");
        System.out.println("Please Enter The Grade Of The Assignment You Added");
        WorkCompleted work = new WorkCompleted(input.next(), input.nextInt());
        c.addCompletedWork(work);
        System.out.println(work.getName() + " " + "added");
    }

    // EFFECTS: removes assignment from course
    private void removeAssignment(Course c) {
        for (WorkCompleted work : c.getCompletedWork()) {
            if (c.getName().equals(input.next())) {
                c.removeCompletedWork(work);
                System.out.println(work.getName() + " " + "Removed");

            } else {
                System.out.println("No Work In List Matches, Please Type Name Again. It Is Case Sensitive");
            }
        }
    }

    //EFFECTS: view details of specific assignment
    private void viewAssignment(Course c) {
        System.out.println("Which Assignment Would You Like To View?");
        displayMenu5(c);
        WorkCompleted work = selectWork(input.nextInt());
        runOneAssignment(work);
    }

    // EFFECTS: show grade of course
    private void showGradeInCourse(Course c) {
        System.out.println("Your Grade In" + " " + c.getName() + " " + "is" + " "
                + c.calculateGradeFinal(c.getCompletedWork()));
    }

    // EFFECTS: shows assignments in the course
    private void showAssignments(Course c) {
        System.out.println("Here Are Your Assignments For The Course: ");
        for (int i = 0; i < c.getCompletedWork().size(); i++) {
            System.out.println(c.getCompletedWork().get(i).getName());
        }
    }

    // END OF ADDING ASSIGNMENTS



    // VIEWING ASSIGNMENTS

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runOneAssignment(WorkCompleted selectedWork) {
        boolean keepGoing = true;
        String command = null;
        Course course = null;

        init();

        while (keepGoing) {
            displayMenu6(selectedWork);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandOneAssignment(command, selectedWork);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandOneAssignment(String command, WorkCompleted selectedWork) {
        if (command.equals("g")) {
            showGradeInAssignment(selectedWork);
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(input.next());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu5(Course selectedCourse) {
        System.out.println("\nSelect from:");
        for (int i = 0; i < selectedCourse.getCompletedWork().size(); i++) {
            System.out.println("press" + " " + i + " " + "for" + " "
                    + selectedCourse.getCompletedWork().get(i).getName());
        }
    }

    // EFFECTS: prompts user to select course list and returns it
    private WorkCompleted selectWork(int command) {
        return course.getCompletedWork().get(command);
    }

    private void displayMenu6(WorkCompleted selectedWork) {
        System.out.print("You are now in the" + " " + selectedWork.getName() + " " + "page");
        System.out.println("\nSelect from:");
        System.out.println("\tg -> show grade for assignment");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> back");
    }

    // EFFECTS: shows the grade for the specified assignment
    private void showGradeInAssignment(WorkCompleted work) {
        System.out.println("Here Is Your Grade For The Assignment:" + " " + work.getGrade());
    }


}
