package com.example.mathmodel.service;

import com.example.mathmodel.entity.MyAnswer;
import com.example.mathmodel.entity.MyLine;
import com.example.mathmodel.entity.MyLocation;

public class Calculator {
    public static MyAnswer calculate(MyLine line1, MyLine line2) {

        int x1 = line1.getPoint().getX();
        int y1 = line1.getPoint().getY();
        int z1 = line1.getPoint().getZ();
        int x2 = line2.getPoint().getX();
        int y2 = line2.getPoint().getY();
        int z2 = line2.getPoint().getZ();

        int l1 = line1.getL();
        int n1 = line1.getN();
        int m1 = line1.getM();
        int l2 = line1.getL();
        int n2 = line1.getN();
        int m2= line1.getM();


        if (isIntersect(line1, line2)) {
            return new MyAnswer(MyLocation.INTERSECTED,line1,line2);
        }

        else if( x1 == x2 && y1 == y2 && z1 == z2 && l1 == l2 && n1 == n2 && m1 == m2) {
            return new MyAnswer(MyLocation.EQUAL,line1,line2);
        }

        else if( x1 / x2 == y1 / y2 && x1 / x2 == z1 / z2) {
            return new MyAnswer(MyLocation.PARALLEL,line1,line2);
        }

        else if(isIntersect(line1,line2) && x1 * x2 + y1 * y2 + z1 * z2 == 0) {
            return new MyAnswer(MyLocation.PERPENDICULAR,line1,line2);
        }

        else return new MyAnswer(MyLocation.NO_COMMON_POINTS,line1,line2);
    }

    private static boolean isIntersect(MyLine line1, MyLine line2) {
        //
        return true;
    }
}
