package com.salesforce.android.restsample.Tablet.StartVisit;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBInvoice.Invoice;
import com.salesforce.android.restsample.DB.Model.DBInvoice.InvoiceDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBMarket.Market;
import com.salesforce.android.restsample.DB.Model.DBMarket.MarketDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBMerchandising.Merchandising;
import com.salesforce.android.restsample.DB.Model.DBMerchandising.MerchandisingDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderDetail.OrderDetail;
import com.salesforce.android.restsample.DB.Model.DBOrderDetail.OrderDetailDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporaryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPurchasedProduct.PurchasedProduct;
import com.salesforce.android.restsample.HomeTabletMainActivity;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIcon.Tab3Fragment;
//import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.StartVisit.Default.TabletTab1Default;
import com.salesforce.android.restsample.Tablet.StartVisit.Default.TabletTab2Default;
import com.salesforce.android.restsample.Tablet.StartVisit.Default.TabletTab2DeliveryDefault;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryAdapterSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetProductSinglePage2;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.Tab2SinglePage;
//import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.Tab2SinglePageEmpty;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.Tab2SinglePageALL;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab1.TabletTab1;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2.TabletTab2;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2.TabletTab2Promotion2;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2.TabletTab2setOrderDeliveryActivity;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2.TabletTab2setOrderDeliveryDetailActivity2;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3.TabletTab3;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3.TabletTab3FragmentDetail;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab4.TabletTab4;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab4.TabletTab4FragmentDetail;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab5.TabletTab5;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab5.TabletTab5FragmentDetail2;
import com.salesforce.android.restsample.Tablet.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pannikar on 7/21/16 AD.
 */
public class TabletCustomViewIconTextTabsActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener,
                                                                                    TabletTab1.Communicator,
                                                                                    TabletTab2.Communicator,
                                                                                    TabletTab3.Communicator,
                                                                                    TabletTab4.Communicator,
                                                                                    TabletTab5.Communicator,
        TabletTab2setOrderDeliveryActivity.Communicator,
        CommunicatorV4 {

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV; //

    ProductBrandDatabaseHelper mDBBrand;
    ActionBarDrawerToggle mDrawerToggle;
    AccountDatabaseHelper mDBHelper;

    List<Account> getAccount;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView mTitle;
   // TextView mTitleSave;


    // test to add Sliding in Activity Start
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    // test to add Sliding inActivity end

    Typeface fontThSarabun;
    Typeface fontThSarabunBold;

    String positionBefore;

    TextView tvName;
    TextView tvContacts;
    TextView tvSumOfVisit;
    TextView tvTitle01;
    TextView tvValue01;
    TextView tvTitle02;
    TextView tvValue02;
    TextView tvTitle03;
    TextView tvValue03;
    TextView tvTitle04;
    TextView tvValue04;
    TextView tvTitle04_2;
    TextView tvValue04_2;
    TextView tvTitle05;
    TextView tvValue05;
    TextView tvBtn01;
    TextView tvBtn02;

    String s1;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    MarketDatabaseHelper markerDB;
    OrderDetailDatabaseHelper orderDetailDB;
    MerchandisingDatabaseHelper merchandDB;
    InvoiceDatabaseHelper invoiceDB;

    ProductDatabaseHelper mDBProduct;

    List<Market> lsMarketDetail = new ArrayList<Market>();
    List<OrderDetail> lsOrderDetail = new ArrayList<OrderDetail>();
    List<Merchandising> lsMerchandisingDetail = new ArrayList<Merchandising>();
    List<Invoice> lsInvoiceDetail = new ArrayList<Invoice>();
    private Bundle args;
    private LoopProductToSet lpdTs;

    FrameLayout frameLayout;

    // set Detail Run in Background START #1
    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();

    List<PurchasedProduct> mPurchasedList = new ArrayList<>();
//    EntryAdapterSinglePage entry;
    // set Detail Run in Background End #1

//    GetProductSinglePage getSnglePg;
    GetProductSinglePage2 getSnglePg;

    ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();

    // for Feedback
    ArrayList arrProductListFdBack = new ArrayList();
    ArrayList arrProductCodeFdBack = new ArrayList();
    ArrayList<ItemSinglePage> itemsALLProductFdBack = new ArrayList<ItemSinglePage>();

    ArrayList arrProductListOrder = new ArrayList();
    ArrayList arrProductCodeOrder = new ArrayList();
    List<String> listHeaderColorOrder = new ArrayList<String>();
    ArrayList<ItemSinglePage> itemsALLProductOrder = new ArrayList<ItemSinglePage>();

    ArrayList arrlistHeaderColor = new ArrayList();


    DownloadALL dlALL;
    DownloadORDER dlORDER;
    DownloadPROMOTION dlPROM;
    DownloadFeedback dlFdBck;

    HashMap<String, String> meMapProdBrd = new HashMap<String, String>();
    HashMap<String, String> meMapProdCat = new HashMap<String, String>();
    HashMap<String, String> meMapProd = new HashMap<String, String>();

    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();

    Fragment frgProduct;

    List<Integer> listNewFeedback = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.tablet_tab_0_3);


        frameLayout = (FrameLayout) findViewById(R.id.containerView2);



        Bundle extras = getIntent().getExtras();
//        newIdAddr= extras.getString("getIdAdd");
//        newId= extras.getString("getId");
//        newNumber= extras.getString("getNumber");
//        newName= extras.getString("getName");
//        newTime= extras.getString("getTime");
//        newIdSV = extras.getInt("getIdSV");
//        s1 = extras.getString("Check");

//        newIdAddr= "00128000007uGpPAAU";
//        newId= "00128000007uGpMAAU";
//        newIdSV = 2;
//        newNumber= "CD355118";
//        newName= "United Oil & Gas Corp.";
//        newTime= "Apr 19, 2016 07:03 PM";
//        s1 = "1";

        /*
        * กล่องยา
        * add_id="0012800000aIwCOAA0"
        * acc_id=0012800000aIvoHAAS
        * number="1019301920"
        * name="กล่องยา"
        * */

        newIdAddr= "00128000013ZFa7AAG";
        newId= "00128000013ZFZqAAO";
        newIdSV = 1;
        newNumber= "00010";
        newName= "กานดาพานิชย์";
        newTime= "Apr 19, 2016 07:03 PM";
        s1 = "1";



        args = new Bundle();
        args.putString("getIdAdd",newIdAddr);
        args.putString("getNumber",newNumber);
        args.putString("getName",newName);
        args.putString("getTime",newTime);
        args.putString("getId",newId);
        args.putInt("getIdSV",newIdSV);
        s1 = "1";

        Log.e("TabFragmentSplitView", "Chck_Bundle:_newIdAddr: " + newIdAddr);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_newId: " + newId);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_newNumber: " + newNumber);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_newName: " + newName);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_newTime: " + newTime);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_newIdSV: " + newIdSV);
        Log.e("TabFragmentSplitView", "Chck_Bundle:_s1: " + s1);

        fontThSarabun = Typeface.createFromAsset(getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getAssets(), "fonts/THSarabunBold.ttf");

        mDBHelper = new AccountDatabaseHelper(this);
        getAccount =  mDBHelper.getListAccountListBySearchId(newId);
        mDBBrand = new ProductBrandDatabaseHelper(this);
        mDBProduct = new ProductDatabaseHelper(this);

        setTitle(null);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // use

        // set Detail Run in Background START #2
        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        itemsALLProduct.clear(); // *** *** *** Clear All IMPORTANT !!!
        mPurchasedList.clear();


//        new DownloadImageTask2().execute();
//        new DownloadALL(this, newId).execute();

         Log.e("OrderDeliveryActivity", "chk_dl.size()_1-0: " + arrProductList.size());
        Log.e("OrderDeliveryActivity", "chk_dl.size()_1-0: " + "arrProductList.size()");


        if(itemsALLProduct.size() ==0  || listNewFeedback.size() ==0 ){
            dlALL = new DownloadALL(this, newId, newIdSV);
            dlALL.setUpdateListener(new DownloadALL.OnUpdateListener() {
                @Override
                public void onUpdate(String obj, ArrayList arrProductListNew,
                                     ArrayList arrProductCodeNew,
                                     ArrayList<ItemSinglePage> itemsALLProductNew,
                                     HashMap<String, String> meMapProdBrdNew,
                                     HashMap<String, String> meMapProdCatNew,
                                     HashMap<String, String> meMapProdNew,

                                     HashMap<Integer, String> mapALLIndPrdNameNew,
                                     HashMap<Integer, String> mapALLIndColorNew,
                                     List<Integer> listNewFeedbackNew) {
                    arrProductList = arrProductListNew;
                    arrProductCode = arrProductCodeNew;
                    itemsALLProduct = itemsALLProductNew;

                    meMapProdBrd = meMapProdBrdNew;
                    meMapProdCat = meMapProdCatNew;
                    meMapProd = meMapProdNew;

                    mapALLIndPrdName = mapALLIndPrdNameNew;
                    mapALLIndColor = mapALLIndColorNew;

                    listNewFeedback = listNewFeedbackNew;

                    Log.e("OrderDeliveryActivity", "mainTab: item.size:_00: " + listNewFeedback.size());

                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-1: " + arrProductList.size());
                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-2: " + arrProductCode.size());
                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-4: " + itemsALLProduct.size());

                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-5: " + meMapProdBrd.size());
                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-6: " + meMapProdCat.size());
                    Log.e("OrderDeliveryActivity", "chk_dl.size()_1-7: " + meMapProd.size());
                }

            });
            dlALL.execute();

//        Log.e("OrderDeliveryActivity", "chk_dl.size()_1-7 after1: " + meMapProd.size());
        }

//             if(arrProductListFdBack.size() ==0 ){
//                dlFdBck = new DownloadFeedback(this, newId, newIdSV);
//                dlFdBck.setUpdateListener(new DownloadFeedback.OnUpdateListener() {
//
//                    @Override
//                    public void onUpdate(String obj,
//                                         ArrayList arrProductListNew,
//                                         ArrayList arrProductCodeNew,
//                                         ArrayList<ItemSinglePage> itemsALLProductNew,
//                                         List<Integer> listNewFeedbackNew) {
//                        arrProductListFdBack = arrProductListNew;
//                        arrProductCodeFdBack = arrProductCodeNew;
//                        itemsALLProductFdBack = itemsALLProductNew;
//                        listNewFeedback = listNewFeedbackNew;
//
//                        Log.e("OrderDeliveryActivity", "mainTab: item.size:_1: " + itemsALLProductFdBack.size());
//                    }
//
//                });
//                dlFdBck.execute();
//            }

        getSnglePg = new GetProductSinglePage2(this, newId);


        Utility utl = new Utility();
        utl.exportDB2(this);


        // test START
        OrderTemporaryDatabaseHelper mDBorderTemp;
        mDBorderTemp = new OrderTemporaryDatabaseHelper(this);

        Log.e("test", "contain: position: ADD: test: size(): " + mDBorderTemp.getListOrderTemp().size());


        // test END

        // set Detail Run in Background END #2

//        String
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ START

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        //View header = mNavigationView.inflateHeaderView(R.layout.navigation);
        View header = mNavigationView.inflateHeaderView(R.layout.navigation2);

        tvName = (TextView) header.findViewById(R.id.tvName);
        tvContacts = (TextView) header.findViewById(R.id.tvContacts);
        tvSumOfVisit = (TextView) header.findViewById(R.id.tvSumOfVisit);
        tvTitle01 = (TextView) header.findViewById(R.id.tvTitle01);
        tvValue01 = (TextView) header.findViewById(R.id.tvValue01);
        tvTitle02 = (TextView) header.findViewById(R.id.tvTitle02);
        tvValue02 = (TextView) header.findViewById(R.id.tvValue02);
        tvTitle03 = (TextView) header.findViewById(R.id.tvTitle03);
        tvValue03 = (TextView) header.findViewById(R.id.tvValue03);
        tvTitle04 = (TextView) header.findViewById(R.id.tvTitle04);
        tvValue04 = (TextView) header.findViewById(R.id.tvValue04);
        tvTitle04_2 = (TextView) header.findViewById(R.id.tvTitle04_2);
        tvValue04_2 = (TextView) header.findViewById(R.id.tvValue04_2);
        tvTitle05 = (TextView) header.findViewById(R.id.tvTitle05);
        tvValue05 = (TextView) header.findViewById(R.id.tvValue05);
        tvBtn01 = (TextView) header.findViewById(R.id.tvBtn01);
        tvBtn02 = (TextView) header.findViewById(R.id.tvBtn02);

        // .setTypeface(fontThSarabunBold);
        tvName.setTypeface(fontThSarabunBold);
        tvContacts.setTypeface(fontThSarabunBold);
        tvSumOfVisit.setTypeface(fontThSarabunBold);
        tvTitle01.setTypeface(fontThSarabunBold);
        tvValue01.setTypeface(fontThSarabunBold);
        tvTitle02.setTypeface(fontThSarabunBold);
        tvValue02.setTypeface(fontThSarabunBold);
        tvTitle03.setTypeface(fontThSarabunBold);
        tvValue03.setTypeface(fontThSarabunBold);
        tvTitle04.setTypeface(fontThSarabunBold);
        tvValue04.setTypeface(fontThSarabunBold);
        tvTitle04_2.setTypeface(fontThSarabunBold);
        tvValue04_2.setTypeface(fontThSarabunBold);
        tvTitle05.setTypeface(fontThSarabunBold);
        tvValue05.setTypeface(fontThSarabunBold);
        tvBtn01.setTypeface(fontThSarabunBold);
        tvBtn02.setTypeface(fontThSarabunBold);

        setDatabase();
        setValueFromDatabase();

        // Set Detail call LoopProduct Start
//        LoopProduct lpd = new LoopProduct(getApplicationContext(), args);
//        lpd.methodLoop();
//
//        lpdTs = new LoopProductToSet(getApplicationContext(), args);
//        lpdTs.methodLoop();
//        Log.e("test", "test_Loop:_size:_" + lpdTs.strA.size());
//        args.putStringArrayList("strPrdLoop", lpdTs.strA);


        // Set Detail call LoopProduct End


        if(getAccount.size()!=0){

            tvName.setText(getAccount.get(0).getName());
            Log.e("Fragment", "navigation_tvName:_close: "+ getAccount.get(0).getName());

        }

        tvContacts.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(TabletCustomViewIconTextTabsActivity.this, FragmentContactList.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                return false;
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.app_name,
                                                    R.string.app_name){
            public void onDrawerClosed(View view) {

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ END

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setTypeface(fontThSarabunBold);
        mTitle.setText("Visit");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        switch (s1){
            case "1": viewPager.setCurrentItem(0);
                break;
            case "2": viewPager.setCurrentItem(1);
                break;
            case "3": viewPager.setCurrentItem(2);
                break;
            case "4": viewPager.setCurrentItem(3);
                break;
            case "5": viewPager.setCurrentItem(4);
                break;
            case "6": viewPager.setCurrentItem(5);
                break;
            case "7": viewPager.setCurrentItem(6);
                break;
        }

        /// +++++++++++++++++++++++++++++++++++++++++++++++++ End

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        // set Fragment Start
        Fragment frgMaster;
        Fragment frgDetail;

        frgMaster = new TabletTab1();
        args = new Bundle();
        args.putString("getId", newId);
        args.putInt("getIdSV", newIdSV);
        // for feedback
        args.putSerializable("arrProductList",arrProductListFdBack);
        args.putSerializable("arrProductCode",arrProductCodeFdBack);
        args.putSerializable("testITEMALL",itemsALLProductFdBack);
        //listNewFeedback
        args.putIntegerArrayList("listNewFeedback",(ArrayList<Integer>)listNewFeedback);

        Log.e("TabletCustom", "mainTab: newId:_" + newId);
        Log.e("TabletCustom", "mainTab: item.size:_2: " + listNewFeedback.size());
        frgMaster.setArguments(args);

//        frgDetail = new TabletTab1FragmentVisitCont();
        frgDetail = new TabletTab1Default();
//        frgDetail = new TabletTab1FragmentProduct();
        frgDetail.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.containerView1, frgMaster);// frgDetail);
        fragmentTransaction.replace(R.id.containerView2, frgDetail);
        Log.e("MainActivity","Row101_main_Landscape_OS_Name:_replace:_" + "fisttime");
        fragmentTransaction.commit();
        // set Fragment End

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        Fragment frg;
                        Fragment frgDetail;

                        int position = tab.getPosition();
                        Log.e("PlanActivity4", "Done_numTab:_" + position);

                        String positionSt = String.valueOf(position);

                        setTitle(null);

                        if (positionSt.equals(positionBefore)) {
                            Log.e("PlanActivity4", "Not_change_numTab:_" + position);
                        } else {
                            Log.e("PlanActivity4", "change_numTab:_" + position);
                            setTitle(null);
                            toolbar = (Toolbar) findViewById(R.id.toolbar);
                            switch (position) {
                                case 0:
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Visit");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    frg = new TabletTab1();
                                    Log.e("TabletCustom", "mainTab: newId:_" + newId);
                                    Log.e("TabletCustom", "mainTab: newIdSV:_" + newIdSV);
                                    // for feedback
                                    args.putSerializable("arrProductList",arrProductListFdBack);
                                    args.putSerializable("arrProductCode",arrProductCodeFdBack);
                                    args.putSerializable("testITEMALL",itemsALLProductFdBack);
                                    args.putIntegerArrayList("listNewFeedback",(ArrayList<Integer>)listNewFeedback);

                                    Log.e("TabletCustom", "mainTab: item.size:_3: " + listNewFeedback.size());
                                    frg.setArguments(args);

//                                    frgDetail = new TabletTab1FragmentVisitCont();
                                    frgDetail = new TabletTab1Default();
//                                    frgDetail = new TabletTab1FragmentProduct();
                                    frgDetail.setArguments(args);
                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);
                                    break;

                                case 1: frameLayout.setVisibility(View.GONE);
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Promotion");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    // frg = new TabletTab2Promotion();
                                    frg = new TabletTab2Promotion2();
                                    frg.setArguments(args);

                                    frgDetail = new TabletTab2Default();
                                    args.putSerializable("testITEM",itemsALLProduct);
                                    frgDetail.setArguments(args);

                                    // set frgDetail to CHECK

                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);


                                    break;

                                case 2: frameLayout.setVisibility(View.GONE);
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Products");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

//                                    mTitleSave.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            if(0>0){
//
//                                            } else {
//                                                Log.e("test", "getView:_entry:_1:_position: " + "Save!!!");
//                                            }
//                                        }
//                                    });


//                                    // +++ add Tab 2 START
//                                    if(arrProductListOrder.size() == 0 ){
//                                        dlORDER = new DownloadORDER(getApplicationContext(), newId, meMapProdBrd, meMapProdCat, meMapProd);
//
//                                        Log.e("OrderDeliveryActivity", "chk_dl.size()_1-7_frg: " + arrProductListOrder.size());
//
//                                        dlORDER.setUpdateListener(new DownloadORDER.OnUpdateListener() {
//                                            @Override
//                                            public void onUpdate(String obj, ArrayList arrProductListNew,
//                                                                 ArrayList arrProductCodeNew, List<String> listHeaderColorNew,
//                                                                 ArrayList<ItemSinglePage> itemsALLProductNew, HashMap<String, String> meMap) {
//
//                                                arrProductListOrder = arrProductListNew;
//                                                arrProductCodeOrder = arrProductCodeNew;
//                                                listHeaderColorOrder = listHeaderColorNew;
//                                                itemsALLProductOrder = itemsALLProductNew;
//
//                                                frgProduct = new Tab2SinglePageALL(getApplicationContext(),
//                                                        //mTitleSave,
//                                                        getSnglePg.getResults().size(),
//                                                        newIdSV,
//                                                        arrProductList,
//                                                        arrProductCode,
//
//                                                        arrProductListOrder,
//                                                        arrProductCodeOrder,
//                                                        listHeaderColorOrder,
//
//                                                        mapALLIndPrdName,
//                                                        mapALLIndColor);
//                                                args.putSerializable("testITEMALL",itemsALLProduct);
//                                                args.putSerializable("testITEMORDER",itemsALLProductOrder);
//                                                frgProduct.setArguments(args);
//
////                                    Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1--: ");
////
////
////                                    Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-4: " + arrProductList.size());
////                                    Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-5: " + arrProductCode.size());
////                                    Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-6: " + listHeaderColor.size());
////                                    Log.e("OrderDeliveryActivity", "chk_dl.listHeaderColor.size()_1-4: " + itemsALLProduct.size());
//
////                                    frgDetail = new TabletTab2Promotion();
////                                    frgDetail.setArguments(args);
//
//                                                // set frgDetail to CHECK
////                                     frgDetail = new TabletTab2setPlanDetailActivity();
////                                     frgDetail = new TabletTab2setOrderDeliveryActivity();
////                                     frgDetail = new TabletTab2setOrderDeliveryDetailActivity2();
////                                     frgDetail = new TabletTab2setOrderSelectAddressBillTo();
////                                     frgDetail = new TabletTab2setOrderSelectAddressShipTo();
////                                     frgDetail = new TabletTab2setPlanDetailPromotionActivity();
////                                     frgDetail = new TabletTab2setTab2ProductReturnReasons();
//
//                                                // frgDetail = new ;
//
//                                                changePageFragment(3, frgProduct);
//
//                                            }
//                                        });
//                                        dlORDER.execute();
//                                        break;
//                                    } else if(arrProductListOrder.size() > 0 ) {
//
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-11:_frg: " + arrProductList.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-12:_frg: " + arrProductCode.size());
////                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-13:_frg: " + listHeaderColor.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-14:_frg: " + itemsALLProduct.size());
//
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-15:_frg: " + arrProductListOrder.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-16:_frg: " + arrProductCodeOrder.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-17:_frg: " + listHeaderColorOrder.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-18:_frg: " + itemsALLProductOrder.size());
//
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-19:_frg: " + itemsALLProduct.size());
//                                        Log.e("OrderDeliveryActivity", "chk_singlePgEmpty._0-20:_frg: " + itemsALLProductOrder.size());
//
//                                        frgProduct = new Tab2SinglePageALL(getApplicationContext(),
//                                                // mTitleSave,
//                                                getSnglePg.getResults().size(),
//                                                newIdSV,
//                                                arrProductList,
//                                                arrProductCode,
//
//                                                arrProductListOrder,
//                                                arrProductCodeOrder,
//                                                listHeaderColorOrder,
//
//                                                mapALLIndPrdName,
//                                                mapALLIndColor);
//                                        args.putSerializable("testITEMALL",itemsALLProduct);
//                                        args.putSerializable("testITEMORDER",itemsALLProductOrder);
//                                        frgProduct.setArguments(args);
//                                        changePageFragment(3, frgProduct);
//                                        break;
//                                    }
////                                    break;
//                                    // +++ add Tab 2 END

//                                    frg = new TabletTab2();
//                                    frg = new TabletTab2ver3();
//                                    frg = new Tab2SinglePage();


                                    /*
                                    * Try to reduce code
                                    * */

                                    frgProduct = new Tab2SinglePageALL(getApplicationContext(),
                                            // mTitleSave,
                                            getSnglePg.getResults().size(),
                                            newIdSV,
                                            arrProductList,
                                            arrProductCode,

                                            arrProductListOrder,
                                            arrProductCodeOrder,
                                            listHeaderColorOrder,

                                            mapALLIndPrdName,
                                            mapALLIndColor);
                                    args.putSerializable("testITEMALL",itemsALLProduct);
                                    args.putSerializable("testITEMORDER",itemsALLProductOrder);
                                    frgProduct.setArguments(args);
                                    changePageFragment(3, frgProduct);
                                    break;



                                case 3: frameLayout.setVisibility(View.GONE);
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Order Delivery");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    frg = new TabletTab2setOrderDeliveryActivity();
                                    args.putSerializable("testITEM",itemsALLProduct);
                                    frg.setArguments(args);

                                    frgDetail = new TabletTab2DeliveryDefault();
                                    args.putString("getId",newId);
                                    args.putInt("getIdSV",newIdSV);
                                    frgDetail.setArguments(args);

                                    // set frgDetail to CHECK
//                                     frgDetail = new TabletTab2setPlanDetailActivity();
//                                     frgDetail = new TabletTab2setOrderDeliveryActivity();
//                                     frgDetail = new TabletTab2setOrderDeliveryDetailActivity2();
//                                     frgDetail = new TabletTab2setOrderSelectAddressBillTo();
//                                     frgDetail = new TabletTab2setOrderSelectAddressShipTo();
//                                     frgDetail = new TabletTab2setPlanDetailPromotionActivity();
//                                     frgDetail = new TabletTab2setTab2ProductReturnReasons();

                                    // frgDetail = new ;

//                                    changePageFragment(3, frg);
                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);

                                    break;


                                case 4:
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Competitor");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    frg = new TabletTab3();
                                    frgDetail = new TabletTab3FragmentDetail();

                                    frg.setArguments(args);
                                    frgDetail.setArguments(args);

                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);
                                    break;


                                case 5:
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Merchandising");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    frg = new TabletTab4();
                                    frgDetail = new TabletTab4FragmentDetail();

                                    frg.setArguments(args);
                                    frgDetail.setArguments(args);

                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);
                                    break;

                                case 6:
                                    mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
                                    mTitle.setText("Bill");
                                    mTitle.setTypeface(fontThSarabunBold);

                                    Log.e("PlanActivity4", "position_numTab:_" + position);

                                    frg = new TabletTab5();
                                    frgDetail = new TabletTab5FragmentDetail2();

                                    frg.setArguments(args);
                                    frgDetail.setArguments(args);

                                    changePageFragment(0, frg);
                                    changePageFragment(1, frgDetail);
                                    break;
                            }
                        }
                        positionBefore = positionSt;
                    }


                    ////                    // add to see
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                });
        // *** *** End


        tvBtn02.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(TabletCustomViewIconTextTabsActivity.this, PlanActivity4Map.class);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                mFragmentManager = getSupportFragmentManager();
//                mFragmentTransaction = mFragmentManager.beginTransaction();
//                mFragmentTransaction.remove(new Tab1FragmentTakePhoto());
//                mFragmentTransaction.remove(new Tab2FragmentALL3Edit());
//                mFragmentTransaction.remove(new Tab3Fragment());
//                mFragmentTransaction.remove(new Tab4Fragment());
//                mFragmentTransaction.remove(new Tab5Fragment());
//                mFragmentTransaction.commit();
//                startActivity(i);
                Intent i = new Intent(TabletCustomViewIconTextTabsActivity.this, HomeTabletMainActivity.class);
                startActivity(i);
            }
        });
    }

    void setDatabase(){
        orderDetailDB = new OrderDetailDatabaseHelper(this);
        markerDB = new MarketDatabaseHelper(this);
        merchandDB = new MerchandisingDatabaseHelper(this);
        invoiceDB = new InvoiceDatabaseHelper(this);

        lsOrderDetail = orderDetailDB.getListOrderDetailListBySearchId(newIdSV);
        lsMarketDetail = markerDB.getListMarketSearchByIdSV(newIdSV);
        lsMerchandisingDetail = merchandDB.getListMerchandisingListBySearchId(newIdSV);
        lsInvoiceDetail = invoiceDB.getListInvoiceListBySearchId(newId);
    }

    void setValueFromDatabase(){
        tvValue01.setText(String.valueOf(lsOrderDetail.size()));
        tvValue04.setText(String.valueOf(lsMarketDetail.size()));
        tvValue04_2.setText(String.valueOf(lsMerchandisingDetail.size()));
        tvValue05.setText(String.valueOf(lsInvoiceDetail.size()));
    }

    /**
     * Adding custom view to tab
     */

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.icon1_white,
                R.drawable.icon2_white,
                R.drawable.icon3_white,
                R.drawable.icon4_white,
                R.drawable.icon5_white,
                R.drawable.icon6_white,
                R.drawable.icon7_white//neutral
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new Tab3Fragment(), "VISIT"); //OneFragment()
        adapter.addFrag(new Tab3Fragment(), "PRODUCTS"); // TwoFragment()
        adapter.addFrag(new Tab3Fragment(), "COMPETITOR"); //ThreeFragment()
        adapter.addFrag(new Tab3Fragment(), "MERCHANDISING"); //FourFragment()
        adapter.addFrag(new Tab3Fragment(), "BILL"); //FiveFragment()

        adapter.addFrag(new Tab3Fragment(), "MERCHANDISING"); //FourFragment()
        adapter.addFrag(new Tab3Fragment(), "BILL"); //FiveFragment()


        viewPager.setAdapter(adapter);
    }



    @Override
    public void onBackStackChanged() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1){
            fragmentManager.popBackStackImmediate();
            fragmentManager.beginTransaction().commit();
        } else {
            //handle finish
            finish(); // Closes app
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.viewpager, new Tab3Fragment());
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void MessageTab1(Fragment frg) {

        changePageFragment(1, frg);
    }

    @Override
    public void MessageTab2(String OS_Name, Fragment frgDetail) {

        // set Fragment Start

                changePageFragment(1, frgDetail);

    }

    @Override
    public void MessageTab3(String OS_Name, Fragment frg) {
        changePageFragment(1, frg);
    }

    @Override
    public void MessageTab4(String OS_Name, Fragment frg) {
        changePageFragment(1, frg);
    }

    @Override
    public void MessageTab5(String OS_Name, Fragment frg) {
        changePageFragment(1, frg);
    }

    @Override
    public void MessageTab(int tabSide, Fragment frg) {
        changePageFragment(tabSide, frg);
    }

    @Override
    public void MessageOrderDelivery(String OS_Name) {

        Fragment frgDetail;
        frgDetail = new TabletTab2setOrderDeliveryDetailActivity2();
        changePageFragment(0, frgDetail);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            Log.e("position", "Done_position: position" + position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
            return null;
        }
    }

    public void changePageFragment(int whatSide, Fragment frg){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if(whatSide == 0) {
            frameLayout.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.containerView1, frg);// frgMaster
            fragmentTransaction.commit();

        }else if (whatSide == 1){
            frameLayout.setVisibility(View.VISIBLE);
            fragmentTransaction.replace(R.id.containerView2, frg); //frgDetail
            fragmentTransaction.commit();
        }else if (whatSide == 3){
            frameLayout.setVisibility(View.GONE);
            fragmentTransaction.replace(R.id.containerView1, frg); //frgDetail
            fragmentTransaction.commit();
        }
    }
}
