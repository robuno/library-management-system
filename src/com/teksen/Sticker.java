package com.teksen;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public interface Sticker {
    String createSticker(DefaultTableModel modelTable,ArrayList<ArrayList<String>> allItems);
}
