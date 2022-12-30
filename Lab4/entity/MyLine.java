package com.example.mathmodel.entity;

public class MyLine {

    public MyLine() {

    }

    //dot M
    private MyPoint point;

    //vector S
    private int l;
    private int m;
    private int n;

    public MyLine(MyPoint point1, int l, int n, int m) {
        this.point = point1;
        this.l = l;
        this.n = n;
        this.m = m;
    }

    public MyPoint getPoint() {
        return point;
    }

    public void setPoint(MyPoint point) {
        this.point = point;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "(x-"+ getPoint().getX()+")/"+getL()+" = " + "(y-"+ getPoint().getY()+")/"+getN()+" = " + "(z-"+ getPoint().getZ()+")/"+getM();
    }
}
