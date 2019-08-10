package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab4;

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

import com.salesforce.android.restsample.DB.Model.DBMerchandising.Merchandising;
import com.salesforce.android.restsample.DB.Model.DBMerchandising.MerchandisingDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.itemForPickList.EntryAdapterPickList;
import com.salesforce.android.restsample.ITEMs.itemForPickList.EntryItemPickList;
import com.salesforce.android.restsample.ITEMs.itemForPickList.ItemPicklist;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIcon.Tab4FragmentDetail;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab4Merchandising;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3.TabletTab3FragmentDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab4 extends Fragment {

    private Communicator communicator;

    String newId;
    int newIdSV;
    private ListView mListView2;

    List<Merchandising> valuesMerch;
    MerchandisingDatabaseHelper merchandisingDBHelper;
    ProductBrandDatabaseHelper productBrandDBHelper;
    ArrayList<ItemPicklist> itemsPickList = new ArrayList<ItemPicklist>();

    boolean chkListoneTime = false;

    private Toolbar toolbar;
    private TextView mTitle;

    ArrayList<String> itemsALLStIdMerch = new ArrayList<String>();
    String get2IdMerch;

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

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");

//        newId= "00128000007uGpMAAU";
//        newIdSV = 2;

        Log.e("Tab2FragmentALL3", "Tab2FragmentALL3: Id: " + newId);

        View v = inflater.inflate(R.layout.tab_4_1merchandising, container, false);
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        mListView2 = (ListView) v.findViewById(R.id.list);

        TextView tv00 = (TextView) v.findViewById(R.id.tvpromotion);
        tv00.setTypeface(fontThSarabun);
        // for this Activity
        tv00.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), Tab4FragmentDetail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
                Fragment frg = new TabletTab4FragmentDetail();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab4("linVistCont", frg);
            }
        });

        merchandisingDBHelper = new MerchandisingDatabaseHelper(getActivity());
        productBrandDBHelper = new ProductBrandDatabaseHelper(getActivity());

        setListMerchandising(newIdSV);

        return v;
    }

    void setListMerchandising(int idSV){

        valuesMerch = merchandisingDBHelper.getListMerchandisingListBySearchId(idSV);

        if(valuesMerch.size() != 0){

            if(chkListoneTime == false) { // check run for one time

                for(int i = 0; i<valuesMerch.size(); i++){
                    String idProdBrd = valuesMerch.get(i).getIdpdbr();
                    String nameProdBrd = productBrandDBHelper.getProductBrandNameBySearchId(idProdBrd);
                    itemsPickList.add(new EntryItemPickList(nameProdBrd));
//                    itemsALLStIdMarket.add(valuesMarket.get(i).getId());
                    itemsALLStIdMerch.add(valuesMerch.get(i).getId());
                }
            }

            EntryAdapterPickList adapterALL = new EntryAdapterPickList(getActivity(), itemsPickList);
            mListView2.setAdapter(adapterALL);
            mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the cursor, positioned to the corresponding row in the result set


                    int position1 = position;//-1;

                    get2IdMerch = itemsALLStIdMerch.get(position1).toString();

                    Log.e(" Check_position1", "Chk_order_to_edit:_itemsALLStIdOrder.size():_" + itemsALLStIdMerch.size());
                    Log.e(" Check_position1", "Chk_order_to_edit:_get2IdOrder:_" + get2IdMerch);
//                    here
                    Intent i = new Intent(getActivity(), Tab4Merchandising.class);
                    i.putExtra("getIdMerch", get2IdMerch);
                    i.putExtra("getId", newId);
                    i.putExtra("getIdSV", newIdSV);
                    startActivity(i);

                }
            });

        }

        chkListoneTime = true;

    }

    public interface Communicator {
        public void MessageTab4(String OS_Name, Fragment frg);
    }

}

