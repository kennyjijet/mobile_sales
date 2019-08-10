package com.salesforce.android.restsample.Tablet.StartVisit;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBCompetitorBrand.CompetitorBrand;
import com.salesforce.android.restsample.DB.Model.DBCompetitorBrand.CompetitorBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPickList.PickList;
import com.salesforce.android.restsample.DB.Model.DBPickList.PickListDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.itemForPickList.EntryAdapterPickList;
import com.salesforce.android.restsample.ITEMs.itemForPickList.EntryItemPickList;
import com.salesforce.android.restsample.ITEMs.itemForPickList.ItemPicklist;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3.TabletTab3FragmentDetail;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab4.TabletTab4FragmentDetail;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab5.TabletTab5FragmentDetail2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletPickListSingleItem extends Fragment {
    private CommunicatorFragmentPickList communicatorTab3;
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set Detail
    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;
    String newPickList;
    String newFrgmnt;
    int getType;
    String newShowName;
    List<PickList> valuesPickList;
    List<CompetitorBrand> valuesCompBrnd;

    PickListDatabaseHelper databasePickList;
    CompetitorBrandDatabaseHelper databaseCompBrand;

    private ListView mListView2;
    ArrayList<ItemPicklist> itemsPickList = new ArrayList<ItemPicklist>();
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

        View view = inflater.inflate(R.layout.detail_tab_picklist_single, container, false);
        // Initialize Views
        setLayout(view);

        mListView2 = (ListView) view.findViewById(R.id.list);

        args = new Bundle();
        // set Detail
        newId = getArguments().getString("getId");

        if (getArguments().getInt("getIdSV") == 0){
            newIdSV = 0;
        }else {
            newIdSV = getArguments().getInt("getIdSV");
        }

        if (getArguments().getString("getIdAdd") == null){
            newIdAddr = "";
        }else {
            newIdAddr = getArguments().getString("getIdAdd");
        }
        if (getArguments().getString("getNumber") == null){
            newNumber = "";
        }else {
            newNumber = getArguments().getString("getNumber");
        }
        if (getArguments().getString("getName") == null){
            newName = "";
        }else {
            newName = getArguments().getString("getName");
        }
        if (getArguments().getString("getTime") == null){
            newTime = "";
        }else {
            newTime = getArguments().getString("getTime");
        }
        if (getArguments().getInt("getType") == 0){
            getType = 0;
        }else {
            getType = getArguments().getInt("getType");
        }
        if (getArguments().getString("getShowName") == null){
            newShowName = "";
        }else {
            newShowName = getArguments().getString("getShowName");
        }

        newPickList = getArguments().getString("CheckPickList");
        newFrgmnt = getArguments().getString("CheckFrgmtParent"); //"CheckFrgmtParent"

        Log.e("OrderDeliveryActivity", "Tab2FragmentALL3: Id: " + newId);

        // for this Activity
        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        TextView tvCancel = (TextView) view.findViewById(R.id.tvgoBack);
        TextView tvToolbar = (TextView) view.findViewById(R.id.toolbar_title);
        tvCancel.setTypeface(fontThSarabunBold);
        tvToolbar.setTypeface(fontThSarabunBold);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                Intent i = new Intent();


                switch (newFrgmnt){
                    case "Tab3":
                        // i = new Intent(PickListSingleItem.this, Tab3FragmentDetail.class);
                        frg = new TabletTab3FragmentDetail();
                        break;
                    case "Tab4":
                        // i = new Intent(PickListSingleItem.this, Tab4FragmentDetail.class);
                        frg = new TabletTab4FragmentDetail();
                        break;
                    case "Tab5":
                        // i = new Intent(PickListSingleItem.this, Tab5FragmentDetail2.class);
                        frg = new TabletTab5FragmentDetail2();
                        break;
                    case "Plan":
                        // i = new Intent(PickListSingleItem.this, PlanActivity4MapContactsAddnew.class);
                        break;
                }

                if (newFrgmnt.equals("Plan")){

//                    args.putString("result", "");
//                    args.putString("getIdAdd", newIdAddr);
//                    args.putString("getId", newId);
//                    args.putString("getName", newName);
//                    args.putString("getNumber", newNumber);
//                    args.putString("getTime", newTime);
//                    args.putInt("getIdSV", newIdSV);
//                    frg.setArguments(args);
//                    communicatorTab3.setNameAtTextView();
//                    setResult(Activity.RESULT_OK, i);
//                    finish();

//                    frg.startActivityForResult(returnIntent,1);



                }else {
                    args.putString("getId", newId);
                    args.putInt("getIdSV", newIdSV);
                    args.putString("Check", "1");
                    startActivity(i);
                }
            }
        });


        databaseCompBrand = new CompetitorBrandDatabaseHelper(getActivity());
        databasePickList = new PickListDatabaseHelper(getActivity());

        switch (newPickList){
            case "1CompOf": tvToolbar.setText("Product Brand");
//                            getType = 0; // Hard
                setProductBrand();
                break;
            case "2CompBrd": tvToolbar.setText("Competitor Brand");
                setCompetitorBrand(); // Hard
                break;
            case "6Activity": tvToolbar.setText("Activity");
                setPickList(3);
                break;
            case "7DisLocation": tvToolbar.setText("Display Location");
                setPickList(4);
                break;
            case "8Pop": tvToolbar.setText("POP");
                setPickList(5);
                break;
            case "9Priority": tvToolbar.setText("Priority");
                setPriority();
                break;
//
//            case "PayType": tvToolbar.setText("Payment Type");
//                setPayment();
//                break;
//
//            case "Tab4DisplayType": tvToolbar.setText("Display Type");
//                setPickList(3);
//                break;
//
//            case "Bank": tvToolbar.setText("Bank");
//                setBank();
//                break;
//            case "Relation": tvToolbar.setText("Relation");
//                setRelation(getType);
//                break;

        }

        return view;
    }

    private void setLayout(View view) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) view.findViewById(R.id.toolbar_title);

//        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
    }

    public interface CommunicatorFragmentPickList {
        public void MessagePickList(int where, String setTextView);
    }


//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()){
//            case R.id.tvgoBack:
//                updateFragment("Back");
//                break;
//        }
//    }
//
//    private void updateFragment(String OS_Name) {
//        communicatorV4.MessageBD(OS_Name);
//        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
//    }

    public void setProductBrand(){
        itemsPickList.add(new EntryItemPickList(" - "));
        itemsPickList.add(new EntryItemPickList(" Kangaroo"));
        itemsPickList.add(new EntryItemPickList(" Tensoplast Brand "));
        itemsPickList.add(new EntryItemPickList(" BIOS LIFE "));
        EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
        mListView2.setAdapter(adapterALL);
        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String getNameShow = "";
                switch (position){
                    case 0: getNameShow = "-"; break;
                    case 1: getNameShow = "Kangaroo"; break;
                    case 2: getNameShow = "Tensoplast Brand"; break;
                    case 3: getNameShow = "BIOS LIFE"; break;
                    default: getNameShow = "None"; break;
                }
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", getNameShow);
//                returnIntent.putExtra("getId", newId);
//                returnIntent.putExtra("getIdSV", newIdSV);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
                frg = new TabletTab3FragmentDetail();
                args.putString("result", getNameShow);
                args.putString("getIdAdd", newIdAddr);
                args.putString("getId", newId);
                args.putString("getName", newName);
                args.putString("getNumber", newNumber);
                args.putString("getTime", newTime);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
//                communicatorTab3.setNameAtTextViewMain(1, getNameShow);
//                communicatorTab3.MessagePickList(1,getNameShow);
                communicatorV4.MessageTab(1,frg);

            }
        });
    }

    public void setCompetitorBrand(){

        valuesCompBrnd = databaseCompBrand.getListCompetitorBrand();


        if(valuesCompBrnd.size() != 0){
            for(int i=0; i<valuesCompBrnd.size(); i++){

                if(i==0) {

                    itemsPickList.add(new EntryItemPickList(" - "));
                    itemsPickList.add(new EntryItemPickList(" "+valuesCompBrnd.get(i).getName()));

                } else {

                    itemsPickList.add(new EntryItemPickList(" "+valuesCompBrnd.get(i).getName()));
                    Log.e("PickListSingleItem", "valuesPickList: Id: " + valuesCompBrnd.get(i).getName());

                }

            }
            EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
            mListView2.setAdapter(adapterALL);
            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.e("PickListSingleItem", "valuesPickList: position:_ " + position);
                    String getNameShow = "";
                    String getCompId = "";
                    int positionReal = position - 1;

                    if(positionReal == -1){
                        getNameShow = "-";
                    } else {
                        getNameShow = valuesCompBrnd.get(positionReal).getName();
                        getCompId = valuesCompBrnd.get(positionReal).getId();
                        Log.e("PickListSingleItem", "valuesPickList: positionReal:_ " + positionReal);
                    }


//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("result", getNameShow);
//                    returnIntent.putExtra("getId", newId);
//                    returnIntent.putExtra("getIdSV", newIdSV);
//                    returnIntent.putExtra("getCompId", getCompId);
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
                    frg = new TabletTab3FragmentDetail();
                    args.putString("result", getNameShow);
                    args.putString("getIdAdd", newIdAddr);
                    args.putString("getId", newId);
                    args.putString("getName", newName);
                    args.putString("getNumber", newNumber);
                    args.putString("getTime", newTime);
                    args.putInt("getIdSV", newIdSV);
                    frg.setArguments(args);
//                communicatorTab3.setNameAtTextViewMain(1, getNameShow);
//                communicatorTab3.MessagePickList(1,getNameShow);
                    communicatorV4.MessageTab(1,frg);
                }
            });
        }
    }

    public void setPriority(){
        itemsPickList.add(new EntryItemPickList(" - "));
        itemsPickList.add(new EntryItemPickList(" Urgent"));
        itemsPickList.add(new EntryItemPickList(" High "));
        itemsPickList.add(new EntryItemPickList(" Mid "));
        itemsPickList.add(new EntryItemPickList(" Low "));
        EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
        mListView2.setAdapter(adapterALL);
        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String getNameShow = "";
                switch (position){
                    case 0: getNameShow = "-"; break;
                    case 1: getNameShow = "Urgent"; break;
                    case 2: getNameShow = "High"; break;
                    case 3: getNameShow = "Mid"; break;
                    case 4: getNameShow = "Low"; break;
                    default: getNameShow = "None"; break;
                }
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", getNameShow);
//                returnIntent.putExtra("getId", newId);
//                returnIntent.putExtra("getIdSV", newIdSV);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
                frg = new TabletTab3FragmentDetail();
                args.putString("result", getNameShow);
                args.putString("getIdAdd", newIdAddr);
                args.putString("getId", newId);
                args.putString("getName", newName);
                args.putString("getNumber", newNumber);
                args.putString("getTime", newTime);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
//                communicatorTab3.setNameAtTextViewMain(1, getNameShow);
//                communicatorTab3.MessagePickList(1,getNameShow);
                communicatorV4.MessageTab(1,frg);
            }
        });
    }

//    public void setPayment(){
//        itemsPickList.add(new EntryItemPickList(" - "));
//        itemsPickList.add(new EntryItemPickList(" Cash"));
//        itemsPickList.add(new EntryItemPickList(" Cheque "));
//        itemsPickList.add(new EntryItemPickList(" Transfer "));
//        EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
//        mListView2.setAdapter(adapterALL);
//        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String getNameShow = "";
//                switch (position){
//                    case 0: getNameShow = "-"; break;
//                    case 1: getNameShow = "Cash"; break;
//                    case 2: getNameShow = "Cheque"; break;
//                    case 3: getNameShow = "Transfer"; break;
//                    default: getNameShow = "None"; break;
//                }
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", getNameShow);
//                returnIntent.putExtra("getId", newId);
//                returnIntent.putExtra("getIdSV", newIdSV);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//            }
//        });
//    }
//
//    public void setBank(){
//        itemsPickList.add(new EntryItemPickList(" - "));
//        itemsPickList.add(new EntryItemPickList(" Bangkok Bank"));
//        itemsPickList.add(new EntryItemPickList(" Siam Commercial Bank "));
//        itemsPickList.add(new EntryItemPickList(" Krung Thai Bank "));
//        itemsPickList.add(new EntryItemPickList(" Kasikornbank "));
//        itemsPickList.add(new EntryItemPickList(" Bank of Ayudhya "));
//        itemsPickList.add(new EntryItemPickList(" Thanachart Bank "));
//        EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
//        mListView2.setAdapter(adapterALL);
//        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String getNameShow = "";
//                switch (position){
//                    case 0: getNameShow = "-"; break;
//                    case 1: getNameShow = "Bangkok Bank"; break;
//                    case 2: getNameShow = "Siam Commercial Bank"; break;
//                    case 3: getNameShow = "Krung Thai Bank"; break;
//                    case 4: getNameShow = "Kasikornbank"; break;
//                    case 5: getNameShow = "Bank of Ayudhya"; break;
//                    case 6: getNameShow = "Thanachart Bank"; break;
//                    default: getNameShow = "None"; break;
//                }
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", getNameShow);
//                returnIntent.putExtra("getId", newId);
//                returnIntent.putExtra("getIdSV", newIdSV);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//            }
//        });
//    }
//
//    public void setRelation(int getType){
//
//        Log.e("PickListSingleItem", "valuesPickList: getType: " + getType);
//        valuesPickList = databasePickList.getPickListSearchByType(getType);
//
//        if(valuesPickList.size() != 0){
//            for(int i=0; i<valuesPickList.size(); i++){
//
//                if(i==0) {
//
//                    itemsPickList.add(new EntryItemPickList(" - "));
//                    itemsPickList.add(new EntryItemPickList(" "+valuesPickList.get(i).getValue()));
//
//                } else {
//
//                    itemsPickList.add(new EntryItemPickList(" "+valuesPickList.get(i).getValue()));
//                    Log.e("PickListSingleItem", "valuesPickList: Id: " + valuesPickList.get(i).getValue());
//
//                }
//
//            }
//            EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
//            mListView2.setAdapter(adapterALL);
//            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                    int positionReal = position - 1;
////                    String getNameShow = valuesPickList.get(positionReal).getValue();
//
//                    Log.e("PickListSingleItem", "valuesPickList: position:_ " + position);
//                    String getNameShow = "";
//                    int positionReal = position - 1;
//
//                    if(positionReal == -1){
//                        getNameShow = "-";
//                    } else {
//                        getNameShow = valuesPickList.get(positionReal).getValue();
//                        Log.e("PickListSingleItem", "valuesPickList: positionReal:_ " + positionReal);
//                    }
//
//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("result", getNameShow);
//                    returnIntent.putExtra("getIdAdd", newIdAddr);
//                    returnIntent.putExtra("getId", newId);
//                    returnIntent.putExtra("getName", newName);
//                    returnIntent.putExtra("getNumber", newNumber);
//                    returnIntent.putExtra("getTime", newTime);
//                    returnIntent.putExtra("getIdSV", newIdSV);
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
//                }
//            });
//        }
//    }

    public void setPickList(int getType){

        Log.e("PickListSingleItem", "valuesPickList: getType: " + getType);
        valuesPickList = databasePickList.getPickListSearchByType(getType);

        if(valuesPickList.size() != 0){
            for(int i=0; i<valuesPickList.size(); i++){

                if(i==0) {

                    itemsPickList.add(new EntryItemPickList(" - "));
                    itemsPickList.add(new EntryItemPickList(" "+valuesPickList.get(i).getValue()));

                } else {

                    itemsPickList.add(new EntryItemPickList(" "+valuesPickList.get(i).getValue()));
                    Log.e("PickListSingleItem", "valuesPickList: Id: " + valuesPickList.get(i).getValue());

                }

            }
            EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
            mListView2.setAdapter(adapterALL);
            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    int positionReal = position - 1;
//                    String getNameShow = valuesPickList.get(positionReal).getValue();

                    Log.e("PickListSingleItem", "valuesPickList: position:_ " + position);
                    String getNameShow = "";
                    int positionReal = position - 1;

                    if(positionReal == -1){
                        getNameShow = "-";
                    } else {
                        getNameShow = valuesPickList.get(positionReal).getValue();
                        Log.e("PickListSingleItem", "valuesPickList: positionReal:_ " + positionReal);
                    }

//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("result", getNameShow);
//                    returnIntent.putExtra("getId", newId);
//                    returnIntent.putExtra("getIdSV", newIdSV);
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();
                    frg = new TabletTab3FragmentDetail();
                    args.putString("result", getNameShow);
                    args.putString("getIdAdd", newIdAddr);
                    args.putString("getId", newId);
                    args.putString("getName", newName);
                    args.putString("getNumber", newNumber);
                    args.putString("getTime", newTime);
                    args.putInt("getIdSV", newIdSV);
                    frg.setArguments(args);
//                communicatorTab3.setNameAtTextViewMain(1, getNameShow);
//                communicatorTab3.MessagePickList(1,getNameShow);
                    communicatorV4.MessageTab(1,frg);
                }
            });
        }
    }

}
