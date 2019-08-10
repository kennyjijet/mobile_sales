package com.salesforce.android.restsample.Tablet.Expand;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.ByAddress;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.Master.TabletMasterCustomer1Activity2;
import com.salesforce.android.restsample.Tablet.Master.TabletMasterCustomer1Activity2.Communicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pannikar on 11/4/2016 AD.
 */

public class MainExpFragment extends Fragment {

    private Communicator communicator;

    AddressDatabaseHelper mDBHelper;
    List<ByAddress> mByAddressList;
    List<ByAddress> mByAddressListWhProv;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listDataHeaderNumber;
    HashMap<String, List<String>> listDataChild;

    List<String> listDataHeaderName;
    List<String> listDataHeaderAccId;
    HashMap<String, List<String>> listDataChildName;
    HashMap<String, List<String>> listDataChildAccId;

    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView toolbar_title;
    private TextView tvcreatenewdev;

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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.exp_activity_main, container, false);
        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

        tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        tvcreatenewdev = (TextView) v.findViewById(R.id.tvcreatenewdev);

        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
        tvcreatenewdev.setTypeface(fontThSarabun);

        /* open Database */
        mDBHelper = new AddressDatabaseHelper(getActivity());
        mByAddressList = mDBHelper.getListByAddress();


        Log.e("test ","byAddress_size:_" + mByAddressList.size());

        // preparing list data
        prepareListData();

        /* set after click Back */
        tvgoBack.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                communicator.MessageCustomer("Back");
            }
        });



        listAdapter = new ExpandableListAdapter(getActivity(),
                                                listDataHeaderNumber,
                                                // listDataHeaderAddress,
                                                listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub


//                String get2accId =  "00128000013ZFZqAAO";
//                String get0name =  "กานดาพานิชย์";
                String get2accId =  listDataChildAccId.get(listDataHeaderAccId.get(groupPosition)).get(childPosition);
                String get0name =  listDataChildName.get(listDataHeaderName.get(groupPosition)).get(childPosition);
                String get1number =  "00000";

//                Toast.makeText(
//                        getActivity(),
//                        listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) +'\n'+
//                                listDataHeaderAccId.get(groupPosition) + " : " + listDataChildAccId.get(listDataHeaderAccId.get(groupPosition)).get(childPosition)+'\n'+
//                                listDataHeaderName.get(groupPosition) + " : " + listDataChildName.get(listDataHeaderName.get(groupPosition)).get(childPosition)+'\n'+
//                                get2accId +" and " +    get0name    +" and " + get1number
//                        , Toast.LENGTH_SHORT)
//                        .show();

                communicator.MessageCustomerItem(get2accId, get0name, get1number);



                return false;
            }
        });

        return v;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataHeaderNumber = new ArrayList<String>();

        /* add Name and Id */
        listDataHeaderName = new ArrayList<String>();
        listDataHeaderAccId = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();
        listDataChildName = new HashMap<String, List<String>>();
        listDataChildAccId = new HashMap<String, List<String>>();

        /* adding child data */
        // mByAddressList.
        for(int i=0; i<mByAddressList.size(); i++){

            String provinceName = mByAddressList.get(i).getProvince();
            String provinceNumber = mByAddressList.get(i).getNumberProvince();
            if(provinceName.length()==0){
                // provinceName = "( - )";
                provinceName = "No Name ";
            }
            listDataHeader.add(provinceName);
            listDataHeaderNumber.add(provinceNumber);

            listDataHeaderName.add(provinceName);
            listDataHeaderAccId.add(provinceName);

            /* add items search by Province */
            List<String> itemWhProv = new ArrayList<String>();
            List<String> itemWhtName = new ArrayList<String>();
            List<String> itemWhtAccId = new ArrayList<String>();
            mByAddressListWhProv = mDBHelper.getListByAddressWhereProvince(provinceName);
            for (int j=0; j<mByAddressListWhProv.size();j++){
                String getItemProv = mByAddressListWhProv.get(j).getProvince();
                String getItemName = mByAddressListWhProv.get(j).getName();
                String getItemAccId = mByAddressListWhProv.get(j).getId();
                itemWhProv.add(getItemProv);
                itemWhtName.add(getItemName);
                itemWhtAccId.add(getItemAccId);
            }

            // listDataChild.put(listDataHeader.get(i), itemWhProv);
            listDataChild.put(listDataHeader.get(i), itemWhtName);

            listDataChildName.put(listDataHeaderName.get(i), itemWhtName);
            listDataChildAccId.put(listDataHeaderAccId.get(i), itemWhtAccId);
        }
    }
}

