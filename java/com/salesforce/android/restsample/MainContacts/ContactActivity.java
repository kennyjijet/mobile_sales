package com.salesforce.android.restsample.MainContacts;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.adapters.ListContactAdapter;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2Edit;
import com.salesforce.android.restsample.MainPLANs.PlanActivity4;
//import com.salesforce.android.restsample.tab.two2.adapters.inner.fragments.TakePhoto.Contact;

import java.util.List;

/**
 * Created by pannikar on 3/27/2016 AD.
 */
public class ContactActivity extends ActionBarActivity {

    ListView lvContact;
    ListContactAdapter adapter;
    List<Contact> mContactList;
    ContactDatabaseHelper mDBHelper;

    String newId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // i.putExtra("getId", get0Id);
        Bundle extras = getIntent().getExtras();
        newId= extras.getString("getId");
        Log.e("position", "Done_name: newId" + newId);

        Typeface fontThSarabun = Typeface.createFromAsset(getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getAssets(), "fonts/THSarabunBold.ttf");
        lvContact = (ListView)findViewById(R.id.listview_contact);
        TextView tvSearch = (TextView) findViewById(R.id.btnSearch);
        tvSearch.setTypeface(fontThSarabunBold);

        ImageView goBack = (ImageView) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                Intent i = new Intent(ContactActivity.this, Customer2Edit.class);
                i.putExtra("getId", newId);
                startActivity(i);
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

        //Get product list in db when db exists
        mContactList = mDBHelper.getListContactSearchById(newId);

        //Init adapter
        adapter = new ListContactAdapter(this, mContactList);

        //Set adapter for listview
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ContactActivity.this, PlanActivity4.class);
//                i.putExtra("getDescription", get2detail);
                startActivity(i);

            }
        });
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
            Toast.makeText(ContactActivity.this, "Refresh App", Toast.LENGTH_LONG).show();
        }
        if(id == R.id.action_new){
            Toast.makeText(ContactActivity.this, "Create Text", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
