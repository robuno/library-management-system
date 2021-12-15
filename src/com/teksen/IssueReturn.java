package com.teksen;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public interface IssueReturn {
    void pressDeleteDigitalButton(DefaultTableModel modelTable, ArrayList<ArrayList<String>> itemsArrayList);
    void pressAddDigitalButton(DefaultTableModel modelTable,  int lastID);
}
