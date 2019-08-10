package com.salesforce.android.restsample.Tablet.StartVisit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SectionItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SubSectionItemSinglePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 9/15/16 AD.
 */
//public class DownloadALL {
//}

public class DownloadPROMOTION extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {


    GetProductSinglePage getSnglePg;
    Context mContext;
    String newId;

    ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();

    List<String> listHeaderColor = new ArrayList<String>();

    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();

    ProductDatabaseHelper mDBProduct;
    ProductBrandDatabaseHelper mDBBrand;

    OnUpdateListener listener;


    public interface OnUpdateListener {
        public void onUpdate(String obj,
                             ArrayList arrProductList,
                             ArrayList arrProductCode,
                             List<String> listHeaderColor,
                             ArrayList<ItemSinglePage> itemsALLProduct);
    }

    public void setUpdateListener(OnUpdateListener listener) {
        this.listener = listener;

    }


    DownloadPROMOTION(Context mContext, String newId){
        this.mContext = mContext;
        this.newId = newId;

        getSnglePg = new GetProductSinglePage(mContext, newId);
        mDBBrand = new ProductBrandDatabaseHelper(mContext);
        mDBProduct = new ProductDatabaseHelper(mContext);
    }

    @Override
//    protected ArrayList<ItemSinglePage> doInBackground(String... urls) {
    public ArrayList<ItemSinglePage> doInBackground(String... urls) {
        String getCode;
        String getNameBrnd = null;
        String getNameGrp = null;
        String getNameItem = null;
        String getColor = null;

        Log.e("test", "getView:_size_SnglePg: " + getSnglePg.getResults().size());

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
                            itemsProduct.add(new SectionItemSinglePage
                                    ("Purchased Products",
                                            "Available Qty\nPrice / Unit",
                                            "OnShelf",
                                            "InStock", "Quantity", "FOC", "Discount 1", "Amount",
                                            colorDefault));

                            break;

                        case 2:// "color"

                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);

                            break;

                        case 3: // "section"
                            arrProductList.add("none_section");

                            getNameBrnd = getSnglePg.getResults().get(i);
                            getColor = mDBBrand.getProductBrandColorBySearchName(getNameBrnd);



                            if (getColor == "NULL"){
                                getColor = "#2c3d47";
                            }

                            arrProductCode.add("none_section"+getNameBrnd);
                            listHeaderColor.add(getColor);

                            Log.e("test", "getView:_getColor_main: "+ "Brnd: "+getNameBrnd + " Color: "+ getColor);
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
                            //"#848484"));

                            break;

                        case 4: // "sub-section"
//                                itemsProduct.add(new SectionItemSinglePage("" + getSnglePg.getResults().get(i)));
                            // SubSectionItemSinglePage
                            arrProductList.add("none_sub-section");
                            arrProductCode.add("none_sub-section");
                            itemsProduct.add(new SubSectionItemSinglePage("" + getSnglePg.getResults().get(i)));
                            break;

                        case 5 : // "items"

                            getCode = getSnglePg.getResultId().get(i);
                            getNameItem = getSnglePg.getResults().get(i);

                            Log.e("test", "getView:_lstProd_getNameItem "+ getNameItem);

                            arrProductList.add(getCode);
                            arrProductCode.add(getCode);

                            String str = null;
                            List<Product> lstProd = mDBProduct.getProductListBySearchProdName(getNameItem);
                            Log.e("test", "getView:_lstProd.size() "+ lstProd.size());

                            String getAvalQuan = Integer.toString(lstProd.get(0).getAvailablequantity());
                            Log.e("test", "getView:_lstProd.size()_getAvalQuan " + getAvalQuan);

                            int getAvalQuanInt = Integer.parseInt(getAvalQuan);
                            String getUnitprice = Double.toString(lstProd.get(0).getUnitprice());
                            String getUom = lstProd.get(0).getUom();

//                            Log.e("test", "getView:_List<Product>:-0 "+ lstProd.get(0).getAvailablequantity());
//                            Log.e("test", "getView:_List<Product>:-1 "+ lstProd.get(0).getUnitprice());
//                            Log.e("test", "getView:_List<Product>:-2 "+ lstProd.get(0).getUom());

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
                            arrProductList.add("none_groupname");
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
        Log.e("chkId","chkSingle_2-0: "+itemsALLProduct.size());
        Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_2: " + listHeaderColor.size());

        listener.onUpdate("testtesttesttest",
                            arrProductList,
                            arrProductCode,
                            listHeaderColor,
                            itemsALLProduct);

    }
}
