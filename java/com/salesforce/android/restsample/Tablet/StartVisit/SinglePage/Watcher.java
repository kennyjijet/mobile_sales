package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pannikar on 9/1/16 AD.
 */
public class Watcher implements TextWatcher {

    TextView output;
    EditText ed;
    Context mContext;
    int position;
    public ArrayList<String> arrOnShelf = new ArrayList<String>();

    public Watcher(Context mContext){
        this.mContext = mContext;
    }

    Watcher(Context mContext, TextView output){
        this.mContext = mContext;
        this.output = output;
    }

    public Watcher(Context mContext, EditText ed){
        this.mContext = mContext;
        this.ed = ed;
    }

    public Watcher(Context mContext, EditText ed, ArrayList<String> arrOnShelf, int position){

        this.mContext = mContext;
        this.ed = ed;
        this.arrOnShelf = arrOnShelf;
        this.position = position;
        Log.e("test", "getView:_position:_1-1: " + position);
        Log.e("test", "getView:_position:_1-2: " + arrOnShelf.get(position));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        output.secolo;
//        arrOnShelf[position] = s.toString();

        arrOnShelf.add(position, s.toString()); // [position] = s.toString();
//        ed.setText(arrOnShelf.get(position));

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
