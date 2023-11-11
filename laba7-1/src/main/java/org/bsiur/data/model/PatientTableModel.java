package org.bsiur.data.model;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientTableModel extends AbstractTableModel {

    private final String[] colNames =
            {
                    "id", "firstname","surname","lastname","birthday", "diagnosis"
            };

    private ArrayList<String[]> ResultSets = new ArrayList<>();

    public PatientTableModel(ResultSet rs) {
        ResultSets=new ArrayList<>();
        try
        {
            while(rs.next())
            {
                String[] row =
                        {
                                rs.getString("id"), rs.getString("firstname"),
                                rs.getString("surname"), rs.getString("lastname"),
                                rs.getString("birthday"), rs.getString("diagnosis")
                        };
                ResultSets.add(row);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in PatientTableModel");
        }

    }

    @Override
    public int getRowCount() {
        return ResultSets.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = ResultSets.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public String getColumnName(int param)
    {
        return colNames[param];
    }

}