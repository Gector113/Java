package com.example.mathmodel.entity;

import com.example.mathmodel.service.Calculator;

public class MyRecord {
    private int recordId;
    private MyAnswer answer;
    private MyLine line1;
    private MyLine line2;

    public MyRecord(MyLine line1, MyLine line2) {
        this.line1 = line1;
        this.line2 = line2;
        this.answer = Calculator.calculate(line1, line2);
    }


    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public MyLine getLine1() {
        return line1;
    }

    public void setLine1(MyLine line1) {
        this.line1 = line1;
    }

    public MyLine getLine2() {
        return line2;
    }

    public void setLine2(MyLine line2) {
        this.line2 = line2;
    }

    public MyAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(MyAnswer answer) {
        this.answer = answer;
    }
}
