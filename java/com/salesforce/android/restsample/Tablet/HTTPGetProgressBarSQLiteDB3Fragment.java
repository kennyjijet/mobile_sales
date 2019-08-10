package com.salesforce.android.restsample.Tablet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBCompetitorBrand.CompetitorBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBInventory.InventoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBInvoice.Invoice;
import com.salesforce.android.restsample.DB.Model.DBInvoice.InvoiceDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBInvoiceDetail.InvoiceDetailDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBLastModified.LastModified;
import com.salesforce.android.restsample.DB.Model.DBLastModified.LastModifiedDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPickList.PickListDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrand;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductCategory.ProductCategoryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductGroup.ProductGroupDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotion.PromotionDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteria.PromotionCriteriaDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaBenefit.PromotionCriteriaBenefitDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaDetail.PromotionCriteriaDetailDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProduct;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisit;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisitDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSpecialBarcode.SpecialBarcodeDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSpecialDiscount.SpecialDiscountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSpecialPrice.SpecialPriceDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBTarget.TargetDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBTradeAgreement.TradeAgreementDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBTradePromotion.TradePromotionDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBVisitTarget.VisitTargetDatabaseHelper;
import com.salesforce.android.restsample.Library.ZipFileHepler;
import com.salesforce.android.restsample.MainActivitySyncData.AccountEditSyncClass.EditAccountSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.AccountSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.AddressSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.ContactsSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.SpecialBarcodeSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.SpecialDiscountSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.SpecialPriceSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.TradeAgreementSync;
import com.salesforce.android.restsample.MainActivitySyncData.AccountInfoSync.TradePromotionSync;
import com.salesforce.android.restsample.MainActivitySyncData.CheckDoubleNull;
import com.salesforce.android.restsample.MainActivitySyncData.CheckIntNull;
import com.salesforce.android.restsample.MainActivitySyncData.CheckLongNull;
import com.salesforce.android.restsample.MainActivitySyncData.CheckNull;
import com.salesforce.android.restsample.MainActivitySyncData.GetArray;
import com.salesforce.android.restsample.MainActivitySyncData.GetJSONArray;
import com.salesforce.android.restsample.MainActivitySyncData.GetJSONObject;
import com.salesforce.android.restsample.MainActivitySyncData.InvoiceSync.InvoiceSync;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.Competitors;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.ProductBrands;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.Products;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.Promotions;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.UserConfig;
import com.salesforce.android.restsample.MainActivitySyncData.MasterDataSync.VisitTarget;
import com.salesforce.android.restsample.MainActivitySyncData.SalesVisitEditSyncClass.EditSalesVisitSync;
import com.salesforce.android.restsample.MainActivitySyncData.SalesVisitSync.SalesVisitSync;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.Detail.TabletDetailProgressBar;
import com.salesforce.android.restsample.Tablet.Master.TabletMasterFragmentHome;
import com.salesforce.android.restsample.sf.GlobalState;
import com.salesforce.android.restsample.sf.OAuthTokens;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/1/2016 AD.
 */
// public class HTTPGetProgressBarSQLiteDB3Fragment extends ActionBarActivity { //} Activity {
public class HTTPGetProgressBarSQLiteDB3Fragment extends Fragment{// implements View.OnClickListener {

    // final Context context = this;
    private Communicator communicator;
    String jsonResultSV;
    String jsonResultAC;
    String jsonResultInv;
    String jsonResultImage;
    //    Button bt;
    TextView txt, txt2, txt3, txt4;

    // Response
    String responseServer, responseHttp;
    String accountNameFinal;

    int countNumAcc;

    String[] accList = new String[22];
    JSONArray accountIdL; // = new JSONArray();

    String[] acc = new String[22];
    int countTarget;

    int myProgress;
    ProgressBar progressBar4, progressBar5, progressBar6, progressBar7, progressBar8;
    TextView tv01, tv02, tv03, tv04;

    // ********************* insert Data to Table Start (SQLite 00)
    String tbVisitTarId = null;
    VisitTargetDatabaseHelper visitTarDBHelper;

    PromotionDatabaseHelper promotionDBHelper;
    PromotionCriteriaDatabaseHelper promotionCriDBHelper;
    PromotionCriteriaDetailDatabaseHelper promotionCriDetDBHelper;
    PromotionCriteriaBenefitDatabaseHelper promotionCriBenDBHelper;

    TargetDatabaseHelper targetDBHelper;
    ProductGroupDatabaseHelper productGrpDBHelper;
    ProductCategoryDatabaseHelper productCtDBHelper;
    ProductBrandDatabaseHelper productBrdDBHelper;

    AccountDatabaseHelper accountDatabaseHelper;
    SalesVisitDatabaseHelper salesVisitDatabaseHelper;
    InventoryDatabaseHelper invenDBHelper;

    ProductDatabaseHelper productDBHelper;
    PurchasedProductDatabaseHelper purchaseDBHelper;

    AddressDatabaseHelper addressDBHelper;
    ContactDatabaseHelper contactDBHelper;

    PickListDatabaseHelper picklistDBHelper;

    CompetitorBrandDatabaseHelper compBrandDBHelper;

    InvoiceDatabaseHelper invoiceDatabaseHelper;
    InvoiceDetailDatabaseHelper invDetDatabaseHelper;

    TradeAgreementDatabaseHelper tradeAgreementDatabaseHelper;
    TradePromotionDatabaseHelper tradePromDBHelper;
    SpecialPriceDatabaseHelper specialPriceDBHelper;
    SpecialDiscountDatabaseHelper specialDisDBHelper;
    SpecialBarcodeDatabaseHelper specialBarDBHelper;
    LastModifiedDatabaseHelper lastModDBHelper;

    // ********************* insert Data to Table End (SQLite 00)

    // for get Account info HTTP

    Fragment frgDetail;
    Fragment frgMaster;

    String ccNumStr;
    String acctest[] = new String[500];
    JSONObject obj; // = new JSONObject();
    String accJson2; // = "accountIdList”; <- Sign incorrect

    //Poa MasterJSON Data
    JSONObject jsonObject;
    GetArray getArray;

    VisitTarget visitTarget;
    ArrayList visitActivities;
    UserConfig userConfig;
    ArrayList relationsToOwner;
    Promotions promotions;
    Products products;
    ProductBrands productBrands;
    String orgId;
    ArrayList marketPops;
    ArrayList marketDisplayLocations;
    ArrayList marketActivities;
    ArrayList goodsReturnReasons;
    Competitors competitors;
    ArrayList channels;
    ArrayList banks;

    CheckNull chk;
    GetJSONArray getJSONArray;
    GetJSONObject getJSONObject;

    int countPick = 1;
    int picklistType = 1;

    //Poa SaleVisit Data
    int visitType;
    String visitNotes;
    ArrayList productReturns;
    long planOut;
    String planNotes;
    ArrayList plannedActivities;
    long planIn;
    ArrayList orders;
    ArrayList merchandisings;
    boolean isVisited;
    ArrayList inventories;
    String id;
    String externalId;
    ArrayList competitorsSV;
    ArrayList collections;
    long checkOut;
    double checkInLongitude;
    double checkInLatitude;
    long checkIn;
    ArrayList actualActivities;
    String accountId;

    SalesVisitSync salesVisitSync;
    ContactsSync contactsSync;
    AccountSync accountSync;
    AddressSync addressSync;
    InvoiceSync invoiceSync;
    TradeAgreementSync tradeAgreementSync;
    TradePromotionSync tradePromSync;
    SpecialPriceSync specialPriceSync;
    SpecialDiscountSync specialDiscountSync;
    SpecialBarcodeSync specialBarcodeSync;

    CheckLongNull chkLong;
    CheckIntNull chkInt;
    CheckDoubleNull chkDouble;

    //Zipfile
    private String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private String dataPath = SDPath + "/Mobilesales/" ;
    private String zipPath = SDPath + "/compress/zip/" ;
    private String unzipPath = SDPath + "/Mobilesales/unzip/";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
            Log.e("MyListFragment","HTTP3Fragment:_" + activity.toString());

        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
           // Log.e("MyListFragment","HTTP3Fragment:_" + activity.toString());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // *********************************** Edit: ListView

//        setContentView(R.layout.progressbar3_activity_progressbar_edit);
        View v = inflater.inflate(R.layout.progressbar3_activity_progressbar_edit, container, false);

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");
        // setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        // setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setTypeface(fontThSarabunBold);

        TextView tv0 = (TextView) v.findViewById(R.id.tv0);
        TextView tv1 = (TextView) v.findViewById(R.id.tv1);
        TextView tv2 = (TextView) v.findViewById(R.id.tv2);
        TextView tv3 = (TextView) v.findViewById(R.id.tv3);
        TextView tv4 = (TextView) v.findViewById(R.id.tv4);
        tv0.setTypeface(fontThSarabunBold);
        tv1.setTypeface(fontThSarabunBold);
        tv2.setTypeface(fontThSarabunBold);
        tv3.setTypeface(fontThSarabunBold);
        tv4.setTypeface(fontThSarabunBold);

        txt = (TextView) v.findViewById(R.id.raw);
        txt2 = (TextView) v.findViewById(R.id.raw2);

        accJson2 = "accountIdList";

        accountIdL = new JSONArray();

        countNumAcc = 0;
        countTarget = 0;

        // Add Status
        tv01 = (TextView) v.findViewById(R.id.tv1);
        tv02 = (TextView) v.findViewById(R.id.tv2);
        tv03 = (TextView) v.findViewById(R.id.tv3); // item_contact_listview
        tv04 = (TextView) v.findViewById(R.id.tv4);

        tv01.setTypeface(fontThSarabunBold);
        tv02.setTypeface(fontThSarabunBold);
        tv03.setTypeface(fontThSarabunBold);
        tv04.setTypeface(fontThSarabunBold);

        progressBar4 = (ProgressBar) v.findViewById(R.id.progressBar4); // customer_add_contact
        progressBar4.setProgress(0);
        progressBar4.setSecondaryProgress(0);

        progressBar5 = (ProgressBar) v.findViewById(R.id.progressBar5); // customer_add_contact
        progressBar5.setProgress(0);
        progressBar5.setSecondaryProgress(0);

        progressBar6 = (ProgressBar) v.findViewById(R.id.progressBar6);
        progressBar6.setProgress(0);
        progressBar6.setSecondaryProgress(0);

        progressBar7 = (ProgressBar) v.findViewById(R.id.progressBar7);
        progressBar7.setProgress(0);
        progressBar7.setSecondaryProgress(0);

        progressBar8 = (ProgressBar) v.findViewById(R.id.progressBar8);
        progressBar8.setProgress(0);
        progressBar8.setSecondaryProgress(0);

        // ********************************** Check Exists Database Start

        File database = getActivity().getApplicationContext().getDatabasePath(TargetDatabaseHelper.DBNAME);

        visitTarDBHelper = new VisitTargetDatabaseHelper(getActivity());
        promotionDBHelper = new PromotionDatabaseHelper(getActivity());
        promotionCriDBHelper = new PromotionCriteriaDatabaseHelper(getActivity());
        promotionCriDetDBHelper = new PromotionCriteriaDetailDatabaseHelper(getActivity());
        promotionCriBenDBHelper = new PromotionCriteriaBenefitDatabaseHelper(getActivity());
        targetDBHelper = new TargetDatabaseHelper(getActivity());
        productBrdDBHelper = new ProductBrandDatabaseHelper(getActivity());
        productCtDBHelper = new ProductCategoryDatabaseHelper(getActivity());
        productGrpDBHelper = new ProductGroupDatabaseHelper(getActivity());
        accountDatabaseHelper = new AccountDatabaseHelper(getActivity());
        salesVisitDatabaseHelper = new SalesVisitDatabaseHelper(getActivity());
        invenDBHelper = new InventoryDatabaseHelper(getActivity());
        productDBHelper = new ProductDatabaseHelper(getActivity());
        purchaseDBHelper = new PurchasedProductDatabaseHelper(getActivity());
        addressDBHelper = new AddressDatabaseHelper(getActivity());
        contactDBHelper = new ContactDatabaseHelper(getActivity());
        picklistDBHelper = new PickListDatabaseHelper(getActivity());
        compBrandDBHelper = new CompetitorBrandDatabaseHelper(getActivity());
        invoiceDatabaseHelper = new InvoiceDatabaseHelper(getActivity());
        invDetDatabaseHelper = new InvoiceDetailDatabaseHelper(getActivity());
        tradeAgreementDatabaseHelper = new TradeAgreementDatabaseHelper(getActivity());
        tradePromDBHelper = new TradePromotionDatabaseHelper(getActivity());
        specialPriceDBHelper = new SpecialPriceDatabaseHelper(getActivity());
        specialDisDBHelper = new SpecialDiscountDatabaseHelper(getActivity());
        specialBarDBHelper = new SpecialBarcodeDatabaseHelper(getActivity());
        lastModDBHelper = new LastModifiedDatabaseHelper(getActivity());

        String str = database.getAbsolutePath();
        Log.e("File database: ", "File database: " + str);

        if(false == database.exists()){
            visitTarDBHelper.getReadableDatabase();
            targetDBHelper.getReadableDatabase();
            //visitTarDBHelper.getReadableDatabase();
            if(copyDatabase(getActivity())){
                Toast.makeText(getActivity(), "YES!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(),"Copy data error", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        // ********************************** Check Exists Database End

        new AsyncT().execute();
        Log.e("Chk_HTTPGetProgress3:", "Run_AsyncT().execute()");

        new AsyncTGETSalesVisit().execute();
        Log.e("Check Salesvisit Array", "Run_AsyncTGETSalesVisit()");

        new AsyncTGETAccountInfo().execute();
        Log.e("Check AccountInfo Array", "Run_AsyncTGETAccountInfo()");

        new AsyncTGETInvoice().execute();
        Log.e("Check Invoice Array", "Run_AsyncTGETInvoice()");

        new AsyncTPOSTAccount().execute();
        Log.e("Check Account Array", "Run_AsyncTPOSTAccount()");

        new AsyncTPOSTSalesVisit().execute();
        Log.e("Check SalesVisit POST", "Run_AsyncTPOSTSalesVisit()");

//        new AsyncTPOSTImage().execute();
//        Log.e("Check Image", "Run_AsyncTPostImage()");

        return v;
    }

    // Start
//    private void changePage(Fragment frgmnt) {
//        communicator.MessageTab(1,frgmnt);
//        //Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
//    }
    // End


    /* Inner class to get response */
    class AsyncT extends AsyncTask<String, String, Void> { // Print 1 start

        @Override
        protected Void doInBackground(String... f_url) {

            int count;

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url2 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/masterdata";
            String url2 = myTokens.get_instance_url() + "/services/apexrest/masterdata";
            HttpGet httpGet = new HttpGet(url2);

            try {

                httpGet.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpGet.setHeader("Content-Type", "application/json");
                HttpResponse response = httpclient.execute(httpGet);


                String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

                Log.e("Chk_HTTPGet3: ", "Chk_jsonResult:_" + jsonResult);

                // FOR Poa to implement this method: jsonResult
                getArray = new GetArray();
                chk = new CheckNull();
                getJSONArray = new GetJSONArray();
                getJSONObject = new GetJSONObject();

                jsonObject = new JSONObject(jsonResult);

//                // If Last modified
//                if (jsonObject.optBoolean("lastmodify")){
//                    //Not sync masterdata
//                }else {
//                    //Sync masterdata
//                }

                // Clear all local database of Promotion , Product , Picklist
                promotionDBHelper.removeAll();
                promotionCriDBHelper.removeAll();
                promotionCriDetDBHelper.removeAll();
                promotionCriBenDBHelper.removeAll();
                productDBHelper.removeAll();
                productBrdDBHelper.removeAll();
                productCtDBHelper.removeAll();
                productGrpDBHelper.removeAll();
                picklistDBHelper.removeAll();

                visitTarget = new VisitTarget(getJSONObject.GetJSONObject(jsonObject.optString("visitTarget")));
                visitTarDBHelper.addVisitTarget(visitTarget.getId(),
                                                visitTarget.getBillToActual(),
                                                visitTarget.getBillToTarget(),
                                                visitTarget.getShipToActual(),
                                                visitTarget.getShipToTarget());

                visitActivities = getArray.GetArray(jsonObject.optString("visitActivities"));
                for (int i=0; i<visitActivities.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                visitActivities.get(i).toString());
                    countPick++;
                }
                picklistType++;

                userConfig = new UserConfig(getJSONObject.GetJSONObject(jsonObject.optString("userConfig")));
                relationsToOwner = getArray.GetArray(jsonObject.optString("relationsToOwner"));
                for (int i=0;i<relationsToOwner.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                relationsToOwner.get(i).toString());
                    countPick++;
                }
                picklistType++;

                promotions = new Promotions(getJSONArray.GetJSONArray(jsonObject.optString("promotions")));

                for (int i=0;i<promotions.getId().size();i++) {

                    int type = 0;

                    if (promotions.getCriteriasList().get(i).getEvaluationType().get(0).equals("Product")){
                        if (promotions.getCriteriasList().get(i).getId().size() == 1){
                            type = 1;
                        }else {
                            type = 2;
                        }
                    }else if (promotions.getCriteriasList().get(i).getEvaluationType().get(0).equals("Basket")){
                        if (promotions.getCriteriasList().get(i).getId().size() == 1){
                            type = 3;
                        }else {
                            type = 4;
                        }
                    }

                    promotionDBHelper.addPromotion(promotions.getId().get(i).toString(),
                            promotions.getName().get(i).toString(),
                            promotions.getPromotionNumber().get(i).toString(),
                            promotions.getDescription().get(i).toString(),
                            promotions.getRecordTypeName().get(i).toString(),
                            promotions.getStartDate().get(i).toString(),
                            promotions.getEndDate().get(i).toString(),
                            promotions.getProductBrandId().get(i).toString(),
                            "",
                            "",
                            type,
                            Boolean.getBoolean(promotions.getIsHighlight().get(i).toString()));

                    for (int j=0;j<promotions.getCriteriasList().get(i).getId().size();j++){
                        int typeCri = 0;
                        if (promotions.getCriteriasList().get(i).getCriteriaType().get(j).toString().equals("Quantity")){
                            typeCri = 2;
                        }
                        Log.e("chkType","chkType_"+typeCri);
                        Log.e("chkType","chkType_"+promotions.getCriteriasList().get(i).getAmountOrQty().get(j));

                        promotionCriDBHelper.addPromotionCriteria(promotions.getCriteriasList().get(i).getId().get(j).toString(),
                                promotions.getId().get(i).toString(),
                                typeCri,
                                promotions.getCriteriasList().get(i).getAmountOrQty().get(j),
                                promotions.getCriteriasList().get(i).getCriteriaNumber().get(j).toString());

                        for (int k=0;k<promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getId().size();k++){
                            type = 0;
                            if (promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getCriteriaType().get(k).equals("Quantity")){
                                type = 2;
                            }

                            promotionCriDetDBHelper.addPromotionCriteriaDetail(promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getId().get(k).toString(),
                                    promotions.getCriteriasList().get(i).getId().get(j).toString(),
                                    promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getProductCode().get(k).toString(),
                                    type,
                                    Double.parseDouble(promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getAmountOrQty().get(k).toString()),
                                    promotions.getCriteriasList().get(i).getCriteriaDetailsList().get(j).getUom().get(k).toString());
                        }

                        for (int k=0;k<promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getId().size();k++){
                            type = 0;
                            if (promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getBenefitType().get(k).equals("Quantity")){
                                type = 2;
                            }

                            promotionCriBenDBHelper.addPromotionCriteriaBenefit(promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getId().get(k).toString(),
                                    promotions.getCriteriasList().get(i).getId().get(j).toString(),
                                    promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getProductCode().get(k).toString(),
                                    type,
                                    Double.parseDouble(promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getAmountOrQty().get(k).toString()),
                                    promotions.getCriteriasList().get(i).getBenefitDetailsList().get(j).getUom().get(k).toString());
                        }
                    }
                }


                products = new Products(getJSONArray.GetJSONArray(jsonObject.optString("products")));

                Log.e("chkProdSize","chkProdSize_"+products.getId().size());
                for (int i=0;i<products.getId().size();i++) {
                    Log.e("chkProdSize","chkProdSize_"+i+":_"+products.getCode().get(i));

                    productDBHelper.addProduct(products.getCode().get(i).toString(),
                            products.getName().get(i).toString(),
                            products.getDescription().get(i).toString(),
                            Integer.parseInt(products.getAvailableQty().get(i).toString()),
                            Double.parseDouble(products.getUnitPrice().get(i).toString()),
                            products.getUom().get(i).toString(),
                            products.getBarcode().get(i).toString(),
                            products.getProductBrandId().get(i).toString(),
                            products.getProductCategoryId().get(i).toString(),
                            products.getProductGroupId().get(i).toString());

                    productGrpDBHelper.addProductGroup(products.getProductGroupList().get(i).getId(),
                            products.getProductGroupList().get(i).getParentId(),
                            products.getProductGroupList().get(i).getName(),
                            products.getProductGroupList().get(i).getTarget());

                    productCtDBHelper.addProductCategory(products.getProductCategoryList().get(i).getId(),
                            products.getProductBrandId().get(i).toString(),
                            products.getProductCategoryList().get(i).getName(),
                            products.getProductCategoryList().get(i).getTarget());

                    productBrdDBHelper.addProductBrand(products.getProductBrandList().get(i).getId(),
                            products.getProductBrandList().get(i).getName(),
                            products.getProductBrandList().get(i).getColorCode(),
                            products.getProductBrandList().get(i).getTarget());

                }

                productBrands = new ProductBrands(getJSONArray.GetJSONArray(jsonObject.optString("productBrands")));

                List<ProductBrand> productBrandList = new ArrayList<>();
                productBrandList = productBrdDBHelper.getListProductBrandList();

                for (int i=0;i<productBrands.getId().size();i++){
                    Log.e("checkBr","checkBr_"+productBrands.getColorCode().get(i).toString());
                    Log.e("chckproBrId","checkproBrId_"+productBrands.getId().get(i).toString());

                    for (int j=0;j<productBrandList.size();j++){
                        Log.e("BrId","checkBrId_"+productBrandList.get(j).getId());
                        if (productBrands.getId().get(i).toString().equals(productBrandList.get(j).getId())){
                            productBrdDBHelper.updateProductBrand(productBrands.getId().get(i).toString(),
                                                                productBrands.getName().get(i).toString(),
                                                                productBrands.getColorCode().get(i).toString(),
                                                                productBrands.getTarget().get(i).toString());
                        }else {
                            productBrdDBHelper.addProductBrand(productBrands.getId().get(i).toString(),
                                    productBrands.getName().get(i).toString(),
                                    productBrands.getColorCode().get(i).toString(),
                                    productBrands.getTarget().get(i).toString());
                        }
                    }

                    for (int j=0;j<productBrands.getProductCategoriesList().get(i).getId().size();j++){
                        productCtDBHelper.addProductCategory(productBrands.getProductCategoriesList().get(i).getId().get(j).toString(),
                                                            productBrands.getId().get(i).toString(),
                                                            productBrands.getProductCategoriesList().get(i).getName().get(j).toString(),
                                                            productBrands.getProductCategoriesList().get(i).getTarget().get(j).toString());

                        for (int k=0;k<productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getId().size();k++){
                            productGrpDBHelper.addProductGroup(productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getId().get(k).toString(),
                                                                productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getParentId().get(k).toString(),
                                                                productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getName().get(k).toString(),
                                                                productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getId());

                            if (productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getSalesActual() != 0.0){
                                targetDBHelper.addTarget(productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getId(),
                                                        productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getQuantityActual(),
                                                        productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getQuantityTarget(),
                                                        productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getSalesActual(),
                                                        productBrands.getProductCategoriesList().get(i).getProductGroupsList().get(j).getProductGroupsTargetList().get(k).getSalesTarget());
                            }
                        }
                    }
                }

//            orgId = chk.CheckNull(jsonObject.optString("orgId"));
//            Log.e("orgId",orgId);
                marketPops = getArray.GetArray(jsonObject.optString("marketPops"));
                for (int i=0;i<marketPops.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                marketPops.get(i).toString());
                    countPick++;
                }

                picklistType++;
                marketDisplayLocations = getArray.GetArray(jsonObject.optString("marketDisplayLocations"));
                for (int i=0;i<marketDisplayLocations.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                marketDisplayLocations.get(i).toString());
                    countPick++;
                }
                picklistType++;
                marketActivities = getArray.GetArray(jsonObject.optString("marketActivities"));
                for (int i=0;i<marketActivities.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                marketActivities.get(i).toString());
                    countPick++;
                }
                picklistType++;
                goodsReturnReasons = getArray.GetArray(jsonObject.optString("goodsReturnReasons"));
                for (int i=0;i<goodsReturnReasons.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                goodsReturnReasons.get(i).toString());
                    countPick++;
                }
                picklistType++;

                competitors = new Competitors(jsonObject.getJSONArray("competitors"));
                for (int i=0;i<competitors.getName().size();i++){
                    compBrandDBHelper.addCompetitorBrand(competitors.getId().get(i).toString(),
                                                        competitors.getName().get(i).toString(),
                                                        "");
                }
                channels = getArray.GetArray(jsonObject.optString("channels"));
                for (int i=0;i<channels.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                channels.get(i).toString());
                    countPick++;
                }
                picklistType++;
                banks = getArray.GetArray(jsonObject.optString("banks"));
                for (int i=0;i< banks.size();i++){
                    picklistDBHelper.addPickList(countPick,
                                                picklistType,
                                                banks.get(i).toString());
                    countPick++;
                }
                picklistType++;



            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // *********** ADD Progress Bar Start
        /* Updating progress bar */
        protected void onProgressUpdate(String... value) {
            progressBar4.setProgress(Integer.parseInt(value[0]) + 5);
            progressBar4.setSecondaryProgress(Integer.parseInt(value[0]) + 2);
        }

        // *********** ADD Progress Bar End

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            progressBar4.setProgress(100);

            //Intent i = new Intent(HTTPGetProgressBarSQLiteDB3.this, HomeActivitySQLiteDBnew2.class);
            //HomeTabletMainActivity.class
            // Intent i = new Intent(HTTPGetProgressBarSQLiteDB3Fragment.this, HomeTabletMainActivity.class);

            // int RESULT_OK = 777;
            // setResult(RESULT_OK, i);
            // finish(); // Back to HomeActivity

//            frgDetail = new TabletDetailProgressBar();
//            communicator.MessageTab(1,frgDetail);
            // communicator.MessageHTTP("tvTitleTest");


        }
    } // Print 1 end

    public interface Communicator {
        public void MessageHTTP(String OS_Name);
    }

//    private void updateFragment(String OS_Name) {
//        communicatorV4.Message(OS_Name);
//        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
//    }


    class AsyncTGETSalesVisit extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            int count;

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/salesvisit";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/salesvisit";
            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);


            try {

                httpGet.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpGet.setHeader("Content-Type", "application/json");
                HttpResponse response = httpclient.execute(httpGet);
                Log.e("Check Salesvisit Array", "Run_AsyncTGETSalesVisit()_jsonResult ----- BEFORE: " + jsonResultSV);

                // ****************** Execute HTTP Post Request ====== Salesvisit
                jsonResultSV = inputStreamToString(response.getEntity().getContent()).toString();
                Log.e("Check Salesvisit Array", "Run_AsyncTGETSalesVisit()_jsonResult ----- After: " + jsonResultSV);

                
                // FOR Poa to implement this method: jsonResult

                JSONArray jsonArr = new JSONArray(jsonResultSV);

                List<SalesVisit> salesCheckId = salesVisitDatabaseHelper.getListSalesVisit();

                for (int i=0;i<jsonArr.length();i++){

                    salesVisitSync = new SalesVisitSync(jsonArr.getJSONObject(i).toString());

                    int check = 0;
                    for (int index=0;index < salesCheckId.size();index++){
                        if (salesCheckId.get(index).getIdServer().equals(salesVisitSync.getId())){
                            check = 1;
                            break;
                        }
                    }
                    if (check == 0){
                        salesVisitDatabaseHelper.addSalesVisit(salesVisitSync.getAccountId(),
                                (int)salesVisitSync.getPlanIn(),
                                (int)salesVisitSync.getPlanOut(),
                                "",
                                salesVisitSync.getVisitType(),
                                false,
                                false,
                                salesVisitSync.isVisited(),
                                salesVisitSync.getCheckInLatitude(),
                                salesVisitSync.getCheckInLongitude(),
                                (int)salesVisitSync.getCheckIn(),
                                (int)salesVisitSync.getCheckOut(),
                                salesVisitSync.getVisitNotes(),
                                false,
                                salesVisitSync.getId(),
                                false);
                    }else {
                        salesVisitDatabaseHelper.updateSalesVisit(salesVisitSync.getAccountId(),
                                (int)salesVisitSync.getPlanIn(),
                                (int)salesVisitSync.getPlanOut(),
                                "",
                                salesVisitSync.getVisitType(),
                                false,
                                false,
                                salesVisitSync.isVisited(),
                                salesVisitSync.getCheckInLatitude(),
                                salesVisitSync.getCheckInLongitude(),
                                (int)salesVisitSync.getCheckIn(),
                                (int)salesVisitSync.getCheckOut(),
                                salesVisitSync.getVisitNotes(),
                                false,
                                salesVisitSync.getId(),
                                false);
                    }

                    Log.e("countSV","count_"+(i+1));

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultSV;
        }
        // *********** ADD Progress Bar End

        protected void onProgressUpdate(String... value) {
            progressBar5.setSecondaryProgress(Integer.parseInt(value[0]) + 2);
        }

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);

        }
    } // Print 5 end

    class AsyncTGETAccountInfo extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            int count;

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/accountinfo";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/accountinfo";
            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);


            try {

                httpGet.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpGet.setHeader("Content-Type", "application/json");
                HttpResponse response = httpclient.execute(httpGet);
                Log.e("Check AccountInfo Array", "Run_AsyncTGETAccountInfo()_jsonResult ----- After: " + jsonResultAC);

                // ****************** Execute HTTP Post Request ====== Salesvisit
                jsonResultAC = inputStreamToString(response.getEntity().getContent()).toString();
                Log.e("Check AccountInfo Array", "Run_AsyncTGETAccountInfo()_jsonResult ----- After: " + jsonResultAC);

                JSONObject jsonObject = new JSONObject(jsonResultAC);
                JSONArray jsonArray = jsonObject.optJSONArray("accountIds");

                JSONObject jsonObjPOST = new JSONObject();
                JSONArray jsonArPOST = new JSONArray();

                ArrayList acc = new ArrayList();
                for (int i=0;i<jsonArray.length();i++){
                    jsonArPOST.put(jsonArray.get(i).toString());
                }

                jsonObjPOST.put("accountIdList",jsonArPOST);
                Log.e("jsonPost", jsonObjPOST.toString());

                HttpPost httpPost = new HttpPost(url3);
                httpPost.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpPost.setHeader("Content-Type", "application/json");
                StringEntity se = new StringEntity(jsonObjPOST.toString());
                httpPost.setEntity(se);
                HttpResponse responsePost = httpclient.execute(httpPost);
                jsonResultAC = inputStreamToString(responsePost.getEntity().getContent()).toString();
                Log.e("Check AccountInfo Array", "Run_AsyncTPOSTAccountInfo()_jsonResult ----- After: " + jsonResultAC);

                int lastmodified = jsonObject.optInt("timestamp");
                Log.e("chkLastMod","chkLastMod_"+lastmodified);
                List<LastModified> lastModifiedList = lastModDBHelper.getListLastModified();

                if (lastModifiedList.size() != 0){
                    if (lastModifiedList.get(0).getValue() != lastmodified){
                        lastModDBHelper.updateLastModified(lastModifiedList.get(0).getId(),
                                                            lastmodified);
                        JSONArray jsonAcct = new JSONArray(jsonResultAC);

                        int check = 0;
                        for (int i=0;i<jsonAcct.length();i++){

                            accountSync = new AccountSync(jsonAcct.getJSONObject(i));
                            List<Account> accountList = accountDatabaseHelper.getListAccountListBySearchId(accountSync.getId());
                            if (accountList.size() == 0) {
                                accountDatabaseHelper.addAccount(accountSync.getId(),
                                        accountSync.getNumber(),
                                        accountSync.getName(),
                                        accountSync.getChannel(),
                                        accountSync.getEmail(),
                                        accountSync.getFax(),
                                        accountSync.getMobile(),
                                        accountSync.getPhone1(),
                                        accountSync.getPhone2(),
                                        accountSync.getWebsite(),
                                        accountSync.getGrade(),
                                        accountSync.getBalance(),
                                        accountSync.getLimitCredit(),
                                        accountSync.getAvaiCredit(),
                                        accountSync.getImageId(),
                                        accountSync.isSync(),
                                        accountSync.isNew(),
                                        accountSync.isOnHold(),
                                        accountSync.isActive(),
                                        accountSync.getPaymentTerm(),
                                        accountSync.getExternalId(),
                                        accountSync.getError());

                                JSONArray jsonShipTo;
                                jsonShipTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("shipTos"));
                                for (int j = 0; j < jsonShipTo.length(); j++) {
                                    addressSync = new AddressSync(jsonShipTo.get(j).toString());
                                    addressDBHelper.addAddress(addressSync.getId(),
                                            addressSync.getName(),
                                            addressSync.getStreet(),
                                            addressSync.getAddressLine1(),
                                            addressSync.getAddressLine2(),
                                            addressSync.getSubDistrict(),
                                            addressSync.getState(),
                                            addressSync.getCity(),
                                            addressSync.getCountry(),
                                            addressSync.getPostalCode(),
                                            addressSync.getEmail(),
                                            addressSync.getFax(),
                                            addressSync.getMobile(),
                                            addressSync.getPhone1(),
                                            addressSync.getPhone2(),
                                            addressSync.getLatitude(),
                                            addressSync.getLongtitude(),
                                            addressSync.getImage(),
                                            addressSync.getType(),
                                            accountSync.getId(),
                                            addressSync.getProvince(),
                                            addressSync.getDistrict(),
                                            addressSync.getExternalId());

                                    JSONArray jsonContactShip;
                                    jsonContactShip = getJSONArray.GetJSONArray(jsonShipTo.getJSONObject(j).optString("contacts"));
                                    for (int k = 0; k < jsonContactShip.length(); k++) {
                                        contactsSync = new ContactsSync(jsonContactShip.get(k).toString());
                                        contactDBHelper.addContact(contactsSync.getId(),
                                                contactsSync.getFirstName(),
                                                contactsSync.getLastName(),
                                                contactsSync.getPosition(),
                                                contactsSync.getTitle(),
                                                contactsSync.getBirthday(),
                                                contactsSync.getEmail(),
                                                contactsSync.getLineId(),
                                                contactsSync.getMobile(),
                                                contactsSync.getFavDrink(),
                                                contactsSync.getFavFood(),
                                                contactsSync.getFavSport(),
                                                contactsSync.getNonFavDrink(),
                                                contactsSync.getNonFavFood(),
                                                contactsSync.getNonFavAct(),
                                                contactsSync.getImageId(),
                                                1,
                                                contactsSync.getOwner(),
                                                contactsSync.getIsDecis(),
                                                contactsSync.getRelateOwn(),
                                                accountSync.getId(),
                                                false,
                                                false,
                                                contactsSync.getExId());
                                    }
                                }

                                for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                    purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                            jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                                }

                                JSONArray jsonContact;
                                jsonContact = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("contacts"));
                                Log.e("contacts size", jsonContact.length() + "");
                                for (int j = 0; j < jsonContact.length(); j++) {
                                    contactsSync = new ContactsSync(jsonContact.get(j).toString());
                                    contactDBHelper.addContact(contactsSync.getId(),
                                            contactsSync.getFirstName(),
                                            contactsSync.getLastName(),
                                            contactsSync.getPosition(),
                                            contactsSync.getTitle(),
                                            contactsSync.getBirthday(),
                                            contactsSync.getEmail(),
                                            contactsSync.getLineId(),
                                            contactsSync.getMobile(),
                                            contactsSync.getFavDrink(),
                                            contactsSync.getFavFood(),
                                            contactsSync.getFavSport(),
                                            contactsSync.getNonFavDrink(),
                                            contactsSync.getNonFavFood(),
                                            contactsSync.getNonFavAct(),
                                            contactsSync.getImageId(),
                                            0,
                                            contactsSync.getOwner(),
                                            contactsSync.getIsDecis(),
                                            contactsSync.getRelateOwn(),
                                            accountSync.getId(),
                                            false,
                                            false,
                                            contactsSync.getExId());
                                }

                                JSONArray jsonBillTo;
                                jsonBillTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("billTos"));
                                for (int j = 0; j < jsonBillTo.length(); j++) {
                                    addressSync = new AddressSync(jsonBillTo.get(j).toString());
                                    addressDBHelper.addAddress(addressSync.getId(),
                                            addressSync.getName(),
                                            addressSync.getStreet(),
                                            addressSync.getAddressLine1(),
                                            addressSync.getAddressLine2(),
                                            addressSync.getSubDistrict(),
                                            addressSync.getState(),
                                            addressSync.getCity(),
                                            addressSync.getCountry(),
                                            addressSync.getPostalCode(),
                                            addressSync.getEmail(),
                                            addressSync.getFax(),
                                            addressSync.getMobile(),
                                            addressSync.getPhone1(),
                                            addressSync.getPhone2(),
                                            addressSync.getLatitude(),
                                            addressSync.getLongtitude(),
                                            addressSync.getImage(),
                                            addressSync.getType(),
                                            accountSync.getId(),
                                            addressSync.getProvince(),
                                            addressSync.getDistrict(),
                                            addressSync.getExternalId());
//                        Log.e("addressContact",jsonShipTo.getJSONObject(j).optString("contacts"));
                                    JSONArray jsonContactBill;
                                    jsonContactBill = getJSONArray.GetJSONArray(jsonBillTo.getJSONObject(j).optString("contacts"));
                                    for (int k = 0; k < jsonContactBill.length(); k++) {
                                        contactsSync = new ContactsSync(jsonContactBill.get(k).toString());
                                        contactDBHelper.addContact(contactsSync.getId(),
                                                contactsSync.getFirstName(),
                                                contactsSync.getLastName(),
                                                contactsSync.getPosition(),
                                                contactsSync.getTitle(),
                                                contactsSync.getBirthday(),
                                                contactsSync.getEmail(),
                                                contactsSync.getLineId(),
                                                contactsSync.getMobile(),
                                                contactsSync.getFavDrink(),
                                                contactsSync.getFavFood(),
                                                contactsSync.getFavSport(),
                                                contactsSync.getNonFavDrink(),
                                                contactsSync.getNonFavFood(),
                                                contactsSync.getNonFavAct(),
                                                contactsSync.getImageId(),
                                                1,
                                                contactsSync.getOwner(),
                                                contactsSync.getIsDecis(),
                                                contactsSync.getRelateOwn(),
                                                accountSync.getId(),
                                                false,
                                                false,
                                                contactsSync.getExId());
                                    }
                                }

//                                JSONArray jsonTrade;
//                                jsonTrade = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("tradeAgreements"));
//                                for (int j = 0; j < jsonTrade.length(); j++) {
//                                    tradeAgreementSync = new TradeAgreementSync(jsonTrade.getJSONObject(j));
//                                    tradeAgreementDatabaseHelper.addTradeAgreement(tradeAgreementSync.getIdAcct(),
//                                            tradeAgreementSync.getProdCode(),
//                                            tradeAgreementSync.getPrice(),
//                                            tradeAgreementSync.getDiscount1(),
//                                            tradeAgreementSync.getDis1Type(),
//                                            tradeAgreementSync.getDiscount2(),
//                                            tradeAgreementSync.getDis2Type(),
//                                            tradeAgreementSync.getDiscount3(),
//                                            tradeAgreementSync.getDis3Type(),
//                                            tradeAgreementSync.getBarcode());
//                                }
                                JSONArray jsonTradeProm;
                                jsonTradeProm = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("tradePromotions"));
                                for (int j=0;j<jsonTradeProm.length();j++){
                                    tradePromSync = new TradePromotionSync(jsonTradeProm.get(j).toString());
                                    tradePromDBHelper.addTradePromotion(tradePromSync.getId(),
                                                                        tradePromSync.getIdAcct(),
                                                                        tradePromSync.getProdCode(),
                                                                        tradePromSync.getType(),
                                                                        tradePromSync.getOrderAmount(),
                                                                        tradePromSync.getFoc());
                                }
                                JSONArray jsonSpecialPrice;
                                jsonSpecialPrice = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialPrices"));
                                for (int j=0;j<jsonSpecialPrice.length();j++) {
                                    specialPriceSync = new SpecialPriceSync(jsonSpecialPrice.get(j).toString());
                                    specialPriceDBHelper.addSpecialPrice(specialPriceSync.getId(),
                                                                        specialPriceSync.getIdAcct(),
                                                                        specialPriceSync.getProdCode(),
                                                                        specialPriceSync.getPrice());
                                }
                                JSONArray jsonDiscount;
                                jsonDiscount = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialDiscounts"));
                                for (int j=0;j<jsonDiscount.length();j++) {
                                    specialDiscountSync = new SpecialDiscountSync(jsonDiscount.get(j).toString());
                                    specialDisDBHelper.addSpecialDiscount(specialDiscountSync.getId(),
                                                                        specialDiscountSync.getIdAcct(),
                                                                        specialDiscountSync.getProdCode(),
                                                                        specialDiscountSync.getProduct(),
                                                                        specialDiscountSync.getExternalId(),
                                                                        specialDiscountSync.getPurchaseQty(),
                                                                        specialDiscountSync.getStartDate(),
                                                                        specialDiscountSync.getEndDate(),
                                                                        specialDiscountSync.getDis1Rate(),
                                                                        specialDiscountSync.getDis1Type(),
                                                                        specialDiscountSync.getDis2Rate(),
                                                                        specialDiscountSync.getDis2Type(),
                                                                        specialDiscountSync.getDis3Rate(),
                                                                        specialDiscountSync.getDis3Type());
                                }
                                JSONArray jsonBarcode;
                                jsonBarcode = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialBarcodes"));
                                for (int j=0;j<jsonBarcode.length();j++) {
                                    specialBarcodeSync = new SpecialBarcodeSync(jsonBarcode.get(j).toString());
                                    specialBarDBHelper.addSpecialBarcode(specialBarcodeSync.getId(),
                                                                        specialBarcodeSync.getIdAcct(),
                                                                        specialBarcodeSync.getProdCode(),
                                                                        specialBarcodeSync.getBarcode());
                                }
                            }else {
                                accountDatabaseHelper.updateAccount(accountSync.getId(),
                                        accountSync.getNumber(),
                                        accountSync.getName(),
                                        accountSync.getChannel(),
                                        accountSync.getEmail(),
                                        accountSync.getFax(),
                                        accountSync.getMobile(),
                                        accountSync.getPhone1(),
                                        accountSync.getPhone2(),
                                        accountSync.getWebsite(),
                                        accountSync.getGrade(),
                                        accountSync.getBalance(),
                                        accountSync.getLimitCredit(),
                                        accountSync.getAvaiCredit(),
                                        accountSync.getImageId(),
                                        accountSync.isSync(),
                                        accountSync.isNew(),
                                        accountSync.isOnHold(),
                                        accountSync.isActive(),
                                        accountSync.getPaymentTerm(),
                                        accountSync.getExternalId(),
                                        accountSync.getError());

                                JSONArray jsonShipTo;
                                jsonShipTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("shipTos"));
                                for (int j = 0; j < jsonShipTo.length(); j++) {
                                    addressSync = new AddressSync(jsonShipTo.get(j).toString());
                                    addressDBHelper.updateAddress(addressSync.getId(),
                                            addressSync.getName(),
                                            addressSync.getStreet(),
                                            addressSync.getAddressLine1(),
                                            addressSync.getAddressLine2(),
                                            addressSync.getSubDistrict(),
                                            addressSync.getState(),
                                            addressSync.getCity(),
                                            addressSync.getCountry(),
                                            addressSync.getPostalCode(),
                                            addressSync.getEmail(),
                                            addressSync.getFax(),
                                            addressSync.getMobile(),
                                            addressSync.getPhone1(),
                                            addressSync.getPhone2(),
                                            addressSync.getLatitude(),
                                            addressSync.getLongtitude(),
                                            addressSync.getImage(),
                                            addressSync.getType(),
                                            accountSync.getId(),
                                            addressSync.getProvince(),
                                            addressSync.getDistrict(),
                                            addressSync.getExternalId());

                                    JSONArray jsonContactShip;
                                    jsonContactShip = getJSONArray.GetJSONArray(jsonShipTo.getJSONObject(j).optString("contacts"));
                                    for (int k = 0; k < jsonContactShip.length(); k++) {
                                        contactsSync = new ContactsSync(jsonContactShip.get(k).toString());
                                        contactDBHelper.updateContact(contactsSync.getId(),
                                                contactsSync.getFirstName(),
                                                contactsSync.getLastName(),
                                                contactsSync.getPosition(),
                                                contactsSync.getTitle(),
                                                contactsSync.getBirthday(),
                                                contactsSync.getEmail(),
                                                contactsSync.getLineId(),
                                                contactsSync.getMobile(),
                                                contactsSync.getFavDrink(),
                                                contactsSync.getFavFood(),
                                                contactsSync.getFavSport(),
                                                contactsSync.getNonFavDrink(),
                                                contactsSync.getNonFavFood(),
                                                contactsSync.getNonFavAct(),
                                                contactsSync.getImageId(),
                                                1,
                                                contactsSync.getOwner(),
                                                contactsSync.getIsDecis(),
                                                contactsSync.getRelateOwn(),
                                                false,
                                                false,
                                                contactsSync.getExId());
                                    }
                                }

                                List<PurchasedProduct> checkPurch = purchaseDBHelper.getListPurchasedProduct();

                                if (checkPurch.size() == 0) {
                                    for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                        purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                                jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                                    }
                                } else {
                                    for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                        int checkId = 0;
                                        for (int k = 0; k < checkPurch.size(); k++) {
                                            if (checkPurch.get(k).getIdAcct().equals(accountSync.getId()) &&
                                                    checkPurch.get(k).getProdCode().equals(jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString())) {
                                                checkId = 1;
                                                break;
                                            }
                                        }
                                        if (checkId == 0) {
                                            purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                                    jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                                        }
                                    }
                                }

                                JSONArray jsonContact;
                                jsonContact = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("contacts"));
                                Log.e("contacts size", jsonContact.length() + "");
                                for (int j = 0; j < jsonContact.length(); j++) {
                                    contactsSync = new ContactsSync(jsonContact.get(j).toString());
                                    contactDBHelper.updateContact(contactsSync.getId(),
                                            contactsSync.getFirstName(),
                                            contactsSync.getLastName(),
                                            contactsSync.getPosition(),
                                            contactsSync.getTitle(),
                                            contactsSync.getBirthday(),
                                            contactsSync.getEmail(),
                                            contactsSync.getLineId(),
                                            contactsSync.getMobile(),
                                            contactsSync.getFavDrink(),
                                            contactsSync.getFavFood(),
                                            contactsSync.getFavSport(),
                                            contactsSync.getNonFavDrink(),
                                            contactsSync.getNonFavFood(),
                                            contactsSync.getNonFavAct(),
                                            contactsSync.getImageId(),
                                            0,
                                            contactsSync.getOwner(),
                                            contactsSync.getIsDecis(),
                                            contactsSync.getRelateOwn(),
                                            false,
                                            false,
                                            contactsSync.getExId());
                                }

                                JSONArray jsonBillTo;
                                jsonBillTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("billTos"));
                                for (int j = 0; j < jsonBillTo.length(); j++) {
                                    addressSync = new AddressSync(jsonBillTo.get(j).toString());
                                    addressDBHelper.updateAddress(addressSync.getId(),
                                            addressSync.getName(),
                                            addressSync.getStreet(),
                                            addressSync.getAddressLine1(),
                                            addressSync.getAddressLine2(),
                                            addressSync.getSubDistrict(),
                                            addressSync.getState(),
                                            addressSync.getCity(),
                                            addressSync.getCountry(),
                                            addressSync.getPostalCode(),
                                            addressSync.getEmail(),
                                            addressSync.getFax(),
                                            addressSync.getMobile(),
                                            addressSync.getPhone1(),
                                            addressSync.getPhone2(),
                                            addressSync.getLatitude(),
                                            addressSync.getLongtitude(),
                                            addressSync.getImage(),
                                            addressSync.getType(),
                                            accountSync.getId(),
                                            addressSync.getProvince(),
                                            addressSync.getDistrict(),
                                            addressSync.getExternalId());
//                        Log.e("addressContact",jsonShipTo.getJSONObject(j).optString("contacts"));
                                    JSONArray jsonContactBill;
                                    jsonContactBill = getJSONArray.GetJSONArray(jsonBillTo.getJSONObject(j).optString("contacts"));
                                    for (int k = 0; k < jsonContactBill.length(); k++) {
                                        contactsSync = new ContactsSync(jsonContactBill.get(k).toString());
                                        contactDBHelper.updateContact(contactsSync.getId(),
                                                contactsSync.getFirstName(),
                                                contactsSync.getLastName(),
                                                contactsSync.getPosition(),
                                                contactsSync.getTitle(),
                                                contactsSync.getBirthday(),
                                                contactsSync.getEmail(),
                                                contactsSync.getLineId(),
                                                contactsSync.getMobile(),
                                                contactsSync.getFavDrink(),
                                                contactsSync.getFavFood(),
                                                contactsSync.getFavSport(),
                                                contactsSync.getNonFavDrink(),
                                                contactsSync.getNonFavFood(),
                                                contactsSync.getNonFavAct(),
                                                contactsSync.getImageId(),
                                                1,
                                                contactsSync.getOwner(),
                                                contactsSync.getIsDecis(),
                                                contactsSync.getRelateOwn(),
                                                false,
                                                false,
                                                contactsSync.getExId());
                                    }
                                }

                                JSONArray jsonTradeProm;
                                jsonTradeProm = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("tradePromotions"));
                                for (int j=0;j<jsonTradeProm.length();j++){
                                    tradePromSync = new TradePromotionSync(jsonTradeProm.get(j).toString());
                                    tradePromDBHelper.updateTradePromotion(tradePromSync.getId(),
                                                                        tradePromSync.getIdAcct(),
                                                                        tradePromSync.getProdCode(),
                                                                        tradePromSync.getType(),
                                                                        tradePromSync.getOrderAmount(),
                                                                        tradePromSync.getFoc());
                                }
                                JSONArray jsonSpecialPrice;
                                jsonSpecialPrice = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialPrices"));
                                for (int j=0;j<jsonSpecialPrice.length();j++) {
                                    specialPriceSync = new SpecialPriceSync(jsonSpecialPrice.get(j).toString());
                                    specialPriceDBHelper.updateSpecialPrice(specialPriceSync.getId(),
                                                                            specialPriceSync.getIdAcct(),
                                                                            specialPriceSync.getProdCode(),
                                                                            specialPriceSync.getPrice());
                                }
                                JSONArray jsonDiscount;
                                jsonDiscount = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialDiscounts"));
                                for (int j=0;j<jsonDiscount.length();j++) {
                                    specialDiscountSync = new SpecialDiscountSync(jsonDiscount.get(j).toString());
                                    specialDisDBHelper.updateSpecialDiscount(specialDiscountSync.getId(),
                                                                            specialDiscountSync.getIdAcct(),
                                                                            specialDiscountSync.getProdCode(),
                                                                            specialDiscountSync.getProduct(),
                                                                            specialDiscountSync.getExternalId(),
                                                                            specialDiscountSync.getPurchaseQty(),
                                                                            specialDiscountSync.getStartDate(),
                                                                            specialDiscountSync.getEndDate(),
                                                                            specialDiscountSync.getDis1Rate(),
                                                                            specialDiscountSync.getDis1Type(),
                                                                            specialDiscountSync.getDis2Rate(),
                                                                            specialDiscountSync.getDis2Type(),
                                                                            specialDiscountSync.getDis3Rate(),
                                                                            specialDiscountSync.getDis3Type());
                                }
                                JSONArray jsonBarcode;
                                jsonBarcode = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialBarcodes"));
                                for (int j=0;j<jsonBarcode.length();j++) {
                                    specialBarcodeSync = new SpecialBarcodeSync(jsonBarcode.get(j).toString());
                                    specialBarDBHelper.updateSpecialBarcode(specialBarcodeSync.getId(),
                                                                            specialBarcodeSync.getIdAcct(),
                                                                            specialBarcodeSync.getProdCode(),
                                                                            specialBarcodeSync.getBarcode());
                                }

//                                JSONArray jsonTrade;
//                                jsonTrade = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("tradeAgreements"));
//                            for (int j = 0; j < jsonTrade.length(); j++) {
//                                tradeAgreementSync = new TradeAgreementSync(jsonTrade.getJSONObject(j));
//                                tradeAgreementDatabaseHelper.addTradeAgreement(tradeAgreementSync.getIdAcct(),
//                                                            tradeAgreementSync.getProdCode(),
//                                                            tradeAgreementSync.getPrice(),
//                                                            tradeAgreementSync.getDiscount1(),
//                                                            tradeAgreementSync.getDis1Type(),
//                                                            tradeAgreementSync.getDiscount2(),
//                                                            tradeAgreementSync.getDis2Type(),
//                                                            tradeAgreementSync.getDiscount3(),
//                                                            tradeAgreementSync.getDis3Type(),
//                                                            tradeAgreementSync.getBarcode());
//                            }


                            }

                        }
                    }
                }else {
                    // FOR Poa to implement this method: jsonResult

                    lastModDBHelper.addLastModified(lastmodified);

                    JSONArray jsonAcct = new JSONArray(jsonResultAC);

                    int check = 0;
                    for (int i=0;i<jsonAcct.length();i++){

                        accountSync = new AccountSync(jsonAcct.getJSONObject(i));
                        List<Account> accountList = accountDatabaseHelper.getListAccountListBySearchId(accountSync.getId());
                        if (accountList.size() == 0) {
                            accountDatabaseHelper.addAccount(accountSync.getId(),
                                                            accountSync.getNumber(),
                                                            accountSync.getName(),
                                                            accountSync.getChannel(),
                                                            accountSync.getEmail(),
                                                            accountSync.getFax(),
                                                            accountSync.getMobile(),
                                                            accountSync.getPhone1(),
                                                            accountSync.getPhone2(),
                                                            accountSync.getWebsite(),
                                                            accountSync.getGrade(),
                                                            accountSync.getBalance(),
                                                            accountSync.getLimitCredit(),
                                                            accountSync.getAvaiCredit(),
                                                            accountSync.getImageId(),
                                                            accountSync.isSync(),
                                                            accountSync.isNew(),
                                                            accountSync.isOnHold(),
                                                            accountSync.isActive(),
                                                            accountSync.getPaymentTerm(),
                                                            accountSync.getExternalId(),
                                                            accountSync.getError());

                            JSONArray jsonShipTo;
                            jsonShipTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("shipTos"));
                            for (int j = 0; j < jsonShipTo.length(); j++) {
                                addressSync = new AddressSync(jsonShipTo.get(j).toString());
                                addressDBHelper.addAddress(addressSync.getId(),
                                                            addressSync.getName(),
                                                            addressSync.getStreet(),
                                                            addressSync.getAddressLine1(),
                                                            addressSync.getAddressLine2(),
                                                            addressSync.getSubDistrict(),
                                                            addressSync.getState(),
                                                            addressSync.getCity(),
                                                            addressSync.getCountry(),
                                                            addressSync.getPostalCode(),
                                                            addressSync.getEmail(),
                                                            addressSync.getFax(),
                                                            addressSync.getMobile(),
                                                            addressSync.getPhone1(),
                                                            addressSync.getPhone2(),
                                                            addressSync.getLatitude(),
                                                            addressSync.getLongtitude(),
                                                            addressSync.getImage(),
                                                            addressSync.getType(),
                                                            accountSync.getId(),
                                                            addressSync.getProvince(),
                                                            addressSync.getDistrict(),
                                                            addressSync.getExternalId());

                                JSONArray jsonContactShip;
                                jsonContactShip = getJSONArray.GetJSONArray(jsonShipTo.getJSONObject(j).optString("contacts"));
                                for (int k = 0; k < jsonContactShip.length(); k++) {
                                    contactsSync = new ContactsSync(jsonContactShip.get(k).toString());
                                    contactDBHelper.addContact(contactsSync.getId(),
                                                                contactsSync.getFirstName(),
                                                                contactsSync.getLastName(),
                                                                contactsSync.getPosition(),
                                                                contactsSync.getTitle(),
                                                                contactsSync.getBirthday(),
                                                                contactsSync.getEmail(),
                                                                contactsSync.getLineId(),
                                                                contactsSync.getMobile(),
                                                                contactsSync.getFavDrink(),
                                                                contactsSync.getFavFood(),
                                                                contactsSync.getFavSport(),
                                                                contactsSync.getNonFavDrink(),
                                                                contactsSync.getNonFavFood(),
                                                                contactsSync.getNonFavAct(),
                                                                contactsSync.getImageId(),
                                                                1,
                                                                contactsSync.getOwner(),
                                                                contactsSync.getIsDecis(),
                                                                contactsSync.getRelateOwn(),
                                                                accountSync.getId(),
                                                                false,
                                                                false,
                                                                contactsSync.getExId());
                                }
                            }

                            for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                                                    jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                            }

                            JSONArray jsonContact;
                            jsonContact = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("contacts"));
                            Log.e("contacts size", jsonContact.length() + "");
                            for (int j = 0; j < jsonContact.length(); j++) {
                                contactsSync = new ContactsSync(jsonContact.get(j).toString());
                                contactDBHelper.addContact(contactsSync.getId(),
                                                            contactsSync.getFirstName(),
                                                            contactsSync.getLastName(),
                                                            contactsSync.getPosition(),
                                                            contactsSync.getTitle(),
                                                            contactsSync.getBirthday(),
                                                            contactsSync.getEmail(),
                                                            contactsSync.getLineId(),
                                                            contactsSync.getMobile(),
                                                            contactsSync.getFavDrink(),
                                                            contactsSync.getFavFood(),
                                                            contactsSync.getFavSport(),
                                                            contactsSync.getNonFavDrink(),
                                                            contactsSync.getNonFavFood(),
                                                            contactsSync.getNonFavAct(),
                                                            contactsSync.getImageId(),
                                                            0,
                                                            contactsSync.getOwner(),
                                                            contactsSync.getIsDecis(),
                                                            contactsSync.getRelateOwn(),
                                                            accountSync.getId(),
                                                            false,
                                                            false,
                                                            contactsSync.getExId());
                            }

                            JSONArray jsonBillTo;
                            jsonBillTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("billTos"));
                            for (int j = 0; j < jsonBillTo.length(); j++) {
                                addressSync = new AddressSync(jsonBillTo.get(j).toString());
                                addressDBHelper.addAddress(addressSync.getId(),
                                                            addressSync.getName(),
                                                            addressSync.getStreet(),
                                                            addressSync.getAddressLine1(),
                                                            addressSync.getAddressLine2(),
                                                            addressSync.getSubDistrict(),
                                                            addressSync.getState(),
                                                            addressSync.getCity(),
                                                            addressSync.getCountry(),
                                                            addressSync.getPostalCode(),
                                                            addressSync.getEmail(),
                                                            addressSync.getFax(),
                                                            addressSync.getMobile(),
                                                            addressSync.getPhone1(),
                                                            addressSync.getPhone2(),
                                                            addressSync.getLatitude(),
                                                            addressSync.getLongtitude(),
                                                            addressSync.getImage(),
                                                            addressSync.getType(),
                                                            accountSync.getId(),
                                                            addressSync.getProvince(),
                                                            addressSync.getDistrict(),
                                                            addressSync.getExternalId());
//                        Log.e("addressContact",jsonShipTo.getJSONObject(j).optString("contacts"));
                                JSONArray jsonContactBill;
                                jsonContactBill = getJSONArray.GetJSONArray(jsonBillTo.getJSONObject(j).optString("contacts"));
                                for (int k = 0; k < jsonContactBill.length(); k++) {
                                    contactsSync = new ContactsSync(jsonContactBill.get(k).toString());
                                    contactDBHelper.addContact(contactsSync.getId(),
                                                                contactsSync.getFirstName(),
                                                                contactsSync.getLastName(),
                                                                contactsSync.getPosition(),
                                                                contactsSync.getTitle(),
                                                                contactsSync.getBirthday(),
                                                                contactsSync.getEmail(),
                                                                contactsSync.getLineId(),
                                                                contactsSync.getMobile(),
                                                                contactsSync.getFavDrink(),
                                                                contactsSync.getFavFood(),
                                                                contactsSync.getFavSport(),
                                                                contactsSync.getNonFavDrink(),
                                                                contactsSync.getNonFavFood(),
                                                                contactsSync.getNonFavAct(),
                                                                contactsSync.getImageId(),
                                                                1,
                                                                contactsSync.getOwner(),
                                                                contactsSync.getIsDecis(),
                                                                contactsSync.getRelateOwn(),
                                                                accountSync.getId(),
                                                                false,
                                                                false,
                                                                contactsSync.getExId());
                                }
                            }

//                            JSONArray jsonTrade;
//                            jsonTrade = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("tradeAgreements"));
//                            for (int j = 0; j < jsonTrade.length(); j++) {
//                                tradeAgreementSync = new TradeAgreementSync(jsonTrade.getJSONObject(j));
//                                tradeAgreementDatabaseHelper.addTradeAgreement(tradeAgreementSync.getIdAcct(),
//                                                                                tradeAgreementSync.getProdCode(),
//                                                                                tradeAgreementSync.getPrice(),
//                                                                                tradeAgreementSync.getDiscount1(),
//                                                                                tradeAgreementSync.getDis1Type(),
//                                                                                tradeAgreementSync.getDiscount2(),
//                                                                                tradeAgreementSync.getDis2Type(),
//                                                                                tradeAgreementSync.getDiscount3(),
//                                                                                tradeAgreementSync.getDis3Type(),
//                                                                                tradeAgreementSync.getBarcode());
//                            }

                            JSONArray jsonTradeProm;
                            jsonTradeProm = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("tradePromotions"));
                            for (int j=0;j<jsonTradeProm.length();j++){
                                tradePromSync = new TradePromotionSync(jsonTradeProm.get(j).toString());
                                tradePromDBHelper.addTradePromotion(tradePromSync.getId(),
                                                                    tradePromSync.getIdAcct(),
                                                                    tradePromSync.getProdCode(),
                                                                    tradePromSync.getType(),
                                                                    tradePromSync.getOrderAmount(),
                                                                    tradePromSync.getFoc());
                            }
                            JSONArray jsonSpecialPrice;
                            jsonSpecialPrice = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialPrices"));
                            for (int j=0;j<jsonSpecialPrice.length();j++) {
                                specialPriceSync = new SpecialPriceSync(jsonSpecialPrice.get(j).toString());
                                specialPriceDBHelper.addSpecialPrice(specialPriceSync.getId(),
                                                                    specialPriceSync.getIdAcct(),
                                                                    specialPriceSync.getProdCode(),
                                                                    specialPriceSync.getPrice());
                            }
                            JSONArray jsonDiscount;
                            jsonDiscount = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialDiscounts"));
                            for (int j=0;j<jsonDiscount.length();j++) {
                                specialDiscountSync = new SpecialDiscountSync(jsonDiscount.get(j).toString());
                                specialDisDBHelper.addSpecialDiscount(specialDiscountSync.getId(),
                                                                    specialDiscountSync.getIdAcct(),
                                                                    specialDiscountSync.getProdCode(),
                                                                    specialDiscountSync.getProduct(),
                                                                    specialDiscountSync.getExternalId(),
                                                                    specialDiscountSync.getPurchaseQty(),
                                                                    specialDiscountSync.getStartDate(),
                                                                    specialDiscountSync.getEndDate(),
                                                                    specialDiscountSync.getDis1Rate(),
                                                                    specialDiscountSync.getDis1Type(),
                                                                    specialDiscountSync.getDis2Rate(),
                                                                    specialDiscountSync.getDis2Type(),
                                                                    specialDiscountSync.getDis3Rate(),
                                                                    specialDiscountSync.getDis3Type());
                            }
                            JSONArray jsonBarcode;
                            jsonBarcode = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialBarcodes"));
                            for (int j=0;j<jsonBarcode.length();j++) {
                                specialBarcodeSync = new SpecialBarcodeSync(jsonBarcode.get(j).toString());
                                specialBarDBHelper.addSpecialBarcode(specialBarcodeSync.getId(),
                                                                    specialBarcodeSync.getIdAcct(),
                                                                    specialBarcodeSync.getProdCode(),
                                                                    specialBarcodeSync.getBarcode());
                            }
                        }else {
                            accountDatabaseHelper.updateAccount(accountSync.getId(),
                                                                accountSync.getNumber(),
                                                                accountSync.getName(),
                                                                accountSync.getChannel(),
                                                                accountSync.getEmail(),
                                                                accountSync.getFax(),
                                                                accountSync.getMobile(),
                                                                accountSync.getPhone1(),
                                                                accountSync.getPhone2(),
                                                                accountSync.getWebsite(),
                                                                accountSync.getGrade(),
                                                                accountSync.getBalance(),
                                                                accountSync.getLimitCredit(),
                                                                accountSync.getAvaiCredit(),
                                                                accountSync.getImageId(),
                                                                accountSync.isSync(),
                                                                accountSync.isNew(),
                                                                accountSync.isOnHold(),
                                                                accountSync.isActive(),
                                                                accountSync.getPaymentTerm(),
                                                                accountSync.getExternalId(),
                                                                accountSync.getError());

                            JSONArray jsonShipTo;
                            jsonShipTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("shipTos"));
                            for (int j = 0; j < jsonShipTo.length(); j++) {
                                addressSync = new AddressSync(jsonShipTo.get(j).toString());
                                addressDBHelper.updateAddress(addressSync.getId(),
                                                                addressSync.getName(),
                                                                addressSync.getStreet(),
                                                                addressSync.getAddressLine1(),
                                                                addressSync.getAddressLine2(),
                                                                addressSync.getSubDistrict(),
                                                                addressSync.getState(),
                                                                addressSync.getCity(),
                                                                addressSync.getCountry(),
                                                                addressSync.getPostalCode(),
                                                                addressSync.getEmail(),
                                                                addressSync.getFax(),
                                                                addressSync.getMobile(),
                                                                addressSync.getPhone1(),
                                                                addressSync.getPhone2(),
                                                                addressSync.getLatitude(),
                                                                addressSync.getLongtitude(),
                                                                addressSync.getImage(),
                                                                addressSync.getType(),
                                                                accountSync.getId(),
                                                                addressSync.getProvince(),
                                                                addressSync.getDistrict(),
                                                                addressSync.getExternalId());

                                JSONArray jsonContactShip;
                                jsonContactShip = getJSONArray.GetJSONArray(jsonShipTo.getJSONObject(j).optString("contacts"));
                                for (int k = 0; k < jsonContactShip.length(); k++) {
                                    contactsSync = new ContactsSync(jsonContactShip.get(k).toString());
                                    contactDBHelper.updateContact(contactsSync.getId(),
                                            contactsSync.getFirstName(),
                                            contactsSync.getLastName(),
                                            contactsSync.getPosition(),
                                            contactsSync.getTitle(),
                                            contactsSync.getBirthday(),
                                            contactsSync.getEmail(),
                                            contactsSync.getLineId(),
                                            contactsSync.getMobile(),
                                            contactsSync.getFavDrink(),
                                            contactsSync.getFavFood(),
                                            contactsSync.getFavSport(),
                                            contactsSync.getNonFavDrink(),
                                            contactsSync.getNonFavFood(),
                                            contactsSync.getNonFavAct(),
                                            contactsSync.getImageId(),
                                            1,
                                            contactsSync.getOwner(),
                                            contactsSync.getIsDecis(),
                                            contactsSync.getRelateOwn(),
                                            false,
                                            false,
                                            contactsSync.getExId());
                                }
                            }

                            List<PurchasedProduct> checkPurch = purchaseDBHelper.getListPurchasedProduct();

                            if (checkPurch.size() == 0) {
                                for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                    purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                                                        jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                                }
                            } else {
                                for (int j = 0; j < jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").length(); j++) {
                                    int checkId = 0;
                                    for (int k = 0; k < checkPurch.size(); k++) {
                                        if (checkPurch.get(k).getIdAcct().equals(accountSync.getId()) &&
                                                checkPurch.get(k).getProdCode().equals(jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString())) {
                                            checkId = 1;
                                            break;
                                        }
                                    }
                                    if (checkId == 0) {
                                        purchaseDBHelper.addPurchasedProduct(accountSync.getId(),
                                                                            jsonAcct.optJSONObject(i).getJSONArray("purchasedProducts").get(j).toString());
                                    }
                                }
                            }

                            JSONArray jsonContact;
                            jsonContact = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("contacts"));
                            Log.e("contacts size", jsonContact.length() + "");
                            for (int j = 0; j < jsonContact.length(); j++) {
                                contactsSync = new ContactsSync(jsonContact.get(j).toString());
                                contactDBHelper.updateContact(contactsSync.getId(),
                                                contactsSync.getFirstName(),
                                                contactsSync.getLastName(),
                                                contactsSync.getPosition(),
                                                contactsSync.getTitle(),
                                                contactsSync.getBirthday(),
                                                contactsSync.getEmail(),
                                                contactsSync.getLineId(),
                                                contactsSync.getMobile(),
                                                contactsSync.getFavDrink(),
                                                contactsSync.getFavFood(),
                                                contactsSync.getFavSport(),
                                                contactsSync.getNonFavDrink(),
                                                contactsSync.getNonFavFood(),
                                                contactsSync.getNonFavAct(),
                                                contactsSync.getImageId(),
                                                0,
                                                contactsSync.getOwner(),
                                                contactsSync.getIsDecis(),
                                                contactsSync.getRelateOwn(),
                                                false,
                                                false,
                                                contactsSync.getExId());
                            }

                            JSONArray jsonBillTo;
                            jsonBillTo = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("billTos"));
                            for (int j = 0; j < jsonBillTo.length(); j++) {
                                addressSync = new AddressSync(jsonBillTo.get(j).toString());
                                addressDBHelper.updateAddress(addressSync.getId(),
                                                            addressSync.getName(),
                                                            addressSync.getStreet(),
                                                            addressSync.getAddressLine1(),
                                                            addressSync.getAddressLine2(),
                                                            addressSync.getSubDistrict(),
                                                            addressSync.getState(),
                                                            addressSync.getCity(),
                                                            addressSync.getCountry(),
                                                            addressSync.getPostalCode(),
                                                            addressSync.getEmail(),
                                                            addressSync.getFax(),
                                                            addressSync.getMobile(),
                                                            addressSync.getPhone1(),
                                                            addressSync.getPhone2(),
                                                            addressSync.getLatitude(),
                                                            addressSync.getLongtitude(),
                                                            addressSync.getImage(),
                                                            addressSync.getType(),
                                                            accountSync.getId(),
                                                            addressSync.getProvince(),
                                                            addressSync.getDistrict(),
                                                            addressSync.getExternalId());
//                        Log.e("addressContact",jsonShipTo.getJSONObject(j).optString("contacts"));
                                JSONArray jsonContactBill;
                                jsonContactBill = getJSONArray.GetJSONArray(jsonBillTo.getJSONObject(j).optString("contacts"));
                                for (int k = 0; k < jsonContactBill.length(); k++) {
                                    contactsSync = new ContactsSync(jsonContactBill.get(k).toString());
                                    contactDBHelper.updateContact(contactsSync.getId(),
                                                                contactsSync.getFirstName(),
                                                                contactsSync.getLastName(),
                                                                contactsSync.getPosition(),
                                                                contactsSync.getTitle(),
                                                                contactsSync.getBirthday(),
                                                                contactsSync.getEmail(),
                                                                contactsSync.getLineId(),
                                                                contactsSync.getMobile(),
                                                                contactsSync.getFavDrink(),
                                                                contactsSync.getFavFood(),
                                                                contactsSync.getFavSport(),
                                                                contactsSync.getNonFavDrink(),
                                                                contactsSync.getNonFavFood(),
                                                                contactsSync.getNonFavAct(),
                                                                contactsSync.getImageId(),
                                                                1,
                                                                contactsSync.getOwner(),
                                                                contactsSync.getIsDecis(),
                                                                contactsSync.getRelateOwn(),
                                                                false,
                                                                false,
                                                                contactsSync.getExId());
                                }
                            }

                            JSONArray jsonTradeProm;
                            jsonTradeProm = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("tradePromotions"));
                            for (int j=0;j<jsonTradeProm.length();j++){
                                tradePromSync = new TradePromotionSync(jsonTradeProm.get(j).toString());
                                tradePromDBHelper.updateTradePromotion(tradePromSync.getId(),
                                                                    tradePromSync.getIdAcct(),
                                                                    tradePromSync.getProdCode(),
                                                                    tradePromSync.getType(),
                                                                    tradePromSync.getOrderAmount(),
                                                                    tradePromSync.getFoc());
                            }
                            JSONArray jsonSpecialPrice;
                            jsonSpecialPrice = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialPrices"));
                            for (int j=0;j<jsonSpecialPrice.length();j++) {
                                specialPriceSync = new SpecialPriceSync(jsonSpecialPrice.get(j).toString());
                                specialPriceDBHelper.updateSpecialPrice(specialPriceSync.getId(),
                                                                        specialPriceSync.getIdAcct(),
                                                                        specialPriceSync.getProdCode(),
                                                                        specialPriceSync.getPrice());
                            }
                            JSONArray jsonDiscount;
                            jsonDiscount = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialDiscounts"));
                            for (int j=0;j<jsonDiscount.length();j++) {
                                specialDiscountSync = new SpecialDiscountSync(jsonDiscount.get(j).toString());
                                specialDisDBHelper.updateSpecialDiscount(specialDiscountSync.getId(),
                                                                        specialDiscountSync.getIdAcct(),
                                                                        specialDiscountSync.getProdCode(),
                                                                        specialDiscountSync.getProduct(),
                                                                        specialDiscountSync.getExternalId(),
                                                                        specialDiscountSync.getPurchaseQty(),
                                                                        specialDiscountSync.getStartDate(),
                                                                        specialDiscountSync.getEndDate(),
                                                                        specialDiscountSync.getDis1Rate(),
                                                                        specialDiscountSync.getDis1Type(),
                                                                        specialDiscountSync.getDis2Rate(),
                                                                        specialDiscountSync.getDis2Type(),
                                                                        specialDiscountSync.getDis3Rate(),
                                                                        specialDiscountSync.getDis3Type());
                            }
                            JSONArray jsonBarcode;
                            jsonBarcode = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).getJSONObject("tradeAgreement").optString("specialBarcodes"));
                            for (int j=0;j<jsonBarcode.length();j++) {
                                specialBarcodeSync = new SpecialBarcodeSync(jsonBarcode.get(j).toString());
                                specialBarDBHelper.updateSpecialBarcode(specialBarcodeSync.getId(),
                                                                        specialBarcodeSync.getIdAcct(),
                                                                        specialBarcodeSync.getProdCode(),
                                                                        specialBarcodeSync.getBarcode());
                            }

//                            JSONArray jsonTrade;
//                            jsonTrade = getJSONArray.GetJSONArray(jsonAcct.getJSONObject(i).optString("tradeAgreements"));
//                            for (int j = 0; j < jsonTrade.length(); j++) {
//                                tradeAgreementSync = new TradeAgreementSync(jsonTrade.getJSONObject(j));
//                                tradeAgreementDatabaseHelper.addTradeAgreement(tradeAgreementSync.getIdAcct(),
//                                                            tradeAgreementSync.getProdCode(),
//                                                            tradeAgreementSync.getPrice(),
//                                                            tradeAgreementSync.getDiscount1(),
//                                                            tradeAgreementSync.getDis1Type(),
//                                                            tradeAgreementSync.getDiscount2(),
//                                                            tradeAgreementSync.getDis2Type(),
//                                                            tradeAgreementSync.getDiscount3(),
//                                                            tradeAgreementSync.getDis3Type(),
//                                                            tradeAgreementSync.getBarcode());
//                            }
                        }

                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultAC;
        }
        // *********** ADD Progress Bar End

        protected void onProgressUpdate(String... value) {
            progressBar6.setSecondaryProgress(Integer.parseInt(value[0]) + 2);
        }

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);

        }
    } // Print 5 end

    class AsyncTGETInvoice extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            int count;

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/invoice";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/invoice";
            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);


            try {

                httpGet.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpGet.setHeader("Content-Type", "application/json");
                HttpResponse response = httpclient.execute(httpGet);
                Log.e("Check Invoice Array", "Run_AsyncTGETInvoice()_jsonResult ----- BEFORE: " + jsonResultInv);

                // ****************** Execute HTTP Post Request ====== Salesvisit
                jsonResultInv = inputStreamToString(response.getEntity().getContent()).toString();
                Log.e("Check Invoice Array", "Run_AsyncTGETInvoice()_jsonResult ----- After: " + jsonResultInv);


                // FOR Poa to implement this method: jsonResult

                JSONArray jsonArr = new JSONArray(jsonResultInv);

                List<Invoice> invoiceCheckId = invoiceDatabaseHelper.getListInvoice();

                int check = 0;
                for (int i=0;i<jsonArr.length();i++){

                    invoiceSync = new InvoiceSync(jsonArr.getJSONObject(i).toString());
                    for (int index=0;index<invoiceCheckId.size();index++){
                        if (invoiceCheckId.get(index).getId().equals(invoiceSync.getId())){
                            check = 1;
                            break;
                        }
                    }
                    if (check == 0){
                        invoiceDatabaseHelper.addInvoice(invoiceSync.getId(),
                                                        invoiceSync.getInvNumber(),
                                                        invoiceSync.getAccountId(),
                                                        invoiceSync.getOrderId(),
                                                        invoiceSync.getTotal(),
                                                        invoiceSync.getDueDate(),
                                                        invoiceSync.getPostingDate());
                        Log.e("chkInvDet","chkInvDetSize_"+invoiceSync.getInvoiceDetailSync().getId().size());
                        for (int j=0;j<invoiceSync.getInvoiceDetailSync().getId().size();j++) {
                            invDetDatabaseHelper.addInvoiceDetail(invoiceSync.getInvoiceDetailSync().getId().get(j),
                                                                invoiceSync.getId(),
                                                                invoiceSync.getInvoiceDetailSync().getLineNum().get(j),
                                                                invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getId(),
                                                                invoiceSync.getInvoiceDetailSync().getProductCode().get(j),
                                                                invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getName(),
                                                                invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getDescription(),
                                                                invoiceSync.getInvoiceDetailSync().getUnitPrice().get(j),
                                                                invoiceSync.getInvoiceDetailSync().getQuantity().get(j),
                                                                invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getUom(),
                                                                invoiceSync.getInvoiceDetailSync().getDisPercent().get(j),
                                                                invoiceSync.getInvoiceDetailSync().getDisAmount().get(j));
                        }
                    }else {
                        invoiceDatabaseHelper.updateInvoice(invoiceSync.getId(),
                                                            invoiceSync.getInvNumber(),
                                                            invoiceSync.getAccountId(),
                                                            invoiceSync.getOrderId(),
                                                            invoiceSync.getTotal(),
                                                            invoiceSync.getDueDate(),
                                                            invoiceSync.getPostingDate());

                        Log.e("chkInvDet","chkInvDetSize_"+invoiceSync.getInvoiceDetailSync().getId().size());
                        for (int j=0;j<invoiceSync.getInvoiceDetailSync().getId().size();j++) {
                            invDetDatabaseHelper.updateInvoiceDetail(invoiceSync.getInvoiceDetailSync().getId().get(j),
                                                                    invoiceSync.getId(),
                                                                    invoiceSync.getInvoiceDetailSync().getLineNum().get(j),
                                                                    invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getId(),
                                                                    invoiceSync.getInvoiceDetailSync().getProductCode().get(j),
                                                                    invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getName(),
                                                                    invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getDescription(),
                                                                    invoiceSync.getInvoiceDetailSync().getUnitPrice().get(j),
                                                                    invoiceSync.getInvoiceDetailSync().getQuantity().get(j),
                                                                    invoiceSync.getInvoiceDetailSync().getInvoiceProductSync().getUom(),
                                                                    invoiceSync.getInvoiceDetailSync().getDisPercent().get(j),
                                                                    invoiceSync.getInvoiceDetailSync().getDisAmount().get(j));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultInv;
        }
        // *********** ADD Progress Bar End
        protected void onProgressUpdate(String... value) {
            progressBar7.setSecondaryProgress(Integer.parseInt(value[0]) + 2);
        }

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);

        }
    } // Print 5 end

    class AsyncTPOSTAccount extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/account";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/account";
//            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);
            HttpPost httpPost = new HttpPost(url3);

            try {

                httpPost.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpPost.setHeader("Content-Type", "application/json");

                List<Account> accountList = accountDatabaseHelper.getListAccountList();

                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                int state = 0;

                for (int i=0;i<accountList.size();i++){
                    if (accountList.get(i).isNew()){
                        EditAccountSync editAccountSync = new EditAccountSync();
                        JSONObject acctObj = editAccountSync.EditAccountSync(getActivity(),
                                                                            accountList.get(i).getId(),
                                                                            accountList.get(i).getName(),
                                                                            accountList.get(i).getChannel(),
                                                                            accountList.get(i).getPhone1(),
                                                                            accountList.get(i).getPhone2(),
                                                                            accountList.get(i).getMobile(),
                                                                            accountList.get(i).getFax(),
                                                                            accountList.get(i).getEmail(),
                                                                            accountList.get(i).getWebsite());

                        jsonArray.put(acctObj);
                        state = 1;
                    }
                }
                jsonObject.put("customers",jsonArray);

//                Log.e("chkJSONAcct","chkJSONAcct_"+jsonObject.toString());
                if (state == 1) {
                    StringEntity se = new StringEntity(jsonObject.toString(), "UTF-8");
                    httpPost.setEntity(se);
                    HttpResponse responsePost = httpclient.execute(httpPost);
                    jsonResultAC = inputStreamToString(responsePost.getEntity().getContent()).toString();
                    Log.e("Check Account Array", "Run_AsyncTPOSTAccount()_jsonResult ----- After: " + jsonResultAC);
                }else {
                    Log.e("chkState","chkState_No more new account");
                }

                // FOR Poa to implement this method: jsonResult



            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultAC;
        }
        // *********** ADD Progress Bar End

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);

        }
    } // Print 5 end

    class AsyncTPOSTSalesVisit extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/salesvisit";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/salesvisit";
//            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);
            HttpPost httpPost = new HttpPost(url3);

            try {

                httpPost.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpPost.setHeader("Content-Type", "application/json");

                List<SalesVisit> salesVisits = salesVisitDatabaseHelper.getListSalesVisit();

                Log.e("chkSVSize","chkSVSize_"+salesVisits.size());

                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                JSONArray deleteObj = new JSONArray();

                int stateNew = 0;
                int stateDel = 0;

                for (int i=0;i<salesVisits.size();i++){
                    if (salesVisits.get(i).isNew()){
                        EditSalesVisitSync editAccountSync = new EditSalesVisitSync();
                        JSONObject salesvisitObj = editAccountSync.EditSalesVisitSync(getActivity(),
                                                                                    salesVisits.get(i).getId(),
                                                                                    salesVisits.get(i).getAddrid(),
                                                                                    salesVisits.get(i).getChkIn(),
                                                                                    salesVisits.get(i).getChkInlat(),
                                                                                    salesVisits.get(i).getChkInlng(),
                                                                                    salesVisits.get(i).getChkOut(),
                                                                                    salesVisits.get(i).getTimeIn(),
                                                                                    salesVisits.get(i).getPrevisitnote(),
                                                                                    salesVisits.get(i).getTimeOut(),
                                                                                    salesVisits.get(i).getVisitNote(),
                                                                                    salesVisits.get(i).getType());
                        jsonArray.put(salesvisitObj);
                        stateNew = 1;
                    }
                    if (salesVisits.get(i).isDeleted()){
                        deleteObj.put(salesVisits.get(i).getId());
                        stateDel = 1;
                    }
                }

                jsonObject.put("salesVisits",jsonArray);
                jsonObject.put("deletedPlanIds",deleteObj);

//                Log.e("salesvisitsync","salesvisitsync:_"+jsonObject.toString());

                if (stateNew == 1 || stateDel == 1) {
                    StringEntity se = new StringEntity(jsonObject.toString(), "UTF-8");
                    httpPost.setEntity(se);
                    HttpResponse responsePost = httpclient.execute(httpPost);
                    jsonResultSV = inputStreamToString(responsePost.getEntity().getContent()).toString();
                    Log.e("Check SalesVisit Array", "Run_AsyncTPOSTSalesVisit()_jsonResult ----- After: " + jsonResultSV);
                }else {
                    Log.e("chkState","chkState_No more new salesvisits");
                }

                // FOR Poa to implement this method: jsonResult



            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultSV;
        }
        // *********** ADD Progress Bar End

        protected void onProgressUpdate(String... value) {
            progressBar8.setSecondaryProgress(Integer.parseInt(value[0]) + 2);
        }

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);
            communicator.MessageHTTP("tvTitleTest");
        }
    } // Print 5 end

    class AsyncTPOSTImage extends AsyncTask<String, String, String> { // Print 5 start

        @Override
//        protected Void doInBackground(String... f_url) {
        protected String doInBackground(String... f_url) {

            HttpClient httpclient = new DefaultHttpClient();
            GlobalState globalState = (GlobalState) getActivity().getApplication();
            OAuthTokens myTokens = globalState.getAccessTokens();

            // String url3 = myTokens.get_instance_url() + "/services/apexrest/MobileSales/image";
            String url3 = myTokens.get_instance_url() + "/services/apexrest/image";
//            HttpGet httpGet = new HttpGet(url3);
//            HttpGet httpGetImg = new HttpGet(downloadUrl);
            HttpPost httpPost = new HttpPost(url3);

            try {

                httpPost.setHeader("Authorization","Authorization: Bearer "+myTokens.get_access_token());
                httpPost.setHeader("Content-Type", "application/json");

                ZipFileHepler.zip(dataPath, zipPath, "data.zip");

                File file = new File(zipPath+"data.zip");
                byte[] byteArray = null;

                InputStream inputStream = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024*20];
                int bytesRead =0;

                while ((bytesRead = inputStream.read(b)) != -1)
                {
                    bos.write(b, 0, bytesRead);
                }

                byteArray = bos.toByteArray();

                ByteArrayEntity be = new ByteArrayEntity(byteArray);
                httpPost.setEntity(be);
                HttpResponse responsePost = httpclient.execute(httpPost);
                jsonResultImage = inputStreamToString(responsePost.getEntity().getContent()).toString();
                Log.e("Check Image", "Run_AsyncTPOSTImage()_jsonResult ----- : " + jsonResultImage);


                // FOR Poa to implement this method: jsonResult



            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonResultImage;
        }
        // *********** ADD Progress Bar End

        @Override
//        protected void onPostExecute(Void aVoid) {
        protected void onPostExecute(String jsonRe) {

            super.onPostExecute(jsonRe);

        }
    } // Print 5 end

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    public static class InputStreamToStringExample { // Print 2 start

        public static void main(String[] args) throws IOException {

            // intilize an InputStream
            InputStream is = new ByteArrayInputStream("file content..blah blah".getBytes());

            String result = getStringFromInputStream(is);

            System.out.println(result);
            System.out.println("Done");

        }

        // convert InputStream to String
        private static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }

    private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();

        InputStreamReader isr = new InputStreamReader(is);

        BufferedReader rd = new BufferedReader(isr);

        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(TargetDatabaseHelper.DBNAME);
            String outFileName = TargetDatabaseHelper.DBLOCATION + TargetDatabaseHelper.DBNAME;
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
}
