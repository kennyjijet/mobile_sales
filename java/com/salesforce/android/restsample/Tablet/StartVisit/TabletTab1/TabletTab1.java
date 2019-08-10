package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.MainFragment.fragmentsTabIcon.Tab1FragmentShowImage;
import com.salesforce.android.restsample.MainFragment.fragmentsTabIcon.Tab1FragmentVisitCont;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.StartVisit.DownloadFeedback;
import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;
import com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2.TabletTab2setPlanDetailActivity;
import com.salesforce.android.restsample.tab.two2.adapters.inner.fragments.TakePhoto.ContactImageAdapter;
import com.salesforce.android.restsample.tab.two2.adapters.inner.fragments.TakePhoto.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pannikar on 7/22/16 AD.
 */


public class TabletTab1 extends Fragment {

    private Communicator communicator;

    String newId;
    int newIdSV;
    Button addImage;
    //    ArrayList<Contact> imageArry = new ArrayList<Contact>();
    ContactImageAdapter imageAdapter;
    private static final int CAMERA_REQUEST = 1888;
    //    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;
    ListView dataList;
    byte[] imageName;
    int imageId;
    Bitmap theImage;
    DataBaseHandler db;

    ImageView imgAdd;

    ImageView imageView01, imageView02, imageView03, imageView04, imageView05;
    ImageView imClose01, imClose02, imClose03, imClose04, imClose05;

    TextView addPhotoText, btnAdd;
    TextView tvNumPhoto;
    Bitmap photo;
    int numClickAdd = 0;

    int chkImageView=0;

    LinearLayout lin01, lin02, lin03, lin04, lin05;
    LinearLayout linVistCont;
    LinearLayout LnProduct;

    EditText edName;
    EditText edCap01;
    EditText edCap02;
    EditText edCap03;
    EditText edCap04;
    EditText edCap05;

    private Toolbar toolbar;
    private TextView mTitle;

    public ArrayList arrProductList = new ArrayList();
    ArrayList arrProductCode = new ArrayList();
    ArrayList<ItemSinglePage> itemsProduct = new ArrayList<ItemSinglePage>();
    ArrayList<ItemSinglePage> itemsALLProduct = new ArrayList<ItemSinglePage>();
    List<Integer> listNewFeedback = new ArrayList<Integer>();
    DownloadFeedback dlFdBck;

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

//        View v = inflater.inflate(R.layout.tab_1visit, container, false);
        View v = inflater.inflate(R.layout.tab_1visit2, container, false);
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");

        arrProductList = (ArrayList<ItemSinglePage>) getArguments().getSerializable("arrProductList");
        arrProductCode= (ArrayList<ItemSinglePage>) getArguments().getSerializable("arrProductCode");
        itemsALLProduct = (ArrayList<ItemSinglePage>) getArguments().getSerializable("testITEMALL");
//        args.putIntegerArrayList("listNewFeedback",(ArrayList<Integer>));
        listNewFeedback = (List<Integer>) getArguments().getIntegerArrayList("listNewFeedback");

//        newId= "00128000007uGpMAAU";
//        newIdSV = 2;

        Log.e(" Tab2FragmentALL3 ", "Tab1FragmentTakePhoto: Id: " + newId);
        Log.e(" Tab2FragmentALL3 ", "_newIdSV_Tab1FragmentTakePhoto: " + newIdSV);

//        getListFeedback();

        Log.e("OrderDeliveryActivity", "TabletTab1:_itemsALLProduct.size: " + itemsALLProduct.size());

//        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        mTitle.setTypeface(fontThSarabunBold);
//        mTitle.setText("Visit");
//
////        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setTitle("Visit");

        imgAdd = (ImageView) v.findViewById(R.id.addPhotoImage);
        imClose01 = (ImageView) v.findViewById(R.id.imClose01); // imClose02
        imClose02 = (ImageView) v.findViewById(R.id.imClose02);
        imClose03 = (ImageView) v.findViewById(R.id.imClose03);
        imClose04 = (ImageView) v.findViewById(R.id.imClose04);
        imClose05 = (ImageView) v.findViewById(R.id.imClose05);

        lin01 = (LinearLayout) v.findViewById(R.id.linearPhoto01);
        lin02 = (LinearLayout) v.findViewById(R.id.linearPhoto02);
        lin03 = (LinearLayout) v.findViewById(R.id.linearPhoto03);
        lin04 = (LinearLayout) v.findViewById(R.id.linearPhoto04);
        lin05 = (LinearLayout) v.findViewById(R.id.linearPhoto05);
        lin01.setVisibility(View.INVISIBLE);
        lin02.setVisibility(View.INVISIBLE);
        lin03.setVisibility(View.INVISIBLE);
        lin04.setVisibility(View.INVISIBLE);
        lin05.setVisibility(View.INVISIBLE);
        linVistCont = (LinearLayout) v.findViewById(R.id.LnVisitContact);
        linVistCont.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                Fragment frg = new TabletTab1FragmentVisitCont(); // Tab1FragmentVisitCont();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                frg.setArguments(args);
                communicator.MessageTab1(frg);
            }
        });

        LnProduct = (LinearLayout) v.findViewById(R.id.LnProduct);
        LnProduct.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                Fragment frg = new TabletTab1FragmentProduct(); // Tab1FragmentVisitCont();
                Bundle args = new Bundle();
                args.putString("getId", newId);
                args.putInt("getIdSV", newIdSV);
                args.putSerializable("arrProductList",arrProductList);
                args.putSerializable("arrProductCode",arrProductCode);
                args.putSerializable("testITEMALL",itemsALLProduct);
                args.putIntegerArrayList("listNewFeedback",(ArrayList<Integer>)listNewFeedback);

                frg.setArguments(args);
                communicator.MessageTab1(frg);
            }
        });

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        edName = (EditText) v.findViewById(R.id.edName);
        edName.setFocusable(false);
//        edName.setOnTouchListener(new View.OnTouchListener() { 
//            @Override 
//            public boolean onTouch(View v, MotionEvent event) {  
//                edName.setFocusableInTouchMode(true);  
//                return false; 
//            }}
//        );

        TextView tvFeedBack = (TextView) v.findViewById(R.id.totalFeedback);
        tvFeedBack.setTypeface(fontThSarabun);

        TextView tvVisit = (TextView) v.findViewById(R.id.visit_note);
        TextView tvProduct = (TextView) v.findViewById(R.id.product);
        tvNumPhoto = (TextView) v.findViewById(R.id.tvNumPhoto);

        imageView01 = (ImageView) v.findViewById(R.id.imageView1);
        imageView02 = (ImageView) v.findViewById(R.id.imageView2);
        imageView03 = (ImageView) v.findViewById(R.id.imageView3);
        imageView04 = (ImageView) v.findViewById(R.id.imageView4);
        imageView05 = (ImageView) v.findViewById(R.id.imageView5);
        btnAdd = (TextView) v.findViewById(R.id.addPhotoText);

        edCap01 = (EditText) v.findViewById(R.id.edCap01);
        edCap02 = (EditText) v.findViewById(R.id.edCap02);
        edCap03 = (EditText) v.findViewById(R.id.edCap03);
        edCap04 = (EditText) v.findViewById(R.id.edCap04);
        edCap05 = (EditText) v.findViewById(R.id.edCap05);

        tt01.setTypeface(fontThSarabunBold);
        edName.setTypeface(fontThSarabun);
        tvVisit.setTypeface(fontThSarabunBold);
        tvProduct.setTypeface(fontThSarabunBold);
        tvNumPhoto.setTypeface(fontThSarabun);
        tvProduct.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(getActivity(), Tab1FragmentProduct.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
////                startActivity(i);
//                startActivity(i);
            }
        });

        edCap01.setTypeface(fontThSarabun);
        edCap02.setTypeface(fontThSarabun);
        edCap03.setTypeface(fontThSarabun);
        edCap04.setTypeface(fontThSarabun);
        edCap05.setTypeface(fontThSarabun);

        btnAdd.setTypeface(fontThSarabunBold);

        // Add take a photo
        /**
         * open dialog for choose camera/gallery START
         */

        final String[] option = new String[] { "View","Take from Camera", "Select from Gallery" };
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

        imgAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(numClickAdd<5 && numClickAdd >=0){
                    Log.e(" countChkGrp ", "click_add_numClickAdd:_0_ " + numClickAdd);
                    clickAddPhoto();

                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                dialog.show();
                if(numClickAdd<5 && numClickAdd >=0){
                    Log.e(" countChkGrp ", "click_add_numClickAdd:_0_ " + numClickAdd);
                    clickAddPhoto();

                }
            }
        });

        // Add take a photo
        /**
         * open dialog for choose camera/gallery END
         */
        imageView01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chkImageView = 1;
                dialog.show();
            }
        });

        imageView02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chkImageView = 2;
                dialog.show();
            }
        });

        imageView03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chkImageView = 3;
                dialog.show();
            }
        });

        imageView04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chkImageView = 4;
                dialog.show();
            }
        });

        imageView05.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chkImageView = 5;
                dialog.show();
            }
        });



        Log.e(" countChkGrp ", "click_sub_numClickAdd:_0_ " + numClickAdd);
        // *** *** *** Close Image

        imClose01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSubPhoto(1);
            }
        });

        imClose02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSubPhoto(2);
            }
        });

        imClose03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSubPhoto(3);
            }
        });

        imClose04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSubPhoto(4);
            }
        });

        imClose05.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                numClickAdd = numClickAdd - 1;
                clickSubPhoto(5);
            }
        });




//        getFragmentManager().addOnBackStackChangedListener(this);



        return v;
    }

//    private void getListFeedback() {
//             if(arrProductList.size() ==0 ){
//                dlFdBck = new DownloadFeedback(getActivity(), newId, newIdSV);
//                dlFdBck.setUpdateListener(new DownloadFeedback.OnUpdateListener() {
//
//                    @Override
//                    public void onUpdate(String obj,
//                                         ArrayList arrProductListNew,
//                                         ArrayList arrProductCodeNew,
//                                         ArrayList<ItemSinglePage> itemsALLProductNew) {
//                        arrProductList = arrProductListNew;
//                        arrProductCode = arrProductCodeNew;
//                        itemsALLProduct = itemsALLProductNew;
//                    }
//
//                });
//                dlFdBck.execute();
//                 Log.e("OrderDeliveryActivity", "TabletTab1:_2_itemsALLProduct.size: " + itemsALLProduct.size());
//
//             }
//    }

    // Add Photo
    void clickAddPhoto(){

        if(numClickAdd<5 && numClickAdd >=0){

            Log.e(" countChkGrp ", "click_add_numClickAdd:_1_ " + numClickAdd);
//
            numClickAdd = numClickAdd + 1;
            String numClickAddSt = String.valueOf(numClickAdd);
            if(numClickAdd <=5){

                Log.e(" countChkGrp ", "click_add_numClickAdd:_2_ " + numClickAdd);

                tvNumPhoto.setText(numClickAddSt+"/5");
                switch (numClickAdd){
                    case 1: lin01.setVisibility(View.VISIBLE);
                        break;
                    case 2: lin02.setVisibility(View.VISIBLE);
                        break;
                    case 3: lin03.setVisibility(View.VISIBLE);
                        break;
                    case 4: lin04.setVisibility(View.VISIBLE);
                        break;
                    case 5: lin05.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    // Sub Photo
    void clickSubPhoto(int chkImView){

        Log.e(" countChkGrp ", "click_sub_numClickAdd:_1_ " + numClickAdd);
//        numClickAdd = numClickAdd - 1;

        Log.e(" countChkGrp ", "click_sub_numClickAdd:_2_ " + numClickAdd);

//        String numClickAddSt = String.valueOf(numClickAdd);
//         if(numClickAdd > -1 && numClickAdd <=5 ){
        if(numClickAdd > 0 && numClickAdd <=5 ){
//            tvNumPhoto.setText(numClickAddSt+"/5");
            switch (chkImView){
                case 1: lin01.setVisibility(View.GONE);
                    imageView01.setImageResource(R.drawable.photon);
                    edCap01.setText("");
                    edCap01.setHint("Caption");
                    break;
                case 2: lin02.setVisibility(View.GONE);
                    imageView02.setImageResource(R.drawable.photon);
                    edCap02.setText("");
                    edCap02.setHint("Caption");
                    break;
                case 3: lin03.setVisibility(View.GONE);
                    imageView03.setImageResource(R.drawable.photon);
                    edCap03.setText("");
                    edCap03.setHint("Caption");
                    break;
                case 4: lin04.setVisibility(View.GONE);
                    imageView04.setImageResource(R.drawable.photon);
                    edCap04.setText("");
                    edCap04.setHint("Caption");
                    break;
                case 5: lin05.setVisibility(View.GONE);
                    imageView05.setImageResource(R.drawable.photon);
                    edCap05.setText("");
                    edCap05.setHint("Caption");
                    break;
            }
            numClickAdd = numClickAdd - 1;
            String numClickAddSt = String.valueOf(numClickAdd);
            tvNumPhoto.setText(numClickAddSt+"/5");
        }
        Log.e(" countChkGrp ", "click_sub_numClickAdd:_3_ " + numClickAdd);
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

    /**
     * open gallery method
     */

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
        Intent i = new Intent(getActivity(), Tab1FragmentShowImage.class);

        startActivity(i);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_AFTER+requestCode"+requestCode);

        if (!(data == null)){
            photo = (Bitmap) data.getExtras().get("data");
            switch(chkImageView){
                case 1: imageView01.setImageBitmap(photo);
                    break;
                case 2: imageView02.setImageBitmap(photo);
                    break;
                case 3: imageView03.setImageBitmap(photo);
                    break;
                case 4: imageView04.setImageBitmap(photo);
                    break;
                case 5: imageView05.setImageBitmap(photo);
                    break;
            }
        }
//        imageView01.setImageBitmap(photo);
        Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_AFTER");

    }

    public interface Communicator {
        public void MessageTab1(Fragment frg);
    }
}
