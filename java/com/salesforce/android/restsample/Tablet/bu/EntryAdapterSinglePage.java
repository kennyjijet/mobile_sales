//package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.salesforce.android.restsample.R;
//
//import java.util.ArrayList;
//
///**
// * Created by chukeatboontae on 6/20/16.
// */
//public class EntryAdapterSinglePage extends ArrayAdapter<ItemSinglePage> {
//
//    private Context context;
//    private ArrayList<ItemSinglePage> items;
//    private LayoutInflater vi;
//
//    TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
//    LinearLayout lnBg;
//
//    TextView tvName, tvNumber, tvAvail, price;
//
//    EditText onshelf, instock, qty, foc, dis1;
//
//    ImageView img;
//    TextView amount;
//
//    Typeface fontThSarabun, fontThSarabunBold;
//    ItemSinglePage i;
//    EntryItemSinglePage ei;
//    private LayoutInflater inflater;
//    private int resource;
//
//    public EntryAdapterSinglePage(Context context, ArrayList<ItemSinglePage> items) {
//        super(context,0 , items);
//        this.context = context;
//        this.items = items;
////        this.resource = resourceId;
//        inflater = LayoutInflater.from(context);
//        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//
//        Log.e("test", "getView:_position:_ " + position);
//        fontThSarabun = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
//        fontThSarabunBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");
//
//
//        i = items.get(position);
//        if (i != null) {
//            if(i.isSection()){
//                SectionItemSinglePage si = (SectionItemSinglePage) i;
////                v = vi.inflate(R.layout.section_activity_products, null);
//                v = vi.inflate(R.layout.section_activity_product_hor, null);
//
//                v.setOnClickListener(null);
//                v.setOnLongClickListener(null);
//                v.setLongClickable(false);
//
//                sName = (TextView) v.findViewById(R.id.session_purchased_product);
//                sAvail = (TextView) v.findViewById(R.id.session_qty_price_unit);
//                sOnshelf = (TextView) v.findViewById(R.id.session_onshelf);
//                sInstock = (TextView) v.findViewById(R.id.session_instock);
//                sQty = (TextView) v.findViewById(R.id.session_quantity);
//                sFoc = (TextView) v.findViewById(R.id.session_foc);
//                sDis1 = (TextView) v.findViewById(R.id.session_discount1);
//                sAmount = (TextView) v.findViewById(R.id.session_amount);
//                lnBg = (LinearLayout) v.findViewById(R.id.lnbg);
//
//                sName.setTypeface(fontThSarabunBold);
//                sAvail.setTypeface(fontThSarabunBold);
//                sOnshelf.setTypeface(fontThSarabunBold);
//                sInstock.setTypeface(fontThSarabunBold);
//                sQty.setTypeface(fontThSarabunBold);
//                sFoc.setTypeface(fontThSarabunBold);
//                sDis1.setTypeface(fontThSarabunBold);
//                sAmount.setTypeface(fontThSarabunBold);
//
//
//                if (sName != null)
//                    sName.setText(si.getName());
//
//
//                if (si.getAvail() != null){
//                    if (si.getColor() != null){
//                        Log.e("test", "getView:_position:_color_in_1_: " + position);
////                        Log.e("chkColor","checkcolor_in:_"+si.getColor());
//                        lnBg.setBackgroundColor(Color.parseColor(si.getColor()));
//                    }else {
//                        Log.e("test", "getView:_position:_color_in_2_: " + position);
////                        Log.e("chkColor","checkcolor_in:_"+"#ffffff");
//                    }
//                }else {
//                    String code = "#ececec";
//                    String textCode = "#1b1b1b";
//                    sName.setTextColor(Color.parseColor(textCode));
//                    sName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//                    lnBg.setBackgroundColor(Color.parseColor(code));
//                }
//
//                if (sAvail != null)
//                    sAvail.setText(si.getAvail());
//
//                if (sOnshelf != null)
//                    sOnshelf.setText(si.getOnshelf());
//
//                if (sInstock != null)
//                    sInstock.setText(si.getInstock());
//
//                if (sQty != null)
//                    sQty.setText(si.getQty());
//
//                if (sFoc != null)
//                    sFoc.setText(si.getFoc());
//
//                if (sDis1 != null)
//                    sDis1.setText(si.getDiscount());
//
//                if (sAmount != null)
//                    sAmount.setText(si.getAmount());
//
//
//            }else{
//                ei = (EntryItemSinglePage) i;
//
//                v = vi.inflate(R.layout.entry_activity_product_hor, null);
//
//                tvName = (TextView)v.findViewById(R.id.title_name);
//                tvNumber = (TextView)v.findViewById(R.id.title_number);
//                tvAvail = (TextView) v.findViewById(R.id.availQty);
//                price = (TextView) v.findViewById(R.id.price);
//                onshelf = (EditText) v.findViewById(R.id.edit_text_onshelf);
//                instock = (EditText) v.findViewById(R.id.edit_text_instock);
//                qty = (EditText) v.findViewById(R.id.edit_text_quantity);
//                foc = (EditText) v.findViewById(R.id.edit_text_foc);
//                dis1 = (EditText) v.findViewById(R.id.edit_text_discount1);
//                img = (ImageView) v.findViewById(R.id.img);
//                amount = (TextView) v.findViewById(R.id.tv_amount_value);
//
//                tvName.setTypeface(fontThSarabun);
//                tvNumber.setTypeface(fontThSarabun);
//                tvAvail.setTypeface(fontThSarabun);
//                price.setTypeface(fontThSarabun);
//                onshelf.setTypeface(fontThSarabun);
//                instock.setTypeface(fontThSarabun);
//                qty.setTypeface(fontThSarabun);
//                foc.setTypeface(fontThSarabun);
//                dis1.setTypeface(fontThSarabun);
//                amount.setTypeface(fontThSarabun);
//
//                if (tvName != null)
//                    tvName.setText(ei.name);
//
//                if (tvNumber != null)
//                    tvNumber.setText(ei.number);
//
//                if (tvAvail != null)
//                    tvAvail.setText(""+ei.availQty);
//
//                if (price != null)
//                    price.setText(ei.price);
//
//                if (onshelf.equals(0))
//                    onshelf.setText(""+ei.onshelf);
//                onshelf.setHint("0.0");
//                onshelf.setPadding(0,0,5,0);
//
//                if (instock.equals(0))
//                    instock.setText(""+ei.instock);
//                instock.setHint("0.0");
//                instock.setPadding(0,0,5,0);
//
//                if (qty.equals(0))
//                    qty.setText(""+ei.qty);
//                qty.setHint("0");
//                qty.setPadding(0,0,5,0);
//
//                if (foc.equals(0))
//                    foc.setText(""+ei.foc);
//                foc.setHint("0");
//                foc.setPadding(0,0,5,0);
//
//                if (dis1.equals(0))
//                    dis1.setText(""+ei.discount1);
//                dis1.setHint("0.0");
//                dis1.setPadding(0,0,5,0);
//
//                if (amount != null) {
//
//                    if (ei.amount != 0) {
//                        String str = String.format("%.2f", ei.amount);
//
//                        if (ei.amount / 1000000 > 0.99999999) {
//                            str = str.substring(0, str.length() - 9) + "," + str.substring(str.length() - 9, str.length() - 6) + "," + str.substring(str.length() - 6, str.length());
//                        } else if (ei.amount / 1000 > 0.99999999) {
//                            str = str.substring(0, str.length() - 6) + "," + str.substring(str.length() - 6, str.length());
//                        }
//                        amount.setText("" + str);
//                    }else {
//                        amount.setText("0");
//                    }
//                }
//
//
//            } // end Entry
//        }
//        return v;
//    }
//}
