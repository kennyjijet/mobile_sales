package com.salesforce.android.restsample.Tablet.Detail.TabletPlan;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.R;

import java.util.List;

/**
 * Created by pannikar on 8/26/16 AD.
 */
public class TabletPlanAcitivity4MapAddress extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;
    AddressDatabaseHelper mDBHelper4Addr;
    List<Address> getAddress;
    String parentId;

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

        // setContentView(R.layout.address_detail);
        View v = inflater.inflate(R.layout.detail_address_detail2, container, false);


//        Bundle extras = getIntent().getExtras();
        newIdAddr= getArguments().getString("getIdAdd");
        newId= getArguments().getString("getId");
        newNumber= getArguments().getString("getNumber");
        newName= getArguments().getString("getName");
        newTime= getArguments().getString("getTime");
        newIdSV = getArguments().getInt("getIdSV");
        Log.e("Customer4AddressDetail", "Done_newId: " + newId);
        Log.e("Customer4AddressDetail", "Done_newName: " + newName);

        mDBHelper4Addr = new AddressDatabaseHelper(getActivity());

        getAddress = mDBHelper4Addr.getListAddressSearchById2(newIdAddr);

        LinearLayout lnContact = (LinearLayout) v.findViewById(R.id.LnContacts);

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04b4 = (TextView) v.findViewById(R.id.title04b4);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);
        TextView tt08 = (TextView) v.findViewById(R.id.title08);
        TextView tt09 = (TextView) v.findViewById(R.id.title09);
        TextView tt10 = (TextView) v.findViewById(R.id.title10);
        TextView tt11 = (TextView) v.findViewById(R.id.title11);
        TextView tt12 = (TextView) v.findViewById(R.id.title12);
        TextView ttContact = (TextView) v.findViewById(R.id.ttContact);

        TextView tvBack = (TextView) v.findViewById(R.id.toolbar_back);
        TextView tvEdit = (TextView) v.findViewById(R.id.toolbar_edit);
        TextView tv1 = (TextView) v.findViewById(R.id.tvName);
        TextView tv2 = (TextView) v.findViewById(R.id.tvAddress1);
        TextView tv3 = (TextView) v.findViewById(R.id.tvAddress2);
        TextView tv4b4 = (TextView) v.findViewById(R.id.tvSubDistrict);
        TextView tv4 = (TextView) v.findViewById(R.id.tvCityDistrict);
        TextView tv5 = (TextView) v.findViewById(R.id.tvStateProvince);
        TextView tv6 = (TextView) v.findViewById(R.id.tvCountry);
        TextView tv7 = (TextView) v.findViewById(R.id.tvZipcode);
        TextView tv8 = (TextView) v.findViewById(R.id.tvPhone1);
        TextView tv9 = (TextView) v.findViewById(R.id.tvPhone2);
        TextView tv10 = (TextView) v.findViewById(R.id.tvMobile);
        TextView tv11 = (TextView) v.findViewById(R.id.tvFax);
        TextView tv12 = (TextView) v.findViewById(R.id.tvEmail);

        // for this Activity
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt04b4.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07.setTypeface(fontThSarabunBold);
        tt08.setTypeface(fontThSarabunBold);
        tt09.setTypeface(fontThSarabunBold);
        tt10.setTypeface(fontThSarabunBold);
        tt11.setTypeface(fontThSarabunBold);
        tt12.setTypeface(fontThSarabunBold);
        ttContact.setTypeface(fontThSarabunBold);

        tvBack.setTypeface(fontThSarabun);
        tvEdit.setTypeface(fontThSarabun);
        tv1.setTypeface(fontThSarabun);
        tv2.setTypeface(fontThSarabun);
        tv3.setTypeface(fontThSarabun);
        tv4b4.setTypeface(fontThSarabun);
        tv4.setTypeface(fontThSarabun);
        tv5.setTypeface(fontThSarabun);
        tv6.setTypeface(fontThSarabun);
        tv7.setTypeface(fontThSarabun);
        tv8.setTypeface(fontThSarabun);
        tv9.setTypeface(fontThSarabun);
        tv10.setTypeface(fontThSarabun);
        tv11.setTypeface(fontThSarabun);
        tv12.setTypeface(fontThSarabun);

        if(getAddress.size()!=0){
            tv1.setText(getAddress.get(0).getName());
            tv2.setText(getAddress.get(0).getStreet()); // newNumber
            tv3.setText("");
            tv4.setText(getAddress.get(0).getCity());
            tv5.setText(getAddress.get(0).getState());
            tv6.setText(getAddress.get(0).getCountry());
            tv7.setText(getAddress.get(0).getPostalCode());
            tv8.setText(getAddress.get(0).getPhone1());
            tv9.setText(getAddress.get(0).getPhone2());
            tv10.setText(getAddress.get(0).getMobile());
            tv11.setText(getAddress.get(0).getFax());
            tv12.setText(getAddress.get(0).getEmail());
        } else {
//            tv1.setText(" null ");
//            tv2.setText(" null ");
//            tv3.setText(" null ");
//            tv4.setText(" null ");
//            tv5.setText(" null ");
//            tv6.setText(" null ");
//            tv7.setText(" null ");
//            tv8.setText(" null ");
        }
//
////        ImageView goBack = (ImageView) v.findViewById(R.id.goBack);
        tvBack.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(PlanAcitivity4MapAddress.this, PlanActivity4Map.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                String get0Id = newId;
                Log.e("PlanActivity4", "Done_Id: " + get0Id);

//                Intent i = new Intent(PlanAcitivity4MapAddress.this, PlanActivity4MapAddEdit.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

        lnContact.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                String get0Id = newId;
                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//                Intent i = new Intent(PlanAcitivity4MapAddress.this, PlanActivity4MapContacts.class);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);

            }
        });


        // Original
        getActivity().setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        //setSupportActionBar(topToolBar);
        topToolBar.setTitle("Address Detail");
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));

        mTitle.setTypeface(fontThSarabunBold);

        return v;
    }

    public interface Communicator {
        public void MessageCustomer(String OS_Name);
        public void MessageCustomerAddress(int newIdSV, String newTime,
                                           String get2Id,String  get0name,String  get1number);
    }

    @Override
    public void onClick(View v) {

//        toolbar_back
        switch (v.getId()){
            case R.id.toolbar_back:
                communicator.MessageCustomerAddress(newIdSV, newTime,
                        newId, newName, newNumber);
                break;
        }
    }
}

