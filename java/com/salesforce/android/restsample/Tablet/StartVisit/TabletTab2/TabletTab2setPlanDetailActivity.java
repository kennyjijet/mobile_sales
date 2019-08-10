package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBInventory.Inventory;
import com.salesforce.android.restsample.DB.Model.DBInventory.InventoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporary;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporaryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotion.Promotion;
import com.salesforce.android.restsample.DB.Model.DBPromotion.PromotionDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.CustomViewIconTextTabsActivity;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.ItemPromAfSelect.ItemPromAfSelect;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.PlanDetailPromotionActivity;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab2ProductReturnReasons;
import com.salesforce.android.restsample.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setPlanDetailActivity extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    int newIdSV;
    String newId;
    String newName;
    String newDescription;

    ProductDatabaseHelper dbProduct;
    InventoryDatabaseHelper dbInvertory;
    OrderTemporaryDatabaseHelper dbOrderTemp;

    List<Product> lsProduct;
    List<Inventory> lsInventtory;
    List<OrderTemporary> lsOrderTemp;


    TextView ed3, ed8TotalAmount;
    EditText ed1, ed2, ed4, ed6;

    TextView tvDis1, tvDis2, tvDis3;
    EditText edDis1, edDis2, edDis3;

    TextView tvUnit, tvUnitValue;

    TextView tv1Quan, tv2FOC, tv1Disc, tv2Disc, tv3Disc;

    ImageView img1disc, img2disc, img3disc;

    TextView goBack, tvDone;

    TextView tvName, tvId, tvDesc;

    TextView ttUnitPrice, tvUnitPriceValue, ttAvailable, tvAvailableValue;

    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv7, tv9;
    String ed1onShelf;
    String ed2inStock;

    String ed4Quantity;
    String ed5Discount;
    String ed6FOC;
    String ed7Unitprice;

    String edDis1st, edDis2st="", edDis3st;

    String totalPrice, totalPrice2, totalPrice3;

    double discountPercent, discountPercent2, discountPercent3;
    double discount, discount2 =0.0, discount3;
    double amountQuan;
    double price;

    //Promotion After Select
    LinearLayout lnPromTitle;
    LinearLayout lnProm1;
    LinearLayout lnProm2;
    LinearLayout lnProm3;
    LinearLayout lnseeall;

    TextView tvProm;
    TextView tvPromName;
    TextView tvPromName2;
    TextView tvPromCode;
    TextView tvPromDate;
    TextView prom1Name;
    TextView prom1Name2;
    TextView prom1Code;
    TextView prom1Date;
    TextView prom2Name;
    TextView prom2Name2;
    TextView prom2Code;
    TextView prom2Date;
    TextView prom3Name;
    TextView prom3Name2;
    TextView prom3Code;
    TextView prom3Date;
    TextView viewall;
    //    ListView mListview;
    PromotionDatabaseHelper mDBHelper;
    ArrayList<ItemPromAfSelect> itemPromAfSelects = new ArrayList<ItemPromAfSelect>();
    List<Promotion> getPromotion;
    String getPosition = "";
    String getName = "";
    String getDes = "";
    boolean isPromotion;

    String prodCode;
    int stock;
    int shelf;
    String idserver;

    // for Database Order Temporary
    int svstid;
    String productcode;
    int quantity;
    int remaining;
    double dispercent;
    int foc;

    String calPerBaht = "percent";
    String calPerBaht2 = "percent";
    String calPerBaht3 = "percent";


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

        View view = inflater.inflate(R.layout.detail_plan_productdetail2, container, false);

        newId = getArguments().getString("getId");
        newName = getArguments().getString("getName");
        newDescription = getArguments().getString("getDescription");
        newIdSV = getArguments().getInt("getIdSV");

//        newId = "00128000007uGpMAAU";
//        newName = "Kangaroo Eucalyptus Oil 8.5 cc. (1 Dozen)";
//        newDescription = "";
//        newIdSV = 2;

        // Initialize Views
        setDatabase();
        setLayout(view);

//        tvgoBack.setOnClickListener(this);

        // set Detail
        goBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanDetailActivity.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                i.putExtra("Check", "2");
//                startActivity(i);
            }
        });


        tvDone.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

                addDBOrderTemp();
                addDBInventory();

//                Intent i = new Intent(PlanDetailActivity.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                i.putExtra("Check", "2");
//                startActivity(i);
            }
        });

        ed3.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(PlanDetailActivity.this, Tab2ProductReturnReasons.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                i.putExtra("Check", "2");
//                startActivity(i);
            }
        });

        ed4.addTextChangedListener(onTextChangedListener());
        edDis1.addTextChangedListener(onTextChangedListener());
        edDis2.addTextChangedListener(onTextChangedListener());

        img1disc.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                switch (calPerBaht){
                    case "percent": img1disc.setImageResource(R.drawable.ic_bahtthai);
                        calPerBaht = "bahtthai";
                        edDis1.setText("");
                        break;
                    case "bahtthai": img1disc.setImageResource(R.drawable.ic_percentage);
                        calPerBaht = "percent";
                        edDis1.setText("");
                        break;
                }
            }
        });

        img2disc.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                switch (calPerBaht2){
                    case "percent": img2disc.setImageResource(R.drawable.ic_bahtthai);
                        calPerBaht2 = "bahtthai";
                        edDis2.setText("");
                        break;
                    case "bahtthai": img2disc.setImageResource(R.drawable.ic_percentage);
                        calPerBaht2 = "percent";
                        edDis2.setText("");
                        break;
                }
            }
        });

        img3disc.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                switch (calPerBaht3){
                    case "percent": img3disc.setImageResource(R.drawable.ic_bahtthai);
                        calPerBaht3 = "bahtthai";
                        edDis3.setText("");
                        break;
                    case "bahtthai": img3disc.setImageResource(R.drawable.ic_percentage);
                        calPerBaht3 = "percent";
                        edDis3.setText("");
                        break;
                }
            }
        });

        //Promotion After Select

        File database = getActivity().getApplicationContext().getDatabasePath(PromotionDatabaseHelper.DBNAME);
        if(false == database.exists()){

            Toast.makeText(getActivity(), "Don't have Database", Toast.LENGTH_SHORT).show();

            String str = database.getAbsolutePath();
            Log.e("File database: ", "File database: " + str);

            if(false == database.exists()){
                mDBHelper.getReadableDatabase();
                //visitTarDBHelper.getReadableDatabase();
                if(copyDatabase(getActivity())){
                    Toast.makeText(getActivity(), "YES!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Copy data error", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }

        isPromotion = true;

        setPromotionList(isPromotion, view);

        return view;
    }

    void setDatabase(){
        dbProduct = new ProductDatabaseHelper(getActivity());
        dbInvertory = new InventoryDatabaseHelper(getActivity());
        dbOrderTemp = new OrderTemporaryDatabaseHelper(getActivity());
        lsProduct = dbProduct.getProductListBySearchProdName(newName);
        getPromotion = new ArrayList<>();
        mDBHelper = new PromotionDatabaseHelper(getActivity());
        mDBHelper.openDatabase();
        getPromotion = mDBHelper.getListPromotionList();
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);

        // set Detail

        img1disc = (ImageView) v.findViewById(R.id.img1disc);
        img2disc = (ImageView) v.findViewById(R.id.img2disc);
        img3disc = (ImageView) v.findViewById(R.id.img3disc);
        goBack = (TextView) v.findViewById(R.id.tvgoCancel);
        goBack.setTypeface(fontThSarabunBold);
        tvDone = (TextView) v.findViewById(R.id.toolbar_done);
        tvDone.setTypeface(fontThSarabunBold);

        tvName = (TextView) v.findViewById(R.id.tvProductName);
        tvId = (TextView) v.findViewById(R.id.tvProductId);
        tvDesc = (TextView) v.findViewById(R.id.tvProductDescription);
        tvName.setTypeface(fontThSarabunBold);
        tvId.setTypeface(fontThSarabun);
        tvDesc.setTypeface(fontThSarabunBold);

        tvUnit = (TextView) v.findViewById(R.id.tvUnit);
        tvUnitValue = (TextView) v.findViewById(R.id.tvUnitValue);
        tvUnit.setTypeface(fontThSarabunBold);
        tvUnitValue.setTypeface(fontThSarabunBold);

        tv1Quan = (TextView) v.findViewById(R.id.tv1Quan);
        tv2FOC = (TextView) v.findViewById(R.id.tv2FOC);
        tv1Disc = (TextView) v.findViewById(R.id.tv1Disc);
        tv2Disc = (TextView) v.findViewById(R.id.tv2Disc);
        tv3Disc = (TextView) v.findViewById(R.id.tv3Disc);

        tv1Quan.setTypeface(fontThSarabunBold);
        tv2FOC.setTypeface(fontThSarabunBold);
        tv1Disc.setTypeface(fontThSarabunBold);
        tv2Disc.setTypeface(fontThSarabunBold);
        tv3Disc.setTypeface(fontThSarabunBold);

        ttUnitPrice = (TextView) v.findViewById(R.id.tvUnitPrice);
        tvUnitPriceValue = (TextView) v.findViewById(R.id.tvUnitPriceValue);

        ttAvailable = (TextView) v.findViewById(R.id.tvAvailable);
        tvAvailableValue = (TextView) v.findViewById(R.id.tvAvailableValue);

        ttUnitPrice.setTypeface(fontThSarabunBold);
        tvUnitPriceValue.setTypeface(fontThSarabunBold);
        ttAvailable.setTypeface(fontThSarabunBold);
        tvAvailableValue.setTypeface(fontThSarabunBold);

        Log.e("setPlanDetailActivity", "setPlanDetailActivity_tvName:_lsProduct.size(): "+ lsProduct.size());

        tvName.setText(lsProduct.get(0).getName());
        tvId.setText(lsProduct.get(0).getCode());
        tvDesc.setText(lsProduct.get(0).getDescription());

        //lsProduct.get(0).getUnitprice()
        tvUnitPriceValue.setText(String.valueOf(lsProduct.get(0).getUnitprice()));
        tvAvailableValue.setText(String.valueOf(lsProduct.get(0).getAvailablequantity()));
        tvUnitValue.setText(String.valueOf(lsProduct.get(0).getUom()));

        tv0 = (TextView) v.findViewById(R.id.tv0);
        tv1 = (TextView) v.findViewById(R.id.tv1);
        tv2 = (TextView) v.findViewById(R.id.tv2);
        tv3 = (TextView) v.findViewById(R.id.tv3);
        tv4 = (TextView) v.findViewById(R.id.tv4);
        tv5 = (TextView) v.findViewById(R.id.tv5);
        tv7 = (TextView) v.findViewById(R.id.tv7);
        tv9 = (TextView) v.findViewById(R.id.tv9);
        tvDis1 = (TextView) v.findViewById(R.id.tvDis1);
        tvDis2 = (TextView) v.findViewById(R.id.tvDis2);
        tvDis3 = (TextView) v.findViewById(R.id.tvDis3);
        tv0.setTypeface(fontThSarabunBold);
        tv1.setTypeface(fontThSarabunBold);
        tv2.setTypeface(fontThSarabunBold);
        tv3.setTypeface(fontThSarabunBold);
        tv4.setTypeface(fontThSarabunBold);
        tv5.setTypeface(fontThSarabunBold);
        tv7.setTypeface(fontThSarabunBold);
        tv9.setTypeface(fontThSarabunBold);

        tvDis1.setTypeface(fontThSarabunBold);
        tvDis2.setTypeface(fontThSarabunBold);
        tvDis3.setTypeface(fontThSarabunBold);
        // Inventory Database
        ed1 = (EditText) v.findViewById(R.id.ed1); // On Shelf:
        ed2 = (EditText) v.findViewById(R.id.ed2); // In Stock:
        ed3 = (TextView) v.findViewById(R.id.ed3); // Returns:

        // OrderTemporary Database
        ed4 = (EditText) v.findViewById(R.id.ed4); // Quantity:
        ed6 = (EditText) v.findViewById(R.id.ed6); // FOC:
        ed8TotalAmount = (TextView) v.findViewById(R.id.ed8); // Total Price:

        edDis1 = (EditText) v.findViewById(R.id.edDis1);
        edDis2 = (EditText) v.findViewById(R.id.edDis2);
        edDis3 = (EditText) v.findViewById(R.id.edDis3);

        ed1.setTypeface(fontThSarabunBold);
        ed2.setTypeface(fontThSarabunBold);
        ed3.setTypeface(fontThSarabunBold);
        ed4.setTypeface(fontThSarabunBold);
        ed6.setTypeface(fontThSarabunBold);
        ed8TotalAmount.setTypeface(fontThSarabunBold);

        edDis1.setTypeface(fontThSarabunBold);
        edDis2.setTypeface(fontThSarabunBold);
        edDis3.setTypeface(fontThSarabunBold);

        lnPromTitle = (LinearLayout) v.findViewById(R.id.lnPromTitle);
        lnProm1 = (LinearLayout) v.findViewById(R.id.lnProm1);
        lnProm2 = (LinearLayout) v.findViewById(R.id.lnProm2);
        lnProm3 = (LinearLayout) v.findViewById(R.id.lnProm3);
        lnseeall = (LinearLayout) v.findViewById(R.id.lnSeeall);

        tvProm = (TextView) v.findViewById(R.id.tvProm);
        prom1Name = (TextView) v.findViewById(R.id.prom1Name);
        prom1Name2 = (TextView) v.findViewById(R.id.prom1Name2);
        prom1Code = (TextView) v.findViewById(R.id.prom1Code);
        prom1Date = (TextView) v.findViewById(R.id.prom1Date);
        prom2Name = (TextView) v.findViewById(R.id.prom2Name);
        prom2Name2 = (TextView) v.findViewById(R.id.prom2Name2);
        prom2Code = (TextView) v.findViewById(R.id.prom2Code);
        prom2Date = (TextView) v.findViewById(R.id.prom2Date);
        prom3Name = (TextView) v.findViewById(R.id.prom3Name);
        prom3Name2 = (TextView) v.findViewById(R.id.prom3Name2);
        prom3Code = (TextView) v.findViewById(R.id.prom3Code);
        prom3Date = (TextView) v.findViewById(R.id.prom3Date);
        viewall = (TextView) v.findViewById(R.id.viewAll);

        tvProm.setTypeface(fontThSarabunBold);
        prom1Name.setTypeface(fontThSarabunBold);
        prom1Name2.setTypeface(fontThSarabunBold);
        prom1Code.setTypeface(fontThSarabun);
        prom1Date.setTypeface(fontThSarabun);
        prom2Name.setTypeface(fontThSarabunBold);
        prom2Name2.setTypeface(fontThSarabunBold);
        prom2Code.setTypeface(fontThSarabun);
        prom2Date.setTypeface(fontThSarabun);
        prom3Name.setTypeface(fontThSarabunBold);
        prom3Name2.setTypeface(fontThSarabunBold);
        prom3Code.setTypeface(fontThSarabun);
        prom3Date.setTypeface(fontThSarabun);
        viewall.setTypeface(fontThSarabunBold);

        lnProm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(getPromotion.get(0).getId() == null)) {
                    getPosition = getPromotion.get(0).getId();
                }
                if (!(getPromotion.get(0).getName() == null)) {
                    getName = getPromotion.get(0).getName();
                }
                if (!(getPromotion.get(0).getDescription() == null)) {
                    getDes = getPromotion.get(0).getDescription();
                }
//                Intent i = new Intent(PlanDetailActivity.this, PlanDetailPromotionActivity.class);
//                i.putExtra("getPromId", getPosition);
//                i.putExtra("getPromName", getName);
//                i.putExtra("getPromDes", getDes);
//
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                startActivity(i);
            }
        });

        lnProm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(getPromotion.get(1).getId() == null)) {
                    getPosition = getPromotion.get(1).getId();
                }
                if (!(getPromotion.get(1).getName() == null)) {
                    getName = getPromotion.get(1).getName();
                }
                if (!(getPromotion.get(1).getDescription() == null)) {
                    getDes = getPromotion.get(1).getDescription();
                }
//                Intent i = new Intent(PlanDetailActivity.this, PlanDetailPromotionActivity.class);
//                i.putExtra("getPromId", getPosition);
//                i.putExtra("getPromName", getName);
//                i.putExtra("getPromDes", getDes);
//
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                startActivity(i);
            }
        });

        lnProm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(getPromotion.get(2).getId() == null)) {
                    getPosition = getPromotion.get(2).getId();
                }
                if (!(getPromotion.get(2).getName() == null)) {
                    getName = getPromotion.get(2).getName();
                }
                if (!(getPromotion.get(2).getDescription() == null)) {
                    getDes = getPromotion.get(2).getDescription();
                }
//                Intent i = new Intent(PlanDetailActivity.this, PlanDetailPromotionActivity.class);
//                i.putExtra("getPromId", getPosition);
//                i.putExtra("getPromName", getName);
//                i.putExtra("getPromDes", getDes);
//
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", newDescription);
//                startActivity(i);
            }
        });

    }

    void setPromotionList(boolean isPromotion, View v){

        if (isPromotion){
//            itemPromAfSelects.add(new SectionItemPromAfSelect("Promotion"));

            if (getPromotion.size() > 3){
                for (int i=0;i<3;i++){
                    String string = "";
                    String str1 = "";
                    String str2 = "";
                    string = getPromotion.get(i).getName();
//                    StringBuilder sb = new StringBuilder(string);
                    int count=0;
                    int state = 0;
                    for (int j=0;j<string.length();j++){
                        if (j>22 && string.charAt(j) == ' ' && state == 0){
//                    sb.replace(j+1,j+2,"\n");
                            str1 = string.substring(0,j);
                            str2 = string.substring(j+1);
                            Log.e("str1",str1);
                            Log.e("str2",str2);

                            count++;
                            state = 1;
                        }
                    }
                    if (count == 0){
                        str1 = string;
                    }
                    if (str2 == ""){
                        String name1 = "prom"+(i+1)+"Name";
                        String name2 = "prom"+(i+1)+"Name2";
                        String code = "prom"+(i+1)+"Code";
                        String date = "prom"+(i+1)+"Date";
                        int resIDname = getResources().getIdentifier(name1, "id", getActivity().getPackageName());
                        int resIDname2 = getResources().getIdentifier(name2, "id", getActivity().getPackageName());
                        int resIDcode = getResources().getIdentifier(code, "id", getActivity().getPackageName());
                        int resIDdate = getResources().getIdentifier(date, "id", getActivity().getPackageName());
                        tvPromName = (TextView) v.findViewById(resIDname);
                        tvPromName2 = (TextView) v.findViewById(resIDname2);
                        tvPromCode = (TextView) v.findViewById(resIDcode);
                        tvPromDate = (TextView) v.findViewById(resIDdate);

                        tvPromName.setText(str1);
                        tvPromName2.setVisibility(View.GONE);
                        tvPromCode.setText(getPromotion.get(i).getNumber());
                        tvPromDate.setText(getPromotion.get(i).getEndDate());
                    }else {
                        String name1 = "prom"+(i+1)+"Name";
                        String name2 = "prom"+(i+1)+"Name2";
                        String code = "prom"+(i+1)+"Code";
                        String date = "prom"+(i+1)+"Date";
                        int resIDname = getResources().getIdentifier(name1, "id", getActivity().getPackageName());
                        int resIDname2 = getResources().getIdentifier(name2, "id", getActivity().getPackageName());
                        int resIDcode = getResources().getIdentifier(code, "id", getActivity().getPackageName());
                        int resIDdate = getResources().getIdentifier(date, "id", getActivity().getPackageName());
                        tvPromName = (TextView) v.findViewById(resIDname);
                        tvPromName2 = (TextView) v.findViewById(resIDname2);
                        tvPromCode = (TextView) v.findViewById(resIDcode);
                        tvPromDate = (TextView) v.findViewById(resIDdate);

                        tvPromName.setText(str1);
                        tvPromName2.setText(str2);
                        tvPromCode.setText(getPromotion.get(i).getNumber());
                        tvPromDate.setText(getPromotion.get(i).getEndDate());
                    }
                }
            }else {
                lnseeall.setVisibility(View.GONE);
                if (getPromotion.size() < 3){
                    if ((3 - getPromotion.size()) == 1){
                        lnProm3.setVisibility(View.GONE);
                    }else {
                        lnProm2.setVisibility(View.GONE);
                    }
                }
                for (int i=0;i<getPromotion.size();i++){
                    String string = "";
                    String str1 = "";
                    String str2 = "";
                    string = getPromotion.get(i).getName();
//                    StringBuilder sb = new StringBuilder(string);
                    int count=0;
                    int state = 0;
                    for (int j=0;j<string.length();j++){
                        if (j>22 && string.charAt(j) == ' ' && state == 0){
//                    sb.replace(j+1,j+2,"\n");
                            str1 = string.substring(0,j);
                            str2 = string.substring(j+1);
                            Log.e("str1",str1);
                            Log.e("str2",str2);

                            count++;
                            state = 1;
                        }
                    }
                    if (count == 0){
                        str1 = string;
                    }
                    if (str2 == ""){
                        String name1 = "prom"+(i+1)+"Name";
                        String name2 = "prom"+(i+1)+"Name2";
                        String code = "prom"+(i+1)+"Code";
                        String date = "prom"+(i+1)+"Date";
                        int resIDname = getResources().getIdentifier(name1, "id", getActivity().getPackageName());
                        int resIDname2 = getResources().getIdentifier(name2, "id", getActivity().getPackageName());
                        int resIDcode = getResources().getIdentifier(code, "id", getActivity().getPackageName());
                        int resIDdate = getResources().getIdentifier(date, "id", getActivity().getPackageName());
                        tvPromName = (TextView) v.findViewById(resIDname);
                        tvPromName2 = (TextView) v.findViewById(resIDname2);
                        tvPromCode = (TextView) v.findViewById(resIDcode);
                        tvPromDate = (TextView) v.findViewById(resIDdate);

                        tvPromName.setText(str1);
                        tvPromName2.setVisibility(View.GONE);
                        tvPromCode.setText(getPromotion.get(i).getNumber());
                        tvPromDate.setText(getPromotion.get(i).getEndDate());
                    }else {
                        String name1 = "prom"+(i+1)+"Name";
                        String name2 = "prom"+(i+1)+"Name2";
                        String code = "prom"+(i+1)+"Code";
                        String date = "prom"+(i+1)+"Date";
                        int resIDname = getResources().getIdentifier(name1, "id", getActivity().getPackageName());
                        int resIDname2 = getResources().getIdentifier(name2, "id", getActivity().getPackageName());
                        int resIDcode = getResources().getIdentifier(code, "id", getActivity().getPackageName());
                        int resIDdate = getResources().getIdentifier(date, "id", getActivity().getPackageName());
                        tvPromName = (TextView) v.findViewById(resIDname);
                        tvPromName2 = (TextView) v.findViewById(resIDname2);
                        tvPromCode = (TextView) v.findViewById(resIDcode);
                        tvPromDate = (TextView) v.findViewById(resIDdate);

                        tvPromName.setText(str1);
                        tvPromName2.setText(str2);
                        tvPromCode.setText(getPromotion.get(i).getNumber());
                        tvPromDate.setText(getPromotion.get(i).getEndDate());
                    }
                }
            }

        }else {
            lnProm1.setVisibility(View.GONE);
            lnProm2.setVisibility(View.GONE);
            lnProm3.setVisibility(View.GONE);
            lnseeall.setVisibility(View.GONE);
        }

    }

    // Add Database #2
    private void addDBInventory(){

        svstid = newIdSV;
        productcode = lsProduct.get(0).getCode();
        ed1onShelf = ed1.getText().toString();
        ed2inStock = ed2.getText().toString();
        if(ed1onShelf.equals("")) {
            shelf= 0;
        } else {
            shelf = Integer.parseInt(ed1onShelf);
        }

        if(ed2inStock.equals("")){
            stock = 0;
        } else {
            stock = Integer.parseInt(ed2inStock);
        }

        idserver = "";

        dbInvertory.addInventory(svstid, productcode, stock, shelf, idserver);

    }

    // Add DataBase #3
    private void addDBOrderTemp(){

        ed4Quantity = ed4.getText().toString();
//        ed5Discount = ed5.getText().toString();
        ed5Discount = "50";
        ed6FOC = ed6.getText().toString();
        ed7Unitprice = ed6.getText().toString();

        if(ed4Quantity.equals("")){
            quantity= 0;
        } else {
            quantity = Integer.parseInt(ed4Quantity);
        }


        if (ed5Discount.equals("")){
            dispercent= 0;
        } else {
            dispercent = Double.parseDouble(ed5Discount);
        }

        if(ed4Quantity.equals("")){
            remaining= 0;
        } else {
            remaining = Integer.parseInt(ed4Quantity);
        }

        if(ed6FOC.equals("")){
            foc = 0;
        } else {
            foc = Integer.parseInt(ed6FOC);
        }

        svstid = newIdSV;
        productcode = lsProduct.get(0).getCode();

//        dbOrderTemp.addOrderTemp(svstid, productcode, quantity, remaining, dispercent, foc);

        //        int idSvst;
//        String productcode;
//        int quantity;
//        int remaining;
//        int foc;
        int focreaming = 0;
        double dispercent1 = 0;
        double dispercent2 = 0;
        double dispercent3 = 0;

        int dispercent1type = 0;
        int dispercent2type = 0;
        int dispercent3type = 0;
        dbOrderTemp.addOrderTemp(svstid, productcode, quantity, remaining, foc, focreaming,
                dispercent1,dispercent2,dispercent3, dispercent1type, dispercent2type, dispercent3type);

    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                switch (calPerBaht){
                    case "percent": calEditTx8TotalAmountPercent();

                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                    case "bahtthai": calEditTx8TotalAmountBahtthaiDisc1();
                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ed4.setFocusableInTouchMode(true);

                switch (calPerBaht){
                    case "percent": calEditTx8TotalAmountPercent();

                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                    case "bahtthai": calEditTx8TotalAmountBahtthaiDisc1();
                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                switch (calPerBaht){
                    case "percent": calEditTx8TotalAmountPercent();

                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                    case "bahtthai": calEditTx8TotalAmountBahtthaiDisc1();
                        switch (calPerBaht2){
                            case "percent": calEditTx8TotalAmountPercent2();
                                break;
                            case "bahtthai": calEditTx8TotalAmountBahtthaiDisc2();
                                break;
                        }
                        break;
                }
            }
        };
    }

    void calEditTx8TotalAmountPercent(){

        ed4Quantity = ed4.getText().toString();
        ed5Discount = edDis1.getText().toString();
        DecimalFormat df = new DecimalFormat("0.00#");

        if(ed4Quantity.equals("")){

            ed8TotalAmount.setText("pls type");

        } else {

            amountQuan = Double.parseDouble(ed4Quantity);
            price = lsProduct.get(0).getUnitprice();

            if(ed5Discount.equals("")){

                discount = 0.0;

            } else {

                discountPercent = Double.parseDouble(ed5Discount);
                discountPercent = discountPercent/100;
                discount = (amountQuan * price) * discountPercent;
            }

            totalPrice = String.valueOf(df.format((amountQuan * price) - discount));
            ed8TotalAmount.setText(totalPrice);
        }

    }

    // Discount 2:
    void calEditTx8TotalAmountPercent2(){

        ed4Quantity = ed4.getText().toString();
        edDis2st = edDis2.getText().toString();
        DecimalFormat df = new DecimalFormat("0.00#");

        if(ed4Quantity.equals("")){

            ed8TotalAmount.setText("pls type");

        } else {

            amountQuan = Double.parseDouble(ed4Quantity);
            price = lsProduct.get(0).getUnitprice();

            if(edDis2st.equals("")){

                discount = 0.0;

            } else {
                discountPercent2 = Double.parseDouble(edDis2st);
                discountPercent2 = discountPercent2/100;
                //discount = (amountQuan * price) * discountPercent;
                discount2 = ((amountQuan * price) * discountPercent) * discountPercent2;
            }

            totalPrice2 = String.valueOf(df.format(((amountQuan * price) - discount)-discount2));
            ed8TotalAmount.setText(totalPrice2);
        }

    }

    void calEditTx8TotalAmountBahtthaiDisc1(){

        ed4Quantity = ed4.getText().toString();
        ed5Discount = edDis1.getText().toString();
        DecimalFormat df = new DecimalFormat("0.00#");

        if(ed4Quantity.equals("")){

            ed8TotalAmount.setText("pls type");

        } else {

            amountQuan = Double.parseDouble(ed4Quantity);
            price = lsProduct.get(0).getUnitprice();

            if(ed5Discount.equals("")){

                discount = 0.0;

            } else {

                discount = Double.parseDouble(ed5Discount);
            }

            totalPrice = String.valueOf(df.format((amountQuan * price) - discount));
            ed8TotalAmount.setText(totalPrice);
        }
    }

    void calEditTx8TotalAmountBahtthaiDisc2(){

        //edDis1.addTextChangedListener(onTextChangedListener());

        ed4Quantity = ed4.getText().toString();
        edDis2st = edDis2.getText().toString();
        DecimalFormat df = new DecimalFormat("0.00#");

        if(ed4Quantity.equals("")){

            ed8TotalAmount.setText("pls type");

        } else {

            amountQuan = Double.parseDouble(ed4Quantity);
            price = lsProduct.get(0).getUnitprice();

            if(edDis2st.equals("")){

                discount2 = 0.0;

            } else {

                discount2 = Double.parseDouble(edDis2st);
            }

            totalPrice2 = String.valueOf(df.format(((amountQuan * price) - discount) - discount2));
            ed8TotalAmount.setText(totalPrice2);
        }
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(PromotionDatabaseHelper.DBNAME);
            String outFileName = PromotionDatabaseHelper.DBLOCATION + PromotionDatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public interface Communicator {
        public void MessageBD(String OS_Name);
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
