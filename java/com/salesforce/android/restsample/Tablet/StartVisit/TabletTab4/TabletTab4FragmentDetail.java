package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBMerchandising.MerchandisingDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProductBrand.ProductBrandDatabaseHelper;
import com.salesforce.android.restsample.ITEMs.PickListSingleItem;
import com.salesforce.android.restsample.Library.GenUUID;
import com.salesforce.android.restsample.Library.ImageConverter;
import com.salesforce.android.restsample.MainFragment.CustomViewIconTextTabsActivity;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIcon.Tab4FragmentShowImage;
import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab4FragmentDetail extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;


    private static final int PICK_FROM_GALLERY = 2;
    Bitmap photo1;
    Bitmap photo2;
    int checkCamera = 0;

    TextView tvCreateplan;

    String newId;
    int newIdSV;

    EditText ed01;

    TextView tv01;
    TextView tv02;
    TextView tv03;
    TextView tv04;

    TextView tvCancel;
    TextView tvToolbar;
    TextView tvDone;

    String setId;
    String setBrand;
    String setDisLo;
    String setDisType;
    String setPop;
    String setRemarks;
    String setImgBef;
    String setImgAft;

    String setFragmentParent;

    MerchandisingDatabaseHelper merchandisingDBHelper;
    ProductBrandDatabaseHelper productBrdDBHelper;

    ImageView circularImageViewBefore1;
    ImageView circularImageViewBefore2;


    int idCharSum01, idChar05, idChar06, idChar07;
    int idCharSum02, idChar08, idChar09, idChar10;
    int idCharSum03, idChar11, idChar12, idChar13;
    String idAscii11, idAscii12, idAscii13;
    String idALL;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_tab_4_2merchandising_detail, container, false);
        // Initialize Views
        setLayout(view);

//        tvgoBack.setOnClickListener(this);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

//        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);

        // set Detail
        setFragmentParent = "Tab4";

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.photon);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 30);
        circularImageViewBefore1 = (ImageView)v.findViewById(R.id.imgPhoto1);
        circularImageViewBefore1.setImageBitmap(circularBitmap);

        circularImageViewBefore2 = (ImageView)v.findViewById(R.id.imgPhoto2);
        circularImageViewBefore2.setImageBitmap(circularBitmap);

//        Bundle extras = getActivity().getIntent().getExtras();
//        newId= extras.getString("getId");
//        newIdSV = extras.getInt("getIdSV");
        newId= "00128000007uGpMAAU";
        newIdSV = 2;
        Log.e("Tab2FragmentALL3", "Tab2FragmentALL3: Id: " + newId);

        tvCancel = (TextView)v.findViewById(R.id.tvgoCancel);
        tvToolbar = (TextView)v.findViewById(R.id.toolbar_title);
        tvDone = (TextView)v.findViewById(R.id.toolbar_done);
        tvCancel.setTypeface(fontThSarabunBold);
        tvToolbar.setTypeface(fontThSarabunBold);
        tvDone.setTypeface(fontThSarabunBold);

        TextView tt01 = (TextView)v.findViewById(R.id.title01); //title01
        TextView tt02 = (TextView)v.findViewById(R.id.title02);  //title02
        TextView tt03 = (TextView) v.findViewById(R.id.title03);  //title03
        TextView tt04 = (TextView) v.findViewById(R.id.title04); //title04
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tvBefore = (TextView) v.findViewById(R.id.tvBefore);  //tvBefore
        TextView tvAfter = (TextView) v.findViewById(R.id.tvAfter);  //tvAfter
        ed01 = (EditText) v.findViewById(R.id.edRemark01); //edRemark01
//        EditText ed02 = (EditText) v.findViewById(R.id.edRemark02); //edRemark02
        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabun);
        tt03.setTypeface(fontThSarabun);
        tt04.setTypeface(fontThSarabun);
        tt05.setTypeface(fontThSarabun);
        tvBefore.setTypeface(fontThSarabun);
        tvAfter.setTypeface(fontThSarabun);
        ed01.setTypeface(fontThSarabun);
        ed01.setFocusable(false);

        ed01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ed01.setFocusableInTouchMode(true);

                return false;
            }
        });

        tv01 = (TextView) v.findViewById(R.id.tvShowBrand);
        tv02 = (TextView) v.findViewById(R.id.tvShowLocation);
        tv03 = (TextView) v.findViewById(R.id.tvShowDisplayType);
        tv04 = (TextView) v.findViewById(R.id.tvShowPop);

        tv01.setTypeface(fontThSarabun);
        tv02.setTypeface(fontThSarabun);
        tv03.setTypeface(fontThSarabun);
        tv04.setTypeface(fontThSarabun);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                onBackPressed();
//                Intent i = new Intent(Tab4FragmentDetail.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "4");
//                startActivity(i);
            }
        });

        merchandisingDBHelper = new MerchandisingDatabaseHelper(getActivity());
        productBrdDBHelper = new ProductBrandDatabaseHelper(getActivity());

//        setTitle(null);
//        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
//        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
//        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
//        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
//        mTitle.setTypeface(fontThSarabunBold);

        tv01.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab4FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "4");
//                i.putExtra("CheckPickList", "1CompOf");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 41);
            }
        });

        tv02.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab4FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "4");
//                i.putExtra("CheckPickList", "7DisLocation");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 42);
            }
        });

        tv03.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab4FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "4");
//                i.putExtra("CheckPickList", "Tab4DisplayType");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 43);
            }
        });

        tv04.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(Tab4FragmentDetail.this, PickListSingleItem.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "4");
//                i.putExtra("CheckPickList", "8Pop");
//                i.putExtra("CheckFrgmtParent", setFragmentParent);
//                startActivityForResult(i, 44);
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                setRemarks = ed01.getText().toString();
                setImgBef = "None";
                setImgAft = "None";

                if(setRemarks.equals("")){
                    setRemarks = "";
                } else {
                    setRemarks = ed01.getText().toString();
                }

                String id = genUUID("m0014"); // UUID

                String idpdbr = productBrdDBHelper.getProductBrandidBySearchName(setBrand); // productBrandId

                merchandisingDBHelper.addMerchandising(id, newIdSV, idpdbr, setDisLo, setDisType, setPop,
                        setRemarks, setImgBef, setImgAft);

                Intent i = new Intent(getActivity(), CustomViewIconTextTabsActivity.class);
                i.putExtra("getId", newId);
                i.putExtra("getIdSV", newIdSV);
                i.putExtra("Check", "4");
                startActivity(i);
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
                                checkCamera = 1;
                                callCamera();
                            }
                            if (which == 1) {
                                checkCamera = 1;
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

        final ImageView temp2 = (ImageView) circularImageViewBefore2;

        circularImageViewBefore2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (circularImageViewBefore2.getDrawable().getConstantState().equals(temp2.getDrawable().getConstantState())){
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
                                checkCamera = 2;
                                callCamera();
                            }
                            if (which == 2) {
                                checkCamera = 2;
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
    public interface Communicator {
        public void MessageBD(String OS_Name);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
                updateFragment("Back");
                break;
        }
    }

    private void updateFragment(String OS_Name) {
        communicator.MessageBD(OS_Name);
        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
    }

    // set Detail 1
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (!(data == null || data.getExtras().get("data") == null)){
            if (checkCamera == 1){
                photo1 = (Bitmap) data.getExtras().get("data");
                circularImageViewBefore1.setImageBitmap(photo1);
                checkCamera = 0;
            }else if (checkCamera == 2){
                photo2 = (Bitmap) data.getExtras().get("data");
                circularImageViewBefore2.setImageBitmap(photo2);
                checkCamera = 0;
            }

            Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_AFTER");
        }

        switch (requestCode){
            case 41:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV",0);
                    setNameAtTextView(41, result);
                    setIdSV(idSV);
                }
                break;
            case 42:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV",0);
                    String idComp = data.getStringExtra("getCompId");
                    Log.e("OrderDeliveryActivity", "setNameAtTextView_result:_ " + result);
//                    tvCompBrand.setText(setCompBrand);
                    setNameAtTextView(42, result);
                    setIdSV(idSV);
                }
                break;
            case 43:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV",0);
                    setNameAtTextView(43, result);
                    setIdSV(idSV);
                }
                break;
            case 44:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV",0);
                    setNameAtTextView(44, result);
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

    // set Detail 2
    void setIdSV(int idSV){
        newIdSV = idSV;
    }

    void setNameAtTextView(int where, String setTextView){
        switch(where){
            case 41: setBrand = setTextView;
                tv01.setText(setBrand);
                break;
            case 42: setDisLo= setTextView;
                tv02.setText(setDisLo);
                break;
            case 43: setDisType = setTextView;
                tv03.setText(setDisType);
                break;
            case 44: setPop = setTextView;
                tv04.setText(setPop);
                break;
        }
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }

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
//        Intent i = new Intent(Tab4FragmentDetail.this, Tab4FragmentShowImage.class);
//
//        startActivity(i);

    }
}
