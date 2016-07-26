package com.example.raymour.tintswear;

import android.media.Image;

/**
 * Created by raymour on 7/25/16.
 */
public class CustomProduct {

    String mItemTitle;
//    Image mItemImage;
    String  mItemPrice;
    String mItemDescription;

    public CustomProduct(){
        mItemTitle = "Item Title";
        mItemPrice = "29.99";
        mItemDescription = "Item Description";

    }

    public  CustomProduct(String itemTitle, String itemPrice, String itemDescription){
        mItemTitle = itemTitle;
        mItemPrice = itemPrice;
        mItemDescription = itemDescription;
    }

    public String getmItemTitle() {
        return mItemTitle;
    }

    public String getmItemPrice() {
        return mItemPrice;
    }

    public String getmItemDescription() {
        return mItemDescription;
    }

    public void setmItemTitle(String mItemTitle) {
        this.mItemTitle = mItemTitle;
    }

    public void setmItemPrice(String mItemPrice) {
        this.mItemPrice = mItemPrice;
    }

    public void setmItemDescription(String mItemDescription) {
        this.mItemDescription = mItemDescription;
    }



}
