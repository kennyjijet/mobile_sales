package com.salesforce.android.restsample.Tablet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pannikar on 8/24/16 AD.
 */
public class DataHelper implements Serializable {

    private ArrayList<String> floors;

    public DataHelper(ArrayList<String> floors) {
        this.floors = floors;
    }

    public ArrayList<String> getList() {
        return this.floors;
    }
}

