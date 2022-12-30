package com.example.mathmodel;

import com.example.mathmodel.controller.MyController;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.mathmodel.entity.MyLine;
import com.example.mathmodel.entity.MyLocation;
import com.example.mathmodel.entity.MyPoint;
import com.example.mathmodel.entity.MyRecord;
import com.example.mathmodel.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MathModelApplicationTests {

    @Autowired
    private MyController controller;
    @Autowired
    private MyService service;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testEqual() throws InterruptedException {
        MyLine line1 = new MyLine(new MyPoint(0,0,0),3,3,3);
        MyLine line2 = new MyLine(new MyPoint(0,0,0),3,3,3);
        int id = service.bookCount(new MyRecord(line1,line2));
        Thread.sleep(1000);
        assertThat(service.getResult(id).getAnswer().getMyLocation() == MyLocation.EQUAL);
    }

    @Test
    public void tesParallel() throws InterruptedException {
        MyLine line1 = new MyLine(new MyPoint(1,2,3),3,2,3);
        MyLine line2 = new MyLine(new MyPoint(2,4,6),3,5,8);
        int id = service.bookCount(new MyRecord(line1,line2));
        Thread.sleep(1000);
        assertThat(service.getResult(id).getAnswer().getMyLocation()== MyLocation.PARALLEL);
    }

    @Test
    public void testPerpendicular() throws InterruptedException {
        MyLine line1 = new MyLine(new MyPoint(0,0,0),3,3,3);
        MyLine line2 = new MyLine(new MyPoint(0,0,0),3,3,3);
        int id = service.bookCount(new MyRecord(line1,line2));
        Thread.sleep(1000);
        assertThat(service.getResult(id).getAnswer().getMyLocation()== MyLocation.PERPENDICULAR);
    }

    @Test
    public void testIntersect() throws InterruptedException {
        MyLine line1 = new MyLine(new MyPoint(0,0,0),3,3,3);
        MyLine line2 = new MyLine(new MyPoint(0,0,0),3,3,3);
        int id = service.bookCount(new MyRecord(line1,line2));
        Thread.sleep(1000);
        assertThat(service.getResult(id).getAnswer().getMyLocation()== MyLocation.INTERSECTED);
    }

    @Test
    public void testNoCommonPoints() throws InterruptedException {
        MyLine line1 = new MyLine(new MyPoint(0,0,0),3,3,3);
        MyLine line2 = new MyLine(new MyPoint(0,0,0),3,3,3);
        int id = service.bookCount(new MyRecord(line1,line2));
        Thread.sleep(1000);
        assertThat(service.getResult(id).getAnswer().getMyLocation()== MyLocation.NO_COMMON_POINTS);
    }

}
