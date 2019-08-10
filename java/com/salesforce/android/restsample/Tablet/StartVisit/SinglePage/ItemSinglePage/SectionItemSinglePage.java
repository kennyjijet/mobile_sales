package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chukeatboontae on 6/20/16.
 */
public class SectionItemSinglePage implements ItemSinglePage{

    private String name;
    private String onshelf;
    private String instock;
    private String avail;
    private String qty;
    private String foc;
    private String discount;
    private String amount;
    private String color;

    public SectionItemSinglePage(String name) {
        this.name = name;
        this.color = "#d2d2d2";
    }

    public SectionItemSinglePage(String name, String avail, String qty,
                                 String foc, String discount, String amount) {
        this.name = name;
        this.avail = avail;
        this.qty = qty;
        this.foc = foc;
        this.discount = discount;
        this.amount = amount;
    }

    public SectionItemSinglePage(String name, String avail, String onshelf, String instock,
                                 String qty, String foc, String discount, String amount, String color) {
        this.name = name;
        this.avail = avail;
        this.onshelf = onshelf;
        this.instock = instock;
        this.qty = qty;
        this.foc = foc;
        this.discount = discount;
        this.amount = amount;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getAvail() {
        return avail;
    }

    public String getOnshelf() {
        return onshelf;
    }

    public String getInstock() {
        return instock;
    }

    public String getQty() {
        return qty;
    }

    public String getFoc() {
        return foc;
    }

    public String getDiscount() {
        return discount;
    }

    public String getAmount() {
        return amount;
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
