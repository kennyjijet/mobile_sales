package com.salesforce.android.restsample.Tablet.Detail.TabletContact;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.adapters.ListContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 8/24/16 AD.
 */
public class TabletContactActivityFromDetail extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Communicator communicator;
    ListView lvContact;
    ListContactAdapter adapter;
    List<Contact> mContactList;
    ContactDatabaseHelper mDBHelper;

    TextView tvAddContact;

    String newId, newName, newNumber;

    //private ListView mListView;
    SearchView searchView;
    String getPosition;
    int position;
    private TextView goBack;

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

//        setContentView(R.layout.activity_contact);
//        View v = inflater.inflate(R.layout.activity_contact, container, false);
        View v = inflater.inflate(R.layout.activity_contact, container, false);

        // i.putExtra("getId", get0Id);
//        Bundle extras = getIntent().getExtras();
        newName= getArguments().getString("getName");//
        newId= getArguments().getString("getId");
        newNumber = getArguments().getString("getNumber");
        Log.e("position", "ContactActivityFromDetail: newName:_" + newName);

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
        lvContact = (ListView) v.findViewById(R.id.listview_contact);

        tvAddContact = (TextView) v.findViewById(R.id.tvNewCreateContact);
        tvAddContact.setTypeface(fontThSarabunBold);
        tvAddContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent i = new Intent(ContactActivityFromDetail.this, Customer1ActivityAddnew.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });

        goBack = (TextView) v.findViewById(R.id.goBack);
        goBack.setTypeface(fontThSarabunBold);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(ContactActivityFromDetail.this, Customer2Detail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i); // stopship
            }
        });

        goBack.setOnClickListener(this);

        LinearLayout lnLeft = (LinearLayout) v.findViewById(R.id.LnLeft);
        lnLeft.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(ContactActivityFromDetail.this, Customer2Detail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i); // stopship
            }
        });

//        setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setTypeface(fontThSarabunBold);
        mDBHelper = new ContactDatabaseHelper(getActivity());

        mContactList = mDBHelper.getListContactSearchById(newId);
        Log.e("position", "ContactActivityFromDetail: mContactList.size():_" + mContactList.size());


        List<Contact> listFinal = new ArrayList<Contact>();

        for(int position=0; position<mContactList.size(); position++){
            String getName = mContactList.get(position).getFirstname(); //getPdbrid
            String getStreet = mContactList.get(position).getLastname(); //getPdbrid
            String getState = mContactList.get(position).getPosition(); //getPdbrid
//            int getType = mContactList.get(position).getType();
            Log.e("position", "ContactActivityFromDetail:_position:_" + position);
            Log.e("position", "ContactActivityFromDetail:_getName:_" + getName);
            listFinal.add(mContactList.get(position));

//            if(getType == 0){
//                listFinal.add(mContactList.get(position));
//            }
        }
        //Init adapter
        // adapter = new ListAddressBillToAdapter(this, mAddressList);
        adapter = new ListContactAdapter(getActivity(), listFinal);

        //Set adapter for listview
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                getPosition = mContactList.get(position).getId();
//                Log.e("position", "ContactActivityFromDetail:_getPosition:_" + getPosition);

//                Intent i = new Intent(ContactActivityFromDetail.this, Customer3ContactDetail.class);
//                i.putExtra("getIdContact", getPosition);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });

        lvContact.setOnItemClickListener(this);

        // Fill the form for Search
        searchView = (SearchView) v.findViewById(R.id.search);
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(getActivity());
//        searchView.setOnCloseListener(this);

        //mListView = (ListView) findViewById(R.id.list);
        //mListView.setTextFilterEnabled(true);

        mDBHelper = new ContactDatabaseHelper(getActivity());
        mDBHelper.openDatabase();
//        showResults(" ");

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
    }

    public interface Communicator {
        public void MessageContactFromDetailBack(String get2Id,String  get0name,String  get1number);
        public void MessageContactFromDetail(String idContact, String get2Id,String  get0name,String  get1number);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.goBack:
                communicator.MessageContactFromDetailBack(newId, newName, newNumber);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getPosition = mContactList.get(position).getId();

        communicator.MessageContactFromDetail(getPosition, newId, newName, newNumber);
    }
}
