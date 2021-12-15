package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StickerScreen extends JFrame{
    private JPanel mainPanel;
    private JTextArea stickerTextArea;
    private JButton printStickerButton;
    private JPanel stickerButtonPanel;

    private ArrayList<ReadingBook> readingBooksArrayList;
    private DefaultTableModel modelTable;
    private JTable table1;

    public StickerScreen() {
        add(mainPanel);
        setSize(500,500);
        setTitle("Sticker Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        printStickerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(stickerButtonPanel,
                            "Sticker is sent to printer.",
                            "Sending Message",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public StickerScreen(String stickerText, DefaultTableModel modelTable, JTable table1 ) {
        add(mainPanel);
        setSize(500,400);
        setTitle("Sticker Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.readingBooksArrayList = readingBooksArrayList;
        this.modelTable = modelTable;
        this.table1 = table1;
        stickerTextArea.setText(stickerText);
        printStickerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(stickerButtonPanel,
                        "Sticker is sent to printer.",
                        "Sending Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


}
