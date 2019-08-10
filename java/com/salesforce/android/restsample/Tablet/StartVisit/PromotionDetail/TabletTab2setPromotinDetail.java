package com.salesforce.android.restsample.Tablet.StartVisit.PromotionDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBOrderPromotionTemporary.OrderPromotionTemporary;
import com.salesforce.android.restsample.DB.Model.DBOrderPromotionTemporary.OrderPromotionTemporaryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBPromotion.Promotion;
import com.salesforce.android.restsample.DB.Model.DBPromotion.PromotionDatabaseHelper;
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
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType2.EntryAdapterPromotionType2;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType2.EntryItemPromotionType2;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType2.ItemPromotionType2;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType3.EntryAdapterPromotionType3;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType3.EntryItemBenType3;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType3.EntryItemPromotionType3;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType3.ItemPromotionType3;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.ItemPromotionType3.SectionItemPromotionType3;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIconDetail.Tab2Promotion;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pannikar on 10/3/16 AD.
 */
public class TabletTab2setPromotinDetail extends Fragment {

    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;


    View v;

    String newName;
    String newIdAddr;
    String newId;
    String newNumber;
    String newTime;
    int newIdSV;

    TextView back;
    TextView title;
    TextView tvDone;
    TextView promName;
    TextView promDes;
    TextView ttAmount;
    TextView tvAmount;
    TextView tvTier;
    EditText orderNum;
    TextView ttCriteria;
    TextView tvprodNameCri;
    TextView tvprodCodeCri;
    TextView tvpriceCri;
    TextView tvTotalCri;
    TextView tvuomCri;
    TextView ttBenefit;
    TextView tvprodNameBen;
    TextView tvprodCodeBen;
    TextView tvpriceBen;
    TextView tvTotalBen;
    TextView tvuomBen;
    TextView ttTotalIn;
    TextView ttTier;
    TextView ttCri;
    TextView ttBen;
    TextView ttCount;
    TextView tvCount;

    String id;
    String name;
    String description;

    ListView mListview;

    PromotionCriteriaDatabaseHelper mDBHelper;
    PromotionCriteriaDetailDatabaseHelper mDBHelper2;
    PromotionCriteriaBenefitDatabaseHelper mDBHelper3;
    ProductDatabaseHelper mDBHelper4;
    PromotionDatabaseHelper mDBPromotion;
    OrderPromotionTemporaryDatabaseHelper mDBHelper5;

    List<PromotionCriteria> getListPromCri;
    List<PromotionCriteriaDetail> getListCriDet;
    List<PromotionCriteriaBenefit> getListCriBenefit;
    List<Product> getListProduct;
    List<Promotion> getListPromotion;
    List<OrderPromotionTemporary> getListOrderProm;

    ArrayList<ItemPromotionDetail> itemPromotionDetails;
    ArrayList<ItemPromotionType2> itemPromotionType2s;
    ArrayList<ItemPromotionType3> itemPromotionType3s;
    ArrayList<String> prodCodeCri;
    ArrayList<Double> amountQtyCri;
    ArrayList<Double> priceCri;
    int promType;
    ArrayList<String> prodCodeBen;
    ArrayList<Double> amountQtyBen;
    String prodCodeCriType2;
    String prodCodeBenType2;
    Map<Double, String> qtyCriType2;
    double countType3 = 0;

    EntryAdapterPromotionDetail entry;

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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        id = getArguments().getString("getPromId");
        name = getArguments().getString("getPromName");
        description = getArguments().getString("getPromDes");

        newId = getArguments().getString("getId");
        newName = getArguments().getString("getName");
        newIdAddr = getArguments().getString("getIdAdd",newIdAddr);
        newIdSV = getArguments().getInt("getIdSV");

        Log.e("id", id);
        Log.e("name", name);
        Log.e("des", description);

        getListPromCri = new ArrayList<PromotionCriteria>();
        getListCriDet = new ArrayList<PromotionCriteriaDetail>();
        getListCriBenefit = new ArrayList<PromotionCriteriaBenefit>();
        getListProduct = new ArrayList<Product>();
        getListPromotion = new ArrayList<Promotion>();
        getListOrderProm = new ArrayList<OrderPromotionTemporary>();

        mDBHelper = new PromotionCriteriaDatabaseHelper(getActivity());
        mDBHelper2 = new PromotionCriteriaDetailDatabaseHelper(getActivity());
        mDBHelper3 = new PromotionCriteriaBenefitDatabaseHelper(getActivity());
        mDBHelper4 = new ProductDatabaseHelper(getActivity());
        mDBPromotion = new PromotionDatabaseHelper(getActivity());
        mDBHelper5 = new OrderPromotionTemporaryDatabaseHelper(getActivity());
        itemPromotionDetails = new ArrayList<ItemPromotionDetail>();
        itemPromotionType2s = new ArrayList<ItemPromotionType2>();
        itemPromotionType3s = new ArrayList<ItemPromotionType3>();
        prodCodeCri = new ArrayList<>();
        amountQtyCri = new ArrayList<>();
        priceCri = new ArrayList<>();
        prodCodeBen = new ArrayList<>();
        amountQtyBen = new ArrayList<>();
        qtyCriType2 = new HashMap<>();

        getListPromCri = mDBHelper.getListPromotionCriteria();
        getListCriDet = mDBHelper2.getListPromotionCriteriaDetail();
        getListCriBenefit = mDBHelper3.getListPromotionCriteriaBenefit();
        getListProduct = mDBHelper4.getListProduct();
        getListPromotion = mDBPromotion.getListPromotionListBySearchId(id);
        getListOrderProm = mDBHelper5.getListOrderPromotionTemporaryList();

        if (getListPromotion.get(0).getType() == 2) {
            for (int i = 0; i < getListPromCri.size(); i++) {
                if (getListPromCri.get(i).getPromId().equals(id)) {
                    for (int j = 0; j < getListCriDet.size(); j++) {
                        Log.e("cridet", getListCriDet.get(j).getIdPrcr());
                        if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                            for (int k = 0; k < getListProduct.size(); k++) {
                                if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                    prodCodeCriType2 = getListCriDet.get(j).getProdCode();
                                    qtyCriType2.put(getListCriDet.get(j).getAmountQty(), getListCriDet.get(j).getIdPrcr());
                                }
                            }
                        }
                    }
                    for (int j = 0; j < getListCriBenefit.size(); j++) {
                        if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                            for (int k = 0; k < getListProduct.size(); k++) {
                                if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                    prodCodeBenType2 = getListCriBenefit.get(j).getProdCode();
                                }
                            }
                        }
                    }
                }
            }
            int tierCount = 1;
            for (Map.Entry entry : qtyCriType2.entrySet()) {
                itemPromotionType2s.add(new EntryItemPromotionType2(tierCount,
                        Double.parseDouble(entry.getKey().toString()),
                        mDBHelper3.getFOCBySearchIdPrcr(entry.getValue().toString())));
                tierCount++;
            }
        }else if (getListPromotion.get(0).getType() == 3){
            for (int i = 0; i < getListPromCri.size(); i++) {
                if (getListPromCri.get(i).getPromId().equals(id)) {
                    itemPromotionType3s.add(new SectionItemPromotionType3("Promotion Criteria"));
                    countType3 = getListPromCri.get(i).getAmountqty();
                    for (int j = 0; j < getListCriDet.size(); j++) {
                        Log.e("cridet", getListCriDet.get(j).getIdPrcr());
                        if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                            for (int k = 0; k < getListProduct.size(); k++) {
                                if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                    prodCodeCri.add(getListCriDet.get(j).getProdCode());
                                    amountQtyCri.add(getListCriDet.get(j).getAmountQty());
                                    priceCri.add(getListProduct.get(k).getUnitprice());
                                    String string = mDBHelper4.getNameBySearchId(getListCriDet.get(j).getProdCode());
                                    String str1 = "";
                                    String str2 = "";

                                    int count = 0;
                                    int state = 0;
                                    for (int m=0;m<string.length();m++){
                                        if (m>22 && string.charAt(m) == ' ' && state == 0){
                                            str1 = string.substring(0,m);
                                            str2 = string.substring(m+1);
                                            count++;
                                            state = 1;
                                        }
                                    }
                                    if (count == 0){
                                        str1 = string;
                                    }
                                    itemPromotionType3s.add(new EntryItemPromotionType3(str1, str2,
                                            getListCriDet.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriDet.get(j).getUom()));
                                }
                            }
                        }
                    }
                    itemPromotionType3s.add(new SectionItemPromotionType3("Promotion Benefit"));
                    for (int j = 0; j < getListCriBenefit.size(); j++) {
                        if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                            for (int k = 0; k < getListProduct.size(); k++) {
                                if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                    prodCodeBen.add(getListCriBenefit.get(j).getProdCode());
                                    amountQtyBen.add(getListCriBenefit.get(j).getAmountQty());
                                    String string = mDBHelper4.getNameBySearchId(getListCriBenefit.get(j).getProdCode());
                                    String str1 = "";
                                    String str2 = "";

                                    int count = 0;
                                    int state = 0;
                                    for (int m=0;m<string.length();m++){
                                        if (m>22 && string.charAt(m) == ' ' && state == 0){
                                            str1 = string.substring(0,m);
                                            str2 = string.substring(m+1);
                                            count++;
                                            state = 1;
                                        }
                                    }
                                    if (count == 0){
                                        str1 = string;
                                    }
                                    itemPromotionType3s.add(new EntryItemBenType3(str1, str2,
                                            getListCriBenefit.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriBenefit.get(j).getUom(), getListCriBenefit.get(j).getAmountQty()));
                                }
                            }
                        }
                    }
                }
            }
        }


        EntryAdapterPromotionType2 entry2 = new EntryAdapterPromotionType2(getActivity(), itemPromotionType2s);
        EntryAdapterPromotionType3 entry3 = new EntryAdapterPromotionType3(getActivity(), itemPromotionType3s);

        final Map<Double, String> treeMap = new TreeMap<Double, String>(new Comparator<Double>() {
            @Override
            public int compare(Double aDouble, Double t1) {
                return t1.compareTo(aDouble);
            }
        });

        treeMap.putAll(qtyCriType2);
        for (Map.Entry entry1 : treeMap.entrySet()){
            Log.e("chkMap","chkMap_"+entry1.getKey());
            Log.e("chkMap","chkMap_"+entry1.getValue());
//            Log.e("chkMap","chkMap_"+qtyBenType2);
        }

        switch (getListPromotion.get(0).getType()){
            case 1:
//                v.setContentView(R.layout.poa_promotion_detail);
                v = inflater.inflate(R.layout.poa_promotion_detail, container, false);

                back = (TextView) v.findViewById(R.id.tvgoBack);
                title = (TextView) v.findViewById(R.id.toolbar_title);
                tvDone = (TextView) v.findViewById(R.id.toolbar_done_1);
                promName = (TextView) v.findViewById(R.id.prom_name);
                promDes = (TextView) v.findViewById(R.id.prom_des);
                ttAmount = (TextView) v.findViewById(R.id.ttAmount);
                tvAmount = (TextView) v.findViewById(R.id.tvAmount);

                back.setTypeface(fontThSarabun);
                title.setTypeface(fontThSarabunBold);
                tvDone.setTypeface(fontThSarabun);
                promName.setTypeface(fontThSarabunBold);
                promDes.setTypeface(fontThSarabun);
                ttAmount.setTypeface(fontThSarabun);
                tvAmount.setTypeface(fontThSarabun);

                tvTier = (TextView) v.findViewById(R.id.tvTier);
                orderNum = (EditText) v.findViewById(R.id.tvOrderNum);

                tvTier.setTypeface(fontThSarabunBold);
                orderNum.setTypeface(fontThSarabunBold);

                mListview = (ListView) v.findViewById(R.id.list_item_promotion_detail);

                if (getListOrderProm.size() != 0){
                    boolean chk = false;
                    double qty = 0;
                    double amt = 0;
                    double foc = 0;
                    double total = 0;
                    for (int i=0;i<getListOrderProm.size();i++){
                        if (getListOrderProm.get(i).getIdSvst() == newIdSV &&
                                getListOrderProm.get(i).getIdProm().equals(id)){
                            orderNum.setText("" + getListOrderProm.get(i).getPromQty());
                            String str = orderNum.getText().toString();
                            total = Double.parseDouble(str);
                            chk = true;
                        }
                    }
                    if (chk){
                        for (int i = 0; i < getListPromCri.size(); i++) {
                            if (getListPromCri.get(i).getPromId().equals(id)) {
                                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Criteria"));

                                for (int j = 0; j < getListCriDet.size(); j++) {
                                    if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                                        qty = getListCriDet.get(j).getAmountQty() * total;
                                        amountQtyCri.add(getListCriDet.get(j).getAmountQty());
                                        for (int k = 0; k < getListProduct.size(); k++) {
                                            if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                                amt += getListProduct.get(k).getUnitprice() * qty;
                                                priceCri.add(getListProduct.get(k).getUnitprice());
                                                prodCodeCri.add(getListCriDet.get(j).getProdCode());
                                                String string = mDBHelper4.getNameBySearchId(getListCriDet.get(j).getProdCode());
                                                String str1 = "";
                                                String str2 = "";

                                                int count = 0;
                                                int state = 0;
                                                for (int m=0;m<string.length();m++){
                                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                                        str1 = string.substring(0,m);
                                                        str2 = string.substring(m+1);
                                                        count++;
                                                        state = 1;
                                                    }
                                                }
                                                if (count == 0){
                                                    str1 = string;
                                                }
                                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                                        getListCriDet.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriDet.get(j).getUom(), getListCriDet.get(j).getAmountQty(),qty));
                                            }
                                        }
                                    }
                                }

                                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Benefit"));
                                for (int j = 0; j < getListCriBenefit.size(); j++) {
                                    if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                                        foc = getListCriBenefit.get(j).getAmountQty() * total;
                                        amountQtyBen.add(getListCriBenefit.get(j).getAmountQty());
                                        for (int k = 0; k < getListProduct.size(); k++) {
                                            if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                                prodCodeBen.add(getListCriBenefit.get(j).getProdCode());
                                                String string = mDBHelper4.getNameBySearchId(getListCriBenefit.get(j).getProdCode());
                                                String str1 = "";
                                                String str2 = "";

                                                int count = 0;
                                                int state = 0;
                                                for (int m=0;m<string.length();m++){
                                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                                        str1 = string.substring(0,m);
                                                        str2 = string.substring(m+1);
                                                        count++;
                                                        state = 1;
                                                    }
                                                }
                                                if (count == 0){
                                                    str1 = string;
                                                }
                                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                                        getListCriBenefit.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriBenefit.get(j).getUom(), getListCriBenefit.get(j).getAmountQty(),foc));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        entry = new EntryAdapterPromotionDetail(getActivity(), itemPromotionDetails);
                    }else {
                        setDefaultPromType1();
                    }
                    tvAmount.setText(new DecimalFormat("##.##").format(amt));
                }else {
                    setDefaultPromType1();
                    tvAmount.setText(new DecimalFormat("##.##").format(0));
                }
                mListview.setAdapter(entry);
                break;
            case 2:
//                setContentView(R.layout.poa_promotion_type_2);
                v = inflater.inflate(R.layout.poa_promotion_type_2, container, false);

                back = (TextView) v.findViewById(R.id.tvgoBack);
                title = (TextView) v.findViewById(R.id.toolbar_title);
                tvDone = (TextView) v.findViewById(R.id.toolbar_done_2);
                promName = (TextView) v.findViewById(R.id.prom_name);
                promDes = (TextView) v.findViewById(R.id.prom_des);
                ttAmount = (TextView) v.findViewById(R.id.ttAmount);
                tvAmount = (TextView) v.findViewById(R.id.tvAmount);

                back.setTypeface(fontThSarabun);
                title.setTypeface(fontThSarabunBold);
                tvDone.setTypeface(fontThSarabun);
                promName.setTypeface(fontThSarabunBold);
                promDes.setTypeface(fontThSarabun);
                ttAmount.setTypeface(fontThSarabun);
                tvAmount.setTypeface(fontThSarabun);

                ttCriteria = (TextView) v.findViewById(R.id.ttCriteria);
                tvprodNameCri = (TextView) v.findViewById(R.id.tvProdNameCri);
                tvprodCodeCri = (TextView) v.findViewById(R.id.tvProdCodeCri);
                tvpriceCri = (TextView) v.findViewById(R.id.tvPriceCri);
                tvTotalCri = (TextView) v.findViewById(R.id.tvTotalCri);
                tvuomCri = (TextView) v.findViewById(R.id.tvUomCri);
                ttBenefit = (TextView) v.findViewById(R.id.ttBenefit);
                tvprodNameBen = (TextView) v.findViewById(R.id.tvProdNameBen);
                tvprodCodeBen = (TextView) v.findViewById(R.id.tvProdCodeBen);
                tvpriceBen = (TextView) v.findViewById(R.id.tvPriceBen);
                tvTotalBen = (TextView) v.findViewById(R.id.tvTotalBen);
                tvuomBen = (TextView) v.findViewById(R.id.tvUomBen);
                ttTotalIn = (TextView) v.findViewById(R.id.ttTotalIn);
                ttTier = (TextView) v.findViewById(R.id.ttTier);
                ttCri = (TextView) v.findViewById(R.id.ttCri);
                ttBen = (TextView) v.findViewById(R.id.ttBen);
                orderNum = (EditText) v.findViewById(R.id.edTotal);
                TextView ttPriceCri = (TextView) v.findViewById(R.id.ttPriceCri);
                TextView ttQtyCri = (TextView) v.findViewById(R.id.ttQtyCri);
                TextView ttPriceBen = (TextView) v.findViewById(R.id.ttPriceBen);
                TextView ttQtyBen = (TextView) v.findViewById(R.id.ttQtyBen);

                ttCriteria.setTypeface(fontThSarabunBold);
                tvprodNameCri.setTypeface(fontThSarabunBold);
                tvprodCodeCri.setTypeface(fontThSarabun);
                tvpriceCri.setTypeface(fontThSarabun);
                tvTotalCri.setTypeface(fontThSarabun);
                tvuomCri.setTypeface(fontThSarabun);
                ttBenefit.setTypeface(fontThSarabunBold);
                tvprodNameBen.setTypeface(fontThSarabunBold);
                tvprodCodeBen.setTypeface(fontThSarabun);
                tvpriceBen.setTypeface(fontThSarabun);
                tvTotalBen.setTypeface(fontThSarabun);
                tvuomBen.setTypeface(fontThSarabun);
                ttTotalIn.setTypeface(fontThSarabunBold);
                ttTier.setTypeface(fontThSarabunBold);
                ttCri.setTypeface(fontThSarabunBold);
                ttBen.setTypeface(fontThSarabunBold);
                orderNum.setTypeface(fontThSarabunBold);
                ttPriceCri.setTypeface(fontThSarabunBold);
                ttQtyCri.setTypeface(fontThSarabunBold);
                ttPriceBen.setTypeface(fontThSarabunBold);
                ttQtyBen.setTypeface(fontThSarabunBold);

                mListview = (ListView) v.findViewById(R.id.list_item_promotion_detail);
                mListview.setAdapter(entry2);

                for (int i = 0; i < getListPromCri.size(); i++) {
                    if (getListPromCri.get(i).getPromId().equals(id)) {
                        for (int j = 0; j < getListCriDet.size(); j++) {
                            Log.e("cridet", getListCriDet.get(j).getIdPrcr());
                            if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                                for (int k = 0; k < getListProduct.size(); k++) {
                                    if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                        tvprodNameCri.setText(getListProduct.get(k).getName());
                                        tvprodCodeCri.setText(getListCriDet.get(j).getProdCode());
                                        tvpriceCri.setText(getListProduct.get(k).getUnitprice()+"");
                                        tvuomCri.setText(getListProduct.get(k).getUom());
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < getListCriBenefit.size(); j++) {
                            if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                                for (int k = 0; k < getListProduct.size(); k++) {
                                    if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                        tvprodNameBen.setText(getListProduct.get(k).getName());
                                        tvprodCodeBen.setText(getListCriBenefit.get(j).getProdCode());
                                        tvpriceBen.setText(getListProduct.get(k).getUnitprice()+"");
                                        tvuomBen.setText(getListProduct.get(k).getUom());
                                    }
                                }
                            }
                        }
                    }
                }

                break;
            case 3:
//                setContentView(R.layout.poa_promotion_type_3);
                v = inflater.inflate(R.layout.poa_promotion_type_3, container, false);

                back = (TextView) v.findViewById(R.id.tvgoBack);
                title = (TextView) v.findViewById(R.id.toolbar_title);
                tvDone = (TextView) v.findViewById(R.id.toolbar_done);
                promName = (TextView) v.findViewById(R.id.prom_name);
                promDes = (TextView) v.findViewById(R.id.prom_des);
                ttAmount = (TextView) v.findViewById(R.id.ttAmount);
                tvAmount = (TextView) v.findViewById(R.id.tvAmount);

                back.setTypeface(fontThSarabun);
                title.setTypeface(fontThSarabunBold);
                tvDone.setTypeface(fontThSarabun);
                promName.setTypeface(fontThSarabunBold);
                promDes.setTypeface(fontThSarabun);
                ttAmount.setTypeface(fontThSarabun);
                tvAmount.setTypeface(fontThSarabun);

                ttCount = (TextView) v.findViewById(R.id.ttCount);
                tvCount = (TextView) v.findViewById(R.id.tvCount);

                ttCount.setTypeface(fontThSarabunBold);
                tvCount.setTypeface(fontThSarabunBold);

                tvCount.setText(""+countType3);

                mListview = (ListView) v.findViewById(R.id.list_item_promotion_detail);
                mListview.setAdapter(entry3);
                break;
            case 4:
//                setContentView(R.layout.poa_promotion_detail);
                v = inflater.inflate(R.layout.poa_promotion_detail, container, false);
                break;
        }

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("chkClick","chkClick_"+mListview.getItemAtPosition(i).toString());
            }
        });

        promName.setText(name);
        promDes.setText(description);

        if (getListOrderProm.size() != 0){
            for (int i=0;i<getListOrderProm.size();i++){
                if (getListOrderProm.get(i).getIdSvst() == newIdSV &&
                        getListOrderProm.get(i).getIdProm().equals(id)){
                    orderNum.setText("" + getListOrderProm.get(i).getPromQty());
                    String str = orderNum.getText().toString();
                    double total = Double.parseDouble(str);
                    if (getListPromotion.get(0).getType() == 1){

                    }else if (getListPromotion.get(0).getType() == 2) {
                        String priceText = tvpriceCri.getText().toString();
                        String[] price = priceText.split("/");
                        tvAmount.setText(new DecimalFormat("##.##").format(total * Double.parseDouble(price[0])));
                        tvTotalCri.setText(new DecimalFormat("##.##").format(total));
                        String idPrcr = "";
                        for (Map.Entry<Double, String> entry1 : treeMap.entrySet()) {
                            if (total >= entry1.getKey()) {
                                idPrcr = entry1.getValue();
                                break;
                            }
                        }
                        double focCal = 0;
                        focCal = mDBHelper3.getFOCBySearchIdPrcr(idPrcr);
                        tvTotalBen.setText(new DecimalFormat("##.##").format(focCal));
                    }
                }
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Poa_PromotionDetail.this, Tab2Promotion.class);
//
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getIdAdd", newIdAddr);
//                i.putExtra("getId", newId); // i for account
//                i.putExtra("getName", newName);
//                i.putExtra("getTime", newTime);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

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
//                    return;
                }
            }
        }

        if (getListPromotion.get(0).getType() == 1 || getListPromotion.get(0).getType() == 2) {
            orderNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String str = orderNum.getText().toString();

                    if (!str.isEmpty()) {
                        double total = Double.parseDouble(str);
                        if (getListPromotion.get(0).getType() == 1) {
                            if (str.equals("") || str.equals(null) || str.isEmpty()){
                                total = 0;
                            }
                            entry.clear();
                            ArrayList<Double> qty = new ArrayList<Double>();
                            double amt = 0;
                            for (int j = 0; j < amountQtyCri.size(); j++) {
                                qty.add(amountQtyCri.get(j) * total);
                                amt += priceCri.get(j) * total * amountQtyCri.get(j);
                            }
                            ArrayList<Double> foc = new ArrayList<Double>();
                            for (int j = 0; j < amountQtyBen.size(); j++) {
                                foc.add(amountQtyBen.get(j) * total);
                            }
                            setPromType1(qty,foc);
                            mListview.setAdapter(entry);
                            tvAmount.setText(new DecimalFormat("##.##").format(amt));
                        } else if (getListPromotion.get(0).getType() == 2) {
                            String priceText = tvpriceCri.getText().toString();
                            String[] price = priceText.split("/");
                            tvTotalCri.setText(new DecimalFormat("##.##").format(total));
                            tvAmount.setText(new DecimalFormat("##.##").format(total * Double.parseDouble(price[0])));
                            String idPrcr = "";
                            for (Map.Entry<Double, String> entry1 : treeMap.entrySet()) {
                                if (total >= entry1.getKey()) {
                                    idPrcr = entry1.getValue();
                                    break;
                                }
                            }
                            double focCal = 0;
                            focCal = mDBHelper3.getFOCBySearchIdPrcr(idPrcr);
                            tvTotalBen.setText(new DecimalFormat("##.##").format(focCal));
                        }
                    } else {
                        tvAmount.setText(new DecimalFormat("##.##").format(0));
                        if (getListPromotion.get(0).getType() == 1) {
                            entry.clear();
                            setDefaultPromType1();
                            mListview.setAdapter(entry);
                        }else if (getListPromotion.get(0).getType() == 2){
                            tvTotalCri.setText(new DecimalFormat("##.##").format(0));
                            tvTotalBen.setText(new DecimalFormat("##.##").format(0));
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }else if (getListPromotion.get(0).getType() == 3){

        }

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), Tab2Promotion.class);

                String str = orderNum.getText().toString();
                if (Double.parseDouble(str) != 0){
                    if (getListOrderProm.size() == 0) {
                        if (getListPromotion.get(0).getType() == 1) {
                            for (int j = 0; j < prodCodeCri.size(); j++) {
                                mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                        prodCodeCri.get(j),
                                        id,
                                        (amountQtyCri.get(j) * Double.parseDouble(str)),
                                        (amountQtyCri.get(j) * Double.parseDouble(str)),
                                        0,
                                        0,
                                        getListPromotion.get(0).getType(),
                                        Double.parseDouble(str),
                                        false);
                            }
                            for (int j = 0; j < prodCodeBen.size(); j++) {
                                mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                        prodCodeBen.get(j),
                                        id,
                                        0,
                                        0,
                                        (amountQtyBen.get(j) * Double.parseDouble(str)),
                                        (amountQtyBen.get(j) * Double.parseDouble(str)),
                                        getListPromotion.get(0).getType(),
                                        Double.parseDouble(str),
                                        false);
                            }
                        }else if (getListPromotion.get(0).getType() == 2){
                            mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                    prodCodeCriType2,
                                    id,
                                    Double.parseDouble(str),
                                    Double.parseDouble(str),
                                    0,
                                    0,
                                    getListPromotion.get(0).getType(),
                                    Double.parseDouble(str),
                                    false);
                            String idPrcr = "";
                            for (Map.Entry<Double, String> entry1 : treeMap.entrySet()){
                                if (Double.parseDouble(str) >= entry1.getKey()){
                                    idPrcr = entry1.getValue();
                                    break;
                                }
                            }
                            double focCal = 0;
                            focCal = mDBHelper3.getFOCBySearchIdPrcr(idPrcr);
                            mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                    prodCodeBenType2,
                                    id,
                                    0,
                                    0,
                                    focCal,
                                    focCal,
                                    getListPromotion.get(0).getType(),
                                    Double.parseDouble(str),
                                    false);
                        }
                    }else {
                        boolean check = false;
                        for (int k=0;k<getListOrderProm.size();k++){
                            if (getListOrderProm.get(k).getIdProm().equals(id) && getListOrderProm.get(k).getIdSvst() == newIdSV){
                                check = true;
                            }
                        }
                        if (check){
                            if (getListPromotion.get(0).getType() == 1) {
                                for (int k = 0; k < getListOrderProm.size(); k++) {
                                    for (int j = 0; j < prodCodeCri.size(); j++) {
                                        if (prodCodeCri.get(j).equals(getListOrderProm.get(k).getProdCode()) &&
                                                getListOrderProm.get(k).getIdProm().equals(id) &&
                                                getListOrderProm.get(k).getIdSvst() == newIdSV) {
                                            if ((Double.parseDouble(str)) > getListOrderProm.get(k).getPromQty()) {
                                                mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                        newIdSV,
                                                        prodCodeCri.get(j),
                                                        id,
                                                        (amountQtyCri.get(j) * Double.parseDouble(str)),
                                                        getListOrderProm.get(k).getQtyRemain() + (amountQtyCri.get(j) * (Double.parseDouble(str) - getListOrderProm.get(k).getPromQty())),
                                                        0,
                                                        0,
                                                        getListPromotion.get(0).getType(),
                                                        Double.parseDouble(str),
                                                        false);
                                            } else if (Double.parseDouble(str) < getListOrderProm.get(k).getPromQty()) {
                                                mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                        newIdSV,
                                                        prodCodeCri.get(j),
                                                        id,
                                                        (amountQtyCri.get(j) * Double.parseDouble(str)),
                                                        getListOrderProm.get(k).getQtyRemain() - (amountQtyCri.get(j) * (getListOrderProm.get(k).getPromQty() - Double.parseDouble(str))),
                                                        0,
                                                        0,
                                                        getListPromotion.get(0).getType(),
                                                        Double.parseDouble(str),
                                                        false);
                                            }
                                        }
                                    }
                                    for (int j = 0; j < prodCodeBen.size(); j++) {
                                        if (prodCodeBen.get(j).equals(getListOrderProm.get(k).getProdCode()) &&
                                                getListOrderProm.get(k).getIdProm().equals(id) &&
                                                getListOrderProm.get(k).getIdSvst() == newIdSV) {
                                            if ((Double.parseDouble(str)) > getListOrderProm.get(k).getPromQty()) {
                                                mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                        newIdSV,
                                                        prodCodeBen.get(j),
                                                        id,
                                                        0,
                                                        0,
                                                        (amountQtyBen.get(j) * Double.parseDouble(str)),
                                                        getListOrderProm.get(k).getFocRemain() + (amountQtyBen.get(j) * (Double.parseDouble(str) - getListOrderProm.get(k).getPromQty())),
                                                        getListPromotion.get(0).getType(),
                                                        Double.parseDouble(str),
                                                        false);
                                            } else if (Double.parseDouble(str) < getListOrderProm.get(k).getPromQty()) {
                                                mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                        newIdSV,
                                                        prodCodeBen.get(j),
                                                        id,
                                                        0,
                                                        0,
                                                        (amountQtyBen.get(j) * Double.parseDouble(str)),
                                                        getListOrderProm.get(k).getFocRemain() - (amountQtyBen.get(j) * (getListOrderProm.get(k).getPromQty() - Double.parseDouble(str))),
                                                        getListPromotion.get(0).getType(),
                                                        Double.parseDouble(str),
                                                        false);
                                            }
                                        }
                                    }
                                }
                            }else if (getListPromotion.get(0).getType() == 2){
                                for (int k = 0; k < getListOrderProm.size(); k++) {
                                    if (prodCodeCriType2.equals(getListOrderProm.get(k).getProdCode()) &&
                                            getListOrderProm.get(k).getIdProm().equals(id) &&
                                            getListOrderProm.get(k).getIdSvst() == newIdSV &&
                                            getListOrderProm.get(k).getQuantity() != 0) {
                                        if ((Double.parseDouble(str)) > getListOrderProm.get(k).getPromQty()) {
                                            mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                    newIdSV,
                                                    prodCodeCriType2,
                                                    id,
                                                    Double.parseDouble(str),
                                                    getListOrderProm.get(k).getQtyRemain() + (Double.parseDouble(str) - getListOrderProm.get(k).getPromQty()),
                                                    0,
                                                    0,
                                                    getListPromotion.get(0).getType(),
                                                    Double.parseDouble(str),
                                                    false);
                                        } else if (Double.parseDouble(str) < getListOrderProm.get(k).getPromQty()) {
                                            mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                    newIdSV,
                                                    prodCodeCriType2,
                                                    id,
                                                    Double.parseDouble(str),
                                                    getListOrderProm.get(k).getQtyRemain() - (getListOrderProm.get(k).getPromQty() - Double.parseDouble(str)),
                                                    0,
                                                    0,
                                                    getListPromotion.get(0).getType(),
                                                    Double.parseDouble(str),
                                                    false);
                                        }
                                    }else if (prodCodeBenType2.equals(getListOrderProm.get(k).getProdCode()) &&
                                            getListOrderProm.get(k).getIdProm().equals(id) &&
                                            getListOrderProm.get(k).getIdSvst() == newIdSV &&
                                            getListOrderProm.get(k).getFoc() != 0){
                                        String idPrcr = "";
                                        for (Map.Entry<Double, String> entry1 : treeMap.entrySet()){
                                            if (Double.parseDouble(str) >= entry1.getKey()){
                                                idPrcr = entry1.getValue();
                                                break;
                                            }
                                        }
                                        double focCal = 0;
                                        focCal = mDBHelper3.getFOCBySearchIdPrcr(idPrcr);
                                        if ((Double.parseDouble(str)) > getListOrderProm.get(k).getPromQty()) {
                                            mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                    newIdSV,
                                                    prodCodeBenType2,
                                                    id,
                                                    0,
                                                    0,
                                                    focCal,
                                                    getListOrderProm.get(k).getFocRemain() + (focCal - getListOrderProm.get(k).getFoc()),
                                                    getListPromotion.get(0).getType(),
                                                    Double.parseDouble(str),
                                                    false);
                                        } else if (Double.parseDouble(str) < getListOrderProm.get(k).getPromQty()) {
                                            mDBHelper5.updateOrderPromotionTemporary(getListOrderProm.get(k).getId(),
                                                    newIdSV,
                                                    prodCodeBenType2,
                                                    id,
                                                    0,
                                                    0,
                                                    focCal,
                                                    getListOrderProm.get(k).getFocRemain() - (getListOrderProm.get(k).getFoc() - focCal),
                                                    getListPromotion.get(0).getType(),
                                                    Double.parseDouble(str),
                                                    false);
                                        }
                                    }
                                }
                            }
                        }else {
                            if (getListPromotion.get(0).getType() == 1) {
                                for (int j = 0; j < prodCodeCri.size(); j++) {
                                    mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                            prodCodeCri.get(j),
                                            id,
                                            (amountQtyCri.get(j) * Double.parseDouble(str)),
                                            (amountQtyCri.get(j) * Double.parseDouble(str)),
                                            0,
                                            0,
                                            getListPromotion.get(0).getType(),
                                            Double.parseDouble(str),
                                            false);
                                }
                                for (int j = 0; j < prodCodeBen.size(); j++) {
                                    mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                            prodCodeBen.get(j),
                                            id,
                                            0,
                                            0,
                                            (amountQtyBen.get(j) * Double.parseDouble(str)),
                                            (amountQtyBen.get(j) * Double.parseDouble(str)),
                                            getListPromotion.get(0).getType(),
                                            Double.parseDouble(str),
                                            false);
                                }
                            }else if (getListPromotion.get(0).getType() == 2){
                                String idPrcr = "";
                                for (Map.Entry<Double, String> entry1 : treeMap.entrySet()){
                                    if (Double.parseDouble(str) >= entry1.getKey()){
                                        idPrcr = entry1.getValue();
                                        break;
                                    }
                                }
                                double focCal = 0;
                                focCal = mDBHelper3.getFOCBySearchIdPrcr(idPrcr);
                                mDBHelper5.addOrderPromotionTemporary(newIdSV,
                                        prodCodeCriType2,
                                        id,
                                        Double.parseDouble(str),
                                        Double.parseDouble(str),
                                        focCal,
                                        focCal,
                                        getListPromotion.get(0).getType(),
                                        Double.parseDouble(str),
                                        false);
                            }
                        }
                    }
                }else {
                    if (getListOrderProm.size() != 0){
                        if (getListPromotion.get(0).getType() == 1) {
                            for (int k = 0; k < getListOrderProm.size(); k++) {
                                for (int j = 0; j < prodCodeCri.size(); j++) {
                                    if (prodCodeCri.get(j).equals(getListOrderProm.get(k).getProdCode()) &&
                                            getListOrderProm.get(k).getIdProm().equals(id) &&
                                            getListOrderProm.get(k).getIdSvst() == newIdSV) {
                                        mDBHelper5.deleteOrderPromotionTemporary(getListOrderProm.get(k).getId());
                                    }
                                }
                                for (int j = 0; j < prodCodeBen.size(); j++) {
                                    if (prodCodeBen.get(j).equals(getListOrderProm.get(k).getProdCode()) &&
                                            getListOrderProm.get(k).getIdProm().equals(id) &&
                                            getListOrderProm.get(k).getIdSvst() == newIdSV) {
                                        mDBHelper5.deleteOrderPromotionTemporary(getListOrderProm.get(k).getId());
                                    }
                                }
                            }
                        }else if (getListPromotion.get(0).getType() == 2){
                            for (int k = 0; k < getListOrderProm.size(); k++) {
                                if (prodCodeCriType2.equals(getListOrderProm.get(k).getProdCode()) &&
                                        getListOrderProm.get(k).getIdProm().equals(id) &&
                                        getListOrderProm.get(k).getIdSvst() == newIdSV) {
                                    mDBHelper5.deleteOrderPromotionTemporary(getListOrderProm.get(k).getId());
                                }
                            }
                        }
                    }
                }

                i.putExtra("getNumber", newNumber);
                i.putExtra("getIdAdd", newIdAddr);
                i.putExtra("getId", newId); // i for account
                i.putExtra("getName", newName);
                i.putExtra("getTime", newTime);
                i.putExtra("getIdSV", newIdSV);
                startActivity(i);
            }
        });

        return v;

    }

    private void setPromType1(ArrayList<Double> qty, ArrayList<Double> foc){
        for (int i = 0; i < getListPromCri.size(); i++) {
            if (getListPromCri.get(i).getPromId().equals(id)) {
                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Criteria"));
                int cnt =0;
                for (int j = 0; j < getListCriDet.size(); j++) {
                    if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                        for (int k = 0; k < getListProduct.size(); k++) {
                            if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                String string = mDBHelper4.getNameBySearchId(getListCriDet.get(j).getProdCode());
                                String str1 = "";
                                String str2 = "";

                                int count = 0;
                                int state = 0;
                                for (int m=0;m<string.length();m++){
                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                        str1 = string.substring(0,m);
                                        str2 = string.substring(m+1);
                                        count++;
                                        state = 1;
                                    }
                                }
                                if (count == 0){
                                    str1 = string;
                                }
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                        getListCriDet.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriDet.get(j).getUom(), getListCriDet.get(j).getAmountQty(),qty.get(cnt)));
                                cnt++;
                            }
                        }
                    }
                }

                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Benefit"));
                cnt = 0;
                for (int j = 0; j < getListCriBenefit.size(); j++) {
                    if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                        for (int k = 0; k < getListProduct.size(); k++) {
                            if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                String string = mDBHelper4.getNameBySearchId(getListCriBenefit.get(j).getProdCode());
                                String str1 = "";
                                String str2 = "";

                                int count = 0;
                                int state = 0;
                                for (int m=0;m<string.length();m++){
                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                        str1 = string.substring(0,m);
                                        str2 = string.substring(m+1);
                                        count++;
                                        state = 1;
                                    }
                                }
                                if (count == 0){
                                    str1 = string;
                                }
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                        getListCriBenefit.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriBenefit.get(j).getUom(), getListCriBenefit.get(j).getAmountQty(),foc.get(cnt)));
                                cnt++;
                            }
                        }
                    }
                }
            }
        }
        entry = new EntryAdapterPromotionDetail(getActivity(), itemPromotionDetails);
    }

    private void setDefaultPromType1(){
        for (int i = 0; i < getListPromCri.size(); i++) {
            if (getListPromCri.get(i).getPromId().equals(id)) {
                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Criteria"));

                for (int j = 0; j < getListCriDet.size(); j++) {
                    if ((getListPromCri.get(i).getId()).equals(getListCriDet.get(j).getIdPrcr())) {
                        amountQtyCri.add(getListCriDet.get(j).getAmountQty());
                        for (int k = 0; k < getListProduct.size(); k++) {
                            if (getListCriDet.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                priceCri.add(getListProduct.get(k).getUnitprice());
                                prodCodeCri.add(getListCriDet.get(j).getProdCode());
                                String string = mDBHelper4.getNameBySearchId(getListCriDet.get(j).getProdCode());
                                String str1 = "";
                                String str2 = "";

                                int count = 0;
                                int state = 0;
                                for (int m=0;m<string.length();m++){
                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                        str1 = string.substring(0,m);
                                        str2 = string.substring(m+1);
                                        count++;
                                        state = 1;
                                    }
                                }
                                if (count == 0){
                                    str1 = string;
                                }
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                        getListCriDet.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriDet.get(j).getUom(), getListCriDet.get(j).getAmountQty(),0));
                            }
                        }
                    }
                }

                itemPromotionDetails.add(new SectionItemPromotionDetail("Promotion Benefit"));
                for (int j = 0; j < getListCriBenefit.size(); j++) {
                    if ((getListPromCri.get(i).getId()).equals(getListCriBenefit.get(j).getIdPrcr())) {
                        amountQtyBen.add(getListCriBenefit.get(j).getAmountQty());
                        for (int k = 0; k < getListProduct.size(); k++) {
                            if (getListCriBenefit.get(j).getProdCode().equals(getListProduct.get(k).getCode())) {
                                prodCodeBen.add(getListCriBenefit.get(j).getProdCode());
                                String string = mDBHelper4.getNameBySearchId(getListCriBenefit.get(j).getProdCode());
                                String str1 = "";
                                String str2 = "";

                                int count = 0;
                                int state = 0;
                                for (int m=0;m<string.length();m++){
                                    if (m>22 && string.charAt(m) == ' ' && state == 0){
                                        str1 = string.substring(0,m);
                                        str2 = string.substring(m+1);
                                        count++;
                                        state = 1;
                                    }
                                }
                                if (count == 0){
                                    str1 = string;
                                }
                                itemPromotionDetails.add(new EntryItemPromotionDetail(str1, str2,
                                        getListCriBenefit.get(j).getProdCode(), getListProduct.get(k).getUnitprice(), getListCriBenefit.get(j).getUom(), getListCriBenefit.get(j).getAmountQty(),0));
                            }
                        }
                    }
                }
            }
        }
        entry = new EntryAdapterPromotionDetail(getActivity(), itemPromotionDetails);
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

