package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage2;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage3;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage4;
import com.salesforce.android.restsample.Tablet.StartVisit.DownloadALL;
import com.salesforce.android.restsample.Tablet.StartVisit.DownloadORDER;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage5;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.widget.SegmentedButton3products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pannikar on 9/12/16 AD.
 */
public class Tab2SinglePageALL extends Fragment {

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;

    Context mContext;

    ListView mListView;

    TextView ttAmount, tvAmount, ttFOC, tvFOC, ttQty, tvQty;

    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();

    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProductALL = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProductORDER = new ArrayList<ItemSinglePage>();

    EntryAdapterSinglePage5 entry;
    EntryAdapterSinglePage5 entryOrder;
    private CommunicatorV4 communicatorV4;
    private ProgressBar pb;
    Typeface fontThSarabun, fontThSarabunBold;
    private String hms;
    //    private ArrayList arrOnShelf = new ArrayList();
//    private ArrayList<Integer> arrQuantity = new ArrayList<Integer>();
//    private ArrayList<Integer> arrQuantityNew = new ArrayList<Integer>();
    // TextView tvSave;

    int sngle;

    GetValueToEditText getValEdt;
    //#1 OnShelf
    private ArrayList<Integer> arrOnShelf = new ArrayList<Integer>();
    private ArrayList<Integer> arrOnShelfNew = new ArrayList<Integer>();

    //#2 InStock
    private ArrayList<Integer> arrInStock = new ArrayList<Integer>();
    private ArrayList<Integer> arrInStockNew = new ArrayList<Integer>();

    //#3 Quantity
//    private ArrayList<Integer> arrQuantity = new ArrayList<Integer>();
    private ArrayList<Integer> arrQuantityNew = new ArrayList<Integer>();
    private ArrayList<Integer> arrQuantityNewORDER = new ArrayList<Integer>();

    //#4 FOC
    private ArrayList<Integer> arrFOC = new ArrayList<Integer>();
    private ArrayList<Integer> arrFOCNew = new ArrayList<Integer>();

    //#5 Discount1
    private ArrayList<Integer> arrDiscount1 = new ArrayList<Integer>();
    private ArrayList<Integer> arrDiscount1New = new ArrayList<Integer>();

    //#6 Discount2
    private ArrayList<Integer> arrDiscount2 = new ArrayList<Integer>();
    private ArrayList<Integer> arrDiscount2New = new ArrayList<Integer>();

    //#7 Discount3
    private ArrayList<Integer> arrDiscount3 = new ArrayList<Integer>();
    private ArrayList<Integer> arrDiscount3New = new ArrayList<Integer>();

    ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();
//    List<String> listHeaderColor = new ArrayList<String>();

    ArrayList arrProductListALL = new ArrayList();
    ArrayList arrProductCodeALL = new ArrayList();
    List<String> listHeaderColorALL = new ArrayList<String>();

    ArrayList arrProductListORDER = new ArrayList();
    ArrayList arrProductCodeORDER = new ArrayList();
    List<String> listHeaderColorORDER = new ArrayList<String>();

    TextView ss_name_product, ss_qty_price_unit,
                ss_onshelf, ss_instock, ss_quantity,
                ss_foc, ss_discount1, ss_amount;

    LinearLayout lnColorSection;
    SegmentedButton3products buttons;
    Button buttons2;

    HashMap<String, String> meMapProdBrd = new HashMap<String, String>();
    HashMap<String, String> meMapProdCat = new HashMap<String, String>();
    HashMap<String, String> meMapProd = new HashMap<String, String>();

    ArrayList arrProductListOrder = new ArrayList();
    ArrayList arrProductCodeOrder = new ArrayList();
    List<String> listHeaderColorOrder = new ArrayList<String>();
    ArrayList<ItemSinglePage> itemsALLProductOrder = new ArrayList<ItemSinglePage>();

    DownloadALL dlALL;
    DownloadORDER dlORDER;

    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();

    int countProduct;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommunicatorV4) {
            communicatorV4 = (CommunicatorV4) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {


        }
    }

    @SuppressLint("ValidFragment")
    public Tab2SinglePageALL(Context mContext,

                             int sngle, int idSvst,
                             ArrayList arrProductList,
                             ArrayList arrProductCode,

                             ArrayList arrProductListOrder,
                             ArrayList arrProductCodeOrder,
                             List<String> listHeaderColorOrder,

                             HashMap<Integer, String> mapALLIndPrdNameNew,
                             HashMap<Integer, String> mapALLIndColorNew){

        this.sngle = sngle;
        this.newIdSV = idSvst;
        this.mContext = mContext;

        getValEdt = new GetValueToEditText(mContext, idSvst);

        this.arrProductList = arrProductList;
        this.arrProductCode = arrProductCode;

        this.arrProductListALL = arrProductList;
        this.arrProductCodeALL = arrProductCode;

        this.arrProductListORDER = arrProductListOrder;
        this.arrProductCodeORDER = arrProductCodeOrder;
        this.listHeaderColorORDER = listHeaderColorOrder;

        mapALLIndPrdName = mapALLIndPrdNameNew;
        mapALLIndColor = mapALLIndColorNew;

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View v = inflater.inflate(R.layout.activity_products_one_page2, container, false);

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        newIdAddr = getArguments().getString("getIdAdd");
        newId = getArguments().getString("getId");
        newNumber = getArguments().getString("getNumber");
        newName = getArguments().getString("getName");
        newTime = getArguments().getString("getTime");
        newIdSV = getArguments().getInt("getIdSV");

        itemsALLProduct  = (ArrayList<ItemSinglePage>) getArguments().getSerializable("testITEMALL");
        itemsALLProductORDER  = (ArrayList<ItemSinglePage>) getArguments().getSerializable("testITEMORDER");

        pb=(ProgressBar) v.findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        mListView = (ListView) v.findViewById(R.id.list);

//        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
//        itemsALLProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        // *** *** *** clear set Arraylist to zero End

        ttAmount = (TextView) v.findViewById(R.id.total_amount);
        tvAmount = (TextView) v.findViewById(R.id.total_amount_value);
        ttFOC = (TextView) v.findViewById(R.id.foc);
        tvFOC = (TextView) v.findViewById(R.id.foc_value);
        ttQty = (TextView) v.findViewById(R.id.qty_of_products);
        tvQty = (TextView) v.findViewById(R.id.qty_of_products_value);
        pb.setVisibility(View.VISIBLE);

        tvQty.setText("0");

        // for Header

        ss_name_product = (TextView) v.findViewById(R.id.session_purchased_product);
        ss_qty_price_unit = (TextView) v.findViewById(R.id.session_qty_price_unit);
        ss_onshelf = (TextView) v.findViewById(R.id.session_onshelf);
        ss_instock = (TextView) v.findViewById(R.id.session_instock);
        ss_quantity = (TextView) v.findViewById(R.id.session_quantity);
        ss_foc = (TextView) v.findViewById(R.id.session_foc);
        ss_discount1 = (TextView) v.findViewById(R.id.session_discount1);
        ss_amount = (TextView) v.findViewById(R.id.session_amount);

        lnColorSection = (LinearLayout) v.findViewById(R.id.lnColorSection); // lnbg // lnColorSection

        // test Start
        // Try to set Map Start
        // Create the segmented buttons
        buttons = new SegmentedButton3products(getActivity());
        buttons = (SegmentedButton3products) v.findViewById(R.id.segmented);

        buttons.addButtons(getString(R.string.friendsactivity_btn_all),
                            getString(R.string.friendsactivity_btn_prom),
                            getString(R.string.friendsactivity_btn_order));

        // First button is selected
        buttons.setPushedButtonIndex(0);
//        buttons.setPushedButtonIndex(1);
//        buttons.setPushedButtonIndex(2);
        buttons.setOnClickListener(new SegmentedButton3products.OnClickListenerSegmentedButton() {
            @Override
            public void onClick(int index) {

                Log.e("test", "chk:_index:_" + index);

                switch (index){
                    case 0: buttons.setPushedButtonIndex(0);
                        mListView.setAdapter(entry);
                        break;

                    case 1: buttons.setPushedButtonIndex(1);
                        break;

                    case 2: buttons.setPushedButtonIndex(2);

                        getValEdt = new GetValueToEditText(mContext, newIdSV);
                        int chkOrderSize = getValEdt.orderTempList.size();
                        if (chkOrderSize>0){
                            mListView.setAdapter(entryOrder);
                        }
                        break;
                }
            }
        });
        // test Stop

        arrProductList = arrProductListALL;
        arrProductCode = arrProductCodeALL;

        new DownloadImageTask().execute();

        ttAmount.setTypeface(fontThSarabunBold);
        tvAmount.setTypeface(fontThSarabunBold);
        ttFOC.setTypeface(fontThSarabunBold);
        tvFOC.setTypeface(fontThSarabunBold);
        ttQty.setTypeface(fontThSarabunBold);
        tvQty.setTypeface(fontThSarabunBold);

        ss_name_product.setTypeface(fontThSarabunBold);
        ss_qty_price_unit.setTypeface(fontThSarabunBold);
        ss_onshelf.setTypeface(fontThSarabunBold);
        ss_instock.setTypeface(fontThSarabunBold);
        ss_quantity.setTypeface(fontThSarabunBold);
        ss_foc.setTypeface(fontThSarabunBold);
        ss_discount1.setTypeface(fontThSarabunBold);
        ss_amount.setTypeface(fontThSarabunBold);


        return v;

    }

    // set Detail START
    public class DownloadImageTask extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {

        @Override
        protected ArrayList<ItemSinglePage> doInBackground(String... urls) {


            try {

                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            return itemsProduct;
        }

        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);
        }

        protected void onPostExecute(ArrayList<ItemSinglePage> itemsProduct) {
            pb.setVisibility(View.GONE);

            arrOnShelf.clear();
            for (int i = 0; i<300; i++){
                // arrOnShelf.add(i, Integer.toString(i));
                arrOnShelf.add(i);

            }

            getLoopQuantity();
            // getLoopQuantityOrderHashMap(arrProductListORDER);

            entry = new EntryAdapterSinglePage5(getActivity(),
                                                    tvFOC,
                                                    tvQty,
                                                    tvAmount,

                                                    itemsALLProduct, // chk size()
                                                    arrOnShelf,
                                                    arrQuantityNew,

                                                    arrProductCode, // this
                                                    newIdSV,
                                                    ss_name_product,
                                                    lnColorSection,

                                                    mapALLIndPrdName,
                                                    mapALLIndColor);

            entryOrder = new EntryAdapterSinglePage5(getActivity(),
                    tvFOC,
                    tvQty,
                    tvAmount,

                    itemsALLProductORDER, // this
                    arrOnShelf,
                    arrQuantityNewORDER,

                    arrProductCodeORDER, // this
                    newIdSV,
                    ss_name_product,
                    lnColorSection,

                    mapALLIndPrdName, // EDit coz just getting Order
                    mapALLIndColor); // EDit coz just getting Order


             mListView.setAdapter(entry);
//            mListView.setAdapter(entryOrder);
        }
    }


    // #1
    void getLoopOnShelf(){

    }

    // #2
    void getLoopInStock(){

    }

    // #3-1
    void getLoopQuantity(){

        arrQuantityNew.clear();

        for (int i = 0; i<arrProductList.size(); i++){
            arrQuantityNew.add(i, 0);
        }

        countProduct = 0;
        String whtType = null;
        int countPurchased =0;

         Log.e("Tab2SgPgEmp2", "chk_arrProductList.size()i: "+ arrProductList.size());



        for(int i = 0; i<arrProductList.size(); i++){


            Log.e("Tab2SgPgEmp2", "chk_arrProductList: " +i+" i:"+ arrProductList.get(i));


            if (arrProductList.get(i) == "none_section"
                    || arrProductList.get(i) == "none_sub-section"
                    || arrProductList.get(i) == "none_purchased"
                    ){

                Log.e("Tab2SgPgEmp2", "chk_arrQuantityNew:_SEC_i " +i+" countProduct: " + countProduct +" arrProductList.get(i): " + arrProductList.get(i));
                countProduct++;


                if(arrProductList.get(i) == "none_purchased"){

                    whtType = "none_purchased";

                } else if(arrProductList.get(i) == "none_section"
                        || arrProductList.get(i) == "none_sub-section"){

                    whtType = "none";
                }


            } else {
                switch (whtType){


                    case "none_purchased":
                        //getPurchased(i);
                        Log.e("Tab2SgPgEmp2", "chk_arrQuantityNew:_none_purchased_i " +i+" countProduct: " + countProduct +" " + arrQuantityNew.get(countProduct));
                        arrQuantityNew.set(countProduct, 0);
                        countProduct++;

                        break;

                    case "none": getNotNone(i);
                        break;
                }

            }



        } // end for Loop
    }

    void getNotNone(int i){
        String getProdCode = arrProductList.get(i).toString();
        getValEdt = new GetValueToEditText(mContext, newIdSV, getProdCode);

        if(getValEdt.orderTempList.size()==0){ // if not Order

            Log.e("Tab2SgPgEmp2", "chk_arrQuantityNew:_none=0_i " +i+" countProduct: " + countProduct +" " + arrQuantityNew.get(countProduct));
            countProduct++;

        }else{

            int getQuan = getValEdt.orderTempList.get(0).getQuantity();
             arrQuantityNew.set(countProduct, getQuan);

            Log.e("Tab2SgPgEmp2", "chk_arrQuantityNew:_none_i " +i+" countProduct: " + countProduct +" " + arrQuantityNew.get(countProduct));

            countProduct++;
            // FOR SHOW in list
        }
    }



    // #3-2
    void getLoopQuantityOrderHashMap(ArrayList arrProductList){

        arrQuantityNewORDER.clear();

        for (int i = 0; i<arrProductList.size(); i++){
            arrQuantityNewORDER.add(i, 0);
        }

        int countProduct = 0;

        for(int i = 0; i<arrProductList.size(); i++){

            // Log.e("Tab2SgPgEmp2", "chk_dl.arrProductList_ORDER i: " +i+" "+ arrProductList.get(i).toString());

            if ( arrProductList.get(i) == "none_section"
                    || arrProductList.get(i) == "none_sub-section"
                    || arrProductList.get(i) == "none_purchased"){


//                    if(){
                countProduct++;
//                    }

            } else { // not "none"

                String getCode = arrProductList.get(i).toString();
                getValEdt = new GetValueToEditText(mContext, newIdSV, getCode);
                int getQuan = getValEdt.orderTempList.get(0).getQuantity();
                arrQuantityNewORDER.set(countProduct, getQuan);
                countProduct++;

            }
        }
    }

    // #4
    void getLoopFOC(){

    }

    // #5
    void getLoopDiscount1(){

    }

    // #6
    void getLoopDiscount2(){

    }

    // #7
    void getLoopDiscount3(){

    }
    // set Detail END



    void downloadOrderMore(){
        dlORDER = new DownloadORDER(getActivity(), newId, meMapProdBrd, meMapProdCat, meMapProd);

        Log.e("OrderDeliveryActivity", "chk_dl.size()_1-7_frg: " + arrProductListOrder.size());

        dlORDER.setUpdateListener(new DownloadORDER.OnUpdateListener() {
            @Override
            public void onUpdate(String obj, ArrayList arrProductListNew,
                                 ArrayList arrProductCodeNew, List<String> listHeaderColorNew,
                                 ArrayList<ItemSinglePage> itemsALLProductNew, HashMap<String, String> meMap) {

                arrProductListOrder = arrProductListNew;
                arrProductCodeOrder = arrProductCodeNew;
                listHeaderColorOrder = listHeaderColorNew;
                itemsALLProductOrder = itemsALLProductNew;

                arrProductListORDER = arrProductListNew;
                arrProductCodeORDER = arrProductCodeNew;
                listHeaderColorORDER = listHeaderColorNew;
                itemsALLProductORDER = itemsALLProductNew;

                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-1:_frg: " + arrProductList.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-2:_frg: " + arrProductCode.size());
//                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-3:_frg: " + listHeaderColor.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-4:_frg: " + itemsALLProduct.size());

                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-5:_frg: " + arrProductListOrder.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-6:_frg: " + arrProductCodeOrder.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-7:_frg: " + listHeaderColorOrder.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-8:_frg: " + itemsALLProductOrder.size());

                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-9:_frg: " + itemsALLProduct.size());
                Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-10:_frg: " + itemsALLProductOrder.size());

            }
        });
        dlORDER.execute();
    }
}

