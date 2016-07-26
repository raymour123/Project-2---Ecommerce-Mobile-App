package com.example.raymour.tintswear;

/**
 * Created by raymour on 7/26/16.
 */
public class SunglassSale {
    private String sunglassSaleName;
    private String sunglassSalePrice;
    private String sunglassCouponCode;

    public SunglassSale(String sunglassSale, String sunglassSalePrice, String sunglassCouponCode){
        this.sunglassSaleName = sunglassSale;
        this.sunglassSalePrice = sunglassSalePrice;
        this.sunglassCouponCode = sunglassCouponCode;
    }

    public String getSunglassSaleName() {
        return sunglassSaleName;
    }

    public void setSunglassSaleName(String sunglassSale) {
        this.sunglassSaleName = sunglassSale;
    }

    public String getSunglassSalePrice() {
        return sunglassSalePrice;
    }

    public void setSunglassSalePrice(String sunglassSalePrice) {
        this.sunglassSalePrice = sunglassSalePrice;
    }

    public String getSunglassCouponCode() {
        return sunglassCouponCode;
    }

    public void setSunglassCouponCode(String sunglassCouponCode) {
        this.sunglassCouponCode = sunglassCouponCode;
    }
}
