package com.salesforce.android.restsample.Tablet.Master;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
//import android.support.v4.app.Fragment;

//import android.support.v4.app.Fragment;
//import android.app.FragmentManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.ByAddress;
import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisitDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBTarget.TargetDatabaseHelper;
import com.salesforce.android.restsample.HBD.CalDate;
import com.salesforce.android.restsample.Library.GetDialogAlert;
//import com.salesforce.android.restsample.Library.GetDialogFragment;
import com.salesforce.android.restsample.Library.TextRoundCornerProgressBar;
import com.salesforce.android.restsample.Library.circleprogress.CircleProgressView;
import com.salesforce.android.restsample.R;

import java.io.File;
import java.util.List;

/**
 * Created by pannikar on 7/12/16 AD.
 */
public class TabletMasterFragmentHome extends Fragment implements View.OnClickListener {

    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView tvTitleTest;
    private TextView btnsync;
    private EditText edSearch;
    private ImageView btnBarcode;
    private TextView tvBD;
    private TextView tvPlan;
    private TextView tvCus;
    private TextView tvCusNum;

    private TextView tvAddr;
    private TextView tvAddrNum;

    private TextView tvHbd;
    private TextRoundCornerProgressBar progressBarShow;
    private TextView strCenter11;
    private TextView strCenter21;
    private TextView strCenter31;
    CircleProgressView mCircleView1_phone, mCircleView2_phone, mCircleView3_phone;

    public float counter = 0;
    public float total = 100; // the total number
    //    TextRoundCornerProgressBar progressBarShow;
    public float percentage = 0;
    private LinearLayout lnBD;
    private LinearLayout lnTablet;
    private TextView tvTitle1;
    private TextView tvTitle2;
    private TextView tvTitle4;
    private TextView tvTitle5;
    private LinearLayout ln;
    private LinearLayout totalallTest;
    private TextView toolbar_title;
    private int btnsyncClick = 0;
    GetDialogAlert getDia;
    private ContactDatabaseHelper dbContact;
    List<Account> mAccountList;
    List<Address> addressList;
    AccountDatabaseHelper mDBHelper;
    AddressDatabaseHelper mDBHelperAddress;
    SalesVisitDatabaseHelper datasourceSV;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.list_fragment, container, false);
        View view = inflater.inflate(R.layout.tablet_activity_home_new9, container, false);

        // Initialize Views
        setLayout(view);
        getDia = new GetDialogAlert();

//        btnsync.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // showEditDialog();
//                showDialog();
//                btnsyncClick = 1;
//                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnsync.setOnClickListener(this);



        lnBD.setOnClickListener(this);
        tvCus.setOnClickListener(this);
        tvAddr.setOnClickListener(this);
        progressBarShow.setOnClickListener(this);
        tvTitleTest.setOnClickListener(this);
        tvPlan.setOnClickListener(this);


        // set Detail HBD Start
        // Check Database exist or not start
        File database = getActivity().getDatabasePath(TargetDatabaseHelper.DBNAME);
        if(false == database.exists()){

            Toast.makeText(getActivity(), "Don't have Database", Toast.LENGTH_SHORT).show();

            Toast.makeText(getActivity(), "Click Sync", Toast.LENGTH_SHORT).show();

        } else {

            dbContact = new ContactDatabaseHelper(getActivity());
            List<Contact> lsCont = dbContact.getListContact();

            int count = 0;
            String stBD = "";
            for (int i = 0; i < lsCont.size(); i++) {
                if (lsCont.get(i).getBirthday() != null) {
                    stBD = lsCont.get(i).getBirthday();
                    if (!(stBD.equals("") || stBD.equals(null))) {
                        CalDate cd = new CalDate(stBD);
                        if (cd.getValue() >= 0) {
                            count++;
                        }
                    }
                }
            }

            tvBD.setText("" + count);
            // set Detail HBD End
            // Comment for sync first time Start

            mDBHelper = new AccountDatabaseHelper(getActivity());
            mDBHelper.openDatabase();

            mDBHelperAddress = new AddressDatabaseHelper(getActivity());
            mDBHelperAddress.openDatabase();
            addressList = mDBHelperAddress.getListAddress();

            if (addressList != null){

                tvAddrNum.setText(Integer.toString(addressList.size()));
                Log.e("test", "chk_after_sync: "+addressList.size());

            }

            datasourceSV = new SalesVisitDatabaseHelper(getActivity());

            mAccountList = mDBHelper.getListAccountList();
            tvCusNum.setText(Integer.toString(mAccountList.size()));

            // Comment for sync first time End
        }

        return view;
    }

    void setLayout(View v){

        String ss = "mm";
        TextView mm;

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView)v.findViewById(R.id.toolbar_title);
        tvTitleTest = (TextView) v.findViewById(R.id.tvTitleTest);

        ln = (LinearLayout) v.findViewById(R.id.layout_full);
        btnsync = (TextView) v.findViewById(R.id.btnsync);
        edSearch = (EditText) v.findViewById(R.id.search);
        edSearch.setFocusable(false);

        btnBarcode = (ImageView) v.findViewById(R.id.btnBarCode);
        tvBD = (TextView) v.findViewById(R.id.tvBirthday);
        tvPlan = (TextView) v.findViewById(R.id.tvplan);
        tvCus = (TextView) v.findViewById(R.id.tvcustomers);
        tvCusNum = (TextView) v.findViewById(R.id.tvcustomersNumber);

        tvAddr = (TextView) v.findViewById(R.id.tvbyAddress);
        tvAddrNum = (TextView) v.findViewById(R.id.tvbyAddressNumber);

        tvHbd = (TextView) v.findViewById(R.id.tvhbd);

        tvTitle1 = (TextView) v.findViewById(R.id.tvTitle1); // tvTitle1 // tv1
        tvTitle2 = (TextView) v.findViewById(R.id.tvTitle2);
        tvTitle4 = (TextView) v.findViewById(R.id.tvTitle4);
        tvTitle5 = (TextView) v.findViewById(R.id.tvTitle5);

        toolbar_title.setTypeface(fontThSarabunBold);
        tvTitle1.setTypeface(fontThSarabunBold);
        tvTitle2.setTypeface(fontThSarabun);
        tvTitle4.setTypeface(fontThSarabunBold);
        tvTitle5.setTypeface(fontThSarabun);

        tvTitleTest.setTypeface(fontThSarabun);
        tvBD.setTypeface(fontThSarabun);
        btnsync.setTypeface(fontThSarabunBold);
        edSearch.setTypeface(fontThSarabun);
        tvCusNum.setTypeface(fontThSarabun);
        tvAddrNum.setTypeface(fontThSarabun);
        tvPlan.setTypeface(fontThSarabunBold);
        tvCus.setTypeface(fontThSarabunBold);
        tvAddr.setTypeface(fontThSarabunBold);
        tvHbd.setTypeface(fontThSarabunBold);

        totalallTest = (LinearLayout)v.findViewById(R.id.totalallTest);

        progressBarShow = (TextRoundCornerProgressBar) v.findViewById(R.id.progressBarShow);

//        lnTablet =(LinearLayout)v.findViewById(R.id.Lnview2);
//        lnTablet.setVisibility(View.GONE);
        lnBD = (LinearLayout) v.findViewById(R.id.layBirthday);

        strCenter11 = (TextView) v.findViewById(R.id.strCenter1);
        strCenter21 = (TextView) v.findViewById(R.id.strCenter2);
        strCenter31 = (TextView) v.findViewById(R.id.strCenter3);
        strCenter11.setTypeface(fontThSarabun);
        strCenter21.setTypeface(fontThSarabun);
        strCenter31.setTypeface(fontThSarabun);

        mCircleView1_phone = (CircleProgressView) v.findViewById(R.id.circleView1);
        mCircleView1_phone.multiplyText = 5.5f;
        mCircleView1_phone.mCurrentText = "Bill-To";

        mCircleView2_phone = (CircleProgressView) v.findViewById(R.id.circleView2);
        mCircleView2_phone.multiplyText = 5.5f;
        mCircleView2_phone.mCurrentText = "Ship-T0";

        mCircleView3_phone = (CircleProgressView) v.findViewById(R.id.circleView3);
        mCircleView3_phone.multiplyText = 5.5f;
        mCircleView3_phone.mCurrentText = "Total";

        mCircleView1_phone.setValueAnimated(0, 30, 3000);
        mCircleView2_phone.setValueAnimated(0, 30, 3000);
        mCircleView3_phone.setValueAnimated(0, 30, 3000);

        mCircleView1_phone.setTextTypeface(fontThSarabun);
        mCircleView2_phone.setTextTypeface(fontThSarabun);
        mCircleView3_phone.setTextTypeface(fontThSarabun);

        setColorCircleView(mCircleView1_phone, 7, 100);
        setColorCircleView(mCircleView2_phone, 5, 100);
        setColorCircleView(mCircleView3_phone, 12, 100);

        mCircleView1_phone.setValueAnimated(0, 7, 1800);
        mCircleView2_phone.setValueAnimated(0, 5, 1800);
        mCircleView3_phone.setValueAnimated(0, 12, 1800);

        progressBarShow.getLayoutParams().height = (int) getResources().getDimension(R.dimen.progressBarHeight_phone);

        strCenter11.setText("5" + "/100" + " [5%]");
        strCenter21.setText("7" + "/100" + " [7%]");
        strCenter31.setText("12" + "/100" + " [6%]");

        progressBarShow.max = 200;
        total = 100;
        percentage = 0;
        setColorTextRoundCornerProgressBar(progressBarShow, total, progressBarShow.max);
        progressBarShow.textProgressTotal = "$"+ String.valueOf(Math.round(progressBarShow.max));
        new Thread(new Runnable() {
            public void run() {
                while (counter < total) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarShow.post(new Runnable() {
                        public void run() {
                            //t.setText("" + counter);
                            progressBarShow.setProgress(counter);
                            progressBarShow.setProgressText("$" + String.valueOf(counter));
                        }
                    });
                    counter++;
                    Log.e("jsonarray", "jsonObject:_Entry_Main_counter0:_" + counter);
                }
            }
        }).start();
    }

    void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        String inputText = "test to call GetDialogAlert";

        GetDialogAlert newFragment = GetDialogAlert.newInstance(inputText,"home");
        newFragment.show(ft, "dialog");

    }

    public interface Communicator {
        public void Message(String OS_Name);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnsync:
                updateFragment("btnsync");
                break;

            case R.id.layBirthday:
                updateFragment("HappyBirthday");
                break;

            case R.id.tvcustomers:
                updateFragment("Customer");
                break;

            case R.id.tvbyAddress:
                updateFragment("ByAddress");
                break;

            case R.id.tvTitleTest: //totalallTest
                updateFragment("tvTitleTest");
                break;

            case R.id.tvplan:
                updateFragment("Plan");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.Message(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }

    public void setColorCircleView (CircleProgressView cir, float base, float max)
    {
        float percentage = (base/max) * 100;
        if(percentage <= 30)
        {
            cir.setBarColor(getResources().getColor(R.color.colorCircularRed));
        }else if(percentage >= 80){
            cir.setBarColor(getResources().getColor(R.color.colorCircularGreen));
        }else{
            cir.setBarColor(getResources().getColor(R.color.colorCircularYellow));
        }
    }

    public void setColorTextRoundCornerProgressBar (TextRoundCornerProgressBar progressBarText, float base, float max)
    {
        float percentage = (base/max) * 100;
        if(percentage <= 30)
        {
            progressBarText.gradientColor1 = getResources().getColor(R.color.colorGradientRed1);
            progressBarText.gradientColor2 = getResources().getColor(R.color.colorGradientRed2);
            progressBarText.gradientColor3 = getResources().getColor(R.color.colorGradientRed3);
        }else if(percentage >= 80){
            progressBarText.gradientColor1 = getResources().getColor(R.color.colorGradientGreen1);
            progressBarText.gradientColor2 = getResources().getColor(R.color.colorGradientGreen2);
            progressBarText.gradientColor3 = getResources().getColor(R.color.colorGradientGreen3);
        }else{
            progressBarText.gradientColor1 = getResources().getColor(R.color.colorGradientYellow1);
            progressBarText.gradientColor2 = getResources().getColor(R.color.colorGradientYellow2);
            progressBarText.gradientColor3 = getResources().getColor(R.color.colorGradientYellow3);
        }
    }
}
