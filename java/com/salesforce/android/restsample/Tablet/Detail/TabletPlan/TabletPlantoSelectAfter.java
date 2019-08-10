package com.salesforce.android.restsample.Tablet.Detail.TabletPlan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBSalesVisit.SalesVisitDatabaseHelper;
//import com.salesforce.android.restsample.DateTimeDialogFragment.SelectDateFragment;
//import com.salesforce.android.restsample.DateTimeDialogFragment.SelectDateFragment;
import com.salesforce.android.restsample.MainPLANs.PlanActivity1new2;
import com.salesforce.android.restsample.MainPLANs.PlanActivity1toSelectAddress;
import com.salesforce.android.restsample.MainPLANs.adapter.ListSelectAddressAdapter;
import com.salesforce.android.restsample.MainPLANs.planDatabase.PlanDataSource;
import com.salesforce.android.restsample.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by pannikar on 8/10/16 AD.
 */
public class TabletPlantoSelectAfter extends Fragment implements View.OnClickListener {

    private Communicator communicator;

    TextView tvCreateplan;

    ListView lvAddress;
    ListSelectAddressAdapter adapter;
    //    List<Address> mAddressList2;
//    List<Account> mAccountList2;
    //    AddressDatabaseHelper mDBHelper;
    AccountDatabaseHelper mDBHelperAcc;
    SalesVisitDatabaseHelper salesVisitDatabaseHelper;

    AddressDatabaseHelper mDBHelper2;
    List<Address> getAddress;
    List<Account> getAccount;

    String newId, newName, newIdAddress;

//    TextView tvTextDate, tvTextTime;

    // Date Picker
//    private TextView tvDisplayDate;
    private DatePicker dpResult;

    int year;
    int month;
    int day;

    static final int DATE_DIALOG_ID = 999;

    // Time Picker
    private TimePicker timePicker1, timePicker2;

//    private int hour;
//    private int minute;
//    private int second;

    private int hourIn;
    private int minuteIn;
    private int secondIn;

    private int hourOut;
    private int minuteOut;
    private int secondOut;

    static final int TIME_DIALOG_ID_IN = 777;
    static final int TIME_DIALOG_ID_OUT = 888;

    long timeToSendBack;
    final static int REQ_CODE = 1;

    String getname, getnumber;
    //gettime;

//    private PlanDataSource datasource;

    //    EditText ed01Number, ed02Name, ed03Date, ed04PlanIn, ed05PlanOut;
    TextView ed01Number, ed02Name, ed03Date, ed04PlanIn, ed05PlanOut;
    TextView tv06tvActivity;

    String timeinSt, timeoutSt;
    int idCharSum01, idChar05, idChar06, idChar07;
    int idCharSum02, idChar08, idChar09, idChar10;
    int idCharSum03, idChar11, idChar12, idChar13;
    String idAscii11, idAscii12, idAscii13;
    String idALL;

    // define members to insert in SQLiteDB
    String id;
    String addrid;
    int timein;
    int timeout;
    String previsitnote;
    int type;
    boolean issync = false;
    boolean isnew = false;
    boolean isvisit= false;
    double chkinlat;
    double chkinlng;
    int chkin;
    int chkout;
    String visitnote;
    boolean isSubmitted;
    String idServer;

    List<Account> mAccountList;
    List<Address> mAddressList;

    AccountDatabaseHelper mDBHelperAccount;
    AddressDatabaseHelper mDBHelperAddress;

    PlanDataSource mDBHelperPlanDataSource;
    private EditText edPreVisitNotes;

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
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.detail_plan_delivery_create_plan, container, false);

        mDBHelperPlanDataSource = new PlanDataSource(getActivity());
        mDBHelperPlanDataSource.open();

        mDBHelperAccount = new AccountDatabaseHelper(getActivity());
        mDBHelperAccount.openDatabase();

        mDBHelperAddress = new AddressDatabaseHelper(getActivity());
        mDBHelperAddress.openDatabase();

        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        Bundle extras = getIntent().getExtras();
        newId= getArguments().getString("getId"); //extras.getString("getId");
        Log.e("position", "After_sel_Done_name: newId" + newId);
        newName = getArguments().getString("getName"); //extras.getString("getName");
        newIdAddress = getArguments().getString("getIdAddress"); //extras.getString("getIdAddress");

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);

        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07.setTypeface(fontThSarabunBold);

        ed01Number = (TextView) v.findViewById(R.id.edNumber);
        ed02Name = (TextView) v.findViewById(R.id.edName);
        ed03Date = (TextView) v.findViewById(R.id.edDate);
        ed04PlanIn = (TextView) v.findViewById(R.id.edPlanIn);
        ed05PlanOut = (TextView) v.findViewById(R.id.edPlanOut);
        edPreVisitNotes = (EditText) v.findViewById(R.id.edPreVisitNotes);

        tv06tvActivity = (TextView) v.findViewById(R.id.tvActivity);
        ed01Number.setTypeface(fontThSarabun);
        ed02Name.setTypeface(fontThSarabun);
        ed03Date.setTypeface(fontThSarabun);
        ed04PlanIn.setTypeface(fontThSarabun);
        ed05PlanOut.setTypeface(fontThSarabun);
        tv06tvActivity.setTypeface(fontThSarabun);

//        setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        mTitle.setTypeface(fontThSarabunBold);

        mDBHelperAcc = new AccountDatabaseHelper(getActivity());
        getAccount = mDBHelperAcc.getListAccountListBySearchId(newId);
        salesVisitDatabaseHelper = new SalesVisitDatabaseHelper(getActivity());

////        here
        ed01Number.setText(getAccount.get(0).getNumber());
        ed02Name.setText(getAccount.get(0).getName());

        LinearLayout lnLeft = (LinearLayout) v.findViewById(R.id.LnLeft);
//        lnLeft.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//                Intent i = new Intent(PlanActivity1toSelectAfter2.this, PlanActivity1toSelectAddress.class);
//                i.putExtra("getId", newId);
//                startActivity(i);
//            }
//        });

        ImageView goBack = (ImageView) v.findViewById(R.id.toolbar_arrowback);
//        goBack.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//                Intent i = new Intent(PlanActivity1toSelectAfter2.this, PlanActivity1toSelectAddress.class);
//                i.putExtra("getId", newId);
//                startActivity(i);
//            }
//        });

        TextView tvgoBack = (TextView) v.findViewById(R.id.tvgoBack);
        tvgoBack.setTypeface(fontThSarabun);
//        tvgoBack.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//                Intent i = new Intent(PlanActivity1toSelectAfter2.this, PlanActivity1toSelectAddress.class);
//                i.putExtra("getId", newId);
//                startActivity(i);
//            }
//        });

        Button btnCreatePlan = (Button) v.findViewById(R.id.btnCreatePlan);
        btnCreatePlan.setTypeface(fontThSarabunBold);
        btnCreatePlan.setOnClickListener(this);
//        btnCreatePlan.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(PlanActivity1toSelectAfter2.this, PlanActivity1new2.class);
//                StringBuilder strBui = new StringBuilder()
//                        // Month is 0 based, just add 1
//                        .append(month + 1).append("-").append(day).append("-")
//                        .append(year).append(" ");
//
//                id = "a0028" + idALL + idAscii11 + idAscii12 + idAscii13; //here
//
//                Log.e(" Check_getTime_BEFORE", "_idAscii:_id_ " + id);
//
//                addrid = newIdAddress;
//                Log.e("position", "Chk_select_Address:_newIdAddress:_" + newIdAddress);
//
//                // #02 Time In
//                StringBuilder sbIn = new StringBuilder();
//                sbIn.append(pad(hourIn));
//                sbIn.append(pad(minuteIn));
//                sbIn.append(pad(secondIn));
//
//                // +++ Start
//                // set current time into textview
//                ed04PlanIn.setText(new StringBuilder().append(pad(hourIn))
//                        .append(":").append(pad(minuteIn)));
//                // === End
//
//                type = 1;
//
//                isSubmitted = false;
//                idServer ="";
//
//
//
//                // +++ ++++ +++++ check In
//
//                Calendar calendarIn = Calendar.getInstance();
//                Calendar calendarOut = Calendar.getInstance();
//                calendarIn.set(dpResult.getYear(), dpResult.getMonth(), dpResult.getDayOfMonth(),
//                                    timePicker1.getCurrentHour(), timePicker1.getCurrentMinute(), 0);
//                calendarOut.set(dpResult.getYear(), dpResult.getMonth(), dpResult.getDayOfMonth(),
//                                    timePicker2.getCurrentHour(), timePicker2.getCurrentMinute(), 0);
//
//                long startTime = calendarIn.getTimeInMillis()/1000L;
//                timein = (int) startTime;
//
//                long endTime = calendarOut.getTimeInMillis()/1000L;
//                timeout = (int) endTime;
//
//                Log.e(" Check_Time", "timein:_chck: " + timein);
//
//                // +++ ++++ +++++ check End
//
//                previsitnote = edPreVisitNotes.getText().toString();
//
//                salesVisitDatabaseHelper.addSalesVisit(addrid, timein, timeout, previsitnote,
//                                                        type, issync, isnew, isvisit,
//                                                        chkinlat, chkinlng, chkin, chkout,
//                                                        visitnote, isSubmitted, idServer);
//
////                mAddressList = mDBHelperAddress.getListAddressSearchById2(addrid);
////
////                // for Add Plan Database
////                mAccountList = mDBHelperAccount.getListAccountListBySearchId(mAddressList.get(0).getAcctid());
////                String nameAccount = mAccountList.get(0).getName();
////
//////                mAddressList = mDBHelperAddress.getListAddressSearchById2(addrid);
////
////
////                mDBHelperPlanDataSource.insertPlan( id,
////                                                    nameAccount,
////                                                    previsitnote,
////                                                    timein,
////                                                    mAccountList.get(0).getNumber(),
////                                                    mAccountList.get(0).getId(),
////                                                    mAddressList.get(0).getId());
//
//                Log.e("System out_insertPlan", "android_sqlite_insertPlan:_" + id + '\n'
//                        + timein + '\n'
//                        + timeout + '\n'
//                        + type + '\n');
//
//
//                startActivityForResult(i, REQ_CODE);
//
//            }
//        });

        setCurrentDateOnView(v);
        addDATEListenerOnButton(v);

        setCurrentTimeINOnView(v);
        addTIMEINListenerOnButton();

        setCurrentTimeOUTOnView(v);
        addTIMEOUTListenerOnButton();

        return v;
    }


    // display current Date
    public void setCurrentDateOnView(View v) {

//        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) v.findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Change Date to compare
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        timeToSendBack = c.getTimeInMillis();

        // set current date into textview
        ed03Date.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    // display current Time
    public void setCurrentTimeINOnView(View v) {

        timePicker1 = (TimePicker) v.findViewById(R.id.timePicker1);

        final Calendar c = Calendar.getInstance();
        hourIn = c.get(Calendar.HOUR_OF_DAY);
        minuteIn = c.get(Calendar.MINUTE);
        secondIn = c.get(Calendar.SECOND);

        // set current time into textview
        ed04PlanIn.setText(
                new StringBuilder().append(pad(hourIn))
                        .append(":").append(pad(minuteIn)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hourIn);
        timePicker1.setCurrentMinute(minuteIn);
    }

    public void setCurrentTimeOUTOnView(View v) {

        timePicker2 = (TimePicker) v.findViewById(R.id.timePicker2);

        final Calendar c = Calendar.getInstance();
        hourOut = c.get(Calendar.HOUR_OF_DAY);
        minuteOut = c.get(Calendar.MINUTE);
        secondOut = c.get(Calendar.SECOND);

        // set current time into textview
        ed05PlanOut.setText( new StringBuilder().append(pad(hourOut)).append(":").append(pad(minuteOut)));

        // set current time into timepicker
        timePicker2.setCurrentHour(hourOut);
        timePicker2.setCurrentMinute(minuteOut);

    }

    public void addDATEListenerOnButton(View v) {

        ed03Date.setOnClickListener(new View.OnClickListener() { //tvDisplayDate //tvTextDate

            @Override
            public void onClick(View v) {
//                getActivity().showDialog(DATE_DIALOG_ID);

                android.app.DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");

            }
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            ed03Date.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };


    public void addTIMEINListenerOnButton() {

        ed04PlanIn.setOnClickListener(new View.OnClickListener() { //tvTextTime
            @Override
            public void onClick(View v) {
                getActivity().showDialog(TIME_DIALOG_ID_IN);
            }
        });
    }

    public void addTIMEOUTListenerOnButton() {
        ed05PlanOut.setOnClickListener(new View.OnClickListener() { //tvTextTime
            @Override
            public void onClick(View v) {
                getActivity().showDialog(TIME_DIALOG_ID_OUT);

            }
        });
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hourIn = selectedHour;
                    minuteIn = selectedMinute;

                    // set current time into textview
                    ed04PlanIn.setText(new StringBuilder().append(pad(hourIn))
                            .append(":").append(pad(minuteIn)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hourIn);
                    timePicker1.setCurrentMinute(minuteIn);

                }
            };
    ///
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public interface Communicator {
        public void MessageCreatePlanSettoDB(String whatyoupress);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnCreatePlan:
                addPlanToDatabase();
                communicator.MessageCreatePlanSettoDB("btnCreatePlan");
                break;
        }
    }

    private void addPlanToDatabase() {
        addrid = newIdAddress;
        Log.e("position", "Chk_select_Address:_newIdAddress:_" + newIdAddress);

        // #02 Time In
        StringBuilder sbIn = new StringBuilder();
        sbIn.append(pad(hourIn));
        sbIn.append(pad(minuteIn));
        sbIn.append(pad(secondIn));

        // +++ Start
        // set current time into textview
        ed04PlanIn.setText(new StringBuilder().append(pad(hourIn)).append(":").append(pad(minuteIn)));
        // === End

        type = 1;

        isSubmitted = false;
        idServer ="";


        // +++ ++++ +++++ check In
//heree

        Calendar calendarIn = Calendar.getInstance();
        Calendar calendarOut = Calendar.getInstance();
//        calendarIn.set(dpResult.getYear(), dpResult.getMonth(), dpResult.getDayOfMonth(),
//                timePicker1.getCurrentHour(), timePicker1.getCurrentMinute(), 0);
//        calendarOut.set(dpResult.getYear(), dpResult.getMonth(), dpResult.getDayOfMonth(),
//                timePicker2.getCurrentHour(), timePicker2.getCurrentMinute(), 0);
        calendarIn.set(year, month, day,
                hourIn, minuteIn, 0);
        calendarOut.set(year, month, day,
                hourOut, minuteOut, 0);

        long startTime = calendarIn.getTimeInMillis()/1000L;
        timein = (int) startTime;

        long endTime = calendarOut.getTimeInMillis()/1000L;
        timeout = (int) endTime;

        Log.e(" Check_Time", "timein:_chck: " + timein);

        // +++ ++++ +++++ check End

        previsitnote = edPreVisitNotes.getText().toString();
        boolean isDeleted = false;

        salesVisitDatabaseHelper.addSalesVisit(addrid, timein, timeout, previsitnote,
                type, issync, isnew, isvisit,
                chkinlat, chkinlng, chkin, chkout,
                visitnote, isSubmitted, idServer, isDeleted);

    } // end addPlanToDatabase()


    // for DialogFragment
    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            year = yy;
            month = mm;
            day = dd;


            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
//            dob.setText(month+"/"+day+"/"+year);
            ed03Date.setText(month+"/"+day+"/"+year);
        }
    }
}


