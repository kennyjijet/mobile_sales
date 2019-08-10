package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBInvoice.Invoice;
import com.salesforce.android.restsample.DB.Model.DBInvoice.InvoiceDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.fragmentTabBill.Tab5FragmentDetail2;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3.TabletTab3FragmentDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab5 extends Fragment {

    private Communicator communicator;

    String newId;
    int newIdSV;

    private Toolbar toolbar;
    private TextView mTitle;

    TextView tt011, tt012Value;
    TextView tt021, tt022Value;
    TextView tt031, tt032Value;

    InvoiceDatabaseHelper invDBHp;
    List<Invoice> invoiceList = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_5bill_to_edit, container, false);



        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");

//        newId= "00128000007uGpMAAU";
//        newIdSV = 2;

        Log.e("Tab2FragmentALL3", "Tab2FragmentALL3: Id: " + newId);

        invDBHp = new InvoiceDatabaseHelper(getActivity());

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        tt011 = (TextView) v.findViewById(R.id.tvTotaldue);
        tt012Value = (TextView) v.findViewById(R.id.tvTotaldueValue);

        tt021 = (TextView) v.findViewById(R.id.tvCollected);
        tt022Value = (TextView) v.findViewById(R.id.tvCollectedValue);

        tt031 = (TextView) v.findViewById(R.id.tvBalance);
        tt032Value = (TextView) v.findViewById(R.id.tvBalanceValue);

        tt011.setTypeface(fontThSarabun);
        tt012Value.setTypeface(fontThSarabun);
        tt021.setTypeface(fontThSarabun);
        tt022Value.setTypeface(fontThSarabun);
        tt031.setTypeface(fontThSarabun);
        tt032Value.setTypeface(fontThSarabun);

        TextView tv00 = (TextView) v.findViewById(R.id.tvpromotion);
        tv00.setTypeface(fontThSarabun);
        tv00.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
                //Intent i = new Intent(getActivity(), Tab5FragmentDetail.class);
//                Intent i = new Intent(getActivity(), Tab5FragmentDetail2.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
                Fragment frg = new TabletTab5FragmentDetail2();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab5("linVistCont", frg);
            }
        });

        setDatabase();
        setValue();

        return v;
    }

    void setDatabase(){
        invoiceList = invDBHp.getListInvoiceListBySearchId(newId);
    }

    void setValue(){

        if(invoiceList.size()!=0){

            tt012Value.setText(String.valueOf(invoiceList.get(0).getTotal()));
            tt022Value.setText("0");
            tt032Value.setText(String.valueOf(invoiceList.get(0).getTotal()));
        }
    }

    public interface Communicator {
        public void MessageTab5(String OS_Name, Fragment frg);
    }
}

