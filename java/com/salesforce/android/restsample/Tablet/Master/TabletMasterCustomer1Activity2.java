package com.salesforce.android.restsample.Tablet.Master;

import android.app.Activity;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.salesforce.android.restsample.BuildConfig;
import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.itemForNameAndCode.EntryAdapterNameCode;
import com.salesforce.android.restsample.ITEMs.itemForNameAndCode.EntryItemNameCode;
import com.salesforce.android.restsample.ITEMs.itemForNameAndCode.ItemNameCode;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2Detail;
import com.salesforce.android.restsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/13/16 AD.
 */

public class TabletMasterCustomer1Activity2 extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView toolbar_title;
    private TextView tvcreatenewdev;
    private LinearLayout createNewAccount;
    private LinearLayout LnLeft;
    private AccountDatabaseHelper mDBHelper;
    private List<Account> getAccount;
    private SearchView searchView2;
    private ListView mListView;
    private ArrayList<ItemNameCode> itemNameCodes = new ArrayList<ItemNameCode>();

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

        View view = inflater.inflate(R.layout.tablet_master_customer_activity1_addnew2, container, false);

        Log.e("TabletMasterCustomer1Ac", "chkk_get2Id:position:_tablet_master_customer_activity1_addnew2");

        // Initialize Views
        setLayout(view);

        tvgoBack.setOnClickListener(this);
        LnLeft.setOnClickListener(this); // back by Linear
        tvcreatenewdev.setOnClickListener(this);
        createNewAccount.setOnClickListener(this); // create by Linear

        // set Detail
        mDBHelper = new AccountDatabaseHelper(getActivity());
        mDBHelper.openDatabase();
        getAccount = mDBHelper.getListAccount();

        // Fill the form for Search
        searchView2 = (SearchView) view.findViewById(R.id.search);

        mListView = (ListView) view.findViewById(R.id.list);
        mListView.setTextFilterEnabled(true);


        // add item in ListView Start
        for(int i=0; i<getAccount.size();i++){
            itemNameCodes.add(new EntryItemNameCode(getAccount.get(i).getName(),
                    getAccount.get(i).getNumber()));
        }

        EntryAdapterNameCode entry = new EntryAdapterNameCode(getActivity(), itemNameCodes);
        mListView.setAdapter(entry);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                String get2Id = getAccount.get(position).getId();
////                String get0name = getAccount.get(position).getName();
////                String get1number = getAccount.get(position).getNumber();
////
////                Intent i = new Intent(Customer1Activity2.this, Customer2Detail.class);
////                i.putExtra("getName", get0name);
////                i.putExtra("getNumber", get1number);
////                i.putExtra("getId", get2Id);
////                startActivity(i);
//
//            }
//        });

        // try to set mListView.setOnItemClickListener
        mListView.setOnItemClickListener(this);

        return view;
    }

    private void setLayout(View view) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView)view.findViewById(R.id.toolbar_title);
        tvcreatenewdev = (TextView) view.findViewById(R.id.tvcreatenewdev);
        createNewAccount = (LinearLayout) view.findViewById(R.id.createNewAccount);

        LnLeft = (LinearLayout) view.findViewById(R.id.LnLeft);

        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
        tvcreatenewdev.setTypeface(fontThSarabun);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.e("TabletMasterCustomer1Ac", "chkk_get2Id:position:_" + position);
        String get2Id = getAccount.get(position).getId();
        String get0name = getAccount.get(position).getName();
        String get1number = getAccount.get(position).getNumber();

        Log.e("TabletMasterCustomer1Ac", "chkk_get2Id:_1" + get2Id);
        Log.e("TabletMasterCustomer1Ac", "chkk_get2Id:_2" + get0name);
        Log.e("TabletMasterCustomer1Ac", "chkk_get2Id:_3" + get1number);
        communicator.MessageCustomerItem(get2Id, get0name, get1number);
    }

    public interface Communicator {
        public void MessageCustomer(String OS_Name);
        public void MessageCustomerItem(String get2Id,String  get0name,String  get1number);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
            case R.id.tvcreatenewdev:
                updateFragment("createNewCustomer");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageCustomer(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}

