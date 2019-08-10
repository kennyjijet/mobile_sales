package com.salesforce.android.restsample.Tablet.Detail.TabletContact;

import android.app.Activity;
import android.app.Fragment;
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

import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer1ActivityAddnew;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer1ActivityAddnewCustomer2;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer3ContactDetail;
import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/20/16 AD.
 */
public class TabletCustomer2DetailContacts extends Fragment implements View.OnClickListener,
                                                                        SearchView.OnQueryTextListener,
                                                                        SearchView.OnCloseListener{
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    final static String TAG = "SQLite";

    TextView tvCreateplan;
    AccountDatabaseHelper mDBHelper;

    private ListView mListView;
    private SearchView searchView;

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

        View view = inflater.inflate(R.layout.detail_activity_contact, container, false);
        // Initialize Views
        setLayout(view);

        // set Detail

        tvCreateplan = (TextView) view.findViewById(R.id.tvNewCreateContact); // tvcreatenewplan
        tvCreateplan.setTypeface(fontThSarabunBold);
        tvCreateplan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent i = new Intent(Customer2DetailContacts.this, Customer1ActivityAddnew.class);
//                startActivity(i);
            }
        });

        TextView goBack = (TextView) view.findViewById(R.id.goBack);
        goBack.setTypeface(fontThSarabunBold);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Customer2DetailContacts.this, Customer1ActivityAddnewCustomer2.class);
//                startActivity(i);
            }
        });


        // Fill the form for Search
        searchView = (SearchView) view.findViewById(R.id.search);
//        searchView.setOnQueryTextListener(getActivity());
//        searchView.setOnCloseListener(getActivity());

        mListView = (ListView) view.findViewById(R.id.list);
        mListView.setTextFilterEnabled(true);

        mDBHelper = new AccountDatabaseHelper(getActivity());
        mDBHelper.openDatabase();

        return view;
    }

    private void setLayout(View view) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(fontThSarabunBold);
    }
    public interface Communicator {
        public void MessageAddressDone(String OS_Name);
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
        communicator.MessageAddressDone(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }

    // set Detail
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
//        Cursor cursor = mDBHelper.searchCustomer2withID(query);

        if (cursor == null) {
            //
        } else {

            String[] from = new String[] {  mDBHelper.COLUMN_NAME,
                                            mDBHelper.COLUMN_NUMBER};

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
                    Cursor cursor = (Cursor) mListView.getItemAtPosition(position);

                    String get2Id = cursor.getString(cursor.getColumnIndexOrThrow("acct_id"));
                    String get0name = cursor.getString(cursor.getColumnIndexOrThrow("acct_name"));
                    String get1number = cursor.getString(cursor.getColumnIndexOrThrow("acct_number"));

//                    Intent i = new Intent(Customer2DetailContacts.this, Customer3ContactDetail.class);
//                    i.putExtra("getName", get0name);
//                    i.putExtra("getNumber", get1number);
//                    i.putExtra("getId", get2Id);
//                    startActivity(i);

                }
            });
        }
    }
}
