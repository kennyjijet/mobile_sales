package com.salesforce.android.restsample.Tablet.StartVisit.SinglePage.ItemSinglePage;


/**
 * Created by chukeatboontae on 6/20/16.
 */
public class EntryItemSinglePage implements ItemSinglePage {

    public String name;
    public String number;
//    public int availQty;
    public int availQty;
    public String price;
    public double onshelf;
    public double instock;
    public int qty;
    public int foc;
    public double discount1;
    public double amount;

    public EntryItemSinglePage(String name, String number, int availQty, String price, double amount) {
        this.name = name;
        this.number = number;
        this.availQty = availQty;
        this.price = price;
        this.amount = amount;

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
