package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage;

import android.content.Context;
import android.util.Log;

import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporary;
import com.salesforce.android.restsample.DB.Model.DBOrderTemporary.OrderTemporaryDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 9/12/16 AD.
 */
public class GetValueToEditText {

    int getQuantity;
    Context mContext;
    public List<OrderTemporary> orderTempList = new ArrayList<>();
    OrderTemporaryDatabaseHelper mDBorderTemp;

    /*
    *
    * Getting Size
    *
    * */

    public GetValueToEditText(Context mContext, int searchById){
        this.mContext = mContext;
        mDBorderTemp = new OrderTemporaryDatabaseHelper(mContext);

        this.orderTempList = mDBorderTemp.getListOrderTempListBySearchId(searchById);
//        Log.e("test",  "arrQuantityNot: this: " + this.orderTempList.size());
    }

    /*
    *
    *
    *
    * */

    GetValueToEditText(Context mContext, int searchById, String byCode){
        this.mContext = mContext;
        mDBorderTemp = new OrderTemporaryDatabaseHelper(mContext);

        this.orderTempList = mDBorderTemp.getListOrderTempListBySearchIdandCode(searchById, byCode);
    }

    /*
    *
    * Refresh Data After Delete
    *
    * */

    public GetValueToEditText(Context mContext, int searchById, String byCode, String deleteThis){
        this.mContext = mContext;
        mDBorderTemp = new OrderTemporaryDatabaseHelper(mContext);

        mDBorderTemp.deleteOrderTemporary(searchById, byCode);
    }

    public GetValueToEditText(Context mContext,
                              int idSvst,
                              String productcode,
                              int quantity,
                              int remaining,
                              int foc,
                              int focreaming,
                              double dispercent1,
                              double dispercent2,
                              double dispercent3,

                              int dispercent1type,
                              int dispercent2type,
                              int dispercent3type){
        this.mContext = mContext;

        mDBorderTemp = new OrderTemporaryDatabaseHelper(mContext);

        getQuantity = mDBorderTemp.updateOrderTempOrNot(idSvst, productcode);

        if (getQuantity == 0){
            mDBorderTemp.addOrderTemp(  idSvst,
                                        productcode,
                                        quantity,
                                        remaining,
                                        foc,
                                        focreaming,
                                        dispercent1,
                                        dispercent2,
                                        dispercent3,

                                        dispercent1type,
                                        dispercent2type,
                                        dispercent3type);

        } else if(getQuantity>0){
            mDBorderTemp.updateOrderTemp(idSvst,
                                            productcode,
                                            quantity,
                                            remaining,
                                            foc,
                                            focreaming,
                                            dispercent1,
                                            dispercent2,
                                            dispercent3,

                                            dispercent1type,
                                            dispercent2type,
                                            dispercent3type);
//            Log.e("test", "contain: position: UPDATE");
//            Log.e("test", "contain: position:  UPDATE: idsvst: " + idSvst +" "+ productcode +" Quantity: "+ getQuantity);

        }
    }


}
