package com.salesforce.android.restsample.Tablet.Master;

import android.app.Activity;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.HBD.CalDate;
import com.salesforce.android.restsample.HBD.EntryAdapterOrder;
import com.salesforce.android.restsample.HBD.EntryItem;
import com.salesforce.android.restsample.HBD.HBD2Contact;
import com.salesforce.android.restsample.HBD.Item;
import com.salesforce.android.restsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/14/16 AD.
 */
public class TabletMasterHBD extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView header_text;
    private TextView toolbar_title;
    private ContactDatabaseHelper dbContact;
    private List<Contact> lsCont;
    private ArrayList<String> lsName;
    private ArrayList<String> lsDate;
    private ListView list;
    private ImageView list_item_image;
    private TextView list_item_name;
    private TextView list_item_subtitle;
    private ArrayList<Item> itemList;
    private EntryAdapterOrder entAdap;
    List<String> idCont;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tablet_master_hbd, container, false);

        // Initialize Views
        setLayout(v);
        tvgoBack.setOnClickListener(this);

//        // set Detail

        // edit set Detail
        dbContact = new ContactDatabaseHelper(getActivity());
        lsCont = dbContact.getListContact();
        lsName = new ArrayList<String>();
        lsDate = new ArrayList<String>();

        Log.e("test db", "siz_of_DB "+ lsCont.size());

        int count=0;
        String stBD = "";
        idCont = new ArrayList<>();
        for(int i=0; i<lsCont.size(); i++){
            if (lsCont.get(i).getBirthday() != null){
                stBD = lsCont.get(i).getBirthday();
                Log.e("test db date", "bd:_ "+ stBD);
                if (!(stBD.equals("") || stBD.equals(null))){
                    CalDate cd = new CalDate(stBD);
                    if (cd.getValue() >= 0){
                        idCont.add(lsCont.get(i).getId());
                        lsName.add(lsCont.get(i).getFirstname()+" "+lsCont.get(i).getLastname());
                        lsDate.add(cd.getValue()+ " days to go...");
                        Log.e("contract_id ", lsCont.get(i).getId());
                    }
                    Log.e("test caldate", "test caldate:_" + cd.getValue());
                    count++;
                }
            }
        }

        Log.e("count contract ", "" + count+" people");

        header_text = (TextView) v.findViewById(R.id.header_text);
        list = (ListView) v.findViewById(R.id.list);

        list_item_image = (ImageView) v.findViewById(R.id.list_item_image);
        list_item_name = (TextView) v.findViewById(R.id.list_item_name);
        list_item_subtitle = (TextView) v.findViewById(R.id.list_item_subtitle);

        itemList = new ArrayList<Item>();
        for (int i=0;i<lsName.size();i++){
            itemList.add(new EntryItem(lsName.get(i).toString(),lsDate.get(i).toString()));
        }

        entAdap = new EntryAdapterOrder(getActivity(), itemList);
        list.setAdapter(entAdap);
        list.setOnItemClickListener(this);

        return v;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView)v.findViewById(R.id.toolbar_title);
        header_text = (TextView) v.findViewById(R.id.header_text);

        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
        header_text.setTypeface(fontThSarabun);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String get2Id = lsCont.get(position).getId();
        String get2Id = idCont.get(position);

        communicator.MessageBDDetail(get2Id);
    }

    public interface Communicator {
        public void MessageBD(String OS_Name);
        public void MessageBDDetail(String getId);
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
