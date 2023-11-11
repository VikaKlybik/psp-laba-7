package org.bsiur.ui.frame;

import org.bsiur.data.connection.MyDBConnection;
import org.bsiur.data.model.PatientTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertPatient extends JDialog {
    private MyDBConnection mdbc;
    private Statement stmt;

    private JPanel contentPane;
    private JButton edit;
    private JButton delete;
    private JTextField firstname;
    private JTextField surname;
    private JTextField lastname;
    private JTextField birthday;
    private JTextField diagnosis;
    private JButton sendButton;
    private JTable patientTable;
    private JLabel message;

    public InsertPatient() {
        try {
            mdbc = new MyDBConnection();
            mdbc.init();
            Connection conn = mdbc.getMyConnection();
            stmt = conn.createStatement();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(edit);

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditDialog dlg = new EditDialog(InsertPatient.this, true);
                int id = Integer.parseInt((String)patientTable.getValueAt(patientTable.getSelectedRow(), 0));
                dlg.setFirstname((String) patientTable.getValueAt(patientTable.getSelectedRow(), 1));
                dlg.setSurname((String) patientTable.getValueAt(patientTable.getSelectedRow(), 2));
                dlg.setLastname((String) patientTable.getValueAt(patientTable.getSelectedRow(), 3));
                dlg.setBirthday((String) patientTable.getValueAt(patientTable.getSelectedRow(), 4));
                dlg.setDiagnosis((String) patientTable.getValueAt(patientTable.getSelectedRow(), 5));
                dlg.setVisible(true);
                try {

                    String insertStr = "UPDATE patient SET lastname=" + quotate(dlg.getLastname()) + "WHERE id =" + id;
                    int done = stmt.executeUpdate(insertStr);
                    insertStr = "UPDATE patient SET firstname=" + quotate(dlg.getFirstname()) + "WHERE id =" + id;
                    done = stmt.executeUpdate(insertStr);
                    insertStr = "UPDATE patient SET surname=" + quotate(dlg.getSurname()) + "WHERE id =" + id;
                    done = stmt.executeUpdate(insertStr);
                    insertStr = "UPDATE patient SET birthday=" + quotate(dlg.getBirthday()) + "WHERE id =" + id;
                    done = stmt.executeUpdate(insertStr);
                    insertStr = "UPDATE patient SET diagnosis=" + quotate(dlg.getDiagnosis()) + "WHERE id =" + id;
                    done = stmt.executeUpdate(insertStr);
                    patientTable.setModel(new PatientTableModel(getResultFromPatient()));
                    revalidate();
                    message.setText("Patient with id "+id+" updated!");
                } catch (Exception exception) {
                    message.setText("Error! Patient with id "+id+" didn't update!");
                }

            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = (String) patientTable.getValueAt(patientTable.getSelectedRow(), 0);
                String insertStr = "";
                try {
                    insertStr = "DELETE FROM patient WHERE id=" + id;
                    int done = stmt.executeUpdate(insertStr);
                    message.setText("1 row deleted");
                    patientTable.setModel(new PatientTableModel(getResultFromPatient()));
                    revalidate();
                } catch (Exception exception) {
                    message.setText("Error occured in deleting data");
                }
            }
        });
        edit.setEnabled(false);
        delete.setEnabled(false);
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

        patientTable.setModel(new PatientTableModel(getResultFromPatient()));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    mdbc.close(stmt.getResultSet());
                    mdbc.destroy();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstnameText = firstname.getText();
                String lastnameText = lastname.getText();
                String surnameText = surname.getText();
                String birthdayText = birthday.getText();
                String diagnosisText = diagnosis.getText();
                try {
                    String insertStr = "Insert into patient(firstname, lastname, surname, birthday, diagnosis) values (" + quotate(firstnameText) + "," + quotate(lastnameText) + "," + quotate(surnameText) + "," + quotate(birthdayText) + "," + quotate(diagnosisText) + ");";
                    stmt.executeUpdate(insertStr);
                    message.setText("1 row inserted");
                    patientTable.setModel(new PatientTableModel(getResultFromPatient()));
                    revalidate();

                } catch (Exception exception) {
                    message.setText("Error occured in inserting data");
                }
            }
        });
        patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(patientTable.getSelectedRowCount() > 0)
                {
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                }
                else
                {
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                }

            }
        });
    }

    public static void main(String[] args) {
        InsertPatient dialog = new InsertPatient();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private String quotate(String text) {
        return "'" + text + "'";
    }

    public ResultSet getResultFromPatient() {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM patient");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}

