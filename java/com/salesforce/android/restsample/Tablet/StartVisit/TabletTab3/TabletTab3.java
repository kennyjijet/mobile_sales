package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBCompetitorBrand.CompetitorBrand;
import com.salesforce.android.restsample.DB.Model.DBCompetitorBrand.CompetitorBrandDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBMarket.Market;
import com.salesforce.android.restsample.DB.Model.DBMarket.MarketDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.itemForTabMain.EntryAdapterTabMain;
import com.salesforce.android.restsample.ITEMs.itemForTabMain.EntryItemTabMain;
import com.salesforce.android.restsample.ITEMs.itemForTabMain.ItemTabMain;
import com.salesforce.android.restsample.MainFragment.fragmentTabCompetitor.Tab3FragmentDetail;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab3MarketDetail;
import com.salesforce.android.restsample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab3 extends Fragment {

    private Communicator communicator;

    String newId;
    int newIdSV;
    private ListView mListView2;

    CompetitorBrandDatabaseHelper databaseCompBrand;
    MarketDatabaseHelper databaseMarket;
    ArrayList<ItemTabMain> itemsPickList = new ArrayList<ItemTabMain>();

    List<Market> valuesMarket;
    List<CompetitorBrand> valueCompBrd;

    boolean chkListoneTime = false;

    private Toolbar toolbar;
    private TextView mTitle;

    //ArrayList<String> itemsALLStIdMarket = new ArrayList<String>();
    ArrayList<Integer> itemsALLStIdMarket = new ArrayList<Integer>();
    //String get2IdMarket;
    int get2IdMarket;

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

        View v = inflater.inflate(R.layout.tab_4_1merchandising, container, false);

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        databaseCompBrand = new CompetitorBrandDatabaseHelper(getActivity());
        databaseMarket = new MarketDatabaseHelper(getActivity());

//        newId = getArguments().getString("getId");
//        newIdSV = getArguments().getInt("getIdSV");

        newId= "00128000007uGpMAAU";
        newIdSV = 2;

        Log.e("OrderDeliveryActivity", "Tab3Fragment:_newId: " + newId);
        Log.e("OrderDeliveryActivity", "Tab3Fragment:_newIdSV: " + newIdSV);

        mListView2 = (ListView) v.findViewById(R.id.list);

        TextView tv00 = (TextView) v.findViewById(R.id.tvpromotion);
        tv00.setTypeface(fontThSarabun);
        tv00.setText("Add new competitor...");


        // for this Activity
        tv00.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Tab3FragmentDetail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
                Fragment frg = new TabletTab3FragmentDetail();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab3("linVistCont", frg);
            }
        });

        setListMarket(newIdSV); // line 99

        return v;
    }

    void setListMarket(int idSV){


        valuesMarket = databaseMarket.getListMarketSearchByIdSV(idSV);
        String idCompBrd = "";
        String nameCompBrand ="";
        String nameProd = "";
        if(valuesMarket.size() != 0){

            if(chkListoneTime == false){ // check run for one time
                Log.e(" Check_position1", "Chk_tab3_-_chkListoneTime:_" + chkListoneTime);

                for(int i=0; i<valuesMarket.size(); i++){
                    Log.e(" Check_position1", "Chk_tab3_0_valuesMarket.size():_" + valuesMarket.size());
                    // idCompBrd = valuesMarket.get(i).getCompBrndid(); //getListCompetitorBrandSearchByIdCompBrd
                    idCompBrd = valuesMarket.get(i).getIdCompbrnd(); //getListCompetitorBrandSearchByIdCompBrd

                    Log.e(" Check_position1", "Chk_tab3_1_idCompBrd:_" + idCompBrd);
                    valueCompBrd = databaseCompBrand.getListCompetitorBrandSearchByIdCompBrd(idCompBrd);
                    Log.e(" Check_position1", "Chk_tab3_2_valueCompBrd.size():_" + valueCompBrd.size());
                    nameCompBrand = valueCompBrd.get(0).getName(); // line 120
                    Log.e(" Check_position1", "Chk_tab3_3_valueCompBrd.get(0).getName():_" + valueCompBrd.get(0).getName());
                    nameProd = valuesMarket.get(i).getProductname();
                    itemsPickList.add(new EntryItemTabMain(" "+ nameCompBrand, " "+ nameProd));
                    itemsALLStIdMarket.add(valuesMarket.get(i).getId());
                }

            } // end if

            EntryAdapterTabMain adapterALL = new EntryAdapterTabMain(getActivity(), itemsPickList);
            mListView2.setAdapter(adapterALL);
            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set

                    int position1 = position;//-1;


                    get2IdMarket = Integer.parseInt(itemsALLStIdMarket.get(position1).toString());

                    Log.e(" Check_position1", "Chk_order_to_edit:_itemsALLStIdOrder.size():_" + itemsALLStIdMarket.size());
                    Log.e(" Check_position1", "Chk_order_to_edit:_get2IdOrder:_" + get2IdMarket);
//                    here
                    Intent i = new Intent(getActivity(), Tab3MarketDetail.class);
                    i.putExtra("getIdMarket", get2IdMarket);
                    i.putExtra("getId", newId);
                    i.putExtra("getIdSV", newIdSV);
                    startActivity(i);

                }
            });
        }

        chkListoneTime = true;

    }

    public interface Communicator {
        public void MessageTab3(String OS_Name, Fragment frg);
    }
}