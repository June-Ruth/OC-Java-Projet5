package com.safetynet.safetynetalerts.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum DataBaseManager {

    INSTANCE;

    private static DataBase dataBase;


    DataBaseManager() {
        init();
    }

    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource("data.json");
        assert url != null;
        File source = new File(url.getFile());
        try {
            dataBase = objectMapper.readValue(source, DataBase.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataBase getDataBase() {
        return dataBase;
    }

}
