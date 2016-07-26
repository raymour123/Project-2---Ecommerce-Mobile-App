package com.example.raymour.tintswear;

/**
 * Created by raymour on 7/26/16.
 */
public class Inventory {
    private String sunglassName;
    private String sunglassImageLocation;
    private String sunglassPrice;
    private String sunglassDescription;

    public Inventory(String sunglassName, String sunglassImageLocation, String sunglassPrice, String sunglassDescription){
        this.sunglassName = sunglassName;
        this.sunglassImageLocation = sunglassImageLocation;
        this.sunglassPrice = sunglassPrice;
        this.sunglassDescription = sunglassDescription;
    }


    public String getSunglassDescription() {
        return sunglassDescription;
    }

    public void setSunglassDescription(String sunglassDescription) {
        this.sunglassDescription = sunglassDescription;
    }

    public String getSunglassName() {
        return sunglassName;
    }

    public void setSunglassName(String sunglassName) {
        this.sunglassName = sunglassName;
    }

    public String getSunglassImageLocation() {
        return sunglassImageLocation;
    }

    public void setSunglassImageLocation(String sunglassImageLocation) {
        this.sunglassImageLocation = sunglassImageLocation;
    }

    public String getSunglassPrice() {
        return sunglassPrice;
    }

    public void setSunglassPrice(String sunglassPrice) {
        this.sunglassPrice = sunglassPrice;
    }
}
