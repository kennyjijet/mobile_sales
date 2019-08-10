package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

//import android.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductList;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrand;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategory;
import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroup;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroupDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.itemForProducts.EntryAdapterProduct;
import com.salesforce.android.restsample.ITEMs.itemForProducts.EntryItemProduct;
import com.salesforce.android.restsample.ITEMs.itemForProducts.ItemProduct;
import com.salesforce.android.restsample.ITEMs.itemForProducts.SectionItemProduct;
import com.salesforce.android.restsample.ITEMs.itemForProducts.SectionSubItemProduct;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.OrderDeliveryActivity;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.PlanDetailActivity;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab2Promotion;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.Detail.TabletPlan.TabletPlanActivity4Map;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletCustomViewIconTextTabsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab2 extends Fragment {

    private Communicator communicator;

    final static String TAG = "SQLite";

    ListView lvProductALL;

    List<ProductBrand> mProductList1;
    List<ProductCategory> mProductList2;
    List<ProductGroup> mProductList3;

    List<ProductList> productListArray;

    List<String> mProductList1st;
    List<String> mProductList2st;
    List<String> mProductList3st;
    List<String> mProductList4st;

    List<String> mProductList4stBrand;
    List<String> mProductList4stCat;
    List<String> mProductList4stGrp;

    List<String> listFinal;
    List<String> listFinalCode;

    String[]  arrayString;

    String[] chkBrandArrayStr;
    String[] chkCatArrayStr;
    String[] chkGroupArrayStr;

    String[] uniqueValuesBrand;
    String[] uniqueValuesCat;
    String[] uniqueValuesGroup;

    ProductBrandDatabaseHelper mDBHelper1;
    ProductCategoryDatabaseHelper mDBHelper2;
    ProductGroupDatabaseHelper mDBHelper3;
    ProductDatabaseHelper mDBHelper4;

    ProductList proList;
    String nameChkGrpBrand;
    String nameChkGrpCat;

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;

    EditText editsearch;


    // *** *** *** Clear All IMPORTANT !!!

    ArrayList<ItemProduct> itemsProduct = new ArrayList<ItemProduct>();
    ArrayList<ItemProduct> itemsALLProduct = new ArrayList<ItemProduct>();

    EntryAdapterProduct adapterProduct;

    boolean chkListoneTime = false;

    TabletCustomViewIconTextTabsActivity mainTab = new TabletCustomViewIconTextTabsActivity();
    private TextView tvDeliv;

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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_2fragment_edit, container, false);
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
        TextView tvPro = (TextView) v.findViewById(R.id.tvpromotion);
        tvDeliv = (TextView) v.findViewById(R.id.tvdelivery);
        tvPro.setTypeface(fontThSarabunBold);
        tvDeliv.setTypeface(fontThSarabunBold);

        newIdAddr = getArguments().getString("getIdAdd");
        newId = getArguments().getString("getId");
        newNumber = getArguments().getString("getNumber");
        newName = getArguments().getString("getName");
        newTime = getArguments().getString("getTime");
        newIdSV = getArguments().getInt("getIdSV");


        Log.e("OrderDeliveryActivity", "Order_0_newIdSV : " + newIdSV);// 1654711252



        // +++++++++++++++++++++++++++++++++++++++++++++++++++ Add Fragment

        //Handle when activity is recreated like on orientation Change
//        ((ActionBarActivity)getActivity()).shouldDisplayHomeUp();

//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.viewpager, new Tab3Fragment());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        // +++++++++++++++++++++++++++++++++++++++++++++++++++ Add Fragment

        // *** *** *** clear set Arraylist to zero Start


        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        itemsALLProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        // *** *** *** clear set Arraylist to zero End

        lvProductALL = (ListView) v.findViewById(R.id.listview_all); // listview_account

        mDBHelper1 = new ProductBrandDatabaseHelper(getActivity());
        mDBHelper2 = new ProductCategoryDatabaseHelper(getActivity());
        mDBHelper3 = new ProductGroupDatabaseHelper(getActivity());
        mDBHelper4 = new ProductDatabaseHelper(getActivity());

        //Get product list in db when db exists
        mProductList1 = mDBHelper1.getListProductBrandList();
        mProductList2 = mDBHelper2.getListProductCategoryList();
        mProductList3 = mDBHelper3.getListProductGroupList();

        //Get product list in db when db exists String
        mProductList1st = mDBHelper1.getListProductBrandListString();
        mProductList2st = mDBHelper2.getListProductCategoryListString();
        mProductList3st = mDBHelper3.getListProductGroupListString();
        mProductList4st = mDBHelper4.getListProductListString();

        // Get Brand - Cat - Group List
        mProductList4stBrand = mDBHelper4.getListProductListBrandString();
        mProductList4stCat = mDBHelper4.getListProductListCatString();
        mProductList4stGrp = mDBHelper4.getListProductListGrpString();

        proList = new ProductList();
        productListArray = new ArrayList<>();

        arrayString = new String[500];


        HashMap<String,String> map = new HashMap<String,String>();
        int countBrand=0;
        int countCat=0;
        int countGroup=0;
        int uncount=0;
        int classifyCount = 0;

        // Loop for check Brand Name
        List<String> chkBrandArray = new ArrayList<>();
        chkBrandArrayStr = new String[500];
        chkCatArrayStr = new String[500];
        chkGroupArrayStr = new String[500];
        String nn = "null";
        Log.e(" countChkGrp ", "countChkGrp_mProductList4st.size(): " + mProductList4st.size());

        Log.e(" countChkGrp ", "count_size_tab2: " + itemsALLProduct.size()); // count size in list view


        if(itemsALLProduct.size()==0){ // check run for one time #1

//        if(chkListoneTime == false){ // check run for one time #2

            // ############# Classify Brand Start
            //for(int i=0; i<mProductList4st.size(); i++){
            for(int i=0; i<mProductList4stBrand.size(); i++){
                nameChkGrpBrand = mProductList4stBrand.get(i); // mProductList4stGrp

                Log.e(" countChkGrp ", "chk_tab2_nameChkGrpBrand: " + nameChkGrpBrand);

                if(nameChkGrpBrand.equals(null)){
                    Log.e(" countChkGrp ", "chk_tab2_nameChkGrpBrand:_chek_null: " + nameChkGrpBrand);
                }

                int check = nn.compareTo(nameChkGrpBrand);
                Log.e(" countChkGrp ", "chk_tab2_check: " + check); //here

                if(check > 0){
                    chkBrandArrayStr[countBrand] = nameChkGrpBrand;
                    Log.e(" countChkGrp ", "chk0_countChkGrp_count: " + countBrand);
                    Log.e(" countChkGrp ", "chk0_ ============ ========== ============ ");
                    countBrand++;
                }else{
                    Log.e(" countChkGrp ", "chk1_countChkGrp_count: " + uncount);
                    Log.e(" countChkGrp ", "chk1_ ============ ========== ============ ");
                    uncount++;
                }
            }

            Set<String> h = new HashSet<String>(Arrays.asList(chkBrandArrayStr));
            uniqueValuesBrand = h.toArray(new String[0]);
            Log.e(" countChkGrp ", "chk2_ ============ ========== ============ uniqueValuesBrand: " + uniqueValuesBrand.length);

            for(int m=0; m<uniqueValuesBrand.length; m++){
                Log.e(" countChkGrp ", "chk4_ ============ ========== ============ m: " + m);
                Integer n = new Integer(m);
                String mmm = uniqueValuesBrand[n];
                Log.e(" countChkGrp ", "chk4_ ============ ========== ============ mmm: " + mmm);

            }
            // ############# Classify Brand End

            // ############# Classify Category Start
//            for(int i=0; i<mProductList4st.size(); i++){
            for(int i=0; i<mProductList4stCat.size(); i++){
                nameChkGrpCat = mProductList4stCat.get(i); // mProductList4stGrp

                int check = nn.compareTo(nameChkGrpCat);
                if(check > 0){
                    chkCatArrayStr[countCat] = nameChkGrpCat;
                    Log.e(" countChkGrp ", "chk0_cat_countChkGrp_count: " + countCat);
                    Log.e(" countChkGrp ", "chk0_cat_ ============ ========== ============ ");
                    countCat++;
                }else{
                    Log.e(" countChkGrp ", "chk1_countChkGrp_count: " + uncount);
                    Log.e(" countChkGrp ", "chk1_cat_ ============ ========== ============ ");
                    uncount++;
                }
            }

            Set<String> h2 = new HashSet<String>(Arrays.asList(chkCatArrayStr));
            uniqueValuesCat = h2.toArray(new String[0]);
            Log.e(" countChkGrp ", "chk2_cat_ ============ ========== ============ uniqueValuesCat: " + uniqueValuesCat.length);

            for(int m=0; m<uniqueValuesCat.length; m++){
                Log.e(" countChkGrp ", "chk4_cat_ ============ ========== ============ m: " + m);
                Integer n = new Integer(m);
                String mmm = uniqueValuesCat[n];
                Log.e(" countChkGrp ", "chk4_cat_ ============ ========== ============ mmm: " + mmm);
            }
            // ############# Classify Category End

            // Loop in Brand -> Cat -> Group
            List<String> stringListCatID = new ArrayList<>();
            List<String> stringListGroupID = new ArrayList<>();

            List<Product> productidList = new ArrayList<>();
            List<String> stringListGroupCatID = new ArrayList<>();
            List<String> stringListCode = new ArrayList<>();
            List<String> stringListDONEname = new ArrayList<>();

            listFinal = new ArrayList<String>();
            listFinalCode = new ArrayList<String>();
            final List<String> listColor = new ArrayList<String>();

            String colorDefault = "#FFFFFF";
            String colorSubtitle = "#FFFFCC";
            int countNum= 0;
            Log.e(" stringListCatID ", "chk9_countNum_ After ============ : " + Integer.toString(countNum));
            for(int n=1; n<uniqueValuesBrand.length; n++){
                Integer n1Int = new Integer(n);
                String idBrand = uniqueValuesBrand[n1Int];

                // getColor start
                String colorBr = new String(colorDefault);
                colorBr = mDBHelper1.getProductBrandColorBySearchId(idBrand);

                Log.e(" countChkGrp ", "chk_colorBr_colorBr: " + colorBr);

                int checkcolorBr = nn.compareTo(colorBr);
                if(checkcolorBr == 0){
                    colorBr = new String("#6699FF");
                }
                String nameBr = "Brand: " + mDBHelper1.getProductBrandNameBySearchId(idBrand);
                //stringListCatID = mDBHelper4.getProductCatIdListBySearchBrandId(idBrand);

                stringListCatID = mDBHelper2.getProductCatIdListBySearchBrandId(idBrand);
                listFinal.add(nameBr);

                // add item start
                itemsProduct.add(new SectionItemProduct(nameBr));
                // add item end

                listFinalCode.add("null");
                listColor.add(colorBr);
                Log.e(" stringListCatID ", "chk10_stringListCatID.size()_ ============ : " + stringListCatID.size());

                String[] stockArr = new String[stringListCatID.size()];
                stockArr = stringListCatID.toArray(stockArr);

                Set<String> hCat = new HashSet<String>(Arrays.asList(stockArr));
                uniqueValuesGroup = hCat.toArray(new String[0]);

                for(int nCat=0; nCat<uniqueValuesGroup.length; nCat++){
                    Integer n2Int = new Integer(nCat);
//                    String idCat = uniqueValuesGroup[n2Int];
//                    String nameCat = "  Cat: " + mDBHelper2.getProductCategoryNameBySearchId(idCat);

                    String nameCat  = uniqueValuesGroup[n2Int];

//                    Log.e(" Check_Time", "chk_product_idCat: " + idCat);
                    Log.e(" Check_Time", "chk_product_nameCat: " + nameCat);

//                    stringListGroupID = mDBHelper4.getProductGroupIdListBySearchCatId(idCat);
//                    stringListCode = mDBHelper4.getProductCodeListBySearchCatId(idCat);

                    stringListGroupID = mDBHelper2.getProductCatIdListBySearchNameCat(nameCat);

//                    // add item start
//                    itemsProduct.add(new SectionSubItemProduct(nameCat));
//                    // add item end
//
//                    listFinal.add(nameCat);
//                    listFinalCode.add("null");
//                    listColor.add(colorSubtitle); // "#FFFFCC"

                    // productidList = mDBHelper4.getProductCodeListBySearchCatId(stringListGroupID.get(0));
                    stringListGroupCatID = mDBHelper4.getProductNameCatListBySearchGrpId(stringListGroupID.get(0));

                    if(stringListGroupCatID.size() != 0){
                        // add item start
                        itemsProduct.add(new SectionSubItemProduct(nameCat));
                        // add item end

                        listFinal.add(nameCat);
                        listFinalCode.add("null");
                        listColor.add(colorSubtitle); // "#FFFFCC"

                        for(int i=0; i<stringListGroupID.size(); i++){

                            productidList = mDBHelper4.getProductListBySearchProdName(stringListGroupCatID.get(i));

                            Log.e(" Check_Time", "chk_product_stringListCatID:_i: " + i + " "+stringListGroupID.get(i));
                            itemsProduct.add(new EntryItemProduct(stringListGroupCatID.get(i),
                                    productidList.get(0).getCode()));

                            listFinal.add(stringListGroupCatID.get(i));

                        }
                    }

                    // add item start
//
//                    Log.e(" Check_Time", "chk_product_stringListCatID: " + stringListGroupID.size());
//
//                    for(int i=0; i<stringListGroupID.size(); i++){
//
//                        Log.e(" Check_Time", "chk_product_stringListCatID:_i: " + i + " "+stringListGroupID.get(i));
//                        itemsProduct.add(new EntryItemProduct(stringListGroupID.get(i),
//                                                                stringListCode.get(i)));
//
//                    }

                    // add item end

//                    listFinal.addAll(stringListGroupID);
//                    listFinalCode.addAll(stringListCode);
//                    Log.e(" stringListCatID ", "chk10_stringListGroupID.size()_ ============ : " + stringListGroupID.size());
//
//                    for(int icolor=0; icolor<stringListGroupID.size();icolor++){
//                        listColor.add(colorDefault); //"#FFFFFF"
//                    }
                }
            }

            // device

//            // set zero Start
//            chkBrandArrayStr = null;
//            chkCatArrayStr = null;
//            chkGroupArrayStr = null;
//            // set zero End

//            chkListoneTime = true;
//
        } // end if

        itemsALLProduct.addAll(itemsProduct);
        adapterProduct = new EntryAdapterProduct(getActivity(), itemsALLProduct);
        lvProductALL.setAdapter(adapterProduct);



//        adapterALL2 = new ListProductBrCatGrpAdapterALL3(getActivity(), listFinal, listFinalCode); // stringList
//        adapterALL2.setBGcolorStList(listColor);
//
//
//        //Set adapter for listview
//        lvProductALL.setAdapter(adapterALL2);
//        Log.e(" stringListCatID ", "chk9_countNum_ Before ============ : " + Integer.toString(countNum));
//
        lvProductALL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String getName = listFinal.get(position);
                String get0id = mDBHelper4.getProductDetailidBySearchName(getName);
                String get1name = mDBHelper4.getProductDetailnameBySearchName(getName);
                String get2detail = mDBHelper4.getProductDetaildesBySearchName(getName);
//                Log.e("position", "Done_id: " + get0id);
//                Log.e("position", "Done_name: " + get1name);
//                Log.e("position", "Done_description: " + get2detail);
//                Log.e("position", "Done_position: " + Integer.toString(position));
//
//                Intent i = new Intent(getActivity(), PlanDetailActivity.class);
////                i.putExtra("getId", get0id);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdProduct", get0id); //here
//                i.putExtra("getName", get1name);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("getDescription", get2detail);
//                startActivity(i);


                Fragment frg = new TabletTab2setPlanDetailActivity();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putString("getIdProduct", get0id); //here
                args.putString("getName", get1name);
                args.putInt("getIdSV", newIdSV);
                args.putString("getDescription", get2detail);
                frg.setArguments(args);
                communicator.MessageTab2("tvitemproduct", frg);

            }
        });

        tvPro.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                Fragment frg = new TabletTab2Promotion();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putString("getIdAdd", newIdAddr);
                args.putString("getName", newName);
                args.putString("getTime", newTime);
                args.putString("getNumber", newNumber);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab2("tvpromotion", frg);
            }
        });

        tvDeliv.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

                Fragment frg = new TabletTab2setOrderDeliveryActivity();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putString("getIdAdd", newIdAddr);
                args.putString("getName", newName);
                args.putString("getTime", newTime);
                args.putString("getNumber", newNumber);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab2("tvdelivery", frg);

            }
        });

//        tvDeliv.setOnClickListener(this);
        // Add EditText to Search here

        // Insert Search Form
        // Locate the EditText in listview_main.xml
        editsearch = (EditText) v.findViewById(R.id.search);
        editsearch.setTypeface(fontThSarabun);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());

//                adapterALL2.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }
        });

        return v;
    }


    public interface Communicator {
        public void MessageTab2(String OS_Name, Fragment frg);
    }

}
