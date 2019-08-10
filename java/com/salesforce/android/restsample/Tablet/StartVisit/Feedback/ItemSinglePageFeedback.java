package com.salesforce.android.restsample.Tablet.StartVisit.Feedback;


import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;

/**
 * Created by chukeatboontae on 6/20/16.
 */
public class ItemSinglePageFeedback implements ItemSinglePage {

    public String name;
    public String number;

    public ItemSinglePageFeedback(String name, String number) {
        this.name = name;
        this.number = number;

    }

    @Override
    public boolean isSection() {
        return false;
    }

    @Override
    public boolean isSubSection() {
        return false;
    }
}
