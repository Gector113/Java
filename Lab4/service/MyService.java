package com.example.mathmodel.service;

import com.example.mathmodel.dao.RecordDAO;
import com.example.mathmodel.entity.MyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

@Service
public class MyService {
    @Autowired
    private RecordDAO recordDAO;

    public int bookCount(MyRecord record) {
        int id; File file;
        //генеруєм id поки не створиться унікальний
        do {
        id = new Random().nextInt(99999) + 10000;
        file = new File(String.valueOf(id));
        } while (file.exists());
        // сеттимо ідентифікатор запису
        record.setRecordId(id);
        try {
            //повертаємо id запису користувачу, що відправив запит на обрахунок
            return recordDAO.saveRecord(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
}

    public MyRecord getResult(Integer record) {
        try {
            return recordDAO.getRecord(record);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
