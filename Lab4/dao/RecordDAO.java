package com.example.mathmodel.dao;

import com.example.mathmodel.entity.MyRecord;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class RecordDAO {
    private final Gson gson = new Gson();

    public MyRecord getRecord(int recordId) throws FileNotFoundException {

        File myFile = new File(String.valueOf(recordId));
        FileReader reader = new FileReader(myFile);

        return gson.fromJson(reader, MyRecord.class);
    }

    public int saveRecord(MyRecord record) throws IOException {
        File myFile = new File(String.valueOf(record.getRecordId()));
        FileWriter writer = new FileWriter(myFile);
        String res = gson.toJson(record, MyRecord.class);
        writer.write(res);
        writer.flush();
        writer.close();
        return record.getRecordId();
    }
}
