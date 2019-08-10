package com.salesforce.android.restsample.Tablet.Detail.TabletPlan;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.Library.ImageConverter;
import com.salesforce.android.restsample.MainFragment.CustomViewIconTextTabsActivity;
import com.salesforce.android.restsample.MainPLANs.ClassifiedPlan.ClassifiedPlan;
import com.salesforce.android.restsample.MainPLANs.PlanActivity1new2;
import com.salesforce.android.restsample.MainPLANs.PlanActivity4Contacts;
import com.salesforce.android.restsample.MainPLANs.PlanActivity4EditPlan;
import com.salesforce.android.restsample.MainPLANs.planDatabase.PlanAcitivity4MapAddress;
import com.salesforce.android.restsample.R;

import java.util.List;

/**
 * Created by pannikar on 7/21/16 AD.
 */
public class TabletPlanActivity4Map extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;
    private String newId;
    private String newIdAdd;
    private String newName;
    private String newTime;

    AccountDatabaseHelper mDBHelperAccount;
    AddressDatabaseHelper mDBHelper2;
    List<Account> getAccount;
    List<Address> getAddress;



    double latitude;
    double longitude;

    ClassifiedPlan classifiedPlan;

    SupportMapFragment mMapFragment;

    static SupportMapFragment supportMapFragment;
    GoogleMap googleMap;
    LinearLayout ln3;
    private Button btnStartVisit;
    private String newNumber;
    private int newIdSV;

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

        newId = getArguments().getString("getId");
        newIdAdd = getArguments().getString("getIdAdd");
        newName = getArguments().getString("getName");
        newTime = getArguments().getString("getTime");
        newNumber = getArguments().getString("getNumber");
        newIdSV = getArguments().getInt("getIdSV");
        Log.e("TabletCustomer2Detail","Detail_value:_" + newId);

        View view = inflater.inflate(R.layout.detail_plan_activity4_detail_edit3_map, container, false);
        // Initialize Views
        setLayout(view);

        btnStartVisit.setOnClickListener(this);

//        tvgoBack.setOnClickListener(this);

        // try to set Map Start
//        mMapFragment = new SupportMapFragment() {
//            @Override
//            public void onActivityCreated(Bundle savedInstanceState) {
//                super.onActivityCreated(savedInstanceState);
//                mMap = mMapFragment.getMap();
//                if (mMap != null) {
//                    setupMap();
//                }
//            }
//        };
//        getChildFragmentManager().beginTransaction().add(R.id.framelayout_location_container, mMapFragment).commit();

        // try to set Map End

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);

        // try to set Detail

        // round Iamge Start
        Bitmap bitmapBuilding = BitmapFactory.decodeResource(this.getResources(), R.drawable.businessbuilding);
        Bitmap circularBitmapBld = ImageConverter.getRoundedCornerBitmap(bitmapBuilding, 30);
        ImageView circularImageViewBeforeL = (ImageView) v.findViewById(R.id.imageLeft);
        circularImageViewBeforeL.setImageBitmap(circularBitmapBld);

        Bitmap bitmapMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.map);
        Bitmap circularBitmapMp = ImageConverter.getRoundedCornerBitmap(bitmapMap, 30);
//        ImageView circularImageViewBeforeR = (ImageView)findViewById(R.id.imageRight);
//        circularImageViewBeforeR.setImageBitmap(circularBitmapMp);
        // round Image End

        //linearCustomer
        LinearLayout lnCustomer = (LinearLayout) v.findViewById(R.id.linearCustomer);
        ln3 = (LinearLayout) v.findViewById(R.id.linearLayout3);

//        TextView goBack = (TextView) v.findViewById(R.id.toolbar_back);
        TextView goEdit = (TextView) v.findViewById(R.id.toolbar_edit);


        mDBHelperAccount = new AccountDatabaseHelper(getActivity());
        mDBHelper2 = new AddressDatabaseHelper(getActivity());
        getAccount = mDBHelperAccount.getListAccountListBySearchId(newId);
        getAddress = mDBHelper2.getListAddressSearchByIdAddress(newIdAdd);

        TextView tvTime1 = (TextView) v.findViewById(R.id.tv_time1);
        TextView tvPreviteNote1 = (TextView) v.findViewById(R.id.tv_previsitnote1);
        TextView tvPreviteNote2 = (TextView) v.findViewById(R.id.tv_previsitnote2);

//        activities
        TextView tvActivity = (TextView) v.findViewById(R.id.tv_activities);
        tvActivity.setTypeface(fontThSarabun);

        //textViewCode // tv_type
        TextView tvType = (TextView) v.findViewById(R.id.tv_type);
        TextView tvCode = (TextView) v.findViewById(R.id.textViewCode);
        TextView tvName0 = (TextView) v.findViewById(R.id.tv_name0);
        TextView tvName = (TextView) v.findViewById(R.id.tv_name);
        TextView tvTime = (TextView) v.findViewById(R.id. tv_time2); // tv_previsitnote1

        TextView tv1 = (TextView) v.findViewById(R.id.tv_1street);
        TextView tv2 = (TextView) v.findViewById(R.id.tv_2city);
        TextView tv3 = (TextView) v.findViewById(R.id.tv_3state);
        TextView tv4 = (TextView) v.findViewById(R.id.tv_4country);
        TextView tv5 = (TextView) v.findViewById(R.id.tv_5postalcode);
        TextView tv6 = (TextView) v.findViewById(R.id.tv_6phone);
        TextView tv7 = (TextView) v.findViewById(R.id.tv_7mobile);

        tvName.setText(newName);

        // for this Activity

        tvType.setTypeface(fontThSarabunBold);
        tvCode.setTypeface(fontThSarabunBold);
        tvName0.setTypeface(fontThSarabunBold);
        tvName.setTypeface(fontThSarabunBold);

        tvTime1.setTypeface(fontThSarabun);

        tvTime.setTypeface(fontThSarabun);
        tvPreviteNote1.setTypeface(fontThSarabun);
        tvPreviteNote2.setTypeface(fontThSarabun);

//        goBack.setTypeface(fontThSarabun);
        goEdit.setTypeface(fontThSarabun);

        tv1.setTypeface(fontThSarabun);
        tv2.setTypeface(fontThSarabun);
        tv3.setTypeface(fontThSarabun);
        tv4.setTypeface(fontThSarabun);
        tv5.setTypeface(fontThSarabun);
        tv6.setTypeface(fontThSarabun);
        tv7.setTypeface(fontThSarabun);

        // set name and code Start
        tvType.setText(getAddress.get(0).getName());
        tvName.setText(getAccount.get(0).getName());
        tvCode.setText(getAccount.get(0).getNumber());

        latitude = getAddress.get(0).getLatitude();
        longitude = getAddress.get(0).getLongtitude();

        // set name and code End

        if(getAddress.size()!=0){
            tv1.setText(getAddress.get(0).getStreet());
            tv2.setText(", " + getAddress.get(0).getCity());
            tv3.setText(getAddress.get(0).getState());
            tv4.setText(", " + getAddress.get(0).getCountry());
            tv5.setText(", Postal code: " + getAddress.get(0).getPostalCode());
            tv6.setText("Phone1: " + getAddress.get(0).getPhone1());
            tv7.setText(", Mobile: " + getAddress.get(0).getMobile());
            tvTime.setText(newTime);
        } else {
            tv1.setText("Street: null    ");
            tv2.setText(", City: null    ");
            tv3.setText("State:  null    ");
            tv4.setText(", Country: null    ");
            tv5.setText(", Postal code: null    ");
            tv6.setText("Phone1: null    ");
            tv7.setText(", Mobile: null    ");
        }


        btnStartVisit =(Button) v.findViewById(R.id.btnStartVisit);
        btnStartVisit.setTypeface(fontThSarabunBold);
        btnStartVisit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                String get0Id = newId;
//                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//
//
//                Intent i = new Intent(PlanActivity4Map.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAdd);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                startActivity(i);
            }
        });

//        LinearLayout lnLeft = (LinearLayout) v.findViewById(R.id.LnLeft);
//        lnLeft.setOnClickListener(new View.OnClickListener() {
//
//            //@Override
//            public void onClick(View v) {
//
////                Intent i = new Intent(PlanActivity4Map.this, PlanActivity1new2.class);
////                startActivity(i);
//            }
//        });

        LinearLayout lnRight = (LinearLayout) v.findViewById(R.id.LnRight);
        lnRight.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanActivity4Map.this, PlanActivity4EditPlan.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAdd);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//
//                startActivity(i);
            }
        });


        goEdit.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanActivity4Map.this, PlanActivity4EditPlan.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAdd);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//
//                startActivity(i);
            }
        });

        lnCustomer.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanActivity4Map.this, PlanActivity4Contacts.class);
//
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAdd);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//
//                startActivity(i);
            }
        });

        ln3.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanActivity4Map.this, PlanAcitivity4MapAddress.class);
//
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAdd);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//
//                startActivity(i);
            }
        });

        // Original
//        setTitle(null);
//        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
//        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
//        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
//        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
//
//        mTitle.setTypeface(fontThSarabunBold);
    }
    public interface Communicator {
        public void Message4Map(String OS_Name);
        public void Message4MapId(String newNumber,
                                  String newIdAdd,
                                  String newId,
                                  String newName,
                                  String newTime,
                                  int newIdSV);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
            case R.id.btnStartVisit:
                updateFragment("btnStartVisit");
                communicator.Message4MapId(newNumber,
                                        newIdAdd,
                                        newId,
                                        newName,
                                        newTime,
                                        newIdSV);
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.Message4Map(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
