package com.salesforce.android.restsample.Tablet.StartVisit.Feedback;

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

import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.GetValueToEditText;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.EntryItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SectionItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.SubSectionItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.Watcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pannikar on 6/20/16.
 */
public class EntryAdapterSinglePage5Feedback extends ArrayAdapter<ItemSinglePage> {

    private Context context;
    private ArrayList<ItemSinglePage> items;
    private LayoutInflater vi;

//    private ArrayList arrOnShelf = new ArrayList();
    private ArrayList<Integer> arrOld = new ArrayList<Integer>();
//    List<Integer> listQuantity = new ArrayList<Integer>();
//    List<Integer> listQuantityNew = new ArrayList<Integer>();

    List<Integer> listNewFeedback = new ArrayList<Integer>();

    List<String> listHeader = new ArrayList<String>();

    ImageView img;

//    Typeface fontThSarabun, fontThSarabunBold;
    static Typeface fontThSarabunSt, fontThSarabunBoldSt;
    ItemSinglePage i;
    SectionItemSinglePageFeedback si;
    ItemSinglePageFeedback ei;
    SubSectionItemSinglePageFeedback ssi;
    private LayoutInflater inflater;
    int positionIndex;
    int countChg = 0;
    int getOrderTempSize = 0;

    ArrayList arrProductCode = new ArrayList();

    GetValueToEditText getValToDB;
    int newIdSV;

    LinearLayout lnColorSectionHeader;
    int firstVisible;
    int indexHeaderVisible;


    HashMap<Integer, String> mapALLIndPrdName =new HashMap<Integer, String>();
    HashMap<Integer, String> mapALLIndColor =new HashMap<Integer, String>();

    int countClickFdbk=0;

    int indRef = 0;

    public EntryAdapterSinglePage5Feedback(Context context,
                                           ArrayList<ItemSinglePage> items,
                                           ArrayList arrProductCode,
                                           int newIdSV,
                                           List<Integer> listNewFeedbackNew) {

        super(context,0 , items);

        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        this.arrProductCode = arrProductCode;
        this.newIdSV = newIdSV;

        this.listNewFeedback = listNewFeedbackNew;

        Log.e("test", "getView:_position:_0:_listNewFeedback_: " + listNewFeedback.size());
    }

    @Override
    public int getCount() {
        // return arrOnShelf.size();
        //return sngle;
        return items.size();
    }


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

        fontThSarabunSt = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBoldSt = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");

        indRef = position;

        // set initail for FeedBack

        i = items.get(position);

        this.positionIndex = position;

        firstVisible = ((ListView) parent).getFirstVisiblePosition();

        int viewType = getItemViewType(position);
        switch (viewType) {

            case 0:

                String headerVisible = null;

                indexHeaderVisible = position;

                Log.e("test", "what in-first+position:_" + position);

                    si = (SectionItemSinglePageFeedback) i;
                    convertView = inflateSectionView(convertView, parent, si, headerVisible);

                break;

            case 1:
                ssi = (SubSectionItemSinglePageFeedback) i;
                convertView = inflateSubSectionView(convertView, parent, ssi);

                break;

            case 2:
                ei = (ItemSinglePageFeedback) i;
                convertView = inflateEntry(convertView, parent, ei, position);

//                listNewFeedback.add(position, 0);
                break;
        }

        return convertView;

    }

    /*
    *
    *  ViewHolder 1-2-3
    *  + Section
    *  + SubSection
    *  + EntryItem
    *
    * */

    // set START
    static class ViewHolderItemSection {

        TextView sName;
        LinearLayout lnColorSection;

        public ViewHolderItemSection(View convertView, Context mContext) {

            fontThSarabunSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabun.ttf");
            fontThSarabunBoldSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabunBold.ttf");

            this.sName = (TextView) convertView.findViewById(R.id.session_purchased_product);
            this.lnColorSection = (LinearLayout) convertView.findViewById(R.id.lnColorSection);

            this.sName.setTypeface(fontThSarabunSt);
        }
    }

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

    static class ViewHolderItemEntry {

        LinearLayout lnFeedback;
        TextView tvName, tvNumber;

        ImageView imgPromDeal;
        ImageView imgDis;
        int ref;

        public ViewHolderItemEntry(View convertView2, Context mContext) {

            fontThSarabunSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabun.ttf");
            fontThSarabunBoldSt = Typeface.createFromAsset(mContext.getAssets(), "fonts/THSarabunBold.ttf");

            this.lnFeedback = (LinearLayout) convertView2.findViewById(R.id.lnFeedback);

            this.tvName = (TextView) convertView2.findViewById(R.id.title_name);
            this.tvNumber = (TextView) convertView2.findViewById(R.id.title_number);
            this.imgPromDeal = (ImageView) convertView2.findViewById(R.id.imgPromDeal);

//            this.imgDis = (ImageView) convertView2.findViewById(R.id.imgDis);

            this.tvName.setTypeface(fontThSarabunSt);
            this.tvNumber.setTypeface(fontThSarabunSt);
        }
    }
    // set END


        /*
        *
        *  inflateView 1-2-3
        *  + Section
        *  + SubSection
        *  + EntryItem
        *
        * */

    private View inflateSectionView(View convertView, ViewGroup parent, SectionItemSinglePageFeedback si, String headerVisible) {
        ViewHolderItemSection viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fdb_section_activity_product_hor, parent, false);
            viewHolder = new ViewHolderItemSection(convertView, context);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolderItemSection) convertView.getTag();
        }

        viewHolder.sName.setText(si.getName());

        if (si.getColor() == "NULL") {

        } else if(si.getColor() != null){

        /*
        * Assume color of Header
        * */
             viewHolder.lnColorSection.setBackgroundColor(Color.parseColor(si.getColor()));
//            viewHolder.lnColorSection.setBackgroundColor(Color.parseColor("#000fff"));

        }

        return convertView;
    }

    private View inflateSubSectionView(View convertView, ViewGroup parent, SubSectionItemSinglePageFeedback ssi) {
        ViewHolderItemSubSection viewHolderSub;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fdb_sub_section_activity_product_hor, parent, false);
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

    private View inflateEntry(View convertView2, ViewGroup parent, ItemSinglePageFeedback ei, final int position) {
        final ViewHolderItemEntry viewHolder2;

        if (convertView2 == null) {
            convertView2 = LayoutInflater.from(context).inflate(R.layout.fdb_entry_activity_product_hor, parent, false);
            viewHolder2 = new ViewHolderItemEntry(convertView2, context);
            convertView2.setTag(viewHolder2);

        }
        else {
            viewHolder2 = (ViewHolderItemEntry) convertView2.getTag();
        }



                viewHolder2.ref = position;

        Log.e("test", "getView:_click_:position:_"+ position);
        Log.e("test", "getView:_click_:ref:_1: "+ viewHolder2.ref);

        // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values

        if(ei != null) {


            viewHolder2.tvName.setText(ei.name);
            viewHolder2.tvNumber.setText(ei.number);

            viewHolder2.tvName.setTag(ei.name);

            /*
            * set View Feedback
            *
            *
            * */
            int chkFdbk = listNewFeedback.get(position);
            switch (chkFdbk){
                case 0: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_1_visit_white));
                    break;
                case 1: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click1));
                    break;
                case 2: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click2));
                    break;
                case 3: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click3));
                    break;
                default: countClickFdbk = 0 ;
                    break;

            }

            Log.e("test", "getView:_click_ref:_"+viewHolder2.ref + " get:_" + listNewFeedback.get(viewHolder2.ref));

            viewHolder2.lnFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("test", "getView:_click");

//                    countClickFdbk++;
                    int cfb = listNewFeedback.get(position)+1;
                    if (cfb > 3){
                        cfb = 0 ;
                    }

                    listNewFeedback.set(viewHolder2.ref, cfb);
                    Log.e("test", "getView:_click:_:position:_2: "+ position);
                    Log.e("test", "getView:_click:_:ref:_2: "+ viewHolder2.ref);
                    Log.e("test", "getView:_click:_:countClickFdbk:_2: "+ cfb);

                    switch (cfb){

                        case 0: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_1_visit_white));
                            break;
                        case 1: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click1));
                            break;
                        case 2: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click2));
                            break;
                        case 3: viewHolder2.imgPromDeal.setImageDrawable(getContext().getResources().getDrawable(R.drawable.click3));
                            break;
                    }
                }
            });

        }

        return convertView2;

    } // end // inflateEntry
}
