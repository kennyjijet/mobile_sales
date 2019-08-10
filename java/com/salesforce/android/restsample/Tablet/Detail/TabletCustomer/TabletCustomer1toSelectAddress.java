package com.salesforce.android.restsample.Tablet.Detail.TabletCustomer;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/20/16 AD.
 */
public class TabletCustomer1toSelectAddress extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;
    private TextView tvNewCreateAddress;
    //    private TextView tvcreatenewdev;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
//            throw new ClassCastException(activity.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_plan_delivery_address, container, false);
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
        toolbar_title.setTypeface(fontThSarabunBold);

        tvNewCreateAddress = (TextView) view.findViewById(R.id.tvNewCreateAddress);
        tvNewCreateAddress.setTypeface(fontThSarabunBold);
    }
    public interface Communicator {
        public void MessageAddress(String OS_Name);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
            case R.id.tvNewCreateAddress:
                updateFragment("tvNewCreateAddress");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageAddress(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
