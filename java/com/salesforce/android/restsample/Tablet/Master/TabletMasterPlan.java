package com.salesforce.android.restsample.Tablet.Master;

import android.app.Activity;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisit;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisitDatabaseHelper;
import com.salesforce.android.restsample.MainPLANs.ClassifiedPlan.ClassifiedPlan;
import com.salesforce.android.restsample.MainPLANs.planDatabase.PlanActivity4Map;
import com.salesforce.android.restsample.MainPLANs.planDatabase.PlanDataSource;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.EntryAdapter;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.EntryItem;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.Item;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.SectionItem;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.tab.ListCustomerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by pannikar on 7/14/16 AD.
 */
public class TabletMasterPlan extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvgoBack;
    private TextView toolbar_title;
    private TextView tvcreatenewdev;

    // add for showing
    TextView tvCreateplan;
    ListView lvProduct;
    ListCustomerAdapter adapter;


    private ListView mListView1, mListView2, mListView3;
    private SearchView searchView;

    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<Item> itemsToday = new ArrayList<Item>();
    ArrayList<Item> itemsTomorrow = new ArrayList<Item>();
    ArrayList<Item> itemsFuture = new ArrayList<Item>();
    ArrayList<Item> itemsPast = new ArrayList<Item>();
    ArrayList<Item> itemsALL = new ArrayList<Item>();

    // *** *** *** ID Address
    ArrayList<String> itemsTodayStIdAdd = new ArrayList<String>();
    ArrayList<String> itemsTomorrowStIdAdd = new ArrayList<String>();
    ArrayList<String> itemsFutureStIdAdd = new ArrayList<String>();
    ArrayList<String> itemsPastStIdAdd = new ArrayList<String>();
    ArrayList<String> itemsALLStIdAdd = new ArrayList<String>();

    // *** *** *** ID
    ArrayList<String> itemsTodayStId = new ArrayList<String>();
    ArrayList<String> itemsTomorrowStId = new ArrayList<String>();
    ArrayList<String> itemsFutureStId = new ArrayList<String>();
    ArrayList<String> itemsPastStId = new ArrayList<String>();
    ArrayList<String> itemsALLStId = new ArrayList<String>();

    // *** *** *** Number
    ArrayList<String> itemsTodayStNumber = new ArrayList<String>();
    ArrayList<String> itemsTomorrowStNumber = new ArrayList<String>();
    ArrayList<String> itemsFutureStNumber = new ArrayList<String>();
    ArrayList<String> itemsPastStNumber = new ArrayList<String>();
    ArrayList<String> itemsALLStNumber = new ArrayList<String>();

    // *** *** *** Name
    ArrayList<String> itemsTodayStName = new ArrayList<String>();
    ArrayList<String> itemsTomorrowStName = new ArrayList<String>();
    ArrayList<String> itemsFutureStName = new ArrayList<String>();
    ArrayList<String> itemsPastStName = new ArrayList<String>();
    ArrayList<String> itemsALLStName = new ArrayList<String>();

    // *** *** *** Time
    ArrayList<String> itemsTodayStTime = new ArrayList<String>();
    ArrayList<String> itemsTomorrowStTime = new ArrayList<String>();
    ArrayList<String> itemsFutureStTime = new ArrayList<String>();
    ArrayList<String> itemsPastStTime = new ArrayList<String>();
    ArrayList<String> itemsALLStTime = new ArrayList<String>();

    // *** *** *** SalesVisit(SV)
    ArrayList<Integer> itemsTodayStSV = new ArrayList<Integer>();
    ArrayList<Integer> itemsTomorrowStSV = new ArrayList<Integer>();
    ArrayList<Integer> itemsFutureStSV = new ArrayList<Integer>();
    //ArrayList<String> itemsPastStSV = new ArrayList<String>();
    ArrayList<Integer> itemsPastStSV = new ArrayList<Integer>();
    ArrayList<Integer> itemsALLStSV = new ArrayList<Integer>();

    int tyear;
    int tmonth;
    int tday;
    List<Account> mAccountList;
    List<Address> mAddressList;
    List<String> mId;

    AccountDatabaseHelper mDBHelperAccount;
    AddressDatabaseHelper mDBHelperAddress;
    SalesVisitDatabaseHelper datasourceSV;
    PlanDataSource mDBHelperPlanDataSource;
    List<SalesVisit> valuesPlan;
    int day;
    long timeToSendBack;
    StringBuilder strTime;
    String strTimeNow;
    Date d1, dNow, dSendBack;
    String strDate1;
    Boolean chkDateToday=true, chkDateTomorrow=true, chkDateFuture=true, chkDatePast=true;

    String stTimeInt="";
    String stTimeformat;
    StringBuilder stBuild;

    String dateAndTime;

    int get2IdSV;

    int timeIn, timeOut;

    ClassifiedPlan classifiedPlan;
    String getResult;
    String nameAccount;
    private ImageView goMap;

    ArrayList<LatLng> pointsToday = new ArrayList<LatLng>();
    ArrayList<LatLng> pointsTomorrow = new ArrayList<LatLng>();
    ArrayList<LatLng> pointsFuture = new ArrayList<LatLng>();

    ArrayList<Integer> pointsTodayStr = new ArrayList<Integer>();
    ArrayList<Integer> pointsTomorrowStr = new ArrayList<Integer>();
    ArrayList<Integer> pointsFutureStr = new ArrayList<Integer>();

    ArrayList<String> pointsTodayName = new ArrayList<String>();
    ArrayList<String> pointsTomorrowName = new ArrayList<String>();
    ArrayList<String> pointsFutureName = new ArrayList<String>();

    int getIdSV;

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

        View view = inflater.inflate(R.layout.tablet_master_plan, container, false);
        // Initialize Views
        datasourceSV = new SalesVisitDatabaseHelper(getActivity());
        mDBHelperPlanDataSource = new PlanDataSource(getActivity());
        classifiedPlan = new ClassifiedPlan();

        mDBHelperPlanDataSource.open();

        mId = new ArrayList<>();
        setLayout(view);

        tvgoBack.setOnClickListener(this);
        tvcreatenewdev.setOnClickListener(this);
        goMap.setOnClickListener(this);


        return view;
    }

    public LatLng convertToLatLng(double lat, double lng) {
        return new LatLng(lat, lng);
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        tvcreatenewdev = (TextView) v.findViewById(R.id.tvcreatenewdev);

        goMap = (ImageView) v.findViewById(R.id.goMap);

        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
        tvcreatenewdev.setTypeface(fontThSarabun);

        // *** *** *** add to show List
        // +++ +++ +++ +++ +++ +++ TEST to use section Header
        mListView2 = (ListView) v.findViewById(R.id.list);
        mListView2.setAdapter(null);

        mDBHelperAccount = new AccountDatabaseHelper(getActivity());
        mDBHelperAccount.openDatabase();

        mDBHelperAddress = new AddressDatabaseHelper(getActivity());
        mDBHelperAddress.openDatabase();

        // ******* ******* ******* ******* ******* ******* ******* To do List Start

        List<Account> accNumber = mDBHelperAccount.getListAccountList();

        // +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ Start
        valuesPlan = datasourceSV.getListSalesVisit();
        // if(valuesPlan.size()!=0){
        if(valuesPlan.size()!=0 ){

            Log.e(" Check_Time", "Chk_Account_PLANs_size: " + valuesPlan.size());

            Collections.sort(valuesPlan, new Comparator<SalesVisit>() {
                @Override
                public int compare(SalesVisit salesVisit, SalesVisit t1) {
                    return Integer.valueOf(t1.getTimeIn()).compareTo(salesVisit.getTimeIn());
                }
            });

            for(int i=0; i<valuesPlan.size();i++){

                Log.e(" Check_Time", "Chk_Account_i: " + i);

                timeIn = valuesPlan.get(i).getTimeIn();
                dateAndTime = classifiedPlan.getFormatDate(timeIn);
                getResult = classifiedPlan.compareDate(timeIn);

                getIdSV = valuesPlan.get(i).getId();

                Log.e(" Check_Time", "Chk_getId: " + getIdSV);

                String getIdAdress = valuesPlan.get(i).getAddrid();
                Log.e(" Check_Time", "Chk_getIdAdress: " + getIdAdress);

                // if(getIdAdress.equals("00128000007uGpMAAU")){
                if(getIdAdress == null){

                    Log.e(" Check_Time", "Chk_Account_check: ");


                } else if( getIdAdress != null) {

                    mAddressList = mDBHelperAddress.getListAddressSearchById2(getIdAdress);

                    mAccountList = mDBHelperAccount.getListAccountListBySearchId(mAddressList.get(0).getAcctid());

                    nameAccount = mAccountList.get(0).getName();

                    Log.e(" Check_Time", "Chk_mAddressList: " + mAddressList.size());
                    Log.e(" Check_Time", "Chk_mAccountList: " + mAccountList.size());

                    switch (getResult){
                        case "past":
                            if(chkDatePast==true){
                                itemsPast.add(new SectionItem("Past"));
                                itemsPastStIdAdd.add("Past");
                                itemsPastStId.add("Past");
                                itemsPastStNumber.add("Past");
                                itemsPastStName.add("Past");
                                itemsPastStTime.add("Past");
                                itemsPastStSV.add(0);

                                chkDatePast = false;
                            }

                            itemsPast.add(new EntryItem( mAddressList.get(0).getName(),
                                                        mAccountList.get(0).getNumber() + ", "+ nameAccount, //valuesPlan.get(i).getPrevisitnote(),
                                                        dateAndTime));

                            itemsPastStIdAdd.add(mAddressList.get(0).getId());
                            itemsPastStId.add(mAccountList.get(0).getId());
                            itemsPastStNumber.add(mAccountList.get(0).getNumber());
                            itemsPastStName.add(nameAccount);
                            itemsPastStTime.add(dateAndTime);
                            itemsPastStSV.add(getIdSV);

                            break;

                        case "today":
                            if(chkDateToday==true){
                                itemsToday.add(new SectionItem("Today"));
                                itemsTodayStIdAdd.add("Today");
                                itemsTodayStId.add("Today");
                                itemsTodayStNumber.add("Today");
                                itemsTodayStName.add("Today");
                                itemsTodayStTime.add("Today");
                                itemsTodayStSV.add(1);
                                chkDateToday = false;
                            }
                            Log.e(" Check_Time", "Chk_5_itemsToday: " + itemsToday.size());

                            itemsToday.add(new EntryItem(   mAddressList.get(0).getName(),
                                    mAccountList.get(0).getNumber() + ", "+ nameAccount, //valuesPlan.get(i).getPrevisitnote(),
                                    dateAndTime));

                            itemsTodayStIdAdd.add(mAddressList.get(0).getId());
                            itemsTodayStId.add(mAccountList.get(0).getId());
                            itemsTodayStNumber.add(mAccountList.get(0).getNumber());
                            itemsTodayStName.add(nameAccount);
                            itemsTodayStTime.add(dateAndTime);
                            itemsTodayStSV.add(getIdSV);

                            LatLng latlngToday = convertToLatLng(mAddressList.get(0).getLatitude(), mAddressList.get(0).getLongtitude());
                            pointsToday.add(latlngToday);
                            pointsTodayName.add(mAddressList.get(0).getName());

                            pointsTodayStr.add(getIdSV);

                            break;

                        case "tomorrow":
                            if(chkDateTomorrow==true){
                                itemsTomorrow.add(new SectionItem("Tomorrow"));
                                itemsTomorrowStIdAdd.add("Tomorrow");
                                itemsTomorrowStId.add("Tomorrow");
                                itemsTomorrowStNumber.add("Tomorrow");
                                itemsTomorrowStName.add("Tomorrow");
                                itemsTomorrowStTime.add("Tomorrow");
                                itemsTomorrowStSV.add(1);
                                chkDateTomorrow = false;
                            }
                            itemsTomorrow.add(new EntryItem(    mAddressList.get(0).getName(),
                                    mAccountList.get(0).getNumber() + ", "+ nameAccount, //valuesPlan.get(i).getPrevisitnote(),
                                    dateAndTime));

                            itemsTomorrowStIdAdd.add(mAddressList.get(0).getId());
                            itemsTomorrowStId.add(mAccountList.get(0).getId());
                            itemsTomorrowStNumber.add(mAccountList.get(0).getNumber());
                            itemsTomorrowStName.add(nameAccount);
                            itemsTomorrowStTime.add(dateAndTime);
                            itemsTomorrowStSV.add(getIdSV);

                            LatLng latlngTomorrow = convertToLatLng(mAddressList.get(0).getLatitude(), mAddressList.get(0).getLongtitude());
                            pointsTomorrow.add(latlngTomorrow);
                            pointsTomorrowName.add(mAddressList.get(0).getName());
                            pointsTomorrowStr.add(getIdSV);

                            break;
                        case "future":

                            if(chkDateFuture==true){
                                itemsFuture.add(new SectionItem("Future"));
                                itemsFutureStIdAdd.add("Tomorrow");
                                itemsFutureStId.add("Tomorrow");
                                itemsFutureStNumber.add("Tomorrow");
                                itemsFutureStName.add("Tomorrow");
                                itemsFutureStTime.add("Tomorrow");
                                itemsFutureStSV.add(1);
                                chkDateFuture = false;
                            }
                            itemsFuture.add(new EntryItem(    mAddressList.get(0).getName(),
                                    mAccountList.get(0).getNumber() + ", "+ nameAccount, //valuesPlan.get(i).getPrevisitnote(),
                                    dateAndTime));

                            itemsFutureStIdAdd.add(mAddressList.get(0).getId());
                            itemsFutureStId.add(mAccountList.get(0).getId());
                            itemsFutureStNumber.add(mAccountList.get(0).getNumber());
                            itemsFutureStName.add(nameAccount);
                            itemsFutureStTime.add(dateAndTime);
                            itemsFutureStSV.add(getIdSV);

                            LatLng latlngFuture = convertToLatLng(mAddressList.get(0).getLatitude(), mAddressList.get(0).getLongtitude());
                            pointsFuture.add(latlngFuture);
                            pointsFutureName.add(mAddressList.get(0).getName());
                            pointsFutureStr.add(getIdSV);

                            break;
                    }
                }

            } // end for


            Log.e("chk","chk_Today:_size:_" + pointsToday.size());
            Log.e("chk","chk_Tomorrow:_size:_" + pointsTomorrow.size());
            Log.e("chk","chk_Future:_size:_" + pointsFuture.size());

            itemsALL.addAll(itemsToday);
            itemsALL.addAll(itemsTomorrow);
            itemsALL.addAll(itemsFuture);
            itemsALL.addAll(itemsPast);
            EntryAdapter adapterALL = new EntryAdapter(getActivity(), itemsALL);
            mListView2.setAdapter(adapterALL);

            // +++++ ++++ ++++ Test addALL End

            itemsALLStIdAdd.addAll(itemsTodayStIdAdd);
            itemsALLStIdAdd.addAll(itemsTomorrowStIdAdd);
            itemsALLStIdAdd.addAll(itemsFutureStIdAdd);
            itemsALLStIdAdd.addAll(itemsPastStIdAdd);

            itemsALLStId.addAll(itemsTodayStId);
            itemsALLStId.addAll(itemsTomorrowStId);
            itemsALLStId.addAll(itemsFutureStId);
            itemsALLStId.addAll(itemsPastStId);

            itemsALLStNumber.addAll(itemsTodayStNumber);
            itemsALLStNumber.addAll(itemsTomorrowStNumber);
            itemsALLStNumber.addAll(itemsFutureStNumber);
            itemsALLStNumber.addAll(itemsPastStNumber);

            itemsALLStName.addAll(itemsTodayStName);
            itemsALLStName.addAll(itemsTomorrowStName);
            itemsALLStName.addAll(itemsFutureStName);
            itemsALLStName.addAll(itemsPastStName);

            itemsALLStTime.addAll(itemsTodayStTime);
            itemsALLStTime.addAll(itemsTomorrowStTime);
            itemsALLStTime.addAll(itemsFutureStTime);
            itemsALLStTime.addAll(itemsPastStTime);

            itemsALLStSV.addAll(itemsTodayStSV);
            itemsALLStSV.addAll(itemsTomorrowStSV);
            itemsALLStSV.addAll(itemsFutureStSV);
            itemsALLStSV.addAll(itemsPastStSV);

            // Set Action after select

            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set

                }
            });

            mListView2.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int position1 = position;//-1;

                    String get2IdAdd = itemsALLStIdAdd.get(position1).toString();
                    String get2Id = itemsALLStId.get(position1).toString();
                    String get2Number = itemsALLStNumber.get(position1).toString();
                    String get2Name = itemsALLStName.get(position1).toString();
                    String get2Time = itemsALLStTime.get(position1).toString();
                    get2IdSV = itemsALLStSV.get(position1);

                    communicator.MessagePlanId(get2Id, get2IdAdd, get2Name, get2Time, get2Number, get2IdSV);
    }

    public interface Communicator {
        public void MessagePlan(String OS_Name);
        public void MessagePlanId(String newId, String newIdAdd, String newName, String newTime,
                                  String get2Number, int get2IdSV);
        //public void MessagePlanLatLng(List<LatLng> ltlngToday, List<LatLng> ltlngTomorrow, List<LatLng> ltlngFuture);
        public void MessagePlanLatLng(ArrayList<LatLng> ltlngToday, ArrayList<LatLng> ltlngTomorrow, ArrayList<LatLng> ltlngFuture,
                                      ArrayList<String> ltlngTodayName, ArrayList<String> ltlngTomorrowName, ArrayList<String> ltlngFutureName);
        public void MessagePlanLatLngStr(ArrayList<Integer> ltlngToday, ArrayList<Integer> ltlngTomorrow, ArrayList<Integer> ltlngFuture);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
            case R.id.tvcreatenewdev:
                updateFragment("tvcreatenewdev");
                break;
            case R.id.goMap:
                communicator.MessagePlanLatLng(pointsToday, pointsTomorrow, pointsFuture,
                        pointsTodayName, pointsTomorrowName, pointsFutureName);

                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessagePlan(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
