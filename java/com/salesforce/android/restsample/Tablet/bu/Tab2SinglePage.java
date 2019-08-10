//package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
//import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
//import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrand;
//import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
//import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategory;
//import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategoryDatabaseHelper;
//import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroup;
//import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroupDatabaseHelper;
//import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProduct;
//import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProductDatabaseHelper;
////import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.TabletSinglePage.ItemSinglePage.EntryAdapterSinglePage;
////import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.TabletSinglePage.ItemSinglePage.EntryItemSinglePage;
////import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.TabletSinglePage.ItemSinglePage.ItemSinglePage;
////import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.TabletSinglePage.ItemSinglePage.SectionItemSinglePage;
//import com.salesforce.android.restsample.R;
//import com.salesforce.android.restsample.Tablet.CommunicatorV4;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryItemSinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SectionItemSinglePage;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * Created by chukeatboontae on 8/10/16.
// */
//public class Tab2SinglePage extends Fragment {
//
//    String newName;
//    String newIdAddr;
//    String newId;
//    String newNumber;
//    String newTime;
//    int newIdSV;
//
//    ListView mListView;
//
//    List<ProductBrand> mProductList1;
//    List<ProductCategory> mProductList2;
//    List<ProductGroup> mProductList3;
//    List<PurchasedProduct> mPurchasedList;
//    List<Product> mProduct;
//
//    List<Product> productListArray;
//
//    ProductBrandDatabaseHelper mDBHelper1;
//    ProductCategoryDatabaseHelper mDBHelper2;
//    ProductGroupDatabaseHelper mDBHelper3;
//    ProductDatabaseHelper mDBHelper4;
//    PurchasedProductDatabaseHelper mDBHelper5;
//
//    List<String> mProductList1st;
//    List<String> mProductList2st;
//    List<String> mProductList3st;
//    List<String> mProductList4st;
//
//    List<String> mProductList4stBrand;
//    List<String> mProductList4stCat;
//    List<String> mProductList4stGrp;
//
//    Product proList;
//    String[]  arrayString;
//
//    String nameChkGrpBrand;
//    String nameChkGrpCat;
//    String nameGrp;
//
//    String[] chkBrandArrayStr;
//    String[] chkCatArrayStr;
//    String[] chkGroupArrayStr;
//
//    String[] uniqueValuesBrand;
//    String[] uniqueValuesCat;
//    String[] uniqueValuesGroup;
//
//    List<String> listFinal;
//    List<String> listFinalCode;
//
//    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
//    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
//
//    private CommunicatorV4 communicator;
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof CommunicatorV4) {
//            communicator = (CommunicatorV4) activity;
//            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());
//
//        } else {
////            throw new ClassCastException(activity.toString()
////                    + " must implemenet MyListFragment.OnItemSelectedListener");
//
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
////        setContentView(R.layout.activity_products_one_page);
//
////        View v = inflater.inflate(R.layout.tab_2fragment_edit, container, false);
//        View v = inflater.inflate(R.layout.activity_products_one_page, container, false);
//
//
////        Bundle extras = getIntent().getExtras();
////        newIdAddr= extras.getString("getIdAdd");
////        newId= extras.getString("getId");
////        newNumber= extras.getString("getNumber");
////        newName= extras.getString("getName");
////        newTime= extras.getString("getTime");
////        newIdSV = extras.getInt("getIdSV");
//
//        newIdAddr = getArguments().getString("getIdAdd");
//        newId = getArguments().getString("getId");
//        newNumber = getArguments().getString("getNumber");
//        newName = getArguments().getString("getName");
//        newTime = getArguments().getString("getTime");
//        newIdSV = getArguments().getInt("getIdSV");
//
//        Log.e("chkId","chkAccId_"+newId);
//
//        // for this Activity
//        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
//        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
//
//        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
//        itemsALLProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
//        // *** *** *** clear set Arraylist to zero End
//
//        TextView ttAmount = (TextView) v.findViewById(R.id.total_amount);
//        TextView tvAmount = (TextView) v.findViewById(R.id.total_amount_value);
//        TextView ttFOC = (TextView) v.findViewById(R.id.foc);
//        TextView tvFOC = (TextView) v.findViewById(R.id.foc_value);
//        TextView ttQty = (TextView) v.findViewById(R.id.qty_of_products);
//        TextView tvQty = (TextView) v.findViewById(R.id.qty_of_products_value);
//
//        ttAmount.setTypeface(fontThSarabunBold);
//        tvAmount.setTypeface(fontThSarabunBold);
//        ttFOC.setTypeface(fontThSarabunBold);
//        tvFOC.setTypeface(fontThSarabunBold);
//        ttQty.setTypeface(fontThSarabunBold);
//        tvQty.setTypeface(fontThSarabunBold);
//
//        mDBHelper1 = new ProductBrandDatabaseHelper(getActivity());
//        mDBHelper2 = new ProductCategoryDatabaseHelper(getActivity());
//        mDBHelper3 = new ProductGroupDatabaseHelper(getActivity());
//        mDBHelper4 = new ProductDatabaseHelper(getActivity());
//        mDBHelper5 = new PurchasedProductDatabaseHelper(getActivity());
//
//        //Get product list in db when db exists
//        mProductList1 = mDBHelper1.getListProductBrandList();
//        mProductList2 = mDBHelper2.getListProductCategoryList();
//        mProductList3 = mDBHelper3.getListProductGroupList();
//        mPurchasedList = new ArrayList<>();
//        mPurchasedList.clear();
//        Log.e("chkPurch","chkPurch_size_1:_"+mPurchasedList.size());
//        mPurchasedList = mDBHelper5.getListPurchasedProductListBySearchAccId(newId);
//        Log.e("chkPurch","chkPurch_size_2:_"+mPurchasedList.size());
//
//        //Get product list in db when db exists String
//        mProductList1st = mDBHelper1.getListProductBrandListString();
//        mProductList2st = mDBHelper2.getListProductCategoryListString();
//        mProductList3st = mDBHelper3.getListProductGroupListString();
//        mProductList4st = mDBHelper4.getListProductListString();
//
//        // Get Brand - Cat - Group List
//        mProductList4stBrand = mDBHelper4.getListProductListBrandString();
//        mProductList4stCat = mDBHelper4.getListProductListCatString();
//        mProductList4stGrp = mDBHelper4.getListProductListGrpString();
//
//        proList = new Product();
//        productListArray = new ArrayList<>();
//
//        int countBrand=0;
//        int countCat=0;
//        int uncount=0;
//
//        arrayString = new String[500];
//        chkBrandArrayStr = new String[500];
//        chkCatArrayStr = new String[500];
//        chkGroupArrayStr = new String[500];
//        String nn = "null";
//
//        if(itemsALLProduct.size()==0){ // check run for one time #1
//
//            // ############# Classify Brand Start
//            for(int i=0; i<mProductList4stBrand.size(); i++){
//                nameChkGrpBrand = mProductList4stBrand.get(i); // mProductList4stGrp
//
//                Log.e(" countChkGrp ", "chk_tab2_nameChkGrpBrand: " + nameChkGrpBrand);
//
//                if(nameChkGrpBrand.equals(null)){
//                    Log.e(" countChkGrp ", "chk_tab2_nameChkGrpBrand:_chek_null: " + nameChkGrpBrand);
//                }
//
//                int check = nn.compareTo(nameChkGrpBrand);
//                Log.e(" countChkGrp ", "chk_tab2_check: " + check); //here
//
//                if(check > 0){
//                    chkBrandArrayStr[countBrand] = nameChkGrpBrand;
//                    Log.e(" countChkGrp ", "chk0_countChkGrp_count: " + countBrand);
//                    Log.e(" countChkGrp ", "chk0_ ============ ========== ============ ");
//                    countBrand++;
//                }else{
//                    Log.e(" countChkGrp ", "chk1_countChkGrp_count: " + uncount);
//                    Log.e(" countChkGrp ", "chk1_ ============ ========== ============ ");
//                    uncount++;
//                }
//            }
//
//            Set<String> h = new HashSet<String>(Arrays.asList(chkBrandArrayStr));
//            uniqueValuesBrand = h.toArray(new String[0]);
//            Log.e(" countChkGrp ", "chk2_ ============ ========== ============ uniqueValuesBrand: " + uniqueValuesBrand.length);
//
//            for(int m=0; m<uniqueValuesBrand.length; m++){
//                Log.e(" countChkGrp ", "chk4_ ============ ========== ============ m: " + m);
//                Integer n = new Integer(m);
//                String mmm = uniqueValuesBrand[n];
//                Log.e(" countChkGrp ", "chk4_ ============ ========== ============ mmm: " + mmm);
//
//            }
//            // ############# Classify Brand End
//
//            // ############# Classify Category Start
//            for(int i=0; i<mProductList4stCat.size(); i++){
//                nameChkGrpCat = mProductList4stCat.get(i); // mProductList4stGrp
//
//                int check = nn.compareTo(nameChkGrpCat);
//                Log.e(" countChkGrp ", "chk_tab2_checkGrp: " + check); //here
//                if(check > 0){
//                    chkCatArrayStr[countCat] = nameChkGrpCat;
//                    Log.e(" countChkGrp ", "chk0_cat_countChkGrp_count: " + countCat);
//                    Log.e(" countChkGrp ", "chk0_cat_ ============ ========== ============ ");
//                    countCat++;
//                }else{
//                    Log.e(" countChkGrp ", "chk1_countChkGrp_count: " + uncount);
//                    Log.e(" countChkGrp ", "chk1_cat_ ============ ========== ============ ");
//                    uncount++;
//                }
//            }
//
//            Set<String> h2 = new HashSet<String>(Arrays.asList(chkCatArrayStr));
//            uniqueValuesCat = h2.toArray(new String[0]);
//            Log.e(" countChkGrp ", "chk2_cat_ ============ ========== ============ uniqueValuesCat: " + uniqueValuesCat.length);
//
//            for(int m=0; m<uniqueValuesCat.length; m++){
//                Log.e(" countChkGrp ", "chk4_cat_ ============ ========== ============ m: " + m);
//                Integer n = new Integer(m);
//                String mmm = uniqueValuesCat[n];
//                Log.e(" countChkGrp ", "chk4_cat_ ============ ========== ============ mmm: " + mmm);
//            }
//            // ############# Classify Category End
//
//            // Loop in Brand -> Cat -> Group
//            List<String> stringListCatID = new ArrayList<>();
//            List<String> stringListGroupID = new ArrayList<>();
//
//            List<Product> productidList = new ArrayList<>();
//            List<String> stringListGroupCatID = new ArrayList<>();
//
//            listFinal = new ArrayList<String>();
//            listFinalCode = new ArrayList<String>();
//            final List<String> listColor = new ArrayList<String>();
//
//            String colorDefault = "#848484";
//            String colorSubtitle = "#848484";
//
//            //Purchased Products
//            if (mPurchasedList.size() != 0) {
//                itemsProduct.add(new SectionItemSinglePage("Purchased Products", "Available Qty\nPrice / Unit", "OnShelf",
//                        "InStock", "Quantity", "FOC", "Discount 1", "Amount",
//                        colorDefault));
//
//                Log.e("chkPurch", "chkPurch_size_3:_" + mPurchasedList.size());
//                for (int i = 0; i < mPurchasedList.size(); i++) {
//                    mProduct = mDBHelper4.getListProductListBySearchCode(mPurchasedList.get(i).getProdCode());
//                    for (int j = 0; j < mProduct.size(); j++) {
//                        itemsProduct.add(new EntryItemSinglePage(mProduct.get(j).getName(),
//                                mProduct.get(j).getCode() + " | " + mDBHelper3.getProductGroupNameByCatId(mProduct.get(j).getIdPdct()),
//                                mProduct.get(j).getAvailablequantity(),
//                                mProduct.get(j).getUnitprice() + " / " + mProduct.get(j).getUom(),
//                                0));
//                    }
//                }
//            }
//
//            // for(int n=1; n<uniqueValuesBrand.length; n++) {
//            for(int n=1; n<uniqueValuesBrand.length; n++) {
//                Integer n1Int = new Integer(n);
//                String idBrand = uniqueValuesBrand[n1Int];
//
//                // getColor start
//                String colorBr = new String(colorDefault);
//                colorBr = mDBHelper1.getProductBrandColorBySearchId(idBrand);
//
//                Log.e(" countChkGrp ", "chk_colorBr_colorBr: " + colorBr);
//
//                int checkcolorBr = nn.compareTo(colorBr);
//                if (checkcolorBr == 0 || checkcolorBr == 4) {
//                    colorBr = new String("#848484");
//                }
//                Log.e(" countChkGrp ", "chk_colorBr_colorBr_after: " + colorBr);
//                String nameBr = mDBHelper1.getProductBrandNameBySearchId(idBrand);
//
//                stringListCatID = mDBHelper2.getProductCatIdListBySearchBrandId(idBrand);
//                listFinal.add(nameBr);
//
//                // add item start
//                itemsProduct.add(new SectionItemSinglePage(nameBr, "Available Qty\nPrice / Unit", "OnShelf",
//                                                        "InStock", "Quantity", "FOC", "Discount 1", "Amount",
//                                                        colorBr));
//                // add item end
//
//                listFinalCode.add("null");
//                listColor.add(colorBr);
//                Log.e(" stringListCatID ", "chk10_stringListCatID.size()_ ============ : " + stringListCatID.size());
//
//                String[] stockArr = new String[stringListCatID.size()];
//                stockArr = stringListCatID.toArray(stockArr);
//
//                Set<String> hCat = new HashSet<String>(Arrays.asList(stockArr));
//                uniqueValuesGroup = hCat.toArray(new String[0]);
//
//                for (int nCat = 0; nCat < uniqueValuesGroup.length; nCat++) {
//                    Integer n2Int = new Integer(nCat);
//
//                    String nameCat = uniqueValuesGroup[n2Int];
//
//                    Log.e(" Check_Time", "chk_product_nameCat: "+ nCat + nameCat);
//
//                    stringListGroupID = mDBHelper2.getProductCatIdListBySearchNameCat(nameCat);
//
//                    stringListGroupCatID = mDBHelper4.getProductNameCatListBySearchGrpId(stringListGroupID.get(0));
//
//                    if (stringListGroupCatID.size() != 0) {
//                        // add item start
//                        itemsProduct.add(new SectionItemSinglePage(nameCat));
//                        // add item end
//
//                        listFinal.add(nameCat);
//                        listFinalCode.add("null");
//                        listColor.add(colorSubtitle); // "#FFFFCC"
//
//                        for (int i = 0; i < stringListGroupID.size(); i++) {
//                            Log.e("CheckGTSize","grSize_"+stringListGroupID.size());
//
//                            productidList = mDBHelper4.getProductListBySearchProdName(stringListGroupCatID.get(i));
//                            nameGrp = mDBHelper3.getProductGroupNameByCatId(stringListGroupID.get(i));
//                            Log.e("chkNameGr","chkNameGr_"+nameGrp);
//
//                            Log.e(" Check_Time", "chk_product_stringListCatID:_i: " + i + " " + stringListGroupID.get(i));
//                            itemsProduct.add(new EntryItemSinglePage(stringListGroupCatID.get(i),
//                                    productidList.get(i).getCode()+" | "+nameGrp,
//                                    productidList.get(i).getAvailablequantity(),
//                                    productidList.get(i).getUnitprice() + " / " + productidList.get(i).getUom(),
//                                    0));
//
//                            listFinal.add(stringListGroupCatID.get(i));
//
//                        }
//                    }
//                }
//            }
//        } // end if
//
//        mListView = (ListView) v.findViewById(R.id.list);
//
//        itemsALLProduct.addAll(itemsProduct);
//        EntryAdapterSinglePage entry = new EntryAdapterSinglePage(getActivity(), itemsALLProduct);
//        mListView.setAdapter(entry);
//
//        return v;
//
//    }
//
//}
