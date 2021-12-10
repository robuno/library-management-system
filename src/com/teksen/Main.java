package com.teksen;

import javax.swing.*;

public class Main {

    public static <Items> void main(String[] args) {
	    //System.out.println("aaa");
	    //Book x1 = new Book(1,"aa","loc1","unat","pearson",
        //        "tr","ii",2014,4,1222,3563535);
        //System.out.println(x1);

        // 1 readingbook, 2 textbook, 3 article, 4 e-readingbook, 5e-textbook, 6 digital, 7 journals
        /*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                form1 f = new form1();
                f.setVisible(true);


            }
        });

         */

        /*
        try {
            File myObj = new File("items.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainPage s = new mainPage();
                s.setVisible(true);


            }
        });
 */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                mainPage itScreen = new mainPage();
                itScreen.setVisible(true);


                //ReadingBookScreen rBScreen = new ReadingBookScreen();
                //rBScreen.setVisible(true);

                //LoanItemScreen lIScreen = new LoanItemScreen();
                //lIScreen.setVisible(true);

                //DigitalScreen digiScreen = new DigitalScreen();
                //digiScreen.setVisible(true);




            }
        });








    }
}
