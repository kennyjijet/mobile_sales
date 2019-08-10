package com.salesforce.android.restsample.Tablet.Detail.TabletCustomer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBInvoice.Invoice;
import com.salesforce.android.restsample.DB.Model.DBInvoice.InvoiceDatabaseHelper;
import com.salesforce.android.restsample.Library.ImageConverter;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer1Activity2;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2DetailBillTo;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2DetailShipTo;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2Detailinvoice;
import com.salesforce.android.restsample.MainCUSTOMERs.Customer2Edit;
import com.salesforce.android.restsample.MainCUSTOMERs.ItemSaleHistory.EntryAdapterSaleHistory;
import com.salesforce.android.restsample.MainCUSTOMERs.ItemSaleHistory.EntryItemSaleHistory;
import com.salesforce.android.restsample.MainCUSTOMERs.ItemSaleHistory.ItemSaleHistory;
import com.salesforce.android.restsample.MainContacts.ContactActivityFromDetail;
import com.salesforce.android.restsample.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/21/16 AD.
 */
public class TabletCustomer2Detail extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;
    private TextView tvEdit;
//    private String valueId;
    private ImageView goBack;

    // set for detail
    AccountDatabaseHelper mDBHelper;
    AddressDatabaseHelper mDBHelper2;
    List<Address> getAddress;
    List<Account> getAccount;
    String getCall01, getCall02, getCall03;
    String imageId;
    Bitmap imageIdBMP;

    //SaleHistory
    ListView mListView;
    ArrayList<ItemSaleHistory> itemSaleHistories = new ArrayList<ItemSaleHistory>();
    InvoiceDatabaseHelper mDBHelper3;
    List<Invoice> getInvoice;


    String downloadUrl = "";
    private ImageView downloadedImg;
    //    private ImageView circularImageViewBefore;
    private ProgressDialog simpleWaitDialog;
    boolean chckImg = false;

    LinearLayout lnInvoice;
    private TextView tt07Contacts;
    String newId, newName; //, newNumber;
    String newNumber;

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

        //Retrieve the value
//        valueId = getArguments().getString("getId");
        newName= getArguments().getString("getName");//
        newId= getArguments().getString("getId");
        // newNumber = getArguments().getString("getNumber");
        Log.e("TabletCustomer2Detail","Detail_value:_" + newId);

        View view = inflater.inflate(R.layout.detail_customer_detail_edit3, container, false);

        // Initialize Views
        setLayout(view);

        return view;
    }

    private void setLayout(View v) {

        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);
        tvEdit = (TextView) v.findViewById(R.id.tvEdit);

        toolbar_title.setTypeface(fontThSarabunBold);
        tvEdit.setTypeface(fontThSarabunBold);

        // set detail
//        Bundle extras = getIntent().getExtras();
//        newName= getArguments().getString("getName");
//        newId= getArguments().getString("getId");
        // newNumber = getArguments().getString("getNumber");
//
//        Log.e("Customer2Detail", "Done_newName: " + newName);

        //https://c.ap2.content.force.com/servlet/servlet.FileDownload?file=01528000001Nn0aAAC
        goBack = (ImageView) v.findViewById(R.id.goBack);
        downloadedImg = (ImageView) v.findViewById(R.id.imgURL);

        // *** *** *** Round Image
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.businessbuilding);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 30);
        //ImageView circularImageViewBefore = (ImageView)findViewById(R.id.leftImage);
//        circularImageViewBefore = (ImageView)findViewById(R.id.leftImage);
//        circularImageViewBefore.setImageBitmap(circularBitmap);


        mDBHelper = new AccountDatabaseHelper(getActivity());
        getAccount =  mDBHelper.getListAccountListBySearchId(newId);

        lnInvoice = (LinearLayout) v.findViewById(R.id.lnInvoice);

        TextView tvtitle01 = (TextView) v.findViewById(R.id.tvtitle01);
        TextView tvtitle02 = (TextView) v.findViewById(R.id.tvtitle02);

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        tt07Contacts = (TextView) v.findViewById(R.id.title07Contacts);
        TextView tt08BillTo = (TextView) v.findViewById(R.id.title08BillTo);
        TextView tt09ShipTo = (TextView) v.findViewById(R.id.title09ShipTo);

//        TextView tvBack = (TextView) v.findViewById(R.id.tvgoBack);
        TextView tv0edit = (TextView) v.findViewById(R.id.tvEdit);
        TextView tv1 = (TextView) v.findViewById(R.id.tvName);
        TextView tv2 = (TextView) v.findViewById(R.id.tvNumber);
        TextView tv3 = (TextView) v.findViewById(R.id.tvPhone1);
        TextView tv4 = (TextView) v.findViewById(R.id.tvPhone2);
        TextView tv5 = (TextView) v.findViewById(R.id.tvMobile);
        TextView tv6 = (TextView) v.findViewById(R.id.tvFax);
        TextView tv7 = (TextView) v.findViewById(R.id.tvEmail);
        TextView tv8 = (TextView) v.findViewById(R.id.tvWebsite);
        TextView tv9Channel = (TextView) v.findViewById(R.id.tvChannel);

        TextView tvType = (TextView) v.findViewById(R.id.tvType);
        TextView tv01 = (TextView) v.findViewById(R.id.tvCreditLimit);
        TextView tv01CreditLimit = (TextView) v.findViewById(R.id.tvCreditLimitValue);
        TextView tv02 = (TextView) v.findViewById(R.id.tvBalance);
        TextView tv02Balance = (TextView) v.findViewById(R.id.tvBalanceValue);
        TextView tv03 = (TextView) v.findViewById(R.id.tvAvailable);
        TextView tv03Available = (TextView) v.findViewById(R.id.tvAvailableValue);

        tvType.setTypeface(fontThSarabunBold);
        tv01.setTypeface(fontThSarabunBold);
        tv02.setTypeface(fontThSarabunBold);
        tv03.setTypeface(fontThSarabunBold);
        tv01CreditLimit.setTypeface(fontThSarabun);
        tv02Balance.setTypeface(fontThSarabun);
        tv03Available.setTypeface(fontThSarabun);

        tvtitle01.setTypeface(fontThSarabunBold);
        tvtitle02.setTypeface(fontThSarabunBold);
        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07Contacts.setTypeface(fontThSarabunBold);
        tt08BillTo.setTypeface(fontThSarabunBold);
        tt09ShipTo.setTypeface(fontThSarabunBold);

//        tvBack.setTypeface(fontThSarabun);
        tv0edit.setTypeface(fontThSarabun);
        tv1.setTypeface(fontThSarabunBold);
        tv2.setTypeface(fontThSarabun);
        tv3.setTypeface(fontThSarabun);
        tv4.setTypeface(fontThSarabun);
        tv5.setTypeface(fontThSarabun);
        tv6.setTypeface(fontThSarabun);
        tv7.setTypeface(fontThSarabun);
        tv8.setTypeface(fontThSarabun);
        tv9Channel.setTypeface(fontThSarabun);
        tt08BillTo.setTypeface(fontThSarabun);
        tt09ShipTo.setTypeface(fontThSarabun);

        if(getAccount.size()!=0){

            newNumber = getAccount.get(0).getNumber();

            tvType.setText(getAccount.get(0).getGrade());
            tv01CreditLimit.setText(String.valueOf(getAccount.get(0).getCreditLimit()));
            tv02Balance.setText(String.valueOf(getAccount.get(0).getBalance()));
            tv03Available.setText(String.valueOf(getAccount.get(0).getAvailCredit()));
            tv9Channel.setText(String.valueOf(getAccount.get(0).getChannel()));
            tv1.setText(getAccount.get(0).getName());
            tv2.setText(getAccount.get(0).getNumber()); // newNumber
            tv3.setText(getAccount.get(0).getPhone1());
            tv4.setText(getAccount.get(0).getPhone2());
            tv5.setText(getAccount.get(0).getMobile());
            tv6.setText(getAccount.get(0).getFax());
            tv7.setText(getAccount.get(0).getEmail());
            tv8.setText(getAccount.get(0).getWebsite());
//            imageId = getAccount.get(0).getImage();
            imageId = "01528000001UmpdAAC";
            downloadUrl = "https://c.ap2.content.force.com/servlet/servlet.ImageServer?id="
                    +imageId//+"01528000001NHlxAAG"
                    +"&oid=00D2800000149LJEAY"; // 01528000001Nn0aAAC
//                            + "&oid=01528000001Nn0aAAC";
            Log.e("loadImageFromStorage", "loadImageFromStorage:_imageId_" + imageId);

            boolean correct = "null".equals(imageId);
//            if(correct == false){
//                new ImageDownloader().execute(downloadUrl);
//            }
            if(correct == false){

                // check Image in Local Database or not
                chckImg = loadImageFromStorage(imageId);
                if(chckImg ==false){
                    new ImageDownloader().execute(downloadUrl);
                    Log.e("loadImageFromStorage", "loadImageFromStorage:_chckImg_" + chckImg);
                }

            }

        } else {
            tv1.setText(" null ");
            tv2.setText(" null ");
            tv3.setText(" null ");
            tv4.setText(" null ");
            tv5.setText(" null ");
            tv6.setText(" null ");
            tv7.setText(" null ");
            tv8.setText(" null ");
        }

        // add phone to call Start
        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener,
                PhoneStateListener.LISTEN_CALL_STATE);

        // add button listener
        getCall01 = tv3.getText().toString();
        if(getCall01.isEmpty()){
            Log.e("OrderDeliveryActivity", "phone_number:_null:_" + getCall01);

        } else {

            tv3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    // set title
                    alertDialogBuilder.setTitle(" Outgoing Call");
                    alertDialogBuilder.setIcon(R.drawable.icon_phonecall32);
//                alertDialogBuilder.setTypeface(fontThSarabun);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Do you want to call:" + '\n' + getCall01.toString() + " ?") // Click yes to exit!
                            .setCancelable(false)
                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, close
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);

                                    callIntent.setData(Uri.parse("tel:" + getCall01));
                                    startActivity(callIntent);
//                                startActivityForResult(callIntent, request_Code);
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();


//                dialog = builder.create();
                    alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);

                    // show it
                    alertDialog.show();
                }
            });
        }
        // add Alert Dialog End

        // add button listener
        getCall02 = tv4.getText().toString();
        if(getCall02.isEmpty()){
            Log.e("OrderDeliveryActivity", "phone_number:_null:_" + getCall02);

        } else {

            tv4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    // set title
                    alertDialogBuilder.setTitle("Outgoing Call");
                    alertDialogBuilder.setIcon(R.drawable.icon_phonecall32);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Do you want to call:" + '\n' + getCall02.toString() + " ?") // Click yes to exit!
                            .setCancelable(false)
                            .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);

                                    callIntent.setData(Uri.parse("tel:" + getCall02));
                                    startActivity(callIntent);
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();


//                dialog = builder.create();
                    alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);

                    // show it
                    alertDialog.show();
                }
            });
        }
        // add Alert Dialog End

        // add button listener
        getCall03 = tv5.getText().toString();
        if(getCall03.isEmpty()){
            Log.e("OrderDeliveryActivity", "phone_number:_null:_" + getCall03);

        } else {
            tv5.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    Log.e("OrderDeliveryActivity", "phone_number:_yes:_" + getCall03);
                    // set title
                    alertDialogBuilder.setTitle("Outgoing Call");
                    alertDialogBuilder.setIcon(R.drawable.icon_phonecall32);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Do you want to call:" + '\n' + getCall03.toString() + " ?") // Click yes to exit!
                            .setCancelable(false)
                            .setPositiveButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setData(Uri.parse("tel:" + getCall03));
                                    startActivity(callIntent);
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);

                    if(getCall03.equals("null")){
                        // show it
                        Log.e("Customer2Detail", "getCall03:NOnoNo_" + getCall03);
                    } else {
                        alertDialog.show();
                        Log.e("Customer2Detail", "getCall03:_" + getCall03);
                    }
                    // show it
//                    alertDialog.show();
                }
            });
        }

        // add Alert Dialog End
//        LinearLayout lnLeft = (LinearLayout) v.findViewById(R.id.LnLeft);
//        lnLeft.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
////                Intent i = new Intent(Customer2Detail.this, Customer1Activity2.class);
////                startActivity(i);
//            }
//        });

//        tvBack.setOnClickListener(new View.OnClickListener() {
//            //@Override
//            public void onClick(View v) {
////                Intent i = new Intent(Customer2Detail.this, Customer1Activity2.class);
////                startActivity(i);
//            }
//        });

        LinearLayout lnRight = (LinearLayout) v.findViewById(R.id.LnRight);
        lnRight.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                String get0Id = newId;
//                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//
//                Intent i = new Intent(Customer2Detail.this, Customer2Edit.class);
//                i.putExtra("getId", get0Id);
//                startActivity(i);
            }
        });


        tv0edit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                String get0Id = newId;
//                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//
//                Intent i = new Intent(Customer2Detail.this, Customer2Edit.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });


        tv0edit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                String get0Id = newId;
//                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//
//                Intent i = new Intent(Customer2Detail.this, Customer2Edit.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });

        tt07Contacts.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                String get0Id = newId;
//                Log.e("PlanActivity4", "Done_Id: " + get0Id);
//                Intent i = new Intent(Customer2Detail.this, ContactActivityFromDetail.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });
        tt07Contacts.setOnClickListener(this);

        tt08BillTo.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Customer2Detail.this, Customer2DetailBillTo.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });
        tt08BillTo.setOnClickListener(this);

        tt09ShipTo.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Customer2Detail.this, Customer2DetailShipTo.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });
        tt09ShipTo.setOnClickListener(this);

        lnInvoice.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(Customer2Detail.this, Customer2Detailinvoice.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                startActivity(i);
            }
        });

        // Original
//        setTitle(null);
//        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
//        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
//        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
//        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));
//
//        mTitle.setTypeface(fontThSarabunBold);
//        new AsyncTGETImage().execute();


        // SaleHistory
        mDBHelper3 = new InvoiceDatabaseHelper(getActivity());

//        File database = getApplicationContext().getDatabasePath(InvoiceDatabaseHelper.DBNAME);
//        if(false == database.exists()){
//
//            Toast.makeText(this, "Don't have Database", Toast.LENGTH_SHORT).show();
//
//            String str = database.getAbsolutePath();
//            Log.e("File database: ", "File database: " + str);
//
//            if(false == database.exists()){
//                mDBHelper3.getReadableDatabase();
//                //visitTarDBHelper.getReadableDatabase();
//                if(copyDatabase(this)){
//                    Toast.makeText(this, "YES!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this,"Copy data error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        }

        mDBHelper3 = new InvoiceDatabaseHelper(getActivity());
        getInvoice = new ArrayList<>();
        getInvoice = mDBHelper3.getListInvoice();

        mListView = (ListView) v.findViewById(R.id.list_salehistory);
        mListView.setTextFilterEnabled(true);

        mDBHelper3 = new InvoiceDatabaseHelper(getActivity());
        mDBHelper3.openDatabase();

        for (int i=0;i<getInvoice.size();i++){
            itemSaleHistories.add(new EntryItemSaleHistory(getInvoice.get(i).getNumber(),
                    getInvoice.get(i).getPostingdate()+"",
                    getInvoice.get(i).getDuedate()+"",
                    getInvoice.get(i).getTotal()));
        }

        EntryAdapterSaleHistory entry = new EntryAdapterSaleHistory(getActivity(), itemSaleHistories);
        mListView.setAdapter(entry);

    }

    public interface Communicator {
//        public void MessageBD(String OS_Name);
        //valueId
        public void Message2DetailContacts(String OS_Name, String get2Id,String  get0name,String  get1number);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvgoBack:
//                updateFragment("Back");
                break;
            case R.id.title07Contacts:

                communicator.Message2DetailContacts("Contacts", newId, newName, newNumber);
                break;
            case R.id.title08BillTo:
                communicator.Message2DetailContacts("BillTo", newId, newName, newNumber);
                break;
            case R.id.title09ShipTo:
                communicator.Message2DetailContacts("ShipTo", newId, newName, newNumber);
                break;

        }
    }

//    private void updateFragment(String OS_Name) {
//        communicator.MessageBD(OS_Name);
//        Log.e("MyListFragment","Row101_OS_Name:_" + OS_Name);
//    }

    // try to test
    // add phone to call
    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    isPhoneCalling = false;
                }

            }
        }
    }

    class ImageDownloader extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... param) {
            // TODO Auto-generated method stub
            return downloadBitmap(param[0]);
        }

        @Override
        protected void onPreExecute() {
            Log.i("Async-Example", "onPreExecute Called");
            simpleWaitDialog = ProgressDialog.show(getActivity(),
                    "Wait", "Downloading Image");

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("Async-Example", "onPostExecute Called");
            downloadedImg.setImageBitmap(result); //
//            circularImageViewBefore.setImageBitmap(result);
            saveImageToInternalStorage(result);
            simpleWaitDialog.dismiss();

        }

        private Bitmap downloadBitmap(String url) {
            // initilize the default HTTP client object
            final DefaultHttpClient client = new DefaultHttpClient();

            //forming a HttoGet request
            final HttpGet getRequest = new HttpGet(url);
            try {

                HttpResponse response = client.execute(getRequest);

                //check 200 OK for success
                final int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    Log.w("ImageDownloader", "Error " + statusCode +
                            " while retrieving bitmap from " + url);
                    return null;

                }

                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        // getting contents from the stream
                        inputStream = entity.getContent();

                        // decoding stream data back into image Bitmap that android understands
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                // You Could provide a more explicit error message for IOException
                getRequest.abort();
                Log.e("ImageDownloader", "Something went wrong while" +
                        " retrieving bitmap from " + url + e.toString());
            }

            return null;
        }
    }
    // Start
    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
// Use the compress method on the Bitmap object to write image to
// the OutputStream
            //FileOutputStream fos = context.openFileOutput("desiredFilename.png", Context.MODE_PRIVATE);
            //FileOutputStream fos = context.openFileOutput(imageId+".png", Context.MODE_PRIVATE);
            FileOutputStream fos = getActivity().openFileOutput(imageId+".jpg", Context.MODE_PRIVATE);

// Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", "why_cannot:_" + e.getMessage());
            return false;
        }
    }

    private boolean loadImageFromStorage(String imageId)
    {

        try {
            File f=new File(getActivity().getFilesDir(), imageId+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            if(b != null){
                chckImg = true;
                downloadedImg.setImageBitmap(b);
//                circularImageViewBefore.setImageBitmap(b);
                Log.e("loadImageFromStorage", "loadImageFromStorage:_chckImg_" + chckImg);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return chckImg;
    } // end loadImageFromStorage

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(InvoiceDatabaseHelper.DBNAME);
            String outFileName = InvoiceDatabaseHelper.DBLOCATION + InvoiceDatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
