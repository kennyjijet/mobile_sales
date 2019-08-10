package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.SharedPrf.SharedPrefOrderDelivery;
import com.salesforce.android.restsample.adapters.adapter.ListAddressBillToAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setOrderSelectAddressBillTo extends Fragment implements View.OnClickListener {
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "myprefOrderDeliv";
    SharedPrefOrderDelivery shrPrfOrd;

    // set Detail
    TextView tvCreateplan, tvgoBack;

    ListView lvAddress;
    ListAddressBillToAdapter adapter;
    List<Address> mAddressList;
    AddressDatabaseHelper mDBHelper;
    String newId;
    int newIdSV;
    int newIdOrder = 0;
    EditText editsearch;

    private Bundle args;
    private Fragment frg;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommunicatorV4) {
            communicatorV4 = (CommunicatorV4) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
//            throw new ClassCastException(activity.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_plan_delivery_address_edittext, container, false);
        // Initialize Views
        setLayout(view);

        args = new Bundle();

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");
        if (!(getArguments().getString("getIdOrder") == null)){
            newIdOrder = Integer.parseInt(getArguments().getString("getIdOrder"));
        }

        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        shrPrfOrd = new SharedPrefOrderDelivery(sharedpreferences, getActivity());

        Log.e("OrderDeliveryActivity2", "Order2_Billto_2-1_newId : " + newId); // 00128000007uGpMAAU
        Log.e("OrderDeliveryActivity2", "Order2_Billto_2-1_newIdSV : " + newIdSV); // 2

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
        lvAddress = (ListView) view.findViewById(R.id.listview_plan);
        tvCreateplan = (TextView) view.findViewById(R.id.tvcreatenewdev);
        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        tvgoBack.setTypeface(fontThSarabunBold);
        tvgoBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(OrderSelectAddressBillTo.this, OrderDeliveryDetailActivity2.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

//        TextView tvSearch = (TextView) findViewById(R.id.btnSearch); // tvcreatenewplan
//        tvSearch.setTypeface(fontThSarabunBold);



        mDBHelper = new AddressDatabaseHelper(getActivity());

        //Get product list in db when db exists
//        mAddressList = mDBHelper.getListAddress();
        mAddressList = mDBHelper.getListAddressSearchById(newId);

        List<Address> listFinal = new ArrayList<Address>();

        for(int position=0; position<mAddressList.size(); position++){
            String getName = mAddressList.get(position).getName(); //getPdbrid
            String getStreet = mAddressList.get(position).getStreet(); //getPdbrid
            String getState = mAddressList.get(position).getState(); //getPdbrid
            int getType = mAddressList.get(position).getType();

            listFinal.add(mAddressList.get(position));


        }

        //Init adapter
        // adapter = new ListAddressBillToAdapter(this, mAddressList);
        adapter = new ListAddressBillToAdapter(getActivity(), listFinal);


        //Set adapter for listview
        lvAddress.setAdapter(adapter);

        lvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String getNameShow = mAddressList.get(position).getName();
                String billtoid = mAddressList.get(position).getId();
                Log.e("OrderDeliveryActivity", "Tbilltoid:_1-1_id:_ " + billtoid);
                Log.e("OrderDeliveryActivity", "Tbilltoid:_1-1_name:_ " + getNameShow);

//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result",getNameShow);
//                returnIntent.putExtra("billtoid", billtoid);
//                returnIntent.putExtra("getId", newId);
//                returnIntent.putExtra("getIdSV", newIdSV);
//                startActivityForResult(returnIntent,1);
//                Log.e("OrderDeliveryActivity", "Tbilltoid:_1-1_idSV:_ " + newIdSV);

//                shrPrfOrd.SaveBillTo(getNameShow);
                shrPrfOrd.SaveBillToSv("billto"+Integer.toString(newIdSV), getNameShow);
//                shrPrfOrd.SaveBillToId(billtoid);

                frg = new TabletTab2setOrderDeliveryDetailActivity2();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("getIdOrder", ""+newIdOrder);
                args.putString("getTag", "billAddress");
                args.putString("getAddrId", billtoid);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);

            }
        });



        ImageView goBack = (ImageView) view.findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(OrderSelectAddressBillTo.this, OrderDeliveryDetailActivity2.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
                frg = new TabletTab2setOrderDeliveryDetailActivity2();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        // Insert Search Form
        // Locate the EditText in listview_main.xml
        editsearch = (EditText) view.findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });

        return view;
    }

    private void setLayout(View view) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);
    }
//    public interface CommunicatorV4 {
//        public void MessageBD(String OS_Name);
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        if(OS_Name == "Billto"){
            frg = new TabletTab2setOrderSelectAddressBillTo();
            args.putString("getId", newId);
            args.putInt("getIdSV", newIdSV);
            frg.setArguments(args);
            communicatorV4.MessageTab(1,frg);
        }else if (OS_Name == "ShipTo"){
            frg = new TabletTab2setOrderSelectAddressShipTo();
            args.putString("getId", newId);
            args.putInt("getIdSV", newIdSV);
            frg.setArguments(args);
            communicatorV4.MessageTab(1,frg);
        }
    }
}
