package com.salesforce.android.restsample.Tablet.StartVisit.TabletTab2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.AboutTime.ClassifiedTime;
import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderDetail.OrderDetail;
import com.salesforce.android.restsample.DB.Model.DBOrderDetail.OrderDetailDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderHeader.OrderHeader;
import com.salesforce.android.restsample.DB.Model.DBOrderHeader.OrderHeaderDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderPromotionTemporary.OrderPromotionTemporaryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporary;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporaryDatabaseHelper;
import com.salesforce.android.restsample.DB.Model.DBProduct.Product;
import com.salesforce.android.restsample.DB.Model.DBProduct.ProductDatabaseHelper;
import com.salesforce.android.restsample.MainFragment.CalculatePrice.OrderPrice;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.OrderDeliveryDetailActivity2MyCustomAdapter;
import com.salesforce.android.restsample.MainFragment.Tab2FragmentALLdetail.ordarConnectDB.orderTempListView;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;
import com.salesforce.android.restsample.Tablet.SharedPrf.SharedPrefOrderDelivery;
import com.salesforce.android.restsample.Tablet.StartVisit.Default.TabletTab2DeliveryDefault;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pannikar on 7/23/16 AD.
 */
public class TabletTab2setOrderDeliveryDetailActivity2 extends Fragment implements View.OnClickListener {
    //public class TabletTab2setOrderDeliveryDetailActivity2 extends Fragment{
    private CommunicatorV4 communicatorV4;
    private Typeface fontThSarabun;
    private Typeface fontThSarabunBold;
    private TextView toolbar_title;

    // set Detail
    // Date Picker
    private TextView tvDelDateDay;
    private TextView tvDevdate;
    private DatePicker dpResult;

    TextView tvtotalAmount, tvTitleRemain, tvBillto, tvShipto,
            tvBillto3, tvShipto3, tvDevdate3, tvDevdate5;

    private TextView tvgoBack, tvDone;
    TextView tvBillToShow, tvShipToShow;
    private int year;
    private int month;
    private int day;
    String setNameBillTo="", setNameShipTo="";

    static final int DATE_DIALOG_ID = 777;

    // set data to Database
    int idSV;
    int idsvst;
    String billtoid;
    String shiptoid;
    double discountDeli = 0;
    double discount1 = 0;
    int discount1Type = 0;
    double discount2 = 0;
    int discount2Type = 0;
    double discount3 = 0;
    int discount3Type = 0;
    int deliverydate;

    int delivdate;
    String ponumber;
    String remark;
    boolean issync;
    boolean isnew;
    String idserver;

    // get from Bundle Intent
    // set to OrderDetail Database

    String newId;
    int newIdOrder = 0;
    int newIdSV;
    String newTag;

    StringBuilder deliverydateStBd;
    String deliverydateSt;

    int deliverydateInt;

    String tvName;
    String tvCode;
    String tvValue;
    String tvDisc;
    String tvSub;
    String tvAll;
    String get2IdOrder;

    private ListView mListView2;

    orderTempListView orderTemp;

    // database
    OrderHeaderDatabaseHelper orderHeaderDBHelper;
    OrderDetailDatabaseHelper orderDetailDBHelper;
    OrderTemporaryDatabaseHelper orderTemporaryDBHelper;
    OrderPromotionTemporaryDatabaseHelper orderPromDBHelper;
    AddressDatabaseHelper addressDBHelper;

    ProductDatabaseHelper prodDBHelper;

    ClassifiedTime classifiedTime;

    EditText edPO;
    EditText editDiscount;
    EditText editRemarks;

    List<Integer> listFinal;
    List<OrderTemporary>  orderTmpListDB = new ArrayList<>();
    List<Product> productListDB = new ArrayList<>();
    List<OrderHeader> orderHeaders = new ArrayList<>();
    List<Address> billAddr = new ArrayList<>();
    List<Address> shipAddr = new ArrayList<>();

    OrderDeliveryDetailActivity2MyCustomAdapter dataAdapter;

    Context mContext;

    private Bundle args;
    private Fragment frg, frgDetail;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "myprefOrderDeliv";
    SharedPrefOrderDelivery shrPrfOrd;

    LinearLayout layBillTo, layShipTo;

    OrderDeliveryDetailActivity2MyCustomAdapter myTextwatchMain;

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

        View view = inflater.inflate(R.layout.detail_tab_2plan_delivery_detail_edit, container, false);
        // Initialize Views

        args = new Bundle();

        setDatabase(getActivity());
        setLayout(view);
        classifiedTime = new ClassifiedTime();

        newId = getArguments().getString("getId");
        newIdSV = getArguments().getInt("getIdSV");
        newTag = getArguments().getString("getTag");

//        GetProductSinglePage gps = new GetProductSinglePage(getActivity(), "0012800000aIvoHAAS");

        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        shrPrfOrd = new SharedPrefOrderDelivery(sharedpreferences, getActivity());

        if (!(getArguments().getString("getIdOrder") == null)){
            newIdOrder = Integer.parseInt(getArguments().getString("getIdOrder"));
        }

        Log.e("chkOrderId","chkOrderId_"+newIdOrder);
        Log.e("chkOrderId","chkTag_"+newTag);

        switch (newTag){
            case "Ordered" :
                Log.e("chkOrderId","chkOrderId----------Order----------");
                orderHeaders = orderHeaderDBHelper.getListOrderListBySearchIdOrder(newIdOrder);
                billAddr = addressDBHelper.getListAddressSearchByIdAddress(orderHeaders.get(0).getIdBill());
                shipAddr = addressDBHelper.getListAddressSearchByIdAddress(orderHeaders.get(0).getIdShip());
                shrPrfOrd.SaveBillToId(orderHeaders.get(0).getIdBill());
                shrPrfOrd.SaveShipToId(orderHeaders.get(0).getIdShip());

                tvBillToShow.setText(billAddr.get(0).getName());
                tvShipToShow.setText(shipAddr.get(0).getName());
                tvDelDateDay.setText(classifiedTime.getFormatDateNoTime(orderHeaders.get(0).getDelivDate()));
                delivdate = orderHeaders.get(0).getDelivDate();
                edPO.setText(orderHeaders.get(0).getPoNumber());
                editDiscount.setText(orderHeaders.get(0).getDiscPercent()+"");
                editRemarks.setText(orderHeaders.get(0).getRemark());
                break;
            case "billAddress" :
                Log.e("chkOrderId","chkOrderId----------Bill Address----------");
                shrPrfOrd.SaveBillToId(getArguments().getString("getAddrId"));
                tvBillToShow.setText(shrPrfOrd.getBilltoSvst("billto"+Integer.toString(newIdSV)));
                tvShipToShow.setText(shrPrfOrd.getShiptoSvst("shipto"+Integer.toString(newIdSV)));
                break;
            case "shipAddress" :
                Log.e("chkOrderId","chkOrderId----------Ship Address----------");
                shrPrfOrd.SaveShipToId(getArguments().getString("getAddrId"));
                tvBillToShow.setText(shrPrfOrd.getBilltoSvst("billto"+Integer.toString(newIdSV)));
                tvShipToShow.setText(shrPrfOrd.getShiptoSvst("shipto"+Integer.toString(newIdSV)));
                break;
            case "addNew" :
                Log.e("chkOrderId","chkOrderId----------Add New----------");
                shrPrfOrd.ClearPref();
                newIdOrder = 0;
                tvBillToShow.setText("");
                tvShipToShow.setText("");
                break;
        }


        setCurrentDateOnView(view);
        addListenerOnButton0();

        // set Detail
        orderTemp = new orderTempListView();
        orderTmpListDB = orderTemporaryDBHelper.getListOrderTempListBySearchId(newIdSV);

        Log.e("OrderDeliveryActivity", "Order_size_orderTmpListDB: " + orderTmpListDB.size());

        ArrayList<OrderPrice> odrPriceList = setListOrderPrice(orderTmpListDB);

        dataAdapter = new OrderDeliveryDetailActivity2MyCustomAdapter(getActivity(),
                R.layout.item_order_detail,
                odrPriceList);
        mListView2.setAdapter(dataAdapter);


        tvBillto.setOnClickListener(this);
        tvShipto.setOnClickListener(this);

        tvgoBack.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(OrderDeliveryDetailActivity2.this, OrderDeliveryActivity.class);
//                i.putExtra("getId", newId);
//                i.putExtra("getIdSV", newIdSV);
//                startActivity(i);
            }
        });

        tvDone.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                ponumber = edPO.getText().toString();
                remark = editRemarks.getText().toString();
//                discountDeli = Double.parseDouble(editDiscount.getText().toString());
                issync = false;
                isnew = false;
                idserver = "";
                if (idsvst == 0){
                    idsvst = newIdSV;
                }

                billtoid = shrPrfOrd.getbilltoId();
                shiptoid = shrPrfOrd.getshiptoId();
                Log.e("chkShrBill_DB","chkShrBill_"+billtoid);
                Log.e("chkShrBill_DB","chkShrShip_"+shiptoid);

                int idordHeader = orderHeaderDBHelper.getLastIdOrderHeader();

                if (newIdOrder == 0 ) {
                    myTextwatchMain = new OrderDeliveryDetailActivity2MyCustomAdapter(getActivity(), idordHeader, idsvst, orderTmpListDB.size());
                }else {
                    myTextwatchMain = new OrderDeliveryDetailActivity2MyCustomAdapter(getActivity(), newIdOrder, idsvst, orderTmpListDB.size());
                }

                if (editDiscount.getText().toString().isEmpty()){
                    Log.e("chkNull","chkNull_Dis_isNull");
                    discountDeli = 0;
                }else {
                    discountDeli = Double.parseDouble(editDiscount.getText().toString());
                }

                if (billtoid.isEmpty() || shiptoid.isEmpty() || ponumber.isEmpty() || myTextwatchMain.lstQuantity2DB.size() == 0){
                    Log.e("chkNull","chkNull_isNull");

                }else {

                    // set method in OrderDeliveryDetailActivity2MyCustomAdapter to do
                    if (newIdOrder == 0 ) {
                        orderHeaderDBHelper.addOrderHeader(newIdSV, billtoid, shiptoid, discountDeli, delivdate,
                                ponumber, remark, issync, isnew, idserver); // save to database
                    }else {
                        orderHeaderDBHelper.updateOrderHeader(newIdOrder, newIdSV, billtoid, shiptoid, discountDeli, delivdate,
                                ponumber, remark, issync, isnew, idserver);
                    }

                    List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
                    Log.e("chkDetSize","chlDetSize_"+orderDetails.size());
                    Log.e("chkidOrd","chkidOrd_"+idordHeader);
                    orderDetails = orderDetailDBHelper.getListOrderDetailListBySearchIdOrder(newIdOrder);

                    Log.e("OrderDeliveryActivity", "TextWatcher_3-4_SET-2_ " + myTextwatchMain.lstProdCode2DBText.size());
                    for(int i=0; i<orderTmpListDB.size(); i++ ){
                        Log.e("OrderDeliveryActivity", "TextWatcher_3-5_SET-2_orderTmpListDB.size() " + orderTmpListDB.size());
                        Log.e("OrderDeliveryActivity", "TextWatcher_3-5_SET-2_ " + idordHeader);

                        if (orderDetails.size() == 0) {
                            if (myTextwatchMain.lstQuantity2DB.get(i) <= orderTmpListDB.get(i).getQuantity()) {
                                orderDetailDBHelper.addOrderDetail(idordHeader,
                                        newIdSV,
                                        myTextwatchMain.lstLinenum2DB.get(i),
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        myTextwatchMain.lstQuantity2DB.get(i),//quantity,
                                        myTextwatchMain.lstFOC2DB.get(i),//foc,
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent3type(),
                                        0,
                                        0);//discpercent);
                                orderTemporaryDBHelper.updateOrderTemp(newIdSV,
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        orderTmpListDB.get(i).getQuantity() - myTextwatchMain.lstQuantity2DB.get(i),
//                                        orderTmpListDB.get(i).getRemaining() - myTextwatchMain.lstQuantity2DB.get(i),
                                        0,
                                        orderTmpListDB.get(i).getFoc(),
                                        orderTmpListDB.get(i).getFocreaming(),
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3type());
                            } else {
                                orderDetailDBHelper.addOrderDetail(idordHeader,
                                        newIdSV,
                                        myTextwatchMain.lstLinenum2DB.get(i),
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        orderTmpListDB.get(i).getQuantity(),//quantity,
                                        myTextwatchMain.lstFOC2DB.get(i),//foc,
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent3type(),
                                        myTextwatchMain.lstQuantity2DB.get(i) - orderTmpListDB.get(i).getQuantity(),
                                        0);//discpercent);
                                orderTemporaryDBHelper.updateOrderTemp(newIdSV,
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        0,
//                                        orderTmpListDB.get(i).getRemaining() - myTextwatchMain.lstQuantity2DB.get(i),
                                        0,
                                        orderTmpListDB.get(i).getFoc(),
                                        orderTmpListDB.get(i).getFocreaming(),
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3type());
                            }
                        }else {
                            if (myTextwatchMain.lstQuantity2DB.get(i) <= orderTmpListDB.get(i).getQuantity()) {
                                orderDetailDBHelper.updateOrderDetail(orderDetails.get(0).getId(),
                                        newIdOrder,
                                        newIdSV,
                                        myTextwatchMain.lstLinenum2DB.get(i),
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        myTextwatchMain.lstQuantity2DB.get(i),//quantity,
                                        myTextwatchMain.lstFOC2DB.get(i),//foc,
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent3type(),
                                        0,
                                        0);//discpercent);
                                orderTemporaryDBHelper.updateOrderTemp(newIdSV,
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        orderTmpListDB.get(i).getQuantity() - myTextwatchMain.lstQuantity2DB.get(i),
//                                        orderTmpListDB.get(i).getRemaining() - myTextwatchMain.lstQuantity2DB.get(i),
                                        0,
                                        orderTmpListDB.get(i).getFoc(),
                                        orderTmpListDB.get(i).getFocreaming(),
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3type());
                            } else {
                                orderDetailDBHelper.updateOrderDetail(orderDetails.get(0).getId(),
                                        newIdOrder,
                                        newIdSV,
                                        myTextwatchMain.lstLinenum2DB.get(i),
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        orderTmpListDB.get(i).getQuantity(),//quantity,
                                        myTextwatchMain.lstFOC2DB.get(i),//foc,
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent3type(),
                                        myTextwatchMain.lstQuantity2DB.get(i) - orderTmpListDB.get(i).getQuantity(),
                                        0);//discpercent);
                                orderTemporaryDBHelper.updateOrderTemp(newIdSV,
                                        myTextwatchMain.lstProdCode2DB.get(i),
                                        0,
//                                        orderTmpListDB.get(i).getRemaining() - myTextwatchMain.lstQuantity2DB.get(i),
                                        0,
                                        orderTmpListDB.get(i).getFoc(),
                                        orderTmpListDB.get(i).getFocreaming(),
                                        orderTmpListDB.get(i).getDispercent1(),
                                        orderTmpListDB.get(i).getDispercent2(),
                                        orderTmpListDB.get(i).getDispercent3(),
                                        orderTmpListDB.get(i).getDispercent1type(),
                                        orderTmpListDB.get(i).getDispercent2type(),
                                        orderTmpListDB.get(i).getDispercent3type());
                            }
                        }
//                    Log.e("OrderDeliveryActivity", "TextWatcher_3-6_SET-2_ " + myTextwatchMain.lstProdCode2DBText.get(i).toString());
                    }

                    Log.e("OrderDeliveryActivity", "TextWatcher_2_myTextwatch_4_List: " + " ////  ////  ////  ////  ////  ////  //// ");

                    frg = new TabletTab2setOrderDeliveryActivity();
                    args.putString("getId", newId);
                    args.putInt("getIdSV", newIdSV);
                    frg.setArguments(args);
                    communicatorV4.MessageTab(0,frg);

                    frgDetail = new TabletTab2DeliveryDefault();
                    communicatorV4.MessageTab(1,frgDetail);
                }

                shrPrfOrd.ClearPref(); //here
//                shrPrfOrd.SaveBillToSv("billto"+Integer.toString(newIdSV), "");
//                shrPrfOrd.SaveShipToSv("shipto"+Integer.toString(newIdSV), "");

            }
        });


        return view;
    }


    void setLayout(View v){


        fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

//        tvgoBack = (TextView) view.findViewById(R.id.tvgoBack);
        toolbar_title = (TextView) v.findViewById(R.id.toolbar_title);

//        tvgoBack.setTypeface(fontThSarabun);
        toolbar_title.setTypeface(fontThSarabunBold);
        mListView2 = (ListView) v.findViewById(R.id.list);
        listFinal = new ArrayList<Integer>();

        tvgoBack = (TextView) v.findViewById(R.id.tvgoCancel);
        tvDone = (TextView) v.findViewById(R.id.toolbar_done);
        tvBillToShow = (TextView) v.findViewById(R.id.tvBillToShow);
        tvShipToShow = (TextView) v.findViewById(R.id.tvShipToShow);
        tvtotalAmount = (TextView) v.findViewById(R.id.tvtotalAmount);
        tvTitleRemain = (TextView) v.findViewById(R.id.tvTitleRemain);

        tvBillto = (TextView) v.findViewById(R.id.tvBillTo);
        tvShipto = (TextView) v.findViewById(R.id.tvShipTo);
        tvDevdate = (TextView) v.findViewById(R.id.tvDelDate);
        tvDelDateDay = (TextView) v.findViewById(R.id.tvDelDateDay);

        tvBillto3 = (TextView) v.findViewById(R.id.tvPO);
        tvShipto3 = (TextView) v.findViewById(R.id.tvDis);
        tvDevdate3 = (TextView) v.findViewById(R.id.tvRemarks);
        edPO = (EditText) v.findViewById(R.id.editPO);
        editDiscount = (EditText) v.findViewById(R.id.editDiscount);
        editRemarks = (EditText) v.findViewById(R.id.editRemarks);
        tvDevdate5 = (TextView) v.findViewById(R.id.tvItems);

        layBillTo = (LinearLayout) v.findViewById(R.id.layBillTo);
        layBillTo.setOnClickListener(this);
        //layShipto
        layShipTo = (LinearLayout) v.findViewById(R.id.layShipTo);
        layShipTo.setOnClickListener(this);

        tvgoBack.setTypeface(fontThSarabunBold);
        tvDone.setTypeface(fontThSarabunBold);
        tvBillToShow.setTypeface(fontThSarabun);
        tvShipToShow.setTypeface(fontThSarabun);
        tvBillToShow.setText(setNameBillTo);
        tvShipToShow.setText(setNameShipTo);
        tvgoBack.setTypeface(fontThSarabunBold);
        tvtotalAmount.setTypeface(fontThSarabun);
        tvTitleRemain.setTypeface(fontThSarabun);
        // add to listView End

        tvBillto.setTypeface(fontThSarabunBold);
        tvShipto.setTypeface(fontThSarabunBold);
        tvDevdate.setTypeface(fontThSarabunBold);

        // tvDelDateDay

        tvDelDateDay.setTypeface(fontThSarabunBold);

        tvBillto3.setTypeface(fontThSarabunBold);
        tvShipto3.setTypeface(fontThSarabunBold);
        tvDevdate3.setTypeface(fontThSarabunBold);
        edPO.setTypeface(fontThSarabunBold);
        editDiscount.setTypeface(fontThSarabunBold);
        editRemarks.setTypeface(fontThSarabunBold);
        tvDevdate5.setTypeface(fontThSarabunBold);
    }

    void setDatabase(Context context){
        orderHeaderDBHelper = new OrderHeaderDatabaseHelper(context);
        orderDetailDBHelper = new OrderDetailDatabaseHelper(context);
        orderTemporaryDBHelper = new OrderTemporaryDatabaseHelper(context);
        orderPromDBHelper = new OrderPromotionTemporaryDatabaseHelper(context);
        prodDBHelper = new ProductDatabaseHelper(context);
        addressDBHelper = new AddressDatabaseHelper(context);
    }

    // set to OrderPrice Start
    public ArrayList<OrderPrice> setListOrderPrice(List<OrderTemporary> orderTmpListDB){

        ArrayList<OrderPrice> odrPriceList =  new ArrayList<OrderPrice>();
        if(orderTmpListDB.size() != 0){

            for(int i=0; i<orderTmpListDB.size(); i++){

                Log.e("OrderDeliveryActivity", "TextWatcher_Order_size_orderTmpListDB: " + orderTmpListDB.size());

                productListDB = prodDBHelper.getListProductListBySearchCode(orderTmpListDB.get(i).getProductcode());

                tvName = productListDB.get(0).getName();
                // tvCode = orderTmpListDB.get(i).getProductcode()+", ";
                tvCode = orderTmpListDB.get(i).getProductcode();
                tvValue = String.valueOf(productListDB.get(0).getUnitprice());
                //tvDisc = String.valueOf(orderTmpListDB.get(i).getDispercent());
                tvDisc = String.valueOf(orderTmpListDB.get(i).getDispercent1());
                tvSub = "0";
                tvAll = String.valueOf(orderTmpListDB.get(i).getQuantity());

                double priceRemain = productListDB.get(0).getUnitprice()
                        *(double)orderTmpListDB.get(i).getQuantity()
                        *(orderTmpListDB.get(i).getDispercent1()/100);

                OrderPrice odrPrice = new OrderPrice(tvName,
                        tvCode,
                        productListDB.get(0).getUnitprice(),
                        orderTmpListDB.get(i).getDispercent1(),
                        orderTmpListDB.get(i).getQuantity(),
                        priceRemain);
                odrPriceList.add(odrPrice);
            }
        }
        return odrPriceList;
    }
    // set to OrderPrice End

    void setIdSV(int idSV){
        idsvst = idSV;
    }

    void setBillToAfterSelectAddress(String name){
        setNameBillTo = name;
    }

    void setShipToAfterSelectAddress(String name){
        setNameShipTo = name;
    }

    void setDeliveryDate(int idate){
        deliverydate = idate;
    }

    void setBillToId(String addressId){
        billtoid = addressId;
    }

    void setShipToId(String addressId){
        shiptoid = addressId;
    }

    // display current Date
    public void setCurrentDateOnView(View v) {

        dpResult = (DatePicker) v.findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        getDateToDB(year, month, day);

        delivdate = classifiedTime.componentTimeToTimestamp(year, month, day);

        // set selected date into textview
        tvDelDateDay.setText(classifiedTime.getFormatDateNoTime(delivdate));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }


    public void addListenerOnButton0() {

        tvDelDateDay.setOnClickListener(new View.OnClickListener() { //tvDisplayDate //tvTextDate

            @Override
            public void onClick(View v) {

//                showDialog(DATE_DIALOG_ID);

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

            getDateToDB(year, month, day);

            delivdate = classifiedTime.componentTimeToTimestamp(year, month, day);

            // set selected date into textview
            tvDelDateDay.setText(classifiedTime.getFormatDateNoTime(delivdate));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);
        }
    };

    public void getDateToDB(int year, int month, int day){

        String monthSt = "";
        String daySt = "";
        month = month + 1;
        if (month<10){
            monthSt = String.valueOf("0"+(month));
        } else {
            monthSt = String.valueOf(month);
        }

        if(day<10){
            daySt = String.valueOf("0" + day);
        } else {
            daySt = String.valueOf(day);
        }

        deliverydateStBd = new StringBuilder().append(monthSt).append(daySt).append(year);
        deliverydateSt = deliverydateStBd.toString();
        deliverydateInt = Integer.parseInt(deliverydateSt);

        setDeliveryDate(deliverydateInt);

        //return deliverydateInt;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvBillTo:
                updateFragment("Billto");
                break;
            case R.id.layBillTo:
                updateFragment("Billto");
                break;

            case R.id.tvShipTo:
                updateFragment("ShipTo");
                break;
            case R.id.layShipTo:
                updateFragment("ShipTo");
                break;

        }
    }

    private void updateFragment(String OS_Name) {
        if(OS_Name == "Billto"){
            frg = new TabletTab2setOrderSelectAddressBillTo();
            args.putString("getId", newId);
            args.putInt("getIdSV", newIdSV);
            args.putString("getIdOrder", ""+newIdOrder);
            frg.setArguments(args);
            communicatorV4.MessageTab(1,frg);
        }else if (OS_Name == "ShipTo"){
            frg = new TabletTab2setOrderSelectAddressShipTo();
            args.putString("getId", newId);
            args.putInt("getIdSV", newIdSV);
            args.putString("getIdOrder", ""+newIdOrder);
            frg.setArguments(args);
            communicatorV4.MessageTab(1,frg);
        }
    }
}
