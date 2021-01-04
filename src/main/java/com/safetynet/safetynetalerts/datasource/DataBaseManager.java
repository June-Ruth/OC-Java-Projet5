package com.safetynet.safetynetalerts.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum DataBaseManager {
    /**
     * Use enum for pattern singleton and allow only one DataBase object.
     */
    INSTANCE;

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(DataBaseManager.class);
    /**
     * @see DataBase
     */
    private DataBase dataBase;

    /**
     * Constructor with initialization of DataBase.
     */
    DataBaseManager() {
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

    /**
     * Getter DataBase.
     * @return instance of database created by DataBaseManager
     */
    public DataBase getDataBase() {

        return dataBase;
    }

}
