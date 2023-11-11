package org.bsiur.ui.frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField firstname;
    private JTextField surname;
    private JTextField lastname;
    private JTextField birthday;
    private JTextField diagnosis;

    public EditDialog(JDialog dialog, boolean b) {
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(dialog);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {
        // add your code here
        setVisible(false);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public String getFirstname() {
        return firstname.getText();
    }

    public void setFirstname(String firstname) {
        this.firstname.setText(firstname);
    }

    public String getSurname() {
        return surname.getText();
    }

    public void setSurname(String surname) {
        this.surname.setText(surname);
    }

    public String getLastname() {
        return lastname.getText();
    }

    public void setLastname(String lastname) {
        this.lastname.setText(lastname);
    }

    public String getBirthday() {
        return birthday.getText();
    }

    public void setBirthday(String birthday) {
        this.birthday.setText(birthday);
    }

    public String getDiagnosis() {
        return diagnosis.getText();
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis.setText(diagnosis);
    }
}