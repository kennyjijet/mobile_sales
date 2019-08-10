package com.salesforce.android.restsample.Tablet.Detail.TabletPlan;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.HBD.CalDate;
import com.salesforce.android.restsample.HBD.EntryAdapterOrder;
import com.salesforce.android.restsample.HBD.EntryItem;
import com.salesforce.android.restsample.HBD.Item;
import com.salesforce.android.restsample.MainPLANs.PlanActivity1toSelectAddress;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.tab.ListCustomerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/21/16 AD.
 */
public class TabletPlanCustomerList extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView toolbar_title;

    Context mContext;

    ListView lvProduct;
    ListCustomerAdapter adapter;
    List<Account> mProductList;
    private List<Account> getAccount;
    AccountDatabaseHelper mDBHelper;


    private ListView mListView;
    private SearchView searchView;
    private String get0Id;
    private Cursor cursor2;


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

//        mContext = this;
        View v = inflater.inflate(R.layout.master_plan_select_customer, container, false);


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

        mListView = (ListView) v.findViewById(R.id.list);
        mListView.setTextFilterEnabled(true);

        mDBHelper = new AccountDatabaseHelper(getActivity());
        mDBHelper.openDatabase();
        showResults("");

    }

    private void showResults(String query) {

        Cursor cursor = mDBHelper.searchCustomer2(query);
        getAccount = mDBHelper.getListAccount();

        if (cursor == null) {
            //
        } else {

            String[] from = new String[] {
                    mDBHelper.COLUMN_NAME,
                    mDBHelper.COLUMN_NUMBER};

            // Specify the Corresponding layout elements where we want the columns to go
            int[] to = new int[] {R.id.tv_name,
                    R.id.tv_number};

            // Create a simple cursor adapter for the definitions and apply them to the ListView
            SimpleCursorAdapter customers = new SimpleCursorAdapter(getActivity(),R.layout.item_customer_listview, cursor, from, to);
            mListView.setAdapter(customers);

            Log.e(" Check", "customers.toString(): " + customers.convertToString(cursor));

            // Define the on-click listener for the list items
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set
//                    cursor2 = (Cursor) mListView.getItemAtPosition(position);
//                    get0Id = cursor2.getString(cursor2.getColumnIndexOrThrow("acct_id"));

//                    Intent i = new Intent(PlanActivity1toCustomerList.this, PlanActivity1toSelectAddress.class);
//                    i.putExtra("getId", get0Id);
//                    startActivity(i);

                }
            });

            mListView.setOnItemClickListener(this);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        communicator.MessageCreatePlanItem(get0Id);
//        Log.e("TabletCustomer2Detail","Detail_value:_1_" + get0Id);
        cursor2 = (Cursor) mListView.getItemAtPosition(position);
        get0Id = cursor2.getString(cursor2.getColumnIndexOrThrow("acct_id"));
        Log.e("TabletCustomer2Detail","Detail_value:_1_" + get0Id);
        communicator.MessageCreatePlanItem(get0Id);
    }

    public interface Communicator {
        public void MessageCustList(String OS_Name);
        public void MessageCreatePlanItem(String get2Id);
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
        communicator.MessageCustList(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
