package com.teksen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form1 extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JTextField textField3;

    public form1() {
        add(panel1);
        setSize(400,200);
        setTitle("aaaaxxxx");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                String ad, soyad;
                ad = textField1.getText();
                soyad = textField2.getText();
                System.out.println(ad+" "+soyad);*/

                String text1 = textField1.getText();
                String text2 = textField2.getText();
                double result = Double.parseDouble(text1) + Double.parseDouble(text2);
                textField3.setText(result+"");
                System.out.println(result);

            }
        });
    }
}
