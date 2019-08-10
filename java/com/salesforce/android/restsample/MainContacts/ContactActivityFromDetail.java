package com.salesforce.android.restsample.MainContacts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.adapters.ListContactAdapter;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer1ActivityAddnew;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2Detail;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer3ContactDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 3/27/2016 AD.
 */
public class ContactActivityFromDetail extends ActionBarActivity implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // i.putExtra("getId", get0Id);
        Bundle extras = getIntent().getExtras();
        newName= extras.getString("getName");//
        newId= extras.getString("getId");
        newNumber = extras.getString("getNumber");
        Log.e("position", "ContactActivityFromDetail: newName:_" + newName);

        Typeface fontThSarabun = Typeface.createFromAsset(getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getAssets(), "fonts/THSarabunBold.ttf");
        lvContact = (ListView)findViewById(R.id.listview_contact);

        tvAddContact = (TextView) findViewById(R.id.tvNewCreateContact);
        tvAddContact.setTypeface(fontThSarabunBold);
        tvAddContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ContactActivityFromDetail.this, Customer1ActivityAddnew.class);
                i.putExtra("getId", newId);
                i.putExtra("getName", newName);
                i.putExtra("getNumber", newNumber);
                startActivity(i);
            }
        });

        TextView goBack = (TextView) findViewById(R.id.goBack);
        goBack.setTypeface(fontThSarabunBold);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent i = new Intent(ContactActivityFromDetail.this, Customer2Detail.class);
                i.putExtra("getId", newId);
                i.putExtra("getName", newName);
                i.putExtra("getNumber", newNumber);
                startActivity(i); // stopship
            }
        });


        LinearLayout lnLeft = (LinearLayout) findViewById(R.id.LnLeft);
        lnLeft.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent i = new Intent(ContactActivityFromDetail.this, Customer2Detail.class);
                i.putExtra("getId", newId);
                i.putExtra("getName", newName);
                i.putExtra("getNumber", newNumber);
                startActivity(i); // stopship
            }
        });

        setTitle(null);
        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setTypeface(fontThSarabunBold);
        mDBHelper = new ContactDatabaseHelper(this);

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
        adapter = new ListContactAdapter(this, listFinal);

        //Set adapter for listview
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getPosition = mContactList.get(position).getId();
                Log.e("position", "ContactActivityFromDetail:_getPosition:_" + getPosition);
//                getId = mContactList.get(position).getId();
//                int position = lv.getPositionForView(v);
                Intent i = new Intent(ContactActivityFromDetail.this, Customer3ContactDetail.class);
                i.putExtra("getIdContact", getPosition);
                i.putExtra("getId", newId);
                i.putExtra("getName", newName);
                i.putExtra("getNumber", newNumber);
                startActivity(i);
            }
        });

        // Fill the form for Search
        searchView = (SearchView) findViewById(R.id.search);
//        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        //mListView = (ListView) findViewById(R.id.list);
        //mListView.setTextFilterEnabled(true);

        mDBHelper = new ContactDatabaseHelper(this);
        mDBHelper.openDatabase();
//        showResults(" ");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blank, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_refresh){
            Toast.makeText(ContactActivityFromDetail.this, "Refresh App", Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(ContactActivityFromDetail.this, "Create Text", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
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

//        Cursor cursor = mDBHelper.searchCustomer2(query);
        Cursor cursor = mDBHelper.searchCustomer2withID(query, newId);

        if (cursor == null) {
            //
        } else {

            String[] from = new String[] {  mDBHelper.COLUMN_FIRSTNAME,
                                            mDBHelper.COLUMN_LASTNAME,
                                            mDBHelper.COLUMN_TITLE,
                                            mDBHelper.COLUMN_POSITION};

            // Specify the Corresponding layout elements where we want the columns to go
//            int[] to = new int[] {R.id.tv_name, R.id.tv_number};
            int[] to = new int[] {R.id.tv_name, R.id.tv_lastname, R.id.tv_title, R.id.tv_position};

            // Create a simple cursor adapter for the definitions and apply them to the ListView
//            SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.item_customer_listview, cursor, from, to);
            //View v = View.inflate(mContext, R.layout.item_contact_listview, null);
            SimpleCursorAdapter customers = new SimpleCursorAdapter(this,R.layout.item_contact_listview, cursor, from, to);

//            mListView.setAdapter(customers);
            lvContact.setAdapter(customers);

            Log.e(" Check", "customers.toString(): " + customers.convertToString(cursor));

            // Define the on-click listener for the list items
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set
//                    Cursor cursor = (Cursor) mListView.getItemAtPosition(position);
                    Cursor cursor = (Cursor) lvContact.getItemAtPosition(position);

                    String get2Id = cursor.getString(cursor.getColumnIndexOrThrow("cont_id"));
                    String get0name = cursor.getString(cursor.getColumnIndexOrThrow("cont_firstname"));
                    String get1number = cursor.getString(cursor.getColumnIndexOrThrow("cont_lastname"));

                    Intent i = new Intent(ContactActivityFromDetail.this, Customer3ContactDetail.class);
//                    Intent i = new Intent(Customer2DetailContacts.this, Customer2Detail.class);
                    i.putExtra("getName", get0name);
                    i.putExtra("getNumber", get1number);
                    i.putExtra("getId", get2Id);
                    startActivity(i);

                }
            });
        }
    }
}
