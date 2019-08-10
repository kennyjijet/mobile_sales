package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBPromotion.Promotion;
import com.salesforce.android.restsample.DB.Model.DBPromotion.PromotionDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotions.EntryAdapterPromotion;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotions.EntryItemPromotion;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotions.ItemPromotion;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.StartVisit.PromotionDetail.TabletTab2setPromotinDetail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 6/28/2016 AD.
 */
public class TabletTab2Promotion2 extends Fragment {

    //    final Context context = this;
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;

    TextView title;
    TextView back;
    ListView mListView;
    ArrayList<ItemPromotion> itemPromotions = new ArrayList<ItemPromotion>();
    PromotionDatabaseHelper mDBHelper;
    List<Promotion> getPromotion;
    LinearLayout lnList;

    String getPosition = "";
    String getName = "";
    String getDes = "";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tab_2fragment_promotion);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommunicatorV4) {
            communicatorV4 = (CommunicatorV4) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
            //            throw new ClassCastException(activity.toString()
            //                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_tab_2fragment_promotion, container, false);

        newIdAddr= getArguments().getString("getIdAdd");
        newId= getArguments().getString("getId");
        newNumber= getArguments().getString("getNumber");
        newName= getArguments().getString("getName");
        newTime= getArguments().getString("getTime");
        newIdSV = getArguments().getInt("getIdSV");

        // for this Activity
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        TextView toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
//        TextView tvBack = (TextView) v.findViewById(R.id.tvgoBack);

//        toolbar_title.setTypeface(fontThSarabun);

        lnList = (LinearLayout) v.findViewById(R.id.lnList);

        mDBHelper = new PromotionDatabaseHelper(getActivity());

        File database = getActivity().getApplicationContext().getDatabasePath(PromotionDatabaseHelper.DBNAME);
        if(false == database.exists()){

            Toast.makeText(getActivity(), "Don't have Database", Toast.LENGTH_SHORT).show();

            String str = database.getAbsolutePath();
            Log.e("File database: ", "File database: " + str);

            if(false == database.exists()){
                mDBHelper.getReadableDatabase();
                //visitTarDBHelper.getReadableDatabase();
                if(copyDatabase(getActivity())){
                    Toast.makeText(getActivity(), "YES!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Copy data error", Toast.LENGTH_SHORT).show();
//                    return;
                }
            }
        }

        // Check Database exist or not start

        mDBHelper = new PromotionDatabaseHelper(getActivity());
        getPromotion = new ArrayList<>();
        getPromotion = mDBHelper.getListPromotionList();

        mListView = (ListView) v.findViewById(R.id.list_item_promotion);
        mListView.setTextFilterEnabled(true);

        mDBHelper = new PromotionDatabaseHelper(getActivity());
        mDBHelper.openDatabase();

        for (int i=0;i<getPromotion.size();i++){
            String string = "";
            String str1 = "";
            String str2 = "";
            string = getPromotion.get(i).getName();
            StringBuilder sb = new StringBuilder(string);
            int count=0;
            int state = 0;
            for (int j=0;j<string.length();j++){
                if (j>22 && string.charAt(j) == ' ' && state == 0){
                    Log.e("test","55555555");
//                    sb.replace(j+1,j+2,"\n");
                    str1 = string.substring(0,j);
                    str2 = string.substring(j+1);

                    count++;
                    state = 1;
                }
            }
            if (count == 0){
                str1 = string;
            }
            itemPromotions.add(new EntryItemPromotion(str1, str2,
                    getPromotion.get(i).getNumber(),
                    getPromotion.get(i).getEndDate()));
        }

        EntryAdapterPromotion entry = new EntryAdapterPromotion(getActivity(), itemPromotions);
        mListView.setAdapter(entry);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
//                Cursor cursor = (Cursor) mListView.getItemAtPosition(position);

//                String get0Id = cursor.getString(cursor.getColumnIndexOrThrow("prom_id"));
//                String get1name = cursor.getString(cursor.getColumnIndexOrThrow("prom_name"));
//                String get2des = cursor.getString(cursor.getColumnIndexOrThrow("prom_description"));
                if (!(getPromotion.get(position).getId() == null)) {
                    getPosition = getPromotion.get(position).getId();
                }
                if (!(getPromotion.get(position).getName() == null)) {
                    getName = getPromotion.get(position).getName();
                }
                if (!(getPromotion.get(position).getDescription() == null)) {
                    getDes = getPromotion.get(position).getDescription();
                }
//                Intent i = new Intent(TabletTab2Promotion2.this, Poa_PromotionDetail.class);
//                i.putExtra("getPromId", getPosition);
//                i.putExtra("getPromName", getName);
//                i.putExtra("getPromDes", getDes);
//
//                i.putExtra("getIdAdd",newIdAddr);
//                i.putExtra("getId",newId);
//                i.putExtra("getNumber",newNumber);
//                i.putExtra("getName",newName);
//                i.putExtra("getTime",newTime);
//                i.putExtra("getIdSV",newIdSV);
//                startActivity(i);

                /*
                *  Try not finish
                *
                * */

//                 Fragment frg = new TabletTab2setPlanDetailPromotionActivity();
                Fragment frg = new TabletTab2setPromotinDetail();
                Bundle args = new Bundle();
                args.putString("getPromId", getPosition);
                args.putString("getPromName", getName);
                args.putString("getPromDes", getDes);

                args.putString("getIdAdd",newIdAddr);
                args.putString("getId",newId);
                args.putString("getNumber",newNumber);
                args.putString("getName",newName);
                args.putString("getTime",newTime);
                args.putInt("getIdSV",newIdSV);
                frg.setArguments(args);
                communicatorV4.MessageTab(1, frg);
            }
        });

        return v;
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(PromotionDatabaseHelper.DBNAME);
            String outFileName = PromotionDatabaseHelper.DBLOCATION + PromotionDatabaseHelper.DBNAME;
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

