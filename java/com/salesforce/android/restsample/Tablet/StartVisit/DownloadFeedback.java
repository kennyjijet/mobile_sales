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
import com.salesforce.android.restsample.Tablet.StartVisit.Feedback.ItemSinglePageFeedback;
import com.salesforce.android.restsample.Tablet.StartVisit.Feedback.SectionItemSinglePageFeedback;
import com.salesforce.android.restsample.Tablet.StartVisit.Feedback.SubSectionItemSinglePageFeedback;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage2;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetValueToEditText;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SectionItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SubSectionItemSinglePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pannikar on 9/15/16 AD.
 */
//public class DownloadALL {
//}

public class DownloadFeedback extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {


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

//    HashMap<String, String> meMapProdBrd = new HashMap<String, String>();
//    HashMap<String, String> meMapProdCat = new HashMap<String, String>();
//    HashMap<String, String> meMapProd = new HashMap<String, String>();


    List<OrderTemporary> orderList = new ArrayList<>();
    List<Product> prodList = new ArrayList<>();

//    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
//    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();

    String prodName;
    String prodColor;

    List<Integer> listNewFeedback = new ArrayList<Integer>();

    public interface OnUpdateListener {
        public void onUpdate(String obj,
                             ArrayList arrProductList,
                             ArrayList arrProductCode,

                             ArrayList<ItemSinglePage> itemsALLProduct,
                             List<Integer> listNewFeedback
                            );
    }

    public void setUpdateListener(OnUpdateListener listener) {
        this.listener = listener;

    }


    public DownloadFeedback(Context mContext, String newId, int newIdSvst){
        this.mContext = mContext;
        this.newId = newId;
        this.newIdSvst = newIdSvst;

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

                // set initail for FeedBack
//                for (int i=0; i<getSnglePg.getResults().size(); i++){
//                    listNewFeedback.add(i, 0);
//                }

                Log.e("test", "getView:_position:_0:_listNewFeedback_: " + listNewFeedback.size());

                for (int i=0; i<getSnglePg.getResults().size();i++){

                    switch (getSnglePg.getResultType().get(i)){

                        case 0: // "purchased" //countIndex++;

                            if(countSectionPurchased==0){

                            arrProductList.add("none_purchased");
                            arrProductCode.add("none_purchased");


                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            prodColor = colorDefault;
                            prodName = "Purchased Products";

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                                itemsProduct.add(new SectionItemSinglePageFeedback("Purchased Products",
                                                                                    colorDefault));



                                countSectionPurchased++;
                            }

                            break;

                        case 1: // "purchased" //countIndex++;

                            /*
                            * SectionHeader: Index/Name/Color
                            * */
                            prodColor = colorDefault;
                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);
                            prodName = "Purchased Products";

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductList.add(getCode);
                            arrProductCode.add(getCode);

                            itemsProduct.add(new ItemSinglePageFeedback( getNameItem + " ",
                                                                         getCode+ " " ));


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

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductCode.add("none_section"+getNameBrnd);

                            itemsProduct.add(new SectionItemSinglePageFeedback( getSnglePg.getResults().get(i),
                                                                                getColor));


                            break;

                        case 4: // "sub-section" // countIndex++;

                            arrProductList.add("none_sub-section");
                            arrProductCode.add("none_sub-section");

                            /*
                            * SectionHeader: Index/Name/Color
                            * */

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            itemsProduct.add(new SubSectionItemSinglePageFeedback("" + getSnglePg.getResults().get(i)));


                            break;

                        case 5 : // "items" // countIndex++;

                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);

                            /*
                            * SectionHeader: Index/Name/Color
                            * */

                            listNewFeedback.add(countIndex, 0);
                            countIndex++;

                            arrProductList.add(getCode);
                            arrProductCode.add(getCode);

                            String str = null;
                            List<Product> lstProd = mDBProduct.getProductListBySearchProdName(getNameItem);

//                            if (getNameItem.length()>30){
//
//                                Log.e("test", "getView:_30: " + getNameItem.length());
//
//                                getNameItem = getNameItem.substring(0,29) + "...";
//                                Log.e("test", "getView:_30: cut: " + getNameItem );
//                            }

                            itemsProduct.add(new ItemSinglePageFeedback(
                                    getNameItem + " ",
                                    getCode+ " " + " | " + getNameGrp + " "));


                            break;

                        case 6 : // "groupname"
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
        listener.onUpdate("testtesttesttest",
                arrProductList,
                arrProductCode,

                itemsALLProduct,

                listNewFeedback);
        Log.e("OrderDeliveryActivity", "TabletTab1:_3_itemsALLProduct.size: " + itemsALLProduct.size());
    }
}
