package ui;

import model.Course;
import model.CourseList;
import model.Rubric;
import model.WorkCompleted;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseUI extends JInternalFrame implements ListSelectionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private JInternalFrame viewWorks;
    private WorkCompleted workCompleted;
    private JInternalFrame viewWorkCompleted;
    private DefaultListModel listModel;
    private JList list;
    private JButton select;
    private JDesktopPane desktop;
    private Course course;

    public CourseUI(Course course1, JDesktopPane desktopPane) {
        super(course1.getName(), true, true, false, false);
        course = course1;
        desktop = desktopPane;

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        listModel = new DefaultListModel<>();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton(new CourseUI.AddWorkAction(course1)));
        buttonPanel.add(new JButton(new CourseUI.RemoveWorkAction(course1)));
        buttonPanel.add(new JButton(new CourseUI.ShowWorkAction(course1)));
        buttonPanel.add(new JButton(new CourseUI.ShowGradeAction(course1)));
        buttonPanel.add(new JButton(new CourseUI.ViewWorkDetailsAction(course1)));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(buttonPanel);
        setLocation(500, 400);

        setupList();

    }

    private void setupList() {
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                select.setEnabled(false);
            } else {
                select.setEnabled(true);
                workCompleted = course.getCompletedWork().get(list.getSelectedIndex());
            }
        }
    }

    private class AddWorkAction extends AbstractAction implements ActionListener {

        AddWorkAction(Course c) {
            super("Add Completed Work");
            course = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseName = JOptionPane.showInputDialog(null, "Work Name?",
                    "Enter Work Name", JOptionPane.QUESTION_MESSAGE);
            String gradeInput = JOptionPane.showInputDialog(null, "Grade?",
                    "Enter Grade in %", JOptionPane.QUESTION_MESSAGE);
            Double grade = Double.parseDouble(gradeInput);
            if (courseName != null) {
                WorkCompleted workCompleted1 = new WorkCompleted(courseName, grade);
                course.addCompletedWork(workCompleted1);
            }
        }
    }

    private class RemoveWorkAction extends AbstractAction implements ActionListener {

        RemoveWorkAction(Course c) {
            super("Remove Completed Work");
            course = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String courseName = JOptionPane.showInputDialog(null, "Completed Work Name?",
                    "Enter Work Name To Remove", JOptionPane.QUESTION_MESSAGE);
            if (courseName != null) {
                for (int i = 0; i <= course.getCompletedWork().size(); i++) {
                    if (course.getCompletedWork().get(i).getName().equals(courseName)) {
                        course.removeCompletedWork(course.getCompletedWork().get(i));
                    }
                }
            }
        }
    }

    private class ShowGradeAction extends AbstractAction implements ActionListener {

        ShowGradeAction(Course c) {
            super("Show Grades in Course");
            course = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Your grade for " + course.getName()
                            + " is " + course.calculateGradeFinal(course.getCompletedWork()),
                    "Grade For " + course.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // TODO: this may not work, test
    private class ShowWorkAction extends AbstractAction implements ActionListener {

        ShowWorkAction(Course c) {
            super("Show Work in Course");
            course = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Your completed work for " + course.getName()
                            + " is as follows: " + course.getCompletedWork(),
                    "Work For " + course.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private class ViewWorkDetailsAction extends AbstractAction implements ActionListener {

        ViewWorkDetailsAction(Course c) {
            super("View Completed Work");
            course = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            viewWorks = new JInternalFrame("Work In Course", true, true, false,
                    false);
            viewWorks.setLocation(650, 400);
            viewWorks.setLayout(new BorderLayout());
            viewWorks.setVisible(true);

            for (int i = 0; i < course.getCompletedWork().size(); i++) {
                if (!listModel.contains(course.getCompletedWork().get(i))) {
                    listModel.add(i, course.getCompletedWork().get(i));
                }
            }

            viewWorkHelper();
        }


        private void viewWorkHelper() {
            int index = list.getSelectedIndex();
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

            select = new JButton("Select");
            select.setActionCommand("Select");
            select.addActionListener(new CourseUI.SelectWorkAction(course.getCompletedWork().get(index)));

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));
            buttonPane.add(select);
            buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


            viewWorks.add(list);
            viewWorks.add(buttonPane, BorderLayout.PAGE_END);
            viewWorks.setSize(250, 250);
            desktop.add(viewWorks);
        }

    }

    private class SelectWorkAction extends AbstractAction implements ActionListener {

        SelectWorkAction(WorkCompleted workCompleted1) {
            super("Select Course List");
            workCompleted = workCompleted1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            desktop.add(new WorkCompletedUI(workCompleted, desktop));
        }
    }

}
