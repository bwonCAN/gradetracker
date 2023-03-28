
package ui;

import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgramUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Program program;
    private JDesktopPane desktop;
    private DefaultListModel listModel;
    private JList list;
    private JInternalFrame controlPanel;
    private JInternalFrame viewCourses;
    private JLabel label;

    // EFFECTS: constructor sets up startup menu
    public ProgramUI() {
        program = new Program();
        program.addCourseList(new CourseList("default"));
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false,
                false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("University Progress Tracker");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addMenu();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        listModel = new DefaultListModel<>();

    }

    // EFFECTS: adds button panel to main menu
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton(new AddCourseListAction()));
        buttonPanel.add(new JButton(new RemoveCourseListAction()));
        buttonPanel.add(new JButton(new ViewCourseListAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

    }


    private class AddCourseListAction extends AbstractAction implements ActionListener, DocumentListener,
            ListSelectionListener {

        AddCourseListAction() {
            super("Add Course List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            String courseListName = JOptionPane.showInputDialog(null, "Course List Name",
                    "Enter Course List Name", JOptionPane.QUESTION_MESSAGE);
            if (courseListName != null) {
                CourseList c = new CourseList(courseListName);
                program.addCourseList(c);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    private class RemoveCourseListAction extends AbstractAction implements ActionListener {

        RemoveCourseListAction() {
            super("Remove Course List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String courseListName = JOptionPane.showInputDialog(null, "Enter Course List Name To Be Removed",
                    "Remove Course List", JOptionPane.QUESTION_MESSAGE);
            if (courseListName != null) {
                for (CourseList c : program.getCourseLists()) {
                    if (c.getName().equals(courseListName)) {
                        program.removeCourseList(c);
                    }
                }
            }
        }
    }

    // TODO: Finish This
    private class ViewCourseListAction extends AbstractAction implements ListSelectionListener {

        ViewCourseListAction() {
            super("View Course List");

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            viewCourses = new JInternalFrame("Course Lists", true, true, false,
                    false);
            viewCourses.setLayout(new BorderLayout());
            viewCourses.pack();
            viewCourses.setVisible(true);

            for (int i = 0; i < program.getCourseLists().size(); i++) {
                if (!listModel.contains(program.getCourseLists().get(i))) {
                    listModel.add(i, program.getCourseLists().get(i));
                }
            }
            list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.addListSelectionListener(this);
            list.setVisibleRowCount(5);

            viewCourses.add(list);
            desktop.add(viewCourses);
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    // TODO: Finish This
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save Information");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //Stub
        }
    }

    // TODO: Finish This
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load Information");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //Stub
        }
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Save");
        saveMenu.setMnemonic('S');
        addMenuItem(saveMenu, new SaveAction(),
                KeyStroke.getKeyStroke("control S"));
        menuBar.add(saveMenu);

        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic('L');
        addMenuItem(loadMenu, new LoadAction(),
                KeyStroke.getKeyStroke("control L"));
        menuBar.add(loadMenu);

        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }


    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            ProgramUI.this.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        new ProgramUI();
    }
}
