package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetValueToEditText;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pannikar on 6/20/16.
 */
public class EntryAdapterSinglePage5 extends ArrayAdapter<ItemSinglePage> {

    private Context context;
    private ArrayList<ItemSinglePage> items;
    private LayoutInflater vi;

    private ArrayList arrOnShelf = new ArrayList();
    //private ArrayList<Integer> arrQuantity = new ArrayList<Integer>();
    private ArrayList<Integer> arrOld = new ArrayList<Integer>();
    List<Integer> listQuantity = new ArrayList<Integer>();
    List<Integer> listQuantityNew = new ArrayList<Integer>();
    List<Integer> listNew = new ArrayList<Integer>();

    List<String> listHeader = new ArrayList<String>();
//    List<String> listHeaderColor = new ArrayList<String>();

    ImageView img;

//    Typeface fontThSarabun, fontThSarabunBold;
    static Typeface fontThSarabunSt, fontThSarabunBoldSt;
    ItemSinglePage i;
    SectionItemSinglePage si;
    EntryItemSinglePage ei;
    SubSectionItemSinglePage ssi;
    private LayoutInflater inflater;
    Watcher wt;
    int positionIndex;
    TextView tvFOC, tvQty, tvAmount; // tvSave;
    int countChg = 0;
    int countChgQuan = 0;
    int getOrderTempSize = 0;
    // int sngle;

    LinearLayout lnColorSection;

    ArrayList arrProductCode = new ArrayList();

    GetValueToEditText getValToDB;
    int newIdSV;

    TextView ss_name_product;
    LinearLayout lnColorSectionHeader;
    int firstVisible;
    int indexHeaderVisible;

    int keepIndexHeader, intHeader;
    String keepName, headerName;

    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();


//    public EntryAdapterSinglePage5(Context context, ArrayList<ItemSinglePage> items, ArrayList arrOnShelf) {
//        super(context,0 , items);
//        this.context = context;
//        this.items = items;
//        inflater = LayoutInflater.from(context);
//        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.arrOnShelf = arrOnShelf;
//
//        Log.e("test", "getView:_position:_0_i:_size2: " + arrOnShelf.size());
//
//    }

    public EntryAdapterSinglePage5(Context context,
                                   TextView tvFOC,
                                   TextView tvQty,
                                   TextView tvAmount,

                                   ArrayList<ItemSinglePage> items,
                                   ArrayList arrOnShelf,

                                   ArrayList arrQuantityNew,

                                   ArrayList arrProductCode, int newIdSV,
                                   TextView ss_name_product,
                                   LinearLayout lnColorSection,

                                   HashMap<Integer, String> mapALLIndPrdNameNew,
                                   HashMap<Integer, String> mapALLIndColorNew) {

        super(context,0 , items);

        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrOnShelf = arrOnShelf;

        this.listQuantityNew = arrQuantityNew; // for show in EditText
        this.tvFOC = tvFOC;
        this.tvQty = tvQty;
        this.tvAmount = tvAmount;

        this.arrProductCode = arrProductCode;
        this.newIdSV = newIdSV;
        this.ss_name_product = ss_name_product;
        this.lnColorSectionHeader = lnColorSection;

        this.mapALLIndPrdName = mapALLIndPrdNameNew;
        this.mapALLIndColor = mapALLIndColorNew;

    }

    @Override
    public int getCount() {
        // return arrOnShelf.size();
        //return sngle;
        return items.size();
    }

//    @Override
//    public Item getItem(int position) {
//        return items.get(position);
//    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        int reType;
        i = items.get(position);


        if(i.isSection()==true && i.isSubSection()==false){
            reType = 0;
        }else if(i.isSection()==false && i.isSubSection()==true){
            reType = 1;
        }else{
            reType = 2;
        }

        return reType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.e("test", "getView:_position:_0:_getView()_: " + position);

        // the Last: GetProductSinglePage1: getView:_position:_0:_getView()_: 35
        // the Last: GetProductSinglePage2: getView:_position:_0:_getView()_: 87
//        fontThSarabun = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
//        fontThSarabunBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");

        fontThSarabunSt = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBoldSt = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");


//        getListOrderTempListBySearchId
        getValToDB = new GetValueToEditText(context, newIdSV);
        getOrderTempSize = getValToDB.orderTempList.size();
        tvQty.setText(Integer.toString(getOrderTempSize));

        i = items.get(position);

        this.positionIndex = position;

        firstVisible = ((ListView) parent).getFirstVisiblePosition();

//        if(firstVisible == 0){
//
//        } else {
//            ss_name_product.setText(mapALLIndPrdName.get(firstVisible).toString());
//            lnColorSectionHeader.setBackgroundColor(Color.parseColor(mapALLIndColor.get(firstVisible).toString()));
//        }

        int viewType = getItemViewType(position);
        switch (viewType) {

            case 0:

                String headerVisible = null;

                indexHeaderVisible = position;

                Log.e("test", "what in-first+position:_" + position);

                if(firstVisible == indexHeaderVisible){
                    headerVisible = "GONE";

                }else {
                    headerVisible = "SHOW";
                }

                    si = (SectionItemSinglePage) i;
                    convertView = inflateSectionView(convertView, parent, si, headerVisible);

                break;

            case 1:
                ssi = (SubSectionItemSinglePage) i;
                convertView = inflateSubSectionView(convertView, parent, ssi);

                break;

            case 2:
                ei = (EntryItemSinglePage) i;
                convertView = inflateEntry(convertView, parent, ei, position);

                break;
        }

//        tvQty.setText(Integer.toString(countChgQuan));

        return convertView;

    }

    // set START
    static class ViewHolderItemSubSection {

        TextView sName;
        LinearLayout lnColorSection;

        public ViewHolderItemSubSection(View convertView, Context mContext) {

            fontThSarabunSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabun.ttf");
            fontThSarabunBoldSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabunBold.ttf");


            this.sName = (TextView) convertView.findViewById(R.id.session_purchased_product);
            this.lnColorSection = (LinearLayout) convertView.findViewById(R.id.lnColorSection);
            this.sName.setTypeface(fontThSarabunSt);
        }
    }

    static class ViewHolderItem {

        TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
        LinearLayout lnColorSection;
//        fontThSarabun = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
//        fontThSarabunBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");

        public ViewHolderItem(View convertView, Context mContext) {

            fontThSarabunSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabun.ttf");
            fontThSarabunBoldSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabunBold.ttf");

            this.sName = (TextView) convertView.findViewById(R.id.session_purchased_product);
            this.sAvail = (TextView) convertView.findViewById(R.id.session_qty_price_unit);
            this.sOnshelf = (TextView) convertView.findViewById(R.id.session_onshelf);
            this.sInstock = (TextView) convertView.findViewById(R.id.session_instock);
            this.sQty = (TextView) convertView.findViewById(R.id.session_quantity);
            this.sFoc = (TextView) convertView.findViewById(R.id.session_foc);
            this.sDis1 = (TextView) convertView.findViewById(R.id.session_discount1);
            this.sAmount = (TextView) convertView.findViewById(R.id.session_amount);
            this.lnColorSection = (LinearLayout) convertView.findViewById(R.id.lnColorSection);

            this.sName.setTypeface(fontThSarabunSt);
            this.sAvail.setTypeface(fontThSarabunSt);
            this.sOnshelf.setTypeface(fontThSarabunSt);
            this.sInstock.setTypeface(fontThSarabunSt);
            this.sQty.setTypeface(fontThSarabunSt);
            this.sFoc.setTypeface(fontThSarabunSt);
            this.sDis1.setTypeface(fontThSarabunSt);
            this.sAmount.setTypeface(fontThSarabunSt);
        }
    }

    static class ViewHolderItem2 {

        TextView tvName, tvNumber, tvAvail, price, amount;

        EditText onshelf, instock, qty, foc, dis1;

        ImageView imgPromDeal;
        ImageView imgDis;
        int ref;



        public ViewHolderItem2(View convertView2, Context mContext) {

            fontThSarabunSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabun.ttf");
            fontThSarabunBoldSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabunBold.ttf");

            this.tvName = (TextView) convertView2.findViewById(R.id.title_name);
            this.tvNumber = (TextView) convertView2.findViewById(R.id.title_number);
            this.imgPromDeal = (ImageView) convertView2.findViewById(R.id.imgPromDeal);

            this.tvAvail = (TextView) convertView2.findViewById(R.id.availQty);
            this.price = (TextView) convertView2.findViewById(R.id.price);
            this.onshelf = (EditText) convertView2.findViewById(R.id.edit_text_onshelf);
            this.instock = (EditText) convertView2.findViewById(R.id.edit_text_instock);
            this.qty = (EditText) convertView2.findViewById(R.id.edit_text_quantity);
            this.foc = (EditText) convertView2.findViewById(R.id.edit_text_foc);
            this.dis1 = (EditText) convertView2.findViewById(R.id.edit_text_discount1);
            this.imgDis = (ImageView) convertView2.findViewById(R.id.imgDis);
            this.amount = (TextView) convertView2.findViewById(R.id.tv_amount_value);

            this.tvName.setTypeface(fontThSarabunSt);
            this.tvNumber.setTypeface(fontThSarabunSt);
            this.tvAvail.setTypeface(fontThSarabunSt);
            this.price.setTypeface(fontThSarabunSt);
            this.onshelf.setTypeface(fontThSarabunSt);
            this.instock.setTypeface(fontThSarabunSt);
            this.qty.setTypeface(fontThSarabunSt);
            this.foc.setTypeface(fontThSarabunSt);
            this.dis1.setTypeface(fontThSarabunSt);
            this.amount.setTypeface(fontThSarabunSt);
        }
    }
    // set END


    private View inflateSectionView(View convertView, ViewGroup parent, SectionItemSinglePage si, String headerVisible) {
        ViewHolderItem viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.section_activity_product_hor, parent, false);
            viewHolder = new ViewHolderItem(convertView, context);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.sName.setText(si.getName());
        viewHolder.sAvail.setText(si.getAvail());
        viewHolder.sOnshelf.setText(si.getOnshelf());
        viewHolder.sInstock.setText(si.getInstock());
        viewHolder.sQty.setText(si.getQty());
        viewHolder.sFoc.setText(si.getFoc());
        viewHolder.sDis1.setText(si.getDiscount());
        viewHolder.sAmount.setText(si.getAmount());
        if (si.getColor() == "NULL") {

        } else if(si.getColor() != null){

        /*
        * Assume color of Header
        * */
             viewHolder.lnColorSection.setBackgroundColor(Color.parseColor(si.getColor()));
//            viewHolder.lnColorSection.setBackgroundColor(Color.parseColor("#000fff"));

        }

        switch (headerVisible){
            case "GONE": viewHolder.lnColorSection.setVisibility(View.GONE);
            /*
            * Assume color of Header
            * */
                lnColorSectionHeader.setBackgroundColor(Color.parseColor(si.getColor()));
//                lnColorSectionHeader.setBackgroundColor(Color.parseColor("#000fff"));

                ss_name_product.setText(si.getName());
                break;
            case "SHOW": viewHolder.lnColorSection.setVisibility(View.VISIBLE);
                break;
        }

        return convertView;
    }

    private View inflateSubSectionView(View convertView, ViewGroup parent, SubSectionItemSinglePage ssi) {
        ViewHolderItemSubSection viewHolderSub;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sub_section_activity_product_hor, parent, false);
            viewHolderSub = new ViewHolderItemSubSection(convertView, context);
            convertView.setTag(viewHolderSub);

        }
        else {
            viewHolderSub = (ViewHolderItemSubSection) convertView.getTag();
        }

        viewHolderSub.sName.setText(ssi.getName());

        if (ssi.getColor() == "NULL") {
            Log.e("test", "getView:_color:_empty " + ssi.getColor());
        } else if(ssi.getColor() != null){
            viewHolderSub.lnColorSection.setBackgroundColor(Color.parseColor(ssi.getColor()));
        }



        return convertView;
    }

    private View inflateEntry(View convertView2, ViewGroup parent, EntryItemSinglePage ei, final int position) {
        final ViewHolderItem2 viewHolder2;

        if (convertView2 == null) {
            convertView2 = LayoutInflater.from(context).inflate(R.layout.entry_activity_product_hor, parent, false);
            viewHolder2 = new ViewHolderItem2(convertView2, context);
            convertView2.setTag(viewHolder2);

        }
        else {
            viewHolder2 = (ViewHolderItem2) convertView2.getTag();
        }

        viewHolder2.ref = position;
        // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values

        if(ei != null) {


            viewHolder2.tvName.setText(ei.name);
            viewHolder2.tvNumber.setText(ei.number);
            viewHolder2.tvAvail.setText(Integer.toString(ei.availQty));
            viewHolder2.price.setText(ei.price);
            viewHolder2.onshelf.setPadding(0,0,5,0);
            viewHolder2.onshelf.setText(arrOnShelf.get(position).toString());
            viewHolder2.onshelf.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub


                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    arrOnShelf.set(viewHolder2.ref, arg0.toString());

                    // set Detail Start

                    tvFOC.setText(Integer.toString(viewHolder2.ref));

                    if((arrOnShelf.get(viewHolder2.ref).toString()).equals(arrOnShelf.get(position).toString())){

                    } else {
                         countChg++;
                    }
                    tvAmount.setText(Integer.toString(countChg));
                    // set Detail End
                }
            });

            viewHolder2.instock.setText(Double.toString(ei.instock));
            viewHolder2.instock.setPadding(0,0,5,0);

            if(listQuantityNew.get(position) == 0){
                viewHolder2.qty.setHint("0"); //0
                viewHolder2.qty.setText("");

            }else {
                viewHolder2.qty.setText(listQuantityNew.get(position).toString());
                // countChgQuan++;

            }

            viewHolder2.qty.setPadding(0,0,5,0);
//            viewHolder2.qty.addTextChangedListener(new TextWatcher() {
//
//                @Override
//                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                    // TODO Auto-generated method stub
//
//                }
//
//                @Override
//                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                              int arg3) {
//                    // TODO Auto-generated method stub
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable arg0) {
//                    // TODO Auto-generated method stub
//
//                    if(arg0.toString().trim().length() != 0){
//
//                        String getprodCode = arrProductCode.get(viewHolder2.ref).toString();
//
//                        Log.e("getprod", "get_prod_zero_no: " + getprodCode);
//
//                        int value = Integer.parseInt(arg0.toString());
//                        saveToDB2(context, newIdSV, viewHolder2.ref, arg0.toString());
//                        listQuantityNew.set(viewHolder2.ref, value);
//                        getValToDB = new GetValueToEditText(context, newIdSV);
//                        showQuantity();
//                    }
//                    else if(arg0.toString().trim().length()==0){
//
//                        viewHolder2.qty.setHint("0");
//                        listQuantityNew.set(viewHolder2.ref, 0);
//                        String getprodCode = arrProductCode.get(viewHolder2.ref).toString();
//
//                        Log.e("getprod", "get_prod_zero_yes: " + getprodCode);
//                        getValToDB = new GetValueToEditText(context, newIdSV, getprodCode, "delete");
//                        getValToDB = new GetValueToEditText(context, newIdSV);
//                        showQuantity();
//                    }
//
//                }
//            });

            viewHolder2.foc.setText(Integer.toString(ei.foc));
            viewHolder2.foc.setPadding(0,0,5,0);

            viewHolder2.dis1.setText(Double.toString(ei.discount1));
            viewHolder2.dis1.setPadding(0,0,5,0);

            viewHolder2.amount.setText(Double.toString(ei.amount));

            viewHolder2.tvName.setTag(ei.name);

            viewHolder2.amount.setText("0");

        }

        return convertView2;

    } // end // inflateEntry

    void saveToDB2(Context context, int newIdSV, int ref, String arg0){

                String getprodCode = arrProductCode.get(ref).toString();
                int getQuan = Integer.parseInt(arg0); //listQuantityNew.get(i);
                getValToDB = new GetValueToEditText(context, newIdSV, getprodCode, getQuan, 0, 0, 0,
                                                    0,0,0,0,0,0);

    }

    void showQuantity(){
        getOrderTempSize = getValToDB.orderTempList.size();
        tvQty.setText(Integer.toString(getOrderTempSize));
    }
}
