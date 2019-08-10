package com.salesforce.android.restsample.Tablet.Detail.TabletCustomer;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.R;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by pannikar on 7/20/16 AD.
 */
public class TabletCustomer1Addnew extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;
    private TextView tvContacts;
    private TextView tvBillTo;
    private TextView tvShipTo;
    //    private TextView tvcreatenewdev;

    AccountDatabaseHelper mDBHelper;
    AddressDatabaseHelper mDBHelper2;
    List<Address> getAddress;
    List<Account> getAccount;

    //TextView tv1;
    EditText tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    TextView tv10Contacts, tv11Billto, tv12Shipto;
    TextView salesHistory;

    String tvSt1, tvSt2, tvSt3, tvSt4, tvSt5, tvSt6, tvSt7, tvSt8, tvSt9;
    String tvSt10Contacts, tvSt11Billto, tvSt12Shipto;

    JSONObject obj;

    String responseServer, responseHttp;

    String accTestArray[] = new String[500];
    private TextView toolbar_back;
    private TextView tv0Done;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_customer_new_add, container, false);
        // Initialize Views
        setLayout(view);

        tvContacts.setOnClickListener(this);
        tvBillTo.setOnClickListener(this);
        tvShipTo.setOnClickListener(this);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_back = (TextView) v.findViewById(R.id.toolbar_back);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(fontThSarabunBold);
        toolbar_back.setTypeface(fontThSarabunBold);
        toolbar_title.setText("New Customer");

        tvContacts = (TextView) v.findViewById(R.id.tvContacts);
        tvBillTo = (TextView) v.findViewById(R.id.tvBillTo);
        tvShipTo = (TextView) v.findViewById(R.id.tvShipTo);

        tvContacts.setTypeface(fontThSarabun);
        tvBillTo.setTypeface(fontThSarabun);
        tvShipTo.setTypeface(fontThSarabun);

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);
        TextView tt08 = (TextView) v.findViewById(R.id.title08);
        TextView tt09 = (TextView) v.findViewById(R.id.title09);
        //salesHistory
        salesHistory = (TextView) v.findViewById(R.id.salesHistory);

        tv0Done = (TextView) v.findViewById(R.id.toolbar_done);
        tv1 = (EditText) v.findViewById(R.id.edNumber);
        tv2 = (EditText) v.findViewById(R.id.edName);
        tv3 = (EditText) v.findViewById(R.id.edChannel);
        tv4 = (EditText) v.findViewById(R.id.edPhone1);
        tv5 = (EditText) v.findViewById(R.id.edPhone2);
        tv6 = (EditText) v.findViewById(R.id.edMobile);
        tv7 = (EditText) v.findViewById(R.id.edFax);
        tv8 = (EditText) v.findViewById(R.id.edEmail);
        tv9 = (EditText) v.findViewById(R.id.edWebsite);
        tv10Contacts = (TextView) v.findViewById(R.id.tvContacts);
        tv11Billto = (TextView) v.findViewById(R.id.tvBillTo);
        tv12Shipto = (TextView) v.findViewById(R.id.tvShipTo);

        // for this Activity

        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07.setTypeface(fontThSarabunBold);
        tt08.setTypeface(fontThSarabunBold);
        tt09.setTypeface(fontThSarabunBold);


        tv0Done.setTypeface(fontThSarabunBold);
        tv1.setTypeface(fontThSarabun);
        tv2.setTypeface(fontThSarabun);
        tv3.setTypeface(fontThSarabun);
        tv4.setTypeface(fontThSarabun);
        tv5.setTypeface(fontThSarabun);
        tv6.setTypeface(fontThSarabun);
        tv7.setTypeface(fontThSarabun);
        tv8.setTypeface(fontThSarabun);
        tv9.setTypeface(fontThSarabun);
        tv10Contacts.setTypeface(fontThSarabun);
        tv11Billto.setTypeface(fontThSarabun);
        tv12Shipto.setTypeface(fontThSarabun);
        salesHistory.setTypeface(fontThSarabun);

        tv1.setHint(" null ");
        tv2.setHint(" null ");
        tv3.setHint(" null ");
        tv4.setHint(" null ");
        tv5.setHint(" null ");
        tv6.setHint(" null ");
        tv7.setHint(" null ");
        tv8.setHint(" null ");
        tv9.setHint(" null ");

        tvSt2 = tv2.getText().toString();
        tvSt3 = tv3.getText().toString();
        tvSt4 = tv4.getText().toString();
        tvSt5 = tv5.getText().toString();
        tvSt6 = tv6.getText().toString();
        tvSt7 = tv7.getText().toString();
        tvSt8 = tv8.getText().toString();
        tvSt9 = tv9.getText().toString();

    }
    public interface Communicator {
        public void MessageCustNew(String OS_Name);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tvContacts:
                updateFragment("tvContacts");
                break;
            case R.id.tvBillTo:
                updateFragment("tvBillTo");
                break;
            case R.id.tvShipTo:
                updateFragment("tvShipTo");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageCustNew(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }
}
