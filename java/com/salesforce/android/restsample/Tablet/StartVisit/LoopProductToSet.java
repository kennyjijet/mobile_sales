package com.salesforce.android.restsample.Tablet.StartVisit;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pannikar on 8/24/16 AD.
 */
public class LoopProductToSet {

    Context mContext;

    final static String TAG = "SQLite";

    // ListView lvProductALL;

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
    ArrayList<String> strA = new ArrayList<>();

    ArrayList<ItemProduct> itemsProduct = new ArrayList<ItemProduct>();
    ArrayList<ItemProduct> itemsALLProduct = new ArrayList<ItemProduct>();

    EntryAdapterProduct adapterProduct;

    boolean chkListoneTime = false;

    TabletCustomViewIconTextTabsActivity mainTab = new TabletCustomViewIconTextTabsActivity();
    private TextView tvDeliv;

    private Bundle args = new Bundle();

    LoopProductToSet(Context mContext, Bundle bb){

        this.mContext = mContext;
        this.args = bb;

    }

    void methodLoop(){

        // set Detail Start

        newIdAddr = args.getString("getIdAdd");
        newId = args.getString("getId");
        newNumber = args.getString("getNumber");
        newName = args.getString("getName");
        newTime = args.getString("getTime");
        newIdSV = args.getInt("getIdSV");

        // *** *** *** clear set Arraylist to zero Start

        strA.clear();;
        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        itemsALLProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        // *** *** *** clear set Arraylist to zero End

        //lvProductALL = (ListView) v.findViewById(R.id.listview_all); // listview_account

        mDBHelper1 = new ProductBrandDatabaseHelper(mContext);
        mDBHelper2 = new ProductCategoryDatabaseHelper(mContext);
        mDBHelper3 = new ProductGroupDatabaseHelper(mContext);
        mDBHelper4 = new ProductDatabaseHelper(mContext);

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



        // set Detail End



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


                // *** *** *** *** set_BRAND Start
                strA.add("Brand");
                strA.add(nameBr);
                // *** *** *** *** set_BRAND End


                listFinalCode.add("null");
                listColor.add(colorBr);
                Log.e(" stringListCatID ", "chk10_stringListCatID.size()_ ============ : " + stringListCatID.size());

                String[] stockArr = new String[stringListCatID.size()];
                stockArr = stringListCatID.toArray(stockArr);

                Set<String> hCat = new HashSet<String>(Arrays.asList(stockArr));
                uniqueValuesGroup = hCat.toArray(new String[0]);

                for(int nCat=0; nCat<uniqueValuesGroup.length; nCat++){
                    Integer n2Int = new Integer(nCat);

                    String nameCat  = uniqueValuesGroup[n2Int];

                    Log.e(" Check_Time", "chk_product_nameCat: " + nameCat);

                    stringListGroupID = mDBHelper2.getProductCatIdListBySearchNameCat(nameCat);

                    stringListGroupCatID = mDBHelper4.getProductNameCatListBySearchGrpId(stringListGroupID.get(0));

                    if(stringListGroupCatID.size() != 0){
                        // add item start
                        itemsProduct.add(new SectionSubItemProduct(nameCat));
                        // add item end

                        // *** *** *** *** set_BRAND Start
                        strA.add("Cat");
                        strA.add(nameCat);
                        // *** *** *** *** set_BRAND End

                        listFinal.add(nameCat);
                        listFinalCode.add("null");
                        listColor.add(colorSubtitle); // "#FFFFCC"

                        for(int i=0; i<stringListGroupID.size(); i++){

                            productidList = mDBHelper4.getProductListBySearchProdName(stringListGroupCatID.get(i));

                            Log.e(" lpd.methodLoop() ", "chk_lpd.methodLoop():_i: " + i + " "+stringListGroupID.get(i));
                            itemsProduct.add(new EntryItemProduct(stringListGroupCatID.get(i),
                                    productidList.get(0).getCode()));

                            // *** *** *** *** set_BRAND Start
                            strA.add("Pro");
                            strA.add(stringListGroupCatID.get(i));
                            // *** *** *** *** set_BRAND End

                            // *** *** *** *** set_BRAND Start
                            strA.add("Color");
                            strA.add(productidList.get(0).getCode());
                            // *** *** *** *** set_BRAND End

                            listFinal.add(stringListGroupCatID.get(i));

                        }
                    }
                }
            }
            // device
        }
    }

}
