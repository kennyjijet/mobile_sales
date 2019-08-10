//package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.salesforce.android.restsample.R;
//
//import java.util.ArrayList;
//
///**
// * Created by chukeatboontae on 6/20/16.
// */
//public class EntryAdapterSinglePage4 extends ArrayAdapter<ItemSinglePage> {
//
//    private Context context;
//    private ArrayList<ItemSinglePage> items;
//    private LayoutInflater vi;
////    View convertView1,
//   View convertView2;
//    View convertViewSend;
////
////    TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
////    LinearLayout lnBg;
//
////    TextView tvName, tvNumber, tvAvail, price;
////
////    EditText onshelf, instock, qty, foc, dis1;
//
//    ImageView img;
//
//    Typeface fontThSarabun, fontThSarabunBold;
//    ItemSinglePage i;
//    EntryItemSinglePage ei;
//    private LayoutInflater inflater;
//    private int resource;
//    ViewHolderItem viewHolder;
//    ViewHolderItem2 viewHolder2;
//    SectionItemSinglePage data[] = null;
//
//    public EntryAdapterSinglePage4(Context context, ArrayList<ItemSinglePage> items) {
//        super(context,0 , items);
//        this.context = context;
//        this.items = items;
////        this.resource = resourceId;
//        inflater = LayoutInflater.from(context);
//        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    static class ViewHolderItem {
//
//        TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
//
////        TextView tvName, tvNumber, tvAvail, price, amount;
////
////        EditText onshelf, instock, qty, foc, dis1;
//    }
//
//    static class ViewHolderItem2 {
//
////        TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
//
//        TextView tvName, tvNumber, tvAvail, price, amount;
//
//        EditText onshelf, instock, qty, foc, dis1;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
////        View v = convertView;
//        convertView2 = convertView;
//        Log.e("test", "getView:_position:_0: " + position);
//        fontThSarabun = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabun.ttf");
//        fontThSarabunBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/THSarabunBold.ttf");
//
//        i = items.get(position);
//
//        int bb = 0;
//        if(i.isSection()){
//            bb = 1;
//
//        } else {
//            bb = 2;
//        }
//        switch (bb){
//            case 1: // section
//
//                SectionItemSinglePage si = (SectionItemSinglePage) i;
//
//                Log.e("test", "getView:_position:_1: " + position);
//
//                if(convertView==null){
//
//                    Log.e("test", "getView:_position:_2: " + position);
//                    convertView = vi.inflate(R.layout.section_activity_product_hor, parent, false);
//
//                    // inflate the layout
//
//                    Log.e("test", "getView:_position:_3: " + position);
//
//                    // well set up the ViewHolder
//                    viewHolder = new ViewHolderItem();
//
//                    //sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
//                    viewHolder.sName = (TextView) convertView.findViewById(R.id.session_purchased_product);
//                    viewHolder.sAvail = (TextView) convertView.findViewById(R.id.session_qty_price_unit);
//                    viewHolder.sOnshelf = (TextView) convertView.findViewById(R.id.session_onshelf);
//                    viewHolder.sInstock = (TextView) convertView.findViewById(R.id.session_instock);
//                    viewHolder.sQty = (TextView) convertView.findViewById(R.id.session_quantity);
//                    viewHolder.sFoc = (TextView) convertView.findViewById(R.id.session_foc);
//                    viewHolder.sDis1 = (TextView) convertView.findViewById(R.id.session_discount1);
//                    viewHolder.sAmount = (TextView) convertView.findViewById(R.id.session_amount);
//
//                    // store the holder with the view.
//                    convertView.setTag(viewHolder);
//
//                }else{
//                    // we've just avoided calling findViewById() on resource everytime
//                    // just use the viewHolder
//
//                    Log.e("test", "getView:_position:_4: " + position);
//
//                    viewHolder = ((ViewHolderItem) convertView.getTag() ); // HERE!!!
//                }
//
//                // object item based on the position
//                Log.e("test", "getView:_position:_5: " + position);
//
//                // assign values if the object is not null
//                if(si != null) {
//
//                    Log.e("test", "getView:_position:_6: " + position);
//
//                    // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
//                    viewHolder.sName.setText(si.getName());
//                    viewHolder.sAvail.setText(si.getAvail());
//                    viewHolder.sOnshelf.setText(si.getOnshelf());
//                    viewHolder.sInstock.setText(si.getInstock());
//                    viewHolder.sQty.setText(si.getQty());
//                    viewHolder.sFoc.setText(si.getFoc());
//                    viewHolder.sDis1.setText(si.getDiscount());
//                    viewHolder.sAmount.setText(si.getAmount());
//
//                    viewHolder.sName.setTag(si.getName());
//                }
//
//                Log.e("test", "getView:_position:_7: " + position);
//
//                // set Detail END
////                break;
//                convertViewSend = convertView;
//            return convertView;
//
//
//            case 2: // entry
//
////                ViewHolderItem2 viewHolder2;
//
//                Log.e("test", "getView:_position:-0: " + position);
//
//                ei = (EntryItemSinglePage) i;
//
//                Log.e("test", "getView:_position:-1: " + position);
//
//                // set Detail START #ENTRY
//
//                Log.e("test", "getView:_position:-2: " + position);
//
//                if(convertView2 == null){
//
//                    Log.e("test", "getView:_position:-3: " + position);
//
//                    convertView2 = vi.inflate(R.layout.entry_activity_product_hor, parent, false);
//
//                    // inflate the layout
//
//                    Log.e("test", "getView:_position:-4: " + position);
//
//                    // well set up the ViewHolder
//                    viewHolder2 = new ViewHolderItem2();
//
//                    Log.e("test", "getView:_position:-4-1: " + position);
////                    viewHolder.textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
//
//                    viewHolder2.tvName = (TextView) convertView2.findViewById(R.id.title_name);
//                    viewHolder2.tvNumber = (TextView) convertView2.findViewById(R.id.title_number);
//                    viewHolder2.tvAvail = (TextView) convertView2.findViewById(R.id.availQty);
//                    viewHolder2.price = (TextView) convertView2.findViewById(R.id.price);
//                    viewHolder2.onshelf = (EditText) convertView2.findViewById(R.id.edit_text_onshelf);
//                    viewHolder2.instock = (EditText) convertView2.findViewById(R.id.edit_text_instock);
//                    viewHolder2.qty = (EditText) convertView2.findViewById(R.id.edit_text_quantity);
//                    viewHolder2.foc = (EditText) convertView2.findViewById(R.id.edit_text_foc);
//                    viewHolder2.dis1 = (EditText) convertView2.findViewById(R.id.edit_text_discount1);
////                    img = (ImageView) convertView.findViewById(R.id.img);
//                    viewHolder2.amount = (TextView) convertView2.findViewById(R.id.tv_amount_value);
//
//                    // store the holder with the view.
//                    convertView2.setTag(viewHolder2);
//
//                    Log.e("test", "getView:_position:-5: " + position);
//
//                }else{
//                    // we've just avoided calling findViewById() on resource everytime
//                    // just use the viewHolder
//
//                    Log.e("test", "getView:_position:-6: " + position);
//
//                    viewHolder2 = (ViewHolderItem2) convertView2.getTag();
//                }
//
//                // object item based on the position
//                // ObjectItem objectItem = data[position];
////                SectionItemSinglePage objectItem = data[position];
//
//                Log.e("test", "getView:_position:-7: " + position);
//
//                // assign values if the object is not null
//                if(ei != null) {
//
//                    Log.e("test", "getView:_position:-8: " + position);
//
//                    // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
//                    viewHolder2.tvName.setText(ei.name);
//                    viewHolder2.tvNumber.setText(ei.number);
//                    viewHolder2.tvAvail.setText(Integer.toString(ei.availQty));
//                    viewHolder2.price.setText(ei.price);
//
//                    viewHolder2.onshelf.setText(Double.toString(ei.onshelf));
//                    viewHolder2.onshelf.setPadding(0,0,5,0);
//
//                    viewHolder2.instock.setText(Double.toString(ei.instock));
//                    viewHolder2.instock.setPadding(0,0,5,0);
//
//                    viewHolder2.qty.setText(Integer.toString(ei.qty));
//                    viewHolder2.qty.setPadding(0,0,5,0);
//
//                    viewHolder2.foc.setText(Integer.toString(ei.foc));
//                    viewHolder2.foc.setPadding(0,0,5,0);
//
//                    viewHolder2.dis1.setText(Double.toString(ei.discount1));
//                    viewHolder2.dis1.setPadding(0,0,5,0);
////                    viewHolder.qty.setText(Integer.toString(ei.qty));
//                    viewHolder2.amount.setText(Double.toString(ei.amount));
//
//                    viewHolder2.tvName.setTag(ei.name);
//
//                    viewHolder2.amount.setText("0");
//
//                }
//
//                Log.e("test", "getView:_position:-9: " + position);
//
//                // set Detail END
//
//
////                convertViewSend = convertView2;
////                return convertView2;
//                break;
//        }
//
//
////        if(i.isSection()){
////            convertViewSend = convertView;
////
////            if(convertView == null){
////                Log.e("test", "getView:_position:_chk1: + null");
////            } else {
////                Log.e("test", "getView:_position:_chk1: + hve");
////            }
////
////        } else {
////            convertViewSend = convertView2;
////            Log.e("test", "getView:_position:_chk2: " + position);
////            if(convertViewSend == null){
////                Log.e("test", "getView:_position:_chk2: + null");
////            } else {
////                Log.e("test", "getView:_position:_chk2: + hve");
////            }
////        }
////        return convertViewSend;
//        return convertView2;
//
//    }
//}
