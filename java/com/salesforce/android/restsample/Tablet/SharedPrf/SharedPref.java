package com.salesforce.android.restsample.Tablet.SharedPrf;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by pannikar on 7/15/16 AD.
 */
public class SharedPref {

    public static SharedPreferences sharedpreferences;
    Context mContext;

    public static String id = "idKey";
    public static String idSvst = "idSvstKey";

    public SharedPref(SharedPreferences sharedpreferences,
                      Context mContext){

        this.sharedpreferences = sharedpreferences;
        this.mContext = mContext;

    }

    public void Save(String setId, String setSvst) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(this.id, setId);
        editor.putString(this.idSvst, setSvst);
        editor.commit();

        Log.e("Class31SharedPref2","chk1_setId:_"+ setId);
        Log.e("Class31SharedPref2","chk1_id:_"+ id);
        Log.e("Class31SharedPref2","chk1_this.id:_"+ this.id);
    }


    public static String getId() {
        id = sharedpreferences.getString(id, "");
        return id;
    }

    public static String getIdSvst() {
        idSvst = sharedpreferences.getString(idSvst, "");
        return idSvst;
    }
}
