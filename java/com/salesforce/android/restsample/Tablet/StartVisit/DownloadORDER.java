package com.salesforce.android.restsample.Tablet.StartVisit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage2;
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

public class DownloadORDER extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {

//    GetProductSinglePage getSnglePg;
    GetProductSinglePage2 getSnglePg;

    Context mContext;
    String newId;

    ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();

    List<String> listHeaderColor = new ArrayList<String>();

    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();
    HashMap<String, String> meMap = new HashMap<String, String>();

    ProductDatabaseHelper mDBProduct;
    ProductBrandDatabaseHelper mDBBrand;

    OnUpdateListener listener;
    HashMap<String, String> meMapProdBrd = new HashMap<String, String>();
    HashMap<String, String> meMapProdCat = new HashMap<String, String>();
    HashMap<String, String> meMapProd = new HashMap<String, String>();

    public interface OnUpdateListener {
        public void onUpdate(String obj,
                             ArrayList arrProductList,
                             ArrayList arrProductCode,
                             List<String> listHeaderColor,
                             ArrayList<ItemSinglePage> itemsALLProduct,
                             HashMap<String, String> meMap);
    }

    public void setUpdateListener(OnUpdateListener listener) {
        this.listener = listener;

    }


    public DownloadORDER(Context mContext, String newId,
                  HashMap<String, String> meMapProdBrdNew,
                  HashMap<String, String> meMapProdCatNew,
                  HashMap<String, String> meMapProdNew){
        this.mContext = mContext;
        this.newId = newId;
        meMapProdBrd = meMapProdBrdNew;
        meMapProdCat = meMapProdCatNew;
        meMapProd = meMapProdNew;

//        getSnglePg = new GetProductSinglePage(mContext, newId);
        getSnglePg = new GetProductSinglePage2(mContext, newId);

        mDBBrand = new ProductBrandDatabaseHelper(mContext);
        mDBProduct = new ProductDatabaseHelper(mContext);

        Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-12: " + meMapProdBrd.size());
        Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-13: " + meMapProdCat.size());
        Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-14: " + meMapProd.size());
    }

    @Override
//    protected ArrayList<ItemSinglePage> doInBackground(String... urls) {
    public ArrayList<ItemSinglePage> doInBackground(String... urls) {
        String getCode;
        String getNameBrnd = null;
        String getNameGrp = null;
        String getNameItem = null;
        String getColor = null;

        Log.e("test", "getView:_ ORDER getView:_size_SnglePg: " + getSnglePg.getResults().size());


        try {
            String colorDefault = "#848484";

            if(getSnglePg.getResults().size() != 0){

                for (int i=0; i<getSnglePg.getResults().size();i++){

                    switch (getSnglePg.getResultType().get(i)){

                        case 0:
                            break;

                        case 1: // "purchased"
                            arrProductList.add("none_purchased");
                            arrProductCode.add("none_purchased");
                            listHeaderColor.add(colorDefault);
                            itemsProduct.add(new SectionItemSinglePage("Purchased Products",
                                                                        "Available Qty\nPrice / Unit",
                                                                        "OnShelf",
                                                                        "InStock", "Quantity", "FOC", "Discount 1", "Amount",
                                                                        colorDefault));

                            break;

                        case 2:// "color"

                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);

                            break;

                        case 3: // "section"


                            getNameBrnd = getSnglePg.getResults().get(i);
                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);

                            if (getColor == "NULL"){
                                getColor = "#2c3d47";
                            }


                            // check START
                            if (meMapProdBrd.containsKey(getNameBrnd)) {
                                arrProductList.add("none_section");
                                arrProductCode.add("none_section"+getNameBrnd);
                                listHeaderColor.add(getColor);

                                Log.e("test", "getView:_ ORDER getColor_main: "+ "Brnd: "+getNameBrnd + " Color: "+ getColor);
                                itemsProduct.add(new SectionItemSinglePage(getSnglePg.getResults().get(i),
                                        "Available Qty\nPrice / Unit",
                                        "OnShelf",
                                        "InStock",
                                        "Quantity",
                                        "FOC",
                                        "Discount 1",
                                        "Amount",
                                        getColor));
                                //"#848484"));


                            } else {
                                // Definitely no such key
                                Log.e("test",  "getView:_ ORDER section_ not Have");
                            }
                            // check STOP

                            break;

                        case 4: // "sub-section"
//                                itemsProduct.add(new SectionItemSinglePage("" + getSnglePg.getResults().get(i)));
                            // SubSectionItemSinglePage
                            // check START
                            if (meMapProdCat.containsKey(getSnglePg.getResults().get(i))) {

                                arrProductList.add("none_sub-section");
                                arrProductCode.add("none_sub-section");
                                itemsProduct.add(new SubSectionItemSinglePage("" + getSnglePg.getResults().get(i)));

                            } else {
                                // Definitely no such key
                                Log.e("test",  "getView:_ ORDER sub-section_ not Have");
                            }
                            // check STOP

                            break;

                        case 5 : // "items"

                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);

                            Log.e("test", "getView:_ ORDER_getNameItem "+ getNameItem);

                            // check START
                            if (meMapProd.containsKey(getNameItem)) {
                                Log.e("test", "getView:_ ORDER_getNameItem_have "+ getNameItem);
                                arrProductList.add(getCode);
                                arrProductCode.add(getCode);

                                String str = null;
                                List<Product> lstProd = mDBProduct.getProductListBySearchProdName(getNameItem);
                                Log.e("test", "getView:_ ORDER lstProd.size() "+ lstProd.size());

                                String getAvalQuan = Integer.toString(lstProd.get(0).getAvailablequantity());
                                Log.e("test", "getView:_ ORDER lstProd.size()_getAvalQuan " + getAvalQuan);

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

                            } else {
                                // Definitely no such key
                                Log.e("test",  "DownloadALL: i=get-getCode_ not Have");
                            }
                            // check STOP

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
        Log.e("chkId","getView:_ ORDER chkSingle_2-0: "+itemsALLProduct.size());
        Log.e("OrderDeliveryActivity", "getView:_ ORDER chk_dl.listHeaderColor.size()_2: " + listHeaderColor.size());

        listener.onUpdate("testtesttesttest",
                            arrProductList,
                            arrProductCode,
                            listHeaderColor,
                            itemsALLProduct,
                            meMap);
    }
}
