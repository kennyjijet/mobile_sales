package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.AboutTime.ClassifiedTime;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderHeader.OrderHeader;
import com.salesforce.android.restsample.DB.Model.DBOrderHeader.OrderHeaderDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.CustomViewIconTextTabsActivity;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.OrderDeliveryDetailActivity2;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab2OrderDeliveryDetailActivity;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.EntryAdapterOrder;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.EntryItem;
import com.salesforce.android.restsample.MainPLANs.plan_today_tomorrow_item.Item;
import com.salesforce.android.restsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setOrderDeliveryActivity extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;
    String get2IdOrder;
//    ListView lvAddress;

    int tyear;
    int tmonth;
    int tday;

    String stTimeInt="";
    String stTimeformat;
    StringBuilder stBuild;
    String chkHourChgd;

    List<Address> lvAddressBill;
    List<Address> lvAddressShip;
    // List<Order> valuesPlan;
    List<OrderHeader> valuesPlanHeader;
    //    OrderDatabaseHelper datasourceSV;
    OrderHeaderDatabaseHelper datasourceSVHeader;
    AddressDatabaseHelper dataDBAddress;

    private ListView mListView2;

    ArrayList<Item> itemsOrder = new ArrayList<Item>();
    ArrayList<Integer> itemsALLStIdOrder = new ArrayList<Integer>();

    LinearLayout layPromo;

    private Bundle args;
    private Fragment frg;

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

        View view = inflater.inflate(R.layout.detail_tab_2delivery, container, false);

        args = new Bundle();

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");

//        Bundle extras = getIntent().getExtras();
//        newIdAddr= extras.getString("getIdAdd");
//        newId= extras.getString("getId");
//        newNumber= extras.getString("getNumber");
//        newName= extras.getString("getName");
//        newTime= extras.getString("getTime");
//        newIdSV = extras.getInt("getIdSV");

        newIdAddr= "00128000007uGpPAAU";
//        newId= "00128000007uGpMAAU";
        newNumber= "CD355118";
        newName= "United Oil & Gas Corp.";
        newTime= "Apr 19, 2016 07:03 PM";
//        newIdSV = 2;

        // Initialize Views
        setLayout(view);

//        tvgoBack.setOnClickListener(this);


        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
//
//        toolbar_title.setTypeface(fontThSarabunBold);

        layPromo = (LinearLayout) v.findViewById(R.id.layPromo);
        layPromo.setOnClickListener(this);

        // set Detail


        // for this Activity
//        lvAddress = (ListView)findViewById(R.id.listview_plan);

//        TextView tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
//        tvgoBack.setTypeface(fontThSarabunBold);

        TextView tvNewDev = (TextView) v.findViewById(R.id.tvcreatenewdev);
        tvNewDev.setTypeface(fontThSarabunBold);
        tvNewDev.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(OrderDeliveryActivity.this, OrderDeliveryDetailActivity2.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
                frg = new TabletTab2setOrderDeliveryDetailActivity2();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("getTag", "addNew");
                frg.setArguments(args);
                communicator.MessageTab(1,frg);
            }
        });

        dataDBAddress = new AddressDatabaseHelper(getActivity());
//        datasourceSV = new OrderDatabaseHelper(this);
        datasourceSVHeader = new OrderHeaderDatabaseHelper(getActivity());
        // valuesPlan = datasourceSV.getListOrderListBySearchId(String.valueOf(newIdSV));

        valuesPlanHeader = datasourceSVHeader.getListOrderListBySearchIdINTEGER(newIdSV);

        Log.e("OrderDeliveryActivity", "OrderDelivery_newIdSV : " + newIdSV);
        Log.e("OrderDeliveryActivity", "OrderDelivery_valuesPlan.size() : " + valuesPlanHeader.size());

        mListView2 = (ListView) v.findViewById(R.id.list);


        if(valuesPlanHeader.size()!=0){

            for(int i=0; i<valuesPlanHeader.size();i++){
                String getIdBillTo = valuesPlanHeader.get(i).getIdBill();
                String getIdShipTo = valuesPlanHeader.get(i).getIdShip();

                lvAddressBill = dataDBAddress.getListAddressSearchById2(getIdBillTo);
                lvAddressShip = dataDBAddress.getListAddressSearchById2(getIdShipTo);

                if(lvAddressBill.size()!=0){

                    int getDateInt = valuesPlanHeader.get(i).getDelivDate();

                    ClassifiedTime classifiedTime = new ClassifiedTime();
                    String dateSt = classifiedTime.getFormatDate(getDateInt);

                    int num = i+1;
                    itemsOrder.add(new EntryItem(num+ "."+
                            " Bill to: " + lvAddressBill.get(0).getName(),
                            "   Ship to: " + lvAddressShip.get(0).getName(), //       valuesPlan.get(i).getTimeIn(),//dateAndTime,
                            "    " + dateSt));
                    // itemsALLStIdOrder.add(valuesPlan.get(i).getId());
                    itemsALLStIdOrder.add(valuesPlanHeader.get(i).getId());
                }

            }

            Log.e("test","test_adapterALL1: " + itemsOrder.size());

            if (itemsOrder.size() !=0 ){

                EntryAdapterOrder adapterALL = new EntryAdapterOrder(getActivity(), itemsOrder);
                Log.e("test","test_adapterALL2: " + adapterALL.isEmpty());

                mListView2.setAdapter(adapterALL);

                mListView2.setOnItemClickListener(this);

            }
        }
    }
    public interface Communicator {
        public void MessageTab(int tabSide, Fragment frg);
        public void MessageOrderDelivery(String OS_Name);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int position1 = position;//-1;

        get2IdOrder = itemsALLStIdOrder.get(position1).toString();

        Log.e(" Check_position1", "Chk_order_to_edit:_itemsALLStIdOrder.size():_" + itemsALLStIdOrder.size());
        Log.e(" Check_position1", "Chk_order_to_edit:_get2IdOrder:_" + get2IdOrder);

        frg = new TabletTab2setOrderDeliveryDetailActivity2();
        args.putString("getId", newId);
        args.putInt("getIdSV", newIdSV);
        args.putString("getIdOrder", get2IdOrder);
        args.putString("getTag", "Ordered");
        frg.setArguments(args);
        communicator.MessageTab(1,frg);
//        communicator.MessageOrderDelivery("deliDetail");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.layPromo:
                updateFragment("layPromo");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageOrderDelivery(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
