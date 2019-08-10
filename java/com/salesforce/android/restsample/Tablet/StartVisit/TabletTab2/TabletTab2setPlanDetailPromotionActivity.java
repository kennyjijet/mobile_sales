package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteria.PromotionCriteria;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteria.PromotionCriteriaDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaBenefit.PromotionCriteriaBenefit;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaBenefit.PromotionCriteriaBenefitDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaDetail.PromotionCriteriaDetail;
import com.salesforce.android.restsample.DB.Model.DBPromotionCriteriaDetail.PromotionCriteriaDetailDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionDetail.EntryAdapterPromotionDetail;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionDetail.EntryItemPromotionDetail;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionDetail.ItemPromotionDetail;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionDetail.SectionItemPromotionDetail;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setPlanDetailPromotionActivity extends Fragment {
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set Detail
    String newDescription;
    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;

    TextView back;
    TextView title;
    TextView promName;
    TextView promDes;
    TextView tvTier;
    EditText tierNum;

    String id;
    String name;
    String description;

    ListView mListview;

    PromotionCriteriaDatabaseHelper mDBHelper;
    PromotionCriteriaDetailDatabaseHelper mDBHelper2;
    PromotionCriteriaBenefitDatabaseHelper mDBHelper3;
    ProductDatabaseHelper mDBHelper4;

    List<PromotionCriteria> getListPromCri;
    List<PromotionCriteriaDetail> getListCriDet;
    List<PromotionCriteriaBenefit> getListCriBenefit;
    List<Product> getListProduct;
    ArrayList<ItemPromotionDetail> itemPromotionDetails;

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

        View view = inflater.inflate(R.layout.detail_poa_promotion_detail, container, false);

//        Bundle extras = getIntent().getExtras();
        id = getArguments().getString("getPromId");
        name = getArguments().getString("getPromName");
        description = getArguments().getString("getPromDes");

        newId = getArguments().getString("getId");
        newName = getArguments().getString("getName");
        newIdAddr = getArguments().getString("getIdAdd",newIdAddr);
        newIdSV = getArguments().getInt("getIdSV");
        newDescription = getArguments().getString("getDescription");



//        id = "PRO-0000003";
//        name = "Promotion Group Tensoplastic Buy 10 Get 2 Tensoplast 100 Piece Free";
//        description = "";
//
//        newId = "00128000007uGpMAAU";
//        newName = "Tensoplast 10 Piece";
//        newIdSV = 2;
//        newDescription = "";

        // Initialize Views
        setLayout(view);


        // set Detail
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment frg = new TabletTab2Promotion();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putString("getIdAdd", newIdAddr);
                args.putString("getName", newName);
                args.putString("getTime", newTime);
                args.putString("getNumber", newNumber);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicatorV4.MessageTab(1, frg);
            }
        });

        mDBHelper = new PromotionCriteriaDatabaseHelper(getActivity());
        mDBHelper2 = new PromotionCriteriaDetailDatabaseHelper(getActivity());
        mDBHelper3 = new PromotionCriteriaBenefitDatabaseHelper(getActivity());
        mDBHelper4 = new ProductDatabaseHelper(getActivity());
        itemPromotionDetails = new ArrayList<ItemPromotionDetail>();

        // Check Database exist or not start
        File database = getActivity().getApplicationContext().getDatabasePath(PromotionCriteriaDatabaseHelper.DBNAME);
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
                    return null;
                }
            }
        }

        getListPromCri = new ArrayList<PromotionCriteria>();
        getListCriDet = new ArrayList<PromotionCriteriaDetail>();
        getListCriBenefit = new ArrayList<PromotionCriteriaBenefit>();
        getListProduct = new ArrayList<Product>();

        getListPromCri = mDBHelper.getListPromotionCriteria();
        getListCriDet = mDBHelper2.getListPromotionCriteriaDetail();
        getListCriBenefit = mDBHelper3.getListPromotionCriteriaBenefit();
        getListProduct = mDBHelper4.getListProduct();

        mListview = (ListView) view.findViewById(R.id.list_item_promotion_detail);
        mListview.setTextFilterEnabled(true);

        int tierCount = 1;
        String str1 = "";
        String str2 = "";

        int count = 0;
        int state = 0;
        for (int i=0;i<name.length();i++){
            if (i>22 && name.charAt(i) == ' ' && state == 0){
                Log.e("test","55555555");
                str1 = name.substring(0,i);
                str2 = name.substring(i+1);

                count++;
                state = 1;
            }
        }
        if (count == 0){
            str1 = name;
        }

        for (int i=0;i<getListPromCri.size();i++){
            if (getListPromCri.get(i).getPromId().equals(id)){
                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Criteria"));
                tierCount++;

                Log.e("CriPromId",getListPromCri.get(i).getPromId());
                Log.e("CriId",""+getListPromCri.get(i).getId());
                for (int j=0;j<getListCriDet.size();j++){
                    Log.e("cridet",getListCriDet.get(j).getIdPrcr());
                    if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                        for (int k=0;k<getListProduct.size();k++){
                            if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())){
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1,str2,
                                        getListCriDet.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriDet.get(j).getUom(), getListCriDet.get(j).getAmountQty(),0));
                            }
                        }
                    }
                }

                itemPromotionDetails.add(new SectionItemPromotionDetail("Benefit"));
                for (int j=0;j<getListCriBenefit.size();j++){
                    if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())){
                        for (int k=0;k<getListProduct.size();k++){
                            if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())){
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1,str2,
                                        getListCriBenefit.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriBenefit.get(j).getUom(), getListCriBenefit.get(j).getAmountQty(),0));
                            }
                        }
                    }
                }
            }
        }

        EntryAdapterPromotionDetail entry = new EntryAdapterPromotionDetail(getActivity(), itemPromotionDetails);
        mListview.setAdapter(entry);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);

        // set Detail
        back = (TextView) v.findViewById(R.id.tvgoBack);
        title = (TextView) v.findViewById(R.id.toolbar_title);
        promName = (TextView) v.findViewById(R.id.prom_name);
        promDes = (TextView) v.findViewById(R.id.prom_des);
        tvTier = (TextView) v.findViewById(R.id.tvTier);
        tierNum = (EditText) v.findViewById(R.id.tierNum);

        promName.setText(name);
        promDes.setText(description);

        back.setTypeface(fontThSarabun);
        title.setTypeface(fontThSarabunBold);
        promName.setTypeface(fontThSarabunBold);
        promDes.setTypeface(fontThSarabun);
        tvTier.setTypeface(fontThSarabunBold);
        tierNum.setTypeface(fontThSarabunBold);
    }


    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(PromotionCriteriaDatabaseHelper.DBNAME);
            String outFileName = PromotionCriteriaDatabaseHelper.DBLOCATION + PromotionCriteriaDatabaseHelper.DBNAME;
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
