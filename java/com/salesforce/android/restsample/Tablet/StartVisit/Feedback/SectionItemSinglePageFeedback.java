package com.salesforce.android.restsample.Tablet.StartVisit.Feedback;

import com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage.ItemSinglePage;

/**
 * Created by chukeatboontae on 6/20/16.
 */
public class SectionItemSinglePageFeedback implements ItemSinglePage{

    private String name;
    private String color;

    public SectionItemSinglePageFeedback(String name) {
        this.name = name;
        this.color = "#d2d2d2";
    }

    public SectionItemSinglePageFeedback(String name, String color) {
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean isSection() {
        return true;
    }

    @Override
    public boolean isSubSection() {
        return false;
    }


}
