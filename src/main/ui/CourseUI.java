package ui;

import model.Course;
import model.WorkCompleted;

import javax.swing.*;

public class CourseUI extends JInternalFrame {
    private JInternalFrame courseUI;
    private WorkCompleted workCompleted;
    private JInternalFrame viewWorkCompleted;
    private DefaultListModel listModel;
    private JList list;
    private JButton select;
    private JDesktopPane desktopPane;

    public CourseUI(Course course, JDesktopPane desktopPane) {
        courseUI = new JInternalFrame(course.getName(), true, true, false, false);


    }
}
