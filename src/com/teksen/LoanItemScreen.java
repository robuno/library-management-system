package com.teksen;

import javax.swing.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanItemScreen extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JButton button1;

    public LoanItemScreen() {
        add(mainPanel);
        setSize(1400,700);
        setTitle("Items Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
