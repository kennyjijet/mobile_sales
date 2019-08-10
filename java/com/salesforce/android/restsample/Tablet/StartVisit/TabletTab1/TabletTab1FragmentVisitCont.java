package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesforce.android.restsample.Library.ImageConverter;
import com.salesforce.android.restsample.MainFragment.CustomViewIconTextTabsActivity;
import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab1FragmentVisitCont extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;
    private String newId;
    private int newIdSV;

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

        View view = inflater.inflate(R.layout.detail_tab1_visited_contact, container, false);

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");
        // Initialize Views
        setLayout(view);

//        tvgoBack.setOnClickListener(this);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

        toolbar_title.setTypeface(fontThSarabunBold);

        // set Detail
        TextView toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(fontThSarabunBold);

        TextView title1 = (TextView) v.findViewById(R.id.Title1);
        TextView title2 = (TextView) v.findViewById(R.id.Title2);
        title1.setTypeface(fontThSarabunBold);
        title2.setTypeface(fontThSarabunBold);

        TextView tt01 = (TextView) v.findViewById(R.id.tt1);
        TextView tt02 = (TextView) v.findViewById(R.id.tt2);
        TextView tt03 = (TextView) v.findViewById(R.id.tt3);
        TextView tt04 = (TextView) v.findViewById(R.id.tt4);
        TextView tt05 = (TextView) v.findViewById(R.id.tt5);
        TextView tt06 = (TextView) v.findViewById(R.id.tt6);
        TextView tt07 = (TextView) v.findViewById(R.id.tt7);
        TextView tt08 = (TextView) v.findViewById(R.id.tt8);
        TextView tt09 = (TextView) v.findViewById(R.id.tt9);

        tt01.setTypeface(fontThSarabun);
        tt02.setTypeface(fontThSarabun);
        tt03.setTypeface(fontThSarabun);
        tt04.setTypeface(fontThSarabun);
        tt05.setTypeface(fontThSarabun);
        tt06.setTypeface(fontThSarabun);
        tt07.setTypeface(fontThSarabun);
        tt08.setTypeface(fontThSarabun);
        tt09.setTypeface(fontThSarabun);

//        TextView toolCancel = (TextView) v.findViewById(R.id.tvgoBack);
//        toolCancel.setTypeface(fontThSarabun);
//        toolCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Tab1FragmentVisitCont.this, CustomViewIconTextTabsActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                i.putExtra("Check", "1");
//                startActivity(i);
//            }
//        });
        TextView toolDone = (TextView) v.findViewById(R.id.tvEdit);
        toolDone.setTypeface(fontThSarabun);

        Bitmap bitmapBuilding1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.people);
        Bitmap circularBitmapBld1 = ImageConverter.getRoundedCornerBitmap(bitmapBuilding1, 150);
        ImageView circularImageViewBefore1 = (ImageView) v.findViewById(R.id.Img1);
        circularImageViewBefore1.setImageBitmap(circularBitmapBld1);


        Bitmap bitmapBuilding2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.people);
        Bitmap circularBitmapBld2 = ImageConverter.getRoundedCornerBitmap(bitmapBuilding2, 150);
        ImageView circularImageViewBefore2 = (ImageView) v.findViewById(R.id.Img2);
        circularImageViewBefore2.setImageBitmap(circularBitmapBld2);

        Bitmap bitmapBuilding3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.people);
        Bitmap circularBitmapBld3 = ImageConverter.getRoundedCornerBitmap(bitmapBuilding3, 150);
        ImageView circularImageViewBefore3 = (ImageView) v.findViewById(R.id.Img3);
        circularImageViewBefore3.setImageBitmap(circularBitmapBld3);
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
}

