package com.teksen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> StickerScreen Class </h1>
 * The StickerScreen program implements that
 * creates an instance variable to display the frame includes
 * text area and button. This class creates a functionality for
 * form of the screen.
 *
 * This screen shows selected item's values in formatted string form.
 * Also has print button to print sticker. (Functionality of that
 * button can be added later)
 *
 * @author Unat Teksen - 20181701048
 * @author Humeyra Dogus -20181701057
 * Computer Engineering
 * @version 1.0
 * @since 24-12-2021
 */
public class StickerScreen extends JFrame{
    private JPanel mainPanel; // panel instance variable
    private JTextArea stickerTextArea; // text area instance variable
    private JButton printStickerButton; // button instance variable
    private JPanel stickerButtonPanel; // panel instance variable

    private DefaultTableModel modelTable; //table model i.v.
    private JTable table1; //table i.v.

    /**
     * StickerScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     *
     */
    public StickerScreen() {
        add(mainPanel); // add panel to the frame
        setSize(500,500);  // set initial size
        setTitle("Sticker Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner

        printStickerButton.addActionListener(new ActionListener() {
            /**
             * Print Sticker Button
             * When this button is pressed, it shows information message
             * that displays sticker is sent to the printer.
             * @param e is the pressing event.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(stickerButtonPanel,
                            "Sticker is sent to printer.",
                            "Sending Message",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * StickerScreen Constructor
     * This constructor adds the components of form to the frame and has
     * listener for buttons. Each listener has its own variable declaration anda
     * assigning and also call to a function for related operation.
     * @param stickerText is the text to be displayed in screen
     * @param modelTable is the table model for articles
     * @param table1 table of the previous frame.
     */
    public StickerScreen(String stickerText, DefaultTableModel modelTable, JTable table1 ) {
        add(mainPanel); // add panel to the frame
        setSize(500,500);  // set initial size
        setTitle("Sticker Screen"); // set title for frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// enable to close window from x in the top right corner
        this.modelTable = modelTable; // assign table model
        this.table1 = table1; // assign table
        stickerTextArea.setText(stickerText); // display text

        printStickerButton.addActionListener(new ActionListener() {
            /**
             * Print Sticker Button
             * When this button is pressed, it shows information message
             * that displays sticker is sent to the printer.
             * @param e is the pressing event.
             */
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
