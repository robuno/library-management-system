package com.teksen;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
/**
 * <h1> Sticker Interface! </h1>
 *
 * This Sticker interface will be used in classes that displays items and
 * when items/users sticker are required.
 * @author Humeyra Dogus - Unat Teksen
 * @version 1.0
 * @since 2021-12-25
 *
 */
public interface Sticker {
    /**
     * Create Sticker Function
     * This function creates Sticker Text.
     * @param modelTable is the table model
     * @param allItems is the list of items
     * @return sticker text in String
     */
    String createSticker(DefaultTableModel modelTable,ArrayList<ArrayList<String>> allItems);
}
