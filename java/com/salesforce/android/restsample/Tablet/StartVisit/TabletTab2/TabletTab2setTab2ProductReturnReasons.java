package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.PlanDetailActivity;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemReturn.EntryAdapterReturn;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemReturn.EntryItemReturn;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemReturn.ItemReturn;
import com.salesforce.android.restsample.R;

import java.util.ArrayList;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setTab2ProductReturnReasons extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set Detail
    final Context context = getActivity();

    int newIdSV;
    String newId;
    String newName;
    String newDescription;

    ListView mListView;
    ArrayList<ItemReturn> itemReturns = new ArrayList<ItemReturn>();

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

        View view = inflater.inflate(R.layout.detail_activity_product_return, container, false);

//        Bundle extras = getIntent().getExtras();
//        newId = extras.getString("getId");
//        newName = extras.getString("getName");
//        newIdSV = extras.getInt("getIdSV");
//        newDescription = extras.getString("getDescription");
        newId = "00128000007uGpMAAU";
        newName = "Tensoplast 10 Piece";
        newIdSV = 2;
        newDescription = "";

        // Initialize Views
        setLayout(view);

        // set Detail
        // for this Activity


        TextView tvBack = (TextView) view.findViewById(R.id.tvgoBack);

        tvBack.setTypeface(fontThSarabun);

        tvBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Tab2ProductReturnReasons.this, PlanDetailActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                i.putExtra("Check", "2");
//                startActivity(i);
            }
        });

        String[] strings = {"Damaged","Delivery Delay","Duplicate Order",
                "Expired Item","Incorrect Delivery","Over Sypply"};

        Log.e("test", strings[0]);
        mListView = (ListView) view.findViewById(R.id.list_item_return);
//        mListView.setTextFilterEnabled(true);

        for (int i=0;i<strings.length;i++){
            itemReturns.add(new EntryItemReturn(strings[i]));
        }
        EntryAdapterReturn entry = new EntryAdapterReturn(getActivity(), itemReturns);
        mListView.setAdapter(entry);

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
    public interface Communicator {
        public void MessageBD(String OS_Name);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageBD(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
