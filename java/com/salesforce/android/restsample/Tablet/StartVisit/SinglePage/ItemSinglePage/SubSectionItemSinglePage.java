package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;

/**
 * Created by pannikar on 9/12/16 AD.
 */
public class SubSectionItemSinglePage implements ItemSinglePage{

    private String name;
    private String color;

    public SubSectionItemSinglePage(String name) {
        this.name = name;
        this.color = "#d2d2d2"; //"#d22222";
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean isSection() {
        return false;
    }
    @Override
    public boolean isSubSection() {
        return true;
    }

}
