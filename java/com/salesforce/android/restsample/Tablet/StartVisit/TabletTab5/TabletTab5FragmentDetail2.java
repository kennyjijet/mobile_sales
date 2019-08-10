package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab5;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesforce.android.restsample.AboutTime.ClassifiedTime;
import com.salesforce.android.restsample.MainFragment.fragmentTabBill.Tab5FragmentShowImage;
import com.salesforce.android.restsample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pannikar on 7/22/16 AD.
 */
public class TabletTab5FragmentDetail2 extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set Detail


    private static final int PICK_FROM_GALLERY = 2;

    String newId;
    int newIdSV;

    TextView tt01;
    TextView tt02;
    TextView tt03;
    TextView tt04;
    TextView tt05;
    TextView tt06;
    TextView tt07;
    TextView tt09;

    TextView tvCancel;
    TextView tvToolbar;
    TextView tvDone;

    String setPayType = "None";
    String setSelctInvoice = "None";
    String setAmtRecv = "0";
    String setRemark = "Type";
    String setTotal = "0";
    String setRemain = "0";
    String setDisLocation = "None";
    String setPop = "None";
    String setPriority = "None";

    TextView tvPayType;
    TextView tvSelctInvoice;
    EditText edAmtRecv;
    EditText edRemark;
    TextView tvTotal;
    TextView tvRemain;

    Double dRetPrice;
    Double dWholePrice;
    String idComp;
    String mrkImg="";

    String setFragmentParent;

    LinearLayout lnSelctInvoice;

    LinearLayout lnCashTrnsChq;
    TextView title06, title07,edTitle07Value, title08, title09RemitDate, tvTitle09RemitDate;
    EditText edTitle06Value, edTitle08Value;

    private DatePicker dpResult;
    static final int DATE_DIALOG_ID = 999;
    int year;
    int month;
    int day;

    ClassifiedTime classifiedTime;

    Bitmap photo;
    ImageView circularImageViewBefore;

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

        View view = inflater.inflate(R.layout.detail_tab_5_2collection_cashtrnschq, container, false);
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

        lnSelctInvoice = (LinearLayout) v.findViewById(R.id.lnSelctInvoice);
        lnCashTrnsChq = (LinearLayout) v.findViewById(R.id.lnCashTrnsChq);

        tvCancel = (TextView) v.findViewById(R.id.tvgoBack);
        tvToolbar = (TextView) v.findViewById(R.id.toolbar_title);
        tvDone = (TextView) v.findViewById(R.id.toolbar_done);
        tvCancel.setTypeface(fontThSarabunBold);
        tvToolbar.setTypeface(fontThSarabunBold);
        tvDone.setTypeface(fontThSarabunBold);

        tt01 = (TextView) v.findViewById(R.id.title01);
        tt02 = (TextView) v.findViewById(R.id.title02);
        tt03 = (TextView) v.findViewById(R.id.title03);
        tt04 = (TextView) v.findViewById(R.id.title04);
        tt05 = (TextView) v.findViewById(R.id.title05);
        tt06 = (TextView) v.findViewById(R.id.title1);
        tt07 = (TextView) v.findViewById(R.id.title2);
        tt09 = (TextView) v.findViewById(R.id.title09);

        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabun);
        tt03.setTypeface(fontThSarabun);
        tt04.setTypeface(fontThSarabun);
        tt05.setTypeface(fontThSarabun);
        tt06.setTypeface(fontThSarabun);
        tt07.setTypeface(fontThSarabun);
        tt09.setTypeface(fontThSarabun);

        tvPayType = (TextView) v.findViewById(R.id.tvPayType);
        tvSelctInvoice = (TextView) v.findViewById(R.id.tvSelctInvoice);
        edAmtRecv = (EditText) v.findViewById(R.id.edAmtRecv); //edRetPrice
        edRemark = (EditText) v.findViewById(R.id.edRemark);
        tvTotal = (TextView) v.findViewById(R.id.tvTotal);
        tvRemain = (TextView) v.findViewById(R.id.tvRemain);

        tvPayType.setTypeface(fontThSarabun);
        tvSelctInvoice.setTypeface(fontThSarabun);
        edAmtRecv.setTypeface(fontThSarabun);
        edRemark.setTypeface(fontThSarabun);
        tvTotal.setTypeface(fontThSarabun);
        tvRemain.setTypeface(fontThSarabun);

        // these in LinearLayout: id:lnCashTrnsChq
        title06 = (TextView) v.findViewById(R.id.title06);
        title07 = (TextView) v.findViewById(R.id.title07);
        title08 = (TextView) v.findViewById(R.id.title08);
        title09RemitDate = (TextView) v.findViewById(R.id.title09RemitDate);

        edTitle06Value = (EditText) v.findViewById(R.id.edTitle06Value);
        edTitle07Value = (TextView) v.findViewById(R.id.edTitle07Value);
        edTitle08Value = (EditText) v.findViewById(R.id.edTitle08Value);
        tvTitle09RemitDate = (TextView) v.findViewById(R.id.tvTitle09RemitDate);

        title06.setTypeface(fontThSarabun);
        title07.setTypeface(fontThSarabun);
        title08.setTypeface(fontThSarabun);
        title09RemitDate.setTypeface(fontThSarabun);
        tvTitle09RemitDate.setTypeface(fontThSarabun);
        edTitle06Value.setTypeface(fontThSarabun);
        edTitle07Value.setTypeface(fontThSarabun);
        edTitle08Value.setTypeface(fontThSarabun);

        dpResult = (DatePicker) v.findViewById(R.id.dpResult);
        setCurrentDateOnView();
        addDATEListenerOnButton();
        setCash();

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

    // set Detail
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e("Tab1FragmentTakePhoto", "Tab1FragmentTakePhoto:_img:_AFTER+requestCode"+requestCode);

        if (!(data == null || data.getExtras().get("data") == null)){
            photo = (Bitmap) data.getExtras().get("data");
            circularImageViewBefore.setImageBitmap(photo);
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
            case 7:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(7, result);
                    setIdSV(idSV);
                }
                break;
            case 8:
                if(resultCode == Activity.RESULT_OK){
                    String result=data.getStringExtra("result");
                    int idSV = data.getIntExtra("getIdSV", 0);
                    setNameAtTextView(8, result);
                    setIdSV(idSV);
                }
                break;
        }

    }//onActivityResult

    void setNameAtTextView(int where, String setTextView){
        switch(where){
            case 1: setPayType = setTextView;
                tvPayType.setText(setPayType);
                break;
            case 2:
//                setCompBrand= setTextView;
//                tvCompBrand.setText(setCompBrand);
                break;
            case 3:
//                setActivity = setTextView;
//                tvActivity.setText(setActivity);
                break;
            case 4: setDisLocation = setTextView;
//                tvDisLocation.setText(setDisLocation);
                break;
            case 5: setPop = setTextView;
//                tvPop.setText(setPop);
                break;
            case 6: setPriority = setTextView;
//                tvPriority.setText(setPriority);
                break;
            case 7: setPayType = setTextView;
                tvPayType.setText(setPayType);

                switch (setPayType){
                    case "Cash":
                        setCash();
                        break;

                    case "Cheque":
                        setCheque();
                        break;

                    case "Transfer":
                        setTransfer();
                        break;
                }

                break;
            case 8: edTitle07Value.setText(setTextView);
                break;
            case 9:
                break;
        }
    }

    void setIdSV(int idSV){
        newIdSV = idSV;
    }

    void setCash(){
        Log.e("Tab5FragmentDetail", "setPayType:_Cash:_" + setPayType);
        lnCashTrnsChq.setVisibility(View.GONE);
    }

    void setTransfer() {
        Log.e("Tab5FragmentDetail", "setPayType:_Transfer:_" + setPayType);
        title06.setText("Reference Number:");
        lnCashTrnsChq.setVisibility(View.VISIBLE);
        edTitle06Value.setFocusable(false);
        edTitle06Value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edTitle06Value.setFocusableInTouchMode(true);

                return false;
            }
        });

        edTitle08Value.setFocusable(false);
        edTitle08Value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edTitle08Value.setFocusableInTouchMode(true);

                return false;
            }
        });
    }

    void setCheque(){
        Log.e("Tab5FragmentDetail", "setPayType:_Cheque:_" + setPayType);
        title06.setText("Cheque Number:");
        lnCashTrnsChq.setVisibility(View.VISIBLE);
        edTitle06Value.setFocusable(false);
        edTitle06Value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edTitle06Value.setFocusableInTouchMode(true);

                return false;
            }
        });

        edTitle08Value.setFocusable(false);
        edTitle08Value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                edTitle08Value.setFocusableInTouchMode(true);

                return false;
            }
        });
    }


    public void setCurrentDateOnView() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Change Date to compare
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String format = new SimpleDateFormat("MMM d, yyyy").format(c.getTime());

//        String format = classifiedTime.getFormatDateFromMMMDYinstance();
        tvTitle09RemitDate.setText(format);

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    public void addDATEListenerOnButton() {

        tvTitle09RemitDate.setOnClickListener(new View.OnClickListener() { //tvDisplayDate //tvTextDate

            @Override
            public void onClick(View v) {

//                showDialog(DATE_DIALOG_ID);
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");

            }

        });
    }
//    @Override
//    public Dialog onCreateDialog(int id) {
//        switch (id) {
//            case DATE_DIALOG_ID:
//                // set date picker as current date
//                return new DatePickerDialog(getActivity(), datePickerListener, year, month, day);
//        }
//        return null;
//    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            String format = classifiedTime.getFormatDateFromMMMDY(year, month, day);
            tvTitle09RemitDate.setText(format);

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

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
//        Intent i = new Intent(Tab5FragmentDetail2.this, Tab5FragmentShowImage.class);
//
//        startActivity(i);
    }

    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            // dob.setText(month+"/"+day+"/"+year);
            // tvTitle09RemitDate.setText(month+"/"+day+"/"+year);
        }

    }
}
