package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;

import android.view.View;

/**
 * Created by pannikar on 8/31/16 AD.
 */
public class ViewEntry {
    private View baseView;

    public ViewEntry ( View baseView ) {
        this.baseView = baseView;
    }

    public View getViewBase ( ) {
        return baseView;
    }
}
