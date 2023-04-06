package ui;

import model.Course;
import model.WorkCompleted;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkCompletedUI extends JInternalFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private JInternalFrame viewGrade;
    private WorkCompleted workCompleted;
    private JButton select;
    private JDesktopPane desktopPane;

    public WorkCompletedUI(WorkCompleted workCompleted, JDesktopPane desktop) {
        super(workCompleted.getName(), true, true, false, false);
        desktopPane = desktop;
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(new JButton(new WorkCompletedUI.ShowGradeAction(workCompleted)));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(buttonPanel);
        setLocation(300, 300);

    }

    private class ShowGradeAction extends AbstractAction implements ActionListener {

        ShowGradeAction(WorkCompleted wk) {
            super("Show Grades in Course");
            workCompleted = wk;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Your grade for " + workCompleted.getName()
                            + " is " + workCompleted.getGrade(),
                    "Grade For " + workCompleted.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
