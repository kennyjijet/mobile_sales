package com.salesforce.android.restsample.Tablet.StartVisit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporary;
import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroupDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProductDatabaseHelper;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage2;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetValueToEditText;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SectionItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SubSectionItemSinglePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pannikar on 9/15/16 AD.
 */
//public class DownloadALL {
//}

public class DownloadALL extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {


//    GetProductSinglePage getSnglePg;
    GetProductSinglePage2 getSnglePg;
    Context mContext;
    String newId;
    int newIdSvst;

    ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();


    ArrayList arrProdBrdName = new ArrayList();
    ArrayList arrProdBrdColor = new ArrayList();


    // List<String> listHeaderColor = new ArrayList<String>();

    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();

    ProductDatabaseHelper mDBProduct;
    ProductBrandDatabaseHelper mDBBrand;

//    ProductBrandDatabaseHelper mDBBrand;
    ProductCategoryDatabaseHelper mDBCategory;
    ProductGroupDatabaseHelper mDBGroup;
//    ProductDatabaseHelper mDBProduct;
    PurchasedProductDatabaseHelper mDBPurchased;

    OnUpdateListener listener;

    GetValueToEditText getValEdt;

    HashMap<String, String> meMapProdBrd = new HashMap<String, String>();
    HashMap<String, String> meMapProdCat = new HashMap<String, String>();
    HashMap<String, String> meMapProd = new HashMap<String, String>();


    List<OrderTemporary> orderList = new ArrayList<>();
    List<Product> prodList = new ArrayList<>();

    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();
    HashMap<Integer, Integer> mapOrdIndPrdcode =new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> mapOrdPrdcodeQuan =new HashMap<Integer, Integer>();

    List<Integer> listNewFeedback = new ArrayList<Integer>();

    String prodName;
    String prodColor;

    public interface OnUpdateListener {
        public void onUpdate(String obj,
                             ArrayList arrProductList,
                             ArrayList arrProductCode,
                             // List<String> listHeaderColor,
                             ArrayList<ItemSinglePage> itemsALLProduct,
                             HashMap<String, String> meMapProdBrd,
                             HashMap<String, String> meMapProdCat,
                             HashMap<String, String> meMapProd,

                             HashMap<Integer, String> mapALLIndPrdName,
                             HashMap<Integer, String> mapALLIndColor,
                             List<Integer> listNewFeedback);
    }

    public void setUpdateListener(OnUpdateListener listener) {
        this.listener = listener;

    }


    public DownloadALL(Context mContext, String newId, int newIdSvst){
        this.mContext = mContext;
        this.newId = newId;
        this.newIdSvst = newIdSvst;

//        getSnglePg = new GetProductSinglePage(mContext, newId);
        getSnglePg = new GetProductSinglePage2(mContext, newId);

        mDBBrand = new ProductBrandDatabaseHelper(mContext);
        mDBCategory = new ProductCategoryDatabaseHelper(mContext);
        mDBGroup = new ProductGroupDatabaseHelper(mContext);
        mDBProduct = new ProductDatabaseHelper(mContext);
        mDBPurchased = new PurchasedProductDatabaseHelper(mContext);
    }

    @Override
    public ArrayList<ItemSinglePage> doInBackground(String... urls) {
        String getCode;
        String getNameBrnd = null;
        String getNameGrp = null;
        String getNameItem = null;
        String getColor = null;

        int countIndex = 0;

        int countSectionPurchased=0;


        Log.e("test", "getView:_ORDER_size_SnglePg: " + getSnglePg.getResults().size());

        try {
            String colorDefault = "#848484";

            if(getSnglePg.getResults().size() != 0){

                for (int i=0; i<getSnglePg.getResults().size();i++){

                    switch (getSnglePg.getResultType().get(i)){

//                        case 0: // "purchased" //countIndex++;
//                            arrProductList.add("none_purchased");
//                            arrProductCode.add("none_purchased");
//
//
//                            /*
//                            * SectionHeader: Index/Name/Color
//                            * */
//                            prodColor = colorDefault;
//                            prodName = "Purchased Products";
//                            mapALLIndPrdName.put(countIndex, prodName);
//                            mapALLIndColor.put(countIndex, prodColor);
//                            countIndex++;
//
//                            // if countSectionPurchased=0 // it's ok
//                            if(countSectionPurchased==0){
////                                arrProductList.add("none_purchased");
////                                arrProductCode.add("none_purchased");
////
////
////                            /*
////                            * SectionHeader: Index/Name/Color
////                            * */
////                                prodColor = colorDefault;
////                                prodName = "Purchased Products";
////                                mapALLIndPrdName.put(countIndex, prodName);
////                                mapALLIndColor.put(countIndex, prodColor);
////                                countIndex++;
//
//                                itemsProduct.add(new SectionItemSinglePage
//                                        ("Purchased Products",
//                                                "Available Qty\nPrice / Unit",
//                                                "OnShelf",
//                                                "InStock", "Quantity", "FOC", "Discount 1", "Amount",
//                                                colorDefault));
//                                countSectionPurchased++;
//                            }
//
//
//                            break;

                        case 0: // "purchased" //countIndex++;

                            if(countSectionPurchased==0){

                            arrProductList.add("none_purchased");
                            arrProductCode.add("none_purchased");


                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            prodColor = colorDefault;
                            prodName = "Purchased Products";
                            mapALLIndPrdName.put(countIndex, prodName);
                            mapALLIndColor.put(countIndex, prodColor);

                                listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            // if countSectionPurchased=0 // it's ok
//                            if(countSectionPurchased==0){
//                                arrProductList.add("none_purchased");
//                                arrProductCode.add("none_purchased");
//
//
//                            /*
//                            * SectionHeader: Index/Name/Color
//                            * */
//                                prodColor = colorDefault;
//                                prodName = "Purchased Products";
//                                mapALLIndPrdName.put(countIndex, prodName);
//                                mapALLIndColor.put(countIndex, prodColor);
//                                countIndex++;

                                itemsProduct.add(new SectionItemSinglePage
                                        ("Purchased Products",
                                                "Available Qty\nPrice / Unit",
                                                "OnShelf",
                                                "InStock", "Quantity", "FOC", "Discount 1", "Amount",
                                                colorDefault));
                                countSectionPurchased++;
                            } else if(getSnglePg.getResults().get(i) == "purchased") {
//                                arrProductList.add("none_purchased");
//                                arrProductCode.add("none_purchased");
//
//                                countIndex++;
                            }


                            break;

                        case 1: // "purchased" //countIndex++;
//                            arrProductList.add("none_purchased");
//                            arrProductCode.add("none_purchased");
//
//
//                            /*
//                            * SectionHeader: Index/Name/Color
//                            * */
                            prodColor = colorDefault;
                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);
                            prodName = "Purchased Products";
                            mapALLIndPrdName.put(countIndex, prodName);
                            mapALLIndColor.put(countIndex, prodColor);

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductList.add(getCode);
                            arrProductCode.add(getCode);

//                            itemsProduct.add(new SectionItemSinglePage
//                                    (getNameItem,
//                                            "Available Qty\nPrice / Unit",
//                                            "OnShelf",
//                                            "InStock", "Quantity", "FOC", "Discount 1", "Amount",
//                                            colorDefault));

                            itemsProduct.add(new EntryItemSinglePage(
                                                    getNameItem + " ",
                                                    getCode+ " " + " | " + getNameGrp + " ",
                                                    0,
                                                    0 + " / " + 0,
                                                    0));

                            break;



//                        case 2:// "color"
//                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);
//                            break;

                        case 3: // "section" // countIndex++;
                            arrProductList.add("none_section");

                            getNameBrnd = getSnglePg.getResults().get(i);
                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);


                            if (getColor == "NULL"){
                                getColor = "#2c3d47";
                            }

                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            prodColor = getColor;
                            prodName = getNameBrnd;
                            mapALLIndPrdName.put(countIndex, prodName);
                            mapALLIndColor.put(countIndex, prodColor);

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductCode.add("none_section"+getNameBrnd);

                            itemsProduct.add(new SectionItemSinglePage(
                                    getSnglePg.getResults().get(i),
                                    "Available Qty\nPrice / Unit",
                                    "OnShelf",
                                    "InStock",
                                    "Quantity",
                                    "FOC",
                                    "Discount 1",
                                    "Amount",
                                    getColor));
                            break;

                        case 4: // "sub-section" // countIndex++;
//                                itemsProduct.add(new SectionItemSinglePage("" + getSnglePg.getResults().get(i)));
                            // SubSectionItemSinglePage
                            arrProductList.add("none_sub-section");
                            arrProductCode.add("none_sub-section");

                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            mapALLIndPrdName.put(countIndex, prodName);
                            mapALLIndColor.put(countIndex, prodColor);

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            itemsProduct.add(new SubSectionItemSinglePage("" + getSnglePg.getResults().get(i)));
                            break;

                        case 5 : // "items" // countIndex++;

                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);

                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            mapALLIndPrdName.put(countIndex, prodName);
                            mapALLIndColor.put(countIndex, prodColor);

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductList.add(getCode);
                            arrProductCode.add(getCode);

                            String str = null;
                            List<Product> lstProd = mDBProduct.getProductListBySearchProdName(getNameItem);
                            String getAvalQuan = Integer.toString(lstProd.get(0).getAvailablequantity());

                            int getAvalQuanInt = Integer.parseInt(getAvalQuan);
                            String getUnitprice = Double.toString(lstProd.get(0).getUnitprice());
                            String getUom = lstProd.get(0).getUom();

                            if (getNameItem.length()>30){

                                Log.e("test", "getView:_30: " + getNameItem.length());

                                getNameItem = getNameItem.substring(0,29) + "...";
                                Log.e("test", "getView:_30: cut: " + getNameItem );
                            }

                            itemsProduct.add(new EntryItemSinglePage(
                                    getNameItem + " ",
                                    getCode+ " " + " | " + getNameGrp + " ",
                                    getAvalQuanInt,
                                    getUnitprice + " / " + getUom,
                                    0));
                            break;

                        case 6 : // "groupname"
//                            arrProductList.add("none_groupname");
                            getNameGrp = getSnglePg.getResults().get(i);
                            break;

                    }
                }
            }

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return itemsProduct;
    }

    public void onProgressUpdate(Integer... progress){
//            pb.setProgress(progress[0]);
    }

    public void onPostExecute(ArrayList<ItemSinglePage> itemsProduct) {

        itemsALLProduct.addAll(itemsProduct);

        getSome();
        getLoopMapHeader();


//        if (listNewFeedback.size()>0){
//            listener.onUpdate("testtesttesttest",
//                    arrProductList,
//                    arrProductCode,
//
//                    itemsALLProduct,
//                    meMapProdBrd,
//                    meMapProdCat,
//                    meMapProd,
//
//                    mapALLIndPrdName,
//                    mapALLIndColor,
//                    listNewFeedback);
//        }



        if (orderList.size()==0){
            listener.onUpdate("testtesttesttest",
                    arrProductList,
                    arrProductCode,

                    itemsALLProduct,
                    meMapProdBrd,
                    meMapProdCat,
                    meMapProd,

                    mapALLIndPrdName,
                    mapALLIndColor,
                    listNewFeedback);
        }
        //if (meMapProd.size() != 0){
        else if (orderList.size()>0 && meMapProd.size() != 0){
            listener.onUpdate("testtesttesttest",
                    arrProductList,
                    arrProductCode,
                    itemsALLProduct,
                    meMapProdBrd,
                    meMapProdCat,
                    meMapProd,

                    mapALLIndPrdName,
                    mapALLIndColor,
                    listNewFeedback);
        }
    }

    void getSome(){

        for(int i = 0; i<arrProductList.size(); i++){
            String getCode = arrProductList.get(i).toString();

            getValEdt = new GetValueToEditText(mContext, newIdSvst);

            orderList = getValEdt.orderTempList;

            if(orderList.size()==0){


            }else{

                for(int j=0; j<orderList.size(); j++){
                    String getProdCode = orderList.get(j).getProductcode();
                    int getQuan = orderList.get(j).getQuantity();
                    String getQuanStr = Integer.toString(getQuan);

                    prodList = mDBProduct.getListProductListBySearchCode(getProdCode);

                    String getProdName = prodList.get(0).getName();
                    meMapProd.put(getProdName,getQuanStr);

                    String prodBrd = mDBBrand.getProductBrandNameBySearchId(prodList.get(0).getIdPdbr());
                    String prodCat = mDBCategory.getProductCategoryNameBySearchId(prodList.get(0).getIdPdct());

                    meMapProdBrd.put(prodBrd, prodList.get(0).getIdPdbr());
                    meMapProdCat.put(prodCat, prodList.get(0).getIdPdct());

                }

//                Iterator myVeryOwnIterator = meMapProdBrd.keySet().iterator();
//                while(myVeryOwnIterator.hasNext()) {
//                    String key=(String)myVeryOwnIterator.next();
//                    String value=(String)meMapProdBrd.get(key);
//                    Log.e("test", "DownloadALL: meMapProdBrdCat Key: "+key+" Value: "+value);
//                }
//
//                // Key might be present...
//                if (meMapProd.containsKey("3020204")) {
//                    // Okay, there's a key but the value is null
//                    String value=(String)meMapProd.get("3020204");
//                    Log.e("test",  "DownloadALL: i=get-getCode_ yes Have " + value);
//                } else {
//                    // Definitely no such key
//                    Log.e("test",  "DownloadALL: i=get-getCode_ not Have");
//                }

            }
        }

    }

    // #3-0 #index/name/color
    void getLoopMapHeader(){

        for(int key = 0; key< mapALLIndPrdName.size(); key++){
            String value = mapALLIndPrdName.get(key);
            String color = mapALLIndColor.get(key);
            // Log.e("Tab2SgPgEmp2", "getLoopMapHeader key: "+key +" value: "+value+" color: "+color);

        } // end for Loop
    }

    // #3-1
    void getLoopMapIndexProductcode(){

        int countProduct = 0;

        for(int i = 0; i<arrProductList.size(); i++){

            Log.e("Tab2SgPgEmp2", "chk_dl.arrProductList_ALL i: " +i +" "+ arrProductList.get(i).toString());

            if (arrProductList.get(i) == "none_section" || arrProductList.get(i) == "none_sub-section" ){

                mapOrdIndPrdcode.put(countProduct, 0);
                countProduct++;

            } else { // productCode
                int getProdCodeInt = Integer.parseInt(arrProductList.get(i).toString());
                mapOrdIndPrdcode.put(countProduct, getProdCodeInt);
                countProduct++;
            }
        } // end for Loop
    }

    // #3-2
    void getLoopMapProductcodeCode(){

        int countProduct = 0;

        for(int i = 0; i<arrProductList.size(); i++){

            Log.e("Tab2SgPgEmp2", "chk_dl.arrProductList_ALL i: " +i +" "+ arrProductList.get(i).toString());

            if (arrProductList.get(i) == "none_section" || arrProductList.get(i) == "none_sub-section" ){

                mapOrdIndPrdcode.put(countProduct, 0);
                countProduct++;

            } else { // productCode
                int getProdCodeInt = Integer.parseInt(arrProductList.get(i).toString());
                mapOrdIndPrdcode.put(countProduct, getProdCodeInt);
                countProduct++;
            }
        } // end for Loop
    }

}
