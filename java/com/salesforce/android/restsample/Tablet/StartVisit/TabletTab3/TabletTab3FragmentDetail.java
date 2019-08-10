package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBMarket.MarketDatabaseHelper;
import com.salesforce.android.restsample.Library.GenUUID;
import com.salesforce.android.restsample.Library.ImageConverter;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletPickListSingleItem;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab3FragmentDetail extends Fragment implements View.OnClickListener,
        TabletPickListSingleItem.CommunicatorFragmentPickList{
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set detail
    private static final int PICK_FROM_GALLERY = 2;
    Bitmap photo;

    String newId;
    int newIdSV;

    String setCompOf = "None";
    String setCompBrand = "None";
    String setProName = "";
    String setRetPrice = "";
    String setWholePrice = "";
    String setActivity = "None";
    String setDisLocation = "None";
    String setPop = "None";
    String setPriority = "None";

    TextView tvCompOf;
    TextView tvCompBrand;
    EditText edProName;
    EditText edRetPrice;
    EditText edWholePrice;
    TextView tvActivity;
    TextView tvDisLocation;
    TextView tvPop;
    TextView tvPriority;

    MarketDatabaseHelper databaseMarket;

    Double dRetPrice;
    Double dWholePrice;
    String idComp;
    String mrkImg="";
    String setFragmentParent;


    //String idMarket;
    int idMarket = 1;
    String setId;
    int idCharSum01, idChar05, idChar06, idChar07;
    int idCharSum02, idChar08, idChar09, idChar10;
    int idCharSum03, idChar11, idChar12, idChar13;
    String idAscii11, idAscii12, idAscii13;
    String idALL;

    ImageView circularImageViewBefore1;

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

        View view = inflater.inflate(R.layout.detail_tab_3competitor_detail, container, false);

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");


        // Initialize Views
        setLayout(view);

//        communicatorV4.setNameAtTextView();

//        tvgoBack.setOnClickListener(this);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);

        // set detail android

        setFragmentParent = "Tab3";

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.photon);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 30);
        circularImageViewBefore1 = (ImageView)v.findViewById(R.id.imgPhoto);
        circularImageViewBefore1.setImageBitmap(circularBitmap);

//        Bundle extras = getIntent().getExtras();
//        newId= extras.getString("getId");
//        newIdSV = extras.getInt("getIdSV");
//        Log.e("OrderDeliveryActivity", "Tab2FragmentALL3: Id: " + newId);

        databaseMarket = new MarketDatabaseHelper(getActivity());

        // for this Activity
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        TextView tvCancel = (TextView) v.findViewById(R.id.tvgoCancel);
        TextView tvToolbar = (TextView) v.findViewById(R.id.toolbar_title);
        TextView tvDone = (TextView) v.findViewById(R.id.toolbar_done);
        tvCancel.setTypeface(fontThSarabunBold);
        tvToolbar.setTypeface(fontThSarabunBold);
        tvDone.setTypeface(fontThSarabunBold);

        TextView tt00 = (TextView) v.findViewById(R.id.title00);
        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);
        TextView tt08 = (TextView) v.findViewById(R.id.title08);
        TextView tt09 = (TextView) v.findViewById(R.id.title09);

        tt00.setTypeface(fontThSarabunBold);
        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabun);
        tt03.setTypeface(fontThSarabun);
        tt04.setTypeface(fontThSarabun);
        tt05.setTypeface(fontThSarabun);
        tt06.setTypeface(fontThSarabun);
        tt07.setTypeface(fontThSarabun);
        tt08.setTypeface(fontThSarabun);
        tt09.setTypeface(fontThSarabun);

        tvCompOf = (TextView) v.findViewById(R.id.tvCompOf);
        tvCompBrand = (TextView) v.findViewById(R.id.tvCompBrand);
        edProName = (EditText) v.findViewById(R.id.edProName); //edRetPrice
        edRetPrice = (EditText) v.findViewById(R.id.edRetPrice);
        edWholePrice = (EditText) v.findViewById(R.id.edWholePrice);
        tvActivity = (TextView) v.findViewById(R.id.tvActivity);
        tvDisLocation = (TextView) v.findViewById(R.id.tvDisLocation);
        tvPop = (TextView) v.findViewById(R.id.tvPop);
        tvPriority = (TextView) v.findViewById(R.id.tvPriority);

        tvCompOf.setTypeface(fontThSarabun);
        tvCompBrand.setTypeface(fontThSarabun);
        edProName.setTypeface(fontThSarabun);
        edRetPrice.setTypeface(fontThSarabun);
        edWholePrice.setTypeface(fontThSarabun);
        tvActivity.setTypeface(fontThSarabun);
        tvDisLocation.setTypeface(fontThSarabun);
        tvPop.setTypeface(fontThSarabun);
        tvPriority.setTypeface(fontThSarabun);

        tvCompOf.setText(setCompOf);
        tvCompBrand.setText(setCompBrand);
        edProName.setText(setProName);
        edRetPrice.setText(setRetPrice);
        edWholePrice.setText(setWholePrice);
        tvActivity.setText(setActivity);
        tvDisLocation.setText(setDisLocation);
        tvPop.setText(setPop);
        tvPriority.setText(setPriority);

        edProName.setFocusable(false);
        edRetPrice.setFocusable(false);
        edWholePrice.setFocusable(false);


        edProName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edProName.setFocusableInTouchMode(true);

                return false;
            }
        });

        edRetPrice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edRetPrice.setFocusableInTouchMode(true);

                return false;
            }
        });

        edWholePrice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edWholePrice.setFocusableInTouchMode(true);

                return false;
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Tab3Fragment fragment3 = new Tab3Fragment();
//
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                Intent i = new Intent(Tab3FragmentDetail.this, CustomViewIconTextTabsActivity.class);
//
////                getFragmentManager().beginTransaction().add().commit();
//
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "3");
//                startActivity(i);
            }
        });

//        idMarket = genUUID("m0013");

        tvDone.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                setProName = edProName.getText().toString();
                setRetPrice = edRetPrice.getText().toString();
                setWholePrice = edWholePrice.getText().toString();

                if(setRetPrice.equals("")){

                    dRetPrice = (double) 0;
                    dWholePrice = (double) 0;

                } else {
                    dRetPrice = Double.parseDouble(setRetPrice);
                    dWholePrice = Double.parseDouble(setWholePrice);
                }

                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_1: " + setCompOf);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_2: " + setCompBrand);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_3: " + setProName);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_4: " + dRetPrice);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_5: " + dWholePrice);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_6: " + setActivity);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_7: " + setDisLocation);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_8: " + setPop);
                Log.e("OrderDeliveryActivity", "Tab3FragmentDetail_9: " + setPriority);

                String remark="";
                String idServer="";
                databaseMarket.addMarket(  newIdSV, idComp, setProName,
                        dRetPrice, dWholePrice, setActivity,
                        setDisLocation, setPop,
                        setPriority, setCompOf, mrkImg,
                        remark, idServer);

//                Intent i = new Intent(Tab3FragmentDetail.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "3");
//                startActivity(i);

            }
        });

        tvCompOf.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "1CompOf");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 1);

                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "1CompOf");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        tvCompBrand.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "2CompBrd");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 2);
                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "2CompBrd");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        tvActivity.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "6Activity");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 3);
                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "6Activity");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);

            }
        });

        tvDisLocation.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "7DisLocation");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 4);
                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "7DisLocation");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        tvPop.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "8Pop");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 5);
                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "8Pop");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        tvPriority.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab3FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                i.putExtra("CheckPickList", "9Priority");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 6);
                Fragment frg = new TabletPickListSingleItem();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putString("Check", "1");
                args.putString("CheckPickList", "9Priority");
                args.putString("CheckFrgmtParent", setFragmentParent);
                frg.setArguments(args);
                communicatorV4.MessageTab(1,frg);
            }
        });

        final ImageView temp = (ImageView) circularImageViewBefore1;

        circularImageViewBefore1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (circularImageViewBefore1.getDrawable().getConstantState().equals(temp.getDrawable().getConstantState())){
                    final String[] option = new String[] {"Take from Camera", "Select from Gallery"};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, option);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle("Select Option");
                    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Log.e("Selected Item", String.valueOf(which));

                            if (which == 0) {
                                callCamera();
                            }
                            if (which == 1) {
                                callGallery();
                            }

                        }
                    });

                    final AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    final String[] option = new String[] { "View", "Take from Camera", "Select from Gallery" };
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, option);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle("Select Option");
                    builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Log.e("Selected Item", String.valueOf(which));

                            if (which == 0) {
                                viewImage();
                            }
                            if (which == 1) {
                                callCamera();
                            }
                            if (which == 2) {
                                callGallery();
                            }

                        }
                    });

                    final AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

    @Override
    public void MessagePickList(int where, String setTextView) {
        setNameAtTextView(where, setTextView);
    }

//    public interface CommunicatorFrgDetail {
////        public void MessageTab3FrgDetail(String OS_Name);
//        public void setNameAtTextViewMain(int where, String setTextView);
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                //updateFragment("Back");
                break;
        }
    }

//    private void updateFragment(String OS_Name) {
//        communicatorV4.MessageTab3FrgDetail(OS_Name);
//        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
//    }

    // set detail 1

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (!(data == null || data.getExtras().get("data") == null)){
            photo = (Bitmap) data.getExtras().get("data");
            circularImageViewBefore1.setImageBitmap(photo);
            Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_AFTER");
        }

        switch (requestCode){
            case 1:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(1, result);
                    setIdSV(idSV);
                }
                break;
            case 2:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    idComp = data.getStringExtra("getCompId");
                    Log.e("OrderDeliveryActivity", "setNameAtTextView_result:_ " + result);
//                    tvCompBrand.setText(setCompBrand);
                    setNameAtTextView(2, result);
                    setIdSV(idSV);
                }
                break;
            case 3:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(3, result);
                    setIdSV(idSV);
                }
                break;
            case 4:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(4, result);
                    setIdSV(idSV);
                }
                break;
            case 5:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(5, result);
                    setIdSV(idSV);
                }
                break;
            case 6:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(6, result);
                    setIdSV(idSV);
                }
                break;
        }

//        tvCompOf.setText(setCompOf);
//        tvCompBrand.setText(setCompBrand);
//        edProName.setText(setProName);
//        edRetPrice.setText(setRetPrice);
//        edWholePrice.setText(setWholePrice);
//        tvActivity.setText(setActivity);
//        tvDisLocation.setText(setDisLocation);
//        tvPop.setText(setPop);
//        tvPriority.setText(setPriority);


    }//onActivityResult

    void setNameAtTextView(int where, String setTextView){
        switch(where){
            case 1: setCompOf = setTextView;
                tvCompOf.setText(setCompOf);
                break;
            case 2: setCompBrand= setTextView;
                tvCompBrand.setText(setCompBrand);
                break;
            case 3: setActivity = setTextView;
                tvActivity.setText(setActivity);
                break;
            case 4: setDisLocation = setTextView;
                tvDisLocation.setText(setDisLocation);
                break;
            case 5: setPop = setTextView;
                tvPop.setText(setPop);
                break;
            case 6: setPriority = setTextView;
                tvPriority.setText(setPriority);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    void setIdSV(int idSV){
        newIdSV = idSV;
    }

    // set detail 2
    // gen UUID Start
    String genUUID(String header){
        //  Gen UUID Start
        GenUUID hGenid = GenUUID.getInstance();
        idALL = hGenid.getId();

        // #Char05+06+07
        idChar05 = Character.getNumericValue(idALL.charAt(5));
        idChar06 = Character.getNumericValue(idALL.charAt(6));
        idChar07 = Character.getNumericValue(idALL.charAt(7));
        idAscii11 = getCharForNumber(idChar05 + idChar06);
        Log.e(" Check_getTime_BEFORE", "_idAscii11:_ " + idAscii11);

        // #Char08+09+10
        idChar08 = Character.getNumericValue(idALL.charAt(8));
        idChar09 = Character.getNumericValue(idALL.charAt(9));
        idChar10 = Character.getNumericValue(idALL.charAt(10));
        idAscii12 = getCharForNumber(idChar08 + idChar09 + idChar10);
        Log.e(" Check_getTime_BEFORE", "_idAscii12:_ " + idAscii12);

        // #Char11+12+13
        idChar11 = Character.getNumericValue(idALL.charAt(11));
        idChar12 = Character.getNumericValue(idALL.charAt(12));
        idChar13 = Character.getNumericValue(idALL.charAt(13));
        idAscii13 = getCharForNumber(idChar11 + idChar12 + idChar13);
        Log.e(" Check_getTime_BEFORE", "_idAscii13:_ " + idAscii13);

        setId = header + idALL + idAscii11 + idAscii12 + idAscii13; //here

        Log.e(" Check_getTime_BEFORE", "_idAscii:_id_ " + setId);

        // Gen UUID End

        return setId;
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
    // gen UUID End

    /**
     * open camera method
     */
    public void callCamera() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_BEFORE");
//        getActivity().
        startActivityForResult(cameraIntent, 1888);

    }

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult( Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }

    public void viewImage() {
//        Intent i = new Intent(Tab3FragmentDetail.this, Tab3FragmentShowImage.class);
//
//        startActivity(i);

    }
}
