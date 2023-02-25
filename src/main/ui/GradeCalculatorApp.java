package ui;

import model.*;
import ui.UniversityUIInfo;

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
    private ArrayList<University> universities;

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
        } else if (command.equals("c")) {
            runUniversity(selectedCourseList);
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
        System.out.println("\tv -> view course details");
        System.out.println("\tc -> determine if my grade is competitive for university");
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

    // END OF VIEWING ASSIGNMENTS


    // UNIVERSITY INFORMATION


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runUniversity(CourseList selectedCourseList) {
        boolean keepGoing = true;
        String command = null;
        Course course = null;

        init();

        while (keepGoing) {
            displayMenuUniversity(selectedCourseList);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandUniversity(command, selectedCourseList);
            }
        }
    }

    // EFFECTS: displays menu of university options to user
    private void displayMenuUniversity(CourseList selectedCourseList) {
        System.out.print("Which University Are You Interested In?");
        System.out.println("\nSelect from:");
        System.out.println("\tf -> University of Toronto");
        System.out.println("\tg -> University of British Columbia");
        System.out.println("\th -> McGill University");
        System.out.println("\ti -> McMaster University");
        System.out.println("\tj -> University of Montreal");
        System.out.println("\tk -> University of Alberta");
        System.out.println("\tl -> University of Ottawa");
        System.out.println("\tm -> University of Calgary");
        System.out.println("\tn -> University of Waterloo");
        System.out.println("\to -> Western University");
        System.out.println("\tp -> Universte Laval");
        System.out.println("\tr -> Queen's University");
        System.out.println("\ts -> Simon Fraser University");
        System.out.println("\tt -> Dalhousie University");
        System.out.println("\tu -> University of Victoria");
        System.out.println("\tv -> go back to home screen");
        System.out.println("\tq -> back");
        System.out.println("\tb -> go back to home screen");


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void processCommandUniversity(String command, CourseList selectedCourseList) {
        if (command.equals("f")) {
            chooseUT(command, selectedCourseList);
        } else if (command.equals("g")) {
            chooseUBC(command, selectedCourseList);
        } else if (command.equals("h")) {
            chooseMcGill(command, selectedCourseList);
        } else if (command.equals("i")) {
            chooseMcMaster(command, selectedCourseList);
        } else if (command.equals("j")) {
            chooseMontreal(command, selectedCourseList);
        } else if (command.equals("k")) {
            chooseAlberta(command, selectedCourseList);
        } else if (command.equals("l")) {
            chooseOttawa(command, selectedCourseList);
        } else if (command.equals("m")) {
            chooseCalgary(command, selectedCourseList);
        } else if (command.equals("n")) {
            chooseWaterloo(command, selectedCourseList);
        } else if (command.equals("o")) {
            chooseWestern(command, selectedCourseList);
        } else if (command.equals("p")) {
            chooseLaval(command, selectedCourseList);
        } else if (command.equals("r")) {
            chooseQueens(command, selectedCourseList);
        } else if (command.equals("s")) {
            chooseSFU(command, selectedCourseList);
        } else if (command.equals("t")) {
            chooseDalhousie(command, selectedCourseList);
        } else if (command.equals("u")) {
            chooseUvic(command, selectedCourseList);
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(input.next());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Chosen University

    private void chooseUT(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Toronto");
        displayMenuPrograms(selectedCourseList);
        processCommandToronto(input.next(), selectedCourseList);
    }

    private void chooseUBC(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of British Columbia");
        displayMenuPrograms(selectedCourseList);
        processCommandUBC(input.next(),selectedCourseList);
    }

    private void chooseMcGill(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: McGill University");
        displayMenuPrograms(selectedCourseList);
        processCommandMcGill(input.next(),selectedCourseList);
    }

    private void chooseMcMaster(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: McMaster University");
        displayMenuPrograms(selectedCourseList);
        processCommandMcMaster(input.next(),selectedCourseList);
    }

    private void chooseMontreal(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Montreal");
        displayMenuPrograms(selectedCourseList);
        processCommandMontreal(input.next(),selectedCourseList);
    }

    private void chooseAlberta(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Alberta");
        displayMenuPrograms(selectedCourseList);
        processCommandAlberta(input.next(),selectedCourseList);
    }

    private void chooseOttawa(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Ottawa");
        displayMenuPrograms(selectedCourseList);
        processCommandOttawa(input.next(),selectedCourseList);
    }

    private void chooseCalgary(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Calgary");
        displayMenuPrograms(selectedCourseList);
        processCommandCalgary(input.next(),selectedCourseList);
    }

    private void chooseWaterloo(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Waterloo");
        displayMenuPrograms(selectedCourseList);
        processCommandWaterloo(input.next(),selectedCourseList);
    }

    private void chooseWestern(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: Western University");
        displayMenuPrograms(selectedCourseList);
        processCommandWestern(input.next(),selectedCourseList);
    }

    private void chooseLaval(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: Universte Laval");
        displayMenuPrograms(selectedCourseList);
        processCommandLaval(input.next(),selectedCourseList);
    }

    private void chooseQueens(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: Queen's University");
        displayMenuPrograms(selectedCourseList);
        processCommandQueens(input.next(),selectedCourseList);
    }

    private void chooseSFU(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: Simon Fraser University");
        displayMenuPrograms(selectedCourseList);
        processCommandSFU(input.next(),selectedCourseList);
    }

    private void chooseDalhousie(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: Dalhousie University");
        displayMenuPrograms(selectedCourseList);
        processCommandDalhousie(input.next(),selectedCourseList);
    }

    private void chooseUvic(String command, CourseList selectedCourseList) {
        System.out.println("You Have Chosen: University Of Victoria");
        displayMenuPrograms(selectedCourseList);
        processCommandUvic(input.next(),selectedCourseList);
    }

    public void displayMenuPrograms(CourseList selectedCourseList) {
        System.out.println("Which Program Are You Interested In?");
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Science");
        System.out.println("\td -> Business");
        System.out.println("\te -> Arts");
        System.out.println("\tf -> Applied Science/Engineering");
        System.out.println("\tg -> Kinesiology");
        System.out.println("\tb -> go back to home screen");
        System.out.println("\tq -> back");
    }

    // EFFECTS: displays yes/no option to user
    public void displayYesNo(CourseList selectedCourseList) {
        System.out.println("\tSelect from:");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandYesNo(String command, CourseList selectedCourseList,
                                     ArrayList<University> allPrograms, String program) {
        if (command.equals("y")) {
            determineCompetitive(selectedCourseList, allPrograms, program);
        } else if (command.equals("n")) {
            displayMenu2(selectedCourseList);
            processCommandCourses(input.next(),selectedCourseList);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // process commands for the universities

    // EFFECTS: passes user command
    private void processCommandToronto(String command, CourseList selectedCourseList) {
        ArrayList<University> utPrograms = utPrograms();
        processCommandPrograms(command, selectedCourseList, utPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandUBC(String command, CourseList selectedCourseList) {
        ArrayList<University> ubcPrograms = ubcPrograms();
        processCommandPrograms(command, selectedCourseList, ubcPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandMcGill(String command, CourseList selectedCourseList) {
        ArrayList<University> mcGillPrograms = mcGillPrograms();
        processCommandPrograms(command, selectedCourseList, mcGillPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandMcMaster(String command, CourseList selectedCourseList) {
        ArrayList<University> mcMasterPrograms = mcMasterPrograms();
        processCommandPrograms(command, selectedCourseList, mcMasterPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandMontreal(String command, CourseList selectedCourseList) {
        ArrayList<University> montrealPrograms = montrealPrograms();
        processCommandPrograms(command, selectedCourseList, montrealPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandAlberta(String command, CourseList selectedCourseList) {
        ArrayList<University> albertaPrograms = albertaPrograms();
        processCommandPrograms(command, selectedCourseList, albertaPrograms);
    }

    // EFFECTS: passes user command
    private void processCommandOttawa(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = ottawaPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandCalgary(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = calgaryPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandWaterloo(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = waterlooPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandWestern(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = westernPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandLaval(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = lavalPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandQueens(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = queensPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandSFU(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = sfuPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandDalhousie(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = dalhousiePrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }

    // EFFECTS: passes user command
    private void processCommandUvic(String command, CourseList selectedCourseList) {
        ArrayList<University> programs = uvicPrograms();
        processCommandPrograms(command, selectedCourseList, programs);
    }



    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommandPrograms(String command, CourseList selectedCourseList,
                                        ArrayList<University> allPrograms) {
        if (command.equals("c")) {
            science(command, selectedCourseList, allPrograms);
        } else if (command.equals("d")) {
            business(command, selectedCourseList, allPrograms);
        } else if (command.equals("e")) {
            arts(command, selectedCourseList, allPrograms);
        } else if (command.equals("f")) {
            appliedScience(command, selectedCourseList, allPrograms);
        } else if (command.equals("g")) {
            kinesiology(command, selectedCourseList, allPrograms);
        } else if (command.equals("b")) {
            displayMenu();
            processCommand(input.next());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void determineCompetitive(CourseList courselist, ArrayList<University> uniPrograms, String program) {
        for (int i = 0; i < uniPrograms.size(); i++) {
            if (uniPrograms.get(i).getProgram().contains(program)) {
                int admissionAverage = uniPrograms.get(i).getAdmissionAverage();
                calculateCompetitive(courselist, admissionAverage);
            }
        }
    }

    private void calculateCompetitive(CourseList courselist, int admissionAvg) {
        if (courselist.calculateCourseListGrade(courselist) >= admissionAvg) {
            System.out.println("Since your course list grade is" + " "
                    + courselist.calculateCourseListGrade(courselist) + " " + "and the admission average is"
                    + " " + admissionAvg + ", you are competitive for the program");
        } else {
            System.out.println("Since your course list grade is" + " "
                    + courselist.calculateCourseListGrade(courselist) + " " + "and the admission average is"
                    + " " + admissionAvg + ", you are not competitive for the program");
        }
    }

    // Programs:
    private void science(String command, CourseList selectedCourseList, ArrayList<University> allPrograms) {
        System.out.println("You have selected: Science");
        System.out.println("Would you like to calculate if your grades are competitive?");
        String program = "Science";
        displayYesNo(selectedCourseList);
        processCommandYesNo((input.next()), selectedCourseList, allPrograms, program);
    }

    private void business(String command, CourseList selectedCourseList, ArrayList<University> allPrograms) {
        System.out.println("You have selected: Business");
        System.out.println("Would you like to calculate if your grades are competitive?");
        String program = "Business";
        displayYesNo(selectedCourseList);
        processCommandYesNo((input.next()), selectedCourseList, allPrograms, program);
    }

    private void arts(String command, CourseList selectedCourseList, ArrayList<University> allPrograms) {
        System.out.println("You have selected: Arts");
        System.out.println("Would you like to calculate if your grades are competitive?");
        String program = "Arts";
        displayYesNo(selectedCourseList);
        processCommandYesNo((input.next()), selectedCourseList, allPrograms, program);
    }

    private void appliedScience(String command, CourseList selectedCourseList, ArrayList<University> allPrograms) {
        System.out.println("You have selected: Applied Science/Engineering");
        System.out.println("Would you like to calculate if your grades are competitive?");
        String program = "Applied Science/Engineering";
        displayYesNo(selectedCourseList);
        processCommandYesNo((input.next()), selectedCourseList, allPrograms, program);
    }

    private void kinesiology(String command, CourseList selectedCourseList, ArrayList<University> allPrograms) {
        System.out.println("You have selected: Kinesiology");
        System.out.println("Would you like to calculate if your grades are competitive?");
        String program = "Kinesiology";
        displayYesNo(selectedCourseList);
        processCommandYesNo((input.next()), selectedCourseList, allPrograms, program);
    }






    //University Info:




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
