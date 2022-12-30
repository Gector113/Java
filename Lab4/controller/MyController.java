package com.example.mathmodel.controller;

import com.example.mathmodel.entity.MyLine;
import com.example.mathmodel.entity.MyPoint;
import com.example.mathmodel.entity.MyRecord;
import com.example.mathmodel.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/bookCount/{x1}/{y1}/{z1}/{l1}/{n1}/{m1}/{x2}/{y2}/{z2}/{l2}/{n2}/{m2}")
    public ResponseEntity bookCount(@PathVariable Integer x1, @PathVariable Integer y1, @PathVariable Integer z1,
                                    @PathVariable Integer l1, @PathVariable Integer n1, @PathVariable Integer m1,
                                    @PathVariable Integer x2, @PathVariable Integer y2, @PathVariable Integer z2,
                                    @PathVariable Integer l2, @PathVariable Integer n2, @PathVariable Integer m2)  {
        try {
            MyPoint point1 = new MyPoint(x1,y1,z1);
            MyPoint point2 = new MyPoint(x2,y2,z2);
            MyLine line1 = new MyLine(point1,l1,n1,m1);
            MyLine line2 = new MyLine(point2,l2,n2,m2);
            MyRecord record = new MyRecord(line1, line2);

            return ResponseEntity.ok(myService.bookCount(record));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getResult/{recordId}")
    public ResponseEntity getOrdersByUserId(@PathVariable Integer recordId)  {
        try {
            return ResponseEntity.ok(myService.getResult(recordId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
