package ui;

import model.Course;
import model.CourseList;
import model.Program;
import model.Rubric;

import java.util.ArrayList;
import java.util.Scanner;


// Grade Calculator Application
public class GradeCalculatorApp {
    private Program program;
    private CourseList courseList;
    private Rubric rubric;
    private Course course;
    private Scanner input;

    // EFFECTS: runs the grade calculator app
    public GradeCalculatorApp() {
        runCourseLists();
    }

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
            System.out.print("Which Course List Would You Like To Remove? Please Type Name Exactly");
            removeCourseLists();
        } else if (command.equals("v")) {
            viewCourseList();
        } else if (command.equals("b")) {
            runCourseLists(); //!!! how to go back to home page?
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("\nHi and welcome to my application! I hope this will be of benefit to you!");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course list");
        System.out.println("\tr -> remove course list");
        System.out.println("\tv -> view course list");
        System.out.println("\tb -> go back to home screen -> current resets everything -> needs work");
        System.out.println("\tq -> quit");
    }

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

        System.out.println("\nCome Back Soon To Update Your Marks, Good Luck With Your Studies!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandCourses(String command, CourseList selectedCourseList) {
        if (command.equals("a")) {
            addCourse(selectedCourseList);
        } else if (command.equals("r")) {
            System.out.print("Which Course Would You Like To Remove? Please Type Name Exactly");
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
            runCourses(selectedCourseList);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandAssignments(String command, CourseList selectedCourseList) {
        if (command.equals("a")) {
            System.out.print("Please Enter The Rubric Of The Course In This Order: Quiz, Assignment, Midterm Exam, "
                    + "Projects, Participation, Final Exam ");
            addCourse(selectedCourseList);
        } else if (command.equals("r")) {
            System.out.print("Which Course Would You Like To Remove? Please Type Name Exactly");
            removeCourse(selectedCourseList);
        } else if (command.equals("s")) {
            showCourses(selectedCourseList);
        } else if (command.equals("v")) {
            viewCourse(selectedCourseList);
        } else if (command.equals("g")) {
            showGrade(selectedCourseList);
        } else if (command.equals("n")) {
            newCourseList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of Course Lists to user
    private void displayMenu1() {
        System.out.println("\nSelect from:");
        for (int i = 0; i < program.getCourseLists().size(); i++) {
            System.out.println("press" + " " + i + " " + "for" + " " + program.getCourseLists().get(i).getName());
        }
        System.out.println("\nb -> go back to home screen");

    }

    // EFFECTS: displays menu of Course options to user
    private void displayMenu2(CourseList selectedCourseList) {
        System.out.print("You are now in the" + " " + selectedCourseList.getName() + " " + "course list");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add course");
        System.out.println("\td -> delete course");
        System.out.println("\ts -> show courses");
        System.out.println("\tv -> view coursed details");
        System.out.println("\tg -> show overall grade");
        System.out.println("\tn -> new course list");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays menu of Courses to user
    private void displayMenu3(CourseList selectedCourseList) {
        System.out.println("\nSelect from:");
        for (int i = 0; i < selectedCourseList.getCourses().size(); i++) {
            System.out.println("press" + " " + i + " " + "for" + " " + selectedCourseList.getCourses().get(i).getName())
            ;
        }
        System.out.println("\nb -> go back to home screen");

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

    // EFFECTS: adds course to course list
    private void addCourse(CourseList c) {
        System.out.print("Please Enter The Rubric Criteria Of The Course You Would Like To Enter, Type The First Number"
                + " And Then Enter.");
        rubric = new Rubric(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(),
                input.nextInt());
        System.out.print("Please Enter The Name Of The Course You Would Like To Add");
        course = new Course(input.next(), rubric);
        c.addCourse(course);
        System.out.println(course.getName() + "added");
    }

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

    // EFFECTS: show course list
    private ArrayList<Course> showCourses(CourseList c) {
        return c.getCourses();
    }

    //EFFECTS: view details of specific course !!!
    private void viewCourse(CourseList c) {
        System.out.println("Which Course Would You Like To View?");
        c.getCourses();
        displayMenu3(c);
        Course selectedCourse = selectCourse(input.nextInt());
        runOneCourse(selectedCourse);
    }

    // EFFECTS: prompts user to select course list and returns it
    private Course selectCourse(int command) {
        return courseList.getCourses().get(command);
    }

    //EFFECTS: show grade of the current course list so far
    private double showGrade(CourseList c) {
        return courseList.calculateCourseListGrade(c);
    }

    //EFFECTS: create a new course list
    private void newCourseList() {
        CourseList c2 = new CourseList(input.next());
    }

}
