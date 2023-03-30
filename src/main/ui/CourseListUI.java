package ui;

import model.Course;
import model.CourseList;
import model.Rubric;
import model.University;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseListUI extends JInternalFrame implements ListSelectionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private CourseList courseList;
    private JInternalFrame viewCourses;
    private JInternalFrame viewUniversity;
    private DefaultListModel universityModel;
    private JList universityList;
    private JButton selectUniversity;
    private double grade;
    private String universityName;
    private DefaultListModel listModel;
    private JList list;
    private JButton select;
    private Course course;
    private JDesktopPane desktopPane;

    public CourseListUI(CourseList c, JDesktopPane desktop) {
        super(c.getName(), true, true, false,false);
        c.addCourse(new Course("default", new Rubric(0,0,0,0,0,0)));
        courseList = c;
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        listModel = new DefaultListModel<>();
        listModel.addElement(new Course("default", new Rubric(0,0,0,0,0,0)));
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);

        desktopPane = desktop;

        select = new JButton("Select");

        addButtonPanel(c);
    }

    private void addButtonPanel(CourseList c) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton(new CourseListUI.AddCourseAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.RemoveCourseAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.ShowGradeAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.ViewCoursesAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.DetermineCompetitiveAction(c)));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(buttonPanel);
        setLocation(500, 0);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                select.setEnabled(false);
            } else {
                select.setEnabled(true);
                course = courseList.getCourses().get(list.getSelectedIndex());
            }
        }
    }

    private class AddCourseAction extends AbstractAction implements ActionListener {

        AddCourseAction(CourseList c) {
            super("Add Course");
            courseList = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseName = JOptionPane.showInputDialog(null, "Course Name?",
                    "Enter Course Name", JOptionPane.QUESTION_MESSAGE);
            int quizRubric = Integer.parseInt(rubricHelper("Quiz"));
            int assignmentRubric = Integer.parseInt(rubricHelper("Assignment"));
            int midtermRubric = Integer.parseInt(rubricHelper("Midterm"));
            int projectRubric = Integer.parseInt(rubricHelper("Project"));
            int participationRubric = Integer.parseInt(rubricHelper("Participation"));
            int finalExamRubric = Integer.parseInt(rubricHelper("Final Exam"));
            if (courseName != null) {
                Rubric r = new Rubric(quizRubric, assignmentRubric, midtermRubric, projectRubric,
                        participationRubric, finalExamRubric);
                Course course = new Course(courseName, r);
                courseList.addCourse(course);
            }
        }
    }

    private class RemoveCourseAction extends AbstractAction implements ActionListener {

        RemoveCourseAction(CourseList c) {
            super("Remove Course");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseName = JOptionPane.showInputDialog(null, "Course Name?",
                    "Enter Course Name To Remove", JOptionPane.QUESTION_MESSAGE);
            if (courseName != null) {
                for (int i = 0; i < courseList.getCourses().size(); i++) {
                    if (courseList.getCourses().get(i).getName().equals(courseName)) {
                        courseList.removeCourse(courseList.getCourses().get(i));
                    }
                }
            }
        }
    }

    private class ShowGradeAction extends AbstractAction implements ActionListener {

        ShowGradeAction(CourseList c) {
            super("Show Grades in Course List");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Your grade for " + courseList.getName()
                            + " is " + courseList.calculateCourseListGrade(courseList),
                    "Grade For " + courseList.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class ViewCoursesAction extends AbstractAction implements ActionListener {

        ViewCoursesAction(CourseList c) {
            super("View Courses");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewCourses = new JInternalFrame("Courses", true, true, false,
                    false);
            viewCourses.setLocation(0, 400);
            viewCourses.setLayout(new BorderLayout());
            viewCourses.setVisible(true);

            for (int i = 0; i < courseList.getCourses().size(); i++) {
                if (!listModel.contains(courseList.getCourses().get(i))) {
                    listModel.add(i, courseList.getCourses().get(i));
                }
            }
            int index = list.getSelectedIndex();
            list.ensureIndexIsVisible(index);

            select.setActionCommand("Select");
            select.addActionListener(new SelectCourseListAction(courseList.getCourses().get(index)));
            viewCourseHelper();
        }


        private void viewCourseHelper() {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));
            buttonPane.add(select);
            buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


            viewCourses.add(list);
            viewCourses.add(buttonPane, BorderLayout.PAGE_END);
            viewCourses.setSize(250, 250);
            desktopPane.add(viewCourses);
        }

    }

    private class SelectCourseListAction extends AbstractAction implements ActionListener {

        SelectCourseListAction(Course course1) {
            super("Select Course List");
            course = course1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            desktopPane.add(new CourseUI(course, desktopPane));
        }
    }

    private class DetermineCompetitiveAction extends AbstractAction implements ActionListener, ListSelectionListener {

        DetermineCompetitiveAction(CourseList c) {
            super("Determine Competitive");
            courseList = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewUniversity = new JInternalFrame("Universities", true, true, false,
                    false);
            viewUniversity.setLocation(650, 450);
            viewUniversity.setLayout(new BorderLayout());
            viewUniversity.setVisible(true);

            universityModel = new DefaultListModel();
            universityModel.addElement("University of Toronto");
            universityModel.addElement("University of British Columbia");
            universityModel.addElement("McGill University");
            universityModel.addElement("McMaster University");
            universityModel.addElement("University of Montreal");

            universityList = new JList<>(universityModel);
            universityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            universityList.setSelectedIndex(0);
            universityList.setVisibleRowCount(5);
            universityList.addListSelectionListener(this);

            selectUniversityHelper(courseList);

        }

        private void selectUniversityHelper(CourseList courseList) {
            int index = universityList.getSelectedIndex();
            universityList.setSelectedIndex(index);
            universityList.ensureIndexIsVisible(index);

            selectUniversity = new JButton("Select");
            selectUniversity.setActionCommand("Select");
            selectUniversity.addActionListener(new SelectUniversityAction(
                    courseList.calculateCourseListGrade(courseList), universityModel.get(index).toString()));

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));
            buttonPane.add(selectUniversity);
            buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


            viewUniversity.add(universityList);
            viewUniversity.add(buttonPane, BorderLayout.PAGE_END);
            viewUniversity.setSize(250, 250);
            desktopPane.add(viewUniversity);
        }

        private class SelectUniversityAction extends AbstractAction implements ActionListener {

            SelectUniversityAction(double grades, String name) {
                super("Select University List");
                grade = grades;
                universityName = name;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.add(new UniversityUI(grade, universityName, desktopPane));
            }
        }

        //University Info, change admission averages here to update application:


        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (universityList.getSelectedIndex() == -1) {
                    selectUniversity.setEnabled(false);
                } else {
                    selectUniversity.setEnabled(true);
                    universityName = universityModel.get(universityList.getSelectedIndex()).toString();
                }
            }
        }
    }

    private String rubricHelper(String str) {
        String input = JOptionPane.showInputDialog(null, str + " Weight ?",
                "Enter " + str + " Weight On Rubric In % (eg: 50 for 50%)", JOptionPane.QUESTION_MESSAGE);

        return input;

    }
}
