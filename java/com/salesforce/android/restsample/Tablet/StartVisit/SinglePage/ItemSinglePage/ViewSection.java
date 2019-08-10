package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 8/31/16 AD.
 */
public class ViewSection {

    private View baseView;
    TextView sName, sAvail, sOnshelf, sInstock, sQty, sFoc, sDis1, sAmount;
    LinearLayout lnBg;

    public ViewSection ( View baseView ) {
        this.baseView = baseView;
    }

    public View getViewBase ( ) {
        return baseView;
    }


    public TextView getsName (int resource) {
        if ( sName == null ) {
            sName = ( TextView ) baseView.findViewById(R.id.session_purchased_product);
        }
        return sName;
    }

    public TextView getsAvail (int resource) {
        if ( sAvail == null ) {
            sAvail = ( TextView ) baseView.findViewById(R.id.session_qty_price_unit);
        }
        return sAvail;
    }

    public TextView getsOnshelf (int resource) {
        if ( sOnshelf == null ) {
            sOnshelf = ( TextView ) baseView.findViewById(R.id.session_onshelf);
        }
        return sOnshelf;
    }

    public TextView getsInstock (int resource) {
        if ( sInstock == null ) {
            sInstock = ( TextView ) baseView.findViewById(R.id.session_instock);
        }
        return sInstock;
    }

    public TextView getsQty (int resource) {
        if ( sQty == null ) {
            sQty = ( TextView ) baseView.findViewById(R.id.session_quantity);
        }
        return sQty;
    }

    public TextView getsFoc (int resource) {
        if ( sFoc == null ) {
            sFoc = ( TextView ) baseView.findViewById(R.id.session_foc);
        }
        return sFoc;
    }

    public TextView getsDis1 (int resource) {
        if ( sDis1 == null ) {
            sDis1 = ( TextView ) baseView.findViewById(R.id.session_discount1);
        }
        return sDis1;
    }

    public TextView getsAmount (int resource) {
        if ( sAmount == null ) {
            sAmount = ( TextView ) baseView.findViewById(R.id.session_amount);
        }
        return sAmount;
    }

    public LinearLayout getlnBg (int resource) {
        if ( lnBg == null ) {
            lnBg = ( LinearLayout ) baseView.findViewById(R.id.lnbg);
        }
        return lnBg;
    }
}
