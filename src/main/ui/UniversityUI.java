package ui;

import model.Course;
import model.CourseList;
import model.University;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UniversityUI extends JInternalFrame implements ListSelectionListener {
    private double grade;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private JInternalFrame viewPrograms;
    private JButton selectProgram;
    private String universityName;
    private DefaultListModel listModel;
    private JList list;
    private JDesktopPane desktop;
    private ArrayList<University> uniList;
    private University selectedProgram;
    private University university;


    public UniversityUI(double grade1, String name, JDesktopPane desktopPane) {
        super(name + " programs", true, true, false, false);
        grade = grade1;
        universityName = name;
        desktop = desktopPane;
        listModel = new DefaultListModel();

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        setUniversityList(name);
        addButtonPanel(grade1);

        for (int i = 0; i < uniList.size(); i++) {
            if (!listModel.contains(uniList.get(i).getProgram())) {
                listModel.add(i, uniList.get(i).getProgram());
            }
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.addListSelectionListener(this);

        selectProgramHelper(grade1);

    }

    private void addButtonPanel(double grade) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(new JButton(new UniversityUI.SelectProgramAction(grade, uniList.get(0))));
        buttonPanel.add(new JButton(new UniversityUI.SelectProgramAction(grade, uniList.get(1))));
        buttonPanel.add(new JButton(new UniversityUI.SelectProgramAction(grade, uniList.get(2))));
        buttonPanel.add(new JButton(new UniversityUI.SelectProgramAction(grade, uniList.get(3))));
        buttonPanel.add(new JButton(new UniversityUI.SelectProgramAction(grade, uniList.get(4))));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(buttonPanel);
        setLocation(500, 500);
    }

    private void selectProgramHelper(double grade1) {
        viewPrograms = new JInternalFrame("Programs", true, true, false, false);
        int index = list.getSelectedIndex();
        list.ensureIndexIsVisible(index);

        selectProgram = new JButton("Select");
        selectProgram.setActionCommand("Select");
        selectProgram.addActionListener(new UniversityUI.SelectProgramAction(grade1, uniList.get(index)));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(selectProgram);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        viewPrograms.add(list);
        viewPrograms.add(buttonPane, BorderLayout.PAGE_END);
        viewPrograms.setSize(250, 250);
        desktop.add(viewPrograms);
    }

    private class SelectProgramAction extends AbstractAction implements ActionListener {

        SelectProgramAction(double grade, University university1) {
            super(university1.getProgram());
            university = university1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Your current grade is " + grade
                            + ", the admission average for this program is: " + university.getAdmissionAverage()
                            + ". Therefore, you are "
                            + determineCompetitive(grade, university.getAdmissionAverage()) + " for this program.",
                    "Are you competitive?", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String determineCompetitive(double grade, int admissionAverage) {
        if (grade >= admissionAverage) {
            return "competitive";
        } else {
            return "not competitive";
        }
    }

    private void setUniversityList(String name) {
        if (name.contains("Toronto")) {
            uniList = utPrograms();
        } else if (name.contains("British")) {
            uniList = ubcPrograms();
        } else if (name.contains("McGill")) {
            uniList = mcGillPrograms();
        } else if (name.contains("McMaster")) {
            uniList = mcMasterPrograms();
        } else if (name.contains("Montreal")) {
            uniList = montrealPrograms();
        }
    }

    // EFFECTS: returns list of UT Programs
    public ArrayList<University> utPrograms() {
        University utScience = new University("University of Toronto", "Science",
                94);
        University utBusiness = new University("University of Toronto", "Business",
                92);
        University utArts = new University("University of Toronto", "Arts", 86);
        University utEngineering = new University("University of Toronto",
                "Applied Science/Engineering", 100);
        University utKin = new University("University of Toronto", "Kinesiology",
                92);
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
                94);
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

    // EFFECTS: returns list of McMaster Programs
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                selectProgram.setEnabled(false);
            } else {
                selectProgram.setEnabled(true);
                university = uniList.get(list.getSelectedIndex());
            }
        }
    }
}