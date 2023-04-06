
package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class ProgramUI extends JFrame implements ListSelectionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Program program;
    protected static JDesktopPane desktop;
    private DefaultListModel listModel;
    private JList list;
    private JInternalFrame controlPanel;
    private JInternalFrame viewCourses;
    private JButton select;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/worklists.json";
    private CourseList courseList;

    // EFFECTS: constructor sets up startup menu
    public ProgramUI() {
        program = new Program();
        program.addCourseList(new CourseList("default"));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

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

        listHelper();


    }

    private void listHelper() {
        listModel = new DefaultListModel<>();
        listModel.addElement(new CourseList("default"));
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                select.setEnabled(false);
            } else {
                select.setEnabled(true);
                courseList = program.getCourseLists().get(list.getSelectedIndex());
            }
        }
    }


    private class AddCourseListAction extends AbstractAction implements ActionListener {

        AddCourseListAction() {
            super("Add Course List");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            String courseListName = JOptionPane.showInputDialog(null, "Course List Name?",
                    "Enter Course List Name", JOptionPane.QUESTION_MESSAGE);
            if (courseListName != null) {
                CourseList c = new CourseList(courseListName);
                program.addCourseList(c);
            }
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
                for (int i = 0; i < program.getCourseLists().size(); i++) {
                    if (program.getCourseLists().get(i).getName().equals(courseListName)) {
                        program.removeCourseList(program.getCourseLists().get(i));
                        listModel.remove(i);

                    }
                }
            }
        }

    }

    private class ViewCourseListAction extends AbstractAction implements ActionListener {

        ViewCourseListAction() {
            super("View Course List");

        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            viewCourses = new JInternalFrame("Course Lists", true, true, false,
                    false);
            viewCourses.setLocation(200, 0);
            viewCourses.setLayout(new BorderLayout());
            viewCourses.setVisible(true);

            for (int i = 0; i < program.getCourseLists().size(); i++) {
                if (!listModel.contains(program.getCourseLists().get(i))) {
                    listModel.add(i, program.getCourseLists().get(i));
                }
            }

            int index = list.getSelectedIndex();
            list.ensureIndexIsVisible(index);

            select = new JButton("Select");
            select.setActionCommand("Select");
            select.addActionListener(new SelectCourseListAction(program.getCourseLists().get(index)));

            viewCourseListHelper();

        }

        private void viewCourseListHelper() {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));
            buttonPane.add(select);
            buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            viewCourses.add(list);
            viewCourses.add(buttonPane, BorderLayout.PAGE_END);
            viewCourses.setSize(250, 250);
            desktop.add(viewCourses);
        }
    }

    private class SelectCourseListAction extends AbstractAction implements ActionListener {

        SelectCourseListAction(CourseList courseList1) {
            super("Select Course List");
            courseList = courseList1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            desktop.add(new CourseListUI(courseList, desktop));
        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save Information");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            saveProgram();
            JInternalFrame photoFrame = new JInternalFrame("Saved", false, true, false,
                    false);
            photoFrame.setLocation(500, 400);
            photoFrame.setSize(400, 250);
            photoFrame.setLayout(new BorderLayout());
            photoFrame.setVisible(true);

            try {
                BufferedImage photo = ImageIO.read(new File(
                        "./data/completed.jpeg"));
                JLabel picLabel = new JLabel(new ImageIcon(photo));
                photoFrame.add(picLabel);
                desktop.add(photoFrame);
            } catch (IOException e) {
                //catch exception
            }
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load Information");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JInternalFrame photoFrame = new JInternalFrame("Loaded", false, true, false,
                    false);
            photoFrame.setLocation(500, 400);
            photoFrame.setSize(400, 250);
            photoFrame.setLayout(new BorderLayout());
            photoFrame.setVisible(true);

            try {
                BufferedImage photo = ImageIO.read(new File(
                        "./data/completed.jpeg"));
                JLabel picLabel = new JLabel(new ImageIcon(photo));
                photoFrame.add(picLabel);
                desktop.add(photoFrame);
            } catch (IOException e) {
                //catch exception
            }
            loadProgram();

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

    // EFFECTS: saves the Program to file
    private void saveProgram() {
        try {
            jsonWriter.open();
            jsonWriter.write(program);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Program from file
    private void loadProgram() {
        try {
            program = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(program, desktop, listModel, list, controlPanel, viewCourses, select, jsonWriter, jsonReader
        );
    }

}
