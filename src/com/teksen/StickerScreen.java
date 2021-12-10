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

        // CALISMIYOR
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
    }

    /*
    @Override
    public String createSticker(DefaultTableModel modelTable, ArrayList<Digital> digitalArrayList) {
        if(table1.getSelectedRowCount() ==1) {
            int selectedRowNo = table1.getSelectedRow();
            //System.out.println("selectedrowid not int: "+ Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0))));
            int selectedRowID = Integer.parseInt(String.valueOf(modelTable.getValueAt(selectedRowNo, 0)));


            String stickerText ="";
            for(int i=0; i <readingBooksArrayList.size(); i++) {
                if ( readingBooksArrayList.get(i).getId() == selectedRowID) {
                    System.out.println(readingBooksArrayList.get(i).getTitle()+"-"+selectedRowID);
                    stickerText = String.format(
                                    "%-25s%-35s\n%-25s%-35s\n%-21s%-35s\n%-23s%-35s\n%-23s%-35s\n"+
                                    "%-23s%-35s\n%-21s%-35s\n%-19s%-35s\n%-24s%-35s\n%-23s%-35s\n"+
                                    "%-16s%-35s\n%-23s%-35s\n%-22s%-35s"
                                    ,
                            "ID:",readingBooksArrayList.get(i).getId(),
                            "Title:",readingBooksArrayList.get(i).getTitle(),
                            "Location:",readingBooksArrayList.get(i).getLocationInformation(),
                            "Type:",readingBooksArrayList.get(i).getItemType(),
                            "Status:",readingBooksArrayList.get(i).getStatus(),

                            "Author:",readingBooksArrayList.get(i).getAuthor(),
                            "Publisher:",readingBooksArrayList.get(i).getPublisher(),
                            "Language:",readingBooksArrayList.get(i).getLanguage(),
                            "Year:",readingBooksArrayList.get(i).getYear(),
                            "Edition:",readingBooksArrayList.get(i).getEdition(),

                            "Page Number:",readingBooksArrayList.get(i).getPageNumber(),
                            "ISBN:",readingBooksArrayList.get(i).getISBN(),
                            "Genre:",readingBooksArrayList.get(i).getGenre()


                            );
                }
            }

            //System.out.println(stickerText);
            stickerTextArea.setText(stickerText);

        }



    }

     */
}
