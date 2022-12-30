package com.example.mathmodel.entity;

public class MyAnswer {

    private MyLocation myLocation;
    private String result;

    public MyAnswer(MyLocation mLoc, MyLine line1, MyLine line2) {
        this.myLocation = mLoc;
        String desc = null;
        switch (myLocation) {
            case EQUAL -> desc = " є копіями з ";
            case INTERSECTED -> desc = " перетинається з ";
            case PARALLEL -> desc = " є паралельною з ";
            case PERPENDICULAR ->  desc = " є перпендикулярна з ";
            case NO_COMMON_POINTS -> desc = " не має спільних точок з";
        }
        this.result = "Пряма " + line1.toString() + desc + " прямою " + line2.toString();
    }

    public MyLocation getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(MyLocation myLocation) {
        this.myLocation = myLocation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
