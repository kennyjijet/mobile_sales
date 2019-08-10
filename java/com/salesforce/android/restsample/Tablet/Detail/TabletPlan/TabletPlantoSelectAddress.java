package com.salesforce.android.restsample.Tablet.Detail.TabletPlan;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer3ContactDetail;
import com.salesforce.android.restsample.MainPLANs.PlanActivity1toSelectAfter2;
import com.salesforce.android.restsample.MainPLANs.adapter.ListSelectAddressAdapter;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.tab.ListCustomerAdapter;

import java.util.List;

/**
 * Created by pannikar on 7/21/16 AD.
 */
public class TabletPlantoSelectAddress extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener  {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView toolbar_title;

    Context mContext;

    ListView lvAddress;
//    ListCustomerAdapter adapter;
//    List<Account> mProductList;
//    AccountDatabaseHelper mDBHelper;

    ListSelectAddressAdapter adapter;
    List<Address> mAddressList;
    AddressDatabaseHelper mDBHelper;


    private ListView mListView;
    private SearchView searchView;

    private String valueId;
    private Cursor cursor;


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

        //Retrieve the value
        valueId = getArguments().getString("getId");
        Log.e("TabletCustomer2Detail","Detail_value:_3_" + valueId);

        View v = inflater.inflate(R.layout.master_plan_delivery_address_select, container, false);


        // Initialize Views
        setLayout(v);
        tvgoBack.setOnClickListener(this);

        return v;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabun);

        // add detail abouot List
        // Fill the form for Search
//        searchView = (SearchView) v.findViewById(R.id.search);
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setOnCloseListener(this);

        lvAddress = (ListView) v.findViewById(R.id.list);
        lvAddress.setTextFilterEnabled(true);

//        mDBHelper = new AccountDatabaseHelper(getActivity());
//        mDBHelper.openDatabase();
//        showResults("");

        // set Detail

//        setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setTypeface(fontThSarabunBold);
        mDBHelper = new AddressDatabaseHelper(getActivity());

        //Get product list in db when db exists
        mAddressList = mDBHelper.getListAddressSearchById(valueId);

        //Init adapter
        adapter = new ListSelectAddressAdapter(getActivity(), mAddressList);

        Log.e(" PlanActivity1 ", "PlanActivity1:_newId:_" + valueId);

        //Set adapter for listview
        lvAddress.setAdapter(adapter);

        lvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String idAccount = mAddressList.get(position).getAcctid();
//                String nameAccount = mAddressList.get(position).getName();
//                String idAddress = mAddressList.get(position).getId();
////                Intent i = new Intent(PlanActivity1toSelectAddress.this, PlanActivity1toSelectAfter.class); //OrderDeliveryDetailActivity
//                Intent i = new Intent(PlanActivity1toSelectAddress.this, PlanActivity1toSelectAfter2.class);
//                i.putExtra("getId", idAccount);
//                i.putExtra("getName", nameAccount);
//                i.putExtra("getIdAddress", idAddress);
//                Log.e("position", "Chk_select_Address:_id:_" + idAccount);
//                Log.e("position", "Chk_select_Address:_name:_" + nameAccount);
//                Log.e("position", "Chk_select_Address:_idAddress:_" + idAddress);
//                Log.e("position", "mAddressList.get(position).getName():_" + mAddressList.get(position).getName());
////                Log.e("position", "mAddressList.get(position).getName():_" + mAddressList.get(position).get);
//                startActivity(i);
            }
        });
        lvAddress.setOnItemClickListener(this);


    }
    // search Text

    public boolean onQueryTextChange(String newText) {
        //showResults(newText + "*");
        showResults(newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
//        showResults(query + "*");
        showResults(query);
        return false;
    }

    public boolean onClose() {
        showResults("");
        return false;
    }

    private void showResults(String query) {

        Cursor cursor = mDBHelper.searchCustomer2(query);

        if (cursor == null) {
            //
        } else {

            String[] from = new String[] {
                    mDBHelper.COLUMN_NAME,
                    mDBHelper.COLUMN_COUNTRY};

            // Specify the Corresponding layout elements where we want the columns to go
            int[] to = new int[] {R.id.tv_name, R.id.tv_number};

            // Create a simple cursor adapter for the definitions and apply them to the ListView
            SimpleCursorAdapter customers = new SimpleCursorAdapter(getActivity(),R.layout.item_customer_listview, cursor, from, to);
            mListView.setAdapter(customers);

            Log.e(" Check", "customers.toString(): " + customers.convertToString(cursor));

            // Define the on-click listener for the list items
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set
//                    Cursor cursor = (Cursor) mListView.getItemAtPosition(position);
//
//                    String get2Id = cursor.getString(cursor.getColumnIndexOrThrow("acct_id"));
//                    String get0name = cursor.getString(cursor.getColumnIndexOrThrow("acct_name"));
//                    String get1number = cursor.getString(cursor.getColumnIndexOrThrow("acct_number"));
//
//                    Intent i = new Intent(PlanActivity1toSelectAddress.this, Customer3ContactDetail.class);
////                    Intent i = new Intent(Customer2DetailContacts.this, Customer2Detail.class);
//                    i.putExtra("getName", get0name);
//                    i.putExtra("getNumber", get1number);
//                    i.putExtra("getId", get2Id);
//                    startActivity(i);

                }
            });
            mListView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        cursor = (Cursor) mListView.getItemAtPosition(position); //lvAddress
////        cursor = (Cursor) lvAddress.getItemAtPosition(position); //lvAddress
//        String get0Id = cursor.getString(cursor.getColumnIndexOrThrow("acct_id"));
//        String get0name = cursor.getString(cursor.getColumnIndexOrThrow("acct_name"));
//        String get1number = cursor.getString(cursor.getColumnIndexOrThrow("acct_number"));
//        communicator.MessagetoSelectAddressId(get0Id, get0name, get1number);

                String get0Id = mAddressList.get(position).getAcctid();
                String get0name = mAddressList.get(position).getName();
                String idAddress = mAddressList.get(position).getId();
                communicator.MessagetoSelectAddressId(get0Id, get0name, idAddress);

    }

    public interface Communicator {
        public void MessagetoSelectAddress(String OS_Name);
        public void MessagetoSelectAddressId(String id, String name, String number);
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
        communicator.MessagetoSelectAddress(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
