package com.salesforce.android.restsample.Tablet.Detail.TabletCustomer;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.adapters.adapter.ListAddressBillToAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 8/26/16 AD.
 */
public class TabletCustomer2DetailBillTo extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener,
        SearchView.OnQueryTextListener,
        SearchView.OnCloseListener{

//        extends ActionBarActivity implements SearchView.OnQueryTextListener,
//        SearchView.OnCloseListener {


    private Communicator communicator;
    TextView tvCreateplan, tvBack, tvAddAddress;

    ListView lvAddress;
    ListAddressBillToAdapter adapter;
    List<Address> mAddressList;
    AddressDatabaseHelper mDBHelper;

    String newName;
    String newId;
    String newNumber;
    String newAcctId;
    String getPosition;

    SearchView searchView;
//    EditText searchView;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.plan_delivery_address);

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

        View v = inflater.inflate(R.layout.detail_plan_delivery_address, container, false);

        newName= getArguments().getString("getName");
        newId= getArguments().getString("getId");
        newNumber = getArguments().getString("getNumber");
        Log.e("OrderDeliveryActivity", "Tab2FragmentALL3: Id: " + newId);

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
        lvAddress = (ListView) v.findViewById(R.id.listview_plan);
        tvCreateplan = (TextView) v.findViewById(R.id.tvcreatenewdev);
        tvBack = (TextView) v.findViewById(R.id.tvgoBack);
        //tvNewCreateAddress
        tvAddAddress = (TextView) v.findViewById(R.id.tvNewCreateAddress);
        tvAddAddress.setTypeface(fontThSarabunBold);
        tvAddAddress.setText("   Add bill to address...");
        tvAddAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent i = new Intent(Customer2DetailBillTo.this, Customer1ActivityAddnew.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });
        tvBack.setTypeface(fontThSarabunBold);

        getActivity().setTitle(null);
        Toolbar topToolBar = (Toolbar)v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setText("Bill To");
        mTitle.setTypeface(fontThSarabunBold);
        mDBHelper = new AddressDatabaseHelper(getActivity());

        //Get product list in db when db exists
        mAddressList = mDBHelper.getListAddressSearchByIdBillTo(newId);//
        Log.e("OrderDeliveryActivity", "Tab2FragmentALL3: size: " + mAddressList.size());

        List<Address> listFinal = new ArrayList<Address>();

        for(int position=0; position<mAddressList.size(); position++){
            String getName = mAddressList.get(position).getName(); //getPdbrid
            String getStreet = mAddressList.get(position).getStreet(); //getPdbrid
            String getState = mAddressList.get(position).getState(); //getPdbrid
            int getType = mAddressList.get(position).getType();
            listFinal.add(mAddressList.get(position));

//            if(getType == 0){
//                listFinal.add(mAddressList.get(position));
//                Log.e("position", "Customer2DetailBillTo:_position:_" + position);
//                Log.e("position", "Customer2DetailBillTo:_getName:_" + getName);
//
//            }
        }

        //Init adapter
        adapter = new ListAddressBillToAdapter(getActivity(), listFinal);

        //Set adapter for listview
        lvAddress.setAdapter(adapter);

        lvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getPosition = mAddressList.get(position).getId();
                newAcctId = mAddressList.get(position).getId();
//                Intent i = new Intent(Customer2DetailBillTo.this, Customer4AddressDetail.class);
//                i.putExtra("getIdAdd", newAcctId);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });
        lvAddress.setOnItemClickListener(this);



        ImageView goBack = (ImageView) v.findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Customer2DetailBillTo.this, Customer2Detail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });

        tvBack.setOnClickListener(this);

        // Fill the form for Search
        searchView = (SearchView) v.findViewById(R.id.search);
//        searchView = (EditText) findViewById(R.id.search);//EditText
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        mDBHelper = new AddressDatabaseHelper(getActivity());
        mDBHelper.openDatabase(); // add to th
//        showResults("");

        return v;
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

        //Cursor cursor = mDBHelper.searchCustomer2(query);
        Cursor cursor = mDBHelper.searchCustomer2withID(query, newId, 0);


        if (cursor == null) {
            //
        } else {

            String[] from = new String[] {  mDBHelper.COLUMN_NAME,
                    mDBHelper.COLUMN_TYPE,
                    mDBHelper.COLUMN_STREET,
                    mDBHelper.COLUMN_STATE};

            // Specify the Corresponding layout elements where we want the columns to go
            int[] to = new int[] {R.id.tv_name, R.id.tv_number,
                    R.id.tv_street, R.id.tv_state };

            // Create a simple cursor adapter for the definitions and apply them to the ListView
            //SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.item_customer_listview, cursor, from, to);
            SimpleCursorAdapter customers = new SimpleCursorAdapter(getActivity(),R.layout.item_shipto_listview, cursor, from, to);
//            mListView.setAdapter(customers);
            lvAddress.setAdapter(customers);

//            lvAddress.setVisibility(View.INVISIBLE);

            Log.e(" Check", "customers.toString(): " + customers.convertToString(cursor));

            // Define the on-click listener for the list items
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            lvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set
                    //Cursor cursor = (Cursor) mListView.getItemAtPosition(position);
                    Cursor cursor = (Cursor) lvAddress.getItemAtPosition(position);


                    String get2Id = cursor.getString(cursor.getColumnIndexOrThrow("addr_id"));
                    String get0name = cursor.getString(cursor.getColumnIndexOrThrow("addr_name"));
//                    String get1number = cursor.getString(cursor.getColumnIndexOrThrow("addr_country"));

//                    Intent i = new Intent(Customer2DetailBillTo.this, Customer4AddressDetail.class);
//                    i.putExtra("getName", get0name);
//                    i.putExtra("getOldIDBack", newId);
//                    i.putExtra("getId", get2Id);
//                    startActivity(i);

                }
            });
        }
    }

    public interface Communicator {
        public void MessageCustomer2DetailBillToBack(String get2Id, String  get0name, String  get1number);
        public void MessageCustomer2DetailBillTo(String idBillTo, String get2Id,String  get0name,String  get1number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tvgoBack:
                communicator.MessageCustomer2DetailBillToBack(newId, newName, newNumber);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        newAcctId = mAddressList.get(position).getId();
        communicator.MessageCustomer2DetailBillTo(newAcctId, newId, newName, newNumber);
    }
}

