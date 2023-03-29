package ui;

import model.Course;
import model.CourseList;
import model.Rubric;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class CourseListUI extends JInternalFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private CourseList courseList;
    private JInternalFrame viewCourses;
    private DefaultListModel listModel;
    private JList list;
    private JButton select;
    private Course course;

    public CourseListUI(CourseList c, Component parent) {
        super(c.getName(), true, true, false,false);
        courseList = c;
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        listModel = new DefaultListModel<>();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,1));
        buttonPanel.add(new JButton(new CourseListUI.AddCourseAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.RemoveCourseAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.ShowGradeAction(c)));
        buttonPanel.add(new JButton(new CourseListUI.ViewCoursesAction(c)));
//        buttonPanel.add(new JButton(new CourseListUI.DetermineCompetitiveAction(c)));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(buttonPanel);
        setLocation(100, 100);
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

    private class ViewCoursesAction extends AbstractAction implements ActionListener, ListSelectionListener {

        ViewCoursesAction(CourseList c) {
            super("View Courses");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewCourses = new JInternalFrame("Courses", true, true, false,
                    false);
            viewCourses.setLocation(650, 100);
            viewCourses.setLayout(new BorderLayout());
            viewCourses.setVisible(true);

            for (int i = 0; i < courseList.getCourses().size(); i++) {
                if (!listModel.contains(courseList.getCourses().get(i))) {
                    listModel.add(i, courseList.getCourses().get(i));
                }
            }
            list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.setVisibleRowCount(5);

            viewCourseHelper();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }

        private void viewCourseHelper() {
            list.addListSelectionListener(this);
            int index = list.getSelectedIndex();
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

            select = new JButton("Select");
            select.setActionCommand("Select");
            select.addActionListener(new SelectCourseListAction(courseList.getCourses().get(index)));

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));
            buttonPane.add(select);
            buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


            viewCourses.add(list);
            viewCourses.add(buttonPane, BorderLayout.PAGE_END);
            viewCourses.setSize(250, 250);
            add(viewCourses);
        }

    }

    private class SelectCourseListAction extends AbstractAction implements ActionListener {

        SelectCourseListAction(Course course1) {
            super("Select Course List");
            course = course1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            desktop.add(new CourseUI(course, CourseListUI.this));
        }
    }






    private String rubricHelper(String str) {
        String input = JOptionPane.showInputDialog(null, str + " Weight ?",
                "Enter " + str + " Weight On Rubric In % (eg: 50 for 50%)", JOptionPane.QUESTION_MESSAGE);

        return input;

    }
}
