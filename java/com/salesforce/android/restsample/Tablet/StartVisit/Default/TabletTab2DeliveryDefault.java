package com.salesforce.android.restsample.Tablet.StartVisit.Default;

/**
 * Created by pannikar on 10/2/16 AD.
 */
//public class TabletTab2DeliveryDefault {
//}


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;

/**
 * Created by chukeatboontae on 9/30/16.
 */
public class TabletTab2DeliveryDefault extends Fragment {
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommunicatorV4) {
            communicatorV4 = (CommunicatorV4) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
//            throw new ClassCastException(activity.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tablet_delivery_default_2, container, false);
        // Initialize Views
        setLayout(view);

//        tvgoBack.setOnClickListener(this);

        return view;
    }

    private void setLayout(View view) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);

//        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
    }
}

