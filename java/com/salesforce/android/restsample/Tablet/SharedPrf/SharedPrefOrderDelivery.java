package com.salesforce.android.restsample.Tablet.SharedPrf;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by pannikar on 7/15/16 AD.
 */
public class SharedPrefOrderDelivery {

    public static SharedPreferences sharedpreferences;
    Context mContext;

    public static String billtoSvst;
    public static String shiptoSvst;

    public static String billto = "billto";
    public static String shipto = "shipto";

    public static String billtoId = "billtoId";
    public static String shiptoId = "shiptoId";

    SharedPreferences.Editor editor;

    public SharedPrefOrderDelivery(SharedPreferences sharedpreferences,
                                   Context mContext){

        this.sharedpreferences = sharedpreferences;
        this.mContext = mContext;

    }

    // set BillTo and Svst
    public void SaveBillToSv(String billtoSvst, String billto) {
        this.billtoSvst = billtoSvst;
        editor = sharedpreferences.edit();
        editor.putString(this.billtoSvst, billto);
        editor.commit();
    }

    public String getBilltoSvst(String billtoSvst) {
        billto = sharedpreferences.getString(this.billtoSvst, "");
        return billto;
    }

    // set ShipTo and Svst
    public void SaveShipToSv(String shiptoSvst, String shipto) {
        this.shiptoSvst = shiptoSvst;
        editor = sharedpreferences.edit();
        editor.putString(this.shiptoSvst, shipto);
        editor.commit();
    }

    public String getShiptoSvst(String shiptoSvst) {
        shipto = sharedpreferences.getString(this.shiptoSvst, "");
        return shipto;
    }



    // clear

    public void SaveBillTo(String billto) {
        editor = sharedpreferences.edit();
        editor.putString(this.billto, billto);
        editor.commit();
    }

    public void SaveShipTo(String billto) {
        editor = sharedpreferences.edit();
        editor.putString(this.shipto, billto);
        editor.commit();
    }

    public void ClearPref() {
        editor = sharedpreferences.edit();
        editor.clear().commit();
//        editor = sharedpreferences.edit();
//        editor.putString(this.billtoId, "");
//        editor.putString(this.shiptoId, "");
//        editor.commit();
    }

    // set Id
    public void SaveBillToId(String billtoId) {
        editor = sharedpreferences.edit();
        editor.putString(this.billtoId, billtoId);
        editor.commit();
    }

    public void SaveShipToId(String billtoId) {
        editor = sharedpreferences.edit();
        editor.putString(this.shiptoId, billtoId);
        editor.commit();
    }

    public String getbillto() {
       billto= sharedpreferences.getString(billto, "");
        return billto;
    }

    public static String getshipto() {
        shipto = sharedpreferences.getString(shipto, "");
        return shipto;
    }

    public static String getbilltoId() {
        billtoId = sharedpreferences.getString(billtoId, "");
        return billtoId;
    }

    public static String getshiptoId() {
        shiptoId = sharedpreferences.getString(shiptoId, "");
        return shiptoId;
    }
}
