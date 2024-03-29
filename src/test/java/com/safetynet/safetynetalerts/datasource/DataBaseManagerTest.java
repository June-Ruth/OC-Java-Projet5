package com.safetynet.safetynetalerts.datasource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataBaseManagerTest {

    private DataBase dataBase;

    @Test
    void getDataBaseTest() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
        assertNotNull(dataBase);
        assertEquals(2, dataBase.getPersons().size());
        assertEquals(2, dataBase.getFireStations().size());
        assertEquals(2, dataBase.getMedicalRecords().size());
    }
}
