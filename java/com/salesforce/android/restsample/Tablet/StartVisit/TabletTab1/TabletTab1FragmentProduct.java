package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab1;

/**
 * Created by pannikar on 9/22/16 AD.
 */
//public class TabletTab1FragmentProduct {
//}

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.StartVisit.DownloadFeedback;
import com.salesforce.android.restsample.Tablet.StartVisit.Feedback.EntryAdapterSinglePage5Feedback;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 5/26/2016 AD.
 */
public class TabletTab1FragmentProduct extends Fragment implements View.OnClickListener {
    private CommunicatorV4 communicatorV4;
    String newId;
    int newIdSV;

    ListView mListView;

    public ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();
    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();

    EditText edSearch;

    Typeface fontThSarabun, fontThSarabunBold;

    private ProgressBar pb;
    EntryAdapterSinglePage5Feedback entry;
    DownloadFeedback dlFdBck;

    List<Integer> listNewFeedback = new ArrayList<Integer>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.tab_1products, container, false);

        itemsProduct.clear(); // *** *** *** Clear All IMPORTANT !!!

        // for this Activity
        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        edSearch = (EditText) v.findViewById(R.id.search);
        edSearch.setTypeface(fontThSarabun);
        edSearch.setFocusable(false);

        edSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edSearch.setFocusableInTouchMode(true);

                return false;
            }
        });

            pb=(ProgressBar) v.findViewById(R.id.progressBar1);
            pb.setVisibility(View.VISIBLE);

        newId= "0012800000aIvoHAAS";
        newIdSV = 2;

            arrProductList = (ArrayList<ItemSinglePage>) getArguments().getSerializable("arrProductList");
            arrProductCode= (ArrayList<ItemSinglePage>) getArguments().getSerializable("arrProductCode");
            itemsALLProduct = (ArrayList<ItemSinglePage>) getArguments().getSerializable("testITEMALL");
            listNewFeedback = (List<Integer>) getArguments().getIntegerArrayList("listNewFeedback");

            Log.e("OrderDeliveryActivity", "mainTab: item.size:_32: " + itemsALLProduct.size());
            Log.e("OrderDeliveryActivity", "mainTab: item.size:_33: " + listNewFeedback.size());

        mListView = (ListView) v.findViewById(R.id.listview_all);

        // Original
        getActivity().setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);

        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
        mTitle.setTypeface(fontThSarabunBold);

//            if(arrProductList.size() ==0 ){
//                dlFdBck = new DownloadFeedback(getActivity(), newId, newIdSV);
//                dlFdBck.setUpdateListener(new DownloadFeedback.OnUpdateListener() {
//
//                    @Override
//                    public void onUpdate(String obj,
//                                         ArrayList arrProductListNew,
//                                         ArrayList arrProductCodeNew,
//                                         ArrayList<ItemSinglePage> itemsALLProductNew) {
//                        arrProductList = arrProductListNew;
//                        arrProductCode = arrProductCodeNew;
//                        itemsALLProduct = itemsALLProductNew;
//                    }
//
//                });
//                dlFdBck.execute();
//            }

            if (listNewFeedback.size()==0 || itemsALLProduct.size()==0){
                new DownloadImageTask().execute();
            }


            return v;
    }

    @Override
    public void onClick(View v) {

    }


    // set Detail START
    public class DownloadImageTask extends AsyncTask<String, Integer, ArrayList<ItemSinglePage>> {

        @Override
        protected ArrayList<ItemSinglePage> doInBackground(String... urls) {


            try {

                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            return itemsProduct;
        }

        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);
        }

        protected void onPostExecute(ArrayList<ItemSinglePage> itemsProduct) {
//            pb.setVisibility(View.GONE);

            // try to set Adapter START

//            if(arrProductList.size() ==0 ){
            if (listNewFeedback.size()==0 || itemsALLProduct.size()==0){
                dlFdBck = new DownloadFeedback(getActivity(), newId, newIdSV);
                dlFdBck.setUpdateListener(new DownloadFeedback.OnUpdateListener() {

                    @Override
                    public void onUpdate(String obj,
                                         ArrayList arrProductListNew,
                                         ArrayList arrProductCodeNew,
                                         ArrayList<ItemSinglePage> itemsALLProductNew,
                                         List<Integer> listNewFeedbackNew) {
                        arrProductList = arrProductListNew;
                        arrProductCode = arrProductCodeNew;
                        itemsALLProduct = itemsALLProductNew;
                        listNewFeedback = listNewFeedbackNew;

                        Log.e("OrderDeliveryActivity", "mainTab: item.size:_43: " + itemsALLProduct.size());
                        Log.e("OrderDeliveryActivity", "mainTab: item.size:_44: " + listNewFeedback.size());

                        // set adapter
                        pb.setVisibility(View.GONE);
                        entry = new EntryAdapterSinglePage5Feedback(getActivity(),

                                itemsALLProduct, // chk size()

                                arrProductCode, // this
                                newIdSV,
                                listNewFeedback);

                        mListView.setAdapter(entry);


                    }

                });
                dlFdBck.execute();

            } else {

                pb.setVisibility(View.GONE);
                entry = new EntryAdapterSinglePage5Feedback(getActivity(),

                        itemsALLProduct, // chk size()

                        arrProductCode, // this
                        newIdSV,
                        listNewFeedback);
                Log.e("OrderDeliveryActivity", "mainTab: item.size:_54: " + itemsALLProduct.size());
                Log.e("OrderDeliveryActivity", "mainTab: item.size:_55: " + listNewFeedback.size());
                mListView.setAdapter(entry);
            }


            // try to set Adapter END
        }
    }
}